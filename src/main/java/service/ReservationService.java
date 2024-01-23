package service;

import model.Reservation;
import model.Room;
import model.enums.ReservationStatus;
import model.users.Customer;
import model.users.Worker;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.*;

public class ReservationService {

    private static ReservationService reservationService;
    private EntityManager entityManager;

    private ReservationService(){
    }

    public static ReservationService getInstance() {
        if (Objects.isNull(reservationService)) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public Reservation save(Reservation reservation){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            Reservation saving = entityManager.find(Reservation.class, reservation.getId());
            if (Objects.isNull(saving))
                saving = new Reservation();

            saving.setDate(reservation.getDate());
            saving.setCustomer(reservation.getCustomer());
            saving.setPrice(reservation.getPrice());
            saving.setWorker(reservation.getWorker());
            saving.setDays(reservation.getDays());
            saving.setRoom(entityManager.find(Room.class, reservation.getRoom().getId()));
            saving.setStatus(reservation.getStatus());

            entityManager.persist(saving);
            entityManager.getTransaction().commit();
            return saving;
        } catch (Exception e) {
            return null;
        }
    }

    public PriorityQueue<Reservation> findAllByStatus(ReservationStatus status){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select r from Reservation r where r.status = :status";
            PriorityQueue<Reservation> queue = new PriorityQueue<>(10, Comparator.comparingLong(r -> r.getDate().getTime()));
            queue.addAll(entityManager.createQuery(jpql, Reservation.class)
                    .setParameter("status", status)
                    .getResultList());
            return queue;
        }catch (Exception e){
            return new PriorityQueue<>();
        }
    }

    public PriorityQueue<Reservation> findAllWaiting(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select r from Reservation r where r.status = :status1 or r.status = :status2";
            PriorityQueue<Reservation> queue = new PriorityQueue<>(10, Comparator.comparingLong(r -> r.getDate().getTime()));
            queue.addAll(entityManager.createQuery(jpql, Reservation.class)
                    .setParameter("status1", ReservationStatus.WAITING_BEFORE_RESERVE)
                    .setParameter("status2", ReservationStatus.WAITING_ENDED)
                    .getResultList());
            return queue;
        }catch (Exception e){
            return new PriorityQueue<>();
        }
    }

    public PriorityQueue<Reservation> findAllAcceptedByCustomer(Customer customer){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select r from Reservation r where r.status = :status1 or r.customer = :customer";
            PriorityQueue<Reservation> queue = new PriorityQueue<>(10, Comparator.comparingLong(r -> r.getDate().getTime()));
            queue.addAll(entityManager.createQuery(jpql, Reservation.class)
                    .setParameter("status1", ReservationStatus.ACCEPTED_RESERVE)
                    .setParameter("customer", entityManager.find(Customer.class, customer.getId()))
                    .getResultList());
            return queue;
        }catch (Exception e){
            return new PriorityQueue<>();
        }
    }

    public List<Reservation> findAll(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select r from Reservation r";
            return entityManager.createQuery(jpql, Reservation.class).getResultList();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Reservation get(long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();
            return entityManager.find(Reservation.class, id);
        }catch (Exception e){
            return null;
        }
    }
}
