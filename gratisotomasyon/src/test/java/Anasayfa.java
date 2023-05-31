import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Anasayfa {

    public static final By anasayfa = By.id("page");

    public WebDriver anasayfaControl(WebDriver driver){

        try {

            String title = driver.getTitle();
            String handle = driver.getWindowHandle();

            System.out.println(title);
        }catch (Exception e){
            e.printStackTrace();
        }

        return driver;
    }
}
