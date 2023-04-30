package com.xxxx.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * api接口文档在线生成配置
 */
@Configuration//声明配置类
@EnableSwagger2//启动api在线接口生成
public class Swagger2 {

    //设置api文档的基本信息
    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("用户管理接口API文档")
                .version("1.0")
                .build();

    }
    @Bean
    public Docket creatRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xxxx.springboot.controller"))//扫描的控制器
                .paths(PathSelectors.any())
                .build();
    }
}
