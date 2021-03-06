package org.ymx.sb_qr_code.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.ymx.sb_qr_code.interceptor.AppInterceptor;

import javax.annotation.Resource;

/**
 * @desc: 拦截器+静态资源配置
 * @author: YanMingXin
 * @create: 2022/6/3-9:02
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Resource
    private AppInterceptor appInterceptor;

    @Value("${file.upload.path}")
    private String filePath;

    @Value("${file.upload.relative}")
    private String fileRelativePath;

    @Value("${server.sys}")
    private String sys;

    /**
     * 配置拦截器和拦截、放行路径
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInterceptor)
                .addPathPatterns("/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        String f = "file:/";
        if ("Linux".equals(sys)) {
            f = "file:";
        }
        registry.addResourceHandler(fileRelativePath).
                addResourceLocations(f + filePath);
    }
}
