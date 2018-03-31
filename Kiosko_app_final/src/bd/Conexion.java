package bd;
//java.sql
import java.sql.Connection; // Sirve para establecwer la conexion
import java.sql.Statement; //Ejecuta consultas (querys)
import java.sql.ResultSet; //Tabla virtual --> select
import java.sql.DriverManager; //Obtener la conexion del driver.
import java.sql.SQLException;

public class Conexion {//General mySql --> Para todo
    private Connection con;
    private Statement sentencia;
    private ResultSet tablaVirtual; //rs
    
    //Constructor
    //Datos necesarios
    /*
    Server --> localhost
    user   --> root
    pass   --> ' '
    bdName --> db_alimentos
    */
    
    public Conexion(String server, String user, String pass, String bdName) throws ClassNotFoundException, SQLException{
        //Definir url de Conexion
        String protocolo = "jdbc:mysql://";
        String lineaUsuario = "user="+user;
        String lineaPass = "password="+pass;
        
        String url = protocolo + server + "/" + bdName + "?" + lineaUsuario + "&" + lineaPass;
        
        System.out.println(url);
        
        //Cargar en tiempo de ejecucion el Driver de mySql.
        Class.forName("com.mysql.jdbc.Driver");
        
        //Rescatar el objeto Conexion desde el Driver
        con = DriverManager.getConnection(url);
    }
    
    
    public void ejecutar(String sql) throws SQLException{//Puede ser insert, delete, update
        //1.- Crear la sentencia a traves de la conexion.
            //Abrir la Conexion
        sentencia = con.createStatement();
        //2.- Ejecutar el sql.
        sentencia.execute(sql);
        System.out.println(sql);
        //3.- Cerrar la conexion. --> desconectar()
        desconectar();
    }
    
    public ResultSet ejecutarSelect(String select) throws SQLException{
        sentencia = con.createStatement();
        tablaVirtual = sentencia.executeQuery(select);
        System.out.println(select);
        
        return tablaVirtual;
    }
    
    public void desconectar() throws SQLException{
        sentencia.close();
    }
}
