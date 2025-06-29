package com.wx.controller;

import com.wx.Pojo.Emp;
import com.wx.Pojo.PageBean;
import com.wx.Pojo.Result;
import com.wx.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmpController {
    private static Logger log = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    private EmpService empService;


    /*
    查询所有员工信息
     */
    @GetMapping("/emps")
    //@RequestParam这个注释可以设置请求默认值参数
    public Result selectAll(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            String name, Short gender,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("分页参数：{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.selectPage(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    //    批量删除员工信息
    @DeleteMapping("/emps/{ids}")
    public Result deleteId(@PathVariable List<Integer> ids) {

        empService.deleteId(ids);

        log.info("批量删除员工信息，员工编号为:{}", ids);
        return Result.success();
    }

    /*
     * 对员工进行插入操作*/
    @PostMapping("/emps")
    public Result insert(@RequestBody Emp emp) {

        empService.insert(emp);
        log.info("插入员工数据");
        return Result.success();
    }

    /*根据ID进行查询*/
    @GetMapping("/emps/{id}")
    public Result selectById(@PathVariable Integer id) {

        List<Emp> empList = empService.selectById(id);
        log.info("根据ID进行查询");
        return Result.success(empList);
    }

    /*
     * 修改员工数据*/
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp) {

        empService.update(emp);
        log.info("修改员工数据");
        return Result.success();
    }
}
