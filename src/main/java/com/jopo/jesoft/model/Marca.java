/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosMarcas;
import java.sql.ResultSet;
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

    public Marca(int id, String nombre, String abrev) {
        this.id = id;
        this.nombre = nombre;
        this.abrev = abrev;
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

    public String getAbrev() {
        return abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
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
        obs = FXCollections.observableArrayList();
        ServiciosMarcas s = new ServiciosMarcas();
        try {
            ResultSet rs = s.all();
            while (rs.next()) {
                id = rs.getInt(1);
                nombre = rs.getString(2);
                abrev = rs.getString(3);
                Marca m = new Marca(id, nombre, abrev);
                obs.add(m);
            }
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - " + ex);
        }
        return obs;
    }

    public void delete(int id) {
        ServiciosMarcas s = new ServiciosMarcas();
        try {
            s.delete(id);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - Eliminacion fallida:" + ex);
        }
    }

    public void insert(String nombre, String abrev) {
        ServiciosMarcas s = new ServiciosMarcas();
        try {
            s.insert(nombre, abrev);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - Insercion fallida:" + ex);
        }
    }

    public void update(int id, String nombre, String abrev) {
        ServiciosMarcas s = new ServiciosMarcas();
        try {
            System.out.println("Id: " + id + " Nombre: " + nombre + " abrev: " + abrev);
            s.update(id, nombre, abrev);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - actualizacion fallida:" + ex);
        }
    }

    public ObservableList<Marca> search(String filtro) {
        obs = FXCollections.observableArrayList();
        ServiciosMarcas s = new ServiciosMarcas();
        try {
            ResultSet rs = s.search(filtro);
            while (rs.next()) {
                id = rs.getInt(1);
                nombre = rs.getString(2);
                abrev = rs.getString(3);
                Marca m = new Marca(id, nombre, abrev);
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
    private String abrev;
    private ObservableList obs;
}
