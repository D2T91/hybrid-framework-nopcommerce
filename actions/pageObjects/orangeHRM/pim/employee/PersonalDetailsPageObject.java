package pageObjects.orangeHRM.pim.employee;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.pim.employee.PersonalDetailsPageUI;

public class PersonalDetailsPageObject extends EmployeeTabs {
    private WebDriver driver;

    public PersonalDetailsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public Dimension getAvatarSize() {
        return getElementSize(driver, PersonalDetailsPageUI.ICON_AVATAR);
    }

    public void clickToEmployeeAvatarImage() {
        waitForElementClickable(driver, PersonalDetailsPageUI.ICON_AVATAR);
        clickToElement(driver, PersonalDetailsPageUI.ICON_AVATAR);
    }

    public void clickToSaveButtonAtChangeProfilePicture() {
        waitForElementClickable(driver, PersonalDetailsPageUI.SAVE_BUTTON);
        clickToElement(driver, PersonalDetailsPageUI.SAVE_BUTTON);
    }

    public boolean isProfileAvatarUpdateSuccess(Dimension beforeImageUpload) {
        sleepInSecond(3000);
        Dimension afterImageUpload = getAvatarSize();
        return !beforeImageUpload.equals(afterImageUpload);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, PersonalDetailsPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElementWithKey(driver, PersonalDetailsPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, PersonalDetailsPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElementWithKey(driver, PersonalDetailsPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public String getEmployeeID() {
        waitForElementVisible(driver, PersonalDetailsPageUI.EMPLOYEE_ID_TEXTBOX);
        sleepInSecond(3000);
        return getElementAttribute(driver, PersonalDetailsPageUI.EMPLOYEE_ID_TEXTBOX,"value");
    }

    public void enterToDriverLicenseTextbox(String driverLicenseNumber) {
        waitForElementVisible(driver, PersonalDetailsPageUI.DRIVER_LICENSE_TEXTBOX);
        sendkeyToElement(driver, PersonalDetailsPageUI.DRIVER_LICENSE_TEXTBOX, driverLicenseNumber);
    }

    public void enterToLicenseExpiryDateTextbox(String LicenseExpiryDate) {
        waitForElementVisible(driver, PersonalDetailsPageUI.LICENSE_EXPIRY_DATE_TEXTBOX);
        sendkeyToElement(driver, PersonalDetailsPageUI.LICENSE_EXPIRY_DATE_TEXTBOX, LicenseExpiryDate);
    }

    public void selectNationalityDropdown(String nationality) {
        waitForElementClickable(driver, PersonalDetailsPageUI.NATIONALITY_DROPDOWN_PARENT);
        selectItemInCustomDropdown(driver, PersonalDetailsPageUI.NATIONALITY_DROPDOWN_PARENT, PersonalDetailsPageUI.NATIONALITY_DROPDOWN_CHILD, nationality);
    }

    public void selectMaritalStatusDropdown(String maritalStatus) {
        waitForElementClickable(driver, PersonalDetailsPageUI.MARITAL_STATUS_DROPDOWN_PARENT);
        selectItemInCustomDropdown(driver, PersonalDetailsPageUI.MARITAL_STATUS_DROPDOWN_PARENT, PersonalDetailsPageUI.MARITAL_STATUS_DROPDOWN_CHILD, maritalStatus);
    }

    public void enterToDateOfBirthTextbox(String dateOfBirth) {
        waitForElementVisible(driver, PersonalDetailsPageUI.DATE_OF_BIRTH_TEXTBOX);
        sendkeyToElement(driver, PersonalDetailsPageUI.DATE_OF_BIRTH_TEXTBOX, dateOfBirth);
    }

    public void selectGenderMaleRadioButton(String gender) {
        //waitForElementVisible(driver, PersonalDetailsPageUI.GENDER_RADIO_BUTTON, gender);
        //checkToCheckBoxradio(driver, PersonalDetailsPageUI.GENDER_RADIO_BUTTON, gender);
        clickToElementByJS(driver, PersonalDetailsPageUI.GENDER_RADIO_BUTTON, gender);
    }

    public void clickSaveButtonAtPersonalDetailContainer() {
        waitForElementClickable(driver, PersonalDetailsPageUI.SAVE_BUTTON);
        clickToElement(driver, PersonalDetailsPageUI.SAVE_BUTTON);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(driver, PersonalDetailsPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver, PersonalDetailsPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getDriverLicenseTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsPageUI.DRIVER_LICENSE_TEXTBOX);
        return getElementAttribute(driver, PersonalDetailsPageUI.DRIVER_LICENSE_TEXTBOX, "value");
    }

    public String getLicenseExpiryDateTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsPageUI.LICENSE_EXPIRY_DATE_TEXTBOX);
        return getElementAttribute(driver, PersonalDetailsPageUI.LICENSE_EXPIRY_DATE_TEXTBOX, "value");
    }

    public String getNationalityDropdownValue() {
        waitForElementVisible(driver, PersonalDetailsPageUI.NATIONALITY_DROPDOWN_ITEM_SELECTED);
        return getElementText(driver, PersonalDetailsPageUI.NATIONALITY_DROPDOWN_ITEM_SELECTED);
    }

    public String getMaritalStatusDropdownValue() {
        waitForElementVisible(driver, PersonalDetailsPageUI.MARITAL_STATUS_DROPDOWN_ITEM_SELECTED);
        return getElementText(driver, PersonalDetailsPageUI.MARITAL_STATUS_DROPDOWN_ITEM_SELECTED);
    }

    public String getDateOfBirthTextboxValue() {
        waitForElementVisible(driver, PersonalDetailsPageUI.DATE_OF_BIRTH_TEXTBOX);
        return getElementAttribute(driver, PersonalDetailsPageUI.DATE_OF_BIRTH_TEXTBOX, "value");
    }

    public boolean isGenderMaleRadioSelected(String gender) {
        waitForElementSelected(driver, PersonalDetailsPageUI.GENDER_RADIO_BUTTON, gender);
        return isElementSelected(driver, PersonalDetailsPageUI.GENDER_RADIO_BUTTON, gender);
    }
}
