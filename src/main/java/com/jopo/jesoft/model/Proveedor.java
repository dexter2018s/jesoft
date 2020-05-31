/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosProveedores;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joelh
 */
public class Proveedor {

    public Proveedor() {

    }

    public Proveedor(int idProveedor, String razonSocial, String ruc, String direccion, String contacto, String telefono, String anexo, String celular, String correo, String web) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.direccion = direccion;
        this.contacto = contacto;
        this.telefono = telefono;
        this.anexo = anexo;
        this.celular = celular;
        this.correo = correo;
        this.web = web;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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

    public int getTotalFilas() {
        return totalFilas;
    }

    public void setTotalFilas(int totalFilas) {
        this.totalFilas = totalFilas;
    }

    public int getFilasAfectadas() {
        return filasAfectadas;
    }

    public void setFilasAfectadas(int filasAfectadas) {
        this.filasAfectadas = filasAfectadas;
    }

    public ObservableList getObs() {
        return obs;
    }

    public void setObs(ObservableList obs) {
        this.obs = obs;
    }

    public ObservableList<Proveedor> getAll() {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosProveedores s = new ServiciosProveedores();
        ResultSet rs = s.all();
        if (rs != null) {
            try {
                while (rs.next()) {
                    idProveedor = rs.getInt(1);
                    razonSocial = rs.getString(2);
                    ruc = rs.getString(3);
                    direccion = rs.getString(4);
                    contacto = rs.getString(5);
                    telefono = rs.getString(6);
                    anexo = rs.getString(7);
                    celular = rs.getString(8);
                    correo = rs.getString(9);
                    web = rs.getString(10);

                    Proveedor p = new Proveedor(idProveedor, razonSocial, ruc, direccion, contacto, telefono, anexo, celular, correo, web);
                    obs.add(p);
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
        ServiciosProveedores s = new ServiciosProveedores();
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

    public void insert(String razonSocial, String ruc, String direccion, String contacto, String telefono, String anexo, String celular, String correo, String web) {
        ServiciosProveedores s = new ServiciosProveedores();
        filasAfectadas = 0;
        try {
            filasAfectadas = s.insert(razonSocial, ruc, direccion, contacto, telefono, anexo, celular, correo, web);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("El proveedor se añadió correctamente");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        //s.close();
    }

    public void update(int idProveedor, String razonSocial, String ruc, String direccion, String contacto, String telefono, String anexo, String celular, String correo, String web) {
        filasAfectadas = 0;
        ServiciosProveedores s = new ServiciosProveedores();
        try {
            filasAfectadas = s.update(idProveedor, razonSocial, ruc, direccion, contacto, telefono, anexo, celular, correo, web);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("El proveedor se actualizó correctamente");
            }
        } catch (Exception ex) {
            Alerta.error("" + ex);
        }
        //s.close();
    }

    public ObservableList<Proveedor> search(String filtro) {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosProveedores s = new ServiciosProveedores();
        ResultSet rs = s.search(filtro);
        try {
            while (rs.next()) {
                idProveedor = rs.getInt(1);
                razonSocial = rs.getString(2);
                ruc = rs.getString(3);
                direccion = rs.getString(4);
                contacto = rs.getString(5);
                telefono = rs.getString(6);
                anexo = rs.getString(7);
                celular = rs.getString(8);
                correo = rs.getString(9);
                web = rs.getString(10);

                Proveedor p = new Proveedor(idProveedor, razonSocial, ruc, direccion, contacto, telefono, anexo, celular, correo, web);
                obs.add(p);
                totalFilas++;
            }
        } catch (SQLException ex) {
            Alerta.error("" + ex);
        }
        //s.close();
        return obs;
    }

    private int idProveedor;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String contacto;
    private String telefono;
    private String anexo;
    private String celular;
    private String correo;
    private String web;
    private int totalFilas;
    private int filasAfectadas;
    private ObservableList obs;
}
