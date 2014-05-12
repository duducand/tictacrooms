<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var habitacion="";
function crear() {
    var eventForm = dojo.byId("eventForm");
    eventForm["_eventId"].value = "crear";
    eventForm["id"].value = "";
    eventForm.submit();
}
function filtrar() {	
	var precio      = dijit.byId("precio").getValue();
	var direccion      = dijit.byId("direccion").getValue();

    var filter = {};
    
    if (precio) 
    {
        filter["precio"] = "%" + precio + "%";        
    }
    if (direccion != "") 
    {
        filter["direccion"] = "%" + direccion + "%";        
    }
    grid.filter(filter);
}
dojo.ready(function() {
	grid.canSort = function(columnIndex) {
        return columnIndex < 2;
     };
});
</script>