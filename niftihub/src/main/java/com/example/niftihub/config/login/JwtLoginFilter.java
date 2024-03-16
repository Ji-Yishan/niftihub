package com.example.niftihub.config.login;

/**
 * @author: Isabella
 * @create: 2023-09-24 01:08
 **/
//public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
//    protected JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
//        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
//        setAuthenticationManager(authenticationManager);
//    }
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException, ServletException {
//        UserDetail user = new ObjectMapper().readValue(req.getInputStream(), UserDetail.class);
//        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//    }
//    @Override
//    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
//        StringBuffer as = new StringBuffer();
//        for (GrantedAuthority authority : authorities) {
//            as.append(authority.getAuthority())
//                    .append(",");
//        }
//        String jwt = Jwts.builder()
//                .claim("authorities", as)//配置用户角色
//                .setSubject(authResult.getName())
//                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
//                .signWith(SignatureAlgorithm.HS512,"decisionmaker@123")
//                .compact();
//        resp.setContentType("application/json;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.write(new ObjectMapper().writeValueAsString(jwt));
//        out.flush();
//        out.close();
//    }
//    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse resp, AuthenticationException failed) throws IOException, ServletException {
//        resp.setContentType("application/json;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.write("登录失败!");
//        out.flush();
//        out.close();
//    }
//}

