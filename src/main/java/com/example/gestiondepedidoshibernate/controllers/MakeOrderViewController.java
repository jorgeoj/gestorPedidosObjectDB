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

/**
 * Controlador para la vista de realización de un pedido.
 */
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

    /**
     * Inicializa la vista de realización de un pedido.
     *
     * @param url            La URL de inicialización.
     * @param resourceBundle El ResourceBundle utilizado.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Carga la lista de productos disponibles en el ComboBox
        observableList = FXCollections.observableArrayList();
        ProductDAOImp productoDAO = new ProductDAOImp();
        observableList.setAll(productoDAO.getAll());

        comboProducto.setItems(observableList);

        // Configura el ComboBox para mostrar solo el nombre del producto
        comboProducto.setCellFactory(param -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNombre() == null) {
                    setText(null);
                } else {
                    setText(item.getNombre());
                }
            }
        });

        // Configura el Spinner para seleccionar la cantidad de productos
        spCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));
    }

    /**
     * Maneja el evento de cerrar sesión.
     *
     * @param actionEvent El evento que desencadena la acción.
     */
    @javafx.fxml.FXML
    public void cerrarSesion(ActionEvent actionEvent) {
        // Cierra la sesión del usuario y carga la ventana de inicio de sesión
        Sesion.setCurrentUser(null);
        Main.loadLogin("ventana-login.fxml");
    }

    /**
     * Maneja el evento de aceptar el pedido.
     *
     * @param actionEvent El evento que desencadena la acción.
     */
    @javafx.fxml.FXML
    public void aceptar(ActionEvent actionEvent) {
        // Crea un nuevo ítem para el pedido actual y carga la ventana de detalles del pedido
        Order order = Sesion.getCurrentOrder();
        if (order != null) {
            Item item = new Item();
            item.setCodigo_pedido(order);
            item.setCantidad(spCantidad.getValue());
            item.setProducto_id(comboProducto.getSelectionModel().getSelectedItem());

            Sesion.setItem((new ItemDAOImp()).save(item));
            Sesion.setItem(item);
            Main.loadWindow("ventana-pedido-detalles.fxml");
        }
    }

    /**
     * Maneja el evento de volver a la ventana principal.
     *
     * @param actionEvent El evento que desencadena la acción.
     */
    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        // Carga la ventana principal
        Main.loadWindow("ventana-principal.fxml");
    }
}