package Modelo;

import Controlador.Coordinador;

public class Logica {

	public static final int SELECCION = 0;
	public static final int ADMINISTRADOR = 1;
	public static final int USUARIO = 2;
	public static final int SECRETARIA = 3;

	private Coordinador miCoordinador;

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}


	public String validarIngreso(int index, String username, String password) {
		UsuarioVo miUsuarioVo = miCoordinador.consultarUsuario(username, password);

		if (miUsuarioVo == null) {
			return "desconectado";
		}

		if (index == ADMINISTRADOR && miUsuarioVo.getTipo() == ADMINISTRADOR) {
			return password.equals(miUsuarioVo.getPassword()) ? miUsuarioVo.getNombre() : "invalido";
		} else if (index == USUARIO && miUsuarioVo.getTipo() == USUARIO) {
			return miUsuarioVo.getNombre();
		} else if (index == SECRETARIA && miUsuarioVo.getTipo() == SECRETARIA) {
			return password.equals(miUsuarioVo.getPassword()) ? miUsuarioVo.getNombre() : "invalido";
		}

		return "error";
	}


	public boolean validarCampos(UsuarioVo miUsuarioVo) {
		boolean validarUsername = false;
		boolean validarPassword = false;

		String password = miUsuarioVo.getPassword();
		String username = miUsuarioVo.getUsername();
		String documento = miUsuarioVo.getDocumento();

		if (password != null && !password.equals("")) {
			validarPassword = true;
		}

		if (username != null && !username.equals("")) {
			validarUsername = true;
		}


		return validarPassword && validarUsername ;
	}


	public Integer validarEdad(int edad) {
		if (edad < 0 || edad > 80) {
			return null;
		}
		return edad;
	}


	public Integer validarTipo(String tipoIngresado) {
		Integer tipo = null;
		try {
			tipo = Integer.parseInt(tipoIngresado);
		} catch (Exception e) {
			tipo = null;
		}
		return tipo;
	}

}
