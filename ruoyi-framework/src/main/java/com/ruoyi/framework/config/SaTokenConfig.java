package com.ruoyi.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;

/**
 * Sa-Token 配置类
 * 
 * @author ruoyi
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    /**
     * 注册 Sa-Token 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> {
            // 指定一条 match 规则
            SaRouter
                    // 拦截所有路由
                    .match("/**")
                    // 排除登录接口
                    .notMatch("/login", "/logout", "/register", "/captchaImage")
                    // 排除静态资源
                    .notMatch("/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**")
                    // 排除 Swagger
                    .notMatch("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**",
                            "/doc.html", "/v3/api-docs/**", "/swagger-ui/**")
                    // 执行认证函数：校验是否登录
                    .check(r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");
    }
}
