package scripting1;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomation1 {
	public static void main(String[] args) {
		ChromeDriver oBrowser = null;
		try {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Library\\drivers\\chromedriver.exe");
			oBrowser = new ChromeDriver();
			oBrowser.manage().window().maximize();
			oBrowser.navigate().to("http://localhost:82/login.do");
			Thread.sleep(2000);
			
			oBrowser.findElement(By.id("username")).sendKeys("admin");
			oBrowser.findElement(By.name("pwd")).sendKeys("manager");
			oBrowser.findElement(By.tagName("a")).click();
			Thread.sleep(4000);
			
			if(oBrowser.findElement(By.xpath("//td[@class='pagetitle']")).isDisplayed())
			{
				System.out.println("Login to ActiTime was successful");
				
				//handle the short cut window
				if(oBrowser.findElement(By.xpath("//div[@style='display: block;']")).isDisplayed())
				{
					oBrowser.findElement(By.id("gettingStartedShortcutsMenuCloseId")).click();
				}
				
				
				oBrowser.findElement(By.xpath("//div[text()='USERS']")).click();
				Thread.sleep(2000);
				oBrowser.findElement(By.xpath("//div[text()='Add User']")).click();
				Thread.sleep(2000);
				
				//Enter user details
				oBrowser.findElement(By.name("firstName")).sendKeys("au	to");
				oBrowser.findElement(By.name("lastName")).sendKeys("user1");
				oBrowser.findElement(By.name("email")).sendKeys("autouser1@sg.com");
				oBrowser.findElement(By.name("username")).sendKeys("autouser1");
				oBrowser.findElement(By.name("password")).sendKeys("mercury");
				oBrowser.findElement(By.name("passwordCopy")).sendKeys("mercury");
				
				oBrowser.findElement(By.xpath("//span[text()='Create User']")).click();
				Thread.sleep(2000);
				
				//VErify user is created?
				if(oBrowser.findElement(By.xpath("//div[@class='name']/span[text()='user1, auto']")).isDisplayed())
				{
					System.out.println("User was created successful");
					
					
					//delete the user
					oBrowser.findElement(By.xpath("//div[@class='name']/span[text()='user1, auto']")).click();
					Thread.sleep(2000);
					
					oBrowser.findElement(By.xpath("//button[contains(text(),'Delete User')]")).click();
					Thread.sleep(2000);
					oBrowser.switchTo().alert().accept();
					Thread.sleep(2000);
					
					
					//verify user deleted
					if(oBrowser.findElements(By.xpath("//div[@class='name']/span[text()='user1, auto']")).size()==0) {
						System.out.println("User deleted successful");
					}else {
						System.out.println("Failed to delete the user");
					}
										
				}else {
					System.out.println("Failed to create the user");
				}
				
				
				//logout
				oBrowser.findElement(By.xpath("//a[@id='logoutLink']")).click();
				Thread.sleep(2000);
			}else {
				System.out.println("Failed to  login to actiTime");
			}
			
			
			oBrowser.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			oBrowser = null;
		}
	}
}