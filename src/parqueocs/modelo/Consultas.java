/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.modelo;

import java.sql.*;
import javax.swing.JOptionPane;
import parqueocs.Conexion;
import parqueocs.modelo.Usuario;
/**
 *
 * @author minio
 */
public class Consultas extends Conexion{
    
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
    
    public Usuario buscarUsuario(Usuario usuario){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM Usuario WHERE cedula = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getCedula());
            rs = ps.executeQuery();
            if(rs.next()){
                usuario.setCedula(Integer.parseInt(rs.getString("cedula")));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                return usuario;
            }
            return null;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
