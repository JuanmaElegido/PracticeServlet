<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:set var = "UsuarioValido" scope = "session" value = "${UsuarioValido}"/>
<c:set var = "intentosQuedan" scope = "session" value = "${maxNumeroIntentos-intento2}"/>
<div class="container">
    <div id="darkModeDom"></div>
    <script src="https://cdn.jsdelivr.net/gh/JuanmaElegido/DarkMode/js/darkMode.js"></script>
	<div class="row mt-5">
		<div class="row mt-5">
			<div class="col-auto">
				<h1>${aplicacion}</h1>
			</div>
		</div>
	    <div class="col-6">
			<c:if test = "${UsuarioValido =='true'}">
				<h5>Usuario identificado.</h5>
				<hr/>
				<h5>Aplicacion: ${aplicacion}, contexto de aplicacion.</h5>
				<h5>Usuario: ${nombre}, contexto de sesión.</h5>
				<form action="logout" method="post">
					<input type="submit" value="Logout" name="Logout">
				</form>
				<hr/>
			</c:if>
		</div>
	</div>
	<div class="row">
	    <c:if test = "${UsuarioValido !='true'}">
	    	<p/>
			<hr/>
	    	<div class="col-6">
				<div class="col-6">
			    	<h3>Login</h3>
			    </div>		
				<form action="login" method="post">
					<div class="input-group input-group-sm mb-3">
						<span class="input-group-text" id="claveUsuario">Nombre usuario</span>
						<input class="form-control" type="text" id="nombreUsuario"
						 		name="nombreUsuario" size="40" value="${param.nombreUsuario}"
								required> (Para todo lo que dure la sesión)</p>
					</div>
					<div class="input-group input-group-sm mb-3">
						<span class="input-group-text" id="claveUsuario">Clave usuario</span>
						<input class="form-control" type="text" id="claveUsuario"
								name="claveUsuario" size="40" value="${param.claveUsuario}"
								required> (Para todo lo que dure la petición)</p>
					</div>
					<input class="btn btn-primary" type="submit" value="Enviar" name="enviar">
					<input class="btn btn-danger" type="reset" value="Borrar">
					<p>
					<input hidden type="text" id="intento2" name="intento2"
							value="${intento2}" size="40">
					</p>
				</form>
				<div class="row">
				    <div class="col">
					    <c:if test = "${intentosQuedan != maxNumeroIntentos}">
							<h5 class="text-danger">Error en el login.</h5>
							<h5 class="text-danger">Quedan ${intentosQuedan} intentos más.</h5>
					    </c:if>
				    </div>
				</div>
	    	</div>
			<hr/>
		</c:if>
	    <div class="col-6">
	    </div>				
	</div>
</div>
</body>
<script>
document.addEventListener("DOMContentLoaded", function(event) {

});

</script>
</html>