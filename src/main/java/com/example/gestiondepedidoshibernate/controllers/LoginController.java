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
            Sesion.setUsuario(usuario);
            Main.loadMain("ventana-principal.fxml");
        }
    }
}