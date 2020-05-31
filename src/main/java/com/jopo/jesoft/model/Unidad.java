/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosUnidades;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joelh
 */
public class Unidad {

    public Unidad() {

    }

    public Unidad(String idUnidad, String descripcion) {
        this.idUnidad = idUnidad;
        this.descripcion = descripcion;
    }

    public String getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(String idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ObservableList getObs() {
        return obs;
    }

    public void setObs(ObservableList obs) {
        this.obs = obs;
    }

    public ObservableList getNombresList() {
        return nombresList;
    }

    public void setNombresList(ObservableList nombresList) {
        this.nombresList = nombresList;
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

    public ObservableList<Unidad> getAll() {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosUnidades s = new ServiciosUnidades();
        ResultSet rs = s.all();
        if (rs != null) {
            try {
                while (rs.next()) {
                    idUnidad = rs.getString(1);
                    descripcion = rs.getString(2);
                    Unidad u = new Unidad(idUnidad, descripcion);
                    obs.add(u);
                    totalFilas++;
                }
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        //s.close();
        return obs;
    }

    public void delete(String idUnidad) {
        ServiciosUnidades s = new ServiciosUnidades();
        filasAfectadas = 0;
        try {
            filasAfectadas = s.delete(idUnidad);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("Eliminación Exitosa");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        //s.close();
    }

    public void insert(String idUnidad, String descripcion) {
        ServiciosUnidades s = new ServiciosUnidades();
        filasAfectadas = 0;
        try {
            filasAfectadas = s.insert(idUnidad, descripcion);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("La marca se añadió correctamente");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        //s.close();
    }

    public void update(String idUnidad, String descripcion ) {
        filasAfectadas = 0;
        ServiciosUnidades s = new ServiciosUnidades();
        try {
            filasAfectadas = s.update(idUnidad, descripcion);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("La marca se actualizó correctamente");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        //s.close();
    }

    public ObservableList<Unidad> search(String filtro) {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosUnidades s = new ServiciosUnidades();
        ResultSet rs = s.search(filtro);
        try {
            while (rs.next()) {
                idUnidad = rs.getString(1);
                descripcion = rs.getString(2);
                Unidad u = new Unidad(idUnidad, descripcion);
                obs.add(u);
                totalFilas++;
            }
        } catch (SQLException ex) {
            Alerta.error("" + ex);
        }
        //s.close();
        return obs;
    }

    public ObservableList<String> getListNombres() {
        nombresList = FXCollections.observableArrayList();
        ServiciosUnidades s = new ServiciosUnidades();
        ResultSet rs = s.getNombres();
        try {
            while (rs.next()) {
                idUnidad = rs.getString(1);
                nombresList.add(idUnidad);
                //System.out.println(nombre);
            }

        } catch (SQLException ex) {
            Alerta.error("" + ex);
        }
        //s.close();
        return nombresList;
    }

    private String idUnidad;
    private String descripcion;
    private ObservableList obs;
    private ObservableList nombresList;
    private int filasAfectadas;
    private int totalFilas;
}
