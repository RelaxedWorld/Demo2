package com.example.demo.listener;

import com.example.demo.listener.event.OperateLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Description
 * Created by zhangsongyu on 2018/8/30 15:01.
 */
@Slf4j
@Component
public class LogListener {
    @EventListener
    public void saveOperateLog(OperateLogEvent operateLogEvent) {
        log.info("记录操作日志");
        log.info(operateLogEvent.getOperateLog().toString());
    }
}
