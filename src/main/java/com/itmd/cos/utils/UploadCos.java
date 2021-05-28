package com.itmd.cos.utils;

import com.itmd.cos.config.UploadProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
@Slf4j
public class UploadCos {
    @Autowired
    private COSClient cosClient;
    @Autowired
    private UploadProperties uploadProperties;
    @Autowired
    private IdWorker idWorker;
    public String upload(MultipartFile file){
        // 指定文件将要存放的存储桶
        String bucketName = uploadProperties.getBucketName();
        InputStream fileInputStream = null;
        try {
            fileInputStream = file.getInputStream();
        } catch (Exception e) {
            log.error("获取文件流失败！");
            return null;
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 设置输入流长度
        objectMetadata.setContentLength(file.getSize());
        // 设置 Content type, 默认是 application/octet-stream
        objectMetadata.setContentType(file.getContentType());
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = idWorker.nextId()+file.getName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key,fileInputStream ,objectMetadata);
        PutObjectResult putObjectResult = null;
        try {
            putObjectResult = cosClient.putObject(putObjectRequest);
        } catch (Exception e) {
            log.error("putObject 上传文件失败！");
            return null;
        }
        return "https://"+putObjectRequest.getBucketName()+".cos.ap-chongqing.myqcloud.com/"+putObjectRequest.getKey();
    }
}
