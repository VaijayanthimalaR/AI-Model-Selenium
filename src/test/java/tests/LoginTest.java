package tests;

import base.BaseTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    
    @Test
    public void validLogin() {
        try {
            // 🔮 Step 1: Call Python script to check if test is flaky
            Process process = Runtime.getRuntime().exec("python src/test/resources/model/predict_flaky.py");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            boolean isFlaky = false;

            while ((line = reader.readLine()) != null) {
                System.out.println("⚠️ ML Output: " + line);
                if (line.toLowerCase().contains("flaky")) {
                    isFlaky = true;
                }
            }
            reader.close();

            // ⚠️ Step 2: Optionally skip or log if flaky
            if (isFlaky) {
                System.out.println("❗Test predicted as flaky. You may skip/assert/record.");
                // return; // Uncomment this to skip the test completely
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // ✅ Step 3: Your actual Selenium test
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

}
