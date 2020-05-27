package com.jopo.jesoft.conexion;

import com.jopo.jesoft.model.Alerta;
import java.io.FileInputStream;
import java.sql.*;

public class ServiciosProductos extends Servicios {

//solicitar todos los registros de una tabla
    public ResultSet all() {
        sql = "SELECT Id_productos, Codigo, Productos.Descripcion, Marcas.Nombre, Web, Unidades.Abreviatura, Imagen, PrecioCompra, PrecioVenta\n"
                + "FROM Productos INNER JOIN Marcas ON Productos.Id_marca=Marcas.Id_marca INNER JOIN Unidades ON Productos.Id_unidad=Unidades.Abreviatura";
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
    public ResultSet search(String descripcion) {
        sql = "SELECT Id_productos, Codigo, Productos.Descripcion, Marcas.Nombre, Web, Unidades.Abreviatura, Imagen, PrecioCompra, PrecioVenta\n"
                + "FROM Productos INNER JOIN Marcas ON Productos.Id_marca=Marcas.Id_marca INNER JOIN Unidades ON Productos.Id_unidad=Unidades.Abreviatura "
                + "WHERE Productos.Descripcion LIKE ?;";
        cn = super.conexionAPP();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + descripcion + "%");
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
        sql = "DELETE FROM Productos WHERE Id_productos = ?;";
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
    public int insert(String codigo, String descripcion, String marca, String web, String unidad, FileInputStream imagen, double precioCompra, double precioVenta) throws Exception {
        sql = "INSERT INTO Productos VALUES(?, ?, (SELECT Id_marca FROM Marcas WHERE Nombre=?), ?, ?, ?, ?, ?)";
        filasAfectadas = 0;
        cn = super.conexionAPP();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, codigo);
                pst.setString(2, descripcion);
                pst.setString(3, marca);
                pst.setString(4, web);
                pst.setString(5, unidad);
                pst.setBinaryStream(6, imagen);
                pst.setDouble(7, precioCompra);
                pst.setDouble(8, precioVenta);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

// editar un registro
    public int update(int id, String codigo, String descripcion, String marca, String web, String unidad, FileInputStream imagen, double precioCompra, double precioVenta) throws Exception {
        sql = "UPDATE Productos SET Codigo= ?, Descripcion= ?, Id_marca= (SELECT Id_marca FROM Marcas WHERE Nombre=?), Web= ?, Id_unidad=?,"
                + " Imagen= ?, PrecioCompra= ?, PrecioVenta= ? WHERE Id_productos= ?;";
        filasAfectadas = 0;
        cn = super.conexionAPP();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, codigo);
                pst.setString(2, descripcion);
                pst.setString(3, marca);
                pst.setString(4, web);
                pst.setString(5, unidad);
                pst.setBinaryStream(6, imagen);
                pst.setDouble(7, precioCompra);
                pst.setDouble(8, precioVenta);
                pst.setInt(9, id);
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
