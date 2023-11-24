package com.example.gestiondepedidoshibernate;

import com.example.gestiondepedidoshibernate.domain.orders.Order;
import com.example.gestiondepedidoshibernate.domain.user.User;
import lombok.Getter;
import lombok.Setter;


public class Sesion {
    @Getter
    @Setter
    private static User currentUser;

    @Getter
    @Setter
    private static Order currentOrder;
}