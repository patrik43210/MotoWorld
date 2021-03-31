package motoworld.project.config;

import motoworld.project.config.interceptor.RequestHomeInterceptor;
import motoworld.project.config.interceptor.SecondIdkInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final RequestHomeInterceptor requestHomeInterceptor;
    private final SecondIdkInterceptor secondIdkInterceptor;

    public InterceptorConfig(RequestHomeInterceptor requestHomeInterceptor,
                             SecondIdkInterceptor secondIdkInterceptor) {
        this.requestHomeInterceptor = requestHomeInterceptor;
        this.secondIdkInterceptor = secondIdkInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestHomeInterceptor);
        registry.addInterceptor(secondIdkInterceptor);
    }
}
