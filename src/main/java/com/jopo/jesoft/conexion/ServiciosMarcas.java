package com.jopo.jesoft.conexion;

import com.jopo.jesoft.model.Alerta;
import java.sql.*;

public class ServiciosMarcas extends Servicios {

//solicitar todos los registros de una tabla
    public ResultSet all() {
        sql = "SELECT * FROM Marcas";
        cn = super.conexionAPP();
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
        sql = "SELECT Nombre FROM Marcas";
        cn = super.conexionAPP();
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
        sql = "SELECT * FROM Marcas WHERE Nombre LIKE ?;";
        cn = super.conexionAPP();
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
        sql = "DELETE FROM Marcas WHERE Id_marca = ?;";
        cn = super.conexionAPP();
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
    public int insert(String nombre, String abrev) {
        filasAfectadas = 0;
        sql = "INSERT INTO Marcas VALUES(?, ?);";
        cn = super.conexionAPP();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, nombre);
                pst.setString(2, abrev);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

// editar un registro
    public int update(int id, String nombre, String abrev) {
        filasAfectadas = 0;
        sql = "UPDATE Marcas SET Nombre= ?, Abreviatura= ? WHERE Id_marca= ?;";
        cn = super.conexionAPP();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, nombre);
                pst.setString(2, abrev);
                pst.setInt(3, id);
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
