package com.jopo.jesoft.main;

import com.jopo.jesoft.conexion.Conexion;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            System.out.println("Ingrese al metodo START");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Marcas.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("JavaFX and Maven");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }

    @Override
    public void init() {
        System.out.println("entro al inicio");
        String url = "jdbc:sqlserver://192.168.1.35:1433; databaseName=memeseg";
        String user = "sa";
        String password = "corel2duo";
        Conexion cn = new Conexion(url, user, password);
        cn.conectarBD();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
