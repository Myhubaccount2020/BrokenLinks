import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BrokenLinks {

       WebDriver driver;

       @Before
    public void setDriver(){
           System.setProperty("webdriver.chrome.driver","c:\\Drivers\\chromedriver.exe" );
           driver= new ChromeDriver();
       }

       @Test
   public void brokenLinks(){
           driver.get("http://demo.guru99.com/test/newtours/");
           driver.manage().window().maximize();
       List<WebElement> links= driver.findElements(By.tagName("a"));
           System.out.println("We have "+links.size()+" links");
           int numBrokenLinks = 0;
       for (int i=0;i<links.size();i++){
           String url =  links.get(i).getAttribute("href");
           int status=  given().when().get(url).getStatusCode();
           if (status!=200){
               numBrokenLinks++;
               System.out.println("This link is broken "+url);
       }
   }
           System.out.println("The number of broken links is "+numBrokenLinks);
}


}
