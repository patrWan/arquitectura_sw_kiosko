package model;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class Tabla_venta  implements TableModel{
     private Data d;
    private List<Venta> listaVentas;
    
     public Tabla_venta(List<Venta> lista) throws SQLException, ClassNotFoundException {
        listaVentas = lista;
        d = new Data();
    }
    public Venta getVenta(int index){// le entrego el indice
        return listaVentas.get(index);//y devuelve los datos del indice entregado..
    }
    @Override
    public int getRowCount() {
         return listaVentas.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
          String titulo = null;
        if (columnIndex == 0) {
            titulo = "Id";
        } else if (columnIndex == 1) {
            titulo = "Total";
        } else if (columnIndex == 2) {
            titulo = "Fecha";
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
          Venta v = listaVentas.get(i);
        if(columnIndex == 0){
            return v.getId();
        }else if(columnIndex == 1){
            return v.getTotal();
        }else{
            return v.getFecha();
        }
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
