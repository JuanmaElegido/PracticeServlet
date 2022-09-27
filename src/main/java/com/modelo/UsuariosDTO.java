package com.modelo;

public class UsuariosDTO {
	
	private String nombreUsuario;
	private String claveUsuario;
	private String observaciones;
	
	public UsuariosDTO() {
	}
	
	public UsuariosDTO(String nombreUsuario, String claveUsuario, String observaciones) {
		this.nombreUsuario = nombreUsuario;
		this.claveUsuario = claveUsuario;
		this.observaciones = observaciones;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getClaveUsuario() {
		return claveUsuario;
	}
	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "UsuariosDTO [nombreUsuario=" + nombreUsuario + ", claveUsuario=" + claveUsuario + ", observaciones="
				+ observaciones + "]";
	}
	
}