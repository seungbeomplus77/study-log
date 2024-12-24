package com.config.prop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.config.prop")
@PropertySource("classpath:com/config/prop/test.properties") // 프로퍼티 파일
public class SpringConfig {

}
