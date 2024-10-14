package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ManufactureDAO {

    // Method to create a new manufacturer
    public void createManufacture(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(manufacture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Method to read a manufacturer by ID
    public Manufacture readManufacture(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Manufacture.class, id);
        }
    }

    // Method to update a manufacturer
    public void updateManufacture(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(manufacture);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Method to delete a manufacturer
    public void deleteManufacture(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            if (manufacture != null) {
                session.delete(manufacture);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Method to check if all manufacturers have more than 100 employees
    public boolean allManufacturersHaveMoreThan100Employees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Manufacture WHERE employee <= 100", Long.class);
            return query.uniqueResult() == 0;
        }
    }

    // Method to return the total number of employees of all manufacturers
    public long getTotalEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT SUM(employee) FROM Manufacture", Long.class);
            return query.uniqueResult() != null ? query.uniqueResult() : 0;
        }
    }

    // Method to return the last manufacturer based in the US
    public Manufacture getLastManufacturerBasedInUS() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Fetch the list of manufacturers
            List<Manufacture> manufactures = session.createQuery("FROM Manufacture", Manufacture.class).list();
            for (int i = manufactures.size() - 1; i >= 0; i--) {
                if (manufactures.get(i).getLocation().equalsIgnoreCase("US")) {
                    return manufactures.get(i);
                }
            }
            throw new IllegalStateException("No manufacturer based in the US found.");
        }
    }
}
