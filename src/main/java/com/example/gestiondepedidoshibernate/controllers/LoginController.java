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
        String pass = txtPassword.getText();

        if( user.length()<4 || pass.length()<4 ){
            info.setText("Introduce los datos");
            info.setStyle("-fx-text-fill: red;");

        } else{

            // ACCESO A BASE DE DATOS PARA LA VALIDACION
            User u = (new UserDAOImp()).validateUser( user, pass );

            if(u==null){
                info.setText("Error, datos incorrectos");
                info.setStyle("-fx-text-fill: red;");
            }else {
                Sesion.setCurrentUser(u);

                // Guardar usuario en sesión e ir a la proxima ventana
                Main.loadWindow("ventana-principal.fxml");
            }

        }
    }
}