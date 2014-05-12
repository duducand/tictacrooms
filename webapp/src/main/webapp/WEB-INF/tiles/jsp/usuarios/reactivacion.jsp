<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1><tiles:importAttribute name="titulo"/></h1>

<form:form method="post" commandName="usuario">
	<h2><spring:message code="usuarios-reactivacion.pregunta" arguments="${usuario.login}"/></h2>
	<p class="buttons">
		<form:hidden path="id" />
		<input id="reactivar" type="submit" name="_eventId_reactivar" value="<spring:message code="formulario.reactivar" />" />
		<script type="text/javascript">
		Spring.addDecoration(new Spring.ElementDecoration({
			elementId: "reactivar",
			widgetType: "dijit.form.Button",
			widgetAttrs: {label: dojo.byId("reactivar").value}
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