package com.example.gestiondepedidoshibernate.domain.items;

import com.example.gestiondepedidoshibernate.domain.orders.Order;
import com.example.gestiondepedidoshibernate.domain.products.Product;
import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;

@Data
@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "codigo_pedido", referencedColumnName = "codigo_pedido")
    private Order codigo_pedido;

    @OneToOne
    @JoinColumn(name = "producto_id")
    private Product producto_id;

    @Column(name = "cantidad")
    private Integer cantidad;
}
