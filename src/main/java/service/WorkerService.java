package service;

import model.Room;
import model.users.Manager;
import model.users.Worker;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkerService {

    private static WorkerService workerService;
    private EntityManager entityManager;

    private WorkerService(){
    }

    public static WorkerService getInstance() {
        if (Objects.isNull(workerService)) {
            workerService = new WorkerService();
        }
        return workerService;
    }

    public Worker save(Worker worker){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            Worker saving = entityManager.find(Worker.class, worker.getId());
            if (Objects.isNull(saving))
                saving = new Worker();

            saving.setHotel(worker.getHotel());
            saving.setBalance(worker.getBalance());
            saving.setSalary(worker.getSalary());
            saving.setPersonCode(worker.getPersonCode());
            saving.setEmail(worker.getEmail());
            saving.setFirstName(worker.getFirstName());
            saving.setLastName(worker.getLastName());
            saving.setPassword(worker.getPassword());

            entityManager.persist(saving);
            entityManager.getTransaction().commit();
            return saving;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean delete(Worker worker){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            Worker deleting = entityManager.find(Worker.class, worker.getId());
            if (!Objects.isNull(deleting)){
                entityManager.remove(deleting);
                entityManager.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Worker> findAll(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select w from Worker w";
            return entityManager.createQuery(jpql, Worker.class).getResultList();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Worker get(long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();
            return entityManager.find(Worker.class, id);
        }catch (Exception e){
            return null;
        }
    }

    public Worker login(String username, String password){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select w from Worker w where w.email = :username and w.password = :pass";
            return entityManager.createQuery(jpql, Worker.class)
                    .setParameter("username", username)
                    .setParameter("pass", password)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
