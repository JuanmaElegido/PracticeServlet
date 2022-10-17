package com.servlet;

import java.io.IOException;
import java.util.ResourceBundle;

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
		String stringIntento = peticion.getParameter("intento2");
		String valorNombre = peticion.getParameter("nombreUsuario");
		String valorClave = peticion.getParameter("claveUsuario");
		String errorUsuario = "";
		String errorClave = "";
		Long numeroIntento;
		boolean formularioValido = true;
		boolean usuarioValido = false;
		
		// Lectura del properties
		ResourceBundle rb;
		rb = ResourceBundle.getBundle("com.properties.configuracion");
		String maxNumeroIntentos = rb.getString("loging.maxNumeroIntentos");
		peticion.getSession().setAttribute("maxNumeroIntentos", maxNumeroIntentos);
		
		// *** 2º CONVERSION ***
		String valorAplicacion = "APP-"+valorNombre.toUpperCase();

		// *** 3º VALIDACION ***
		
		// CONTADOR DE INTENTOS

		if (stringIntento == null || stringIntento == "") {
			// Primer intento
			numeroIntento = 1L;
			stringIntento = numeroIntento.toString();
			
		} else if (Long.parseLong(stringIntento)<Long.parseLong(maxNumeroIntentos)) {
			// Dentro de los intentos permitidos
			numeroIntento = Long.parseLong(stringIntento);
			numeroIntento = numeroIntento+1;
			stringIntento = numeroIntento.toString();
			peticion.getSession().setAttribute("intento2", stringIntento);
			
		} else {
			// Superados los intentos y se manda a exit.jsp
			RequestDispatcher rqd = peticion.getRequestDispatcher("/jsp/exit.jsp");
			rqd.forward(peticion, respuesta);
			return;
		}

		// Verificación de campos obligatorios completos
		if (null == valorNombre || null == valorClave ||
					valorNombre.isEmpty() || valorClave.isEmpty() ||
					valorClave.equals("") || valorClave.length() < 5) {
			// Formulario no valido (Cuando faltan datos obligatorios por completar)
			formularioValido = false;	
			errorClave = "Clave incorrecta.";
//			peticion.setAttribute("primerAviso", "avisado");
		} 

		
		// *** 4º LOGICA DE NEGOCIO ***
		if (formularioValido){
			IUsuariosFachada usuarioFachada = new UsuariosFachada();
			UsuariosDTO usuarioConsultado = usuarioFachada.consultarCredenciales(valorNombre);
			
			if(usuarioConsultado != null ) {
				if(usuarioConsultado.getClaveUsuario().equals(valorClave)){
					// Credenciales correctas
					usuarioValido = true;
					
				}else {
					// Error clave incorrecta 
					numeroIntento = numeroIntento++;
					stringIntento = numeroIntento.toString();
					usuarioValido = false;
					errorClave = "Clave incorrecta.";
					System.out.println("inicio");
					System.out.println("fin");
				}
				
			}else {
				// Error nombre de usuario incorrecto
				usuarioValido = false;
				errorUsuario = "Nombre de usuario incorrecto.";
			}
		} 
		
		// *** 5º NAVEGACION *** 
		if (usuarioValido){
			// Usuario identificado pasa a sesionUsuario.jsp
			peticion.getServletContext().setAttribute("aplicacion", valorAplicacion);
			peticion.getSession().setAttribute("nombre", valorNombre);
			peticion.getSession().setAttribute("UsuarioValido", "true");
			peticion.getSession().removeAttribute("intento2");
			peticion.setAttribute("clave", valorClave);
			RequestDispatcher rqd = peticion.getRequestDispatcher("/jsp/sesionUsuario.jsp");
			rqd.forward(peticion, respuesta);
			
		} else {
			// Usuario no identificado se queda en la pagina de login.jsp
			peticion.getSession().setAttribute("nombre", valorNombre);
			peticion.setAttribute("clave", valorClave);
			peticion.getSession().setAttribute("intento2", stringIntento);
			peticion.getSession().setAttribute("UsuarioValido", "false");
			RequestDispatcher rqd = peticion.getRequestDispatcher("/jsp/login.jsp");
			rqd.forward(peticion, respuesta);
		}
	}
}