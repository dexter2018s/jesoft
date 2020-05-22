/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosMonedas;
import java.sql.ResultSet;
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

    public ObservableList<Moneda> getMonedas() {
        obs = FXCollections.observableArrayList();
        ServiciosMonedas s = new ServiciosMonedas();
        try {
            ResultSet rs = s.all();
            while (rs.next()) {
                id = rs.getInt(1);
                nombre = rs.getString(2);
                simbolo = rs.getString(3);
                descripcion = rs.getString(4);
                Moneda m = new Moneda(id, nombre, simbolo, descripcion);
                obs.add(m);
            }
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - " + ex);
        }
        return obs;
    }

    public void delete(int id) {
        ServiciosMonedas s = new ServiciosMonedas();
        try {
            s.delete(id);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - Eliminacion fallida:" + ex);
        }
    }

    public void insert(String nombre, String simbolo, String descripcion) {
        ServiciosMonedas s = new ServiciosMonedas();
        try {
            s.insert(nombre, simbolo, descripcion);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - Insercion fallida:" + ex);
        }
    }

    public void update(int id, String nombre, String simbolo, String descripcion) {
        ServiciosMonedas s = new ServiciosMonedas();
        try {
            s.update(id, nombre, simbolo, descripcion);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - actualizacion fallida:" + ex);
        }
    }

    public ObservableList<Moneda> search(String filtro) {
        obs = FXCollections.observableArrayList();
        ServiciosMonedas s = new ServiciosMonedas();
        try {
            ResultSet rs = s.search(filtro);
            while (rs.next()) {
                id = rs.getInt(1);
                nombre = rs.getString(2);
                simbolo = rs.getString(3);
                descripcion = rs.getString(4);
                Moneda m = new Moneda(id, nombre, simbolo, descripcion);
                obs.add(m);
            }
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - " + ex);
        }
        return obs;
    }

    private int id;
    private String nombre;
    private String simbolo;
    private String descripcion;
    private ObservableList obs;
}
