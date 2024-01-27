package com.example.gestiondepedidoshibernate.domain.items;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.ObjectDBUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

/**
 * Implementación concreta de DAO para la entidad Item.
 */
public class ItemDAOImp implements DAO<Item> {

    /**
     * Obtiene todos los elementos Item.
     *
     * @return Lista de todos los elementos Item almacenados.
     */
    @Override
    public ArrayList<Item> getAll() {
        var salida = new ArrayList<Item>(0);
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Item> query = entityManager.createQuery("select i from Item i", Item.class);
            salida = (ArrayList<Item>) query.getResultList();
        } finally {
            entityManager.close();
        }
        return salida;
    }

    /**
     * Obtiene un elemento Item por su ID.
     *
     * @param id El ID del elemento Item a obtener.
     * @return El elemento Item correspondiente al ID especificado.
     */
    @Override
    public Item get(Long id) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Item i;
        try{
            i = em.find(Item.class,id);
        } finally {
            em.close();
        }
        return i;
    }

    /**
     * Guarda un nuevo elemento Item.
     *
     * @param data El elemento Item a guardar.
     * @return El elemento Item guardado.
     */
    @Override
    public Item save(Item data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(data);
            em.flush();
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return data;
    }

    /**
     * Actualiza un elemento Item existente.
     *
     * @param data El elemento Item a actualizar.
     */
    @Override
    public Item update(Item data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(data);
            em.getTransaction().commit();

        }catch (Exception ex){

            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return data;
    }

    /**
     * Elimina un elemento Item existente.
     *
     * @param data El elemento Item a eliminar.
     */
    @Override
    public Boolean delete(Item data) {
        Boolean salida;
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Item i = em.find(Item.class,data.getId());
            salida = (i!=null);
            if(salida) {
                em.remove(i);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return salida;
    }
}
