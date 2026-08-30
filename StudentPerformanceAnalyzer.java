-import java.util.Scanner;

class Student {
    private int registerNumber;
    private String name;
    private int[] marks = new int[5];

    public Student(int registerNumber, String name, int[] marks) {
        this.registerNumber = registerNumber;
        this.name = name;
        this.marks = marks;
    }
    public int calculateTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }
    public double calculateAverage() {
        return calculateTotal() / 5.0;
    }

    public int getHighestMark() {
        int highest = marks[0];
        for (int mark : marks) {
            if (mark > highest) {
                highest = mark;
            }
        }
        return highest;
    }

    public int getLowestMark() {
        int lowest = marks[0];
        for (int mark : marks) {
            if (mark < lowest) {
                lowest = mark;
            }
        }
        return lowest;
    }
    public char calculateGrade() {
        double avg = calculateAverage();
        if (avg >= 90) {
            return 'A';
        } else if (avg >= 80) {
            return 'B';
        } else if (avg >= 70) {
            return 'C';
        } else if (avg >= 60) {
            return 'D';
        } else if (avg >= 50) {
            return 'E';
        } else {
            return 'F';
        }
    }

    
    public String getStatus() {
        for (int mark : marks) {
            if (mark < 40) {
                return "FAIL";
            }
        }
        return "PASS";
    }
    public void displayReport() {
        System.out.println("Register Number: " + registerNumber);
        System.out.println("Name: " + name);
        System.out.println("Total: " + calculateTotal());
        System.out.printf("Average: %.2f\n", calculateAverage());
        System.out.println("Highest mark: " + getHighestMark());
        System.out.println("Lowest mark: " + getLowestMark());
        System.out.println("Grade: " + calculateGrade());
        System.out.println("Status: " + getStatus());
    }
}

public class StudentPerformanceAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int registerNumber = scanner.nextInt();
            String name = scanner.next();
            int[] marks = new int[5];
            
            for (int i = 0; i < 5; i++) {
                marks[i] = scanner.nextInt();
            }

            Student student = new Student(registerNumber, name, marks);
            student.displayReport();
        }
        
        scanner.close();
    }
}