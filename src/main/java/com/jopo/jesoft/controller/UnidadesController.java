package com.jopo.jesoft.controller;

import com.jopo.jesoft.model.Unidad;
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

public class UnidadesController implements Initializable {

    //variables propias
    private ObservableList<Unidad> unidadesList;
    private boolean updateOn = false;
    private boolean addOn = false;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Unidad> tblUnidades;
    @FXML
    private TableColumn colDescripcion;

    private int selectedIndex;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtAbreviatura;
    @FXML
    private TableColumn colAbreviatura;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        unidadesList = FXCollections.observableArrayList();
        this.colAbreviatura.setCellValueFactory(new PropertyValueFactory("abreviatura"));
        this.colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        Unidad u = new Unidad();
        unidadesList = u.getUnidades();
        tblUnidades.setItems(unidadesList);
        this.disableField();
    }

    public void clearField() {
        txtAbreviatura.setText("");
        txtDescripcion.setText("");

    }

    public void enableField() {
        txtAbreviatura.setDisable(false);
        txtDescripcion.setDisable(false);
        btnGuardar.setDisable(false);
    }

    public void disableField() {
        txtAbreviatura.setDisable(true);
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
    }

    @FXML
    private void clic_btnEliminar(ActionEvent event) {
        selectedIndex = tblUnidades.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String abreviatura = tblUnidades.getItems().get(selectedIndex).getAbreviatura();
            System.out.println(abreviatura);
            Unidad u = new Unidad();
            u.delete(abreviatura);
            unidadesList = u.getUnidades(); //obtener toda la lista de registros de la base de datos
            tblUnidades.setItems(unidadesList);
            tblUnidades.refresh();
        } else {
            // Nothing selected.
            System.out.println("No se ha seleccionado nada");
        }
    }

    @FXML
    private void clic_btnGuardar(ActionEvent event) {
        if (addOn) {
            Unidad u = new Unidad();
            u.insert(txtAbreviatura.getText(), txtDescripcion.getText());
            unidadesList = u.getUnidades(); //obtener toda la lista de registros de la base de datos
            tblUnidades.setItems(unidadesList);
            tblUnidades.refresh();
            this.disableField();
            addOn = false;
        }

        if (updateOn) {

            selectedIndex = tblUnidades.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Unidad u = new Unidad();
                String abreviatura = tblUnidades.getItems().get(selectedIndex).getAbreviatura();
                System.out.println("La abreviatura es: " + abreviatura);
                u.update(txtAbreviatura.getText(), txtDescripcion.getText());
                unidadesList = u.getUnidades(); //obtener toda la lista de registros de la base de datos
                tblUnidades.setItems(unidadesList);
                tblUnidades.refresh();
                this.disableField();
                updateOn = false;
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
        txtDescripcion.setDisable(false);
        updateOn = true;
    }

    @FXML
    private void clic_btnBuscar(ActionEvent event) {
        String filtro = txtBuscar.getText();
        Unidad u = new Unidad();
        unidadesList = u.search(filtro);
        tblUnidades.setItems(unidadesList);
        tblUnidades.refresh();
        this.disableField();
    }

    @FXML
    private void clic_tblUnidades(MouseEvent event) {
        this.disableField();
        Unidad u = tblUnidades.getSelectionModel().getSelectedItem(); //retorna el item seleccionado
        if (u == null) {
            System.out.println("No se seleccionó nada");
        } else {
            txtAbreviatura.setText(u.getAbreviatura());
            txtDescripcion.setText(u.getDescripcion());
        }
    }

    @FXML
    private void key_tblUnidades(KeyEvent event) {
        System.out.println("Escribi algo :)");
        this.disableField();
        Unidad m = tblUnidades.getSelectionModel().getSelectedItem(); //retorna el item seleccionado
        if (m == null) {
            System.out.println("No se seleccionó nada");
        } else {
            txtAbreviatura.setText(m.getAbreviatura() + "");
            txtDescripcion.setText(m.getDescripcion());
        }
    }

    @FXML
    private void key_txtBuscar(KeyEvent event) {
        String filtro = txtBuscar.getText();
        Unidad u = new Unidad();
        unidadesList = u.search(filtro);
        tblUnidades.setItems(unidadesList);
        tblUnidades.refresh();
        this.disableField();
    }

}
