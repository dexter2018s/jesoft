/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.model;

import com.jopo.jesoft.conexion.ServiciosProductos;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joelh
 */
public class Producto {

    public Producto() {

    }

    public Producto(String codigo, String descripcion, String web, String imagen, String unidad,
            String marca, String tipoProducto, double precioCompra, double precioVenta, String moneda, int idProducto) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.web = web;
        this.imagen = imagen;
        this.unidad = unidad;
        this.marca = marca;
        this.tipoProducto = tipoProducto;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.moneda = moneda;
        this.idProducto = idProducto;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public void setFilasAfectadas(int filasAfectadas) {
        this.filasAfectadas = filasAfectadas;
    }

    public int getTotalFilas() {
        return totalFilas;
    }

    public void setTotalFilas(int totalFilas) {
        this.totalFilas = totalFilas;
    }

    public ObservableList<Producto> getAll() {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosProductos s = new ServiciosProductos();
        ResultSet rs = s.all();
        if (rs != null) {
            try {
                while (rs.next()) {
                    idProducto = rs.getInt(1);
                    codigo = rs.getString(2);
                    descripcion = rs.getString(3);
                    web = rs.getString(4);
                    imagen = rs.getString(5);
                    unidad = rs.getString(6);
                    marca = rs.getString(7);
                    tipoProducto = rs.getString(8);
                    precioCompra = rs.getDouble(9);
                    precioVenta = rs.getDouble(10);
                    moneda = rs.getString(11);

//                    InputStream is = rs.getBinaryStream(7);
//                    if (is != null) {
//                        file = new File("photo.png"); //crea un archivo .png
//                        OutputStream os = new FileOutputStream(file);
//                        byte[] contents = new byte[1024]; //crea un array de 1024 bytes
//                        int size = 0;//inicializa el tamaño en cero
//                        while ((size = is.read(contents)) != -1) {
//                            os.write(contents, 0, size);
//                        }
//                        imagen = new Image("file:photo.png");
//                    } else {
//                        String path = "/images/producto-sin-imagen.png";
//                        imagen = new Image(path);
//                    }
                    Producto p = new Producto(codigo, descripcion, web, imagen, unidad, marca, tipoProducto, precioCompra, precioVenta, moneda, idProducto);
                    obs.add(p);
                    totalFilas++;
                }
                // s.close();
            } catch (SQLException ex) {
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
        //s.close();
    }

    public void insert(String codigo, String descripcion, String web, String imagen, String unidad,
            String marca, String tipoProducto, double precioCompra, double precioVenta, String moneda) {

        ServiciosProductos s = new ServiciosProductos();
        filasAfectadas = 0;
        try {
            filasAfectadas = s.insert(codigo, descripcion, web, imagen, unidad, marca, tipoProducto, precioCompra, precioVenta, moneda);
//            if (filasAfectadas >= 1) {
//                Alerta.info("La marca se añadió correctamente");
//            }
        } catch (Exception ex) {
            Alerta.info("" + ex);
        }
        //s.close();
    }

    public void update(int idProducto, String codigo, String descripcion, String web, String imagen, String unidad,
            String marca, String tipoProducto, double precioCompra, double precioVenta, String moneda) {

        filasAfectadas = 0;
        ServiciosProductos s = new ServiciosProductos();
        try {
            filasAfectadas = s.update(idProducto, codigo, descripcion, web, imagen, unidad, marca, tipoProducto, precioCompra, precioVenta, moneda);
            if (filasAfectadas >= 1) {
                Alerta.info("La marca se actualizó correctamente");
            }
        } catch (Exception ex) {
            Alerta.info("" + ex);
        }
        //s.close();
    }

    public ObservableList<Producto> search(String filtro) {
        totalFilas = 0;
        obs = FXCollections.observableArrayList();
        ServiciosProductos s = new ServiciosProductos();
        ResultSet rs = s.search(descripcion);
        if (rs != null) {
            try {
                while (rs.next()) {
                    idProducto = rs.getInt(1);
                    codigo = rs.getString(2);
                    descripcion = rs.getString(3);
                    web = rs.getString(4);
                    imagen = rs.getString(5);
                    unidad = rs.getString(6);
                    marca = rs.getString(7);
                    tipoProducto = rs.getString(8);
                    precioCompra = rs.getDouble(9);
                    precioVenta = rs.getDouble(10);
                    moneda = rs.getString(11);

//                    InputStream is = rs.getBinaryStream(7);
//                    if (is != null) {
//                        file = new File("photo.png"); //crea un archivo .png
//                        OutputStream os = new FileOutputStream(file);
//                        byte[] contents = new byte[1024]; //crea un array de 1024 bytes
//                        int size = 0;//inicializa el tamaño en cero
//                        while ((size = is.read(contents)) != -1) {
//                            os.write(contents, 0, size);
//                        }
//                        imagen = new Image("file:photo.png");
//                    } else {
//                        String path = "/images/producto-sin-imagen.png";
//                        imagen = new Image(path);
//                    }
                    Producto p = new Producto(codigo, descripcion, web, imagen, unidad, marca, tipoProducto, precioCompra, precioVenta, moneda, idProducto);
                    obs.add(p);
                    totalFilas++;
                }
                // s.close();
            } catch (SQLException ex) {
                Alerta.error("" + ex);
            }
        }
        return obs;
    }

    private String codigo;
    private String descripcion;
    private String web;
    private String imagen;
    private String unidad;
    private String marca;
    private String tipoProducto;
    private double precioCompra;
    private double precioVenta;
    private String moneda;
    private int idProducto;

//    private FileInputStream fs;
//    private File file;
//    private Image foto;
    private ObservableList obs;
    private int filasAfectadas;
    private int totalFilas;
}
