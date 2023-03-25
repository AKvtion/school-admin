package com.dev.schooladmin.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sa-Token相关配置
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    /**
     * 注册sa-token拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaRouteInterceptor((req, resp, handler) -> {
            // 获取配置文件中的白名单路径
            List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
            // 登录认证：除白名单路径外均需要登录认证
            SaRouter.match(Collections.singletonList("/**"), ignoreUrls, StpUtil::checkLogin);
        })).addPathPatterns("/**");
    }
}
