package com.example.gestiondepedidoshibernate.domain.orders;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.ObjectDBUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

/**
 * Implementación concreta de DAO para la entidad Order.
 */
public class OrderDAOImp implements DAO<Order> {

    /**
     * Obtiene todos los pedidos.
     *
     * @return Lista de todos los pedidos almacenados.
     */
    @Override
    public ArrayList<Order> getAll() {
        return null;
    }

    /**
     * Obtiene un pedido por su ID.
     *
     * @param id El ID del pedido a obtener.
     * @return El pedido correspondiente al ID especificado.
     */
    @Override
    public Order get(Long id) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Order o;
        try{
            o = em.find(Order.class,id);
        } finally {
            em.close();
        }
        return o;
    }

    /**
     * Guarda un nuevo pedido.
     *
     * @param data El pedido a guardar.
     * @return El pedido guardado.
     */
    @Override
    public Order save(Order data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(data);
            em.flush();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return data;
    }

    /**
     * Actualiza un pedido existente.
     *
     * @param data El pedido a actualizar.
     */
    @Override
    public Order update(Order data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            data = em.merge(data);
            em.getTransaction().commit();

        }catch (Exception ex){

            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return data;
    }

    /**
     * Elimina un pedido existente.
     *
     * @param data El pedido a eliminar.
     */
    @Override
    public Boolean delete(Order data) {
        Boolean salida= false;
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Order o = em.find(Order.class,data.getId());
            salida = (o != null);
            if(salida) {
                em.remove(o);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return salida;
    }

    public String getUltimoCodigo() {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        TypedQuery<String> query = em.createQuery("select max(p.codigo) from Order p", String.class);
        return query.getSingleResult();
    }
}