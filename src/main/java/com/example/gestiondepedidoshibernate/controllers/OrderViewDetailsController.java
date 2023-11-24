package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.Main;
import com.example.gestiondepedidoshibernate.Sesion;
import com.example.gestiondepedidoshibernate.domain.items.Item;
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

public class OrderViewDetailsController implements Initializable {
    @javafx.fxml.FXML
    private Button btnCerrarSesion;
    @javafx.fxml.FXML
    private Label lbPrueba;
    @javafx.fxml.FXML
    private TableView<Item> tvItem;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cIdItem;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cCPedido;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cCantidad;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cProducto;
    @javafx.fxml.FXML
    private Button btnAnyadir;
    @javafx.fxml.FXML
    private Button btnEliminar;

    private ObservableList<Item> observableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cIdItem.setCellValueFactory((fila) -> {
            String cIdItem = String.valueOf(fila.getValue().getId());
            return new SimpleStringProperty(cIdItem);
        });

        cCPedido.setCellValueFactory((fila) -> {
            String cCPedido = String.valueOf(fila.getValue().getCodigo_pedido().getCodigo());
            return new SimpleStringProperty(cCPedido);
        });

        cCantidad.setCellValueFactory((fila) -> {
            String cCantidad = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(cCantidad);
        });

        cProducto.setCellValueFactory((fila) -> {
            String cProducto = String.valueOf(fila.getValue().getProducto_id().getNombre() + " / " + fila.getValue().getProducto_id().getPrecio() + "€");
            return new SimpleStringProperty(cProducto);
        });

        observableList = FXCollections.observableArrayList();



        Sesion.setCurrentOrder((new OrderDAOImp()).get(Sesion.getCurrentOrder().getId()));
        observableList.setAll(Sesion.getCurrentOrder().getItems());
        System.out.println(observableList);
        tvItem.setItems(observableList);
    }

    //TODO: Mirar a ver si necesito botones de añadir y eliminar o no

    @javafx.fxml.FXML
    public void anyadir(ActionEvent actionEvent) {
        /*
        var item = new Item();
        Sesion.setItem(item);
        Main.loadAnyadirProducto("ventana-hacer-pedido.fxml");
        */
    }

    @javafx.fxml.FXML
    public void eliminar(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        Sesion.setCurrentUser(null);
        Main.loadLogin("ventana-login.fxml");
    }
}
