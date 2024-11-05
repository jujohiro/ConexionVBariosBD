package Dao;

import Controlador.Coordinador;
import Modelo.ProductoVo;
import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDao {
    private Coordinador miCoordinador;

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public boolean registrarProducto(ProductoVo miProducto) {

        String consulta = "INSERT INTO producto (idProducto, nombreProducto, descripcion, precio, cantidad) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Conexion.getInstance().getConnection();
             PreparedStatement preStatement = connection.prepareStatement(consulta)) {

            preStatement.setString(1, miProducto.getIdProducto());
            preStatement.setString(2, miProducto.getNombre());
            preStatement.setString(3, miProducto.getDescripcion());
            preStatement.setInt(4, miProducto.getPrecio());
            preStatement.setInt(5, miProducto.getCantidad());

            preStatement.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<ProductoVo> listarProductos() {
        ArrayList<ProductoVo> productos = new ArrayList<>();
        String consulta = "SELECT idProducto, nombreProducto, descripcion, precio, cantidad FROM producto";
        try (Connection connection = Conexion.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(consulta);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ProductoVo producto = new ProductoVo();
                producto.setIdProducto(resultSet.getString("idProducto"));
                producto.setNombre(resultSet.getString("nombreProducto"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setPrecio(resultSet.getInt("precio"));
                producto.setCantidad(resultSet.getInt("cantidad"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return productos;
    }


    public boolean actualizarProducto(ProductoVo producto) {
        String consulta = "UPDATE producto SET nombreProducto = ?, cantidad = ?, descripcion = ?, precio = ? WHERE idProducto = ?";
        try (Connection connection = Conexion.getInstance().getConnection();
             PreparedStatement preStatement = connection.prepareStatement(consulta)) {

            preStatement.setString(1, producto.getNombre());
            preStatement.setInt(2, producto.getCantidad());
            preStatement.setString(3, producto.getDescripcion());
            preStatement.setInt(4, producto.getPrecio());
            preStatement.setString(5, producto.getIdProducto());
            preStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(String idProducto) {
        String consulta = "DELETE FROM producto WHERE idProducto = ?";
        try (Connection connection = Conexion.getInstance().getConnection();
             PreparedStatement preStatement = connection.prepareStatement(consulta)) {

            preStatement.setString(1, idProducto);
            preStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    //metodo para consultar producto
    public ProductoVo consultarProducto(String idProducto) {
        ProductoVo producto = null;
        String consulta = "SELECT * FROM producto WHERE idProducto = ?";

        try (Connection connection = Conexion.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(consulta)) {

            statement.setString(1, idProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                producto = new ProductoVo();
                producto.setIdProducto(resultSet.getString("idProducto"));
                producto.setNombre(resultSet.getString("nombreProducto"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setPrecio(resultSet.getInt("precio"));
                producto.setCantidad(resultSet.getInt("cantidad"));
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar producto por ID: " + e.getMessage());
        }
        return producto;
    }

    public ProductoVo consultarProductoPorNombre(String nombreProducto) {
        ProductoVo producto = null;
        String consulta = "SELECT * FROM producto WHERE nombreProducto = ?";

        try (Connection connection = Conexion.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(consulta)) {

            statement.setString(1, nombreProducto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                producto = new ProductoVo();
                producto.setIdProducto(resultSet.getString("idProducto"));
                producto.setNombre(resultSet.getString("nombreProducto"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setPrecio(resultSet.getInt("precio"));
                producto.setCantidad(resultSet.getInt("cantidad"));
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar producto por nombre: " + e.getMessage());
        }
        return producto;
    }

}

