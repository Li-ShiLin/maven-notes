package com.sin.imperial.court;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

// 为了让当前微服务对接（注册或发现服务）注册中心
@EnableDiscoveryClient
// SpringBoot 标配注解
@SpringBootApplication
// 扫描通用 Mapper 的 Mapper 接口所在包
// 这个注解全类名：tk.mybatis.spring.annotation.MapperScan
// basePackage 属性：指定要扫描的 Mapper 接口所在的包
@MapperScan(basePackages = "com.sin.imperial.court.mapper")
public class MysqlDataProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlDataProviderApplication.class, args);
    }
}
