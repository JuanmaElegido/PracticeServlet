<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mensaje enviado</title>
</head>
<body>
	<div>
		<h1>Pr�ctica de SERVLET</h1>
		<hr/>
		<h4>Aplicaci�n: ${aplicacion}, guardado en el contexto de aplicacion.</h4> 
		<h4>Usuario: ${nombre}, guardado en el contexto de sesi�n.</h4>
		<h4>Clave: ${clave}, guardado en el contexto de sesi�n.</h4>
		<h4>Observaciones: ${observacion}, guardado en el contexto de petici�n.</h4>
		<hr/>
		<button onclick="location.href='/web-login'">Volver</button>
	</div>
</body>
</html>