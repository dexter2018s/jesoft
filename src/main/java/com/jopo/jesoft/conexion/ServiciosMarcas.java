package com.jopo.jesoft.conexion;

import java.sql.*;

public class ServiciosMarcas {

//establecer conexion con base de datos DERBY
    public static Connection conexionAPP() throws Exception {
        String url = "jdbc:sqlserver://192.168.1.35:1433; databaseName=memeseg";
        String user = "sa";
        String password = "corel2duo";
        return DriverManager.getConnection(url, user, password);
    }

//solicitar todos los registros de una tabla
    public ResultSet all() throws Exception {
        sql = "SELECT * FROM Marcas";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        return pst.executeQuery();
    }

//solicitar todos los registros que cumplan el filtro de marca
    public ResultSet search(String nombre) throws Exception {
        sql = "SELECT * FROM Marcas WHERE Nombre LIKE ?;";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, "%"+nombre+"%");
        return pst.executeQuery();
    }

//eliminar un registro mediante su id
    public void delete(int id) throws Exception {
        sql = "DELETE FROM Marcas WHERE Id_marca = ?;";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();
    }

// añadir un registro tipo Marca
    public void insert(String nombre, String abrev) throws Exception {
        sql = "INSERT INTO Marcas VALUES(?, ?);";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, nombre);
        pst.setString(2, abrev);
        pst.executeUpdate();
    }

// editar un registro tipo Marca
    public void update(int id, String nombre, String abrev) throws Exception {
        sql = "UPDATE Marcas SET Nombre= ?, Abreviatura= ? WHERE Id_marca= ?;";
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, nombre);
        pst.setString(2, abrev);
        pst.setInt(3, id);
        pst.executeUpdate();
    }

//cerrar coneccion con la base de datos
    public void close() throws Exception {
        cn.close();
    }
    private Connection cn;
    private String sql;

}
