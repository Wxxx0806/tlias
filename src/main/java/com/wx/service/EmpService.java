package com.wx.service;

import com.wx.Pojo.Emp;
import com.wx.Pojo.PageBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /*
    查询所有员工信息*/
    PageBean selectPage(Integer start, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /*批量删除*/
    void deleteId(List<Integer> ids);

    /*插入员工数据*/
    void insert(Emp emp);

    List<Emp> selectById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
