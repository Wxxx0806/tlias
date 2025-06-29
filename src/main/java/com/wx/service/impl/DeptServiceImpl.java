package com.wx.service.impl;

import com.wx.Mapper.DeptMapper;
import com.wx.Mapper.EmpMapper;
import com.wx.Pojo.Dept;
import com.wx.Pojo.DeptLog;
import com.wx.service.DeptLogService;
import com.wx.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {

        return deptMapper.select();
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        try {
            deptMapper.deleteId(id);//第一步只是删除部门信息,并没有删除部门下的员工信息
//            int i = 1 / 0;
            empMapper.deleteByDeptId(id);//删除对应的员工信息


        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，解散的部门是" + id + "号部门");
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void insert(Dept dept) {

        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public List<Dept> selectId(Integer id) {
        return deptMapper.selectId(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}
