package com.atguigu.admin.acutuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component    //实现健康检测类的名字必须是以HealthIndicator结尾
public class MyComHealthIndicator extends AbstractHealthIndicator {
    /**
     * 编写真实的检查方法
     *
     * @param builder
     * @throws Exception
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        //mongodb   获取连接进行测试

        Map<String, Object> map = new HashMap<>();
        //如果这里不是true 则会OUT_OF_SERVICE
        if (1 == 1) {
            builder.status(Status.UP);
//            builder.up();//  标识项目健康 为up状态
            map.put("count", 1);
            map.put("ms", 100);

        } else {
            builder.status(Status.OUT_OF_SERVICE);
//            builder.down();//不健康 宕机状态
            map.put("error", "连接超时");
            map.put("ms", 3000);
        }
        builder.withDetail("code",100).withDetails(map);
    }
}

