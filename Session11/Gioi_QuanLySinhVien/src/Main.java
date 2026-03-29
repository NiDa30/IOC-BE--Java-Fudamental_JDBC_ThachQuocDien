public class Main {
    public static void main(String[] args) {
        // Create an array to store employees
        Employee[] employees = new Employee[3];

        // Populate the array with both FullTime and PartTime employees
        employees[0] = new FullTimeEmployee(1, "Nguyen Van A", 15000000); // basic salary: 15,000,000
        employees[1] = new PartTimeEmployee(2, "Tran Thi B", 120);        // working hours: 120
        employees[2] = new FullTimeEmployee(3, "Le Van C", 10000000); // basic salary: 10,000,000

        // Iterate through the array to display info, salary, and bonus (if applicable)
        for (Employee emp : employees) {
            System.out.println("-------------------------");
            emp.showInfo();
            
            // Print salary
            System.out.printf("Lương: %,.0f VND%n", emp.calculateSalary());

            // Check if employee is eligible for a bonus
            if (emp instanceof BonusEligible) {
                BonusEligible eligibleEmp = (BonusEligible) emp;
                System.out.printf("Thưởng: %,.0f VND%n", eligibleEmp.calculateBonus());
            }
        }
        System.out.println("-------------------------");
    }
}