<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- Meto código css en la estructura para que se imponga al css de digit y se puedan ver los campos disabled en chrome -->
	<style type="text/css">
		.dj_webkit .dijitTextBoxDisabled input {
			color: grey;
		}
	</style>
	
<tiles:importAttribute name="titulo"/>
<h1><spring:message code="${titulo}"/></h1>
<form:form method="post" commandName="usuario" id="usuario-form">
	<table class="form">
		<tbody>
			<tr>
				<th><span><spring:message code="usuarios.login" /></span></th>
				<td>
					<form:input path="login" id="login" />
					<form:errors path="login" cssClass="error" />
					<script type="text/javascript">
					Spring.addDecoration(new Spring.ElementDecoration({
						elementId: "login",
						widgetType: "dijit.form.TextBox"
					}));
					</script>
				</td>
			</tr>
			<tr>
                <th><span>Clave</span></th>
                <td>
                    <form:input type="password" path="claveUsuario" id="claveUsuario" />
                    <form:errors path="claveUsuario" cssClass="error" />
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "claveUsuario",
                        widgetType: "dijit.form.TextBox"
                    }));
                    </script>
                </td>
            </tr>
			<tr>
                <th><span>Nombre:</span></th>
                <td>
                    <form:input path="nombre" id="nombre"/>
                    <form:errors path="nombre" cssClass="error" />
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "nombre",
                        widgetType: "dijit.form.TextBox"
                    }));
                    </script>
                </td>
            </tr>
            <tr>
                <th><span>Apellidos:</span></th>
                <td>
                    <form:input path="apellidos" id="apellidos"/>
                    <form:errors path="apellidos" cssClass="error" />
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "apellidos",
                        widgetType: "dijit.form.TextBox"
                    }));
                    </script>
                </td>
            </tr>
            <tr>
                <th><span>Dirección:</span></th>
                <td>
                    <form:input path="direccion" id="direccion"/>
                    <form:errors path="direccion" cssClass="error" />
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "direccion",
                        widgetType: "dijit.form.TextBox"
                    }));
                    </script>
                </td>
            </tr>
            <tr>
                <th><span>Población:</span></th>
                <td>
                    <form:input path="poblacion" id="poblacion"/>
                    <form:errors path="poblacion" cssClass="error" />
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "poblacion",
                        widgetType: "dijit.form.TextBox"
                    }));
                    </script>
                </td>
            </tr>
            <tr>
                <th><span>Provincia:</span></th>
                <td>
                    <form:input path="provincia" id="provincia"/>
                    <form:errors path="provincia" cssClass="error" />
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "provincia",
                        widgetType: "dijit.form.TextBox"
                    }));
                    </script>
                </td>
            </tr>
            <tr>
                <th><span>Código Postal:</span></th>
                <td>
                    <form:input path="codigoPostal" id="codigoPostal"/>
                    <form:errors path="codigoPostal" cssClass="error" />
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "codigoPostal",
                        widgetType: "dijit.form.TextBox"
                    }));
                    </script>
                </td>
            </tr>
            <tr>
                <th><span>Cuenta Bancaria:</span></th>
                <td>
                    <form:input path="cuentaBancaria" id="cuentaBancaria"/>
                    <form:errors path="cuentaBancaria" cssClass="error" />
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "cuentaBancaria",
                        widgetType: "dijit.form.TextBox"
                    }));
                    </script>
                </td>
            </tr>
            <tr>
                <th><span>Correo Electrónico:</span></th>
                <td>
                    <form:input path="email" id="email"/>
                    <form:errors path="email" cssClass="error" />
                    <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId: "email",
                        widgetType: "dijit.form.TextBox"
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
