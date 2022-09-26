<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<input hidden="true" id="contieneNombre" value="<%=request.getParameter("nombre-usuario")%>" />
	<input hidden="true" id="contieneClave" value="<%=request.getParameter("clave-usuario")%>" />
	<input hidden="true" id="contieneObservaciones" value="<%=request.getParameter("observaciones")%>" />

	<h1>Formulario</h1>
	<h3>Aplicacion: ${aplicacion}</h3>
	<h3>Usuario: ${nombre}</h3>
	<h3>Clave: ${clave}</h3>
	<h3>${observaciones}</h3>
	<hr/>
	<form action="login" method="post">
		<!-- Datos aplicacion -->
		<%if(request.getParameter("nombre-aplicacion") != null){%>
			<input hidden name="nombre-aplicacion" value="${aplicacion}">
		<%}else{%>
			<p>Nombre aplicación: <input type="text" id="nombre-aplicacion" name="nombre-aplicacion" size="40" required> (Durará todo el tiempo que la App este corriento)</p>
		<%}%>
		
		<!-- Datos Nombre -->
		<%if(request.getParameter("nombre-usuario") != null){%>
			<input hidden type="text" name="nombre-usuario" value="${nombre}">
		<%}else{%>
			<p>Nombre usuario: <input type="text" id="nombre-usuario" name="nombre-usuario" size="40" required> (Para todo lo que dure la sesión)</p>
		<%}%>
		
		<!-- Datos Clave -->
		<%if(request.getParameter("clave-usuario") != null){%>
			<input hidden name="clave-usuario" value="${clave}">
		<%}else{%>
			<p>Clave usuario: <input type="text" id="clave-usuario" name="clave-usuario" size="40" required> (Para todo lo que dure la sesión)</p>
		<%}%>
		
		<p>Observaciones: <input type="text" id="observaciones" name="observaciones"size="40"> (Dato sólo para la petición)</p>
		<p hidden><input type="text" id="primerAviso" name="primerAviso" value="${primerAviso}" size="40"></p>
		<p type="text" id="nota"></p>
		<hr/>
		<p>
			<input type="submit" value="Enviar" name="enviar">
			<input type="reset" value="Borrar">
		</p>
	</form>
</body>
<script>
document.addEventListener("DOMContentLoaded", function(event) {
	recuperarDatos()
});

function recuperarDatos(){
	let esPrimerAviso = document.getElementById("primerAviso").value;
	let tieneObservaciones = document.getElementById("contieneObservaciones").value;
	if ((tieneObservaciones == "null" || tieneObservaciones == "" || tieneObservaciones == null) && esPrimerAviso != ""){
		document.getElementById("nota").innerHTML = "* Falta por rellenar: las Observaciones para confirmar pulse Enviar";
	}	
}    
</script>
</html>