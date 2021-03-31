package motoworld.project.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestHomeInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory
            .getLogger(RequestHomeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {

        if(request.getRequestURL().toString().contains("home")){
            logger.info("preHandle HOME");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

        if(request.getRequestURL().toString().contains("home")){
            logger.info("postHandle HOME");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {

        if(request.getRequestURL().toString().contains("home")){
            logger.info("afterCompletion HOME");
        }

    }
}
