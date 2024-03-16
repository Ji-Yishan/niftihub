package com.example.niftihub.config.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Matcher;

import static com.example.niftihub.uitl.JwtUtils.TOKEN_PATTEN;

/**
 * @author: Isabella
 * @create: 2023-09-24 01:14
 **/
//@WebFilter(urlPatterns = "/testToken", filterName = "jwtFilter")
@Slf4j
//@Component
public class JwtInterceptor implements HandlerInterceptor {

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
    @Override
//  todo 拦截器没办法读，loginservice用不了。验证合法性就行了吧应该，就加一个prefix好了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("执行了拦截器的preHandle方法");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String token= request.getHeader("Authorization");
        if(checkToken(token)){
            log.info("token 验证成功");
        }else {
            log.error("token 验证失败");
            response.getWriter().write("token 验证失败");
        }
        return true;
        //如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    /***
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("执行了拦截器的postHandle方法");
    }

    /***
     * 整个请求结束之后被调用，也就是在DispatchServlet渲染了对应的视图之后执行（主要用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("执行了拦截器的afterCompletion方法");
    }
    public static boolean checkToken(final String token){
        Matcher matcher=TOKEN_PATTEN.matcher(token);
        return matcher.matches();
    }
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        String token= request.getHeader("Authorization");
//        User user = new User();
//        user.setId("1");
//        user.setUsername("hand2020");
//        user.setPassword("123456");
//        boolean flag = JwtUtils.isVerify(token,user);
//        if (flag){
//            filterChain.doFilter(servletRequest,servletResponse);
//        }else {
//            System.out.println("失败。。。。。。。。");
//            response.getWriter().write("失败。。。。。。。。");
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
}
