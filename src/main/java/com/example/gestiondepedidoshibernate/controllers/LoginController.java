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

public class LoginController implements Initializable {

    @FXML
    private Label info;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUser;
    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void login(ActionEvent actionEvent) {
        String user = txtUser.getText();
        String password = txtPassword.getText();
        if (user.length() < 4 || password.length() < 4) {
            info.setText("Introduce los datos");
            info.setStyle("-fx-background-color:red; -fx-text-fill: white;");

        } else {
            User u = (new UserDAOImp()).validateUser(user, password);
            Sesion.setUsuario(u);
            if (u == null) {
                info.setText("Usuario no encontrado");
                info.setStyle("-fx-background-color:red; -fx-text-fill: white;");
            } else {
                info.setText("Usuario " + user + "(" + password + ") correcto");
                info.setStyle("-fx-background-color:green; -fx-text-fill: white;");
                Main.loadMain("ventana-principal.fxml");
            }
        }
    }
}