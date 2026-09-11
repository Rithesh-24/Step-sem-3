class Employee {
    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class Problem5_EmployeeStatic {
    public static void main(String[] args) {
        Employee e1 = new Employee("A", 50000);
        Employee e2 = new Employee("B", 60000);
        Employee e3 = new Employee("C", 70000);

        Employee.printCompanyInfo();
    }
}