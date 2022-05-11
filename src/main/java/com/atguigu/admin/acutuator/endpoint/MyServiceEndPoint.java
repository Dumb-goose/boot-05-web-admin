package com.atguigu.admin.acutuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

//自定义节点
@Endpoint(id = "myservice")
@Component
public class MyServiceEndPoint {
    @ReadOperation  //端点的读操作
    public Map getDockerInfo() {
        //端点的读操作
        return Collections.singletonMap("dockerInfo", "docker starter ......");
    }

    @WriteOperation
    public void stopDocker() {
        System.out.println("docker stopped ......");
    }
}
