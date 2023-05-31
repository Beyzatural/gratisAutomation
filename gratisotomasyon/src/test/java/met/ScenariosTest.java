package met;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.*;


public class ScenariosTest {

    public int randomSayi(int i, int j) throws InterruptedException {
        Thread.sleep(2000);
        Random random = new Random();
        return random.nextInt(i, j);
    }

    public boolean anasayfaKontrol(WebDriver driver) {

        if (!driver.getTitle().contains("Gratis | T")) {
            throw new IllegalStateException("This is not Home Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
        return true;
    }

    public void hoverCiltBakimi(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"wi300074-rich-text-100102\"]/div/nav/div/ul/li[2]/a"))).build().perform();
        System.out.println();
    }

    public void randomTitle(WebDriver driver) throws InterruptedException {




        int kategoriKolon = randomSayi(1, 5);
        int kategoriSatir = 0;
        Thread.sleep(1000);
        if (kategoriKolon == 1) {
            kategoriSatir = randomSayi(1, 21);
        } else if (kategoriKolon == 2) {
            kategoriSatir = randomSayi(1, 15);
        } else if (kategoriKolon == 3) {
            kategoriSatir = randomSayi(1, 14);
        } else {
            kategoriSatir = randomSayi(1, 9);
        }

        Thread.sleep(2000);

        WebElement element = driver.findElement(By.xpath("//*[@id=\"wi300074-rich-text-100102\"]/div/nav/div/ul/li[2]/div/div/ul"));
        Thread.sleep(2000);
        List<WebElement> webEleList = element.findElements(By.xpath(".//*"));
        webEleList.get(randomSayi(1, webEleList.size())).click();
        Thread.sleep(2000);

    }

    public void randomCheckBox(WebDriver driver) throws InterruptedException {

        Thread.sleep(4000);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"facet_v1-wi300032\"]/div/div/div[2]"));
        List<WebElement> webEleList = element.findElements(By.tagName("div"));

        webEleList.get(randomSayi(1,  webEleList.size()))
                .findElement(By.tagName("input"))
                .click();

        Thread.sleep(2000);
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"facet_v1-wi300032\"]/div/div/div[2]"));
        List<WebElement> webEleList2 = element2.findElements(By.tagName("div"));

        webEleList2.get(randomSayi(1,  webEleList2.size()))
                .findElement(By.tagName("input"))
                .click();

    }

    public void filtreAlanininYanindaMi (WebDriver driver){

        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"refinementsList_v1-wi300112\"]/div/div/ul"));


        if (elements.get(0).findElements(By.tagName("li")).size() != 0 )
            System.out.println("Filtreleri Temizle butonunun yanında !!!");
        else
            System.out.println("Filtreleri Temizle butonunun yanında degil!!!");

    }

    public void urunSec ( WebDriver driver) throws InterruptedException {

        Thread.sleep(2000);
        List<WebElement> elements = driver.findElement(By.xpath("//*[@id=\"searchBrowseProductGrid_v1-wi1000475\"]/div/div[3]"))
                .findElements(By.className("product-card-wrapper"));


        //*[@id="searchBrowseProductGrid_v1-wi1000475"]/div/div[2]/div[1]/g-product-card/div/div[1]/div[1]/a[2]
        Thread.sleep(2000);
        WebElement element = elements.get(randomSayi(1, elements.size()));
                element.click();
        Thread.sleep(2000);

        //element.findElement(By.xpath("//*[@id=\"product-grid-quickview-dialog\"]/div/div/div[2]/product-details/div/div/div[2]/ul[1]/li[2]/a"));

        //driver.findElement(By.xpath("//*[@id=\"searchBrowseProductGrid_v1-wi1000475\"]/div/div[2]/div[ " + randomSayi(1, 18) + " ]/g-product-card/div/div[1]/div[1]/a[2]")).click();
        //Thread.sleep(2000);
        System.out.println("Stok kontrolü yapılabilir");

    }

    public void writeExcel(WebDriver driver) throws IOException {

        String urunAd = driver.findElement(By.xpath("//*[@id=\"product-grid-quickview-dialog\"]/div/div/div[2]/product-details/div/div/div[2]/h1")).getText();
        String adet = driver.findElement(By.xpath("//*[@id=\"product-grid-quickview-dialog\"]/div/div/div[2]/product-details/div/div/div[2]/ul[1]/li[1]/div/span/span")).getText();
        String fiyat = driver.findElement(By.xpath("//*[@id=\"product-grid-quickview-dialog\"]/div/div/div[2]/product-details/div/div/div[2]/div[3]/div[1]/div/g-price/span/span[1]")).getText();


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Urun Detay");
        XSSFRow row;

        Map<String, Object[]> urunData
                = new TreeMap<String, Object[]>();

        urunData.put(
                "1",
                new Object[] { "Ürün Adı", "Adet", "Fiyat" });

        urunData.put(
                "2",
                new Object[] { urunAd, adet, fiyat });

        Set<String> keyid = urunData.keySet();

        int rowid = 0;

        for (String key : keyid) {

            row = sheet.createRow(rowid++);
            Object[] objectArr = urunData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        // writing the workbook into the file...
        FileOutputStream out = new FileOutputStream(
                new File("C:\\Users\\halil\\Desktop\\GratisUrun.xlsx"));

        workbook.write(out);
        out.close();

    }

    public void girisYap(WebDriver driver) throws IOException, InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"product-grid-quickview-dialog\"]/div/div/div[2]/product-details/div/div/div[2]/ul[1]/li[2]/a")).click();

        if (driver.findElement(By.xpath("//*[@id=\"login-register-modal\"]/div/div/div[2]/section/header/span")).isDisplayed())
            System.out.println("Giris yap sayfası acildi.");
        else
            System.out.println("Ürün stokda olmayabilir.");

        File file = new File("C:\\Users\\halil\\Desktop\\GratisUrun.xlsx");   //creating a new file instance
        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
        //creating Workbook instance that refers to .xlsx file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
        XSSFRow row;
        Iterator<Row> itr = sheet.iterator();//iterating over excel file


        String urunAd = sheet.getRow(1).getCell(0).toString();
        String urunUcret = sheet.getRow(1).getCell(0).toString();

        driver.findElement(By.xpath("//*[@id=\"login-register-modal\"]/div/div/div[2]/section/div/div/div/div/form/div[1]/input")).sendKeys(urunAd);
        driver.findElement(By.xpath("//*[@id=\"login-register-modal\"]/div/div/div[2]/section/div/div/div/div/form/div[2]/input")).sendKeys(urunUcret);

        Thread.sleep(5000);

    }

}
