package handson.handson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Selenium2 {
	ExtentHtmlReporter htmlReporter;
	ExtentTest test;
	ExtentReports extent;
	
	@Test(priority=1)
	  public void startReport() {
		  htmlReporter = new ExtentHtmlReporter(System.getenv("user.dir") +"/test-output/testreport.html");
		  	extent = new ExtentReports();
		  extent.attachReporter(htmlReporter);
		  
		  htmlReporter .config().setDocumentTitle("Extent Report Demo");
		  htmlReporter.config().setReportName("Test Report");
		  
		  htmlReporter.config().setTheme(Theme.STANDARD);
		  htmlReporter.config().setTimeStampFormat("EEEE,MMMM dd, yyyy, hh:mm a '('zzz')' ");
	  }
	
	@Test(priority = 2)
	public void f() throws IOException {

		WebDriver driver;
	
		

		File src = new File("C:\\Users\\training_d5.01.02\\Desktop\\sheet\\Sheet2.xlsx");
		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = workbook.getSheetAt(0);
		int rowCount = sheet1.getLastRowNum();
		for (int i = 0; i <= rowCount; i++) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\mydriver\\Drivers_Parvesh\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[1]/a")).click(); // singn
																												// In
																												// link
			WebElement username = driver.findElement(By.xpath("//input[@name='userName']"));
			username.sendKeys(sheet1.getRow(i).getCell(0).getStringCellValue());
			WebElement pswrd = driver.findElement(By.xpath("//input[@name='password']"));
			pswrd.sendKeys(sheet1.getRow(i).getCell(1).getStringCellValue());
			WebElement lgn = driver.findElement(By.name("Login"));
			lgn.click();

			String a_title = driver.findElement(By.xpath("//a[contains(text(),'SignOut')]")).getText();
			String e_title = "SignOut";
			Assert.assertEquals(e_title, a_title);
			System.out.println("Login Success");
			driver.close();
		}

	}

	@Test(priority = 3)
	public void login() throws InterruptedException {
		
		WebDriver driver;
		
		System.setProperty("webdriver.chrome.driver","C:\\mydriver\\Drivers_Parvesh\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[1]/a")).click();
		driver.findElement(By.id("userName")).sendKeys("lalitha"); // user id
		driver.findElement(By.id("password")).sendKeys("password123"); // user password
		driver.findElement(By.xpath("/html/body/main/div/div/div/form/fieldset/div[4]/div/input[1]")).click();

		WebElement allcategory = driver.findElement(By.xpath("//*[@id='menu3']/li[2]/a/span"));
		Actions act1 = new Actions(driver);
		act1.moveToElement(allcategory).build().perform();

		WebElement Electronics = driver.findElement(By.xpath("//*[@id='menu3']/li[2]/ul/li[1]/a/span"));
		Actions act2 = new Actions(driver);
		act2.moveToElement(Electronics).click().perform();
		Electronics.click();
		// driver.findElement(By.xpath("//*[@id='menu3']/li[2]/ul/li[1]/a/span")).click();

		Thread.sleep(10000);
		WebElement headphone = driver.findElement(By.xpath("//*[@id='submenuul11290']/li[1]/a/span"));
		Actions act3 = new Actions(driver);
		act3.moveToElement(headphone).click().perform();

		// driver.findElement(By.xpath("//*[@id='submenuul11290']/li[1]/a/span")).click();

		driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click(); // addto
																												// cart
		driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div/div[2]/div/a[2]")).click(); // cart

		driver.findElement(By.xpath("//*[@id='cart']/tfoot/tr[2]/td[5]/a")).click(); // checkout
		driver.findElement(By.xpath("//*[@id='add1']")).sendKeys("delhi"); // delhi
		driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click(); // proceed
																													// to
			Thread.sleep(5000);																										// pay
			  driver.findElement(By.xpath("//*[contains(text(),'Andhra Bank')]")).click(); // select andhra bank
			  driver.findElement(By.xpath("//*[@id=\"btn\"]")).click(); //continue
	 
		
		driver.findElement(By.xpath("//*[@id='horizontalTab']/div[2]/div/div/div/div/form/input[1]")).sendKeys("123456"); // useranme
																												// trans
		driver.findElement(By.xpath("//*[@id='horizontalTab']/div[2]/div/div/div/div/form/input[2]")).sendKeys("Pass@456"); // user
																													// password
		driver.findElement(By.xpath("//*[@id='horizontalTab']/div[2]/div/div/div/div/form/div/div[3]/input")).click(); // click
																															// login
		String a_title = driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/h3")).getText();
		String e_title = "Welcome to Payment Gateway";
		Assert.assertEquals(e_title, a_title);
		System.out.println("Login Success");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='horizontalTab']/div[2]/div/div/div/div/form/input")).sendKeys("Trans@456");// T

		driver.findElement(By.xpath("//*[@id='horizontalTab']/div[2]/div/div/div/div/form/div/div[2]/input")).click(); // paynow
		String aa_title = driver.findElement(By.xpath("/html/body/b/section/div/div/div/div[2]/p")).getText();
		String ee_title = "Your order has been confirmed";
		Assert.assertEquals(ee_title, aa_title);
		System.out.println("Login Success");

	}

}
