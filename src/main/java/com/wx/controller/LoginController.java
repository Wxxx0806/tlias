package com.wx.controller;

import com.wx.Pojo.Emp;
import com.wx.Pojo.Result;
import com.wx.service.EmpService;
import com.wx.utils.jwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
//@Slf4j
public class LoginController {

    public static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private EmpService empService;


    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {


        log.info("登录界面员工的信息{}", emp);
        Emp e = empService.login(emp);

        if (e != null) {
            Map<String, Object> claim = new HashMap<>();
            claim.put("id", e.getId());
            claim.put("name", e.getName());
            claim.put("username", e.getUsername());

            String jwt = jwtUtils.generateJWT(claim);
            return Result.success(jwt);
        }


        return e != null ? Result.success(e) : Result.error("用户名或密码错误");
    }
}
