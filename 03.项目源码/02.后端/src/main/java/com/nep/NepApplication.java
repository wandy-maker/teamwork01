package com.nep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NepApplication {

    public static void main(String[] args) {
        SpringApplication.run(NepApplication.class, args);
        System.out.println("======================================");
        System.out.println("  东软环保公众监督系统后端启动成功！");
        System.out.println("  访问地址: http://localhost:8080/nepm");
        System.out.println("======================================");
    }
}
