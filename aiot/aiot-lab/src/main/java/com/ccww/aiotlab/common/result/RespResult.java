package com.ccww.aiotlab.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespResult<T> implements Serializable{

    // 状态码
    private Integer code;
    // 描述信息
    private String msg;
    private Long count;
    // 数据
    private T data;
}


