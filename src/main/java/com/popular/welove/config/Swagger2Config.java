package com.popular.welove.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zmp
 * @Description:
 * @Date: Created in 2018/12/6 20:38
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${swagger.enabled}")
    private boolean swagger_is_enable;

    @Bean
    public Docket api() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("token").description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swagger_is_enable)
                .apiInfo(apiInfo())
                .globalOperationParameters(pars)
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.popular.welove.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Demo系统API")
                .description("liuhang")
                //服务条款网址
                .termsOfServiceUrl("http://localhost:8080")
                .version("1.0")
                .build();
    }
}
