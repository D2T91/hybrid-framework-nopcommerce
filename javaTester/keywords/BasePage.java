package keywords;

public abstract class BasePage {

    public BasePage(String browserName) {

    }

    // abstract Dùng để thể hiện tính chất trừu tượng trong OOP

    // Hàm abstract
    public abstract boolean isPageDisplay();

    // Hàm non-abstract
    public void clickToElement() {
        System.out.println("In ra");
    // 1 lớp con muốn sử dụng hàm abstract của lớp BasePage phải sử dụng @Override
    }

    // private/ default/ protected/ public
    private String fullName;
    String city;
    protected String address;
    public String phoneNumber;

    // Hàm, Phương thức
    private void setFullName() {

    }

    void setCity() throws InterruptedException {
        Thread.sleep(3000);

    }

    protected void setAddress() {

    }

    public boolean isElementDisplayed() {

        try {
            // Action - happy case
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}
