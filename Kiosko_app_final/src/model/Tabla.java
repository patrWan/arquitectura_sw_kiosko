
package model;

import java.sql.SQLException;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class Tabla  implements TableModel{
     private Data d;
    private List<Producto> listaProductos;
    
     public Tabla(List<Producto> lista) throws SQLException, ClassNotFoundException {
        listaProductos = lista;
        d = new Data();
    }
    public Producto getProducto(int index){// le entrego el indice
        return listaProductos.get(index);//y devuelve los datos del indice entregado..
    }
    @Override
    public int getRowCount() {
         return listaProductos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
          String titulo = null;
        if (columnIndex == 0) {
            titulo = "ID";
        } else if (columnIndex == 1) {
            titulo = "Marca";
        } else if (columnIndex == 2) {
            titulo = "Modelo";
        } else if (columnIndex == 3) {
            titulo = "Precio";
        } else {
            titulo = "Stock";
        }
        return titulo;
    
    }

    @Override
    public Class<?> getColumnClass(int i) {
          return String.class;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
              return false;
    }

    @Override
    public Object getValueAt(int i, int columnIndex) {
          Producto p = listaProductos.get(i);
        if(columnIndex == 0){
            return p.getId();
        }else if(columnIndex == 1){
            try {
                Tipo_producto t = d.getTipo(p.getId_tipo());
                return p.getNombre();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }else if(columnIndex == 2){
            return p.getNombre();
        }else if(columnIndex == 3){
            return p.getPrecio();
        }else{
            return p.getStock();
        }
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
    }
    
}
