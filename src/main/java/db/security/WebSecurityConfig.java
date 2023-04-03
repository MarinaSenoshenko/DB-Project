package db.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //TODO разобраться с авторизацией
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("password").roles("ADMIN")
                .and()
                .withUser("user").password("123").roles("USER")
                 .and()
                .withUser("user1").password("123").roles("USER")
                .and()
                .withUser("user2").password("123").roles("USER")
                .and()
                .withUser("user3").password("123").roles("USER")
                 .and()
                .withUser("user4").password("123").roles("USER")
                .and()
                .withUser("user5").password("123").roles("USER")
                .and()
                .withUser("user6").password("123").roles("USER")
                .and()
                .withUser("user7").password("123").roles("USER")
                .and()
                .withUser("user8").password("123").roles("USER")
                .and()
                .withUser("user9").password("123").roles("USER")
                .and()
                .withUser("user10").password("123").roles("USER")
                .and()
                .withUser("user11").password("123").roles("USER")
                .and()
                .withUser("user12").password("123").roles("USER")
                .and()
                .withUser("user13").password("123").roles("USER")
                .and()
                .withUser("user14").password("123").roles("USER");;
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/main/*/add").hasRole("ADMIN")
                .antMatchers("/main/*/delete").hasRole("ADMIN")
                .antMatchers("/main/*/update").hasRole("ADMIN")
                .and().formLogin()
                .and().csrf().disable();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
