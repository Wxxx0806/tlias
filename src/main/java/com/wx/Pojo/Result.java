package com.wx.Pojo;

import lombok.Data;


public class Result {
    private Integer code;//响应码：1为成功,0表示失败
    private String msg;//响应字符串
    private Object data;//返回数据

    public static Result success() {//增删改成功响应
        return new Result(1, "success", null);
    }

    public static Result error(String msg) {//失败响应
        return new Result(0, msg, null);
    }

    public static Result success(Object data) {//查询成功响应
        return new Result(1, "success", data);
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
