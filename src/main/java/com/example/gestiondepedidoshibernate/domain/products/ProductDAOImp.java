package com.example.gestiondepedidoshibernate.domain.products;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ProductDAOImp implements DAO<Product> {
    @Override
    public ArrayList<Product> getAll() {
        var salida = new ArrayList<Product>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Product> query = sesion.createQuery("from Product ", Product.class);
            salida = (ArrayList<Product>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Product get(Long id) {
        return null;
    }

    @Override
    public Product save(Product data) {
        return null;
    }

    @Override
    public void update(Product data) {}

    @Override
    public void delete(Product data) {}
}
