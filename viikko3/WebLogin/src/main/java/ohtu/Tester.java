package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        
        /* Login when user does not exist
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        Login when user does not exist end */
        
        String newUsername = createUser(driver);
        logoutAfterCreation(driver);
        loginWithWrongPassword(driver, newUsername);
        
        
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
    private static String createUser(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);
        
        Random r = new Random();
        String createdUsername = "pekka" + r.nextInt(100000);
        element = driver.findElement(By.name("username"));
        element.sendKeys(createdUsername);
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
        
        return createdUsername;
        
    }
    
    private static void logoutAfterCreation(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(2);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(2);
    }
    
    private static void loginWithWrongPassword(WebDriver driver, String username) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);
        
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
        sleep(2);

    }
}
