package FirstPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AddEmployee {
	WebDriver driver;
	XSSFWorkbook excel;
	XSSFSheet sheet;
	XSSFCell cell;
	
	@Test (priority = 0)
	public void openbrowser(){
		System.setProperty("webdriver.chrome.driver", "D:\\E-Backup\\Automation\\Drivers\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://secure.payrollspan.net/");
		driver.manage().window().maximize();
	}
	
	@Test(priority = 1)
	public void credentials() throws IOException, InterruptedException{
		driver.findElement(By.id("EmailAddress")).sendKeys("arunkumar.dhanasekar+always1@spanllc.com");
		driver.findElement(By.id("Password")).sendKeys("Arun@123");
		driver.findElement(By.id("btnsignin")).click();
	
	
	
		
			
		
		driver.findElement(By.id("aPeople")).click();
		driver.findElement(By.id("aEmployeeuser")).click();
		driver.findElement(By.xpath("//button[@class='btn btn_sm btn_primary floatR']")).click();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\STS-111\\Desktop\\DBF37420.xlsx");
		excel = new XSSFWorkbook(fis);
		sheet = excel.getSheetAt(1);
		
		for(int i=1; i<=sheet.getLastRowNum();i++){
		
		//EMPLOYMENT DETAILS PAGE
			
		cell = sheet.getRow(i).getCell(0);
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(1);
		driver.findElement(By.id("MiddleName")).sendKeys(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(2);
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(3);
		driver.findElement(By.id("HireDate")).sendKeys(cell.getStringCellValue());
		driver.findElement(By.id("HireDate")).sendKeys(Keys.TAB);
		cell = sheet.getRow(i).getCell(4);
		Select drp1 = new Select (driver.findElement(By.id("EmploymentStatus")));
		drp1.selectByValue(cell.getStringCellValue());
		
		cell = sheet.getRow(i).getCell(5);
		if(cell.getStringCellValue().equalsIgnoreCase("PAIDBYHOUR")){
			Select drp51 = new Select (driver.findElement(By.id("CompensationType")));
			drp51.selectByValue("PAIDBYHOUR");
			
			cell = sheet.getRow(i).getCell(7);
			driver.findElement(By.id("WagesAmount")).sendKeys(cell.getStringCellValue());
		}
		else if(cell.getStringCellValue().equalsIgnoreCase("SALARYNOOVERTIME")){
			
			Select drp52 = new Select (driver.findElement(By.id("CompensationType")));
			drp52.selectByValue("SALARYNOOVERTIME");
			cell = sheet.getRow(i).getCell(6);
			driver.findElement(By.id("WagesAmount")).sendKeys(cell.getStringCellValue());
		}
	else if(cell.getStringCellValue().equalsIgnoreCase("OTELIGIBLE")){
		Select drp53 = new Select (driver.findElement(By.id("CompensationType")));
		drp53.selectByValue("SALARYELIGIBLEFOROVERTIME");
		cell = sheet.getRow(i).getCell(6);
		driver.findElement(By.id("WagesAmount")).sendKeys(cell.getStringCellValue());
		
	}}}}
		/*Select drp2 = new Select (driver.findElement(By.id("CompensationType")));
		drp2.selectByValue(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(6);
		driver.findElement(By.id("WagesAmount")).sendKeys(cell.getStringCellValue());
		driver.findElement(By.id("WagesPaidType")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		try {
			driver.findElement(By.id("btnAddEmployee")).click();
		} catch (Exception e) {
			
			Thread.sleep(2000);
			driver.findElement(By.id("btnAddEmployee")).click();
			
			//COMPLETE EMPLOYEE DETAILS POP UP
			
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Enter Manually')]"))).click();
			
			//EMPLOYEE PERSONAL INFO
			
			cell = sheet.getRow(i).getCell(7);
			WebDriverWait wait5 = new WebDriverWait(driver,10);
			wait5.until(ExpectedConditions.elementToBeClickable(By.id("SSN"))).sendKeys(cell.getStringCellValue());
			//driver.findElement(By.id("SSN")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(8);
			driver.findElement(By.id("DateOfBirth")).sendKeys(cell.getStringCellValue());
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,3000)");
			Thread.sleep(2000);
			WebDriverWait wait6 = new WebDriverWait(driver,10);
			wait6.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(@for,'IsManualAddress')]"))).click();
			
			driver.findElement(By.id("AddressLine1")).sendKeys("123 E Main Street");
			driver.findElement(By.id("AddressLine2")).sendKeys("Suire # 150");
			driver.findElement(By.id("City")).sendKeys("Rock Hill");
			Select drp3 = new Select(driver.findElement(By.id("StateId")));
			drp3.selectByValue("49");
			driver.findElement(By.id("ZipCode")).sendKeys("29730");
			driver.findElement(By.id("btnNextAddBusiness")).click();
			
			//ADDRESS CONFIRMATION POP UP
			
			WebDriverWait wait7 = new WebDriverWait(driver,10);
			wait7.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Skip')]"))).click();
			
			//FEDERAL ALLOWANCES DETAILS
			
			WebDriverWait wait8 = new WebDriverWait(driver,10);
			wait8.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(.,'Yes')]"))).click();
			
			driver.findElement(By.id("TaxInfoNextbtn")).click();
			
			//STATE ALLOWANCES DETAILS
			
			WebDriverWait wait9 = new WebDriverWait(driver,10);
			wait9.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='IsUseSameFederalTaxesYes']"))).click();
			WebDriverWait wait19 = new WebDriverWait(driver,10);
			wait19.until(ExpectedConditions.elementToBeClickable(By.id("StateTaxInfoNextbtn"))).click();
			//Thread.sleep(3000);
			
			//NEW HIRE REPORT
			
			WebDriverWait wait10 = new WebDriverWait(driver,10);
			wait10.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='IsNeedToFileNewHireReportYes']"))).click();
			driver.findElement(By.id("btnNextSaveNewHireReport")).click();
			
			//PAYMENT DETAILS PAGE
			Thread.sleep(3000);
			Select drp4 = new Select(driver.findElement(By.id("PaymentMethod")));
			drp4.selectByValue("17");
			driver.findElement(By.id("TaxInfoNextbtn")).click();
			
			//FORM I-9 PAGE
			
			Thread.sleep(4000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,18000)");
			//WebDriverWait wait11 = new WebDriverWait(driver,20);
			//wait11.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[5]/div[1]/div[2]/div/div[1]/form/table/tbody/tr[5]/td/span/label/span[1]/i"))).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("html/body/div[5]/div[1]/div[2]/div/div[1]/form/table/tbody/tr[5]/td/span/label/span[1]/i")).click();
			driver.findElement(By.id("btnNextSaveI9Documents")).click();
			
			//INVITE PAGE
			
			WebDriverWait wait1 = new WebDriverWait(driver,10);
			wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Invite later')]"))).click();
			
			//EMPLOYEE ADDED PAGE
			
			WebDriverWait wait12 = new WebDriverWait(driver,10);
			wait12.until(ExpectedConditions.elementToBeClickable(By.id("addOnotherEmployee"))).click();
			
			
			
		}
		
	}

	}
}
*/