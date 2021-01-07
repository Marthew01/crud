package com.atsuwell.springbootweb.config;

import com.atsuwell.springbootweb.compoment.LoginHandlerIncepter;
import com.atsuwell.springbootweb.compoment.MyLocaleResver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 功能拓展
 */
@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {

    /**
     * 拦截器  除了登录  其他请求一律拦截 必须登录
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerIncepter())
//                拦截所有请求   放行静态资源
                .addPathPatterns("/**")
//               放行登录请求
                .excludePathPatterns("/index.html","/","/user/login","/asserts/**","/webjars/**","/static/**");
    }

    /**
     * 视图控制器
     * @return
     */
    @Bean
    public MyConfig myMvcConfig() {
        MyConfig myMvcConfig = new MyConfig() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };

        return myMvcConfig;
    }

    /**
     * 注册国际化
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResver();
    }
}
