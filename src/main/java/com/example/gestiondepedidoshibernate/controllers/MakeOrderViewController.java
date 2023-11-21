package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.Main;
import com.example.gestiondepedidoshibernate.Sesion;
import com.example.gestiondepedidoshibernate.domain.items.Item;
import com.example.gestiondepedidoshibernate.domain.items.ItemDAOImp;
import com.example.gestiondepedidoshibernate.domain.orders.Order;
import com.example.gestiondepedidoshibernate.domain.products.Product;
import com.example.gestiondepedidoshibernate.domain.products.ProductDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MakeOrderViewController implements Initializable {
    @javafx.fxml.FXML
    private Button btnCerrarSesion;
    @javafx.fxml.FXML
    private Spinner<Integer> spCantidad;
    @javafx.fxml.FXML
    private ComboBox<Product> comboProducto;
    @javafx.fxml.FXML
    private Button btnAceptar;
    @javafx.fxml.FXML
    private Button btnVolver;

    private ObservableList<Product> observableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableList = FXCollections.observableArrayList();
        ProductDAOImp productoDAO = new ProductDAOImp();
        observableList.setAll(productoDAO.getAll());
        comboProducto.setItems(observableList);
        spCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));
    }

    @javafx.fxml.FXML
    public void cerrarSesion(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        Main.loadLogin("ventana-login.fxml");
    }

    @javafx.fxml.FXML
    public void aceptar(ActionEvent actionEvent) {
        Order order = Sesion.getPedido();
        if (order != null) {
            Item item = new Item();
            item.setCodigo_pedido(order);
            item.setCantidad(spCantidad.getValue());
            item.setProducto_id(comboProducto.getSelectionModel().getSelectedItem());

            Sesion.setItem((new ItemDAOImp()).save(item));
            Sesion.setItem(item);
            Main.loadMain("ventana-principal.fxml");
        }
    }

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        Main.loadMain("ventana-principal.fxml");
    }


}
