package com.sakury.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

/**
 * @author Zhang
 * @package com.sakury.config
 * @date 2024/7/19 12:37
 */
/**
 * 安全配置类，用于配置Spring Security的各个方面。
 */
@Configuration
public class SecurityConfiguration {

    /**
     * 配置安全过滤链，定义如何处理不同的HTTP请求。
     *
     * @param http Security配置对象，用于构建安全过滤链。
     * @return 返回配置后的SecurityFilterChain对象。
     * @throws Exception 如果配置过程中出现错误。
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(conf->conf
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(conf -> conf
                        .loginProcessingUrl("/api/auth/login")
                        .failureHandler(this::onAuthenticationFailure)
                        .successHandler(this::onAuthenticationSuccess)
                )
                .logout(conf -> conf
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf->conf
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
    }

    /**
     * 处理注销成功后的逻辑。
     *
     * @param httpServletRequest HTTP请求对象。
     * @param httpServletResponse HTTP响应对象。
     * @param authentication 当前的认证对象。
     */
    private void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse,
                                 Authentication authentication) {
        // 注销成功后的处理逻辑可以在这里实现
    }

    /**
     * 处理认证成功后的逻辑。
     *
     * @param httpServletRequest HTTP请求对象。
     * @param httpServletResponse HTTP响应对象。
     * @param authentication 当前的认证对象。
     * @throws IOException 如果在写响应时发生错误。
     */
    private void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Authentication authentication) throws IOException {
        httpServletResponse.getWriter().write("Success");
    }

    /**
     * 处理认证失败后的逻辑。
     *
     * @param httpServletRequest HTTP请求对象。
     * @param httpServletResponse HTTP响应对象。
     * @param e 认证异常对象。
     * @throws IOException 如果在写响应时发生错误。
     */
    private void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         AuthenticationException e) throws IOException {
        httpServletResponse.getWriter().write("Failure");
    }
}

