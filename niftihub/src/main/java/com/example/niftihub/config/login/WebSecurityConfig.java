package com.example.niftihub.config.login;

//import com.example.decisionmaker.config.login.JwtLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Isabella
 * @create: 2023-09-24 01:16
 **/
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new JwtInterceptor());
        registration.addPathPatterns("/**"); //所有路径都被拦截
        registration.excludePathPatterns(    //添加不拦截路径
                "/login",                    //登录路径
                "/register",
                "/test"
        );
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin")
//                .password("123").roles("admin")
//                .and()
//                .withUser("sang")
//                .password("456")
//                .roles("user");
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/hello").hasRole("user")
////                .antMatchers("/admin").hasRole("admin")
////                .antMatchers(HttpMethod.POST, "/login").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()),UsernamePasswordAuthenticationFilter.class)
////                .addFilterBefore(new JwtInterceptor(), UsernamePasswordAuthenticationFilter.class)
////                .csrf().disable();
////    }
//
//    @Override
//    public void init(SecurityBuilder builder) throws Exception {
//
//    }
//
//    @Override
//    public void configure(SecurityBuilder builder) throws Exception {
//
//    }
}