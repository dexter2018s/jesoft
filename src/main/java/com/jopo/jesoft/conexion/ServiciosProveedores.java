package com.jopo.jesoft.conexion;

import com.jopo.jesoft.model.Alerta;
import java.sql.*;

public class ServiciosProveedores{

//solicitar todos los registros de una tabla
    public ResultSet all() {
        sql = "SELECT * FROM PROVEEDORES  ORDER BY idProveedor;";
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
    public ResultSet getRazonesSociales() {
        sql = "SELECT razonSocial FROM PROVEEDORES;";
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

    public ResultSet search(String razonSocial) {
        sql = "SELECT * FROM PROVEEDORES WHERE razonSocial LIKE ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + razonSocial + "%");
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
        sql = "DELETE FROM PROVEEDORES WHERE idProveedor = ?;";
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
    public int insert(String razonSocial, String ruc, String direccion, String contacto, String telefono, String anexo, String celular, String correo, String web) {
        filasAfectadas = 0;
        sql = "INSERT INTO PROVEEDORES (razonSocial, ruc, direccion, contacto, telefono, anexo, celular, correo, web) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, razonSocial);
                pst.setString(2, ruc);
                pst.setString(3, direccion);
                pst.setString(4, contacto);
                pst.setString(5, telefono);
                pst.setString(6, anexo);
                pst.setString(7, celular);
                pst.setString(8, correo);
                pst.setString(9, web);
                filasAfectadas = pst.executeUpdate();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return filasAfectadas;
    }

// editar un registro
    public int update(int id, String razonSocial, String ruc, String direccion, String contacto, String telefono, String anexo, String celular, String correo, String web) {
        filasAfectadas = 0;
        sql = "UPDATE PROVEEDORES SET razonSocial= ?, ruc= ?, direccion= ?, contacto= ?, telefono= ?, anexo= ?, celular= ?, correo= ?, web= ? WHERE idMarca= ?;";
        cn = Servicio.getConnection();
        if (cn != null) {
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, razonSocial);
                pst.setString(2, ruc);
                pst.setString(3, direccion);
                pst.setString(4, contacto);
                pst.setString(5, telefono);
                pst.setString(6, anexo);
                pst.setString(7, celular);
                pst.setString(8, correo);
                pst.setString(9, web);
                pst.setInt(10, id);
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
