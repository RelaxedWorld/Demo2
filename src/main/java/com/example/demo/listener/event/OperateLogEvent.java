package com.example.demo.listener.event;

import com.example.demo.domains.OperateLog;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * Description
 * Created by zhangsongyu on 2018/8/30 15:01.
 */
@Data
public class OperateLogEvent extends ApplicationEvent {
    private OperateLog operateLog;

    public OperateLogEvent(Object source) {
        super(source);
    }
}
