package com.jopo.jesoft.conexion;

import java.sql.*;

public class ServiciosUnidades {

//establecer conexion con base de datos SQL SERVER
    public static Connection conexionAPP() throws Exception {
        String url = "jdbc:sqlserver://192.168.1.35:1433; databaseName=memeseg";
        String user = "sa";
        String password = "corel2duo";
        return DriverManager.getConnection(url, user, password);
    }

//solicitar todos los registros de una tabla
    public ResultSet all() throws Exception {
        sql = "SELECT * FROM Unidades";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        return pst.executeQuery();
    }

//solicitar todos los registros que cumplan el filtro de nombre moneda
    public ResultSet search(String descripcion) throws Exception {
        sql = "SELECT * FROM Unidades WHERE Descripcion LIKE ?;";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, "%" + descripcion + "%");
        return pst.executeQuery();
    }

//eliminar un registro
    public void delete(String abreviatura) throws Exception {
        sql = "DELETE FROM Unidades WHERE Abreviatura = ?;";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, abreviatura);
        pst.executeUpdate();
    }

// añadir un registro
    public void insert(String abreviatura, String descripcion) throws Exception {
        sql = "INSERT INTO Unidades VALUES(?, ?);";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, abreviatura);
        pst.setString(2, descripcion);
        pst.executeUpdate();
    }

// editar un registro tipo Marca
    public void update(String abreviatura, String descripcion) throws Exception {
        sql = "UPDATE Monedas SET Descripcion= ? WHERE Abreviatura= ?;";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, descripcion);
        pst.setString(2, abreviatura);
        pst.executeUpdate();
    }

//cerrar coneccion con la base de datos
    public void close() throws Exception {
        cn.close();
    }
    private Connection cn;
    private String sql;

}
