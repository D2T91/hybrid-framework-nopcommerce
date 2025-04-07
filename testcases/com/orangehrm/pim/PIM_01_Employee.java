package com.orangehrm.pim;

import commons.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.PageGenerator;
import pageObjects.orangeHRM.pim.employee.AddEmployeePageObject;
import pageObjects.orangeHRM.pim.employee.EmployeeListPageObject;
import pageObjects.orangeHRM.pim.employee.PersonalDetailsPageObject;

public class PIM_01_Employee extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private PersonalDetailsPageObject personalDetailsPage ;
    private AddEmployeePageObject addEmployeePage;
    private String employeeID, firstName, lastName, editFirstName, editLastName;
    private String driverLicenseNumber, driverLicenseExpiryDate, nationality, maritalStatus, dateOfBirth, gender;
    private String avatarImageName = "Thai Nguyen.jpg";

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {
        firstName = "Do";
        lastName = "Tung Thanh";
        editFirstName = "Donal";
        editLastName = "Trump";
        driverLicenseNumber = "012345678";
        driverLicenseExpiryDate = "2025-09-09";
        nationality = "German";
        maritalStatus = "Married";
        dateOfBirth = "1995-03-05";
        gender = "Male";


        driver = getBrowserDriver(urlValue, browserName);

        loginPage = PageGenerator.getPageInstance(LoginPageObject.class, driver);

        loginPage.enterToUserNametextbox("automationfc");
        loginPage.enterToPasswordtextbox("Tung8545059@");

        dashboardPage = loginPage.clickToLoginButton();
    }

    @Test
    public void Employee_01_Add_New() {

        employeeListPage = dashboardPage.clickToPIMPage();

        addEmployeePage = employeeListPage.clickToAddEmployeeButon();

        addEmployeePage.enterToFirstNameTextBox(firstName);
        addEmployeePage.enterToLastNameTextBox(lastName);

        employeeID = addEmployeePage.getEmployeeID();

        personalDetailsPage = addEmployeePage.clickToSaveButtonAtEmployeeContainer();
    }

    @Test
    public void Employee_02_Upload_Avatar() {

        personalDetailsPage.clickToEmployeeAvatarImage();

        // Lấy height/ width của Avatar > so sánh kích thước avatar before và after
        Dimension beforeImageUpload = personalDetailsPage.getAvatarSize();

        personalDetailsPage.upLoadMultipleFiles(driver, avatarImageName);

        personalDetailsPage.clickToSaveButtonAtChangeProfilePicture();

        Assert.assertTrue(personalDetailsPage.isSuccessMessageIsDisplayed(driver));

        personalDetailsPage.waitAllLoadingIconInvisible(driver);

        Assert.assertTrue(personalDetailsPage.isProfileAvatarUpdateSuccess(beforeImageUpload));

    }

    @Test
    public void Employee_03_Personal_Details() {

        personalDetailsPage.openPersonalDetailsPage();
        personalDetailsPage.waitAllLoadingIconInvisible(driver);

        personalDetailsPage.enterToFirstNameTextbox(editFirstName);
        personalDetailsPage.enterToLastNameTextbox(editLastName);

        Assert.assertEquals(personalDetailsPage.getEmployeeID(), employeeID);

        personalDetailsPage.enterToDriverLicenseTextbox(driverLicenseNumber);
        personalDetailsPage.enterToLicenseExpiryDateTextbox(driverLicenseExpiryDate);
        personalDetailsPage.selectNationalityDropdown(nationality);
        personalDetailsPage.selectMaritalStatusDropdown(maritalStatus);

        personalDetailsPage.enterToDateOfBirthTextbox(dateOfBirth);

        personalDetailsPage.selectGenderMaleRadioButton(gender);

        personalDetailsPage.clickSaveButtonAtPersonalDetailContainer();

        Assert.assertTrue(personalDetailsPage.isSuccessMessageIsDisplayed(driver));

        personalDetailsPage.waitAllLoadingIconInvisible(driver);

        // Verify Step
        Assert.assertEquals(personalDetailsPage.getFirstNameTextboxValue(), editFirstName);

        Assert.assertEquals(personalDetailsPage.getLastNameTextboxValue(), editLastName);

        Assert.assertEquals(personalDetailsPage.getEmployeeID(), employeeID);

        Assert.assertEquals(personalDetailsPage.getDriverLicenseTextboxValue(), driverLicenseNumber);

        Assert.assertEquals(personalDetailsPage.getLicenseExpiryDateTextboxValue(), driverLicenseExpiryDate);

        Assert.assertEquals(personalDetailsPage.getNationalityDropdownValue(), nationality);

        Assert.assertEquals(personalDetailsPage.getMaritalStatusDropdownValue(), maritalStatus);

        Assert.assertEquals(personalDetailsPage.getDateOfBirthTextboxValue(), dateOfBirth);

        Assert.assertTrue(personalDetailsPage.isGenderMaleRadioSelected(gender));

    }

    @Test
    public void Employee_04_Contact_Details() {

    }

    @Test
    public void Employee_05_Emergency_Details() {

    }

    @Test
    public void Employee_06_Assigned_Dependents() {

    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

}
