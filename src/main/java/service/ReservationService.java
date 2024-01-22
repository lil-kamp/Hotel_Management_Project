package service;

import model.Reservation;
import model.enums.ReservationStatus;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

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
            saving.setRoom(reservation.getRoom());
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
}
