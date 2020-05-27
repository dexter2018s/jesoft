/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopo.jesoft.controller;

import com.jopo.jesoft.model.Alerta;
import com.jopo.jesoft.model.Marca;
import com.jopo.jesoft.model.Producto;
import com.jopo.jesoft.model.Unidad;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

/**
 *
 * @author joelh
 */
public class ProductosController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtWeb;
    @FXML
    private ComboBox<String> cbMarca;
    @FXML
    private ComboBox<String> cbUnidad;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Producto> tbl;
    @FXML
    private TableColumn colId;
    @FXML
    private ImageView img;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescripcion;
    @FXML
    private TableColumn colMarca;
    @FXML
    private TableColumn colUnidad;
    @FXML
    private TextField txtPrecioCompra;
    @FXML
    private TextField txtPrecioVenta;
    @FXML
    private TableColumn colPrecioCompra;
    @FXML
    private TableColumn colPrecioVenta;
    @FXML
    private WebView webView;

    private ObservableList<Producto> productosList;
    private ObservableList<String> marcasList;
    private ObservableList<String> unidadesList;
    private boolean updateOn = false;
    private boolean addOn = false;
    private int selectedIndex;
    private File imgFile;
    private TextField txtPrecio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Iniciando formulario PRODUCTOS");
        productosList = FXCollections.observableArrayList();
        marcasList = FXCollections.observableArrayList();
        unidadesList = FXCollections.observableArrayList();
        this.colId.setCellValueFactory(new PropertyValueFactory("id"));
        this.colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        this.colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        this.colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        this.colUnidad.setCellValueFactory(new PropertyValueFactory("unidad"));
        this.colPrecioCompra.setCellValueFactory(new PropertyValueFactory("precioCompra"));
        this.colPrecioVenta.setCellValueFactory(new PropertyValueFactory("precioVenta"));

        Producto p = new Producto();
        productosList = p.getProductos();
        tbl.setItems(productosList);
        Marca m = new Marca();
        marcasList = m.getListNombres();
        cbMarca.setItems(marcasList);

        Unidad u = new Unidad();
        unidadesList = u.getListNombres();
        cbUnidad.setItems(unidadesList);
        // cbMarca.getItems() para leer items
        this.disableField();
    }

    public void clearField() {
        txtId.setText("");
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtWeb.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
    }

    public void enableField() {

        txtId.setDisable(false);
        txtCodigo.setDisable(false);
        txtDescripcion.setDisable(false);
        txtWeb.setDisable(false);
        txtPrecioCompra.setDisable(false);
        txtPrecioVenta.setDisable(false);
        btnGuardar.setDisable(false);
        cbMarca.setDisable(false);
        cbUnidad.setDisable(false);
    }

    public void disableField() {
        txtId.setDisable(true);
        txtCodigo.setDisable(true);
        txtDescripcion.setDisable(true);
        txtWeb.setDisable(true);
        txtPrecioCompra.setDisable(true);
        txtPrecioVenta.setDisable(true);
        btnGuardar.setDisable(true);
        cbMarca.setDisable(true);
        cbUnidad.setDisable(true);
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
            int id = tbl.getItems().get(selectedIndex).getId();
            Producto p = new Producto();
            Boolean opcion = Alerta.confirm("¿Deseas eliminar el registro?");
            if (opcion) {
                p.delete(id);
                productosList = p.getProductos(); //obtener toda la lista de registros de la base de datos
                tbl.setItems(productosList);
                tbl.refresh();
            }

        } else {
            //mostrar mensaje de informacion
            Alerta.info("Debe seleccionar una fila antes de eliminar");
        }
    }

    @FXML
    private void clic_btnGuardar(ActionEvent event) {
        if (addOn) {
            Producto p = new Producto();
            String codigo = txtCodigo.getText();
            String descripcion = txtDescripcion.getText();
            String nombreMarca = cbMarca.getValue(); //devuelve el valor seleccionado en el combobox
            String web = txtWeb.getText();
            String unidad = cbUnidad.getValue();
            FileInputStream fs;
            try {
                fs = new FileInputStream(imgFile);
                double precioCompra = Double.parseDouble(txtPrecioCompra.getText());
                double precioVenta = Double.parseDouble(txtPrecioVenta.getText());
                p.insert(codigo, descripcion, nombreMarca, web, unidad, fs, precioCompra, precioVenta);
            } catch (FileNotFoundException ex) {
                Alerta.info("" + ex);
            }

            productosList = p.getProductos(); //obtener toda la lista de registros de la base de datos
            tbl.setItems(productosList);
            tbl.refresh();
            if (p.getFilasAfectadas() > 0) {
                this.disableField();
                addOn = false;
            }
        }

        if (updateOn) {

            selectedIndex = tbl.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Producto p = new Producto();
                int id = Integer.parseInt(txtId.getText());
                String codigo = txtCodigo.getText();
                String descripcion = txtDescripcion.getText();
                String nombreMarca = cbMarca.getValue();
                String web = txtWeb.getText();
                String unidad = cbUnidad.getValue();
                Image image= img.getImage();
                
                FileInputStream fs;
                try {
                    fs = new FileInputStream(imgFile);
                    double precioCompra = Double.parseDouble(txtPrecioCompra.getText());
                    double precioVenta = Double.parseDouble(txtPrecioVenta.getText());
                    p.update(id, codigo, descripcion, nombreMarca, web, unidad, fs, precioCompra, precioVenta);

                } catch (FileNotFoundException ex) {

                }
                productosList = p.getProductos(); //obtener toda la lista de registros de la base de datos
                tbl.setItems(productosList);
                tbl.refresh();
                if (p.getFilasAfectadas() > 0) {
                    this.disableField();
                    updateOn = false;
                }
            } else {
                // Nothing selected.
                System.out.println("No se ha seleccionado nada");
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
    private void clic_cbMarca(ActionEvent event) {
    }

    @FXML
    private void clic_cbUnidad(ActionEvent event) {
    }

    @FXML
    private void clic_btnBuscar(ActionEvent event) {

        String filtro = txtBuscar.getText();
        Producto p = new Producto();
        productosList = p.search(filtro);
        tbl.setItems(productosList);
        tbl.refresh();
        this.disableField();
        addOn = false;
        updateOn = false;
    }

    @FXML
    private void key_txtBuscar(KeyEvent event) {
        String filtro = txtBuscar.getText();
        Producto p = new Producto();
        productosList = p.search(filtro);
        tbl.setItems(productosList);
        tbl.refresh();
        this.disableField();
        addOn = false;
        updateOn = false;
    }

    @FXML
    private void clic_tbl(MouseEvent event) {
        this.disableField();
        Producto p = tbl.getSelectionModel().getSelectedItem(); //retorna el item seleccionado
        if (p == null) {
            System.out.println("No se seleccionó nada");
        } else {
            txtId.setText(p.getId() + "");
            txtCodigo.setText(p.getCodigo());
            txtDescripcion.setText(p.getDescripcion());
            txtWeb.setText(p.getWeb());
            txtPrecioCompra.setText(p.getPrecioCompra() + "");
            txtPrecioVenta.setText(p.getPrecioVenta() + "");
            img.setImage(p.getImagen());
            cbMarca.getSelectionModel().select(p.getMarca());
            cbUnidad.getSelectionModel().select(p.getUnidad());
            WebEngine webEngine = webView.getEngine();
            webEngine.load(p.getWeb());

        }
    }

    @FXML
    private void key_tbl(KeyEvent event) {
        this.disableField();
        Producto p = tbl.getSelectionModel().getSelectedItem(); //retorna el item seleccionado
        if (p == null) {
            System.out.println("No se seleccionó nada");
        } else {
            txtId.setText(p.getId() + "");
            txtCodigo.setText(p.getCodigo());
            txtDescripcion.setText(p.getDescripcion());
            txtWeb.setText(p.getWeb());
            txtPrecioCompra.setText(p.getPrecioCompra() + "");
            txtPrecioVenta.setText(p.getPrecioVenta() + "");
            cbMarca.getSelectionModel().select(p.getMarca());
            cbUnidad.getSelectionModel().select(p.getUnidad());
            img.setImage(p.getImagen());
            WebEngine webEngine = webView.getEngine();
            webEngine.load(p.getWeb());

        }
    }

    @FXML
    private void clic_img(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Obtener la imagen seleccionada
        imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            img.setImage(image);
        }
        System.out.println(imgFile.getPath());
    }

    private void clic_btnImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Obtener la imagen seleccionada
        imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            img.setImage(image);
        }
        System.out.println(imgFile.getPath());
    }

    @FXML
    private void clic_webView(MouseEvent event) {

        this.disableField();
        Producto p = tbl.getSelectionModel().getSelectedItem(); //retorna el item seleccionado
        try {
            if (p == null) {
                System.out.println("No se seleccionó nada");
            } else {

                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(new URI(p.getWeb()));
                    }
                }
            }
        } catch (URISyntaxException | IOException ex) {
            Alerta.info("" + ex);
        }
    }
}
