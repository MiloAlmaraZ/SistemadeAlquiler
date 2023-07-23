/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadealquiler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author emili
 */
public class Metodos {
     String pass = "milo";
    String user = "postgres";
    String host = "localhost";
    Connection con1 = null;
     String nom;
    
    
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

    
    
    
    
}
