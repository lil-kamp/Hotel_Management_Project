package service;

import model.Room;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.EntityManager;
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
}
