package com.wx.Pojo;

import lombok.Data;

import java.util.List;


public class PageBean {
    private Long total;
    private List rows;


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public PageBean() {
    }

    public PageBean(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }
}
