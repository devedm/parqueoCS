/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.modelo;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
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
            ps.setDate(1, java.sql.Date.valueOf(vehiculo.getFecha())); // fecha
            ps.setTime(2, java.sql.Time.valueOf(vehiculo.getEntradaHora())); // entradaHora
            ps.setTime(3, java.sql.Time.valueOf(vehiculo.getSalidaHora())); // salidaHora
            ps.setInt(4, vehiculo.getDuracionMinutos()); // duracionMinutos
            ps.setString(5, vehiculo.getPlaca());
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
    
    public ArrayList<Vehiculo> buscarVehiculosUsuarioFull(Usuario usuario){
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
                String placa = rs.getString("placa");
                Date fecha = rs.getDate("fecha");
                Vehiculo vehiculo = new Vehiculo(placa , fecha.toLocalDate(), LocalTime.of(rs.getTime("entradaHora").getHours(), rs.getTime("entradaHora").getMinutes()), LocalTime.of(rs.getTime("salidaHora").getHours(), rs.getTime("salidaHora").getMinutes()), rs.getInt("duracionMinutos"));
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
    
    public Vehiculo buscarVehiculosPorPlaca(Vehiculo vehiculo){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM vehiculo WHERE placa = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, vehiculo.getPlaca());
            rs = ps.executeQuery();
            while(rs.next()){
                return new Vehiculo(rs.getString("placa"),LocalDate.of(rs.getDate("fecha").getYear(), rs.getDate("fecha").getMonth(), rs.getDate("fecha").getDay()), LocalTime.of(rs.getTime("entradaHora").getHours(), rs.getTime("entradaHora").getMinutes()), LocalTime.of(rs.getTime("salidaHora").getHours(), rs.getTime("salidaHora").getMinutes()), rs.getInt("duracionMinutos"));
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
    
    public boolean registrarParqueo(Parqueo parqueo){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "Insert INTO parqueo (nombreParqueo) VALUES (?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, parqueo.getNombreParqueo()); // placa
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
    
    public boolean registrarEspacioParqueo(EspacioParqueo espacioParqueo){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "Insert INTO espacioParqueo (vehiculoPlaca, parqueoId) VALUES (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, espacioParqueo.getPlacaVehiculo()); // placa
            ps.setInt(2, espacioParqueo.getIdParqueo()); // Id Parqueo default 0
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
    
    public ArrayList<EspacioParqueo> buscarEspacioParqueoPorParqueo(Parqueo parqueo){
        ArrayList listaEspaciosParqueo= new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM espacioparqueo WHERE parqueoId = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, parqueo.getId());
            rs = ps.executeQuery();
            while(rs.next()){
                EspacioParqueo espacioParqueo = new EspacioParqueo(rs.getInt("id"), rs.getString("vehiculoPlaca"), rs.getInt("parqueoId"));
                listaEspaciosParqueo.add(espacioParqueo);
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
        return listaEspaciosParqueo;
    }
    
    public EspacioParqueo buscarEspacioParqueoPorVehiculo(Vehiculo vehiculo){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM espacioparqueo WHERE vehiculoPlaca = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, vehiculo.getPlaca());
            rs = ps.executeQuery();
            while(rs.next()){
                return new EspacioParqueo(rs.getInt("id"), rs.getString("vehiculoPlaca"), rs.getInt("parqueoId"));
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
    
    public boolean modificarEspacioParqueo(EspacioParqueo espacio){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE espacioparqueo SET vehiculoPlaca=?  WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, espacio.getPlacaVehiculo()); // Placa vehiculo Parqueado
            ps.setInt(2, espacio.getId()); // id de espacio parqueo
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
}
