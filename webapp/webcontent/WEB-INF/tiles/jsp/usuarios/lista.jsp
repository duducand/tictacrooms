<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1><spring:message code="usuarios.titulo"/></h1>
<div dojoType="dojox.data.JsonRestStore" jsId="store" target="<c:url value="/tabla/usuarios" />" idAttribute="id"></div>

<c:if test="${estadoUsuario.usuario.administrador || estadoUsuario.usuario.trabajador }">
	<button type="button" dojoType="dijit.form.Button" onclick="javascript:crearUsuario()">Crear Usuario</button>
	
</c:if>
<c:if test="${estadoUsuario.usuario.administrador }">
    <button type="button" dojoType="dijit.form.Button" onclick="javascript:crearTrabajador()">Crear Trabajador</button>
</c:if>
<table class="filter" id="filter-table" style>
	<tbody>
		<tr>
			<td width="11%"><input style="width: 90%; text-align: center" type="text" id="login" dojoType="dijit.form.TextBox" intermediateChanges="true" onchange="javascript:filtrar()" /></td>
			<td width="12%"><input style="width: 90%; text-align: center" type="text" id="nombre" dojoType="dijit.form.TextBox" intermediateChanges="true" onchange="javascript:filtrar()" /></td>
			<td width="12%"><input style="width: 90%; text-align: center" type="text" id="apellidos" dojoType="dijit.form.TextBox" intermediateChanges="true" onchange="javascript:filtrar()" /></td>
			<td width="9%"><input style="width: 90%; text-align: center" type="text" id="codigoPostal" dojoType="dijit.form.TextBox" intermediateChanges="true" onchange="javascript:filtrar()" /></td>
			<td width="11%"><input style="width: 90%; text-align: center" type="text" id="provincia" dojoType="dijit.form.TextBox" intermediateChanges="true" onchange="javascript:filtrar()" /></td>
			<td width="11%"><input style="width: 90%; text-align: center" type="text" id="poblacion" dojoType="dijit.form.TextBox" intermediateChanges="true" onchange="javascript:filtrar()" /></td>
			<td width="12%"><input style="width: 90%; text-align: center" type="text" id="email" dojoType="dijit.form.TextBox" intermediateChanges="true" onchange="javascript:filtrar()" /></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="30"><div style="width: 30px"></div></td>
			<td width="30"><div style="width: 30px"></div></td>
			<td width="30"><div style="width: 30px"></div></td>
		</tr>
	</tbody>
</table>

<table dojoType="dojox.grid.DataGrid" jsId="grid" store="store" selectionMode="none" style="height: 400px">
	<thead>
		<tr>
			<th field="login" width="11%" styles="text-align: center; ">Usuario</th>
			<th field="nombre" width="12%" styles="text-align: center; ">Nombre</th>
			<th field="apellidos" width="12%" styles="text-align: center; ">Apellidos</th>
			<th field="codigoPostal" width="9%" styles="text-align: center; ">Código Postal</th>
			<th field="provincia" width="11%" styles="text-align: center; ">Provincia</th>
			<th field="poblacion" width="11%" styles="text-align: center; ">Población</th>
			<th field="email" width="12%" styles="text-align: center; ">E-mail</th>
			<th field="fechaBaja" width="11%" styles="text-align: center; ">Fecha Baja</th>
			<th field="perfiles" width="11%" styles="text-align: center; ">Perfiles</th>
			<th get="celdaConsultarUsuario" width="30" styles="text-align: center; ">&nbsp;</th>
			<th get="celdaEditarUsuario" width="30" styles="text-align: center; ">&nbsp;</th>
			<th get="celdaEliminarUsuario" width="30" styles="text-align: center; ">&nbsp;</th>
		</tr>
	</thead>
</table>


<form id="eventForm" action="" method="post">
	<input type="hidden" name="_eventId" value="" />
	<input type="hidden" name="id" value="" />
</form>