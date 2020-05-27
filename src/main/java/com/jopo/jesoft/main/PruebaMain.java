/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.main;

import com.jopo.jesoft.conexion.ServiciosProductos;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author joelh
 */
public class PruebaMain {

    public static void main(String[] args) {

        try {
//            ServiciosProductos s = new ServiciosProductos();
//            String pathname = "C:\\Users\\joelh\\OneDrive\\Imagenes\\pistola.png";
//            File file = new File(pathname);
//            FileInputStream fs = new FileInputStream(file);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );


//            s.insert("1492-j9", "BORNERA", "ABB", "14A", "FERRETERIA", "NSADAD", "DASDAD", "WWWW.EDDD.ED", "und", fs, file, 1, 0.25);
//            public void insert(String codigo, String descripcion, String marca, String rating, String tipo, String misc1, 
//            String misc2, String web, String unidad, FileInputStream imagen, File file, int id_dibujo, double precio) throws Exception {
//            ResultSet rs=s.all();
//            ResultSetMetaData rsmd = rs.getMetaData();
//
//            System.out.println("columns: " + rsmd.getColumnCount());
//            System.out.println("Column Name of 1st column: " + rsmd.getColumnName(2));
//            System.out.println("Column Type Name of 1st column: " + rsmd.getColumnTypeName(2));
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                String codigo = rs.getString(2);
//                String descripcion = rs.getString(3);
//                String marca = rs.getString(4);
//                String rating = rs.getString(5);
//                String tipo = rs.getString(6);
//                String misc1 = rs.getString(7);
//                String misc2 = rs.getString(8);
//                String web = rs.getString(9);
//                String unidad = rs.getString(10);
//                Blob imagenBlob = rs.getBlob(11);
//                int id_dibujo = rs.getInt(12);
//                double precio = rs.getDouble(13);
//                String temp = rs.getString(14);
//                System.out.println("id: " + id + "codigo: " + codigo + " descripcion: " + descripcion + "temporal: " + temp);
//            }
//            s.close();
        } catch (Exception ex) {
            System.out.println("BD ERROR: " + ex);
        }

    }
}
