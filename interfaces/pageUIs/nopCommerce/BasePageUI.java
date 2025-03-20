package pageUIs.nopCommerce;

public class BasePageUI {
    // User site
    public static final String USER_MY_ACCOUNT_LINK = "xpath=//a[@class='ico-account']";
    public static final String USER_LOGOUT_LINK = "xpath=//a[@class='ico-logout']";

    // Admin site
    public static final String ADMIN_LOGOUT_LINK = "xpath=//div[@id='navbarText']//a[@class='nav-link' and @href='/logout']";
    public static final String ADMIN_PRODUCT_MENU = "xpath=//i[@class='nav-icon fas fa-book']/ancestor::li[contains(@class,'nav-item has-treeview')]";
    public static final String ADMIN_PRODUCT_SUBMENU = "xpath=//p[text()=' Products']";

}
