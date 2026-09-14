<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/conexion.jspf" %>
<%@ include file="/WEB-INF/jspf/utilidades.jspf" %>
<% session.invalidate(); response.sendRedirect(request.getContextPath()+"/index.jsp?msg=Sesion%20cerrada"); %>