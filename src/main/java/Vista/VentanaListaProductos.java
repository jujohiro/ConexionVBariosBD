package Vista;

import Modelo.ProductoVo;
import Controlador.Coordinador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class VentanaListaProductos extends JFrame {

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private Coordinador miCoordinador;

    public VentanaListaProductos() {
        miCoordinador = new Coordinador();
        setTitle("Lista de Productos");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        initComponents();
    }

    private void initComponents() {
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(new Color(250, 250, 245));

        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);

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
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        add(panelTabla, BorderLayout.CENTER);
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public void actualizarTablaProductos() {
    }
}
