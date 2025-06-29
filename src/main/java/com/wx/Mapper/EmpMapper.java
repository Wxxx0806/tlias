package com.wx.Mapper;

import com.wx.Pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /*
     * 查询总记录数*/
    @Select("select count(*) from db02.emp")
    Long count();

    /*
     分页查询*/
    @Select("select * from db02.emp limit #{start} , #{end}")
    List<Emp> page(Integer start, Integer pageSize);

    /*
     * 根据条件查询*/
    List<Emp> list(Integer start, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /*
     * 批量删除员工信息*/
//    @Delete("delete from db02.emp where id in (#{id})")
    void delete(List<Integer> ids);


    void insert(Emp emp);

    @Select("select * from db02.emp where id=#{id}")
    List<Emp> selectById(Integer id);


    void update(Emp emp);

    /*
     * 根据用户名和密码进行查询*/
    @Select("select * from db02.emp where username=#{username} and password=#{password}")
    Emp getUserNameAndPassWord(Emp emp);

    @Delete("delete from db02.emp where dept_id=#{id}")
    void deleteByDeptId(Integer id);
}
