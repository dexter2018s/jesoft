package com.jopo.jesoft.controller;

import com.jopo.jesoft.model.Alerta;
import com.jopo.jesoft.model.Moneda;
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

public class MonedasController implements Initializable {

    //variables propias
    private ObservableList<Moneda> monedasList;
    private boolean updateOn = false;
    private boolean addOn = false;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtSimbolo;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Moneda> tblMonedas;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colSimbolo;
    @FXML
    private TableColumn colDescripcion;

    private int selectedIndex;
    @FXML
    private Button btnGuardar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        monedasList = FXCollections.observableArrayList();
        this.colId.setCellValueFactory(new PropertyValueFactory("id"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colSimbolo.setCellValueFactory(new PropertyValueFactory("simbolo"));
        this.colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        Moneda moneda = new Moneda();
        monedasList = moneda.getMonedas();
        tblMonedas.setItems(monedasList);
        this.disableField();
    }

    public void clearField() {
        txtId.setText("");
        txtNombre.setText("");
        txtSimbolo.setText("");
        txtDescripcion.setText("");

    }

    public void enableField() {
        txtNombre.setDisable(false);
        txtSimbolo.setDisable(false);
        txtDescripcion.setDisable(false);
        btnGuardar.setDisable(false);
    }

    public void disableField() {
        txtId.setDisable(true);
        txtNombre.setDisable(true);
        txtSimbolo.setDisable(true);
        txtDescripcion.setDisable(true);
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
        selectedIndex = tblMonedas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int id = tblMonedas.getItems().get(selectedIndex).getId();
            Moneda m = new Moneda();
            Boolean opcion = Alerta.confirm("¿Deseas eliminar el registro?");
            if (opcion) {
                m.delete(id);
                monedasList = m.getMonedas(); //obtener toda la lista de registros de la base de datos
                tblMonedas.setItems(monedasList);
                tblMonedas.refresh();
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
            Moneda m = new Moneda();
            m.insert(txtNombre.getText(), txtSimbolo.getText(), txtDescripcion.getText());
            monedasList = m.getMonedas(); //obtener toda la lista de registros de la base de datos
            tblMonedas.setItems(monedasList);
            tblMonedas.refresh();
            if (m.getFilasAfectadas() > 0) {
                this.disableField();
                addOn = false;
            }
        }

        if (updateOn) {
            selectedIndex = tblMonedas.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Moneda m = new Moneda();
                int id = tblMonedas.getItems().get(selectedIndex).getId();
                m.update(id, txtNombre.getText(), txtSimbolo.getText(), txtDescripcion.getText());
                monedasList = m.getMonedas(); //obtener toda la lista de registros de la base de datos
                tblMonedas.setItems(monedasList);
                tblMonedas.refresh();
                if (m.getFilasAfectadas() > 0) {
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
        selectedIndex = tblMonedas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            this.enableField();//habilitando campos
            updateOn = true;
            addOn = false;
        } else {
            Alerta.info("Debe seleccionar la fila que desea actualizar");
        }
    }

    @FXML
    private void clic_btnBuscar(ActionEvent event) {
        String filtro = txtBuscar.getText();
        Moneda moneda = new Moneda();
        monedasList = moneda.search(filtro);
        tblMonedas.setItems(monedasList);
        tblMonedas.refresh();
        this.disableField();
        addOn = false;
        updateOn = false;
    }

    @FXML
    private void clic_tblMonedas(MouseEvent event) {
        this.disableField();
        Moneda m = tblMonedas.getSelectionModel().getSelectedItem(); //retorna el item seleccionado
        if (m == null) {
            System.out.println("No se seleccionó nada");
        } else {
            txtId.setText(m.getId() + "");
            txtNombre.setText(m.getNombre());
            txtSimbolo.setText(m.getSimbolo());
            txtDescripcion.setText(m.getDescripcion());
        }
        addOn = false;
        updateOn = false;
    }

    @FXML
    private void key_txtDescripcion(KeyEvent event) {
        //pendiente
    }

    @FXML
    private void key_tblMonedas(KeyEvent event) {
        this.disableField();
        Moneda m = tblMonedas.getSelectionModel().getSelectedItem(); //retorna el item seleccionado
        if (m == null) {
            System.out.println("No se seleccionó nada");
        } else {
            txtId.setText(m.getId() + "");
            txtNombre.setText(m.getNombre());
            txtSimbolo.setText(m.getSimbolo());
            txtDescripcion.setText(m.getDescripcion());
        }
        addOn = false;
        updateOn = false;
    }

    @FXML
    private void key_txtBuscar(KeyEvent event) {
        String filtro = txtBuscar.getText();
        Moneda moneda = new Moneda();
        monedasList = moneda.search(filtro);
        tblMonedas.setItems(monedasList);
        tblMonedas.refresh();
        this.disableField();
    }

}
