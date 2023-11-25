package com.example.gestiondepedidoshibernate.controllers;

import com.example.gestiondepedidoshibernate.Main;
import com.example.gestiondepedidoshibernate.Sesion;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import com.example.gestiondepedidoshibernate.domain.items.Item;
import com.example.gestiondepedidoshibernate.domain.orders.Order;
import com.example.gestiondepedidoshibernate.domain.orders.OrderDAOImp;
import com.example.gestiondepedidoshibernate.domain.user.UserDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controlador para la vista principal.
 */
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
    @javafx.fxml.FXML
    private Button btnEliminar;

    private ObservableList<Order> observableList;
    private OrderDAOImp orderDAOImp = new OrderDAOImp();

    /**
     * Constructor vacío de la clase.
     */
    public MainViewController(){}

    /**
     * Inicializa la vista principal.
     *
     * @param url            La URL de inicialización.
     * @param resourceBundle El ResourceBundle utilizado.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colIdPedido.setCellValueFactory((fila)->{
            String id = String.valueOf((fila.getValue().getId()));
            return new SimpleStringProperty(id);
        });
        colFechaPedido.setCellValueFactory((fila)->{
            String fecha = String.valueOf((fila.getValue().getFecha()));
            return new SimpleStringProperty(fecha);
        });
        colCodPedido.setCellValueFactory((fila) -> {
            String codigoPedido = fila.getValue().getCodigo();
            return new SimpleStringProperty(codigoPedido);
        });
        colUsuarioPedido.setCellValueFactory((fila) -> {
            String usuarioId = String.valueOf(fila.getValue().getUsuarioId().getNombre());
            return new SimpleStringProperty(usuarioId);
        });
        colTotalPedido.setCellValueFactory((fila) -> {
            String total = fila.getValue().getTotal() + " €";
            return new SimpleStringProperty(total);
        });

        observableList = FXCollections.observableArrayList();
        Sesion.setCurrentUser((new UserDAOImp()).get(Sesion.getCurrentUser().getId()));
        cargarLista();

        lblTitulo.setText("Bienvenido, " + Sesion.getCurrentUser().getNombre()+ " estos son tus pedidos");
        tvPedidos.getSelectionModel().selectedItemProperty().addListener((observableValue, pedido, t1) -> {
            Sesion.setCurrentOrder(t1);
        });



        tvPedidos.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Order selectedPedido = tvPedidos.getSelectionModel().getSelectedItem();
                if (selectedPedido != null) {
                    Sesion.setCurrentOrder(selectedPedido);
                    Main.loadWindow("ventana-pedido-detalles.fxml");
                }
            }
        });
    }

    /**
     * Carga la lista de pedidos del usuario actual en la tabla.
     */
    private void cargarLista() {

        //Obtiene la lista de pedidos del usuario actual y la asigna a la lista Observable.
        observableList.setAll(Sesion.getCurrentUser().getPedidos());

        //Recorre cada pedido en la lista Observable.
        for (Order order : observableList) {
            //Calcula el total del pedido y lo establece en el pedido actual.
            Double totalPedido = calcularTotalPedido(order);
            order.setTotal(totalPedido);
        }
        //Establece la lista Observable como los datos a mostrar en la tabla.
        tvPedidos.setItems(observableList);
    }

    /**
     * Calcula el total de un pedido basado en sus items.
     *
     * @param order El pedido del cual calcular el total.
     * @return El total del pedido.
     */
    private Double calcularTotalPedido(Order order) {
        //Inicializa la variable total como 0.0 para almacenar el total del pedido.
        Double total  = 0.0;

        //Itera a través de los items del pedido para calcular el total.
        for (Item item : order.getItems()){

            //Obtiene el precio del producto y lo multiplica por la cantidad, sumando al total.
            total += item.getProducto_id().getPrecio() * item.getCantidad();
        }
        //Devuelve el total calculado del pedido.
        return total;
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
     * Maneja el evento de añadir un nuevo pedido.
     *
     * @param actionEvent El evento que desencadena la acción.
     */
    @javafx.fxml.FXML
    public void anyadirPedido(ActionEvent actionEvent) {
        Order newOrder = new Order();

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            // Obtener el último código de pedido
            Query<String> query = sesion.createQuery("select max(o.codigo) from Order o", String.class);
            String ultimoCodigoPedido = query.uniqueResult();

            // Incrementar el último código de pedido
            int ultimoNumero = Integer.parseInt(ultimoCodigoPedido.substring(4));
            int nuevoNumero = ultimoNumero + 1;
            String nuevoCodigoPedido = "PED-" + String.format("%03d", nuevoNumero);

            // Establecer el nuevo código de pedido en el pedido
            newOrder.setCodigo(nuevoCodigoPedido);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Establecer la fecha actual por defecto
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormat.format(new Date());
        newOrder.setFecha(fechaActual);

        newOrder.setUsuarioId(Sesion.getCurrentUser());
        newOrder.setId(0L);

        if (newOrder.getItems().isEmpty()) {
            newOrder.setTotal(0.0);
        }

        // Agregar el nuevo pedido a la lista observable
        observableList.add(newOrder);

        // Actualizar la tabla
        tvPedidos.setItems(observableList);
        Sesion.setCurrentOrder((new OrderDAOImp()).save(newOrder));
        Sesion.setCurrentOrder(newOrder);
    }

    /**
     * Maneja el evento de eliminar un pedido seleccionado.
     *
     * @param actionEvent El evento que desencadena la acción.
     */
    @javafx.fxml.FXML
    public void eliminar(ActionEvent actionEvent) {
        //Se coge el pedido seleccionado.
        Order pedidoSeleccionado = tvPedidos.getSelectionModel().getSelectedItem();

        //Confirmación de eliminación mediante un diálogo de confirmación.
        if (pedidoSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿Deseas borrar el pedido: " + pedidoSeleccionado.getCodigo() + "?");
            var result = alert.showAndWait().get();

            //Si se confirma la eliminación, se borra el ítem seleccionado de la lista y de la base de datos.
            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                orderDAOImp.delete(pedidoSeleccionado);
                observableList.remove(pedidoSeleccionado);
            }
        } else {
            //Muestra un mensaje de error o advertencia al usuario si no se ha seleccionado ningún pedido para eliminar.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, selecciona un pedido para eliminar.");
            alert.showAndWait();
        }
    }
}
