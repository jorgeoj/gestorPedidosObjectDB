package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.Main;
import com.example.gestiondepedidoshibernate.Session;
import com.example.gestiondepedidoshibernate.domain.orders.Order;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.Serializable;

public class MainViewController implements Serializable {
    @javafx.fxml.FXML
    private Button btnCerrarSesion;
    @javafx.fxml.FXML
    private TableView<Order> tvPedidos;
    @javafx.fxml.FXML
    private TableColumn<Order, String> colIdPedido;
    @javafx.fxml.FXML
    private TableColumn<Order, String> colCodPedido;
    @javafx.fxml.FXML
    private TableColumn<Order, String> colFechaPedido;
    @javafx.fxml.FXML
    private TableColumn<Order, String> colUsuarioPedido;
    @javafx.fxml.FXML
    private TableColumn<Order, String> colTotalPedido;
    @javafx.fxml.FXML
    private Button btnNuevoPedido;

    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        Session.setCurrentUser(null);
        Main.loadLogin("ventana-login.fxml");
    }
}
