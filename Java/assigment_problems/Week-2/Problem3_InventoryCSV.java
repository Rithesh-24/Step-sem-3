import java.util.Scanner;

public class Problem3_InventoryCSV {

    static void parseInventoryRecord(String csvLine) {
        String[] data = csvLine.split(",");

        if (data.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.println("Product: " + data[0]
                    + " | SKU: " + data[1]
                    + " | Qty: " + data[2]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter CSV record: ");
        String csv = sc.nextLine();

        parseInventoryRecord(csv);
    }
}