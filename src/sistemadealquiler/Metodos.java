package sistemadealquiler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Metodos {

    String pass = "rajkire16";
    String user = "postgres";
    String host = "localhost";
    String BD = "Renta";
    Connection con1 = null;
    String nom;

    //Linea de prueba
    public Connection conectaServer() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost" + host + "/", user, pass);
            System.out.println("conexión establecida");

        } catch (Exception ex) {
            System.out.println("Error al conectar:" + ex.getMessage());
        }
        return con;
    }

    public Connection conectaBase(String bd) {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://" + host + "/" + bd, user, pass);
            System.out.println("conexión establecida");

        } catch (Exception ex) {
            System.out.println("Error al conectar:" + ex.getMessage());
        }
        return con;
    }

    //constructor vacio
    public Connection conexion() {
        Connection cn = null;
        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection("jdbc:postgresql://localhost/"+BD, "postgres", pass);
            System.out.println("conectado");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cn;
    }


    public int contraseña(String contraseña, String bd) {//Validar contraseña 
        int a = 0;
        String SQL = "select * from USUARIOS where contrasena ='" + contraseña + "' ";
        try {
            Statement s = conectaBase(bd).createStatement();
            ResultSet rs = s.executeQuery(SQL);
            if (rs.next()) {
                a = 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error contraseña " + e);
        }
        return a;
    }

    public int nombre(String usuario, String bd) {// validar usuario
        Connection c;
        int a = 0;
        String SQL = "select * from USUARIOS where usuario='" + usuario + "'";
        try {
            Statement s = conectaBase(bd).createStatement();
            ResultSet rs = s.executeQuery(SQL);
            if (rs.next()) {
                a = 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error nombre" + e);
        }
        return a;
    }

    public String usuario(String usuario, String bd) {// determinamos si es administrador o usuario 
        String SQL = "select * from USUARIOS where usuario='" + usuario + "' ";
        try {
            Statement s = conectaBase(bd).createStatement();
            ResultSet rs = s.executeQuery(SQL);
            if (rs.next()) {
                nom = rs.getString(2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error usuario" + e);
        }
        return nom;
    }

    public void insertaRenta(String bd, String table, String idcuartos, String Nombre, String FechaInicio, String FechaFin) {
        try {
            Statement s = conectaBase(bd).createStatement();
            PreparedStatement ps = conectaBase(bd).prepareStatement("INSERT INTO " + table + "(idcuartos,Nombre,FechaInicio,FechaFin)values (?,?,?,?)");
            ps.setInt(1, Integer.parseInt(idcuartos));
            ps.setString(2, Nombre);
            ps.setDate(3, java.sql.Date.valueOf(FechaInicio));
            ps.setDate(4, java.sql.Date.valueOf(FechaFin));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "datos insertados correctamente");
            conectaBase(bd).close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void fechaInicio(String bd) {
        String SQL = "select fechainicio from Renta";
        try {
            Statement s = conectaBase(bd).createStatement();
            ResultSet rs = s.executeQuery(SQL);
            if (rs.next()) {
                nom = rs.getString(1);
            }
            System.out.println("Fecha inicio" + nom);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error usuario" + e);
        }
    }

    public void DatosPersona(String bd) {
        String SQL = "select fechainicio from Renta";
        try {
            Statement s = conectaBase(bd).createStatement();
            ResultSet rs = s.executeQuery(SQL);
            if (rs.next()) {
                nom = rs.getString(1);
            }
            System.out.println("Fecha inicio" + nom);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error usuario" + e);
        }
    }

    public void fechaFin(String bd, LocalDate Fecha) throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost/" + bd; // Usa el nombre de la base de datos en el jdbcUrl
        //   String user = "tu_usuario"; // Reemplaza con el nombre de usuario de la base de datos
        // String pass = "tu_contraseña"; // Reemplaza con la contraseña de la base de datos
        String SQL = "SELECT fechainicio FROM Renta WHERE fechainicio >= ?";
        try (Connection con2 = DriverManager.getConnection(jdbcUrl, user, pass);
                PreparedStatement statement = con2.prepareStatement(SQL)) {
            statement.setObject(1, Fecha);
            LocalDate fechaVencimiento = null;
            try (ResultSet resultSet = statement.executeQuery()) {
                // Procesar el resultado
                while (resultSet.next()) {
                    fechaVencimiento = resultSet.getObject("fechainicio", LocalDate.class);
                    System.out.println("Fecha fin: " + fechaVencimiento);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    public void eliminarCliente(String bd, String nombreTabla, int a) {
            try {
                Statement s = conectaBase(bd).createStatement();
                String eliminar = "DELETE FROM " + nombreTabla + " WHERE idcuartos= " + a + ";";
                s.executeUpdate(eliminar);
                JOptionPane.showMessageDialog(null, "Se elimino cliente exitosamente");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }


    public void RellenarComboBox(String bd, String tabla, String valor, JComboBox combo){
        String sql = "select * from categoria";
        try{
            Statement st = conectaBase(bd).createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                combo.addItem(rs.getString(valor));
            }    
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }   
    }
}
