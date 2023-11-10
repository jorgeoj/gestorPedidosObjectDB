package com.example.gestiondepedidoshibernate.domain.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private Integer id;
    private String nombre;
    private Double precio;
    private Integer cantidad_disponible;
}
