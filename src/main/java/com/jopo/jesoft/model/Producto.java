/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosMarcas;
import com.jopo.jesoft.conexion.ServiciosProductos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author joelh
 */
public class Producto {

    public Producto() {

    }

    public Producto(int id, String codigo, String descripcion, String marca, String web, String unidad, Image imagen, double precioCompra, double precioVenta) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.marca = marca;
        this.web = web;
        this.unidad = unidad;
        this.imagen = imagen;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public FileInputStream getFs() {
        return fs;
    }

    public void setFs(FileInputStream fs) {
        this.fs = fs;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public ObservableList getObs() {
        return obs;
    }

    public void setObs(ObservableList obs) {
        this.obs = obs;
    }

    public int getFilasAfectadas() {
        return filasAfectadas;
    }

    public int getTotalFilas() {
        return totalFilas;
    }

    public ObservableList<Producto> getProductos() {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosProductos s = new ServiciosProductos();
        ResultSet rs = s.all();
        if (rs != null) {
            try {
                while (rs.next()) {
                    id = rs.getInt(1);
                    codigo = rs.getString(2);
                    descripcion = rs.getString(3);
                    marca = rs.getString(4);
                    web = rs.getString(5);
                    unidad = rs.getString(6);
                    InputStream is = rs.getBinaryStream(7);
                    if (is != null) {
                        file = new File("photo.png"); //crea un archivo .png
                        OutputStream os = new FileOutputStream(file);
                        byte[] contents = new byte[1024]; //crea un array de 1024 bytes
                        int size = 0;//inicializa el tamaño en cero
                        while ((size = is.read(contents)) != -1) {
                            os.write(contents, 0, size);
                        }
                        imagen = new Image("file:photo.png");
                    } else {
                        String path = "/images/producto-sin-imagen.png";
                        imagen = new Image(path);
                    }
                    precioCompra = rs.getDouble(8);
                    precioVenta = rs.getDouble(9);
                    Producto p = new Producto(id, codigo, descripcion, marca, web, unidad, imagen, precioCompra, precioVenta);
                    obs.add(p);
                    totalFilas++;
                }
                s.close();
            } catch (IOException | SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return obs;
    }

    public void delete(int id) {
        ServiciosProductos s = new ServiciosProductos();
        filasAfectadas = 0;
        try {
            filasAfectadas = s.delete(id);//ejecutando delete mediante id
            if (filasAfectadas >= 1) {
                Alerta.info("Eliminación Exitosa");
            }
        } catch (Exception ex) {
            Alerta.info("Eliminación fallida");
        }
        s.close();
    }

    public void insert(String codigo, String descripcion, String marca, String web, String unidad, FileInputStream imagen, double precioCompra, double precioVenta) {

        ServiciosProductos s = new ServiciosProductos();
        filasAfectadas = 0;
        try {
            filasAfectadas = s.insert(codigo, descripcion, marca, web, unidad, imagen, precioCompra, precioVenta);
            if (filasAfectadas >= 1) {
                Alerta.info("La marca se añadió correctamente");
            }
        } catch (Exception ex) {
            Alerta.info("" + ex);
        }
        s.close();
    }

    public void update(int id, String codigo, String descripcion, String marca, String web, String unidad, FileInputStream imagen, double precioCompra, double precioVenta) {

        filasAfectadas = 0;
        ServiciosProductos s = new ServiciosProductos();
        try {
            filasAfectadas = s.update(id, codigo, descripcion, marca, web, unidad, imagen, precioCompra, precioVenta);
            if (filasAfectadas >= 1) {
                Alerta.info("La marca se actualizó correctamente");
            }
        } catch (Exception ex) {
            Alerta.info("" + ex);
        }
        s.close();
    }

    public ObservableList<Producto> search(String filtro) {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosProductos s = new ServiciosProductos();
        ResultSet rs = s.search(filtro);
        try {
            while (rs.next()) {
                id = rs.getInt(1);
                codigo = rs.getString(2);
                descripcion = rs.getString(3);
                marca = rs.getString(4);
                web = rs.getString(5);
                unidad = rs.getString(6);
                InputStream is = rs.getBinaryStream(7);
                if (is != null) {
                    file = new File("photo.png"); //crea un archivo .png
                    OutputStream os = new FileOutputStream(file);
                    byte[] contents = new byte[1024]; //crea un array de 1024 bytes
                    int size = 0;//inicializa el tamaño en cero
                    while ((size = is.read(contents)) != -1) {
                        os.write(contents, 0, size);
                    }
                    imagen = new Image("file:photo.png");
                } else {
                    String path = "/images/producto-sin-imagen.png";
                    imagen = new Image(path);
                }
                precioCompra = rs.getDouble(8);
                precioVenta = rs.getDouble(9);
                Producto p = new Producto(id, codigo, descripcion, marca, web, unidad, imagen, precioCompra, precioVenta);
                obs.add(p);
                totalFilas++;
            }
            s.close();
        } catch (IOException | SQLException ex) {
            Alerta.info("" + ex);
        }
        return obs;
    }

    private int id;
    private String codigo;
    private String descripcion;
    private String marca;
    private String web;
    private String unidad;
    private FileInputStream fs;
    private File file;
    private double precioCompra;
    private double precioVenta;
    private Image imagen;
    private Image foto;
    private ObservableList obs;
    private int filasAfectadas;
    private int totalFilas;
}
