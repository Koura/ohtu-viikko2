
package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {
    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:8080");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));
        //scenario: creating new user.
//        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click(); 
        
        System.out.println("==");
        
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
//        element.sendKeys("Pertti");
        //scenario: non-existent user
//        element.sendKeys("nuija");
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        //scenario: wrong password.
        //element.sendKeys("kkep");
        element.sendKeys("akkep");
//        element.sendKeys("12345678");
////        element = driver.findElement(By.name("passwordConfirmation"));
//        element.sendKeys("12345678");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
        
    }
}
