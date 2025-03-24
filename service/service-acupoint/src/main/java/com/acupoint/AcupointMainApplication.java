package com.acupoint;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
/**
 * @Description: 主启动类
 * @Author: WangZhiWei
 * @Time: 2025/3/24 11:08
 */
@SpringBootApplication
@MapperScan("com.acupoint.mapper")
public class AcupointMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcupointMainApplication.class, args);
    }
}
