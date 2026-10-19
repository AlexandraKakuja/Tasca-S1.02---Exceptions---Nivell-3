package nivell3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = readPositiveInteger(scanner, "Enter the number of rows: ");
        int seatsPerRow = readPositiveInteger(
                scanner, "Enter the number of seats per row: "
        );

        ReservationService service =
                new ReservationService(rows, seatsPerRow);

        ConsoleUI ui = new ConsoleUI(service);
        ui.start();
    }

    private static int readPositiveInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

                System.out.println("Enter a number greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}
