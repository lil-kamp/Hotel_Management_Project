package service;

import model.Hotel;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Objects;

public class HotelService {

    private static HotelService hotelService;
    private EntityManager entityManager;

    private HotelService(){
    }

    public static HotelService getInstance() {
        if (Objects.isNull(hotelService)) {
            hotelService = new HotelService();
        }
        return hotelService;
    }

    public Hotel save(Hotel hotel){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entityManager = session.getEntityManagerFactory().createEntityManager();

            entityManager.getTransaction().begin();
            Hotel saving = entityManager.find(Hotel.class, hotel.getId());
            if (Objects.isNull(saving))
                saving = new Hotel();

            saving.setManager(hotel.getManager());
            saving.setBalance(hotel.getBalance());
            saving.setStatus(hotel.getStatus());

            entityManager.persist(saving);
            entityManager.getTransaction().commit();
            return saving;
        } catch (Exception e) {
            return null;
        }
    }
}
