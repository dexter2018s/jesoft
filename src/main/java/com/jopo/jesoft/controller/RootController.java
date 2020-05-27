/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author joelh
 */
public class RootController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void clic_btnPresupuestos(ActionEvent event) {
    }

    @FXML
    private void clic_btnProductos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductosOverview.fxml"));
            VBox productosOverview = (VBox) loader.load();
            //ProveedoresController controlador=loader.getController();
            Scene scene = new Scene(productosOverview);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.sizeToScene();
            stage.setTitle("Registro de PRODUCTOS");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clic_btnProyectos(ActionEvent event) {
    }

    @FXML
    private void clic_btnClientes(ActionEvent event) {
    }

    @FXML
    private void clic_btnMarcas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MarcasOverview.fxml"));
            VBox marcasOverview = (VBox) loader.load();
            //ProveedoresController controlador=loader.getController();
            Scene scene = new Scene(marcasOverview);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.sizeToScene();
            stage.setTitle("Registro de MARCAS");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clic_btnProveedores(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProveedoresOverview.fxml"));
            VBox proveedoresOverview = (VBox) loader.load();
            //ProveedoresController controlador=loader.getController();
            Scene scene = new Scene(proveedoresOverview);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro de PROVEEDORES");
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clic_btnCuadrilla(ActionEvent event) {
    }

    @FXML
    private void clic_btnMonedas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MonedasOverview.fxml"));
            VBox monedasOverview = (VBox) loader.load();
            //ProveedoresController controlador=loader.getController();
            Scene scene = new Scene(monedasOverview);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro de MONEDAS");
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void clic_btnUnidades(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UnidadesOverview.fxml"));
            VBox unidadesOverview = (VBox) loader.load();
            //ProveedoresController controlador=loader.getController();
            Scene scene = new Scene(unidadesOverview);
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro de UNIDADES");
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
