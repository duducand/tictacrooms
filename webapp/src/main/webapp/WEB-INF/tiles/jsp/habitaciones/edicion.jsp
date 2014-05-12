<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:importAttribute name="titulo"/>
<h1><spring:message code="${titulo}"/></h1>
<form:form method="post" commandName="habitacion" id="habitacion-form">
	<table class="form">
        <tbody>
            <tr>
                <th><span>Precio: </span></th>
                <td>
                    <form:input path="precio" id="precio"/>
                    <form:errors path="precio" id="precio"/>
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "precio",
                        widgetType: "dijit.form.TextBox"
                    }));
                    </script>
                </td>
            </tr>
            <tr>
                <th><span>Dirección: </span></th>
                <td>
                    <form:input path="direccion" id="direccion"/>
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "direccion",
                        widgetType: "dijit.form.Textarea",
                        widgetAttrs: {
                            style:{width: 400}
                        }
                    }));
                    </script>
                </td>
            </tr>
        </tbody>
    </table>
	<p class="buttons">
		<form:hidden path="id" />
		<input id="guardar" type="submit" name="_eventId_guardar" value="<spring:message code="formulario.guardar" />" />
		<script type="text/javascript">
		Spring.addDecoration(new Spring.ElementDecoration({
			elementId: "guardar",
			widgetType: "dijit.form.Button",
			widgetAttrs: {label: dojo.byId("guardar").value}
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

