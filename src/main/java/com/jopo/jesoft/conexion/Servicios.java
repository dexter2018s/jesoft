package com.jopo.jesoft.conexion;

import java.sql.*;
import java.util.logging.Logger;

public class Servicios {

//establecer conexion con base de datos DERBY
    public static Connection conexionAPP() throws Exception {
        String url = "jdbc:sqlserver://192.168.1.35:1433; databaseName=memeseg";
        String user = "sa";
        String password = "corel2duo";
        return DriverManager.getConnection(url, user, password);
    }

    public ResultSet allRegister(String table) throws Exception {
        this.sql = "SELECT * FROM " + table;
        System.out.println(this.sql);
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(this.sql);
        return pst.executeQuery();
    }

    public void deleteRegister(String table, int id) throws Exception {
        this.sql = "DELETE FROM " + table + " WHERE Id_Marca = ?;";
        System.out.println(this.sql);
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(this.sql);
        pst.setInt(1, id);
        pst.executeUpdate();
    }

    // "insert into person values (null,?,?,?)");
    public void insertRegister(String table,String nombre, String abrev) throws Exception {
        this.sql = "INSERT INTO " + table + " VALUES(?, ?);";
        System.out.println(this.sql);
        cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(this.sql);
        pst.setString(1, nombre);
        pst.setString(2, abrev);
        pst.executeUpdate();
    }

    public void close() throws Exception {
        cn.close();
        System.out.println("BD-Conexion con base de datos cerrada");
    }

    private ResultSet rs;
    private Connection cn;
    private String sql;

}
