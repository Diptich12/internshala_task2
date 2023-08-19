package myproject;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.asserts.Assertion;

import io.github.bonigarcia.wdm.WebDriverManager;

public class simplemavenproject<WebDriver> {

	public static void main(String[] args) {
		
	}
		@org.testng.annotations.Test(dataProvider="LoginData")
		public void Test(String Username,String Password) {	
			WebDriver driver =(WebDriver) new FirefoxDriver();
			String alertname="Login Unsuccessful";
			String alertname2="Invalid login credentials";
			((org.openqa.selenium.WebDriver) driver).get("https://beta.deepthought.education/login");
			((WebElement) ((By) driver).findElements((SearchContext) By.name("username"))).sendKeys(Username);
			((RemoteWebDriver) driver).findElement(By.name("password")).sendKeys(Password);
			((WebElement) driver).findElement(By.id("login")).click();
			String title=((org.openqa.selenium.WebDriver) driver).getTitle();
			if(title.equals(" "))
			{
				Assert.assertTrue(true);
			}
			else {
				String alert=((WebElement) driver).findElement(By.xpath("//strong[normalize-space()='Login Unsuccessful']")).getAttribute("innerHTML");
				System.out.println(alert);
				if(alert.equals(alertname)|| alert.equals(alertname2))
				{
					System.out.println("Error Msg should be displayed ");	
				}
				else {
					System.out.println("Error msg should not diplayed");
				}
				Assert.assertTrue(false);	
			}
			((org.openqa.selenium.WebDriver) driver).close();
		}

		@DataProvider(name="LoginData")
		public Object[][] Dataset(){
			return new Object[][] 
					{
				 {"validUserName","ValidPassword"},
			     {"ValidUserName","InvalidPassword"},
			     {"InvaliduserName","ValidPassword"},
			     {"InvalidUserName","InvalidPassword"}
			
					};
		
		}

}
