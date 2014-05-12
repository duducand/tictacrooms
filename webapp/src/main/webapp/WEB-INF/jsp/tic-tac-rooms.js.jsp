<%@ page language="java" contentType="text/javascript; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

dojo.require("dijit.Dialog");
dojo.require("dijit.MenuBar");
dojo.require("dijit.PopupMenuBarItem");
dojo.require("dijit.Menu");
dojo.require("dijit.MenuItem");
dojo.require("dijit.PopupMenuItem");
dojo.require("dijit.form.Form");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.DateTextBox");
dojo.require("dijit.form.TimeTextBox");
dojo.require("dijit.form.Textarea");
dojo.require("dijit.form.Button");
dojo.require("dojox.form.BusyButton");
dojo.require("dijit.form.Select");
dojo.require("dijit.form.FilteringSelect");
dojo.require("dijit.form.RadioButton");
dojo.require("dijit.layout.ContentPane");
dojo.require("dojox.grid.DataGrid");
dojo.require("dojox.data.JsonRestStore");
dojo.require("dijit.layout.AccordionPane");
dojo.require("dijit.layout.AccordionContainer");
dojo.require("dijit.layout.TabContainer");
dojo.require("dijit.layout.BorderContainer");
dojo.require("dijit.form.CheckBox");
dojo.require("dojo.fx");
dojo.require("dijit.Tree");
dojo.require("dijit.tree.ForestStoreModel");
dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojo.data.ItemFileReadStore");

function load(destination) {
	dojo.doc.location.href = destination;
}

function resizeFilter(grid, filterTable) {
	var filterCells = dojo.query("tbody > tr > td", filterTable);
	var totalWidth = 0;
	for (var columnIndex = 0; true; columnIndex++) {
		var cell = grid.getCell(columnIndex);
		if (cell) {
			filterCells[columnIndex].style.width = cell.unitWidth;
			if (totalWidth != null && cell.unitWidth.substring(cell.unitWidth.length - 2) == "px") {
				totalWidth += parseInt(cell.unitWidth) + 2;
			} else {
				totalWidth == null;
			}
		} else {
			break;
		}
	}
	if (totalWidth != null) {
		filterTable.style.width = totalWidth + "px";
	}
}

function crear() {
	var eventForm = dojo.byId("eventForm");
	eventForm["_eventId"].value = "crear";
	eventForm["id"].value = "";
	eventForm.submit();
}

function celdaConsultar(rowIndex, item, permiso) {
		return new dijit.form.Button({
			label: "<spring:message code="formulario.consultar" />",
			iconClass: "customIconShow",
            showLabel:false,
			onClick: function() {
				var eventForm = dojo.byId("eventForm");
				eventForm["_eventId"].value = "consultar";
				eventForm["id"].value = grid.store.getValue(item, "id");
				eventForm.submit();
			}
		});
}

function celdaEditar(rowIndex, item, permiso) {
		return new dijit.form.Button({
			label: "<spring:message code="formulario.editar" />",
			iconClass: "customIconEditar",
            showLabel:false,
			onClick: function() {
				var eventForm = dojo.byId("eventForm");
				eventForm["_eventId"].value = "editar";
				eventForm["id"].value = grid.store.getValue(item, "id");
				eventForm.submit();
			}
		});
}

function celdaEliminar(rowIndex, item, permiso) {
		return new dijit.form.Button({
			label: "<spring:message code="formulario.eliminar" />",
			iconClass: "customIconBorrar",
			showLabel:false,
			onClick: function() {
				var eventForm = dojo.byId("eventForm");
				eventForm["_eventId"].value = "eliminar";
				eventForm["id"].value = grid.store.getValue(item, "id");
				eventForm.submit();
			}
		});
}

function celdaRestaurar(rowIndex, item){
    return new dijit.form.Button({
        label: "<spring:message code="formulario.restaurar" />",
        iconClass: "customIconRestaurar",
        showLabel:false,
        onClick: function() {
            var eventForm = dojo.byId("eventForm");
            eventForm["_eventId"].value = "restaurar";
            eventForm["id"].value = grid.store.getValue(item, "id");
            eventForm.submit();
        }
    });
}



