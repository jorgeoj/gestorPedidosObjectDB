package com.example.gestiondepedidoshibernate.domain.products;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;

/**
 * Clase que representa un producto en el sistema.
 */
@Data
@Entity
public class Product implements Serializable {

    public Product(String nombre, Double precio, Integer cantidad_disponible) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad_disponible = cantidad_disponible;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String nombre;

    private Double precio;

    private Integer cantidad_disponible;

    @Override
    public String toString() {
        return " " + nombre +
                ", (" + precio + ")";
    }
}