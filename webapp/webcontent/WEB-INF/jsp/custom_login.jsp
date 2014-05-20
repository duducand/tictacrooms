<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:importAttribute name="title"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bienvenido a Tic-Tac-Rooms</title>



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


<body onload='document.f.j_username.focus();' class="claro tic-tac-rooms">
	<div id="content">
	<h1>Bienvenido a Tic-Tac-Rooms</h1>
	<h1>
		<img width="200px" src="img/logo-tic-tac-rooms.jpg" alt="Tic-Tac-Rooms"/>
	</h1>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Ha habido un error en el acceso, intentelo de nuevo.<br /> 
		</div>
	</c:if>

	<form method='POST' name='f' action="<c:url value='j_spring_security_check' />" >

		<table>
			<tr>
				<td>Usuario:</td>
				<td><input id="j_username" type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Contraseña:</td>
				<td><input id="j_password" type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input id="enviar" name="Acceder" type="submit" value="Acceder" />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input id="reset" name="Restablecer" type="reset" value="Restablecer" />
				</td>
			</tr>
		</table>

	</form>
	<a href="registro">Crear usuario</a>
	
	</div>
	<div id="footer">
	   <div class="message">&nbsp;</div>
	</div>

</body>
</html>