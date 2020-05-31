/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosTipoProductos;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joelh
 */
public class TipoProducto {

    public TipoProducto() {

    }

    public TipoProducto(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public ObservableList<Marca> getMarcas() {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosTipoProductos s = new ServiciosTipoProductos();
        ResultSet rs = s.all();
        if (rs != null) {
            try {
                while (rs.next()) {
                    id = rs.getInt(1);
                    nombre = rs.getString(2);
                    Marca m = new Marca(id, nombre);
                    obs.add(m);
                    totalFilas++;
                }
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        //s.close();
        return obs;
    }

    public void delete(int id) {
        ServiciosTipoProductos s = new ServiciosTipoProductos();
        filasAfectadas = 0;
        try {
            filasAfectadas = s.delete(id);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("Eliminación Exitosa");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        //s.close();
    }

    public void insert(String nombre) {
        ServiciosTipoProductos s = new ServiciosTipoProductos();
        filasAfectadas = 0;
        try {
            filasAfectadas = s.insert(nombre);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("La marca se añadió correctamente");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        //s.close();
    }

    public void update(int id, String nombre) {
        filasAfectadas = 0;
        ServiciosTipoProductos s = new ServiciosTipoProductos();
        try {
            filasAfectadas = s.update(id, nombre);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("La marca se actualizó correctamente");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        //s.close();
    }

    public ObservableList<Marca> search(String filtro) {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosTipoProductos s = new ServiciosTipoProductos();
        ResultSet rs = s.search(filtro);
        try {
            while (rs.next()) {
                id = rs.getInt(1);
                nombre = rs.getString(2);
                Marca m = new Marca(id, nombre);
                obs.add(m);
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
        ServiciosTipoProductos s = new ServiciosTipoProductos();
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
    private ObservableList obs;
    private ObservableList nombresList;
    private int filasAfectadas;
    private int totalFilas;
}
