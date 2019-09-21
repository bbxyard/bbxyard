package com.bbxyard.sfb.swagger.config;


import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Documentation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bbxyard.sfb.swagger.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("标题: XX公司_用户信息管理系统_接口文档")
                .description("描述: 用于管理集团旗下公司的人员信息,具体包括XXX,XXX模块...")
                .termsOfServiceUrl("https://bbxyard.com")
                .version("1.0")
                .contact(new Contact("bbxyard", "https://bbxyard.com", "boxu@yvhai.com"))
                .build();
    }

}
