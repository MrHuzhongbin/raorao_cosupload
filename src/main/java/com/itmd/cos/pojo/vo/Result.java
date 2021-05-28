package com.itmd.cos.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {

    private Boolean isSuccess;
    private int code;
    private String msg;
    private Object data;

}
