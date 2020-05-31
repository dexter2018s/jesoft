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
public class Servicio {

    //establecer conexion con base de datos SQL SERVER
    private Servicio() {
        url = "jdbc:mysql://35.193.188.51:3306/memeseg";
        user = "sa";
        password = "corel2duo";
        try {
            cn = DriverManager.getConnection(url, user, password);
            //Alerta.info("Conexion exitosa");
            //System.out.println("Conexion exitosa");
        } catch (SQLException ex) {
            Alerta.error("" + ex);
            //System.out.println("Conexion fallida: "+ex);
        }
    }

    public static Connection getConnection() {
        if (cn == null) {
            new Servicio();
        }
        return cn;
    }

    public static void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    // Propiedades
    private static Connection cn = null;
    private String url;
    private String user;
    private String password;
}
