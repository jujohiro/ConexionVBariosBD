package Controlador;

import Dao.ProductoDao;
import Dao.UsuarioDao;
import Modelo.Logica;
import Modelo.ProductoVo;
import Modelo.UsuarioVo;
import Vista.*;
import conexion.Conexion;

import java.util.ArrayList;

public class Coordinador {

    // Declaración de ventanas y lógica de negocio
    private VentanaPrincipal miVentana;
    private VentanaLogin miLogin;
    private VentanaConsultaIndividual miVentanaConsultaIndividual;
    private VentanaListaProductos miVentanaLista;
    private VentanaProductos ventanaProductos;
    private InactivarUsuarioUI inactivarUsuarioUI;
    private ventanaUsuarios ventanaUsuarios;
    private Logica miLogica;
    private int rolActual;


    // DAO para operaciones de usuario y producto
    private UsuarioDao miUsuarioDao;
    private ProductoDao miProductoDao;



    public Coordinador() {
        this.miUsuarioDao = new UsuarioDao();
        this.miProductoDao = new ProductoDao();
        this.miProductoDao.setCoordinador(this);  // Se asigna el coordinador al DAO
    }

    public void setRolActual(int rol) {
        this.rolActual=rol;
    }
    public int getRolActual(){
        return rolActual;
    }




    public void setVentanaPrincipal(VentanaPrincipal miVentana) {
        this.miVentana = miVentana;
    }

    public void setVentanaLogin(VentanaLogin miLogin) {
        this.miLogin = miLogin;
    }

    public void setVentanaConsultaIndividual(VentanaConsultaIndividual miVentanaConsultaIndividual) {
        this.miVentanaConsultaIndividual = miVentanaConsultaIndividual;
    }

    public void setInactivarUsuarioUI(InactivarUsuarioUI inactivarUsuarioUI) {
        this.inactivarUsuarioUI = inactivarUsuarioUI;
    }

    public void setLogica(Logica miLogica) {
        this.miLogica = miLogica;
    }


    public void mostrarLogin() {
        if (miLogin != null) {
            miLogin.limpiar();
            miLogin.setVisible(true);
        }
    }

    public void mostrarVentanaConsulta() {
        if (miVentanaConsultaIndividual != null) {
            miVentanaConsultaIndividual.setVisible(true);
        }
    }

    public void mostrarVentanaInactivacion() {
        if (inactivarUsuarioUI != null) {
            inactivarUsuarioUI.setVisible(true);
        }
    }

    public void mostrarVentanaUsuarios() {
        if (ventanaUsuarios == null) {
            ventanaUsuarios = new ventanaUsuarios();
            ventanaUsuarios.setCoordinador(this);
        }
        ventanaUsuarios.setVisible(true);
    }

    public void mostrarVentanaProductos() {
        if (ventanaProductos == null) {
            ventanaProductos = new VentanaProductos(this);
            ventanaProductos.setCoordinador(this);
        }
        ventanaProductos.setVisible(true);
    }

    public void mostrarVentanaListaProductos() {
        if (miVentanaLista == null) {
            miVentanaLista = new VentanaListaProductos();
            miVentanaLista.setCoordinador(this);
        }
        miVentanaLista.actualizarTablaProductos();
        miVentanaLista.setVisible(true);
    }

    public void cerrarVentanaLogin() {
        if (miLogin != null) {
            miLogin.dispose();
        }
    }

    // ------------------------------------------------------------------------
    // Métodos de Privilegios y Validación
    // ------------------------------------------------------------------------

    public void asignarPrivilegios(int index, String usuario) {
        setRolActual(index);
        if (miVentana != null) {
            miVentana.asignarPrivilegios(index, usuario);
        }
        if (miVentanaConsultaIndividual != null) {
            miVentanaConsultaIndividual.asignarPrivilegios(index, usuario);
        }
    }

    public String validarIngreso(int index, String username, String password) {
        return miLogica != null ? miLogica.validarIngreso(index, username, password) : "";
    }

    public boolean validarCampos(UsuarioVo miUsuarioVo) {
        return miLogica != null && miLogica.validarCampos(miUsuarioVo);
    }

    public Integer validarEdad(int edadIngresada) {
        return miLogica != null ? miLogica.validarEdad(edadIngresada) : 0;
    }

    public Integer validarTipo(String tipoIngresado) {
        return miLogica != null ? miLogica.validarTipo(tipoIngresado) : -1;
    }

    // ------------------------------------------------------------------------
    // Métodos para Gestión de Usuarios
    // ------------------------------------------------------------------------

    public String registrarUsuario(UsuarioVo miUsuarioVo) {
        return miUsuarioDao != null ? miUsuarioDao.registrarUsuario(miUsuarioVo) : "Error: no inicializado";
    }

    public UsuarioVo consultarUsuario(String username, String password) {
        return miUsuarioDao != null ? miUsuarioDao.consultarUsuario(username, password) : null;
    }

    public String actualizaUsuario(UsuarioVo miUsuarioVo) {
        return miUsuarioDao != null ? miUsuarioDao.actualizaUsuario(miUsuarioVo) : "Error:  no inicializado";
    }

    public String eliminarUsuario(String documento) {
        return miUsuarioDao != null ? miUsuarioDao.eliminarUsuario(documento) : "Error: no inicializado";
    }

    public UsuarioVo buscarUsuarioPorDocumento(String documento) {
        return miUsuarioDao != null ? miUsuarioDao.buscarUsuarioPorDocumento(documento) : null;
    }

    public ArrayList<UsuarioVo> listarUsuarios() {
        return miUsuarioDao != null ? miUsuarioDao.listarUsuarios() : new ArrayList<>();
    }

    public String inactivarUsuario(String documento) {
        return miUsuarioDao != null ? miUsuarioDao.inactivarUsuario(documento) : "Error: no inicializado";
    }

    public String activarUsuario(String documento) {
        return miUsuarioDao != null ? miUsuarioDao.activarUsuario(documento) : "Error:  no inicializado";
    }

    // ------------------------------------------------------------------------
    // Métodos para Gestión de Productos
    // ------------------------------------------------------------------------

    public ProductoVo consultarProducto(String id) {
        return miProductoDao != null ? miProductoDao.consultarProducto(id) : null;
    }

    public ProductoVo consultarProductoPorNombre(String nombre) {
        return miProductoDao != null ? miProductoDao.consultarProductoPorNombre(nombre) : null;
    }

    public ArrayList<ProductoVo> listarProductos() {
        return miProductoDao != null ? miProductoDao.listarProductos() : new ArrayList<>();
    }

    public void registrarProducto(ProductoVo producto) {
        if (miProductoDao != null) {
            miProductoDao.registrarProducto(producto);
        }
    }

    public void actualizarProducto(ProductoVo producto) {
        if (miProductoDao != null) {
            miProductoDao.actualizarProducto(producto);
        }
    }

    public void eliminarProducto(String id) {
        if (miProductoDao != null) {
            miProductoDao.eliminarProducto(id);
        }
    }

    public void setUsuarioDao(UsuarioDao miUsuarioDao) {
    }



}
