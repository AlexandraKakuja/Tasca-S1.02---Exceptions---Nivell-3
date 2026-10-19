package nivell3;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private ReservationService service;
    private Scanner scanner;

    public ConsoleUI(ReservationService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== CINEMA RESERVATIONS =====");
            System.out.println("1. Show all reserved seats");
            System.out.println("2. Show seats reserved by a person");
            System.out.println("3. Reserve a seat");
            System.out.println("4. Cancel a seat reservation");
            System.out.println("5. Cancel all reservations by a person");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1":
                        showAllSeats();
                        break;
                    case "2":
                        showSeatsByPerson();
                        break;
                    case "3":
                        reserveSeat();
                        break;
                    case "4":
                        cancelSeat();
                        break;
                    case "5":
                        cancelAllByPerson();
                        break;
                    case "0":
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showAllSeats() {
        List<Seat> seats = service.getAllSeats();

        if (seats.isEmpty()) {
            System.out.println("There are no reserved seats.");
            return;
        }

        for (Seat seat : seats) {
            System.out.println(seat);
        }
    }

    private void showSeatsByPerson() {
        System.out.print("Enter the person's name: ");
        String name = scanner.nextLine();

        List<Seat> seats = service.getSeatsByPerson(name);

        if (seats.isEmpty()) {
            System.out.println("This person has no reservations.");
            return;
        }

        for (Seat seat : seats) {
            System.out.println(seat);
        }
    }

    private void reserveSeat() {
        System.out.print("Enter the row number: ");
        int row = readInteger();

        System.out.print("Enter the seat number: ");
        int seat = readInteger();

        System.out.print("Enter the person's name: ");
        String name = scanner.nextLine();

        service.reserveSeat(row, seat, name);
        System.out.println("Seat reserved successfully!");
    }

    private void cancelSeat() {
        System.out.print("Enter the row number: ");
        int row = readInteger();

        System.out.print("Enter the seat number: ");
        int seat = readInteger();

        service.cancelSeat(row, seat);
        System.out.println("Reservation cancelled successfully!");
    }

    private void cancelAllByPerson() {
        System.out.print("Enter the person's name: ");
        String name = scanner.nextLine();

        service.cancelAllByPerson(name);
        System.out.println("All reservations cancelled successfully!");
    }

    private int readInteger() {
        while (true) {
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Enter a whole number: ");
            }
        }
    }
}
