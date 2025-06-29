package com.wx.Mapper;

import com.wx.Pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {

    @Insert("insert into db02.dept_log (create_time, description) VALUES (#{createTime},#{description})")
    void insert(DeptLog deptLog);
}
