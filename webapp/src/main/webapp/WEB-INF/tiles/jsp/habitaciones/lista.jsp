<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1><spring:message code="tic-tac-rooms.msg.habitaciones.titulo" /></h1>

<div dojoType="dojox.data.JsonRestStore" jsId="storeHabitacion" target="<c:url value="/tabla/habitacion" />" idAttribute="id"></div>

<button type="button" dojoType="dijit.form.Button" onclick="javascript:crear()"><spring:message code="formulario.crear" /></button>
<table class="filter" id="filter-table">
    <tbody>
        <tr>
            <td width="30%"><input  style="width: 90%" type="text" id="precio" dojoType="dijit.form.TextBox" intermediateChanges="true" onchange="javascript:filtrar()" /></td>
            <td width="70%"><input  style="width: 90%" type="text" id="direccion" dojoType="dijit.form.TextBox" intermediateChanges="true" onchange="javascript:filtrar()" /></td>
            <td width="30"><div     style="width: 30px"></div></td>
            <td width="30"><div     style="width: 30px"></div></td>
        </tr>
    </tbody>
</table>

<table dojoType="dojox.grid.DataGrid" jsId="grid" store="storeHabitacion" selectionMode="none" style="height: 250px" >
    <thead>
        <tr>
            <th field="precio"                  width="30%">Precio</th>
            <th field="direccion"         width="70%">Dirección</th>
            <th get="celdaConsultar" width="30" styles="text-align: center; " ></th>
            <th get="celdaEditar" width="30" styles="text-align: center; "></th>
        </tr>
    </thead>    
</table>

<form id="eventForm" action="" method="post">
    <input type="hidden" name="_eventId" value="" />
    <input type="hidden" name="id" value="" />
</form>