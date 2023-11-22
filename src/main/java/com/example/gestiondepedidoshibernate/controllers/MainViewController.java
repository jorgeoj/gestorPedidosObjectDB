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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        Sesion.setUsuario((new UserDAOImp()).get(Sesion.getUsuario().getId()));
        
        loadList();

        lblTitulo.setText("Bienvenido, " + Sesion.getUsuario().getNombre() + " estos son tus pedidos");

        tvPedidos.getSelectionModel().selectedItemProperty().addListener((observableValue, pedido, t1) -> {
            Sesion.setPedido(t1);
        });

        tvPedidos.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Order selectedPedido = tvPedidos.getSelectionModel().getSelectedItem();
                if (selectedPedido != null) {
                    Sesion.setPedido(selectedPedido);
                    Main.loadFXMLDetalles("ventana-pedido-detalles.fxml");
                }
            }
        });
    }

    private void loadList() {
        observableList.setAll(Sesion.getUsuario().getPedidos());
        for (Order order : observableList) {
            Double totalPedido = calculateTotal(order);
            order.setTotal(totalPedido);
        }
        tvPedidos.setItems(observableList);
    }

    private Double calculateTotal(Order order) {
        Double total = 0.0;
        for (Item item: order.getItems()){
            total += item.getProducto_id().getPrecio() * item.getCantidad();
        }
        return total;
    }

    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        Main.loadLogin("ventana-login.fxml");
    }

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

        newOrder.setUsuarioId(Sesion.getUsuario());
        newOrder.setId(0L);

        if (newOrder.getItems().isEmpty()) {
            newOrder.setTotal(0.0);
        }

        // Agregar el nuevo pedido a la lista observable
        observableList.add(newOrder);

        // Actualizar la tabla
        tvPedidos.setItems(observableList);
        Sesion.setPedido((new OrderDAOImp()).save(newOrder));
        Sesion.setPedido(newOrder);
    }
}
