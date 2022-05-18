public class Driver {
    private String id;
    private String name;
    private String car;
    public Driver() {
    }

    public Driver(String id, String name, String car) {
        this.id = id;
        this.name = name;
        this.car = car;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public static Driver addDriver(String id, String name, String car){
        Driver driver = new Driver();
        driver.id = id;
        driver.name = name;
        driver.car = car;
        return driver;
    }
}
