public class Car {
    private int id;
    private String name;
    private String driver;
    private State state;
    public Car() {
    }

    public Car(int id, String name, String driver, State state) {
        this.id = id;
        this.name = name;
        this.driver = driver;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    public static Car addCar(int id, String name, String driver, State state){
        Car car = new Car();
        car.id = id;
        car.name = name;
        car.driver = driver;
        car.state = state;
        return car;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", driver='" + driver + '\'' +
                ", state=" + state +
                '}';
    }
}
