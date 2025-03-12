package pratice;

public class EmployeeRY {

    public void showFulltimeKennyG() {
        System.out.println("Name = Kenny G");
        System.out.println("Position = Developer");
        System.out.println("Type = Fulltime");
        System.out.println("Salary = 2000");
    }
    public void showParttimeVintG() {
        System.out.println("Name = VintG");
        System.out.println("Position = QA");
        System.out.println("Type = Parttime");
        System.out.println("Rate = 50");
    }

    public static void main(String[] args) {
        EmployeeRY firtsEmploy = new EmployeeRY();
        firtsEmploy.showFulltimeKennyG();

        EmployeeRY secondEmploy = new EmployeeRY();
        secondEmploy.showParttimeVintG();
    }

}
