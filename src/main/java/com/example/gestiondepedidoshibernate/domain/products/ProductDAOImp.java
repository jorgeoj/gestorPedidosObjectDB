package com.example.gestiondepedidoshibernate.domain.products;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Implementación concreta de DAO para la entidad Product.
 */
public class ProductDAOImp implements DAO<Product> {

    /**
     * Obtiene todos los productos.
     * @return Lista de todos los productos almacenados.
     */
    @Override
    public ArrayList<Product> getAll() {
        var salida = new ArrayList<Product>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Product> query = sesion.createQuery("from Product ", Product.class);
            salida = (ArrayList<Product>) query.getResultList();
        }
        return salida;
    }

    /**
     * Obtiene un producto por su ID.
     * @param id El ID del producto a obtener.
     * @return El producto correspondiente al ID especificado.
     */
    @Override
    public Product get(Long id) {
        return null; // No hace nada
    }

    /**
     * Guarda un nuevo producto.
     * @param data El producto a guardar.
     * @return El producto guardado.
     */
    @Override
    public Product save(Product data) {
        return null; // No hace nada
    }

    /**
     * Actualiza un producto existente.
     * @param data El producto a actualizar.
     */
    @Override
    public void update(Product data) {
        // No hace nada
    }

    /**
     * Elimina un producto existente.
     * @param data El producto a eliminar.
     */
    @Override
    public void delete(Product data) {
        // No hace nada
    }
}