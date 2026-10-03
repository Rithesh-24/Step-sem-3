public class FitZoneMembershipDesk {

    interface MembershipPlan {
        double calculateFee();
        String getName();
    }

    static class MonthlyPlan implements MembershipPlan {

        public double calculateFee() {
            return 1000;
        }

        public String getName() {
            return "Monthly";
        }
    }

    static class QuarterlyPlan implements MembershipPlan {

        public double calculateFee() {
            return 1000 * 3 * 0.90;
        }

        public String getName() {
            return "Quarterly";
        }
    }

    static class AnnualPlan implements MembershipPlan {

        public double calculateFee() {
            return 1000 * 12 * 0.75;
        }

        public String getName() {
            return "Annual";
        }
    }

    static class Member {
        String name;

        Member(String name) {
            this.name = name;
        }
    }

    static class Membership {

        Member member;
        MembershipPlan plan;
        String status;

        Membership(Member member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
            this.status = "Active";
        }

        void checkIn() {
            if (status.equals("Active")) {
                System.out.println(
                    member.name + " checked in successfully."
                );
            } else {
                System.out.println(
                    "Check-in denied: " +
                    member.name +
                    "'s membership is " +
                    status + "."
                );
            }
        }

        void freeze() {

            if (status.equals("Expired")) {
                System.out.println(
                    "Cannot freeze an Expired membership."
                );
                return;
            }

            if (status.equals("Frozen")) {
                System.out.println(
                    "Membership is already Frozen."
                );
                return;
            }

            status = "Frozen";

            System.out.println(
                member.name + "'s membership frozen."
            );

            System.out.println("Status: Frozen.");
        }

        void unfreeze() {

            if (status.equals("Expired")) {
                System.out.println(
                    "Cannot unfreeze an Expired membership."
                );
                return;
            }

            status = "Active";

            System.out.println(
                member.name + "'s membership unfrozen."
            );
        }

        void expire() {
            status = "Expired";

            System.out.println(
                member.name + "'s membership expired."
            );

            System.out.println("Status: Expired.");
        }
    }

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership =
            new Membership(asha, new QuarterlyPlan());

        Membership raviMembership =
            new Membership(ravi, new MonthlyPlan());

        System.out.printf(
            "Quarterly membership created for Asha. Fee: ₹%.2f. Status: Active.%n",
            ashaMembership.plan.calculateFee()
        );

        System.out.printf(
            "Monthly membership created for Ravi. Fee: ₹%.2f. Status: Active.%n",
            raviMembership.plan.calculateFee()
        );

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}