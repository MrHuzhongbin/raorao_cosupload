package com.itmd.cos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "raorao.upload")
public class UploadProperties {
    private String bucketName;
    private List<String> AllowTypes;
}
