package nivell3;

import java.util.Objects;

public class Seat {
    private int row;
    private int seat;
    private String personName;

    public Seat(int row, int seat, String personName) {
        this.row = row;
        this.seat = seat;
        this.personName = personName;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public String getPersonName() {
        return personName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Seat other = (Seat) obj;
        return row == other.row && seat == other.seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, seat);
    }

    @Override
    public String toString() {
        return "Row " + row + ", Seat " + seat
                + " - Reserved by: " + personName;
    }
}
