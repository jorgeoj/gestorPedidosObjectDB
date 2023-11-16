package com.example.gestiondepedidoshibernate.domain.orders;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;

import java.util.ArrayList;

public class OrderDAOImp implements DAO<Order> {

    @Override
    public ArrayList<Order> getAll() {
        return null;
    }

    @Override
    public Order get(Long id) {
        return null;
    }

    @Override
    public Order save(Order data) {
        return null;
    }

    @Override
    public void update(Order data) {

    }

    @Override
    public void delete(Order data) {
        HibernateUtil.getSessionFactory().inTransaction((session -> {
            Order o = session.get(Order.class, data.getId());
            session.remove(o);
        }));
    }
}
