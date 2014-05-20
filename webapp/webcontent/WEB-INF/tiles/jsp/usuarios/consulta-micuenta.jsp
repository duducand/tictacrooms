<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1><spring:message code="usuarios-consulta-micuenta.titulo"/></h1>
<div dojoType="dojox.data.JsonRestStore" jsId="storeFormatoFisico" target="<c:url value="/tabla/prestamo_fisico" />" idAttribute="id"></div>
<div dojoType="dojox.data.JsonRestStore" jsId="storeFormatoLogico" target="<c:url value="/tabla/prestamo_logico" />" idAttribute="id"></div>
<form:form method="post" commandName="usuario" id="usuario-form">
	<table class="form">
		<tbody>
			<tr>
				<th><span><spring:message code="usuarios.login" /></span></th>
				<td>
					${usuario.login }
				</td>
			</tr>
			<tr>
                <th><span>Nombre:</span></th>
                <td>
                	${usuario.nombre }
                </td>
            </tr>
            <tr>
                <th><span>Apellidos:</span></th>
                <td>
                	${usuario.apellidos }
                </td>
            </tr>
            <tr>
                <th><span>Dirección:</span></th>
                <td>
                	${usuario.direccion }
                </td>
            </tr>
            <tr>
                <th><span>Población:</span></th>
                <td>
                	${usuario.poblacion }
                </td>
            </tr>
            <tr>
                <th><span>Provincia:</span></th>
                <td>
                	${usuario.provincia }
                </td>
            </tr>
            <tr>
                <th><span>Código Postal:</span></th>
                <td>
                	${usuario.codigoPostal }
                </td>
            </tr>
            <tr>
                <th><span>Cuenta Bancaria:</span></th>
                <td>
                	${usuario.cuentaBancaria }
                </td>
            </tr>
            <tr>
                <th><span>Correo Electrónico:</span></th>
                <td>
                	${usuario.email }
                </td>
            </tr>
            <tr>
                <td colspan="2" height="15px">
	                <form:hidden path="id" />
					<input id="editar" type="submit" name="_eventId_editar" value="<spring:message code="formulario.editar" />" />
					<script type="text/javascript">
					Spring.addDecoration(new Spring.ElementDecoration({
						elementId: "editar",
						widgetType: "dijit.form.Button",
						widgetAttrs: {label: dojo.byId("editar").value}
					}));
					</script>
                </td>
            </tr>
		</tbody>
	</table>
	
	
	<p class="buttons">
	</p>
</form:form>
