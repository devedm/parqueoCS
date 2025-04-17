/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs;

import java.sql.*;

/**
 *
 * @author Eddy Mena Lopez
 */
public class Conexion {
    Connection con = null;
    String basedatos = "parqueo"; // nombre de la base de datos
    String url = "jdbc:mysql://localhost:3306/" + basedatos; // direccion, puerto y nombre de db
    String usuario = "root";
    String password = "";
    
    public Connection getConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,usuario,password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }
}
