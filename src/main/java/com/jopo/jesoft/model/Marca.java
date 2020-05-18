/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.Servicios;
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
        Servicios s = new Servicios();
        try {
            ResultSet rs = s.allRegister("Marcas");
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
        Servicios s = new Servicios();
        try {
            s.deleteRegister("Marcas", id);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - Eliminacion fallida:" + ex);
        }
    }

    public void insert(String nombre,String abrev) {
        Servicios s = new Servicios();
        try {
            s.insertRegister("Marcas",nombre,abrev);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - Insercion fallida:" + ex);
        }
    }

    private int id;
    private String nombre;
    private String abrev;
    private ObservableList obs;
}
