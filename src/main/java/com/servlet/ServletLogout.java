package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletLogout
 */
@WebServlet(name = "logout", description = "Cerrar la sesión del usuario", urlPatterns = { "/logout" })
public class ServletLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
//		peticion.getServletContext().removeAttribute("aplicacion");
		peticion.getSession().removeAttribute("nombre");
		peticion.getSession().removeAttribute("nombre-usuario");
		peticion.getSession().removeAttribute("UsuarioValido");
		RequestDispatcher rqd = peticion.getRequestDispatcher("/jsp/login.jsp");
		rqd.include(peticion, respuesta);
	}

}
