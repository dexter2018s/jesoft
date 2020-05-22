/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosProveedores;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joelh
 */
public class Proveedor {

    public Proveedor() {

    }

    public Proveedor(int id, String razonSocial, long ruc, String direccion, String telefono, int anexo, String celular, String contacto, String correo, String web) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.direccion = direccion;
        this.telefono = telefono;
        this.anexo = anexo;
        this.celular = celular;
        this.contacto = contacto;
        this.correo = correo;
        this.web = web;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getRuc() {
        return ruc;
    }

    public void setRuc(long ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getAnexo() {
        return anexo;
    }

    public void setAnexo(int anexo) {
        this.anexo = anexo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public ObservableList getObs() {
        return obs;
    }

    public void setObs(ObservableList obs) {
        this.obs = obs;
    }

    public ObservableList<Proveedor> getProveedor() {
        obs = FXCollections.observableArrayList();
        ServiciosProveedores s = new ServiciosProveedores();
        try {
            ResultSet rs = s.all();
            while (rs.next()) {
                id = rs.getInt(1);
                razonSocial = rs.getString(2);
                ruc = rs.getLong(3);
                direccion = rs.getString(4);
                telefono = rs.getString(5);
                anexo = rs.getInt(6);
                celular = rs.getString(7);
                contacto = rs.getString(8);
                correo = rs.getString(9);
                web = rs.getString(10);

                Proveedor p = new Proveedor(id, razonSocial, ruc, direccion, telefono, anexo, celular, contacto, correo, web);
                obs.add(p);
            }
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - " + ex);
        }
        return obs;
    }

    public void delete(int id) {
        ServiciosProveedores s = new ServiciosProveedores();
        try {
            s.delete(id);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - Eliminacion fallida:" + ex);
        }
    }

    public void insert(String razonSocial, long ruc, String direccion, String telefono, int anexo, String celular, String contacto, String correo, String web) {
        ServiciosProveedores s = new ServiciosProveedores();
        try {
            s.insert(razonSocial, ruc, direccion, telefono, anexo, celular, contacto, correo, web);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - Insercion fallida:" + ex);
        }
    }

    public void update(int id, String razonSocial, long ruc, String direccion, String telefono, int anexo, String celular, String contacto, String correo, String web) {
        ServiciosProveedores s = new ServiciosProveedores();
        try {
            s.update(id, razonSocial, ruc, direccion, telefono, anexo, celular, contacto, correo, web);//ejecutando delete mediante id
            //System.out.println("BD-Eliminacion exitosa");
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - actualizacion fallida:" + ex);
        }
    }

    public ObservableList<Proveedor> search(String filtro) {
        obs = FXCollections.observableArrayList();
        ServiciosProveedores s = new ServiciosProveedores();
        try {
            ResultSet rs = s.search(filtro);
            while (rs.next()) {
                id = rs.getInt(1);
                razonSocial = rs.getString(2);
                ruc = rs.getLong(3);
                direccion = rs.getString(4);
                telefono = rs.getString(5);
                anexo = rs.getInt(6);
                celular = rs.getString(7);
                contacto = rs.getString(8);
                correo = rs.getString(9);
                web = rs.getString(10);
                Proveedor p = new Proveedor(id, razonSocial, ruc, direccion, telefono, anexo, celular, contacto, correo, web);
                obs.add(p);
            }
            s.close();
        } catch (Exception ex) {
            System.out.println("BD - " + ex);
        }
        return obs;
    }

    private int id;
    private String razonSocial;
    private long ruc;
    private String direccion;
    private String telefono;
    private int anexo;
    private String celular;
    private String contacto;
    private String correo;
    private String web;
    private ObservableList obs;
}
