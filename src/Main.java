import core.Commands;
import core.ParkingLot;
import models.Car;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ParkingLot parkingLot = null;

        System.out.println("Hello, Welcome to parking lot.");
        System.out.println("Please, input your command - ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        /*
        * Wait for a valid command
        * */
        while ((input == null || input.isEmpty())) {

            System.out.println("Invalid Command --- ");
            System.out.println("Please, input your command.");

            input = scanner.nextLine();
        }

        while (!(input.equalsIgnoreCase(Commands.QUIT))) {

            String[] parts = input.split(" ");

        /*
        * The first index contains the command
        * */

            String command = parts[0];

            if (parkingLot == null && !command.equalsIgnoreCase(Commands.CREATE)) {
                System.err.println("Please, create a parking lot first.");
                break; //Quit
            }

            switch (command) {
                case Commands.CREATE:

                    int n = 0;
                    try {
                        n = Integer.parseInt(parts[1]);
                    }catch (NumberFormatException ex) {
                        System.err.println("Error: Invalid input - not an integer value - ");
                    }

                    parkingLot = new ParkingLot(n);
                    System.out.println("Created a parking lot with " + n + " slots");
                    break;
                case Commands.PARK:

                    Car car = new Car();
                    car.registrationNumber = parts[1];
                    car.colour = parts[2];

                    String allocationResult = parkingLot.allocate(car);
                    System.out.println(allocationResult);
                    break;
                case Commands.LEAVE:

                    int slot = 0;
                    try {
                        slot = Integer.parseInt(parts[1]);
                    }catch (NumberFormatException ex) {
                        System.out.println("Error: Invalid slot number");
                    }

                    String response = parkingLot.leave(slot);
                    System.out.println(response);
                    break;
                case Commands.STATUS:
                    String status = parkingLot.status();
                    System.out.println(status);
                    break;
                case Commands.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                    String result = parkingLot.registrationNumberOfCarsWithColour(parts[1]);
                    System.out.println(result);
                    break;
                case Commands.SLOT_NUMBER_FOR_REGISTRATION_NUMBER:

                    String r = parkingLot.slotNumbersForRegistrationNumber(parts[1]);
                    System.out.println(r);
                    break;

                case Commands.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:

                    String res = parkingLot.slotNumbersOfCarsWithColour(parts[1]);
                    System.out.println(res);
                    break;
                default:
                    System.out.println("Unknown command => " + command);
                    break;
            }
            input = scanner.nextLine();
        }

        System.out.println("Thanks for using this system.");
    }
}