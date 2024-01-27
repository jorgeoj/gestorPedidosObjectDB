package com.example.gestiondepedidoshibernate.domain.products;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.ObjectDBUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación concreta de DAO para la entidad Product.
 */
public class ProductDAOImp implements DAO<Product> {

    /**
     * Obtiene todos los productos.
     *
     * @return Lista de todos los productos almacenados.
     */
    @Override
    public List<Product> getAll() {
        List<Product> salida;

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
            salida = query.getResultList();
        } finally {
            em.close();
        }
        return salida;
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id El ID del producto a obtener.
     * @return El producto correspondiente al ID especificado.
     */
    @Override
    public Product get(Long id) {
        return null; // No hace nada
    }

    /**
     * Guarda un nuevo producto.
     *
     * @param data El producto a guardar.
     * @return El producto guardado.
     */
    @Override
    public Product save(Product data) {
        return null; // No hace nada
    }

    /**
     * Actualiza un producto existente.
     *
     * @param data El producto a actualizar.
     */
    @Override
    public Product update(Product data) {
        return null;
    }

    /**
     * Elimina un producto existente.
     *
     * @param data El producto a eliminar.
     */
    @Override
    public Boolean delete(Product data) {
        return null;
    }

    /**
     * Guarda una lista de productos en la base de datos.
     * Utiliza JPA (Java Persistence API) para realizar la operación de persistencia.
     *
     * @param productos La lista de productos que se va a guardar en la base de datos.
     * @throws javax.persistence.PersistenceException Si ocurre un error durante la operación de persistencia.
     */
    public void saveAll(List<Product> productos) {
        // Se obtiene una instancia del EntityManager.
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            // Se inicia una transacción antes de realizar la operación de persistencia.
            em.getTransaction().begin();

            // Se itera sobre la lista de productos y se guarda cada uno en la base de datos.
            for (Product p : productos) {
                em.persist(p);
            }

            // Se realiza un commit para confirmar la transacción.
            em.getTransaction().commit();
        } finally {
            // Se asegura de cerrar el EntityManager, independientemente de si la transacción fue exitosa o no.
            em.close();
        }
    }

}