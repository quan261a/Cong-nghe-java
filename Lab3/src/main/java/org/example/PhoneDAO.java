package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PhoneDAO {

    public void create(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(phone);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Phone read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Phone.class, id);
        }
    }

    public void update(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(phone);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Phone phone = session.get(Phone.class, id);
            if (phone != null) {
                session.delete(phone);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Additional methods

    public Phone getHighestPricePhone() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Phone> query = session.createQuery("FROM Phone ORDER BY price DESC", Phone.class);
            return query.setMaxResults(1).uniqueResult();
        }
    }

    public List<Phone> getPhonesSortedByCountryAndPrice() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Phone> query = session.createQuery("FROM Phone p JOIN p.manufacture m ORDER BY m.name, p.price DESC", Phone.class);
            return query.list();
        }
    }

    public boolean hasPhoneAbove50Million() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(p) FROM Phone p WHERE p.price > 50000000", Long.class);
            return query.uniqueResult() > 0;
        }
    }

    public Phone getFirstPinkPhoneOver15Million() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Phone> query = session.createQuery("FROM Phone WHERE color = :color AND price > :price", Phone.class);
            query.setParameter("color", "Pink");
            query.setParameter("price", 15000000);
            return query.setMaxResults(1).uniqueResult();
        }
    }
}

