package motoworld.project.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class SecondIdkInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory
            .getLogger(SecondIdkInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {

        if(request.getRequestURL().toString().contains("profile")){
            logger.info("second preHandle Profile");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

        if(request.getRequestURL().toString().contains("profile")){
            logger.info("second postHandle Profile");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {

        if(request.getRequestURL().toString().contains("profile")){
            logger.info("second afterCompletion Profile");
        }

    }
}

