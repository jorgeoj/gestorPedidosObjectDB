package com.example.gestiondepedidoshibernate;

import com.example.gestiondepedidoshibernate.domain.items.Item;
import com.example.gestiondepedidoshibernate.domain.orders.Order;
import com.example.gestiondepedidoshibernate.domain.user.User;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la sesión actual del usuario en el sistema.
 */
public class Sesion {
    /**
     * Usuario actualmente logueado en la sesión.
     */
    @Getter
    @Setter
    private static User currentUser;

    /**
     * Pedido actual asociado a la sesión.
     */
    @Getter
    @Setter
    private static Order currentOrder;

    /**
     * Ítem actual asociado a la sesión.
     */
    @Getter
    @Setter
    private static Item item;
}

