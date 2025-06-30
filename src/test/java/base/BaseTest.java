package base;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
//        WebDriverManager.chromedriver().clearResolutionCache().cachePath("drivers").avoidBrowserDetection().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
    	
    	System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
    	driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    public boolean isFlaky(double durationInSec) {
        boolean result = false;
        try {
            ProcessBuilder pb = new ProcessBuilder("python",
                "src/test/resources/model/predict_flaky.py", String.valueOf(durationInSec));

            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.readLine();

            if ("1".equals(output)) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
