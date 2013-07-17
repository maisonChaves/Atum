/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gerador.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Ian
 */
public class DAO {

    protected Session session;
    protected Transaction transaction;

    public DAO(Session session) {
        this.session = session;
    }

    public Object inserir(Object objeto) {
        session.persist(objeto);
        transaction = session.beginTransaction();
        transaction.commit();
        return objeto;
    }

    public Object busca(Class clazz, Integer id) {
        return session.get(clazz, id);
    }

    public List buscaCriteria(Criteria criteria) {
        return criteria.list();
    }
}
