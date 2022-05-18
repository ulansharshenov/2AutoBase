import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final GsonBuilder GSON_BUILDER = new GsonBuilder();
    public static final Gson GSON = GSON_BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./car.json");
    public static final Path WRITE_PATH_DRIVER = Paths.get("./driver.json");

    public static void main(String[] args) {

        List<Car> cars = new ArrayList<>();
        cars.add(Car.addCar(1, "Renault Magnum", "", State.ON_BASE));
        cars.add(Car.addCar(2, "Volvo FH12", "", State.ON_BASE));
        cars.add(Car.addCar(3, "DAF XF", "", State.ON_BASE));

        List<Driver> drivers = new ArrayList<>();
        drivers.add(Driver.addDriver("","Sasha",""));
        drivers.add(Driver.addDriver("","Petya",""));
        drivers.add(Driver.addDriver("","Vasya",""));

        String jsonCar1 = GSON.toJson(cars);
        writeCar(jsonCar1);
        printCarTable(cars);

        System.out.println();
        System.out.println("********************************************************************************");

        String jsonDriver = GSON.toJson(drivers);
        writeDriver(jsonDriver);
        printDriverTable(drivers);

        String jsonCar2 = GSON.toJson(cars);
        writeCar(jsonCar2);
//        System.out.println(readFile());
        printTable2(cars);

        System.out.println();
        System.out.println("********************************************************************************");

        String jsonDriver2 = GSON.toJson(drivers);
        writeDriver(jsonDriver2);
//        System.out.println(readFile());
        printDriverTable2(drivers);

        System.out.println();
        System.out.println("********************************************************************************");

        Service service = new Service();
        Scanner scanner = new Scanner(System.in);

        for (;true;) {
            System.out.println("""
                    Сменить водителя нажмите 1
                    Отправить на маршрут нажмите 2
                    Отправить на ремонт нажмите 3
                    Чтобы выйти нажмите 0""");
            int input = scanner.nextInt();
            if (input == 1) {
                try{
                    System.out.println("Выберите водителя ");
                    int inputId = scanner.nextInt();
                    service.changeDriver(drivers.get(inputId - 1), cars.get(inputId - 1));
                    printTable2(cars);
                    printDriverTable(drivers);
                }
                catch (DriverException e) {
                    System.err.println(e.getMessage());
                }

            }
            if (input == 2) {
                try {
                    System.out.println("Выберите машину");
                    int inputId = scanner.nextInt();
                    service.startDriving(drivers.get(inputId - 1), cars.get(inputId - 1));
                    System.out.println(cars.get(inputId - 1));
                    printTable2(cars);
                    printDriverTable(drivers);
                }
                catch (CarException e) {
                    System.err.println(e.getMessage());
                }
            }
            if (input == 3) {
                try{
                    System.out.println("Выберите машину ");
                    int inputId = scanner.nextInt();
                    service.startRepair(drivers.get(inputId - 1), cars.get(inputId - 1));
                    System.out.println(cars.get(inputId - 1));
                    printTable2(cars);
                    printDriverTable(drivers);
                }
                catch (CarException e) {
                    System.err.println(e.getMessage());
                }
            }
            if (input == 0) {
                System.out.println("Досвидание!");
                break;
            }
        }
    }
    public static void printCarTable(List<Car> cars) {
        System.out.println();
        System.out.println("-----------  1 ETAP  -----------");
        System.out.println("   #   |  Bus                |  Driver            |  State         ");
        System.out.println("-------+---------------------+--------------------+----------------");
        int counter = 1, tabsBus = 20, tabsDriver = 18, tabsState = 14;
        for(Car i: cars) {
            System.out.print("   " + counter + "   | ");
            System.out.print(i.getName());
            for(int j = 0; j < tabsBus - i.getName().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|  " + i.getDriver());
            for(int j = 0; j < tabsDriver; j++) {
                System.out.print(" ");
            }
            System.out.print("|  ");
            System.out.print(i.getState());
            for(int j = 0; j < tabsState - i.getState().toString().length(); j++) {
                System.out.print(" ");
            }
            counter++;
            System.out.println();
        }
    }

    public static void printDriverTable(List<Driver> drivers) {
        System.out.println();
        System.out.println("-----------  2 ETAP  -----------");
        System.out.println("   #   |  Driver             |  Bus               ");
        System.out.println("-------+---------------------+--------------------");
        int counter = 1, tabsDriver = 20, tabsBus = 18;
        for(Driver i: drivers) {
            System.out.print("   " + counter + "   | ");
            System.out.print(i.getName());
            for(int j = 0; j < tabsDriver - i.getName().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|  " + i.getCar());
            for(int j = 0; j < tabsBus; j++) {
                System.out.print(" ");
            }
            counter++;
            System.out.println();
        }
    }

    public static void printDriverTable2(List<Driver> drivers) {
        System.out.println();
        System.out.println("-----------  DRIVER  -----------");
        System.out.println("   #   |  Driver             |  Bus               ");
        System.out.println("-------+---------------------+--------------------");
        int counter = 1, tabsDriver = 20, tabsBus = 18;
        for(Driver i: drivers) {
            System.out.print("   " + counter + "   | ");
            System.out.print(i.getName());
            for(int j = 0; j < tabsDriver - i.getName().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|  " + i.getCar());
            for(int j = 0; j < tabsDriver - i.getName().length(); j++) {
                System.out.print(" ");
            }
            counter++;
            System.out.println();
        }
    }

    public static void printTable2(List<Car> cars) {
        System.out.println();
        System.out.println("-----------  CAR  -----------");
        System.out.println("   #   |  Bus                |  Driver            |  State         ");
        System.out.println("-------+---------------------+--------------------+----------------");
        int counter = 1, tabsBus = 20, tabsDriver = 18, tabsState = 14;
        for(Car i: cars) {
            System.out.print("   " + counter + "   | ");
            System.out.print(i.getName());
            for(int j = 0; j < tabsBus - i.getName().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|  " + i.getDriver());
            for(int j = 0; j < tabsDriver - i.getDriver().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|  ");
            System.out.print(i.getState());
            for(int j = 0; j < tabsState - i.getState().toString().length(); j++) {
                System.out.print(" ");
            }
            counter++;
            System.out.println();
        }
    }
    private static void writeCar(String object){
        Path writeCar = Paths.get(String.valueOf(WRITE_PATH));
        try{
            Files.writeString(writeCar, object, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void writeDriver(String object){
        Path writeDriver = Paths.get(String.valueOf(WRITE_PATH_DRIVER));
        try{
            Files.writeString(writeDriver, object, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String readFile() {
        String json = "";
        try {
            FileReader fileReader = new FileReader(String.valueOf(WRITE_PATH));
            int temp;
            while ((temp = fileReader.read()) != -1) {
                json += (char) temp;
            }
            return json;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return json;
    }
}