package com.dev.schooladmin.base.config;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 接口文档配置
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class BeanConfig {

    @Bean
    public Docket createRestApi() {
        // Swagger 2 使用的是：DocumentationType.SWAGGER_2
        // Swagger 3 使用的是：DocumentationType.OAS_30
        return new Docket(DocumentationType.OAS_30)
                // 定义是否开启swagger，false为关闭，可以通过变量控制
                .enable(true)
                // 将api的元信息设置为包含在json ResourceListing响应中。
                .apiInfo(new ApiInfoBuilder()
                        .title("校园后台管理网站")
                        // 描述
                        .description("校园后台管理网站 RESTful APIs")
                        .contact(new Contact("梁发灿", "http://localhost:8080/", "ifauchard@163.com"))
                        .version("1.0.0")
                        .build())
                // 分组名称
                .groupName("1.0")
                .select()
                // 要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("com.dev.schooladmin.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}

