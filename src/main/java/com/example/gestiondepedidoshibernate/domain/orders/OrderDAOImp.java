package com.example.gestiondepedidoshibernate.domain.orders;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class OrderDAOImp implements DAO<Order> {

    @Override
    public ArrayList<Order> getAll() {
        var salida = new ArrayList<Order>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            //TODO: Mirar que vuelve a dar error
            Query<Order> query = sesion.createQuery("from Order", Order.class);
            salida = (ArrayList<Order>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Order get(Long id) {
        var salida = new Order();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            salida = session.get(Order.class, id);
        }
        return salida;
    }

    @Override
    public Order save(Order data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                // Comenzar la transacción
                transaction = session.beginTransaction();

                // Guardar el nuevo pedido en la base de datos
                session.save(data);

                // Commit de la transacción
                transaction.commit();
            } catch (Exception e) {
                // Manejar excepción que pueda ocurrir durante la transacción
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return data;
        }
    }

    @Override
    public void update(Order data) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Order data) {
        HibernateUtil.getSessionFactory().inTransaction((session -> {
            Order o = session.get(Order.class, data.getId());
            session.remove(o);
        }));
    }
}
