package com.example.demo.listener;

import com.example.demo.listener.event.OperateLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Description
 * Created by zhangsongyu on 2018/8/30 15:01.
 */
@Slf4j
@Component
public class LogListener {
    //声明一个异步任务,不加这句话会阻塞主要任务
    @Async
    @EventListener
    public void saveOperateLog(OperateLogEvent operateLogEvent) {
        log.info("记录操作日志");
        //休眠10秒钟测试异步效果
        sleep(10);
        log.info("记录操作日志完成");
        log.info(operateLogEvent.getOperateLog().toString());
    }

    private void sleep(Integer seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
