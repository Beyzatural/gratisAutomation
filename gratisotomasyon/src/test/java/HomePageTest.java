import met.ScenariosTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class HomePageTest extends ScenariosTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void GratisAnasayfaAcilmaTesti() throws InterruptedException, IOException {
        driver.get("https://www.gratis.com/");

        Thread.sleep(2000);
        anasayfaKontrol(driver);
        Thread.sleep(2000);
        hoverCiltBakimi(driver);
        Thread.sleep(2000);
        randomTitle(driver);
        Thread.sleep(2000);
        randomCheckBox(driver);
        Thread.sleep(2000);
        filtreAlanininYanindaMi(driver);
        Thread.sleep(2000);
        urunSec(driver);
        Thread.sleep(2000);
        writeExcel(driver);
        Thread.sleep(2000);
        girisYap(driver);
        Thread.sleep(2000);

    }

    @AfterEach
    void tearDown(){
        driver.close();
    }


}
