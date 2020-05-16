package com.jopo.jesoft.conexion;

import java.sql.*;

public class Servicios {

//establecer conexion con base de datos DERBY
    public static Connection conexionAPP() throws Exception {
        String url="jdbc:sqlserver://192.168.1.7:1433; databaseName=memeseg";
        String user="sa";
        String password="corel2duo";
        return DriverManager.getConnection(
                "jdbc:derby:c:/Onran;create=true","System","Tec01.jhh");
    }

    public ResultSet buscarPorCatalogo(String codigo) throws Exception {
        String sql = "SELECT*FROM PRODUCTO WHERE codigo = " + "'" + codigo + "' OR codigo_fab = " + "'" + codigo + "'";
        Connection cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        return pst.executeQuery();
    }
    

    public ResultSet buscarProductos(String codigo) throws Exception {
        String sql = "SELECT*FROM PRODUCTO WHERE codigo like " + "'%" + codigo + "%' OR codigo_fab LIKE " + "'%" + codigo + "%'";
        System.out.println(sql);
        Connection cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        return pst.executeQuery();
    }
    
    
    public ResultSet buscarDescripcion(String descripcion) throws Exception {
        String sql = "SELECT*FROM PRODUCTO WHERE nombre like " + "'%" + descripcion + "%'";
        Connection cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        return pst.executeQuery();
    }
    
    public ResultSet listaProductos() throws Exception {
        String sql = "SELECT*FROM PRODUCTO";
        Connection cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        return pst.executeQuery();
    }

    public ResultSet listaProductosPorMarca(int mID) throws Exception {
        String sql = "SELECT CODIGO, MANUFACTURER_ID,purchase_cost"
                + " FROM APP.PRODUCTOS where MANUFACTURER_ID > ?";
        Connection cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, mID);
        return pst.executeQuery();
    }

    public ResultSet listaFabricanteID() throws Exception {
        String sql = "SELECT MANUFACTURER_ID FROM MANUFACTURER";
        Connection cn = conexionAPP();
        PreparedStatement pst = cn.prepareStatement(sql);
        return pst.executeQuery();
    }

}
