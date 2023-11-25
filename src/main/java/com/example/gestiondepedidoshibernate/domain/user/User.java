package com.example.gestiondepedidoshibernate.domain.user;

import com.example.gestiondepedidoshibernate.domain.orders.Order;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un usuario en el sistema.
 */
@Data
@Entity
@Table(name = "usuario")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "contrasenya")
    private String contrasenya;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "usuarioId", fetch = FetchType.EAGER)
    private List<Order> pedidos = new ArrayList<>(0);
}