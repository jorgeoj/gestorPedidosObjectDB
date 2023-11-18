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
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<User> query = sesion.createQuery("from User", User.class);
            salida = (ArrayList<User>) query.getResultList();
        }
        return salida;
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
    public void update(User data) {}

    @Override
    public void delete(User data) {}

    public User validateUser(String username, String password){
        //Desde un lambda no se puede escribir desde una variable externa.
        User result = null;

        //Si la sesión está dentro de un try con recursos se cierra sola.
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            //Se hacen consultas a la entidad (clase User) no a la tabla.
            Query<User> query = session.createQuery("from User where email=:u and contrasenya=:p", User.class);

            //Se refieren a los que entran por el método.
            query.setParameter("u", username);
            query.setParameter("p", password);
            //

            try {
                result = query.getSingleResult();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
