package com.m51.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{
    private Integer code;
    private String message;
    private T data;
    public static <T> Result<T> Success(){
        return new Result(20000,"Success",null);
    }
    public static <T> Result<T> Success(String message,T data){
        return new Result(20000,message,data);
    }
    public static <T> Result<T> Success(T data){
        return new Result(20000,"Success",data);
    }
    public static <T> Result<T> Fail(){
        return new Result(20001,"Fail",null);
    }
    public static <T> Result<T> Fail(String message){
        return new Result(20001,message,null);
    }
    public static <T> Result<T> fail(int code,String message){return new Result<>(code,message,null);}

}
