package cn.itsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @auth: wujiangbo
 * @date: 2022-02-13 12:43
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("cn.itsource.feign")
public class OrderApp1020 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp1020.class);
    }
}