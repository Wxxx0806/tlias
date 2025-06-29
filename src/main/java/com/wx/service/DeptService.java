package com.wx.service;

import com.wx.Pojo.Dept;

import java.util.List;


public interface DeptService {

    /*
     *此方法实现的是查询所有部门的接口
     */

    List<Dept> list();
    /*
    * 用于删除指定的部门*/
    void delete(Integer id);

    /*
    * 新增部门*/
    void insert(Dept dept);

    List<Dept> selectId(Integer id);

    void update(Dept dept);
}
