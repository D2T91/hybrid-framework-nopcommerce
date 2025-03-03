package pratice;

public class EmployeeRY {

    public void showFulltimeEmploy() {
        System.out.println("Name = Kenny G");
        System.out.println("Position = Developer");
        System.out.println("Type = Fulltime");
        System.out.println("Salary = 2000");
    }
    public void showParttimeEmploy() {
        System.out.println("Name = VintG");
        System.out.println("Position = QA");
        System.out.println("Type = Parttime");
        System.out.println("Rate = 50");
    }

    public static void main(String[] args) {
        EmployeeRY firtsEmploy = new EmployeeRY();
        firtsEmploy.showFulltimeEmploy();

        EmployeeRY secondEmploy = new EmployeeRY();
        secondEmploy.showParttimeEmploy();
    }

}
