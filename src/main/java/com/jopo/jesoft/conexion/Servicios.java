/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.conexion;

import com.jopo.jesoft.model.Alerta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author joelh
 */
public class Servicios {

    //establecer conexion con base de datos SQL SERVER
    public static Connection conexionAPP() {
        String url = "jdbc:sqlserver://192.168.1.7:1433; databaseName=memeseg";
        String user = "sa";
        String password = "corel2duo";
        try {
            cn = DriverManager.getConnection(url, user, password);
            return cn;

        } catch (SQLException ex) {
            Alerta.error("" + ex);
            return null;
        }
    }

    //cerrar coneccion con la base de datos SQL 
    public static void close() {
        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }

        }
    }

    private static Connection cn;
}
