package com.example.demo.domains;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Description
 * Created by zhangsongyu on 2018/8/30 14:52.
 */
@Data
@ToString
public class OperateLog {
    /**
     * 业务id
     */
    private Long businessId;
    /**
     * 操作者
     */
    private String opetater;
    /**
     * 操作类型
     */
    private Integer operateType;
    /**
     * 请求ip
     */
    private String userIp;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
}
