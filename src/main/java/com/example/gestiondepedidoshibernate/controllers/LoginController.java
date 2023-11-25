package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.Main;
import com.example.gestiondepedidoshibernate.Sesion;
import com.example.gestiondepedidoshibernate.domain.user.User;
import com.example.gestiondepedidoshibernate.domain.user.UserDAOImp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana de inicio de sesión.
 */
public class LoginController implements Initializable {

    @FXML
    private Label info;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUser;
    @FXML
    private Button btnLogin;

    /**
     * Inicializa la ventana de inicio de sesión.
     *
     * @param url            La URL de inicialización.
     * @param resourceBundle El ResourceBundle utilizado.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    /**
     * Maneja el evento de inicio de sesión.
     *
     * @param actionEvent El evento que desencadena la acción.
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        if (user.length() < 4 || pass.length() < 4) {
            info.setText("Introduce los datos");
            info.setStyle("-fx-text-fill: red;");

        } else {
            // ACCESO A BASE DE DATOS PARA LA VALIDACION
            User u = (new UserDAOImp()).validateUser(user, pass);

            if (u == null) {
                info.setText("Error, datos incorrectos");
                info.setStyle("-fx-text-fill: red;");
            } else {
                Sesion.setCurrentUser(u);

                // Guardar usuario en sesión e ir a la próxima ventana
                Main.loadWindow("ventana-principal.fxml");
            }
        }
    }
}