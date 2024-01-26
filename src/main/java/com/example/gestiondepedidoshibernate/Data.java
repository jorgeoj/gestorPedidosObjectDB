package com.example.gestiondepedidoshibernate;

import com.example.gestiondepedidoshibernate.domain.products.Product;
import com.example.gestiondepedidoshibernate.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<User> generarUsuarios() {
        List<User> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new User("Jorge", "1234", "jorge@gmail.com", new ArrayList<>()));
        listaUsuarios.add(new User("Raul", "12345", "raul@gmail.com", new ArrayList<>()));
        return listaUsuarios;
    }

    public static List<Product> generarProductos() {
        List<Product> listaProductos = new ArrayList<>();
        listaProductos.add(new Product("Smartphone", 299.0, 50));
        listaProductos.add(new Product("Portátil", 799.0, 30));
        listaProductos.add(new Product("Auriculares Inalámbricos", 79.0, 100));
        listaProductos.add(new Product("Televisor LED", 599.0, 20));
        listaProductos.add(new Product("Tableta", 199.0, 40));
        listaProductos.add(new Product("Altavoz", 19.0,50));
        return listaProductos;
    }
}
