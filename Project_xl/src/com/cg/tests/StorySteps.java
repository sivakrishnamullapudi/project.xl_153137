package com.cg.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import jxl.Sheet;
import jxl.Workbook;

public class StorySteps {
	WebDriver driver;
	static String fName;
	static String lName;
	static String mail;
	static String cNum;
	static String address;
	static String city;
	static String stateName;

	@Before
	public static void readerFunction() throws Exception {
		FileInputStream inputStream=new FileInputStream(new File("D:\\Book1.xls"));
		Workbook wb=Workbook.getWorkbook(inputStream);
		Sheet sh = wb.getSheet(0);
		fName=sh.getCell(0,0).getContents();
		lName=sh.getCell(1,0).getContents();
		mail=sh.getCell(2,0).getContents();
		cNum=sh.getCell(3,0).getContents();
		address=sh.getCell(4,0).getContents();
		city=sh.getCell(5,0).getContents();
		stateName=sh.getCell(6,0).getContents();
		
		
	}

	@Given("^Registration Page is loaded$")
	public void registration_Page_is_loaded() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\Users\\simullap\\Documents\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("D:\\JavaWorkSpaceSTS\\ProjRegistration\\Pages\\RegistrationPage.html");

	}

	@When("^Enter employee firstName and lastName$")
	public void enter_employee_firstName_and_lastName() throws Throwable {
		RegistrationPage.fName(driver).sendKeys(fName);
		RegistrationPage.lName(driver).sendKeys(lName);
	}

	@When("^Enter mail and number$")
	public void enter_mail_and_number() throws Throwable {
		RegistrationPage.mail(driver).sendKeys(mail);
		RegistrationPage.cNum(driver).sendKeys(cNum);
	}

	@When("^Enter address and city$")
	public void enter_address_and_city() throws Throwable {
		RegistrationPage.address(driver).sendKeys(address);
		RegistrationPage.city(driver).sendKeys(city);
	}

	@When("^Select stateName$")
	public void select_stateName() throws Throwable {
		WebElement state = RegistrationPage.state(driver);
		Select select = new Select(state);
		select.selectByValue(stateName);
		RegistrationPage.submit(driver).click();
	}

	@Then("^Load Project Page$")
	public void load_Project_Page() throws Throwable {
		RegistrationPage.clickAlert(driver);
	}

	@Then("^Click Register for Project$")
	public void click_Register_for_Project() throws Throwable {
		ProjectPage.submit(driver).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Alert alert = driver.switchTo().alert();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		alert.accept();

	}

}
