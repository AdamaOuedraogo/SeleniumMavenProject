/**
 * 
 */



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author aouedraogo
 */
public class SeleniumTestResources {

  private static SeleniumTestResources INSTANCE;

  private MyProperties myProperties;

  private final WebDriver driver;

  private final StringBuffer verificationErrors;

  private final WebDriverWait wait;

  protected long timeoutInSeconds = 30;

  private SeleniumTestResources() throws Throwable {
    this.verificationErrors = new StringBuffer();
    initMyProperties();
    this.driver = new DriverInitializer(this.myProperties).createDriver();
    this.wait = new WebDriverWait(this.driver, this.timeoutInSeconds);
  }

  private void initMyProperties() {
    final PropertiesLoader propertiesLoader = new PropertiesLoader();
    try {
      this.myProperties = propertiesLoader.getProperties();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public static SeleniumTestResources getInstance() throws Throwable {
    if (INSTANCE == null) {
      INSTANCE = new SeleniumTestResources();
    }
    return INSTANCE;
  }

  public MyProperties getMyProperties() {
    return this.myProperties;
  }

  public WebDriver getDriver() {
    return this.driver;
  }

  public StringBuffer getVerificationErrors() {
    return this.verificationErrors;
  }

  public WebDriverWait getWait() {
    return this.wait;
  }

}
