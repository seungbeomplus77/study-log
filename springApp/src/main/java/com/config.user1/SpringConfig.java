package com.config.user1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 환경 설정 파일
@ComponentScan(basePackages = {"com.config.user1"})
public class SpringConfig {
}
