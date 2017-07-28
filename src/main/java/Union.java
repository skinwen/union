import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by Administrator on 2017/7/29 0029.
 */
public class Union {
    private static final String PATH = "D:\\union\\contact.dat";
    private static final String deviceName = "e1459dc8";
    private static final String platformVersion = "5.1.1";
    private static final String udid = "e1459dc8";
    private static final String appPackage = "com.primeton.emp.client.core";
    //    private static final String appActivity = "cn.cucsi.global.umap3.Main";
    private static final String appActivity = ".activitys.Activity2";
    private static final String AppiumServerIP = "http://127.0.0.1:4723/wd/hub";
    private static Logger logger = LoggerFactory.getLogger(Union.class);
    private static AndroidDriver<AndroidElement> driver = null;
    private static final String LAST_DEPARTMENT = "";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("udid", udid);
//	    	capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("noSign", true);
        capabilities.setCapability("newCommandTimeout", "6000");
//            capabilities.setCapability("noReset", true);
        //设置设备
        capabilities.setCapability("platformName", "Android");
        driver = new AndroidDriver<>(new URL(AppiumServerIP), capabilities);
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[@content-desc=\"72\"]")));
        driver.findElementByAccessibilityId("72").click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")));
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"全国通讯录\"]")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"中国联通总部管理部门\"]")));

    }
}
