<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:importAttribute name="title"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Alta nuevo socio</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/dijit/themes/claro/claro.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/dojox/grid/resources/claroGrid.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/dojox/form/resources/CheckedMultiSelect.css" />" />
    
    <script type="text/javascript" src="<c:url value="/resources/dojo/dojo.js" />" djConfig="parseOnLoad: true, locale: 'es'"></script>
    <script type="text/javascript" src="<c:url value="/resources/spring/Spring.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/spring/Spring-Dojo.js" />"></script>
    <script type="text/javascript" src="<c:url value="/tic-tac-rooms.js" />"></script>
    <tiles:insertAttribute name="javascript" />

<script type="text/javascript">
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

</script>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

</head>
<body class="claro tic-tac-rooms">
<div id="content">
<h1>Alta usuario</h1>

<c:if test="${not empty error}">
	<div class="errorblock">
		${error}<br /> 
	</div>
</c:if>
	
<form method="post" action="<c:url value="/registro/form" />" id="usuario-form">
	<table class="form">
		<tbody>
			<tr>
				<th><span><spring:message code="usuarios.login" /></span></th>
				<td>
					<input name="login" id="login" />
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
                    <input type="password" name="claveUsuario" id="claveUsuario" />
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
                    <input name="nombre" id="nombre"/>
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
                    <input name="apellidos" id="apellidos"/>
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
                    <input name="direccion" id="direccion"/>
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
                    <input name="poblacion" id="poblacion"/>
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
                    <input name="provincia" id="provincia"/>
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
                    <input name="codigoPostal" id="codigoPostal"/>
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
                    <input name="cuentaBancaria" id="cuentaBancaria"/>
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
                    <input name="email" id="email"/>
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
		<form:hidden name="id" />
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
</form>
</div>
<div id="footer">
    <div class="message">&nbsp;</div>
</div>
</html>