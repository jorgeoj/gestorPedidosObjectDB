package com.example.gestiondepedidoshibernate.domain;

import lombok.Getter;
import lombok.extern.java.Log;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Utilidad para la gestión de la EntityManagerFactory en el contexto de ObjectDB.
 * Proporciona una instancia estática de EntityManagerFactory para ser utilizada en la aplicación.
 *
 * @since 1.0
 */
@Log
public class ObjectDBUtil {
    /**
     * La instancia estática de EntityManagerFactory para la aplicación.
     */
    @Getter
    private final static EntityManagerFactory entityManagerFactory;

    static {
        // Se inicializa la EntityManagerFactory al cargar la clase.
        entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");
    }
}
