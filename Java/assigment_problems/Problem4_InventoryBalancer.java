import java.util.Scanner;

public class Problem4_InventoryBalancer {

    static void analyzeInventory(int[] sectionA, int[] sectionB) {

        int totalA = 0;
        int totalB = 0;
        int highest = sectionA[0];
        String section = "A";
        int index = 0;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];

            if (sectionA[i] > highest) {
                highest = sectionA[i];
                section = "A";
                index = i;
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];

            if (sectionB[i] > highest) {
                highest = sectionB[i];
                section = "B";
                index = i;
            }
        }

        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);

        if (totalA == totalB)
            System.out.println("Status: Balanced");
        else
            System.out.println("Status: Not Balanced");

        System.out.println("Highest Quantity: " + highest
                + " (Section " + section + ", Item " + (index + 1) + ")");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] A = {20, 15, 30};
        int[] B = {25, 10, 30};

        analyzeInventory(A, B);
    }
}