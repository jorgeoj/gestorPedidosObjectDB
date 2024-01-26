package com.example.gestiondepedidoshibernate;

import com.example.gestiondepedidoshibernate.domain.products.Product;
import com.example.gestiondepedidoshibernate.domain.products.ProductDAOImp;
import com.example.gestiondepedidoshibernate.domain.user.User;
import com.example.gestiondepedidoshibernate.domain.user.UserDAOImp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Clase principal que inicia la aplicación de gestión de pedidos.
 */
public class Main extends Application {

    private static Stage myStage;

    /**
     * Método estático que carga la ventana de inicio de sesión con la ruta especificada.
     *
     * @param ruta La ruta del archivo FXML de la ventana de inicio de sesión.
     */
    public static void loadLogin(String ruta) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(ruta));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            myStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método estático que carga una ventana con la ruta especificada.
     *
     * @param ruta La ruta del archivo FXML de la ventana a cargar.
     */
    public static void loadWindow(String ruta) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(ruta));
            Scene scene = new Scene(fxmlLoader.load(), 850, 600);
            myStage.setResizable(false);
            myStage.setScene(scene);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Método que inicia la aplicación y carga la ventana de inicio de sesión.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si hay un error al cargar la interfaz gráfica.
     */
    @Override
    public void start(Stage stage) throws IOException {

        try{
            ProductDAOImp productoDAO = new ProductDAOImp();
            List<Product> productos=productoDAO.getAll();
            if (productos.isEmpty()){
                productos=Data.generarProductos();
                productoDAO.saveAll(productos);
            }

            UserDAOImp usuarioDAO = new UserDAOImp();
            List<User> usuarios=usuarioDAO.getAll();
            if (usuarios.isEmpty()){
                usuarios=Data.generarUsuarios();
                usuarioDAO.saveAll(usuarios);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ventana-login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Gestión de pedidos");
        stage.setScene(scene);
        stage.show();
    }
}
