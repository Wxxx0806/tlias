package com.wx.controller;

import com.wx.Pojo.Dept;
import com.wx.Pojo.Emp;
import com.wx.Pojo.Result;
import com.wx.service.DeptService;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@Slf4j:作用相当于下面的日志定义的代码
@RestController
@RequestMapping("/depts")
public class DeptController {

    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    public DeptService deptService;

    /*
     *1.查询所有员工的请求
     */

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)//指定请求方式为GET
    @GetMapping
    public Result list() {

        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /*
     *2.删除员工的请求
     */

    @DeleteMapping("/{id}")
    //@PathVariable这个注解是将上面请求中的id传递给Integer所定义的参数★
    public Result deptDelete(@PathVariable Integer id) {

        deptService.delete(id);

        log.info("删除特定部门:" + id);
        return Result.success(id);
    }

    /*
     *3.添加部门
     */

    @PostMapping
    //@RequestBody将请求中的json请求体中的参数携带过来
    //Dept dept：接收页面传递过来的参数
    public Result deptInsert(@RequestBody Dept dept) {

        deptService.insert(dept);

        log.info("添加部门 :{}", dept);
        return Result.success();
    }

    /*
    * 根据ID查询*/
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        List<Dept> list=deptService.selectId(id);
        log.info("根据ID查询部门");
        return Result.success(list);
    }

    /*
    * 修改数据*/
    @PutMapping
    public Result update(@RequestBody Dept dept){

        deptService.update(dept);
        log.info("修改数据");
        return Result.success(dept);
    }
}
