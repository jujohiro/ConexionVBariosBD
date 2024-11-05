package Vista;

import Controlador.Coordinador;
import Modelo.UsuarioVo;

import javax.swing.*;
import java.awt.*;

public class InactivarUsuarioUI extends JFrame {
    private JTextField txtDocumento;
    private JButton btnBuscar;
    private JButton btnInactivar;
    private JButton btnActivar;
    private JButton btnCancelar;
    private JLabel lblNombre;
    private JLabel lblMensaje;
    private UsuarioVo usuario;
    private Coordinador miCoordinador;

    public InactivarUsuarioUI() {
        miCoordinador = new Coordinador(); // Inicializa Coordinador
        setTitle("Control de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(50, 50, 50));
        inicializarComponentes();
        agregarAcciones();
        agregarComponentes();
        pack();
    }

    private void inicializarComponentes() {
        txtDocumento = new JTextField();
        txtDocumento.setPreferredSize(new Dimension(350, 30));
        txtDocumento.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        btnBuscar = crearBoton("Buscar Usuario", Color.BLUE);
        btnInactivar = crearBoton("Inactivar Usuario", Color.RED);
        btnActivar = crearBoton("Activar Usuario", Color.GREEN);
        btnCancelar = crearBoton("Cancelar Búsqueda", Color.ORANGE);
        lblNombre = new JLabel("");
        lblNombre.setForeground(Color.WHITE);
        lblMensaje = new JLabel("");
        lblMensaje.setForeground(Color.WHITE);
    }

    private JButton crearBoton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void agregarAcciones() {
        btnBuscar.addActionListener(e -> buscarUsuario());
        btnInactivar.addActionListener(e -> inactivarUsuario());
        btnActivar.addActionListener(e -> activarUsuario());
        btnCancelar.addActionListener(e -> cancelarBusqueda());
    }

    private void agregarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setForeground(Color.WHITE);
        add(lblDocumento, gbc);

        gbc.gridx = 1;
        add(txtDocumento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(btnBuscar, gbc);

        gbc.gridy = 2;
        add(lblNombre, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(btnInactivar, gbc);
        btnInactivar.setVisible(false);

        gbc.gridx = 1;
        add(btnActivar, gbc);
        btnActivar.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnCancelar, gbc);
        btnCancelar.setVisible(false);

        gbc.gridy = 5;
        add(lblMensaje, gbc);
    }

    private void buscarUsuario() {
        String documento = txtDocumento.getText().trim();
        if (documento.isEmpty()) {
            mostrarMensaje("El campo de documento no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        usuario = miCoordinador.buscarUsuarioPorDocumento(documento);
        if (usuario != null) {
            lblNombre.setText("Nombre: " + usuario.getNombre());
            lblMensaje.setText("Usuario encontrado.");
            txtDocumento.setVisible(false);
            btnBuscar.setVisible(false);
            btnInactivar.setVisible(true);
            btnActivar.setVisible(true);
            btnCancelar.setVisible(true);
            mostrarMensaje("Usuario encontrado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mostrarMensaje("Usuario no encontrado. Recargando vista...", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }

    private void inactivarUsuario() {
        if (usuario == null) {
            mostrarMensaje("Primero busca un usuario.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String resultado = miCoordinador.inactivarUsuario(usuario.getDocumento());
        if ("ok".equals(resultado)) {
            mostrarMensaje("Usuario inactivado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            resetearUsuario();
        } else {
            mostrarMensaje("Error al inactivar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void activarUsuario() {
        if (usuario == null) {
            mostrarMensaje("Primero busca un usuario.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String resultado = miCoordinador.activarUsuario(usuario.getDocumento());
        if ("ok".equals(resultado)) {
            mostrarMensaje("Usuario activado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            resetearUsuario();
        } else {
            mostrarMensaje("Error al activar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelarBusqueda() {
        limpiarCampos();
        mostrarMensaje("Búsqueda cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetearUsuario() {
        usuario = null;
        lblNombre.setText("");
        lblMensaje.setText("");
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtDocumento.setText("");
        txtDocumento.setVisible(true);
        lblNombre.setText("");
        lblMensaje.setText("");
        btnBuscar.setVisible(true);
        btnInactivar.setVisible(false);
        btnActivar.setVisible(false);
        btnCancelar.setVisible(false);
    }

    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }


}
