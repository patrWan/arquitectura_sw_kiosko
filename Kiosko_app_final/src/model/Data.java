package model;

import bd.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Data {

    private Conexion con;
    private String query;
    private ResultSet rs;

    private List<Producto> listaProducto;
    private List<Tipo_producto> listaTipoProducto;
    private List<Venta> listaVentas;
    private List<Boleta> listaboletas;

    public Data() throws ClassNotFoundException, SQLException {
        con = new Conexion("localhost", "root", "", "bd_kiosko");
    }

    public void registrar_producto(Producto p) throws SQLException {
        query = "INSERT INTO producto VALUES(NULL," + p.getId_tipo() + ",'" + p.getNombre() + "'," + p.getPrecio() + "," + p.getStock() + ");";
        con.ejecutar(query);
    }

    public List<Producto> getListaProducto() throws SQLException {
        query = "SELECT * FROM producto";

        listaProducto = new ArrayList<>();
        Producto p;

        rs = con.ejecutarSelect(query);
        while (rs.next()) {
            p = new Producto();

            p.setId(rs.getInt(1));
            p.setId_tipo(rs.getInt(2));
            p.setNombre(rs.getString(3));
            p.setPrecio(rs.getInt(4));
            p.setStock(rs.getInt(5));

            listaProducto.add(p);

        }
        con.desconectar();
        return listaProducto;
    }

    public List<Producto> buscar_producto(String buscar) throws SQLException {
        query = "SELECT id, nombre, precio, stock FROM producto WHERE nombre LIKE '%" + buscar + "%';";

        listaProducto = new ArrayList<>();
        Producto p;

        rs = con.ejecutarSelect(query);
        while (rs.next()) {
            p = new Producto();

            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            p.setPrecio(rs.getInt(3));
            p.setStock(rs.getInt(4));

            listaProducto.add(p);

        }
        con.desconectar();
        return listaProducto;
    }//Es una lista porque puede devolver mas de un producto.

    public void eliminar_producto(int id) throws SQLException {
        query = "DELETE FROM producto WHERE id = '" + id + "';";
        con.ejecutar(query);
    }

    public void generar_venta(List<Boleta> listaBoleta, int total) throws SQLException {//listaBoleta lleva los datos necesarios para hacer el insert en la tabla boleta.
        //INSERT A VENTA, NECESARIO PARA LUEGO HACER EL INSERT EN BOLETA.
        //El total se calcula antes.
        query = "INSERT INTO venta VALUES(NULL, " + total + ",NOW());";
        con.ejecutar(query);
        //RESCATO EL ID DE LA VENTA ACTUAL, PARA LUEGO HACER EL INSERT EN BOLETA.
        int boleta = 0;
        query = "SELECT id FROM venta ORDER BY id DESC LIMIT 1;";
        rs = con.ejecutarSelect(query);

        if (rs.next()) {
            boleta = rs.getInt(1);
        }
        con.desconectar();

        //SE REGISTRA LA VENTA(BOLETA).
        for (Boleta b : listaBoleta) {
            query = "INSERT INTO boleta VALUES(NULL, " + b.getId_producto() + "," + boleta + " , " + b.getCantidad() + ", " + b.getSubtotal() + ");";
            con.ejecutar(query);
            //FALTA QUITAR LA CANTIDAD COMPRADA DEL STOCK.
            query = "UPDATE producto SET stock = stock - " + b.getCantidad() + " WHERE id = " + b.getId_producto() + ";";
            con.ejecutar(query);
        }
    }

    public Tipo_producto getTipo(int id) throws SQLException {
        Tipo_producto t = null;
        String select = "SELECT * FROM tipo_producto WHERE id='" + id + "'";
        rs = con.ejecutarSelect(select);
        if (rs.next()) {
            t = new Tipo_producto();
            t.setId(rs.getInt(1));
            t.setDescripcion(rs.getString(2));

        }

        return t;
    }

    public Producto getProducto(int id) throws SQLException {
        Producto p = null;
        String select = "SELECT nombre FROM Producto WHERE id='" + id + "'";
        rs = con.ejecutarSelect(select);
        if (rs.next()) {
            p = new Producto();
            p.setNombre(rs.getString(1));
        }

        return p;
    }

    public int getStock(int id) throws SQLException {
        int stock = 0;
        query = "SELECT stock FROM producto WHERE id = " + id + ";";
        rs = con.ejecutarSelect(query);

        if (rs.next()) {
            stock = rs.getInt(1);
        }
        return stock;
    }

    public List<Tipo_producto> getListaTipo() throws SQLException {
        query = "SELECT * FROM tipo_producto";

        listaTipoProducto = new ArrayList<>();
        Tipo_producto t;

        rs = con.ejecutarSelect(query);
        while (rs.next()) {
            t = new Tipo_producto();

            t.setId(rs.getInt(1));
            t.setDescripcion(rs.getString(2));

            listaTipoProducto.add(t);

        }
        con.desconectar();
        return listaTipoProducto;
    }

    public List<Venta> getListaVenta() throws SQLException {
        listaVentas = new ArrayList<>();
        query = "SELECT * FROM venta;";
        rs = con.ejecutarSelect(query);
        Venta v = null;
        while (rs.next()) {
            v = new Venta();
            v.setId(rs.getInt(1));
            v.setTotal(rs.getInt(2));
            v.setFecha(rs.getString(3));
            listaVentas.add(v);
        }
        con.desconectar();
        return listaVentas;
    }
    
    public List<Venta> getListaVentaPorMes(int mes, int anio) throws SQLException {
        listaVentas = new ArrayList<>();
        query = "SELECT * FROM venta WHERE MONTH(fecha) = "+mes+" AND YEAR(fecha) = "+anio+" ;";
        rs = con.ejecutarSelect(query);
        Venta v = null;
        while (rs.next()) {
            v = new Venta();
            v.setId(rs.getInt(1));
            v.setTotal(rs.getInt(2));
            v.setFecha(rs.getString(3));
            listaVentas.add(v);
        }
        con.desconectar();
        return listaVentas;
    }
    
     public Venta getVenta(int id) throws SQLException {
        query = "SELECT * FROM venta WHERE id = "+id+";";
        rs = con.ejecutarSelect(query);
        Venta v = null;
        if (rs.next()) {
            v = new Venta();
            v.setId(rs.getInt(1));
            v.setTotal(rs.getInt(2));
            v.setFecha(rs.getString(3));
            listaVentas.add(v);
        }
        con.desconectar();
        return v;
    }
    
    public List<Boleta> getListaBoleta(int id_venta) throws SQLException{
        listaboletas = new ArrayList<>();
        query = "SELECT id_producto, cantidad, subtotal FROM boleta WHERE id_venta = "+id_venta+";";
        Boleta b = null;
        rs = con.ejecutarSelect(query);
        
        while (rs.next()) {            
            b = new Boleta();
            b.setId_producto(rs.getInt(1));
            b.setCantidad(rs.getInt(2));
            b.setSubtotal(rs.getInt(3));
            
            listaboletas.add(b);
        }
        con.desconectar();
        
        return listaboletas;
    }

}
