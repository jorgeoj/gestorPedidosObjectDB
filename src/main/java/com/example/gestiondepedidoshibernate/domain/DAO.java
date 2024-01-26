package com.example.gestiondepedidoshibernate.domain;

import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en una base de datos.
 *
 * @param <T> Tipo genérico que representa la entidad asociada al DAO.
 */
public interface DAO<T> {

    /**
     * Obtiene todos los elementos del tipo T.
     *
     * @return Una lista de todos los elementos del tipo T.
     */
    public List<T> getAll();

    /**
     * Obtiene un elemento del tipo T mediante su identificador.
     *
     * @param id El identificador del elemento a buscar.
     * @return El elemento del tipo T correspondiente al identificador especificado.
     */
    public T get(Long id);

    /**
     * Guarda un nuevo elemento del tipo T en la base de datos.
     *
     * @param data El elemento del tipo T a guardar.
     * @return El elemento del tipo T guardado.
     */
    public T save(T data);

    /**
     * Actualiza un elemento del tipo T en la base de datos.
     *
     * @param data El elemento del tipo T a actualizar.
     */
    T update(T data);

    /**
     * Elimina un elemento del tipo T de la base de datos.
     *
     * @param data El elemento del tipo T a eliminar.
     */
    public Boolean delete(T data);
}
