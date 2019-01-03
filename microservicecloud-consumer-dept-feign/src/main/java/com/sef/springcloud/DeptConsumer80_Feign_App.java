package com.sef.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
//// 在七佛那个给微服务时，就能去加载我们自定义的Ribbon配置类，从而使配置失效
//@RibbonClient(name="MICROSERVICECLOUD-DEPT", configuration = MySelfRule.class)
@EnableFeignClients(basePackages = {"com.sef.springcloud"})
@ComponentScan("com.sef.springcloud")
public class DeptConsumer80_Feign_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_Feign_App.class, args);
    }
}
