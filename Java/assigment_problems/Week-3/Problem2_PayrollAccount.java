class PayrollAccount {
    private double basicSalary;
    private double bonus;

    public PayrollAccount(double salary) {
        if (salary < 0) {
            System.out.println("Invalid salary. Starting with 0.");
            basicSalary = 0;
        } else {
            basicSalary = salary;
        }
        bonus = 0;
    }

    public void creditBonus(double amount) {
        if (amount <= 0)
            System.out.println("Invalid bonus.");
        else {
            bonus += amount;
            System.out.println("Bonus credited: Rs " + amount);
        }
    }

    public void deductTax(double percent) {
        if (percent < 0 || percent > 100)
            System.out.println("Invalid tax percentage.");
        else {
            basicSalary -= basicSalary * percent / 100;
            System.out.println("Tax deducted: " + percent + "%");
        }
    }

    public double getNetSalary() {
        return basicSalary + bonus;
    }
}

public class Problem2_PayrollAccount {
    public static void main(String[] args) {
        PayrollAccount p = new PayrollAccount(50000);

        p.creditBonus(5000);
        p.deductTax(10);

        System.out.println("Net salary: Rs " + p.getNetSalary());
    }
}