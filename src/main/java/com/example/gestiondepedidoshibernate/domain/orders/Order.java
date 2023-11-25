package com.example.gestiondepedidoshibernate.domain.orders;

import com.example.gestiondepedidoshibernate.domain.items.Item;
import com.example.gestiondepedidoshibernate.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un pedido en el sistema.
 */
@Data
@Entity
@Table(name = "pedido")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "fecha")
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private User usuarioId;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "codigo_pedido", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

    /**
     * Devuelve una representación en cadena de la orden.
     * @return Cadena que representa la orden.
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", usuarioId=" + usuarioId.getId() +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}

