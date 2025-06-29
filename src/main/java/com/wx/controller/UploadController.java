package com.wx.controller;

import com.wx.Pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
public class UploadController {

    private static Logger log = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {

        //获取提交文件的原始名
        String originalFilename = image.getOriginalFilename();

        //如何保证接收到的文件不重名 --uuid(通用唯一识别符)
        int index = originalFilename.indexOf(".");
        String substring = originalFilename.substring(index);
        String newFile = UUID.randomUUID().toString() + substring;
        log.info("新的文件名为{}", newFile);

        //将接收到的方法保存到本地的磁盘目录
        image.transferTo(new File("D:\\images\\" + newFile));

//        log.info("文件上传模块:{},{},{}", username, age, image);
        return Result.success();
    }

}


