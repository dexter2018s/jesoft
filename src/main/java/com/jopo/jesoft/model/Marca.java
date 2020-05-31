/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosMarcas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joelh
 */
public class Marca {

    public Marca() {

    }

    public Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public int getFilasAfectadas() {
        return filasAfectadas;
    }

    public int getTotalFilas() {
        return totalFilas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Marca other = (Marca) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public ObservableList<Marca> getMarcas() {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosMarcas s = new ServiciosMarcas();
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
        ServiciosMarcas s = new ServiciosMarcas();
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
        ServiciosMarcas s = new ServiciosMarcas();
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
        ServiciosMarcas s = new ServiciosMarcas();
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
        ServiciosMarcas s = new ServiciosMarcas();
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
        ServiciosMarcas s = new ServiciosMarcas();
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
    private String abrev;
    private ObservableList obs;
    private ObservableList nombresList;
    private int filasAfectadas;
    private int totalFilas;
}
