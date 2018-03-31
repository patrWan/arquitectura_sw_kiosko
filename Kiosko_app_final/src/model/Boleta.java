package model;

public class Boleta {
    private int id;
    private int id_producto;
    private int id_venta;
    private int cantidad;
    private int subtotal;

    public Boleta() {
    }

    public Boleta(int id, int id_producto, int id_venta, int cantidad, int subtotal) {
        this.id = id;
        this.id_producto = id_producto;
        this.id_venta = id_venta;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
