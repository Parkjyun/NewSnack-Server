package com.newsnack.www.newsnackserver.config;

import com.newsnack.www.newsnackserver.NewsnackserverApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = NewsnackserverApplication.class)
public class FeignClientConfig {
}
