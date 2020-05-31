/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosMonedas;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joelh
 */
public class Moneda {

    public Moneda() {

    }

    public Moneda(int id, String nombre, String simbolo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFilasAfectadas() {
        return filasAfectadas;
    }

    public void setFilasAfectadas(int filasAfectadas) {
        this.filasAfectadas = filasAfectadas;
    }

    public int getTotalFilas() {
        return totalFilas;
    }

    public void setTotalFilas(int totalFilas) {
        this.totalFilas = totalFilas;
    }

    public ObservableList<Moneda> getMonedas() {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosMonedas s = new ServiciosMonedas();
        ResultSet rs = s.all();
        if (rs != null) {
            try {
                while (rs.next()) {
                    id = rs.getInt(1);
                    nombre = rs.getString(2);
                    simbolo = rs.getString(3);
                    descripcion = rs.getString(4);
                    Moneda m = new Moneda(id, nombre, simbolo, descripcion);
                    obs.add(m);
                    totalFilas++;
                }
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        // s.close();
        return obs;
    }

    public void delete(int id) {
        ServiciosMonedas s = new ServiciosMonedas();
        filasAfectadas = 0;
        try {
            filasAfectadas = s.delete(id);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("Eliminación Exitosa");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        // s.close();
    }

    public void insert(String nombre, String simbolo, String descripcion) {
        ServiciosMonedas s = new ServiciosMonedas();
        filasAfectadas = 0;
        try {
            filasAfectadas = s.insert(nombre, simbolo, descripcion);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("La marca se añadió correctamente");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        //s.close();
    }

    public void update(int id, String nombre, String simbolo, String descripcion) {
        filasAfectadas = 0;
        ServiciosMonedas s = new ServiciosMonedas();
        try {
            filasAfectadas = s.update(id, nombre, simbolo, descripcion);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("La marca se actualizó correctamente");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        //s.close();
    }

    public ObservableList<Moneda> search(String filtro) {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosMonedas s = new ServiciosMonedas();
        ResultSet rs = s.search(filtro);
        try {
            while (rs.next()) {
                id = rs.getInt(1);
                nombre = rs.getString(2);
                simbolo = rs.getString(3);
                descripcion = rs.getString(4);
                Moneda m = new Moneda(id, nombre, simbolo, descripcion);
                obs.add(m);
                totalFilas++;
            }
        } catch (SQLException ex) {
            Alerta.error("" + ex);
        }
        // s.close();
        return obs;
    }

    public ObservableList<String> getListNombres() {
        nombresList = FXCollections.observableArrayList();
        ServiciosMonedas s = new ServiciosMonedas();
        ResultSet rs = s.getNombres();
        try {
            while (rs.next()) {
                nombre = rs.getString(1);
                nombresList.add(nombre);
                //System.out.println(nombre);
            }

        } catch (SQLException ex) {
            Alerta.error("" + ex);
        }
        //s.close();
        return nombresList;
    }

    private int id;
    private String nombre;
    private String simbolo;
    private String descripcion;
    private ObservableList obs;
    private ObservableList nombresList;
    private int filasAfectadas;
    private int totalFilas;
}
