package com.modelo;

public class UsuariosFachada implements IUsuariosFachada {
	
	private UsuariosDAO usuarioDAO;
	
	public UsuariosFachada() {
		usuarioDAO = new UsuariosDAO();
	}
	
	@Override
	public UsuariosDTO consultarCredenciales(String nombreUsuario) {
		UsuariosDTO usuarioConsultado = usuarioDAO.consultarCredenciales(nombreUsuario);
		return usuarioConsultado;
	}

}
