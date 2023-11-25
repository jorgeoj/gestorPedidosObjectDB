package com.example.gestiondepedidoshibernate.domain.user;

import org.hibernate.Session;
import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Implementación concreta de DAO para la entidad User.
 */
public class UserDAOImp implements DAO<User> {

    /**
     * Obtiene todos los usuarios.
     * @return Lista de todos los usuarios almacenados.
     */
    @Override
    public ArrayList<User> getAll() {
        var salida = new ArrayList<User>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<User> query = sesion.createQuery("from User", User.class);
            salida = (ArrayList<User>) query.getResultList();
        }
        return salida;
    }

    /**
     * Obtiene un usuario por su ID.
     * @param id El ID del usuario a obtener.
     * @return El usuario correspondiente al ID especificado.
     */
    @Override
    public User get(Long id) {
        var salida = new User();
        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            salida = s.get(User.class,id);
        }
        return salida;
    }

    /**
     * Guarda un nuevo usuario.
     * @param data El usuario a guardar.
     * @return El usuario guardado.
     */
    @Override
    public User save(User data) {
        return null; // No hace nada
    }

    /**
     * Actualiza un usuario existente.
     * @param data El usuario a actualizar.
     */
    @Override
    public void update(User data) {
        // No hace nada
    }

    /**
     * Elimina un usuario existente.
     * @param data El usuario a eliminar.
     */
    @Override
    public void delete(User data) {
        // No hace nada
    }

    /**
     * Valida un usuario por su nombre de usuario y contraseña.
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return El usuario validado o null si no se encuentra.
     */
    public User validateUser(String username, String password){
        User result = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<User> query = session.createQuery("from User where nombre=:u and contrasenya=:p", User.class);
            query.setParameter("u", username);
            query.setParameter("p", password);
            try {
                result = query.getSingleResult();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}