package com.wx.service.impl;

import com.wx.Mapper.EmpMapper;
import com.wx.Pojo.Emp;
import com.wx.Pojo.PageBean;
import com.wx.Pojo.Result;
import com.wx.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean selectPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {

        /*
         * 1.获取总记录数*/

        Long count = empMapper.count();
        /*
         * 2.获取分页查询结果列表*/

        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.page(start, pageSize);
        List<Emp> line = empMapper.list(start, pageSize, name, gender, begin, end);
        /*
         * 封装PageBean对象*/
        return new PageBean(count, line);
    }

    @Override
    public void deleteId(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public List<Emp> selectById(Integer id) {
        return empMapper.selectById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    /*
     * 员工登录*/
    @Override
    public Emp login(Emp emp) {
        return empMapper.getUserNameAndPassWord(emp);
    }
}
