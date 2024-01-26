package com.example.gestiondepedidoshibernate.domain.orders;

import com.example.gestiondepedidoshibernate.domain.items.Item;
import com.example.gestiondepedidoshibernate.domain.user.User;

import lombok.Data;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un pedido en el sistema.
 */
@Data
@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String codigo;

    private String fecha;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private User usuarioId;

    private Double total;

    @OneToMany(mappedBy = "codigo_pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    /**
     * Devuelve una representación en cadena de la orden.
     *
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

