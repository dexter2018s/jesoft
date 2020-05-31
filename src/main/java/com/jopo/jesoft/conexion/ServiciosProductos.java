package com.jopo.jesoft.conexion;

import com.jopo.jesoft.model.Alerta;
import java.sql.*;

public class ServiciosProductos {

//solicitar todos los registros de una tabla
    public ResultSet all() {
        sql = "SELECT idProducto, codigo, PRODUCTOS.descripcion, web, imagen, UNIDADES.idUnidad, MARCAS.nombre,\n"
                + "TIPOPRODUCTOS.nombre, precioCompra, precioVenta, MONEDAS.simbolo\n"
                + "FROM PRODUCTOS\n"
                + "INNER JOIN MARCAS ON PRODUCTOS.idMarca=MARCAS.idMarca\n"
                + "INNER JOIN UNIDADES ON PRODUCTOS.idUnidad=UNIDADES.idUnidad\n"
                + "INNER JOIN TIPOPRODUCTOS ON PRODUCTOS.idTipoProducto=TIPOPRODUCTOS.idTipoProducto\n"
                + "INNER JOIN MONEDAS ON PRODUCTOS.idMoneda=MONEDAS.idMoneda\n"
                + "ORDER BY PRODUCTOS.idProducto;";
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
    public ResultSet search(String descripcion) {
        sql = "SELECT idProducto, codigo, PRODUCTOS.descripcion, web, imagen, UNIDADES.idUnidad, MARCAS.nombre, ,\n"
                + "TIPOPRODUCTOS.nombre, precioCompra, precioVenta, MONEDAS.simbolo\n"
                + "FROM PRODUCTOS\n"
                + "INNER JOIN MARCAS ON PRODUCTOS.idMarca=MARCAS.idMarca\n"
                + "INNER JOIN UNIDADES ON PRODUCTOS.idUnidad=UNIDADES.idUnidad\n"
                + "INNER JOIN TIPOPRODUCTOS ON PRODUCTOS.idTipoProducto=TIPOPRODUCTOS.idTipoProducto\n"
                + "INNER JOIN MONEDAS ON PRODUCTOS.idMoneda=MONEDAS.idMoneda\n"
                + "WHERE PRODUCTOS.idProducto=?"
                + "ORDER BY PRODUCTOS.idProducto;";
        cn = Servicio.getConnection();
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
        sql = "DELETE FROM PRODUCTOS WHERE idProducto = ?;";
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
    public int insert(String codigo, String descripcion, String web, String imagen, String unidad, String marca, String tipoProducto,
            double precioCompra, double precioVenta, String moneda) {
        sql = "INSERT INTO PRODUCTOS (codigo, descripcion, web, imagen, idUnidad, idMarca, idTipoProducto, precioCompra, precioVenta, idMoneda)\n"
                + " VALUES(?, ?, ?, ?, ?,(SELECT idMarca FROM MARCAS WHERE nombre=?), (SELECT idTipoProducto FROM TIPOPRODUCTOS WHERE nombre=?),"
                + " ?,?,(SELECT idMoneda FROM MONEDAS WHERE simbolo=?));";
        filasAfectadas = 0;
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, codigo);
                pst.setString(2, descripcion);
                pst.setString(3, web);
                pst.setString(4, imagen);
                pst.setString(5, unidad);
                pst.setString(6, marca);
                pst.setString(7, tipoProducto);
                pst.setDouble(8, precioCompra);
                pst.setDouble(9, precioVenta);
                pst.setString(10, moneda);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

// editar un registro
    public int update(int idProducto, String codigo, String descripcion, String web, String imagen, String unidad, String marca, String tipoProducto,
            double precioCompra, double precioVenta, String moneda) throws Exception {
        sql = "UPDATE PRODUCTOS SET codigo= ?, descripcion= ?, web = ?, imagen = ?, idUnidad = ?, idMarca= (SELECT idMarca FROM MARCAS WHERE nombre=?), "
                + "idTipoProducto = (SELECT idTipoProducto FROM TIPOPRODUCTOS WHERE nombre=?), "
                + "precioCompra=?, precioVenta=?, idMoneda= (SELECT idMoneda FROM MONEDAS WHERE simbolo=?) "
                + "WHERE idProducto= ?;";
        filasAfectadas = 0;
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, codigo);
                pst.setString(2, descripcion);
                pst.setString(3, web);
                pst.setString(4, imagen);
                pst.setString(5, unidad);
                pst.setString(6, marca);
                pst.setString(7, tipoProducto);
                pst.setDouble(8, precioCompra);
                pst.setDouble(9, precioVenta);
                pst.setString(10, moneda);
                pst.setInt(11, idProducto);
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
