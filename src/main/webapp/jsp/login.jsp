<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>Este es el intento: ${intento2}
	<c:set var = "nombre" scope = "session" value = "${nombre}"/>
	<c:if test = "${nombre == 'Juan'}">
		<h2>Usuario identificado.</h2>
		<hr/>
		<h3>Aplicacion: ${aplicacion}, contexto de aplicacion.</h3>
		<h3>Usuario: ${nombre}, contexto de sesión.</h3>
		<form action="logout" method="post">
			<input type="submit" value="Logout" name="Logout">
		</form>
		<hr/>
	</c:if>

	<c:if test = "${nombre != 'Juan'}">
		<h2>Login</h2>
		<hr/>
		<form action="login" method="post">
			<p>Nombre usuario: <input type="text" id="nombre-usuario" name="nombre-usuario" size="40" required> (Para todo lo que dure la sesión)</p>
			<p>Clave usuario: <input type="text" id="clave-usuario" name="clave-usuario" size="40" required> (Para todo lo que dure la petición)</p>
			<input type="submit" value="Enviar" name="enviar">
			<input type="reset" value="Borrar">
			<p><input hidden type="text" id="intento2" name="intento2" value="${intento2}" size="40"></p>
			<hr/>
		</form>
	</c:if>
</body>
<script>
document.addEventListener("DOMContentLoaded", function(event) {

});

</script>
</html>