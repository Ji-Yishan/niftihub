package com.example.niftihub.config.login;

import cn.hutool.json.JSONUtil;
import com.example.niftihub.uitl.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JwtTokenFilter extends OncePerRequestFilter {


    private static String[] allowUrls;
    private static String[] adminUrls;
    private static final String tokenHeader = "Authorization";

    @Value("${niftihub.allow-url}")
    private void setAllowUrls(String[] allowUrls){
        JwtTokenFilter.allowUrls = allowUrls;
    }
    @Value("${niftihub.admin-url}")
    private void setAdminUrls(String[] adminUrls){
        JwtTokenFilter.adminUrls = adminUrls;
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        log.info("经过tokenFilter");
        log.info("{}请求：{}",request.getMethod(),request.getRequestURI());
        String token = request.getHeader(tokenHeader);

        if(token == null && matchUrl(request.getRequestURI(),allowUrls)){
            log.info("无需验证");
            filterChain.doFilter(request, response);
        }
        //todo 这里可以判断用户的Authorization来决定接下来的操作
        JwtUtils.getAuthorization(token);
        //验证是否正确
        if(!JwtUtils.isVerify(token)){
            //todo 改用result
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter writer = response.getWriter();
            Map<String,Object> map =new HashMap<>();
            map.put("code",401);
            writer.write(JSONUtil.toJsonStr(map));
            writer.flush();
            return;
        }
        //如果为管理员链接
        if(matchUrl(request.getRequestURI(),adminUrls)&&JwtUtils.getLevel(token)==0){
            //todo 改用result
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter writer = response.getWriter();
            Map<String,Object> map =new HashMap<>();
            map.put("code",401);
            writer.write(JSONUtil.toJsonStr(map));
            writer.flush();
            return;
        }
        filterChain.doFilter(request, response);
    }

    /***
     * 用来判断是否需要token，在yaml文件中配置
     * @param requestURI 要判断的路径，如"/login"
     * @return 返回true则无需验证
     */
    private boolean matchUrl(String requestURI,String[] urls) {
        for(String allowUrl:urls){
            // 首先检查是否是完全匹配的情况
            if (requestURI.equals(allowUrl)) {
                return true;
            }
            // 然后检查是否有通配符的情况
            if (allowUrl.endsWith("/**")) {
                String pattern = allowUrl.substring(0, allowUrl.length() - 3);
                return requestURI.startsWith(pattern);
            }
        }
        // 如果以上条件都不满足，则不匹配
        return false;
    }

}
