/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.conexion;

import com.jopo.jesoft.model.Alerta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joelh
 */
public class ServiciosPrecios {

    //solicitar todos los registros de una tabla
    public ResultSet all() {
        sql = "SELECT * FROM PRECIOS ORDER BY idPrecio;";
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

//    public ResultSet getNombres() {
//        sql = "SELECT nombre FROM MARCAS;";
//        cn = Servicio.getConnection();
//        if (cn != null) {
//            try {
//                PreparedStatement pst = cn.prepareStatement(sql);
//                rs = pst.executeQuery();
//            } catch (SQLException ex) {
//                Alerta.error("" + ex);
//            }
//        }
//        return rs;
//    }
    //solicitar todos los registros que cumplan el filtro
    public ResultSet search(int id) {
        sql = "SELECT * FROM PRECIOS WHERE nombre LIKE ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + id + "%");
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
        sql = "DELETE FROM PRECIOS WHERE idMarca = ?;";
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
    public int insert(int idProducto, double precioCompra, double precioVenta, double descuento, String simbolo) {
        filasAfectadas = 0;
        sql = "INSERT INTO PRECIOS (idProducto,precioCompra, precioVenta, descuento, (SELECT * FROM MONEDAS WHERE simbolo = ? )) VALUES (?,?,?,?,?);";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, idProducto);
                pst.setDouble(2, precioCompra);
                pst.setDouble(3, precioVenta);
                pst.setDouble(4, descuento);
                pst.setString(5, simbolo);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

// editar un registro
    public int update(int idProducto, double precioCompra, double precioVenta, double descuento, String simbolo) {
        filasAfectadas = 0;
        sql = "UPDATE PRECIOS SET nombre= ? WHERE idMarca= ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setDouble(1, precioCompra);
                pst.setDouble(2, precioVenta);
                pst.setDouble(3, descuento);
                pst.setString(4, simbolo);
                pst.setInt(4, idProducto);
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
