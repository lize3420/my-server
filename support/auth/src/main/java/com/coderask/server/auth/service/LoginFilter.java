package com.coderask.server.auth.service;

import com.coderask.server.auth.model.AuthResponseConstant;
import com.coderask.server.auth.model.LoginUserInfo;
import com.coderask.server.auth.protocol.LoginRequest;
import com.coderask.server.auth.protocol.LoginResponse;
import com.coderask.server.common.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @作者 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    {
        setSuccessHandler();
        setFailHandler();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new IllegalArgumentException();
        }
        if (!request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            throw new IllegalArgumentException();

        }
        String username = null;
        String password = null;
        LoginRequest loginData;
        try {
            var reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String data = reader.readLine();
            reader.close();
            loginData = new ObjectMapper().readValue(data, LoginRequest.class);
            username = loginData.getLoginName();
            password = loginData.getPassword();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);
        setDetails(request, authRequest);
//        MyUserDetails principal = new MyUserDetails();
//        principal.setUsername(username);
//        sessionRegistry.registerNewSession(request.getSession(true).getId(), principal);
        return this.getAuthenticationManager().authenticate(authRequest);

    }

    public void setSuccessHandler() {
        setAuthenticationSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    var user = (LoginUserInfo) authentication.getPrincipal();
                    var result = new LoginResponse();
                    result.setUser(user.toLoginUser());
                    String s = new ObjectMapper().writeValueAsString(authentication);
                    out.write(s);
                    out.flush();
                    out.close();
                }
        );
    }

    public void setFailHandler() {
        setAuthenticationFailureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    Response respBean = Response.ofFail(AuthResponseConstant.RESPONSE_CODE_PASSWORD_ERROR);
                    if (exception instanceof LockedException) {
                        respBean.setCode(AuthResponseConstant.RESPONSE_CODE_USER_DISABLED);
                    } else if (exception instanceof AccountExpiredException) {
                        respBean.setCode(AuthResponseConstant.RESPONSE_CODE_USER_DISABLED);
                    } else if (exception instanceof DisabledException) {
                        respBean.setCode(AuthResponseConstant.RESPONSE_CODE_USER_DISABLED);
                    }
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                    out.flush();
                    out.close();
                }
        );
    }

}
