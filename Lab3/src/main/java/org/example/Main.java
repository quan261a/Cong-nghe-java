package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Tạo mới một nhà sản xuất
            Manufacture manufacture = new Manufacture();
            manufacture.setName("Apple");
            manufacture.setEmployee(1000);

            // Tạo mới một điện thoại
            Phone phone = new Phone();
            phone.setName("iPhone 14");
            phone.setPrice(999);
            phone.setColor("Black");
            phone.setManufacture(manufacture); // Set the manufacture for the phone

            // Tạo danh sách điện thoại nếu cần và thêm điện thoại vào danh sách của nhà sản xuất
            List<Phone> phones = new ArrayList<>();
            phones.add(phone);
            manufacture.setPhones(phones);

            // Lưu nhà sản xuất (cũng sẽ lưu điện thoại nếu cascade được cấu hình đúng)
            session.save(manufacture);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
