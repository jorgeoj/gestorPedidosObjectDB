package com.example.gestiondepedidoshibernate.domain.user;

import org.hibernate.Session;
import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class UserDAOImp implements DAO<User> {
    @Override
    public ArrayList<User> getAll() {
        var salida = new ArrayList<User>(0);

        //TODO: mirar porque User da error ahi
        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<User> q = s.createQuery("from User", User.class);
            salida = (ArrayList<User>) q.getResultList();
        }

        return null;
    }

    @Override
    public User get(Long id) {
        var salida = new User();

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            salida = s.get(User.class,id);
        }

        return salida;
    }

    @Override
    public User save(User data) {
        return null;
    }

    @Override
    public void update(User data) {

    }

    @Override
    public void delete(User data) {

    }
}
