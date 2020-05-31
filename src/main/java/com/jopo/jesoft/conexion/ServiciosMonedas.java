package com.jopo.jesoft.conexion;

import com.jopo.jesoft.model.Alerta;
import java.sql.*;

public class ServiciosMonedas {

//solicitar todos los registros de una tabla
    public ResultSet all() {
        sql = "SELECT * FROM MONEDAS ORDER BY idMoneda;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return rs;
    }

    public ResultSet getNombres() {
        sql = "SELECT simbolo FROM MONEDAS;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return rs;
    }
//solicitar todos los registros que cumplan el filtro

    public ResultSet search(String nombre) {
        sql = "SELECT * FROM MONEDAS WHERE nombre LIKE ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + nombre + "%");
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return rs;
    }

//eliminar un registro mediante su id
    public int delete(int id) {
        filasAfectadas = 0;
        sql = "DELETE FROM MONEDAS WHERE idMoneda = ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

// añadir un registro
    public int insert(String nombre, String simbolo, String descripcion) {
        filasAfectadas = 0;
        sql = "INSERT INTO MONEDAS (nombre, simbolo, descripcion) VALUES (?,?,?);";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, nombre);
                pst.setString(2, simbolo);
                pst.setString(3, descripcion);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

// editar un registro
    public int update(int id, String nombre, String simbolo, String descripcion) {
        filasAfectadas = 0;
        sql = "UPDATE MONEDAS SET nombre= ?, simbolo= ?, descripcion=? WHERE idMoneda= ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, nombre);
                pst.setString(2, simbolo);
                pst.setString(3, descripcion);
                pst.setInt(4, id);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

    private Connection cn;
    private String sql;
    private ResultSet rs;
    int filasAfectadas = 0;

}
