package com.safe.safecampusbackend.config;

import cn.hutool.json.JSONObject;
import com.safe.safecampusbackend.util.jwt.JWTUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JWTFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String header = request.getHeader("Authorization");

        boolean isBearerExist = header == null || !header.startsWith("Bearer ");

        // 根据请求路径进行不同的验证
        if (request.getRequestURI().startsWith("/api/admin/")) {
            if (!isBearerExist) {
                String token = header.replace("Bearer ", "");
                if (validateAdminJWT(token)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            checkFailure(response);
            return;
        } else if (request.getRequestURI().startsWith("/api/protected/")) {
            if (!isBearerExist) {
                String token = header.replace("Bearer ", "");
                if (validateProtectedJWT(token)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            checkFailure(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void checkFailure(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 返回401 Unauthorized状态码
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.set("code", 401);
        response.getWriter().write(jsonResponse.toString());
    }

    private boolean validateAdminJWT(String token) {
        return JWTUtil.validateJWT(token) && JWTUtil.extractSubject(token).equals("admin");
    }

    private boolean validateProtectedJWT(String token) {
        return JWTUtil.validateJWT(token);
    }
}