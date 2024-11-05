package Modelo;

public class ProductoVo {
    private String idProducto;
    private String nombreProducto;

    public String getDescripcion() {
        return descripcion;
    }

    private String descripcion;
    private int precio;
    private int cantidad;

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombreProducto;
    }

    public void setNombre(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = (int) precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setDescripcion(String descripcion) { this.descripcion=descripcion;
    }
}

