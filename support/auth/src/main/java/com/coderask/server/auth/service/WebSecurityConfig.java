package com.coderask.server.auth.service;

import com.coderask.server.auth.model.AuthResponseConstant;
import com.coderask.server.common.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {

        // 忽略文件
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/verification/send").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("api/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write(new ObjectMapper().writeValueAsString(Response.ofSuccess()));
                            out.flush();
                            out.close();
                        }
                ).permitAll()
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                .and().rememberMe().tokenRepository(jdbcTokenRepository())
                .and().exceptionHandling()
                .authenticationEntryPoint(this::authenticationEntryPoint);


//        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
//            HttpServletResponse resp = event.getResponse();
//            resp.setContentType("application/json;charset=utf-8");
//            resp.setStatus(401);
//            PrintWriter out = resp.getWriter();
//            out.write(new ObjectMapper().writeValueAsString(Response.ofFail("您已在另一台设备登录，本次登录已下线!")));
//            out.flush();
//            out.close();
//        }), ConcurrentSessionFilter.class);
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/api/login");
//        ConcurrentSessionControlAuthenticationStrategy sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
//        sessionStrategy.setMaximumSessions(-1);
//        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
        return loginFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
//        auth.inMemoryAuthentication().withUser("lize").password("123").roles("admin");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return myUserDetailsService;
    }


    /**
     * rememberme persistant
     * @return
     */
    @Bean
    JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

//    @Bean
//    SessionRegistryImpl sessionRegistry() {
//        return new SessionRegistryImpl();
//    }


    /**
     * 未登录访问需要登录的资源
     */
    private void authenticationEntryPoint(HttpServletRequest request, HttpServletResponse resp,
                                          AuthenticationException authException) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setStatus(401);
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(Response.ofFail(AuthResponseConstant.RESPONSE_CODE_UNAUTHORIZED)));
        out.flush();
        out.close();
    }
}
