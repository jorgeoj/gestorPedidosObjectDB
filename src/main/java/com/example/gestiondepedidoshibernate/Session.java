package com.example.gestiondepedidoshibernate;

import com.example.gestiondepedidoshibernate.domain.items.Item;
import com.example.gestiondepedidoshibernate.domain.orders.Order;
import com.example.gestiondepedidoshibernate.domain.products.Product;
import com.example.gestiondepedidoshibernate.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Session {

    /**
     * Posición del pedido seleccionado en la tabla.
     */
    @Getter
    @Setter
    private static Integer pos = null;

    /**
     * Usuario actual en la sesión.
     */
    @Getter
    @Setter
    private static User usuario;

    /**
     * Pedido en la sesión.
     */
    @Getter
    @Setter
    private static Order pedido;

    @Setter
    @Getter
    private static Item item;

    @Getter
    @Setter
    private static Product producto;

    /**
     * Lista de pedidos en la sesión.
     */
    @Getter
    @Setter
    private static ArrayList<Order> pedidos = new ArrayList<>();

    /**
     * Lista de productos en la sesión.
     */
    @Getter
    @Setter
    private static ArrayList<Product> productos = new ArrayList<>();

    /**
     * Lista de items en la sesión.
     */
    @Getter
    @Setter
    private static ArrayList<Item> items = new ArrayList<>();
}
