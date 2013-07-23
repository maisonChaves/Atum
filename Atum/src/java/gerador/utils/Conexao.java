/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gerador.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Ian
 */
public class Conexao {

    private static SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

    public static Session getConexao() {
        return sessionFactory.openSession();
    }

    public static void fechaSessao(Session session) {
        if (session.isOpen()) {
            session.close();
            session.clear();
            session.flush();
        }
    }
}
