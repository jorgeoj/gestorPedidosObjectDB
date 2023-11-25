package com.example.gestiondepedidoshibernate.domain.products;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un producto en el sistema.
 */
@Data
@Entity
@Table(name = "producto")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "cantidad_disponible")
    private Integer cantidad_disponible;
}