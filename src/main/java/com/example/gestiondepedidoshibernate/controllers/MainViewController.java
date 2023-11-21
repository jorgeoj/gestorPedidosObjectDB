package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.Main;
import com.example.gestiondepedidoshibernate.Session;
import com.example.gestiondepedidoshibernate.domain.orders.Order;
import com.example.gestiondepedidoshibernate.domain.orders.OrderDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
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
    private Label lblTitulo;

    private ObservableList<Order> observableList;
    private OrderDAOImp orderDAOImp = new OrderDAOImp();


    public MainViewController(){}

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.colIdPedido.setCellValueFactory((fila)->{
            String id = String.valueOf((fila.getValue().getId()));
            return new SimpleStringProperty(id);
        });
        this.colFechaPedido.setCellValueFactory((fila)->{
            String fecha = String.valueOf((fila.getValue().getFecha()));
            return new SimpleStringProperty(fecha);
        });
        this.colCodPedido.setCellValueFactory((fila) -> {
            String codigoPedido = fila.getValue().getCodigo();
            return new SimpleStringProperty(codigoPedido);
        });
        this.colUsuarioPedido.setCellValueFactory((fila) -> {
            String usuarioId = String.valueOf(fila.getValue().getUsuarioId());
            return new SimpleStringProperty(usuarioId);
        });
        this.colTotalPedido.setCellValueFactory((fila) -> {
            String total = String.valueOf(fila.getValue().getTotal());
            return new SimpleStringProperty(total);
        });

        observableList = FXCollections.observableArrayList();


    }

    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        Session.setUsuario(null);
        Main.loadLogin("ventana-login.fxml");
    }
}
