

import com.coheris.selenium.test.constants.CSTBrowsers;
import com.coheris.selenium.test.constants.CSTServers;
import com.coheris.selenium.test.constants.CSTUsers;

public class MyProperties {

	// TODO : SELENIUM - A rendre variable en fonction du serveur � tester
	private String baseUrl = CSTServers.URL_ORACLE;
	private String browser = CSTBrowsers.FIREFOX;

	private String firefoxprofile = null;
	private String firefoxpath = null;
	private String internetExplorerDriverPath = null;
	private String chromeDriverPath = null;
	
	private boolean acceptNextAlert = true;
	private String remote = "false";
	private String remoteHub = null;
	private int implicteWait = 15; // en seconde

	// TODO : SELENIUM - A simplifier en fonction de l'utilisateur souhait� 1 seul loginname et password
	private String LoginNameCss = CSTUsers.CSS_USER;
	private String passwordCss = CSTUsers.CSS_PASSWORD;
	private String LoginNameAdm = CSTUsers.CRM_USER;
	private String passwordAdm = CSTUsers.CRM_PASSWORD;


	
	
	public String getLoginName(String user) {
		if(user.equals(CSTUsers.CSS))
			return LoginNameCss;
		else
			return LoginNameAdm;
	}

	public String getPassword(String user) {
		if(user.equals(CSTUsers.CSS))
			return passwordCss;
		else
			return passwordAdm;
	}

//	public void setLoginame(final String Loginame, String user) {
//		if(user.equals(CSTUsers.CSS))
//			this.LoginNameCss = Loginame;
//		else
//			this.LoginNameAdm = Loginame;
//	}
//
//	public void setPassword(final String password, String user) {
//		if(user.equals(CSTUsers.CSS))
//			this.passwordCss = password;
//		else
//			this.passwordAdm = password;
//	}

	public String getBaseUrl() {
		return baseUrl;
	}

//	public void setBaseUrl(final String baseUrl) {
//		this.baseUrl = baseUrl;
//	}

	public String getBrowser() {
		return browser;
	}

//	public void setBrowser(final String browser) {
//		this.browser = browser;
//	}

	public boolean isAcceptNextAlert() {
		return acceptNextAlert;
	}

	public void setAcceptNextAlert(final boolean acceptNextAlert) {
		this.acceptNextAlert = acceptNextAlert;
	}

	public String getRemote() {
		return remote;
	}

	public void setRemote(final String remote) {
		this.remote = remote;
	}

	public String getRemoteHub() {
		return remoteHub;
	}

	public void setRemoteHub(final String remoteHub) {
		this.remoteHub = remoteHub;
	}

//	public String getDriverPathChrome() {
//		return driverPathChrome;
//	}

//	public void setDriverPathChrome(final String driverPathChrome) {
//		this.driverPathChrome = driverPathChrome;
//	}

//	public String getDriverPathIe() {
//		return driverPathIe;
//	}

//	public void setDriverPathIe(final String driverPathIe) {
//		this.driverPathIe = driverPathIe;
//	}

	public int getImplicteWait() {
		return implicteWait;
	}

	public void setImplicteWait(final int implicteWait) {
		this.implicteWait = implicteWait;
	}

	public String getFirefoxprofile() {
		return firefoxprofile;
	}

	public void setFirefoxprofile(String firefoxprofile) {
		this.firefoxprofile = firefoxprofile;
	}

	public String getFirefoxpath() {
		return firefoxpath;
	}

	public void setFirefoxpath(String firefoxpath) {
		this.firefoxpath = firefoxpath;
	}

	public String getInternetExplorerDriverPath() {
		return internetExplorerDriverPath;
	}

	public void setInternetExplorerDriverPath(String internetExplorerDriverPath) {
		this.internetExplorerDriverPath = internetExplorerDriverPath;
	}

	public String getChromeDriverPath() {
		return chromeDriverPath;
	}

	public void setChromeDriverPath(String chromeDriverPath) {
		this.chromeDriverPath = chromeDriverPath;
	}

}
