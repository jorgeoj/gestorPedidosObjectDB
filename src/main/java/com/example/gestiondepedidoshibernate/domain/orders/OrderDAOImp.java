package com.example.gestiondepedidoshibernate.domain.orders;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import java.util.ArrayList;

/**
 * Implementación concreta de DAO para la entidad Order.
 */
public class OrderDAOImp implements DAO<Order> {

    /**
     * Obtiene todos los pedidos.
     * @return Lista de todos los pedidos almacenados.
     */
    @Override
    public ArrayList<Order> getAll() {
        var salida = new ArrayList<Order>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Order> query = sesion.createQuery("from Order", Order.class);
            salida = (ArrayList<Order>) query.getResultList();
        }
        return salida;
    }

    /**
     * Obtiene un pedido por su ID.
     * @param id El ID del pedido a obtener.
     * @return El pedido correspondiente al ID especificado.
     */
    @Override
    public Order get(Long id) {
        var salida = new Order();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            salida = session.get(Order.class, id);
        }
        return salida;
    }

    /**
     * Guarda un nuevo pedido.
     * @param data El pedido a guardar.
     * @return El pedido guardado.
     */
    @Override
    public Order save(Order data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                // Guardar el nuevo pedido en la base de datos
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
     * Actualiza un pedido existente.
     * @param data El pedido a actualizar.
     */
    @Override
    public void update(Order data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                // Actualizar el pedido en la base de datos
                session.update(data);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    /**
     * Elimina un pedido existente.
     * @param data El pedido a eliminar.
     */
    @Override
    public void delete(Order data) {
        HibernateUtil.getSessionFactory().inTransaction((session -> {
            Order o = session.get(Order.class, data.getId());
            session.remove(o);
        }));
    }
}