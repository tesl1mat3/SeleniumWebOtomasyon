import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import com.opencsv.CSVReader;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(testlogger.TestLogger.class)
public class BaseTest {

    String CSV_PATH = "TestData.csv";
    public CSVReader csvReader;
    String[] csvCell;
    WebDriver driver ;
    boolean check = false;
    boolean present;

    @BeforeAll
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        if(check ==isAccessable("https://www.amazon.com.tr/", 10)){
            driver.manage().window().maximize();
            driver.get("https://www.amazon.com.tr/");
        }
    }

    @AfterAll
    public void exit(){
        driver.close();
        driver.quit();
    }

    public static boolean isAccessable(String url, int timeout) {
        url = url.replaceFirst("https", "http");

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
        } catch (IOException exception) {
            return false;
        }
        return true;
    }
}