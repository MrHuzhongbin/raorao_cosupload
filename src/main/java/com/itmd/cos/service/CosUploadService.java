package com.itmd.cos.service;

import com.itmd.cos.config.UploadProperties;
import com.itmd.cos.pojo.vo.Result;
import com.itmd.cos.utils.UploadCos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class CosUploadService {
    @Autowired
    private UploadProperties properties;

    @Autowired
    private UploadCos uploadCos;

    public String CosUploadObject(MultipartFile obj) {

            //校验文件类型
            String contentType = obj.getContentType();
            if(!properties.getAllowTypes().contains(contentType)){
                throw new RuntimeException("文件类型不符合要求");
            }
            //校验内容的开头是否满足audio的格式 TODO

            //上传到腾讯cos服务器,并返回返回链接
            return uploadCos.upload(obj);
    }
}
