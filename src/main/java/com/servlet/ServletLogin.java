package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(name = "login", description = "Comprobacion de credenciales del usuario", urlPatterns = { "/login" })
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) 
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
		// *** 1º CAPTURA DE INFORMACION DE LA PETICION ENVIADA ***
		String valorAplicacion = peticion.getParameter("nombre-aplicacion");
		String valorNombre = peticion.getParameter("nombre-usuario");
		String valorClave = peticion.getParameter("clave-usuario");
		String valorObservacion = peticion.getParameter("observaciones");
		String primerAviso = peticion.getParameter("primerAviso");
		boolean formularioValido = false;
		
		// *** 2º CONVERSION ***
		
		// *** 3º VALIDACION *** 
		if (null == valorAplicacion || null == valorNombre || null == valorClave || 
					valorAplicacion.isEmpty() || valorNombre.isEmpty() || valorClave.isEmpty()) {
			// Cuando faltan datos obligatorios por completar
			formularioValido = false;	
			
		} else if (valorObservacion.isEmpty() && primerAviso == ""){
			// Cuando se han completado los campos obligatorios y
			// es la primera vez que recordamos completar observaciones.
			peticion.setAttribute("primerAviso", "avisado");
			formularioValido = false;	
			
		} else {
			// Cuando hemos preguntado una vez para recordar completar observaciones
			// y ademas el resto de datos obligatorios estan ya cumplimentados
			// se da por completado el formulario.
			formularioValido = true;
		}
		
		// *** 4º LOGICA DE NEGOCIO *** 
		
		peticion.getServletContext().setAttribute("aplicacion", valorAplicacion);
		peticion.getSession().setAttribute("nombre", valorNombre);
		peticion.getSession().setAttribute("clave", valorClave);

		// *** 5º NAVEGACION *** 
		if (formularioValido) {
			// Mandamos a enviado y presentamos los datos
			peticion.setAttribute("observacion", valorObservacion);
			RequestDispatcher rqd = peticion.getRequestDispatcher("/jsp/enviado.jsp");
			rqd.include(peticion, respuesta);
//			rqd.forward(peticion, respuesta);
			
		} else {
			// Mantenemos en el formulario: faltan datos por rellenar
			// 								observaciones vacias (solo se pasa una vez)
			peticion.setAttribute("clave", valorClave);
			RequestDispatcher rqd = peticion.getRequestDispatcher("/jsp/login.jsp");
			rqd.include(peticion, respuesta);
//			rqd.forward(peticion, respuesta);
			
		}
	}
}