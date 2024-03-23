package com.safe.safecampusbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  /images/** 映射到哪里去,前端里面的路由      file:/home/fileUpload/ 表示需要访问linux系统文件所在的文件夹路径名称
        registry.addResourceHandler("/**").addResourceLocations("file:/Users/ahao/project/resource");
    }
}
