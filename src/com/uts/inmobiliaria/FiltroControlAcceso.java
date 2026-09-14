package com.uts.inmobiliaria;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FiltroControlAcceso implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String contexto = httpRequest.getContextPath();
        String ruta = httpRequest.getRequestURI().substring(contexto.length());
        if (session == null || session.getAttribute("idUsuario") == null) {
            httpResponse.sendRedirect(contexto + "/login.jsp?error=sesion");
            return;
        }
        String rol = (String) session.getAttribute("rol");
        boolean permitido = (ruta.startsWith("/cliente/") && "CLIENTE".equals(rol))
                || (ruta.startsWith("/inmobiliaria/") && "INMOBILIARIA".equals(rol))
                || (ruta.startsWith("/administrador/") && "ADMINISTRADOR".equals(rol));
        if (!permitido) {
            httpResponse.sendRedirect(contexto + "/acceso-denegado.jsp");
            return;
        }
        chain.doFilter(request, response);
    }
}