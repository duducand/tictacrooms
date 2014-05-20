<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:importAttribute name="title"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="${title}"/></title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/dijit/themes/claro/claro.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/dojox/grid/resources/claroGrid.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/dojox/form/resources/CheckedMultiSelect.css" />" />
	
	<script type="text/javascript" src="<c:url value="/resources/dojo/dojo.js" />" djConfig="parseOnLoad: true, locale: 'es'"></script>
	<script type="text/javascript" src="<c:url value="/resources/spring/Spring.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/spring/Spring-Dojo.js" />"></script>
	<script type="text/javascript" src="<c:url value="/tic-tac-rooms.js" />"></script>
	<tiles:insertAttribute name="javascript" />
</head>

<body class="claro tic-tac-rooms">
<div id="header">
	<h1>
		<span class="logo-tic-tac-rooms"><img width="200px" src="<c:url value="/img/logo-tic-tac-rooms.jpg"/>" alt="Tic-Tac-Rooms"/></span>
		<span class="titulo-tic-tac-rooms">Tic-Tac-Rooms</span>
	</h1>
	<p class="user">
		<span class="status"><c:out value="${estadoUsuario.usuario.login}" /></span>
		
		<a href="<c:url value="/j_spring_security_logout" />" id="cabecera.salir"><spring:message code="cabecera.salir"/></a>
		<script type="text/javascript">
		Spring.addDecoration(new Spring.ElementDecoration({
			elementId: "cabecera.salir",
			widgetType: "dijit.form.Button",
			widgetAttrs: {
				onClick: function() {window.location = '<c:url value="/j_spring_security_logout" />';}
			}
		}));
		</script>
	</p>
	
	<div class="separator"></div>
	<ul dojoType="dijit.MenuBar" class="menubar">
	   <c:if test="${estadoUsuario.usuario.administrador || estadoUsuario.usuario.trabajador}">
		    <li dojoType="dijit.PopupMenuBarItem">
                <span>Usuarios</span>
                <ul dojoType="dijit.Menu">
                    <li dojoType="dijit.MenuItem" onclick="load('usuarios')"><a href="<c:url value="/usuarios"/>">Usuarios</a></li>
                </ul>
            </li>
	   </c:if>	
		    <li dojoType="dijit.PopupMenuBarItem">
                <span>Mi Cuenta</span>
                <ul dojoType="dijit.Menu">
                    <li dojoType="dijit.MenuItem" onclick="load('micuenta')"><a href="<c:url value="/micuenta"/>">Mi Cuenta</a></li>
                </ul>
            </li>
		
			<li dojoType="dijit.PopupMenuBarItem">
               <span>Habitaciones</span>
               <ul dojoType="dijit.Menu">
                   <li dojoType="dijit.MenuItem" onclick="load('habitaciones')"><a href="<c:url value="habitaciones"/>">Habitaciones</a></li>
               </ul>
           </li>   
        
	</ul>
	
	<script type="text/javascript">
	dojo.query("#header .menubar ul").forEach(function(node, index, arr) {
		dojo.style(node, "display", "none");
	});
	dojo.query("#header .menubar a").forEach(function(node, index, arr) {
		dojo.style(node, "textDecoration", "none");
	});
	</script>
</div>

<c:set var="showMessages" value="false" />
<c:forEach var="message" items="${flowRequestContext.messageContext.allMessages}">
	<c:if test="${message.source == null}">
		<c:set var="showMessages" value="true" />
	</c:if>	
</c:forEach>

<c:if test="${showMessages}">
	<div id="messages">
		<c:forEach var="message" items="${flowRequestContext.messageContext.allMessages}">
			<c:if test="${message.source == null}">
				<c:choose>
					<c:when test="${message.severity == 'FATAL'}">
						<p class="fatal"><spring:message code="mensaje.fatal"/>: ${message.text}</p>
					</c:when>
					<c:when test="${message.severity == 'ERROR'}">
						<p class="error"><spring:message code="mensaje.error"/>: ${message.text}</p>
					</c:when>
					<c:when test="${message.severity == 'WARNING'}">
						<p class="warning"><spring:message code="mensaje.advertencia"/>: ${message.text}</p>
					</c:when>
					<c:when test="${message.severity == 'INFO'}">
						<p class="info"><spring:message code="mensaje.informacion"/>: ${message.text}</p>
					</c:when>
					<c:otherwise>
						<p><spring:message code="mensaje.mensaje"/>: ${message.text}</p>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>
	</div>
</c:if>


<div id="content"><tiles:insertAttribute name="content" /></div>
<div id="footer">
	<div class="message">&nbsp;</div>
</div>
</html>
