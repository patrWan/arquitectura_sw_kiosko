
package model;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class Tabla_boleta  implements TableModel{
     private Data d;
    private List<Boleta> listaBoleta;
    
     public Tabla_boleta(List<Boleta> lista) throws SQLException, ClassNotFoundException {
        listaBoleta = lista;
        d = new Data();
    }
    public Boleta getBoleta(int index){// le entrego el indice
        return listaBoleta.get(index);//y devuelve los datos del indice entregado..
    }
    @Override
    public int getRowCount() {
         return listaBoleta.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
          String titulo = null;
        if (columnIndex == 0) {
            titulo = "Producto";
        } else if (columnIndex == 1) {
            titulo = "Cantidad";
        } else if (columnIndex == 2) {
            titulo = "Subtotal";
        } else {
            titulo = "Subtotal";
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
          Boleta b = listaBoleta.get(i);
        if(columnIndex == 0){
              try {
                  Producto p = d.getProducto(b.getId_producto());
                  return p.getNombre();
              } catch (SQLException ex) {
                  Logger.getLogger(Tabla_boleta.class.getName()).log(Level.SEVERE, null, ex);
              }
            
        }else if(columnIndex == 1){
            return b.getCantidad();
        }else{
            return b.getSubtotal();
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
