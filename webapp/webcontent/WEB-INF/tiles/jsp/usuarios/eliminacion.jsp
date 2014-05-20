<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1><tiles:importAttribute name="titulo"/></h1>

<form:form method="post" commandName="usuario">
	<h2><spring:message code="usuarios-eliminacion.pregunta" arguments="${usuario.login}"/></h2>
	<p class="buttons">
		<form:hidden path="id" />
		<input id="eliminar" type="submit" name="_eventId_eliminar" value="<spring:message code="formulario.eliminar" />" />
		<script type="text/javascript">
		Spring.addDecoration(new Spring.ElementDecoration({
			elementId: "eliminar",
			widgetType: "dijit.form.Button",
			widgetAttrs: {label: dojo.byId("eliminar").value}
		}));
		</script>
		<input id="cancelar" type="submit" name="_eventId_cancelar" value="<spring:message code="formulario.cancelar" />" />
		<script type="text/javascript">
		Spring.addDecoration(new Spring.ElementDecoration({
			elementId: "cancelar",
			widgetType: "dijit.form.Button",
			widgetAttrs: {label: dojo.byId("cancelar").value}
		}));
		</script>
	</p>
</form:form>