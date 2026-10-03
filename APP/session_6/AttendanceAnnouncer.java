public class AttendanceAnnouncer {

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

        public String displayInfo() {

            return "Standard | Sessions: "
                    + sessionsAttended;
        }
    }


    static class PremiumMember
            extends GymMember {

        private String trainerName;

        public PremiumMember(
                String memberId,
                int monthlyFee,
                String trainerName) {

            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return trainerName;
        }

        @Override
        public String displayInfo() {

            return "Premium | Trainer: "
                    + trainerName
                    + " | Sessions: "
                    + sessionsAttended;
        }
    }


    static String batchPrint(
            GymMember[] members) {

        StringBuilder result =
                new StringBuilder();

        for (GymMember member : members) {

            result.append(
                    member.displayInfo()
            );

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) member;

                result.append(
                        " [Trainer via downcast: "
                                + premium.getTrainerName()
                                + "]"
                );
            }

            result.append(" | ");
        }

        return result.toString();
    }


    public static void main(String[] args) {

        GymMember standard =
                new GymMember(
                        "MEM6",
                        1000
                );

        PremiumMember premium =
                new PremiumMember(
                        "MEM7",
                        2000,
                        "Coach Riya"
                );

        System.out.println(
                batchPrint(
                        new GymMember[]{
                                standard,
                                premium
                        }
                )
        );
    }
}