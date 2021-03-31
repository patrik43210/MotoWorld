package motoworld.project.config;

import motoworld.project.security.SecurityUserDetails;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final SecurityUserDetails securityUserDetails;


    public AppSecurityConfig(PasswordEncoder passwordEncoder, SecurityUserDetails securityUserDetails) {
        this.passwordEncoder = passwordEncoder;
        this.securityUserDetails = securityUserDetails;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests().
                antMatchers("/js/**", "/css/**", "/images/**").permitAll().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                        antMatchers("/", "/user/login", "/user/register","/user/login-error").permitAll().
                        antMatchers("/**").authenticated().
                and().
                        formLogin().
                        loginPage("/user/login").
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                        defaultSuccessUrl("/home").
                        failureForwardUrl("/user/login-error").
                and().
                logout().
                logoutUrl("/user/logout").
                        logoutSuccessUrl("/").
                        invalidateHttpSession(true).
                        deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(securityUserDetails).
                passwordEncoder(passwordEncoder);
    }
}
