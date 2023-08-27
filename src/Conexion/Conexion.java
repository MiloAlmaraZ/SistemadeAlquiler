package Conexion;


/**
 * @author emili
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection cn;
    //constructor vacio
    public Connection conexion(){
        try {
                //Class.forName("com.mysql.jdbc.Driver");            
                cn = DriverManager.getConnection("jdbc:postgresql://localhost/renta","postgres","rajkire16");           
                System.out.println("conectado");  
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cn;
    }
}
