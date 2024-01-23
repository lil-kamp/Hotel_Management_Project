package service;

import model.Hotel;
import model.Room;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomService {

    private static RoomService roomService;
    private EntityManager entityManager;

    private RoomService(){
    }

    public static RoomService getInstance() {
        if (Objects.isNull(roomService)) {
            roomService = new RoomService();
        }
        return roomService;
    }

    public Room save(Room room){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            Room saving = entityManager.find(Room.class, room.getId());
            if (Objects.isNull(saving))
                saving = new Room();

            saving.setHotel(room.getHotel());
            saving.setBedCount(room.getBedCount());
            saving.setPrice(room.getPrice());
            saving.setAllowed(room.getAllowed());

            entityManager.persist(saving);
            entityManager.getTransaction().commit();
            return saving;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Room> findAll(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select r from Room r";
            return entityManager.createQuery(jpql, Room.class).getResultList();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public List<Room> findAllAllowed(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            String jpql = "select r from Room r where r.allowed = true";
            return entityManager.createQuery(jpql, Room.class).getResultList();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Room get(long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();
            return entityManager.find(Room.class, id);
        }catch (Exception e){
            return null;
        }
    }
}
