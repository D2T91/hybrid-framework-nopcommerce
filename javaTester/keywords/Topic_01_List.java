package keywords;

public class Topic_01_List {
    public static void main(String[] args) {

        // Biểu thức điều kiện
        // if, if-else, if-eles-if
        // switch-case
        if (3 > 5) {
            System.out.println("In ra cai nay");
        }

        if (3 > 5) {
            System.out.println("IF...");
        } else {
            System.out.println("Else...");
        }

        //  If-Else cho phép trùng lặp khi đưa giá trị vào
        String browserName = "Chrome";
        if (browserName.equalsIgnoreCase("Chrome")) {
            System.out.println("In ra Chrome");
        } else if (browserName.equalsIgnoreCase("Edge")) {
            // actions
        }  else {
            // actions
        }

        // KO cho phép trùng lặp giá trị đầu vào
        switch (browserName) {
            case "Chrome" :
                System.out.println("Chrome Browser");
                break;
            case "Firefox" :
                System.out.println("Firefox Browser");
                break;
            case "Edge" :
                System.out.println("Edge Browser");
                break;
            default :      // Trình duyệt còn lại
                System.out.println("");
                break;
        }

        // Biểu thức vòng lặp
        // for
        for (int i = 0; i <= 5; i++) {
            System.out.println(i);
        }

        // while
        int x = 0;
        while (x < 5) {
            System.out.println(x);
            x++;
        }

        // do-while
        x = 5;
        do {
            System.out.println(x);
            x++;
        } while (x < 5);

        // Kiểu dữ liệu Nguyên thủy
//        char c = 'c';
//        byte;
//        short;
//        int;
//        long;
//        float;
//        double;
//        boolean;

    }
}
