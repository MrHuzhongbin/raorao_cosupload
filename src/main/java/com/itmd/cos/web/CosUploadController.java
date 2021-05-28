package com.itmd.cos.web;

import com.itmd.cos.pojo.vo.Result;
import com.itmd.cos.service.CosUploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CosUploadController {

    @Autowired
    private CosUploadService cosUploadService;

    /**
     * 上传文件
     * @param obj
     * @return
     */
    @PostMapping("object")
    public Result CosUploadObject(@RequestParam("object")MultipartFile obj){
        String url = null;
        try {
            url = cosUploadService.CosUploadObject(obj);
            if(StringUtils.isBlank(url)){
                return new Result(false, 400, "上传失败", url);
            }
            return new Result(true, 200, "上传成功" , url);
        } catch (Exception e) {
            return new Result(false, 400, e.getMessage(),url);
        }
    }
}
