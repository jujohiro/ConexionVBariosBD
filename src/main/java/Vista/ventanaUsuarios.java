package Vista;

import Controlador.Coordinador;
import Modelo.UsuarioVo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ventanaUsuarios extends JFrame {

    private Coordinador miCoordinador;
    private JTable tablaUsuarios;
    private DefaultTableModel modelo;

    public ventanaUsuarios() {
        initComponents();
        setSize(1200, 600);           // Ajusta el tamaño de la ventana a 1200x600 píxeles
        setLocationRelativeTo(null);   // Centrar la ventana en la pantalla
        setTitle("Gestión de Usuarios");
    }

    public void setCoordinador(Coordinador coordinador) {
        this.miCoordinador = coordinador;
        mostrarTabla();
        asignarPrivilegios();
    }

    private void initComponents() {
        miCoordinador = new Coordinador();

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(230, 240, 250));
        panelPrincipal.setLayout(new BorderLayout(10, 10));

        // Crear un encabezado con un título
        JLabel titulo = new JLabel("Lista de Usuarios", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(60, 120, 200));
        titulo.setForeground(Color.WHITE);
        titulo.setPreferredSize(new Dimension(1000, 50));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // Configurar la tabla y modelo de la tabla
        modelo = new DefaultTableModel();
        modelo.addColumn("Documento");
        modelo.addColumn("Nombre");
        modelo.addColumn("Profesión");
        modelo.addColumn("Edad");
        modelo.addColumn("Dirección");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Tipo");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Usuario");
        modelo.addColumn("Estado");

        tablaUsuarios = new JTable(modelo);
        tablaUsuarios.setRowHeight(25);
        tablaUsuarios.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaUsuarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaUsuarios.getTableHeader().setBackground(new Color(60, 120, 200));
        tablaUsuarios.getTableHeader().setForeground(Color.WHITE);

        // Centrar el texto en cada columna
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaUsuarios.getColumnCount(); i++) {
            tablaUsuarios.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Agregar la tabla dentro de un scroll pane
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Agregar el panel principal a la ventana
        this.add(panelPrincipal);
    }

    public void mostrarTabla() {
        modelo.setRowCount(0);

        ArrayList<UsuarioVo> listaUsuarios = miCoordinador.listarUsuarios();
        for (UsuarioVo usuario : listaUsuarios) {
            String[] datos = new String[10];
            datos[0] = usuario.getDocumento();
            datos[1] = usuario.getNombre();
            datos[2] = usuario.getProfesion();
            datos[3] = String.valueOf(usuario.getEdad());
            datos[4] = usuario.getDireccion();
            datos[5] = usuario.getTelefono();
            datos[6] = String.valueOf(usuario.getTipo());
            datos[7] = usuario.getPassword();
            datos[8] = usuario.getUsername();
            datos[9] = String.valueOf(usuario.getEstado());
            modelo.addRow(datos);
        }

        ajustarColumnas();
    }

    private void ajustarColumnas() {
        // Ajusta el ancho de cada columna en función de tus necesidades
        tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(150); // Documento
        tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre
        tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150); // Profesión
        tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(50);  // Edad
        tablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(200); // Dirección
        tablaUsuarios.getColumnModel().getColumn(5).setPreferredWidth(100); // Teléfono
        tablaUsuarios.getColumnModel().getColumn(6).setPreferredWidth(80);  // Tipo
        tablaUsuarios.getColumnModel().getColumn(7).setPreferredWidth(100); // Contraseña
        tablaUsuarios.getColumnModel().getColumn(8).setPreferredWidth(150); // Usuario
        tablaUsuarios.getColumnModel().getColumn(9).setPreferredWidth(80);  // Estado
    }

    public void asignarPrivilegios() {
        int index=miCoordinador.getRolActual();
        switch (index) {
            case 1:
                mostrarTodasLasColumnas();
                break;
            case 2:
                ocultarColumnas(new int[]{6,7, 8, 9});
                break;
            case 3:
                mostrarTodasLasColumnas();
                break;
            default:
                break;
        }
    }

    private void ocultarColumnas(int[] indices) {
        for (int index : indices) {
            tablaUsuarios.getColumnModel().getColumn(index).setMinWidth(0);
            tablaUsuarios.getColumnModel().getColumn(index).setMinWidth(0);
            tablaUsuarios.getColumnModel().getColumn(index).setMaxWidth(0);
            tablaUsuarios.getColumnModel().getColumn(index).setWidth(0);
        }
    }

    private void mostrarTodasLasColumnas() {
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            tablaUsuarios.getColumnModel().getColumn(i).setMinWidth(15);
            tablaUsuarios.getColumnModel().getColumn(i).setMaxWidth(Integer.MAX_VALUE);
            tablaUsuarios.getColumnModel().getColumn(i).setWidth(100);
        }
    }
}
