/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.modelo;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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
    
    public boolean registrarVehiculo(Vehiculo vehiculo, Usuario usuario){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "Insert INTO vehiculo (placa, fecha, entradaHora, salidaHora, duracionMinutos, cedulaUsuario) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, vehiculo.getPlaca()); // placa
            ps.setDate(2, java.sql.Date.valueOf(vehiculo.getFecha())); // fecha
            ps.setTime(3, java.sql.Time.valueOf(vehiculo.getEntradaHora())); // entradaHora
            ps.setTime(4, java.sql.Time.valueOf(vehiculo.getSalidaHora())); // salidaHora
            ps.setInt(5, vehiculo.getDuracionMinutos()); // duracionMinutos
            ps.setInt(6, usuario.getCedula()); // cedulaUsuario
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
    
    public boolean modificarVehiculo(Vehiculo vehiculo){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE vehiculo SET fecha=?, entradaHora=?, salidaHora=?, duracionMinutos=?  WHERE placa=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(2, java.sql.Date.valueOf(vehiculo.getFecha())); // fecha
            ps.setTime(3, java.sql.Time.valueOf(vehiculo.getEntradaHora())); // entradaHora
            ps.setTime(4, java.sql.Time.valueOf(vehiculo.getSalidaHora())); // salidaHora
            ps.setInt(5, vehiculo.getDuracionMinutos()); // duracionMinutos
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
    
    public ArrayList<Vehiculo> buscarVehiculosUsuario(Usuario usuario){
        ArrayList listaVehiculos= new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM vehiculo WHERE cedulaUsuario = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getCedula());
            rs = ps.executeQuery();
            while(rs.next()){
                Vehiculo vehiculo = new Vehiculo(rs.getString("placa"));
                listaVehiculos.add(vehiculo);
            }
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
        return listaVehiculos;
    }
    
    public boolean eliminarVehiculo(Vehiculo vehiculo){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM Vehiculo WHERE placa=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, vehiculo.getPlaca());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Producto eliminado con exito!");
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
