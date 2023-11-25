package com.example.gestiondepedidoshibernate.domain.items;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Implementación concreta de DAO para la entidad Item.
 */
public class ItemDAOImp implements DAO<Item> {

    /**
     * Obtiene todos los elementos Item.
     * @return Lista de todos los elementos Item almacenados.
     */
    @Override
    public ArrayList<Item> getAll() {
        var salida = new ArrayList<Item>(0);
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            Query<Item> query = sesion.createQuery("from Item", Item.class);
            salida = (ArrayList<Item>) query.getResultList();
        }
        return salida;
    }

    /**
     * Obtiene un elemento Item por su ID.
     * @param id El ID del elemento Item a obtener.
     * @return El elemento Item correspondiente al ID especificado.
     */
    @Override
    public Item get(Long id) {
        return null; // Implementar la lógica para obtener un elemento Item por ID
    }

    /**
     * Guarda un nuevo elemento Item.
     * @param data El elemento Item a guardar.
     * @return El elemento Item guardado.
     */
    @Override
    public Item save(Item data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                // Guardar el nuevo elemento Item en la base de datos
                session.save(data);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return data;
        }
    }

    /**
     * Actualiza un elemento Item existente.
     * @param data El elemento Item a actualizar.
     */
    @Override
    public void update(Item data) {
        // Implementar la lógica para actualizar un elemento Item
    }

    /**
     * Elimina un elemento Item existente.
     * @param data El elemento Item a eliminar.
     */
    @Override
    public void delete(Item data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Item item = session.get(Item.class, data.getId());
            session.remove(item);
        });
    }
}