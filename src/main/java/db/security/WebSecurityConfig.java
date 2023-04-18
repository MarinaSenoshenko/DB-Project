package db.security;

import db.api.service.user.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/main/*/add").hasAuthority("ADMIN")
                .antMatchers("/main/*/delete").hasAuthority("ADMIN")
                .antMatchers("/main/*/update").hasAuthority("ADMIN")
                .antMatchers("/main/users").hasAnyAuthority("ADMIN", "ATHLETE", "TRAINER")
                .antMatchers("/main/**").hasAnyAuthority("USER", "ADMIN", "ATHLETE", "TRAINER")
                .antMatchers("/").hasAnyAuthority("USER", "ADMIN", "ATHLETE", "TRAINER")
                .and().formLogin()
                .loginPage("/login")
                .permitAll()
                .and().csrf().disable();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
