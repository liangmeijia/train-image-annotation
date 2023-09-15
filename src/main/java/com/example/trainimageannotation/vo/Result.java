package com.example.trainimageannotation.vo;

/**
 * @description 统一返回结果
 * @author LENOVO
 */
public class Result {
    private int code;
    private String info;

    public Result() {
    }

    public Result(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
