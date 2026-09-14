<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/conexion.jspf" %>
<%@ include file="/WEB-INF/jspf/utilidades.jspf" %>
<% String tituloPagina="Acceso denegado"; String ctx=request.getContextPath(); Integer idUsuarioSesion=(Integer)session.getAttribute("idUsuario"); String rolSesion=(String)session.getAttribute("rol"); String nombreSesion=(String)session.getAttribute("nombre"); %>
<%@ include file="/WEB-INF/jspf/cabecera.jspf" %>
<div class="text-center py-5"><i class="bi bi-shield-lock display-3 text-danger"></i><h1 class="h2 mt-3">No tienes permisos para esta sección</h1><p class="text-muted">Tu sesión sigue activa, pero este espacio pertenece a otro rol.</p><a class="btn btn-primary" href="<%= ctx %>/index.jsp">Volver al inicio</a></div>
<%@ include file="/WEB-INF/jspf/pie.jspf" %>