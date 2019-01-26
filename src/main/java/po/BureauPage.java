package po;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author patrice NYAMY
 */
public class BureauPage extends BasePage {

  @FindBy(id = "Se339974p261")
  WebElement socialbtnElement;

  /**
   * acces au element dans la page
   */

  // @FindBy(id = "Se65226p261")
  // WebElement ciblagesElement;

  public BureauPage(final WebDriver driver) throws Throwable {
    super();
  }

  public BureauSocialPage accesSocial() {
    this.waitForElementVisible2("id", this.socialbtnElement.getAttribute("id"));
    this.socialbtnElement.click();
    return this.initElements(BureauSocialPage.class);
  }

  // }
  // return initElements(CampagnePage.class);
  // }

}
