package com.example.trainimageannotation.vo;

import java.util.List;

/**
 * @deacriptation 前端界面【表格】统一返回数据
 * @author LENOVO
 */
public class TableResult<T> {
    private int code;
    private String msg;
    private Long count;
    private List<T> data;

    public TableResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
