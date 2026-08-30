package exam;

public import java.util.Scanner;

class Passenger {
    int passengerId;
    String name;
    int age;
    int ticketType;

    Passenger(int passengerId, String name, int age, int ticketType) {
        this.passengerId = passengerId;
        this.name = name;
        this.age = age;
        this.ticketType = ticketType;
    }

    double calculateFare() {
        double fare;

        // Ticket types
        if (ticketType == 1) {
            fare = 500;       // Sleeper
        } else if (ticketType == 2) {
            fare = 1200;      // AC
        } else if (ticketType == 3) {
            fare = 2000;      // First Class
        } else {
            fare = 0;
        }

        // Discount rules
        if (age < 5) {
            return 0;
        } else if (age >= 60) {
            return fare * 0.70;   // 30% discount
        } else {
            return fare;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Passenger[] passengers = new Passenger[n];

        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            String name = sc.next();
            int age = sc.nextInt();
            int ticketType = sc.nextInt();

            passengers[i] = new Passenger(id, name, age, ticketType);
        }

        for (Passenger p : passengers) {
            double fare = p.calculateFare();

            System.out.println(p.passengerId + " " + p.name + " $" + 
                               (int)fare);
        }

        sc.close();
    }
} {
    
}
