package com.brins.controller;

import com.brins.pojo.Result;
import com.brins.utils.AliYunOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by lipeilin on 2024/10/15.
 */
@RestController
public class FileUploadController {


    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file) throws Exception {
        // 将文件内容存到本地磁盘上
        String originalFilename = file.getOriginalFilename();
        // 使用uuid保证文件名唯一性，防止文件覆盖
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new File("D:\\javaproject\\big-event\\files\\" + fileName));
        String url = AliYunOssUtil.uploadFile(fileName, file.getInputStream());
        return Result.success(url);
    }
}
