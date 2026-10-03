public class MembershipTiers {

    static class GymMember {

        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(
                String memberId,
                int monthlyFee) {

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        public void attendSession() {
            sessionsAttended++;
        }

        public int getSessionsAttended() {
            return sessionsAttended;
        }

        public void displayInfo() {

            System.out.println(
                    "Standard Member | Sessions: "
                    + sessionsAttended
            );
        }
    }


    static class PremiumMember
            extends GymMember {

        protected String trainerName;

        public PremiumMember(
                String memberId,
                int monthlyFee,
                String trainerName) {

            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        public void displayInfo() {

            System.out.println(
                    "Premium Member | Trainer: "
                    + trainerName
                    + " | Sessions: "
                    + sessionsAttended
            );
        }
    }


    static class EliteMember
            extends PremiumMember {

        private String lockerNumber;

        public EliteMember(
                String memberId,
                int monthlyFee,
                String trainerName,
                String lockerNumber) {

            super(
                    memberId,
                    monthlyFee,
                    trainerName
            );

            this.lockerNumber = lockerNumber;
        }

        @Override
        public void displayInfo() {

            System.out.println(
                    "Elite Member | Trainer: "
                    + trainerName
                    + " | Locker: "
                    + lockerNumber
                    + " | Sessions: "
                    + sessionsAttended
            );
        }
    }


    static class GroupClassMember
            extends GymMember {

        private String className;

        public GroupClassMember(
                String memberId,
                int monthlyFee,
                String className) {

            super(memberId, monthlyFee);
            this.className = className;
        }

        @Override
        public void displayInfo() {

            System.out.println(
                    "Group Class Member | Class: "
                    + className
                    + " | Sessions: "
                    + sessionsAttended
            );
        }
    }


    static String classifyGeneration(
            GymMember member) {

        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof PremiumMember) {
            return "Second generation";
        }

        return "Standard Member";
    }


    static int getTotalSessionsAttended(
            GymMember[] members) {

        int total = 0;

        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }

        return total;
    }


    public static void main(String[] args) {

        PremiumMember premium =
                new PremiumMember(
                        "MEM2",
                        2000,
                        "Coach Riya"
                );

        EliteMember elite =
                new EliteMember(
                        "MEM3",
                        3000,
                        "Coach Arjun",
                        "L12"
                );

        GroupClassMember group =
                new GroupClassMember(
                        "MEM4",
                        1500,
                        "Zumba"
                );

        premium.attendSession();
        premium.attendSession();
        premium.attendSession();

        elite.attendSession();
        elite.attendSession();

        group.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();

        premium.displayInfo();
        elite.displayInfo();
        group.displayInfo();

        System.out.println(
                classifyGeneration(elite)
        );

        System.out.println(
                classifyGeneration(group)
        );

        System.out.println(
                getTotalSessionsAttended(
                        new GymMember[]{
                                premium,
                                elite,
                                group
                        }
                )
        );
    }
}