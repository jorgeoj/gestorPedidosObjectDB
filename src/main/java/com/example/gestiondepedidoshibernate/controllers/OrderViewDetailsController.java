package com.example.gestiondepedidoshibernate.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.Serializable;

public class OrderViewDetailsController implements Serializable {
    @javafx.fxml.FXML
    private Button btnCerrarSesion;
    @javafx.fxml.FXML
    private Label lbPrueba;
    @javafx.fxml.FXML
    private TableView tvItem;
    @javafx.fxml.FXML
    private TableColumn cIdItem;
    @javafx.fxml.FXML
    private TableColumn cCPedido;
    @javafx.fxml.FXML
    private TableColumn cCantidad;
    @javafx.fxml.FXML
    private TableColumn cProducto;
    @javafx.fxml.FXML
    private Button btnAnyadir;
    @javafx.fxml.FXML
    private Button btnEliminar;

    @javafx.fxml.FXML
    public void anyadir(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void eliminar(ActionEvent actionEvent) {
    }
}
