package keywords;

public class LoginPageObject extends BasePage {

    String browserName;

    public LoginPageObject(String browserName) {
        // Gọi qua constructor của lớp Cha ( ở đây là BasePage )
        super(browserName);

    }


//    public static void main(String[] args) {
//        LoginPageObject objectA = new LoginPageObject();
//        objectA.clickToElement();
//    }

    @Override
    public boolean isPageDisplay() {
        return false;
    }
}
