import java.util.*;

public class CampusNoticeBroadcaster {

    interface NotificationChannel {
        void send(Student student, String message);
    }

    static class EmailChannel implements NotificationChannel {

        public void send(Student student, String message) {
            System.out.println(
                "[Email → " + student.name + "] " + message
            );
        }
    }

    static class SmsChannel implements NotificationChannel {

        public void send(Student student, String message) {
            System.out.println(
                "[SMS → " + student.name + "] " + message
            );
        }
    }

    static class AppChannel implements NotificationChannel {

        public void send(Student student, String message) {
            System.out.println(
                "[App → " + student.name + "] " + message
            );
        }
    }

    static class Student {
        String name;
        String department;
        List<NotificationChannel> channels = new ArrayList<>();

        Student(String name, String department) {
            this.name = name;
            this.department = department;
        }

        void addChannel(NotificationChannel channel) {
            channels.add(channel);
        }
    }

    static class Notice {
        String title;
        Set<String> departments;

        Notice(String title, String... departments) {

            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException(
                    "Notice title is required."
                );
            }

            if (departments.length == 0) {
                throw new IllegalArgumentException(
                    "At least one target department is required."
                );
            }

            this.title = title;
            this.departments =
                new HashSet<>(Arrays.asList(departments));
        }
    }

    static class NoticeBoard {

        List<Student> students = new ArrayList<>();

        void addStudent(Student student) {
            students.add(student);
        }

        void post(Notice notice) {

            System.out.print(
                "Notice '" + notice.title +
                "' posted to "
            );

            int count = 0;

            for (String dept : notice.departments) {
                if (count > 0) {
                    System.out.print(", ");
                }

                System.out.print(dept);
                count++;
            }

            System.out.println(".");

            for (Student student : students) {

                if (notice.departments.contains(
                        student.department)) {

                    for (NotificationChannel channel :
                         student.channels) {

                        channel.send(
                            student,
                            notice.title
                        );
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        NoticeBoard board = new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        try {
            board.post(
                new Notice(
                    "Lab Closed Tomorrow",
                    "CSE"
                )
            );

            board.post(
                new Notice(
                    "Fee Deadline Extended",
                    "CSE",
                    "ECE"
                )
            );

            board.post(
                new Notice("Sports Day")
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                "Cannot post notice: " + e.getMessage()
            );
        }
    }
}