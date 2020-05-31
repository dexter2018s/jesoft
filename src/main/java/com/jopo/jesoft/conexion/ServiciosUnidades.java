package com.jopo.jesoft.conexion;

import com.jopo.jesoft.model.Alerta;
import java.sql.*;

public class ServiciosUnidades {

//solicitar todos los registros de una tabla
    public ResultSet all() {
        sql = "SELECT * FROM UNIDADES;";
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
        sql = "SELECT idUnidad FROM UNIDADES;";
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

    public ResultSet search(String idUnidad) {
        sql = "SELECT * FROM UNIDADES WHERE idUnidad LIKE ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + idUnidad + "%");
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return rs;
    }

//eliminar un registro mediante su id
    public int delete(String idUnidad) {
        filasAfectadas = 0;
        sql = "DELETE FROM UNIDADES WHERE idUnidad = ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, idUnidad);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

// añadir un registro
    public int insert(String idUnidad, String descripcion) {
        filasAfectadas = 0;
        sql = "INSERT INTO UNIDADES (idUnidad, descripcion) VALUES (?,?);";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, idUnidad);
                pst.setString(2, descripcion);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

// editar un registro
    public int update(String idUnidad, String descripcion) {
        filasAfectadas = 0;
        sql = "UPDATE UNIDADES SET descripcion= ? WHERE idUnidad= ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, descripcion);
                pst.setString(2, idUnidad);
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
