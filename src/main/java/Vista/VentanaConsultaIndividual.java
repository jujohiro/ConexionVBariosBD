package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Controlador.Coordinador;
import Modelo.UsuarioVo;
import java.awt.Color;

public class VentanaConsultaIndividual extends JDialog implements ActionListener {

    private JLabel LabelDireccion, TituloConsulta, labelDocumento, labelEdad, labelNombre, labelProfesion, labelTelefono, labelPassword, labelTipo, labelUsername, labelEstado;
    private JButton btonCancelar, btonConsultar, btonActualizar, btonEliminar, btonRegistrar;
    private JTextField campoTelefono, campoDireccion, campoDocumento, campoEdad, campoNombre, campoProfesion, campoPassword, campoBuscarUsuario;
    private JComboBox<String> comboTipo, comboEstado;
    private JTextField campoUsername;
    private JPanel panelConsulta;
    private JSeparator separadorInferior, separadorSuperior;
    private Coordinador miCoordinador;

    public VentanaConsultaIndividual(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        panelConsulta = new JPanel();
        panelConsulta.setLayout(null);
        panelConsulta.setBackground(new Color(240, 240, 240));

        // Título principal
        TituloConsulta = new JLabel("Consultar Usuario", SwingConstants.CENTER);
        TituloConsulta.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36));
        TituloConsulta.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TituloConsulta.setBounds(20, 10, 660, 60);
        TituloConsulta.setForeground(new Color(62, 141, 227));
        panelConsulta.add(TituloConsulta);


        // Labels y campos de texto
        labelDocumento = new JLabel("*Documento:", SwingConstants.RIGHT);
        labelDocumento.setFont(new java.awt.Font("Verdana", 0, 12));
        labelDocumento.setBounds(0, 130, 90, 20);
        panelConsulta.add(labelDocumento);

        campoDocumento = new JTextField();
        campoDocumento.setBounds(100, 130, 300, 20);
        panelConsulta.add(campoDocumento);

        labelNombre = new JLabel("*Nombre:", SwingConstants.RIGHT);
        labelNombre.setFont(new java.awt.Font("Verdana", 0, 12));
        labelNombre.setBounds(0, 160, 90, 20);
        panelConsulta.add(labelNombre);

        campoNombre = new JTextField();
        campoNombre.setBounds(100, 160, 300, 20);
        panelConsulta.add(campoNombre);

        labelProfesion = new JLabel("Profesión:", SwingConstants.RIGHT);
        labelProfesion.setFont(new java.awt.Font("Verdana", 0, 12));
        labelProfesion.setBounds(0, 190, 90, 20);
        panelConsulta.add(labelProfesion);

        campoProfesion = new JTextField();
        campoProfesion.setBounds(100, 190, 300, 20);
        panelConsulta.add(campoProfesion);

        LabelDireccion = new JLabel("Dirección:", SwingConstants.RIGHT);
        LabelDireccion.setFont(new java.awt.Font("Verdana", 0, 12));
        LabelDireccion.setBounds(0, 220, 90, 20);
        panelConsulta.add(LabelDireccion);

        campoDireccion = new JTextField();
        campoDireccion.setBounds(100, 220, 300, 20);
        panelConsulta.add(campoDireccion);

        labelTelefono = new JLabel("Teléfono:", SwingConstants.RIGHT);
        labelTelefono.setFont(new java.awt.Font("Verdana", 0, 12));
        labelTelefono.setBounds(400, 220, 100, 20);
        panelConsulta.add(labelTelefono);

        campoTelefono = new JTextField();
        campoTelefono.setBounds(510, 220, 170, 20);
        panelConsulta.add(campoTelefono);

        labelEdad = new JLabel("Edad:", SwingConstants.RIGHT);
        labelEdad.setFont(new java.awt.Font("Verdana", 0, 12));
        labelEdad.setBounds(400, 190, 100, 20);
        panelConsulta.add(labelEdad);

        campoEdad = new JTextField();
        campoEdad.setBounds(510, 190, 170, 20);
        panelConsulta.add(campoEdad);

        labelPassword = new JLabel("*Contraseña:", SwingConstants.RIGHT);
        labelPassword.setFont(new java.awt.Font("Verdana", 0, 12));
        labelPassword.setBounds(400, 130, 100, 20);
        panelConsulta.add(labelPassword);

        campoPassword = new JTextField();
        campoPassword.setBounds(510, 130, 170, 20);
        panelConsulta.add(campoPassword);

        labelUsername = new JLabel("Usuario:", SwingConstants.RIGHT);
        labelUsername.setFont(new java.awt.Font("Verdana", 0, 12));
        labelUsername.setBounds(400, 160, 100, 20);
        panelConsulta.add(labelUsername);

        campoUsername = new JTextField();
        campoUsername.setBounds(510, 160, 170, 20);
        panelConsulta.add(campoUsername);

        labelTipo = new JLabel("Tipo:", SwingConstants.RIGHT);
        labelTipo.setFont(new java.awt.Font("Verdana", 0, 12));
        labelTipo.setBounds(0, 250, 90, 20);
        panelConsulta.add(labelTipo);

        comboTipo = new JComboBox<>(new String[]{"Administrador", "Usuario", "Secretaria"});
        comboTipo.setBounds(100, 250, 300, 20);
        panelConsulta.add(comboTipo);

        labelEstado = new JLabel("Estado:", SwingConstants.RIGHT);
        labelEstado.setFont(new java.awt.Font("Verdana", 0, 12));
        labelEstado.setBounds(0, 280, 90, 20);
        panelConsulta.add(labelEstado);

        comboEstado = new JComboBox<>(new String[]{"Inactivo", "Activo"});
        comboEstado.setBounds(100, 280, 300, 20);
        panelConsulta.add(comboEstado);

        // Separadores
        separadorSuperior = new JSeparator();
        separadorSuperior.setBounds(20, 120, 660, 10);
        panelConsulta.add(separadorSuperior);

        separadorInferior = new JSeparator();
        separadorInferior.setBounds(20, 320, 660, 10);
        panelConsulta.add(separadorInferior);

        // Campo de búsqueda y botón
        campoBuscarUsuario = new JTextField();
        campoBuscarUsuario.setBounds(100, 90, 300, 20);
        panelConsulta.add(campoBuscarUsuario);

        btonConsultar = new JButton("Buscar");
        btonConsultar.setFont(new java.awt.Font("Verdana", 0, 14));
        btonConsultar.setBounds(420, 90, 110, 20);
        btonConsultar.addActionListener(this);
        panelConsulta.add(btonConsultar);
        btonConsultar.setBackground(new Color(54, 228, 158));
        btonConsultar.setForeground(Color.BLACK);


        // Botones de acción
        btonCancelar = new JButton("Cancelar");
        btonCancelar.setFont(new java.awt.Font("Verdana", 0, 14));
        btonCancelar.setBounds(510, 350, 170, 30);
        btonCancelar.addActionListener(this);
        panelConsulta.add(btonCancelar);
        btonCancelar.setBackground(new Color(255, 102, 102));
        btonCancelar.setForeground(Color.BLACK);


        btonActualizar = new JButton("Actualizar");
        btonActualizar.setFont(new java.awt.Font("Verdana", 0, 14));
        btonActualizar.setBounds(110, 350, 170, 30);
        btonActualizar.addActionListener(this);
        panelConsulta.add(btonActualizar);
        btonActualizar.setBackground(new Color(102, 178, 255));
        btonActualizar.setForeground(Color.BLACK);

        btonEliminar = new JButton("Eliminar");
        btonEliminar.setFont(new java.awt.Font("Verdana", 0, 14));
        btonEliminar.setBounds(310, 350, 170, 30);
        btonEliminar.addActionListener(this);
        panelConsulta.add(btonEliminar);
        btonEliminar.setBackground(new Color(255, 51, 51));
        btonEliminar.setForeground(Color.BLACK);


        btonRegistrar = new JButton("Registrar Usuario");
        btonRegistrar.setFont(new java.awt.Font("Verdana", 0, 14));
        btonRegistrar.setBounds(510, 250, 170, 30);
        btonRegistrar.addActionListener(this);
        panelConsulta.add(btonRegistrar);
        btonRegistrar.setBackground(new Color(102, 255, 102));
        btonRegistrar.setForeground(Color.BLACK);

        getContentPane().add(panelConsulta);
        panelConsulta.setBounds(0, 0, 710, 390);
        pack();
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btonCancelar) {
            limpiarVentana();
            dispose();
        }
        if (e.getSource() == btonActualizar) {
            actualizaUsuario();
            limpiarVentana();
        }
        if (e.getSource() == btonEliminar) {
            eliminaUsuario();
            limpiarVentana();
        }
        if (e.getSource() == btonRegistrar) {
            registrarUsuario();
            limpiarVentana();
        }
        if (e.getSource() == btonConsultar) {
            buscarUsuario();
        }
    }

    public void asignarPrivilegios(int index, String nombre) {
        switch (index) {
            case 1: // Administrador
                btonActualizar.setVisible(true);
                btonRegistrar.setVisible(true);
                btonEliminar.setVisible(true);
                btonConsultar.setVisible(true);
                btonCancelar.setVisible(true);
                break;
            case 2: // Usuario
                btonActualizar.setVisible(true);
                btonRegistrar.setVisible(false);
                btonEliminar.setVisible(false);
                btonConsultar.setVisible(true);
                btonCancelar.setVisible(true);
                comboTipo.setVisible(false);
                labelTipo.setVisible(false);
                labelEstado.setVisible(false);
                comboEstado.setVisible(false);
                break;
            case 3: // Secretaria
                btonActualizar.setVisible(false);
                btonRegistrar.setVisible(true);
                btonEliminar.setVisible(false);
                btonConsultar.setVisible(true);
                btonCancelar.setVisible(true);
                labelEstado.setVisible(true);
                comboEstado.setVisible(true);
                labelTipo.setVisible(true);
                comboTipo.setVisible(true);
                break;
            default:
                break;
        }
    }

    private void limpiarVentana() {
        campoNombre.setText("");
        campoDocumento.setText("");
        campoBuscarUsuario.setText("");
        campoPassword.setText("");
        campoProfesion.setText("");
        campoDireccion.setText("");
        campoTelefono.setText("");
        campoEdad.setText("");
        comboTipo.setSelectedIndex(0);
        campoUsername.setText("");
        comboEstado.setSelectedIndex(0);
    }

    private void buscarUsuario() {
        String documento = campoBuscarUsuario.getText().trim();
        if (documento.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        UsuarioVo usuarioVO = miCoordinador.buscarUsuarioPorDocumento(documento);
        if (usuarioVO != null) {
            campoNombre.setText(usuarioVO.getNombre());
            campoDocumento.setText(usuarioVO.getDocumento());
            campoProfesion.setText(usuarioVO.getProfesion());
            campoDireccion.setText(usuarioVO.getDireccion());
            campoTelefono.setText(usuarioVO.getTelefono());
            campoEdad.setText(String.valueOf(usuarioVO.getEdad()));
            campoPassword.setText(usuarioVO.getPassword());

            comboTipo.setSelectedIndex(usuarioVO.getTipo() - 1);
            campoUsername.setText(usuarioVO.getUsername());
            comboEstado.setSelectedIndex(usuarioVO.getEstado());
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no se encuentra registrado en el sistema", "Datos Inexistentes", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizaUsuario() {
        if (campoDocumento.getText().trim().isEmpty() ||
                campoNombre.getText().trim().isEmpty() ||
                campoEdad.getText().trim().isEmpty() ||
                campoProfesion.getText().trim().isEmpty() ||
                campoTelefono.getText().trim().isEmpty() ||
                campoDireccion.getText().trim().isEmpty() ||
                campoPassword.getText().trim().isEmpty() ||
                campoUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos obligatorios", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Integer edad = miCoordinador.validarEdad(Integer.parseInt(campoEdad.getText().trim()));
        if (edad != null) {
            UsuarioVo miUsuarioVo = new UsuarioVo();
            miUsuarioVo.setDocumento(campoDocumento.getText().trim());
            miUsuarioVo.setNombre(campoNombre.getText().trim());
            miUsuarioVo.setEdad(edad);
            miUsuarioVo.setProfesion(campoProfesion.getText().trim());
            miUsuarioVo.setTelefono(campoTelefono.getText().trim());
            miUsuarioVo.setDireccion(campoDireccion.getText().trim());
            miUsuarioVo.setPassword(campoPassword.getText().trim());
            miUsuarioVo.setTipo(comboTipo.getSelectedIndex() + 1);
            miUsuarioVo.setUsername(campoUsername.getText().trim());
            miUsuarioVo.setEstado(comboEstado.getSelectedIndex());




            String actualiza = miCoordinador.validarCampos(miUsuarioVo) ? miCoordinador.actualizaUsuario(miUsuarioVo) : "mas_datos";

            if ("ok".equals(actualiza)) {
                JOptionPane.showMessageDialog(null, "Se ha Modificado Correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String mensaje = "mas_datos".equals(actualiza) ? "Debe Ingresar los campos obligatorios" : "Error al Modificar";
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar una edad válida!!!", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
        limpiarVentana();
    }




    private void eliminaUsuario() {
        String documento = campoDocumento.getText().trim();
        if (!documento.equals("")) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el usuario " + documento + "?");
            if (JOptionPane.OK_OPTION == resp) {
                String elimina = miCoordinador.eliminarUsuario(documento);

                if (elimina.equals("ok")) {
                    JOptionPane.showMessageDialog(null, "Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    limpiarVentana();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar ", "Información", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un documento ", "Información", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void registrarUsuario() {
        if (campoNombre.getText().trim().isEmpty() ||
                campoDocumento.getText().trim().isEmpty() ||
                campoEdad.getText().trim().isEmpty() ||
                campoProfesion.getText().trim().isEmpty() ||
                campoTelefono.getText().trim().isEmpty() ||
                campoDireccion.getText().trim().isEmpty() ||
                campoPassword.getText().trim().isEmpty() ||
                campoUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioVo nuevoUsuario = new UsuarioVo();
        nuevoUsuario.setNombre(campoNombre.getText().trim());
        nuevoUsuario.setDocumento(campoDocumento.getText().trim());
        nuevoUsuario.setEdad(Integer.parseInt(campoEdad.getText().trim()));
        nuevoUsuario.setProfesion(campoProfesion.getText().trim());
        nuevoUsuario.setTelefono(campoTelefono.getText().trim());
        nuevoUsuario.setDireccion(campoDireccion.getText().trim());
        nuevoUsuario.setPassword(campoPassword.getText().trim());
        nuevoUsuario.setTipo(comboTipo.getSelectedIndex() + 1);
        nuevoUsuario.setUsername(campoUsername.getText().trim());
        nuevoUsuario.setEstado(comboEstado.getSelectedIndex());

        String resultado = miCoordinador.registrarUsuario(nuevoUsuario);
        if ("ok".equals(resultado)) {
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            limpiarVentana();
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarVentana();
        }
    }


}
