/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.controller;

import com.jopo.jesoft.model.Alerta;
import com.jopo.jesoft.model.Proveedor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author joelh
 */
public class ProveedoresController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtRazonSocial;
    @FXML
    private TextField txtRuc;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtAnexo;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextField txtContacto;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtWeb;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Proveedor> tbl;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colRazonSocial;
    @FXML
    private TableColumn colRuc;
    @FXML
    private TableColumn colDireccion;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colAnexo;
    @FXML
    private TableColumn colCelular;
    @FXML
    private TableColumn colContacto;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private TableColumn colWeb;

    private ObservableList<Proveedor> proveedoresList;
    private boolean updateOn = false;
    private boolean addOn = false;
    private int selectedIndex;
    @FXML
    private TextField txtTelefono;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        proveedoresList = FXCollections.observableArrayList();
        this.colId.setCellValueFactory(new PropertyValueFactory("idProveedor"));
        this.colRazonSocial.setCellValueFactory(new PropertyValueFactory("razonSocial"));
        this.colRuc.setCellValueFactory(new PropertyValueFactory("ruc"));
        this.colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        this.colContacto.setCellValueFactory(new PropertyValueFactory("contacto"));
        this.colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        this.colAnexo.setCellValueFactory(new PropertyValueFactory("anexo"));
        this.colCelular.setCellValueFactory(new PropertyValueFactory("celular"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        this.colWeb.setCellValueFactory(new PropertyValueFactory("web"));

        Proveedor p = new Proveedor();
        proveedoresList = p.getAll();
        tbl.setItems(proveedoresList);
        this.disableField();
    }

    public void clearField() {
        txtId.setText("");
        txtRazonSocial.setText("");
        txtRuc.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtAnexo.setText("");
        txtCelular.setText("");
        txtContacto.setText("");
        txtCorreo.setText("");
        txtWeb.setText("");

    }

    public void enableField() {

        txtRazonSocial.setDisable(false);
        txtRuc.setDisable(false);
        txtDireccion.setDisable(false);
        txtTelefono.setDisable(false);
        txtAnexo.setDisable(false);
        txtCelular.setDisable(false);
        txtContacto.setDisable(false);
        txtCorreo.setDisable(false);
        txtWeb.setDisable(false);
        btnGuardar.setDisable(false);
    }

    public void disableField() {
        txtId.setDisable(true);
        txtRazonSocial.setDisable(true);
        txtRuc.setDisable(true);
        txtDireccion.setDisable(true);
        txtTelefono.setDisable(true);
        txtAnexo.setDisable(true);
        txtCelular.setDisable(true);
        txtContacto.setDisable(true);
        txtCorreo.setDisable(true);
        txtWeb.setDisable(true);
        btnGuardar.setDisable(true);
    }

    @FXML
    private void clic_btnPrimer(ActionEvent event) {
    }

    @FXML
    private void clic_btnAnterior(ActionEvent event) {
    }

    @FXML
    private void clic_btnSiguiente(ActionEvent event) {
    }

    @FXML
    private void clic_btnUltimo(ActionEvent event) {
    }

    @FXML
    private void clic_btnAgregar(ActionEvent event) {
        this.clearField();//limpiando campos
        this.enableField();//habilitando campos
        addOn = true;
        updateOn = false;
    }

    @FXML
    private void clic_btnEliminar(ActionEvent event) {
        selectedIndex = tbl.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int id = tbl.getItems().get(selectedIndex).getIdProveedor();
            Proveedor p = new Proveedor();
            Boolean opcion = Alerta.confirm("¿Deseas eliminar el registro?");
            if (opcion) {
                p.delete(id);
                proveedoresList = p.getAll(); //obtener toda la lista de registros de la base de datos
                tbl.setItems(proveedoresList);
                tbl.refresh();
            }
        } else {
            Alerta.info("Debe seleccionar una fila antes de eliminar");
        }
        addOn = false;
        updateOn = false;
    }

    @FXML
    private void clic_btnGuardar(ActionEvent event) {
        if (addOn) {
            Proveedor p = new Proveedor();
            p.insert(txtRazonSocial.getText(), txtRuc.getText(), txtDireccion.getText(), txtContacto.getText(),
                    txtTelefono.getText(), txtAnexo.getText(), txtCelular.getText(), txtCorreo.getText(), txtWeb.getText());
            proveedoresList = p.getAll(); //obtener toda la lista de registros de la base de datos
            tbl.setItems(proveedoresList);
            tbl.refresh();
            if (p.getFilasAfectadas() > 0) {
                this.disableField();
                addOn = false;
            }
        }

        if (updateOn) {

            selectedIndex = tbl.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Proveedor p = new Proveedor();
                int id = tbl.getItems().get(selectedIndex).getIdProveedor();
                p.update(id, txtRazonSocial.getText(), txtRuc.getText(), txtDireccion.getText(), txtContacto.getText(),
                        txtTelefono.getText(), txtAnexo.getText(), txtCelular.getText(), txtCorreo.getText(), txtWeb.getText());
                proveedoresList = p.getAll(); //obtener toda la lista de registros de la base de datos
                tbl.setItems(proveedoresList);
                tbl.refresh();
                if (p.getFilasAfectadas() > 0) {
                    this.disableField();
                    updateOn = false;
                }
            } else {
                // Nothing selected.
                Alerta.info("Debe seleccionar la fila que desea actualizar");
                this.disableField();
                updateOn = false;
            }
        }
    }

    @FXML
    private void clic_btnEditar(ActionEvent event) {
        selectedIndex = tbl.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            this.enableField();//habilitando campos
            updateOn = true;
            addOn = false;
        } else {
            Alerta.info("Debe seleccionar la fila que desea actualizar");
        }
    }

    @FXML
    private void key_txtDescripcion(KeyEvent event) {
    }

    @FXML
    private void clic_btnBuscar(ActionEvent event) {
        String filtro = txtBuscar.getText();
        Proveedor p = new Proveedor();
        proveedoresList = p.search(filtro);
        tbl.setItems(proveedoresList);
        tbl.refresh();
        this.disableField();
        addOn = false;
        updateOn = false;
    }

    @FXML
    private void key_txtBuscar(KeyEvent event) {
        String filtro = txtBuscar.getText();
        Proveedor p = new Proveedor();
        proveedoresList = p.search(filtro);
        tbl.setItems(proveedoresList);
        tbl.refresh();
        this.disableField();
        addOn = false;
        updateOn = false;
    }

    @FXML
    private void clic_tbl(MouseEvent event) {
        this.disableField();
        Proveedor p = tbl.getSelectionModel().getSelectedItem(); //retorna el item seleccionado
        if (p == null) {
            System.out.println("No se seleccionó nada");
        } else {
            txtId.setText(p.getIdProveedor() + "");
            txtRazonSocial.setText(p.getRazonSocial());
            txtRuc.setText(p.getRuc());
            txtDireccion.setText(p.getDireccion());
            txtContacto.setText(p.getContacto());
            txtTelefono.setText(p.getTelefono());
            txtAnexo.setText(p.getAnexo());
            txtCelular.setText(p.getCelular());
            txtCorreo.setText(p.getCorreo());
            txtWeb.setText(p.getWeb());
        }
        addOn = false;
        updateOn = false;
    }

    @FXML
    private void key_tbl(KeyEvent event) {
        this.disableField();
        Proveedor p = tbl.getSelectionModel().getSelectedItem(); //retorna el item seleccionado
        if (p == null) {
            System.out.println("No se seleccionó nada");
        } else {
            txtId.setText(p.getIdProveedor() + "");
            txtRazonSocial.setText(p.getRazonSocial());
            txtRuc.setText(p.getRuc());
            txtDireccion.setText(p.getDireccion());
            txtContacto.setText(p.getContacto());
            txtTelefono.setText(p.getTelefono());
            txtAnexo.setText(p.getAnexo());
            txtCelular.setText(p.getCelular());
            txtCorreo.setText(p.getCorreo());
            txtWeb.setText(p.getWeb());
        }
        addOn = false;
        updateOn = false;
    }
}
