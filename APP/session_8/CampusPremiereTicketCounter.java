import java.util.*;

public class CampusPremiereTicketCounter {

    interface Seat {
        String getId();
        double getPrice();
    }

    static class RegularSeat implements Seat {
        String id;

        RegularSeat(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public double getPrice() {
            return 150;
        }
    }

    static class PremiumSeat implements Seat {
        String id;

        PremiumSeat(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public double getPrice() {
            return 250;
        }
    }

    static class ReclinerSeat implements Seat {
        String id;

        ReclinerSeat(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public double getPrice() {
            return 400;
        }
    }

    static class Customer {
        String name;

        Customer(String name) {
            this.name = name;
        }
    }

    static class Show {
        String time;
        boolean started;
        Set<String> bookedSeats = new HashSet<>();

        Show(String time) {
            this.time = time;
            started = false;
        }

        boolean isAvailable(Seat seat) {
            return !bookedSeats.contains(seat.getId());
        }

        Booking book(Customer customer, Seat... seats) {

            if (seats.length > 6) {
                System.out.println("Maximum 6 seats allowed.");
                return null;
            }

            for (Seat seat : seats) {
                if (!isAvailable(seat)) {
                    System.out.println(
                        "Seat " + seat.getId() +
                        " is already booked for this show."
                    );
                    return null;
                }
            }

            for (Seat seat : seats) {
                bookedSeats.add(seat.getId());
            }

            Booking booking = new Booking(customer, this, seats);

            System.out.print(
                "Booking confirmed for " + customer.name + ": "
            );

            for (Seat seat : seats) {
                System.out.print(seat.getId() + " ");
            }

            System.out.printf(
                ". Total: ₹%.2f%n",
                booking.getTotal()
            );

            return booking;
        }
    }

    static class Booking {
        Customer customer;
        Show show;
        List<Seat> seats;
        boolean cancelled;

        Booking(Customer customer, Show show, Seat[] seats) {
            this.customer = customer;
            this.show = show;
            this.seats = Arrays.asList(seats);
        }

        double getTotal() {
            double total = 0;

            for (Seat seat : seats) {
                total += seat.getPrice();
            }

            return total;
        }

        void cancel() {

            if (show.started) {
                System.out.println(
                    "Cannot cancel: show has already started."
                );
                return;
            }

            for (Seat seat : seats) {
                show.bookedSeats.remove(seat.getId());
            }

            cancelled = true;

            System.out.println(
                customer.name + "'s booking cancelled."
            );

            System.out.print("Seats released: ");

            for (Seat seat : seats) {
                System.out.print(seat.getId() + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM");

        Booking ashaBooking = show.book(
            asha,
            new RegularSeat("A1"),
            new RegularSeat("A2"),
            new PremiumSeat("F5")
        );

        show.book(
            ravi,
            new RegularSeat("A2")
        );

        show.book(
            ravi,
            new ReclinerSeat("R1")
        );

        ashaBooking.cancel();

        show.book(
            neha,
            new RegularSeat("A2")
        );
    }
}