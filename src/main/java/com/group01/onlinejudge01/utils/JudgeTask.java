package com.group01.onlinejudge01.utils;

import com.group01.onlinejudge01.pojo.JudgeRequest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import redis.clients.jedis.Jedis;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class JudgeTask implements Runnable {
    private JudgeRequest judgeRequest;
    private String url;
    private String timeStr;

    public JudgeTask(JudgeRequest judgeRequest, String url) {
        this.judgeRequest = judgeRequest;
        this.url = url;
        this.timeStr = LocalDateTime
                .now().
                format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                );
    }

    private void send() {
        System.out.println("http://" + this.url + "/send");
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.put("codeUrl", Collections.singletonList(judgeRequest.getCodeUrl()));
        formData.put("submitId", Collections.singletonList(judgeRequest.getSubmitId()));
        formData.put("testUrl", Collections.singletonList(judgeRequest.getTestUrl()));
        formData.put("subtestNum", Collections.singletonList(String.valueOf(judgeRequest.getSubtestNum())));
        formData.put("memoryLimit", Collections.singletonList(String.valueOf(judgeRequest.getMemoryLimit())));
        formData.put("timeLimit", Collections.singletonList(String.valueOf(judgeRequest.getTimeLimit())));
        formData.put("isContest", Collections.singletonList(String.valueOf(judgeRequest.getIsContest())));
        formData.put("problemType", Collections.singletonList(String.valueOf(judgeRequest.getProblemType())));

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(this.url.split(":")[0])
                .port(this.url.split(":")[1])// 目标服务器地址
                .path("/send"); // 目标路径
        URI uri = builder.build().toUri();
        System.out.println(uri);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 创建请求实体
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        // 发送POST请求
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

        // 处理响应
        if (response.getStatusCode() == HttpStatus.OK) {
            // 请求成功，处理响应体
            System.out.println("Response body: " + response.getBody());
        } else {
            // 请求失败，处理错误
            System.out.println("Request failed with status: " + response.getStatusCode());
        }
    }
    /*
     * 判题回馈算法机制
     * 2秒一次心跳
     * status = 0: in queue等待期间，假如在这个状态下进行150次心跳(即5分钟后)redis上依然没有数据则超时重发，状态不变。
     * status = 0时假如检测到redis上有数据，则status = 1进入判题模式
     * status = 1: 假如submitnum再运行到最后一个测试点之前消失了，鉴定为服务宕机，重新发送数据并且status = 0，进入等待期间
     * status = 1且submitid不为0，鉴定为提交代码不正确，status = 2并且将数据存入数据库，结束线程
     * status = 1且submitbnum是最后一个测试点且submitid为0，鉴定为accept，status = 2并且将数据存入数据库
     * 线程运行期间，外部请求判题数据从redis上读取，线程结束后从数据库读取数据。
     */

    @Override
    public void run() {
        Jedis jedis = JedisLinkPool.getJedis();
        String submitId = this.judgeRequest.getSubmitId();
        String submitIdNum = submitId + "num";
        isRunning.add(submitId);
        this.send();

        jedis.setex(submitId + "time", 10, this.timeStr);
        Integer status = 0;
        Integer cnt = 0;
        Integer res = 0;
        Integer passedNum = 0;
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                isRunning.del(submitId);
                break;
            }
            cnt++;
            jedis.setex(submitId + "time", 10, this.timeStr);
            if (status == 0) {
                if (cnt >= 150) {
                    this.send();
                    cnt = 0;
                } else if (jedis.exists(submitId) && jedis.exists(submitIdNum)) {
                    status = 1;
                    cnt = 0;
                }
            } else if (status == 1) {
                if (!jedis.exists(submitId) || !jedis.exists(submitIdNum)) {
                    status = 0;
                    this.send();
                    cnt = 0;
                } else {
                    res = Integer.parseInt(jedis.get(submitId));
                    passedNum = Integer.parseInt(jedis.get(submitIdNum));
                    if (passedNum == this.judgeRequest.getSubtestNum() || res != 0) {
                        break;
                    }
                }
            }
        }
        isRunning.del(submitId);
        System.out.println("res:" + res);
        System.out.println("submitId:" + submitId);
        System.out.println("timeStr:" + timeStr);
    }
}
