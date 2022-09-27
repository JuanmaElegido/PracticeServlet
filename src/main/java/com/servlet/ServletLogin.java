package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modelo.IUsuariosFachada;
import com.modelo.UsuariosDTO;
import com.modelo.UsuariosFachada;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(name = "login", description = "Comprobacion de credenciales del usuario", urlPatterns = { "/login" })
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
		// *** 1º CAPTURA DE INFORMACION DE LA PETICION ENVIADA ***
		String valorNombre = peticion.getParameter("nombre-usuario");
		String valorClave = peticion.getParameter("clave-usuario");
		String stringIntento = peticion.getParameter("intento2");
		boolean formularioValido = true;
		boolean usuarioValido = false;
		
		// *** 2º CONVERSION ***
		String valorAplicacion = "APP-"+valorNombre.toUpperCase();
		Long numeroIntento;
		
		if (stringIntento == null || stringIntento == "") {
			numeroIntento = 1L;
		} else {
			numeroIntento = Long.parseLong(stringIntento);
		}
				
		// *** 3º VALIDACION *** 
		if (null == valorNombre || null == valorClave ||
					valorNombre.isEmpty() || valorClave.isEmpty() ||
					valorClave.equals("") || valorClave.length() < 5) {
			// Formulario no valido (Cuando faltan datos obligatorios por completar)
			formularioValido = false;	
//			peticion.setAttribute("primerAviso", "avisado");
		} 

		
		// *** 4º LOGICA DE NEGOCIO ***
		if (formularioValido){
			IUsuariosFachada usuarioFachada = new UsuariosFachada();
			UsuariosDTO usuarioConsultado = usuarioFachada.consultarCredenciales(valorNombre);
			if(usuarioConsultado != null ) {
				if(usuarioConsultado.getClaveUsuario().equals(valorClave)){
					// CREDENCIALES CORRECTAS
					usuarioValido = true;
				}else {
					// ERROR CLAVE INCORRECTA 
					numeroIntento = numeroIntento++;
					stringIntento = numeroIntento.toString();
					usuarioValido = false;
				}
			}else {
				numeroIntento = numeroIntento+1;
				stringIntento = numeroIntento.toString();
				peticion.getSession().setAttribute("intento2", stringIntento);
				// ERROR DE NOMBRE DE USUARIO
				usuarioValido = false;
			}
		} 
		
		// *** 5º NAVEGACION *** 
		if (usuarioValido){
			peticion.getServletContext().setAttribute("aplicacion", valorAplicacion);
			peticion.getSession().setAttribute("nombre", valorNombre);
			peticion.setAttribute("clave", valorClave);
			RequestDispatcher rqd = peticion.getRequestDispatcher("/jsp/sesionUsuario.jsp");
			rqd.include(peticion, respuesta);
//			rqd.forward(peticion, respuesta);
		} else {
			peticion.removeAttribute("nombre-usuario");
			peticion.removeAttribute("clave-usuario");
			RequestDispatcher rqd = peticion.getRequestDispatcher("/jsp/login.jsp");
			rqd.include(peticion, respuesta);
//			rqd.forward(peticion, respuesta);
		}
	}
}