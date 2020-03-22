package com.sxt.controller;

import com.sxt.utils.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @program: fastdfs-demo
 * @author: 雷哥
 * @create: 2020-01-03 10:08
 **/

@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;


    /**
     * 作上传
     */
    @RequestMapping("doUpload")
    public Map<String,Object> doUpload(MultipartFile mf){
        System.out.println(mf.getOriginalFilename());
        Map<String, Object> map = uploadService.upload(mf);
        return map;
    }

}
