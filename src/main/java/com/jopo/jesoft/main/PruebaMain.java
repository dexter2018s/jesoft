/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.main;

import com.jopo.jesoft.conexion.Servicio;
import com.jopo.jesoft.conexion.ServiciosProductos;
import com.jopo.jesoft.model.Crawler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void main(String[] args) throws IOException {

        try {
            Crawler aw = new Crawler();

            String url = "https://se.com/pe/es/product/LC1D12B7";
            
            aw.listTextSchneider(url);
  

//            Servicio.getConnection();
//            String pathname = "C:\\Users\\joelh\\OneDrive\\Imagenes\\pistola.png";
//            File file = new File(pathname);
//            FileInputStream fs = new FileInputStream(file);
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Buscar Imagen");
//
//            // Agregar filtros para facilitar la busqueda
//            fileChooser.getExtensionFilters().addAll(
//                    new FileChooser.ExtensionFilter("All Images", "*.*"),
//                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
//                    new FileChooser.ExtensionFilter("PNG", "*.png")
//            );
//            s.close();
        } catch (IOException ex) {
            System.out.println("BD ERROR: " + ex);
        }

    }
}
