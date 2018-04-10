package com.ganster.cms.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganster.cms.core.pojo.LogEntry;
import com.ganster.cms.core.service.LogService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

@Component
public class IPUtil {
    private static IPUtil ipUtil;
    private static LinkedBlockingQueue<Integer> queue;
    @Resource
    private
    LogService logService;

    public void makeAddr(Integer logId) {
        queue.offer(logId);
    }

    private void processId() {
        Stream.generate(() -> {
            try {
                return queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }).forEach((Integer id) -> {
            LogEntry logEntry = logService.selectByPrimaryKey(id);
            String info = logEntry.getLogInfo();
            try {
                ObjectMapper mapper = new ObjectMapper();
                Map map = mapper.readValue(info, Map.class);
                String ip = (String) map.get("ip");
                String addr = getAddr(ip);
                map.put("addr",addr);
                logEntry.setLogInfo(mapper.writeValueAsString(map));
                logService.updateByPrimaryKeyWithBLOBs(logEntry);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    //TODO
    public String getAddr(String ip){
        return "";
    }
    @PostConstruct
    private void init() {
        ipUtil = this;
        ipUtil.logService = this.logService;
        queue = new LinkedBlockingQueue<>();
        new Thread(this::processId).start();
    }
}
