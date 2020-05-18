package com.jopo.jesoft.controller;

import com.jopo.jesoft.model.Marca;
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
import javafx.scene.input.MouseEvent;

public class FXMLController implements Initializable {

    //variables creadas por Scene Builder
    @FXML
    private Button btnInitRegister;
    @FXML
    private Button btnPreviewRegister;
    @FXML
    private Button btnNextRegister;
    @FXML
    private Button btnFinishRegister;
    @FXML
    private Button btnAddRegister;
    @FXML
    private Button btnSaveRegister;
    @FXML
    private Button btnDeleteRegister;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtAbrev;
    @FXML
    private TableView<Marca> tblMarcas;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colMarca;
    @FXML
    private TableColumn colAbrev;
    @FXML
    private Button btnSearchRegister;
    @FXML
    private TextField txtSearch;

    //variables propias
    private ObservableList<Marca> marcasList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        marcasList = FXCollections.observableArrayList();
        this.colId.setCellValueFactory(new PropertyValueFactory("id"));
        this.colMarca.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colAbrev.setCellValueFactory(new PropertyValueFactory("abrev"));
        Marca marcas = new Marca();
        marcasList = marcas.getMarcas();
        tblMarcas.setItems(marcasList);
        this.disableField();

    }

    @FXML
    private void clic_btnInitRegister(ActionEvent event) {
    }

    @FXML
    private void clic_btnPreviewRegister(ActionEvent event) {

    }

    @FXML
    private void clic_btnNextRegister(ActionEvent event) {
    }

    @FXML
    private void clic_btnFinishRegister(ActionEvent event) {
    }

    @FXML
    private void clic_btnAddRegister(ActionEvent event) {
        this.clearField();//limpiando campos
        this.enableField();//habilitando campos
    }

    @FXML
    private void clic_btnSaveRegister(ActionEvent event) {

        Marca marca = new Marca();
        marca.insert(txtMarca.getText(), txtAbrev.getText());
        marcasList = marca.getMarcas(); //obtener toda la lista de registros de la base de datos
        tblMarcas.setItems(marcasList);
        this.disableField();
    }

    @FXML
    private void clic_btnDeleteRegister(ActionEvent event) {
        int selectedIndex = tblMarcas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int id = tblMarcas.getItems().get(selectedIndex).getId();
            System.out.println(id);
            Marca marcas = new Marca();
            marcas.delete(id);
            marcasList = marcas.getMarcas(); //obtener toda la lista de registros de la base de datos
            tblMarcas.setItems(marcasList);
        } else {
            // Nothing selected.
            System.out.println("No se ha seleccionado nada");
        }
    }

    @FXML
    private void clic_btnSearchRegister(ActionEvent event) {
//        Servicios servicios = new Servicios();
//        String nombre = txtSearch.getText();
//        try {
//            ResultSet rs = servicios.consulta("SELECT * FROM Marca WHERE Nombre LIKE" + "'%" + nombre + "%'");
//            while (rs.next()) {
//                System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
//                Marca m = new Marca(rs.getString(1), rs.getString(2), rs.getString(3));
//
//            }
//        } catch (Exception ex) {
//            System.out.println("Error" + ex);
//        }
//        tblMarcas.refresh();
    }

    @FXML
    private void clic_tblMarcas(MouseEvent event) {
        this.disableField();
        Marca m = tblMarcas.getSelectionModel().getSelectedItem(); //retorna el item seleccionado
        if (m == null) {
            System.out.println("No se seleccionó nada");
        } else {
            txtId.setText(m.getId() + "");
            txtMarca.setText(m.getNombre());
            txtAbrev.setText(m.getAbrev());
        }
    }

    public void clearField() {
        txtId.setText("");
        txtMarca.setText("");
        txtAbrev.setText("");
    }

    public void enableField() {
        txtMarca.setDisable(false);
        txtAbrev.setDisable(false);
        btnSaveRegister.setDisable(false);
    }

    public void disableField() {
       txtId.setDisable(true);
        txtMarca.setDisable(true);
        txtAbrev.setDisable(true);
        btnSaveRegister.setDisable(true);
    }
}
