/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs;

import java.sql.*;
import javax.swing.JOptionPane;
import parqueocs.modelo.Usuario;
/**
 *
 * @author minio
 */
public class ConsultasUsuario extends Conexion{
    
    public boolean registrarUsuario(Usuario usuario){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "Insert INTO Usuario (cedula, nombre, contrasenia) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getCedula());
            ps.setString(2,usuario.getNombre());
            ps.setString(3, usuario.getContrasenia());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Usuario registrado con exito!");
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
