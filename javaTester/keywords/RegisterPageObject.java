package keywords;

public class RegisterPageObject implements IBrowser {

    String browserName;

    // HẰNG SỐ
    static final String ENVIRONMENT = "DEV";

    // Thuộc tính static có thể lấy ra với phạm vị Class
    static String browserVersion;


    @Override
    public void clickToElement() {

    }

    // phương thức static có thể lấy ra với phạm vị Class
    public static void selectToElement() {

    }

//    public static void main(String[] args) {
//
//        // Các thuộc tính và phương thức static có thể truy cập từ phạm vi Class
//        RegisterPageObject.browserVersion;
//        RegisterPageObject.selectToElement();
//    }
}
