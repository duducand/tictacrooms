<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1><spring:message code="excepcion.titulo" /></h1>
<%
Exception exception = (Exception) pageContext.findAttribute("flowExecutionException");
if (exception != null) {
	%><pre><%
	exception.printStackTrace(new PrintWriter(out));
	%></pre><%
}
%>
