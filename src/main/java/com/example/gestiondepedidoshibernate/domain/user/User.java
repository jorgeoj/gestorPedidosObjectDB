package com.example.gestiondepedidoshibernate.domain.user;

import com.example.gestiondepedidoshibernate.domain.orders.Order;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un usuario en el sistema.
 */
@Data
@Entity
public class User implements Serializable {

    public User(String nombre, String contrasenya, String email, List<Order> pedidos) {
        this.nombre = nombre;
        this.contrasenya = contrasenya;
        this.email = email;
        this.pedidos = pedidos;
    }

    @Id
    @GeneratedValue
    private Long id;


    private String nombre;


    private String contrasenya;


    private String email;

    @OneToMany(mappedBy = "usuarioId", fetch = FetchType.EAGER)
    private List<Order> pedidos;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", contraseña='" + contrasenya + '\'' +
                ", email='" + email + '\'' +
                ", pedidos=" + pedidos +
                '}';
    }
}