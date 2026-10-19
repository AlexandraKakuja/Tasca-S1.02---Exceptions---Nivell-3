package nivell3;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private int totalRows;
    private int seatsPerRow;
    private List<Seat> seats;

    public ReservationService(int totalRows, int seatsPerRow) {
        if (totalRows <= 0 || seatsPerRow <= 0) {
            throw new InvalidSeatException(
                    "The number of rows and seats must be greater than zero."
            );
        }

        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
        this.seats = new ArrayList<>();
    }

    public void reserveSeat(int row, int seat, String name) {
        validateSeatPosition(row, seat);
        validatePersonName(name);

        for (Seat reservedSeat : seats) {
            if (reservedSeat.getRow() == row
                    && reservedSeat.getSeat() == seat) {
                throw new SeatAlreadyTakenException(
                        "This seat is already reserved."
                );
            }
        }

        seats.add(new Seat(row, seat, name.trim()));
    }

    public void cancelSeat(int row, int seat) {
        validateSeatPosition(row, seat);

        for (Seat reservedSeat : seats) {
            if (reservedSeat.getRow() == row
                    && reservedSeat.getSeat() == seat) {
                seats.remove(reservedSeat);
                return;
            }
        }

        throw new SeatAlreadyEmptyException(
                "This seat is not reserved."
        );
    }

    public void cancelAllByPerson(String name) {
        validatePersonName(name);

        boolean removed = seats.removeIf(
                reservedSeat -> reservedSeat.getPersonName()
                        .equalsIgnoreCase(name.trim())
        );

        if (!removed) {
            throw new SeatAlreadyEmptyException(
                    "This person has no reservations."
            );
        }
    }

    public List<Seat> getAllSeats() {
        return new ArrayList<>(seats);
    }

    public List<Seat> getSeatsByPerson(String name) {
        validatePersonName(name);

        List<Seat> personSeats = new ArrayList<>();

        for (Seat reservedSeat : seats) {
            if (reservedSeat.getPersonName()
                    .equalsIgnoreCase(name.trim())) {
                personSeats.add(reservedSeat);
            }
        }

        return personSeats;
    }

    private void validateSeatPosition(int row, int seat) {
        if (row < 1 || row > totalRows
                || seat < 1 || seat > seatsPerRow) {
            throw new InvalidSeatException(
                    "Invalid seat position. Row must be between 1 and "
                            + totalRows + ", and seat must be between 1 and "
                            + seatsPerRow + "."
            );
        }
    }

    private void validatePersonName(String name) {
        if (name == null || name.trim().isEmpty()
                || name.matches(".*\\d.*")) {
            throw new InvalidPersonNameException(
                    "The name must not be empty or contain numbers."
            );
        }
    }
}
