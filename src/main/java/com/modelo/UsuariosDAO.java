package com.modelo;

public class UsuariosDAO {
	
	
	public UsuariosDTO consultarCredenciales(String nombreUsuario){
		UsuariosDTO usuario =null;
				
			if (nombreUsuario.equals("Juan")) {
				usuario = new UsuariosDTO();
				usuario.setNombreUsuario("Juan");
				usuario.setClaveUsuario("123456");
			}
			
		return usuario;
	}

}
