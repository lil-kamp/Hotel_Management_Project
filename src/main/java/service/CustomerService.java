package service;

import model.Hotel;
import model.users.Customer;
import model.users.Manager;
import model.users.Worker;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Objects;

public class CustomerService {

    private static CustomerService customerService;
    private EntityManager entityManager;

    private CustomerService(){
    }

    public static CustomerService getInstance() {
        if (Objects.isNull(customerService)) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public Customer save(Customer customer){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            Customer saving = entityManager.find(Customer.class, customer.getId());
            if (Objects.isNull(saving))
                saving = new Customer();

            saving.setHotel(customer.getHotel());
            saving.setNationalId(customer.getNationalId());
            saving.setEmail(customer.getEmail());
            saving.setFirstName(customer.getFirstName());
            saving.setLastName(customer.getLastName());
            saving.setPassword(customer.getPassword());

            entityManager.persist(saving);
            entityManager.getTransaction().commit();
            return saving;
        } catch (Exception e) {
            return null;
        }
    }

    public Customer login(String username, String password){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select c from Customer c where c.email = :username and c.password = :pass";
            return entityManager.createQuery(jpql, Customer.class)
                    .setParameter("username", username)
                    .setParameter("pass", password)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
