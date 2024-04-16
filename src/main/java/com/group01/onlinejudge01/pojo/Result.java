package com.group01.onlinejudge01.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data; //数据

    public static <E> Result<E> success(E data){
        return new Result(200, "成功", data);
    }
    public static Result error(String mes){return new Result(500, mes, null);}
    public static Result success(String mes){
        return new Result(200,mes,null);
    }
    public static Result success(){
        return new Result(200,"成功",null );
    }
}
