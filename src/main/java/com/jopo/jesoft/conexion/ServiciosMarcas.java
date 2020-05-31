package com.jopo.jesoft.conexion;

import com.jopo.jesoft.model.Alerta;
import java.sql.*;


public class ServiciosMarcas {

//solicitar todos los registros de una tabla
    public ResultSet all() {
        sql = "SELECT * FROM MARCAS ORDER BY idMarca;";
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
    //solicitar todos los registros de una tabla

    public ResultSet getNombres() {
        sql = "SELECT nombre FROM MARCAS;";
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
        sql = "SELECT * FROM MARCAS WHERE nombre LIKE ?;";
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
        sql = "DELETE FROM MARCAS WHERE idMarca = ?;";
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
    public int insert(String nombre) {
        filasAfectadas = 0;
        sql = "INSERT INTO MARCAS (nombre) VALUES (?);";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, nombre);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

// editar un registro
    public int update(int id, String nombre) {
        filasAfectadas = 0;
        sql = "UPDATE MARCAS SET nombre= ? WHERE idMarca= ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, nombre);
                pst.setInt(2, id);
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
