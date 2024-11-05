package Vista;

import Controlador.Coordinador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class VentanaLogin extends JDialog implements ActionListener {

    private JButton botonAceptar;
    private JPasswordField campoPass;
    private JComboBox<String> comboUsuarios;
    private JLabel imagen;
    private JLabel labelPass;
    private JLabel labelUser;
    private JLabel labelUsername;
    private JPanel panelLogin;
    private JLabel tituloLogin;
    private JTextField campoUsername;

    private Coordinador miCoordinador;

    public VentanaLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Login");
        setSize(400, 550); // Tamaño ajustado para un panel más grande
        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Continuar", "Cerrar"};
                int n = JOptionPane.showOptionDialog(null,
                        "Seleccione un tipo de Usuario.\nSi sale el sistema se Cerrará", "Confirmación", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                if (n == JOptionPane.NO_OPTION) {
                    System.exit(0); // Cerrar todo el sistema
                }
            }
        });
    }
    
    
    
    private void initComponents() {
        panelLogin = new JPanel();
        tituloLogin = new JLabel();
        imagen = new JLabel();
        labelUser = new JLabel();
        labelPass = new JLabel();
        labelUsername = new JLabel();
        botonAceptar = new JButton();
        comboUsuarios = new JComboBox<>();
        campoPass = new JPasswordField();
        campoUsername = new JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Panel Principal con un color suave
        panelLogin.setBackground(new Color(240, 240, 240));
        panelLogin.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15); // Ajuste de espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título con fuente más grande y en negrita
        tituloLogin.setFont(new Font("Arial", Font.BOLD, 32));  // Negrita
        tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLogin.setText("Login");
        tituloLogin.setForeground(new Color(33, 150, 243));  // Azul vibrante
        tituloLogin.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); // Espacio debajo
        gbc.gridwidth = 2;  // Título ocupa dos columnas
        gbc.gridy = 0;
        panelLogin.add(tituloLogin, gbc);

        // Imagen de candado centrada y más grande
        imagen.setHorizontalAlignment(SwingConstants.CENTER);
        imagen.setIcon(new ImageIcon(getClass().getResource("/imagenes/candado.png")));
        imagen.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243), 3, true));  // Borde azul
        gbc.gridwidth = 1;  // Imagen ocupa una columna
        gbc.gridy = 1;
        panelLogin.add(imagen, gbc);

        // Etiqueta Usuario  en negrita
        labelUser.setText("Seleccione Usuario:");
        labelUser.setFont(new Font("Arial", Font.BOLD, 16));  // Negrita
        gbc.gridy = 2;
        panelLogin.add(labelUser, gbc);

        // ComboBox de Selección de Usuario
        comboUsuarios.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione", "Administrador", "Usuario", "Secretaria"}));
        comboUsuarios.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 3;
        panelLogin.add(comboUsuarios, gbc);
        comboUsuarios.addActionListener(this);

        // Etiqueta Nombre de Usuario en negrita
        labelUsername.setText("Nombre de Usuario:");
        labelUsername.setFont(new Font("Arial", Font.BOLD, 16));  // Negrita
        gbc.gridy = 4;
        panelLogin.add(labelUsername, gbc);
        labelUsername.setVisible(false);

        // Campo Nombre de Usuario
        campoUsername.setFont(new Font("Arial", Font.PLAIN, 16));
        campoUsername.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243), 2)); // Borde azul
        gbc.gridy = 5;
        panelLogin.add(campoUsername, gbc);
        campoUsername.setVisible(false);

        // Etiqueta Contraseña en negrita
        labelPass.setText("Contraseña:");
        labelPass.setFont(new Font("Arial", Font.BOLD, 16));  // Negrita
        gbc.gridy = 6;
        panelLogin.add(labelPass, gbc);
        labelPass.setVisible(false);

        // Campo Contraseña
        campoPass.setFont(new Font("Arial", Font.PLAIN, 16));
        campoPass.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243), 2)); // Borde azul
        gbc.gridy = 7;
        panelLogin.add(campoPass, gbc);
        campoPass.setVisible(false);

        // Botón Aceptar en negrita
        botonAceptar.setText("Aceptar");
        botonAceptar.setFont(new Font("Arial", Font.BOLD, 16)); // Negrita
        botonAceptar.setForeground(Color.WHITE);
        botonAceptar.setBackground(new Color(33, 150, 243));
        botonAceptar.setFocusPainted(false);
        botonAceptar.setBorder(BorderFactory.createEmptyBorder());
        gbc.gridy = 8;
        panelLogin.add(botonAceptar, gbc);

        // Agregar Panel al contenido
        getContentPane().add(panelLogin, BorderLayout.CENTER);

        // Asignar ActionListener al botón Aceptar
        botonAceptar.addActionListener(this);

        pack();
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == comboUsuarios) {
            mostrarElementos();

        }
        if (evento.getSource() == botonAceptar) {

            String pass = new String(campoPass.getPassword());

            if (pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String resp = miCoordinador.validarIngreso(comboUsuarios.getSelectedIndex(), campoUsername.getText(), pass);


            if (resp.equals("error")) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado un usuario", "Advertencia", JOptionPane.WARNING_MESSAGE);
                limpiar();
            } else {
                if (resp.equals("invalido")) {
                    JOptionPane.showMessageDialog(null, "La contraseña no corresponde", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    limpiar();
                } else {
                    if (resp.equals("desconectado")) {
                        JOptionPane.showMessageDialog(null, "Su usuario esta inactivo, "
                                + "por favor comuniquese con un administrador", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                        limpiar();
                    } else {
                        miCoordinador.asignarPrivilegios(comboUsuarios.getSelectedIndex(), resp);
                        miCoordinador.cerrarVentanaLogin();
                    }
                }
            }
        }
    }

    public void limpiar() {
        comboUsuarios.setSelectedIndex(0);
        campoPass.setText("");
        campoUsername.setText("");
    }

    private void mostrarElementos() {
        int index = comboUsuarios.getSelectedIndex();
        System.out.println("Seleccionado: " + index);

        if (index == 0) {
            labelPass.setVisible(false);
            campoPass.setVisible(false);
            labelUsername.setVisible(false);
            campoUsername.setVisible(false);
        } else if (index == 1 || index == 2 || index == 3) {
            labelPass.setVisible(true);
            campoPass.setVisible(true);
            labelUsername.setVisible(true);
            campoUsername.setVisible(true);

        }


    }
}
