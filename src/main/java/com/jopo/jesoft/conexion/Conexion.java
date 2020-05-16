package com.jopo.jesoft.conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author joelh
 */
public class Conexion {

    public Conexion(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void conectarBD() {
        try {
            Connection conex = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
        } catch (SQLException ex) {
            System.out.println("No se logró la conexion con la Base de datos MEMESEG");
            System.out.println(ex);
        }
    }
    private final String url;
    private final String user;
    private final String password;
}
