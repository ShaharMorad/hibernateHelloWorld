package net.codejava.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateManager {
    protected SessionFactory sessionFactory;

    protected void setup() {
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure() // configures settings from hibernate.cfg.xml
//                .build();
//        try {
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        } catch (Exception ex) {
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    protected void exit() {
        sessionFactory.close();
    }

    protected void create() {
        Product book = new Product("book3",1);
        Store s = new Store("store1",1);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(book);
        session.save(s);

        session.getTransaction().commit();
        session.close();
    }

    protected void read() {
        // code to get a book
    }

    protected void update() {
        // code to modify a book
    }

    protected void delete() {
        // code to remove a book
    }

    public static void main(String[] args) {
        hibernateManager manager = new hibernateManager();
        manager.setup();
        manager.create();
        manager.exit();
    }
}
