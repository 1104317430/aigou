package cn.cqlyy.aigou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.cqlyy.aigou.mapper")
@EnableFeignClients(basePackages = "cn.cqlyy.aigou")
public class ProductApplication8002 {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication8002.class, args);
    }
}
