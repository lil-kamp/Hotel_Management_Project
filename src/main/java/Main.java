import model.Hotel;
import model.enums.HotelStatus;
import model.users.Manager;
import service.HotelService;
import service.ManagerService;
import view.manager.RoomsGrid;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        if (Objects.isNull(HotelService.getInstance().find())){
            Manager manager = ManagerService.getInstance().save(new Manager(0L, "reza", "rezai", "manager@gmail.com", "123456", "11"));
            HotelService.getInstance().save(new Hotel(0L, manager, 0D, HotelStatus.OPEN));
        }
        new RoomsGrid();
    }
}
