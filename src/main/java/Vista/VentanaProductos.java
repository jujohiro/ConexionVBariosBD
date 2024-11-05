package Vista;

import Modelo.ProductoVo;
import Controlador.Coordinador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class VentanaProductos extends JFrame {  // Cambiado de JDialog a JFrame

    private Coordinador miCoordinador;
    private JPanel panelIzquierdo, panelDerecho;
    private JTextField campoNombre, campoDescripcion, campoPrecio;
    private JSpinner spinnerCantidad;
    private JButton btnRegistrar, btnActualizar, btnEliminar, btnCancelar, btnComprar, btnCancelarCompra;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    public VentanaProductos(Coordinador coordinador) {
        this.miCoordinador = coordinador;
        setTitle("Gestión de Productos");
        setSize(900, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Permite maximización
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar ventana al salir
        setLayout(new BorderLayout(10, 10));
        initComponents();
        configurarPermisos();
        actualizarTabla();
    }

    private void initComponents() {
        // Título principal
        JLabel lblTitulo = new JLabel("Gestión de Productos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(54, 54, 54));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel izquierdo para detalles del producto y botones de acción
        panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridLayout(13, 1, 5, 5));
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelIzquierdo.setBackground(new Color(250, 250, 245));

        // Campos de texto y spinner para cantidad
        campoNombre = new JTextField(15);
        campoDescripcion = new JTextField(15);
        campoPrecio = new JTextField(15);
        spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

        // Etiquetas y campos en el panel izquierdo
        panelIzquierdo.add(new JLabel("Nombre del producto:"));
        panelIzquierdo.add(campoNombre);
        panelIzquierdo.add(new JLabel("Descripción:"));
        panelIzquierdo.add(campoDescripcion);
        panelIzquierdo.add(new JLabel("Precio:"));
        panelIzquierdo.add(campoPrecio);
        panelIzquierdo.add(new JLabel("Cantidad:"));
        panelIzquierdo.add(spinnerCantidad);

        // Panel para los botones de Comprar y Cancelar Compra
        JPanel panelBotonesCompra = new JPanel(new GridLayout(1, 2, 5, 5));
        panelBotonesCompra.setBackground(new Color(250, 250, 245));

        btnComprar = crearBoton("Comprar", new Color(60, 179, 113), Color.WHITE);
        btnCancelarCompra = crearBoton("Cancelar Compra", new Color(255, 69, 0), Color.WHITE);

        panelBotonesCompra.add(btnComprar);
        panelBotonesCompra.add(btnCancelarCompra);

        panelIzquierdo.add(panelBotonesCompra);

        // Espacio adicional para separar las secciones
        panelIzquierdo.add(Box.createVerticalStrut(20));

        // Panel para los botones de Registro, Actualización, Eliminación y Cancelación
        JPanel panelBotonesGestion = new JPanel(new GridLayout(2, 2, 5, 5));
        panelBotonesGestion.setBackground(new Color(250, 250, 245));

        btnRegistrar = crearBoton("Registrar", new Color(30, 144, 255), Color.WHITE);
        btnActualizar = crearBoton("Actualizar", new Color(218, 165, 32), Color.WHITE);
        btnEliminar = crearBoton("Eliminar", new Color(255, 99, 71), Color.WHITE);
        btnCancelar = crearBoton("Cancelar", new Color(100, 149, 237), Color.WHITE);

        panelBotonesGestion.add(btnRegistrar);
        panelBotonesGestion.add(btnActualizar);
        panelBotonesGestion.add(btnEliminar);
        panelBotonesGestion.add(btnCancelar);

        panelIzquierdo.add(panelBotonesGestion);

        // Panel derecho para la tabla de productos
        panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(250, 250, 245));

        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);

        // Configuración de estilo para la tabla
        tablaProductos.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tablaProductos.setRowHeight(28);
        tablaProductos.setSelectionBackground(new Color(255, 230, 200));
        tablaProductos.setGridColor(new Color(220, 220, 220));

        tablaProductos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                comp.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 240));
                return comp;
            }
        });

        JTableHeader header = tablaProductos.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 15));
        header.setBackground(new Color(100, 150, 200));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        panelDerecho.add(scrollPane, BorderLayout.CENTER);

        // Añade ambos paneles a la ventana
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

        // Listener para llenar los campos al seleccionar un producto
        tablaProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaProductos.getSelectedRow() != -1) {
                cargarProductoSeleccionado();
            }
        });

        // Listeners para los botones con lógica de acción
        btnRegistrar.addActionListener(e -> registrarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnCancelar.addActionListener(e -> limpiarCampos());
        btnComprar.addActionListener(e -> comprarProducto());
        btnCancelarCompra.addActionListener(e -> cancelarCompra());
    }

    private JButton crearBoton(String texto, Color bgColor, Color fgColor) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setBackground(bgColor);
        boton.setForeground(fgColor);
        boton.setFocusPainted(false);
        return boton;
    }

    private void cargarProductoSeleccionado() {
        int selectedRow = tablaProductos.getSelectedRow();
        campoNombre.setText(modeloTabla.getValueAt(selectedRow, 1).toString());
        campoDescripcion.setText(modeloTabla.getValueAt(selectedRow, 2).toString());
        campoPrecio.setText(modeloTabla.getValueAt(selectedRow, 3).toString());
        spinnerCantidad.setValue(Integer.parseInt(modeloTabla.getValueAt(selectedRow, 4).toString()));
    }

    private void registrarProducto() {
        String nombre = campoNombre.getText();
        String descripcion = campoDescripcion.getText();
        double precio = Double.parseDouble(campoPrecio.getText());
        int cantidad = (int) spinnerCantidad.getValue();

        ProductoVo producto = new ProductoVo();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCantidad(cantidad);

        miCoordinador.registrarProducto(producto);
        actualizarTabla();
        limpiarCampos();
    }

    private void actualizarProducto() {
        int selectedRow = tablaProductos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para actualizar.");
            return;
        }

        String id = modeloTabla.getValueAt(selectedRow, 0).toString();
        String nombre = campoNombre.getText();
        String descripcion = campoDescripcion.getText();
        double precio = Double.parseDouble(campoPrecio.getText());
        int cantidad = (int) spinnerCantidad.getValue();

        ProductoVo producto = new ProductoVo();
        producto.setIdProducto(id);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCantidad(cantidad);

        miCoordinador.actualizarProducto(producto);
        actualizarTabla();
        limpiarCampos();
    }

    private void eliminarProducto() {
        int selectedRow = tablaProductos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.");
            return;
        }

        String id = modeloTabla.getValueAt(selectedRow, 0).toString();
        miCoordinador.eliminarProducto(id);
        actualizarTabla();
        limpiarCampos();
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoDescripcion.setText("");
        campoPrecio.setText("");
        spinnerCantidad.setValue(1);
    }

    private void comprarProducto() {
        int selectedRow = tablaProductos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para comprar.");
            return;
        }

        String id = modeloTabla.getValueAt(selectedRow, 0).toString();
        ProductoVo producto = miCoordinador.consultarProducto(id);

        if (producto != null) {
            JOptionPane.showMessageDialog(this, "Se ha realizado la compra de " + producto.getNombre() + " exitosamente");
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido realizar la compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelarCompra() {
        JOptionPane.showMessageDialog(this, "Compra cancelada.");
        limpiarCampos();
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        ArrayList<ProductoVo> productos = miCoordinador.listarProductos();
        for (ProductoVo producto : productos) {
            modeloTabla.addRow(new Object[]{
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getCantidad()
            });
        }
    }
    private void configurarPermisos() {
        int rol=miCoordinador.getRolActual();
        switch (rol) {
            case 1: // Administrador
                btnRegistrar.setVisible(true);
                btnActualizar.setVisible(true);
                btnEliminar.setVisible(true);

                btnComprar.setVisible(true);
                btnCancelarCompra.setVisible(true);
                break;
            case 2: // Usuario
                btnRegistrar.setVisible(false);
                btnActualizar.setVisible(false);
                btnEliminar.setVisible(false);
                break;
            case 3: // Secretaria
                btnRegistrar.setVisible(true);
                btnActualizar.setVisible(true);
                btnEliminar.setVisible(true);

                btnComprar.setVisible(true);
                btnCancelarCompra.setVisible(true);
                break;
            default:
                break;
        }
    }

    public void setCoordinador(Coordinador coordinador) {
        this.miCoordinador = coordinador;
    }
}
