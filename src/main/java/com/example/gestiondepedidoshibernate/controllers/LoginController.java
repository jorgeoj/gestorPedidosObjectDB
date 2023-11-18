package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.Main;
import com.example.gestiondepedidoshibernate.Session;
import com.example.gestiondepedidoshibernate.domain.user.User;
import com.example.gestiondepedidoshibernate.domain.user.UserDAOImp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class LoginController implements Serializable {

    @FXML
    private Label info;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUser;
    @FXML
    private Button btnLogin;

    @FXML
    public void login(ActionEvent actionEvent) {
        String user = txtUser.getText();
        String password = txtPassword.getText();
        User usuario = (new UserDAOImp()).validateUser(user, password);

        if (usuario == null){
            info.setText("Datos no validos");
            info.setTextFill(Color.RED);
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hola!");
            alert.setHeaderText("Inicio correcto");
            alert.setContentText("Bienvenid@, " + usuario.getNombre() + ".");
            alert.showAndWait();
            Session.setCurrentUser(usuario);
            Main.loadMain("ventana-principal.fxml");
        }
    }
}