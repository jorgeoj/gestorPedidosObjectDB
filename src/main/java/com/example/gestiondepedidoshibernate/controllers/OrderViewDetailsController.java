package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.Main;
import com.example.gestiondepedidoshibernate.Sesion;
import com.example.gestiondepedidoshibernate.domain.items.Item;
import com.example.gestiondepedidoshibernate.domain.items.ItemDAOImp;
import com.example.gestiondepedidoshibernate.domain.orders.OrderDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de detalles de un pedido.
 */
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
    @javafx.fxml.FXML
    private Button btnVolver;

    private ItemDAOImp itemDAOImp = new ItemDAOImp();
    private ObservableList<Item> observableList;

    /**
     * Inicializa la vista de detalles del pedido.
     *
     * @param url            La URL de inicialización.
     * @param resourceBundle El ResourceBundle utilizado.
     */
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

        tvItem.setItems(observableList);
    }

    /**
     * Maneja el evento de agregar un nuevo ítem al pedido.
     *
     * @param actionEvent El evento que desencadena la acción.
     */
    @javafx.fxml.FXML
    public void anyadir(ActionEvent actionEvent) {
        var item = new Item();
        Sesion.setItem(item);
        Main.loadWindow("ventana-hacer-pedido.fxml");
    }

    /**
     * Maneja el evento de eliminar un ítem del pedido.
     *
     * @param actionEvent El evento que desencadena la acción.
     */
    @javafx.fxml.FXML
    public void eliminar(ActionEvent actionEvent) {
        //Se coge el item seleccionado.
        Item itemSeleccionado = tvItem.getSelectionModel().getSelectedItem();

        //Confirmación de eliminación mediante un diálogo de confirmación.
        if (itemSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿Deseas borrar el item: " + itemSeleccionado.getId() + ", que contiene el producto: " + itemSeleccionado.getProducto_id().getNombre() + "?");
            var result = alert.showAndWait().get();

            //Si se confirma la eliminación, se borra el ítem seleccionado de la lista y de la base de datos.
            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                itemDAOImp.delete(itemSeleccionado);
                observableList.remove(itemSeleccionado);
            }
        } else {
            //Muestra un mensaje de error o advertencia al usuario si no se ha seleccionado ningún pedido para eliminar.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, selecciona un pedido para eliminar.");
            alert.showAndWait();
        }
    }

    /**
     * Maneja el evento de cerrar sesión.
     *
     * @param actionEvent El evento que desencadena la acción.
     */
    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        Sesion.setCurrentUser(null);
        Main.loadLogin("ventana-login.fxml");
    }

    /**
     * Maneja el evento de volver a la ventana principal.
     *
     * @param actionEvent El evento que desencadena la acción.
     */
    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        Main.loadWindow("ventana-principal.fxml");
    }
}