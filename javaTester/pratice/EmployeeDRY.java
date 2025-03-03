package pratice;

public class EmployeeDRY {
    private String employName;
    private String employPosition;
    private String employType;
    private String employSalary;
    private String employRate;

    public EmployeeDRY(String employName, String employPosition, String employType, String employSalary, String employRate) {
        this.employName = employName;
        this.employType = employType;
        this.employPosition = employPosition;
        this.employSalary = employSalary;
        this.employRate = employRate;
    }

    public void showEmployInfo() {
        System.out.println("Name = " + this.employName);
        System.out.println("Position = " + this.employPosition);
        System.out.println("Type = " + this.employType);
        if (this.employType.equalsIgnoreCase("Fulltime")) {
            System.out.println("Salary = " + this.employSalary);
        } else {
            System.out.println("Rate = " + this.employRate);
        }

    }

    public static void main(String[] args) {
        EmployeeDRY Kenny = new EmployeeDRY("Kenny","Developer",
                "Fulltime", "3000","");
        Kenny.showEmployInfo();

    }

}
