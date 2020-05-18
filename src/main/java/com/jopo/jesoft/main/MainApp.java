package com.jopo.jesoft.main;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage; //stage primario
    private BorderPane rootLayout; //aun no se para que se usa

    @Override
    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Jesoft APP - Sistema MEMESEG S.A.C.");
        initRootLayout();
        showMarcasOverview();
    }

    public void initRootLayout() {

        try {
            System.out.println("Iniciando RootLayout");
            //cargando el root layout desde el archivo fxml

            rootLayout = FXMLLoader.load(getClass().getResource("/fxml/RootLayout.fxml"));
            //mostrando la escena contenida en el root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMarcasOverview() {

        try {
            System.out.println("Iniciando MarcasOverview");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/MarcasOverview.fxml"));
            VBox marcasOverview = (VBox) loader.load();
            rootLayout.setCenter(marcasOverview); //colocar en el centro del root layout

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
