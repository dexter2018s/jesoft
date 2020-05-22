package com.jopo.jesoft.conexion;

import java.sql.*;

public class ServiciosProveedores {

//establecer conexion con base de datos SQL SERVER
    public static Connection conexionAPP() throws Exception {
        String url = "jdbc:sqlserver://192.168.1.35:1433; databaseName=memeseg";
        String user = "sa";
        String password = "corel2duo";
        return DriverManager.getConnection(url, user, password);
    }

//solicitar todos los registros de una tabla
    public ResultSet all() throws Exception {
        sql = "SELECT * FROM Proveedores";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        return pst.executeQuery();
    }

//solicitar todos los registros que cumplan el filtro de nombre moneda
    public ResultSet search(String razonSocial) throws Exception {
        sql = "SELECT * FROM Proveedores WHERE Razon_social LIKE ?;";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, "%" + razonSocial + "%");
        return pst.executeQuery();
    }

//eliminar un registro mediante su id
    public void delete(int id) throws Exception {
        sql = "DELETE FROM Proveedores WHERE Id_proveedor = ?;";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();
    }

// añadir un registro tipo Marca
    public void insert(String razonSocial, long ruc, String direccion, String telefono, int anexo, String celular,String contacto, String correo, String web) throws Exception {
        sql = "INSERT INTO Proveedores VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, razonSocial);
        pst.setLong(2, ruc);
        pst.setString(3, direccion);
        pst.setString(4, telefono);
        pst.setInt(5, anexo);
        pst.setString(6, celular);
        pst.setString(7, contacto);
        pst.setString(8, correo);
        pst.setString(9, web);
        pst.executeUpdate();
    }

// editar un registro
    public void update(int id,String razonSocial, long ruc, String direccion, String telefono, int anexo, String celular,String contacto, String correo, String web) throws Exception {
        sql = "UPDATE Proveedores SET Razon_social= ?, Ruc= ?, Direccion= ?, Telefono= ?, Anexo=?, Celular= ?, Contacto= ?, Correo= ?, Web=? WHERE Id_proveedor= ?;";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, razonSocial);
        pst.setLong(2, ruc);
        pst.setString(3, direccion);
        pst.setString(4, telefono);
        pst.setInt(5, anexo);
        pst.setString(6, celular);
        pst.setString(7, contacto);
        pst.setString(8, correo);
        pst.setString(9, web);
        pst.setInt(10, id);
        pst.executeUpdate();
    }

//cerrar coneccion con la base de datos
    public void close() throws Exception {
        cn.close();
    }
    private Connection cn;
    private String sql;

}
