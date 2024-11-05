package Controlador;

import Dao.ProductoDao;
import Dao.UsuarioDao;
import Modelo.Logica;
import Vista.*;

public class Aplicacion {

	public void iniciarSistema() {
		// Instanciamos las clases únicas
		VentanaPrincipal miVentana = new VentanaPrincipal();
		VentanaLogin miLogin = new VentanaLogin(miVentana, true);

		// Instancia de Coordinador
		Coordinador miCoordinador = new Coordinador();

		Logica miLogica = new Logica();

		// Ventanas adicionales
		VentanaConsultaIndividual miVentanaConsultaIndividual = new VentanaConsultaIndividual(miVentana, true);
		InactivarUsuarioUI miInactivarUsuarioUI = new InactivarUsuarioUI();


		UsuarioDao miUsuarioDao = new UsuarioDao();
		ProductoDao miProductoDao = new ProductoDao();

		// Relacionamos las clases con el Coordinador
		miVentana.setCoordinador(miCoordinador);
		miLogin.setCoordinador(miCoordinador);
		miLogica.setCoordinador(miCoordinador);
		miVentanaConsultaIndividual.setCoordinador(miCoordinador);
		miUsuarioDao.setCoordinador(miCoordinador);

		// Relacionamos el Coordinador con las clases
		miCoordinador.setVentanaPrincipal(miVentana);
		miCoordinador.setVentanaLogin(miLogin);
		miCoordinador.setLogica(miLogica);
		miCoordinador.setVentanaConsultaIndividual(miVentanaConsultaIndividual);
		miCoordinador.setUsuarioDao(miUsuarioDao);
		miCoordinador.setInactivarUsuarioUI(miInactivarUsuarioUI);



		// Mostramos las ventanas
		miLogin.setVisible(true);
		miVentana.setVisible(true);
	}
}
