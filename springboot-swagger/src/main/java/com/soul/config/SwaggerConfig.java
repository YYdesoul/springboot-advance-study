package com.soul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2 // 开启swagger2
public class SwaggerConfig {
    // Config bean
    @Bean
    public Docket docket() {
        // new自己的状态码信息链表
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        Arrays.stream(StateCodeEnum.values()).forEach(stateCodeEnum ->
        {
            responseMessageList.add(
                    new ResponseMessageBuilder()
                            .code(stateCodeEnum.getCode())
                            .message(stateCodeEnum.getMsg())
                            .build());
        });

        // 根据环境来开启swagger
        boolean flag = true;

        return new Docket(DocumentationType.SWAGGER_2)
                // 使用自己的全局状态码
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
//                .useDefaultResponseMessages(true)
                .apiInfo(ApiInfo())
                .groupName("soul")  // 设置包名
                .enable(flag) // 设置什么环境下开启swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.soul"))   // 设置扫描包
                .build();
    }

    private ApiInfo ApiInfo() {
        Contact contact = new Contact("Yu Yan", "", "yuyan@qq.com");

        return new ApiInfo("My Api Documentation",
                "My Api",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
