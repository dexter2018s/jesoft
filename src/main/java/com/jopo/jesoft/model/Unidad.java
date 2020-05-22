/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosMonedas;
import com.jopo.jesoft.conexion.ServiciosUnidades;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joelh
 */
public class Unidad {

    public Unidad() {

    }

    public Unidad(String abreviatura, String descripcion) {
        this.abreviatura = abreviatura;
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ObservableList<Unidad> getUnidades() {
        obs = FXCollections.observableArrayList();
        ServiciosUnidades s = new ServiciosUnidades();
        try {
            ResultSet rs = s.all();
            while (rs.next()) {
                abreviatura = rs.getString(1);
                descripcion = rs.getString(2);
                Unidad u = new Unidad(abreviatura, descripcion);
                obs.add(u);
            }
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - " + ex);
        }
        return obs;
    }

    public void delete(String abreviatura) {
        ServiciosUnidades s = new ServiciosUnidades();
        try {
            s.delete(abreviatura);//ejecutando delete mediante abreviatura
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - Eliminacion fallida:" + ex);
        }
    }

    public void insert(String abreviatura, String descripcion) {
        ServiciosUnidades s = new ServiciosUnidades();
        try {
            s.insert(abreviatura, descripcion);//ejecutando delete mediante abreviatura
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - Insercion fallida:" + ex);
        }
    }

    public void update(String abreviatura, String descripcion) {
        ServiciosUnidades s = new ServiciosUnidades();
        try {
            s.update(abreviatura, descripcion);//ejecutando delete mediante abreviatura
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - actualizacion fallida:" + ex);
        }
    }

    public ObservableList<Unidad> search(String filtro) {
        obs = FXCollections.observableArrayList();
        ServiciosUnidades s = new ServiciosUnidades();
        try {
            ResultSet rs = s.search(filtro);
            while (rs.next()) {
                abreviatura = rs.getString(1);
                
                descripcion = rs.getString(2);
                Unidad u = new Unidad(abreviatura, descripcion);
                obs.add(u);
            }
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - " + ex);
        }
        return obs;
    }

    private String abreviatura;
    private String descripcion;
    private ObservableList obs;
}
