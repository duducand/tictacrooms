<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
dojo.addOnLoad(function() {
	grid.canSort = function(columnIndex) {
        return columnIndex < 9;
     };
});
function crearUsuario() {
	var eventForm = dojo.byId("eventForm");
	eventForm["_eventId"].value = "crearSocio";
	eventForm["id"].value = "";
	eventForm.submit();
}
function crearTrabajador() {
    var eventForm = dojo.byId("eventForm");
    eventForm["_eventId"].value = "crearTrabajador";
    eventForm["id"].value = "";
    eventForm.submit();
}

function filtrar() {
	
	var login = dijit.byId("login").getValue();
	var nombre = dijit.byId("nombre").getValue();
	var apellidos = dijit.byId("apellidos").getValue();
	var codigoPostal = dijit.byId("codigoPostal").getValue();
	var provincia = dijit.byId("provincia").getValue();
	var poblacion = dijit.byId("poblacion").getValue();
	var email = dijit.byId("email").getValue();

	var filter = {};
	if (login) {
		filter["login"] = "%" + login + "%";
	}
	if (nombre) {
        filter["nombre"] = "%" + nombre + "%";
    }
	if (apellidos) {
        filter["apellidos"] = "%" + apellidos + "%";
    }
	if (codigoPostal) {
        filter["codigoPostal"] = "%" + codigoPostal + "%";
    }
	if (provincia) {
        filter["provincia"] = "%" + provincia + "%";
    }
	if (poblacion) {
        filter["poblacion"] = "%" + poblacion + "%";
    }
	if (email) {
        filter["email"] = "%" + email + "%";
    }

	grid.filter(filter);
}

function celdaConsultarUsuario(rowIndex, item, permiso) {
    var disabled = false;
    if(item &&(item.trabajador || item.administrador)){
    	<c:if test="${!estadoUsuario.usuario.administrador }">
    	disabled = true;
    	</c:if>
    }
	return new dijit.form.Button({
        label: "<spring:message code="formulario.consultar" />",
        iconClass: "customIconShow",
        showLabel:false,
        disabled: disabled,
        onClick: function() {
            var eventForm = dojo.byId("eventForm");
            eventForm["_eventId"].value = "consultar";
            eventForm["id"].value = grid.store.getValue(item, "id");
            eventForm.submit();
        }
    });
}

function celdaEditarUsuario(rowIndex, item, permiso) {
	var disabled = false;
	if(item &&(item.trabajador || item.administrador)){
        <c:if test="${!estadoUsuario.usuario.administrador }">
        disabled = true;
        </c:if>
    }
    return new dijit.form.Button({
        label: "<spring:message code="formulario.editar" />",
        iconClass: "customIconEditar",
        showLabel:false,
        disabled: disabled,
        onClick: function() {
            var eventForm = dojo.byId("eventForm");
            eventForm["_eventId"].value = "editar";
            eventForm["id"].value = grid.store.getValue(item, "id");
            eventForm.submit();
        }
    });
}

function celdaEliminarUsuario(rowIndex, item, permiso) {
	var disabled = false;
	if(item &&(item.trabajador || item.administrador)){
        <c:if test="${!estadoUsuario.usuario.administrador }">
        disabled = true;
        </c:if>
    }
	if(item && item.fechaBaja==null){
		return new dijit.form.Button({
	        label: "<spring:message code="formulario.eliminar" />",
	        iconClass: "customIconBorrar",
	        showLabel:false,
	        disabled: disabled,
	        onClick: function() {
	            var eventForm = dojo.byId("eventForm");
	            eventForm["_eventId"].value = "eliminar";
	            eventForm["id"].value = grid.store.getValue(item, "id");
	            eventForm.submit();
	        }
	    });
	}else{
		return new dijit.form.Button({
            label: "<spring:message code="formulario.restaurar" />",
            iconClass: "customIconRestaurar",
            showLabel:false,
            disabled: disabled,
            onClick: function() {
                var eventForm = dojo.byId("eventForm");
                eventForm["_eventId"].value = "restaurar";
                eventForm["id"].value = grid.store.getValue(item, "id");
                eventForm.submit();
            }
        });
	}
    
}

</script>

