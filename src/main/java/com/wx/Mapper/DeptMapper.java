package com.wx.Mapper;

import com.wx.Pojo.Dept;
import com.wx.Pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id, name, create_time, update_time from db02.dept")
    List<Dept> select();

    @Delete("delete  from db02.dept where id=#{id}")
    Integer deleteId(Integer id);

    int insert(Dept dept);

    @Select("select * from db02.dept where id=#{id}")
    List<Dept> selectId(Integer id);


    void update(Dept dept);
}
