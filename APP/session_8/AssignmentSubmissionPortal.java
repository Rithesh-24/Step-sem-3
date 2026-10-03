import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AssignmentSubmissionPortal {

    interface AssignmentType {
        String getName();
        double applyPenalty(double marks, long lateDays);
    }

    static class CodingAssignment implements AssignmentType {

        public String getName() {
            return "Coding";
        }

        public double applyPenalty(double marks, long lateDays) {
            return marks * Math.max(0, 1 - (lateDays * 0.10));
        }
    }

    static class WrittenAssignment implements AssignmentType {

        public String getName() {
            return "Written";
        }

        public double applyPenalty(double marks, long lateDays) {
            return marks * Math.max(0, 1 - (lateDays * 0.20));
        }
    }

    static class Student {
        String name;

        Student(String name) {
            this.name = name;
        }
    }

    static class Assignment {
        String title;
        double maxMarks;
        LocalDate dueDate;
        AssignmentType type;

        Assignment(String title, double maxMarks,
                   LocalDate dueDate, AssignmentType type) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
            this.type = type;
        }
    }

    static class Submission {
        Student student;
        Assignment assignment;
        LocalDate submissionDate;
        String status = "Submitted";

        Submission(Student student, Assignment assignment,
                   LocalDate submissionDate) {
            this.student = student;
            this.assignment = assignment;
            this.submissionDate = submissionDate;
        }

        long getLateDays() {
            long days = ChronoUnit.DAYS.between(
                assignment.dueDate, submissionDate
            );

            return Math.max(0, days);
        }

        void grade(double marks) {

            if (!status.equals("Submitted")) {
                System.out.println(
                    "Cannot grade: submission already graded."
                );
                return;
            }

            long lateDays = getLateDays();
            double finalMarks =
                assignment.type.applyPenalty(marks, lateDays);

            status = "Graded";

            if (lateDays == 0) {
                System.out.printf(
                    "%s graded: %.0f/%.0f.%n",
                    student.name,
                    finalMarks,
                    assignment.maxMarks
                );
            } else {
                double penalty = lateDays *
                                 (assignment.type instanceof CodingAssignment
                                  ? 10 : 20);

                System.out.printf(
                    "%s graded: %.0f/%.0f after %.0f%% late penalty.%n",
                    student.name,
                    finalMarks,
                    assignment.maxMarks,
                    penalty
                );
            }

            System.out.println("Status: Graded.");
        }

        void resubmit(LocalDate date) {

            if (status.equals("Graded")) {
                System.out.println(
                    "Cannot resubmit: '" +
                    assignment.title +
                    "' has already been graded."
                );
            } else {
                submissionDate = date;
                System.out.println("Submission updated.");
            }
        }
    }

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new Assignment(
            "Linked List Lab",
            50,
            LocalDate.of(2026, 3, 10),
            new CodingAssignment()
        );

        Assignment written = new Assignment(
            "Design Essay",
            50,
            LocalDate.of(2026, 3, 12),
            new WrittenAssignment()
        );

        Submission s1 = new Submission(
            asha,
            coding,
            LocalDate.of(2026, 3, 10)
        );

        Submission s2 = new Submission(
            ravi,
            written,
            LocalDate.of(2026, 3, 14)
        );

        System.out.println(
            "Asha's submission for 'Linked List Lab' received (on time)."
        );
        System.out.println("Status: Submitted.");

        System.out.println(
            "Ravi's submission for 'Design Essay' received (2 days late)."
        );
        System.out.println("Status: Submitted.");

        s1.grade(45);
        s2.grade(40);

        s1.resubmit(LocalDate.of(2026, 3, 11));
    }
}