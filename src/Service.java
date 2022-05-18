import java.util.Random;

public class Service {

    public static void changeDriver(Driver drivers, Car cars) {
        if(cars.getState().equals(State.ON_BASE)){
            cars.setDriver(drivers.getName());
            drivers.setCar(cars.getName());
            System.out.println("Готово... " + drivers.getName() + "Водитель грузовика" + cars.getName());        }
        if (cars.getState().equals(State.ON_ROAD)) {
            throw new DriverException("Водитель на дороге. Вы не можете изменить этого водителя");
        }
        if(cars.getState().equals(State.ON_SERVICE)) {
            throw new DriverException("Грузовик в ремонте. Вы не можете поменять водителя");
        }
    }

    public static void startDriving(Driver drivers, Car cars) {
        if(cars.getState().equals(State.ON_BASE) && cars.getDriver() != null) {
            cars.setState(State.ON_ROAD);
            cars.setDriver(drivers.getName());
            System.out.println("Грузовик в пути!");
        }
        else if(cars.getState().equals(State.ON_ROAD)) {
            throw new CarException("Грузовик в пути!");
        }
        else if(cars.getState().equals(State.ON_SERVICE)) {
            Random random = new Random();
            int a = random.nextInt(1,3);
            if(a == 1)
                cars.setState(State.ON_ROAD);
            else
                cars.setState(State.ON_BASE);
            System.out.println("Статус грузовика: " + cars.getState());
        }
    }

    public static void startRepair(Driver drivers, Car cars) {
        if(cars.getState().equals(State.ON_BASE) || cars.getState().equals(State.ON_ROAD)) {
            cars.setState(State.ON_SERVICE);
            System.out.println("Грузовик на ремонте");
        }
        else if(cars.getState().equals(State.ON_SERVICE))
            throw new CarException("Этот грузовик уже в ремонте!");
    }
}
