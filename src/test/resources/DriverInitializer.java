

import java.io.File;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.coheris.selenium.test.constants.CSTBrowsers;

public class DriverInitializer {

	private WebDriver webDriver;

	private final MyProperties myProperties;

	public DriverInitializer(final MyProperties myProperties) {
		this.myProperties = myProperties;
	}

	private void initRemoteDriver() throws Throwable {
		final String browser = this.myProperties.getBrowser();
		DesiredCapabilities capabilities;
		if (browser != null) {
			switch (browser) {
			case CSTBrowsers.FIREFOX:
				capabilities = DesiredCapabilities.firefox();
				break;
			case CSTBrowsers.CHROME:
				capabilities = DesiredCapabilities.chrome();
				break;
			case CSTBrowsers.INTERNET_EXPLORER:
				capabilities = DesiredCapabilities.internetExplorer();
				break;
			case CSTBrowsers.HTML_UNIT:
				capabilities = DesiredCapabilities.htmlUnit();
				break;
			default: {
				// logger.warn("Driver inconnu {}. On va utiliser HtmlUnit.",
				// browser);
				capabilities = DesiredCapabilities.htmlUnit();
			}
			}
		} else {
			// logger.warn("Driver non pr�cis�. On va utiliser HtmlUnit.",
			// browser);
			capabilities = DesiredCapabilities.htmlUnit();
		}
		final URL url = new URL(this.myProperties.getRemoteHub());
		this.webDriver = new RemoteWebDriver(url, capabilities);
		this.webDriver.manage().window().maximize();

	}

	private void initLocalDriver() {
		final String browser = this.myProperties.getBrowser();
		if (browser != null) {
			switch (browser) {
			case CSTBrowsers.FIREFOX:
				final FirefoxProfile profile = new FirefoxProfile(new File(this.myProperties.getFirefoxprofile()));
				// TODO : A VARIABILISER
				this.webDriver = new FirefoxDriver(
						new FirefoxBinary(new File(this.myProperties.getFirefoxpath())), profile);
				break;
			case CSTBrowsers.INTERNET_EXPLORER:
				System.setProperty("webdriver.ie.driver", this.myProperties.getInternetExplorerDriverPath());
				this.webDriver = new InternetExplorerDriver();
				break;
			case CSTBrowsers.HTML_UNIT:
				this.webDriver = new HtmlUnitDriver();
				break;
			case CSTBrowsers.CHROME:
				System.setProperty("webdriver.chrome.driver", this.myProperties.getChromeDriverPath());
				System.out.println(System.getProperty("webdriver.chrome.driver"));
				this.webDriver = new ChromeDriver();
				break;
			default:
				this.webDriver = new HtmlUnitDriver();
			}

		}
		this.webDriver.get(this.myProperties.getBaseUrl());
	}

	public WebDriver createDriver() throws Throwable {
		final String remote = this.myProperties.getRemote();
		if ("true".equals(remote)) {
			this.initRemoteDriver();
		} else {
			this.initLocalDriver();
		}
		return this.webDriver;
	}
}
