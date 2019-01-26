

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map.Entry;

import com.coheris.selenium.test.constants.CSTUsers;

import java.util.Properties;


/**
 * Created on Jan 24, 2007
 */
public class PropertiesLoader {

  private final String propertiesFileName = "properties/myproperties.properties";

  /**
   * Cette m�thode stocke le fichier Properties � l'emplacement sp�cifi�
   *
   * @param props Le fichier � stocker
   * @param fileLocation L'emplacement o� le fichier doit �tre stock�
   * @param comments Commentaires � ins�rer en t�te du fichier
   * @throws FileNotFoundException
   * @throws IOException si une erreur est survenue lors de l'�criture du fichier
   */
  public void saveProperties(final Properties props, final String fileLocation, final String comments)
      throws FileNotFoundException, IOException {

    final OutputStream out = new FileOutputStream(fileLocation);
    props.store(out, comments);
    out.flush();
    out.close();
  }

  /**
   * Cette m�thode lit un fichier Properties � l'emplacement sp�cifi�
   *
   * @param propertiesFileLocation L'emplacemnt du fichier
   * @return Le fichier Properties charg�
   * @throws FileNotFoundException si le fichier n'a pas �t� trouv�
   * @throws IOException si une erreur est survenue durant la lecture
   */
  private Properties loadProperties() throws FileNotFoundException, IOException {

    final Properties props = new Properties();

    props.load(new FileInputStream(this.getBasedir().concat(this.propertiesFileName)));

    return props;
  }

  private String getBasedir() {

    String basedir = System.getProperty("basedir");
    if (basedir != null && !basedir.isEmpty()) {
      final String normalizedBasedir = basedir.replace('\\', '/');
      if (!normalizedBasedir.endsWith("/")) {
        basedir = normalizedBasedir.concat("/");
      } else {
        basedir = normalizedBasedir;
      }
    } else {
      basedir = "";
    }
    return basedir;
  }

  /**
   * Cette m�thode affiche cahque paire [cl�,valuer] d'un fichier Properties
   *
   * @param props Le fichier � afficher
   */
  public void displayProperties(final Properties props) {

    String propertyValue = null;
    String propertyName = null;
    final Iterator<Object> it = props.keySet().iterator();
    while (it.hasNext()) {
      propertyName = (String) it.next();
      propertyValue = props.getProperty(propertyName);
      System.out.println(propertyName + "=" + propertyValue);
    }
  }

  public MyProperties getProperties() throws FileNotFoundException, IOException {

    final MyProperties myProperties = new MyProperties();
    final Properties properties = this.loadProperties();
    
    for (final Entry<Object, Object> entry : properties.entrySet()) {
      final String propertyName = (String) entry.getKey();
      final String propertyValue = (String) entry.getValue();

      if (propertyName.equals("remoteHub")) {
        myProperties.setRemoteHub(propertyValue);
      } else if (propertyName.equals("browser")) {
        final String override = System.getProperty("browser-override"); // TODO : SELENIUM - A INTEGRER
//        if (override != null && !override.isEmpty()) {
//          myProperties.setBrowser(override);
//        } else {
//          myProperties.setBrowser(propertyValue);
//        }
      } else if (propertyName.equals("remote")) {
        myProperties.setRemote(propertyValue);
      } else if (propertyName.equals("fireFoxProfile")) {
          myProperties.setFirefoxprofile(propertyValue);
      } else if (propertyName.equals("fireFoxPath")) {
          myProperties.setFirefoxpath(propertyValue);
      } else if (propertyName.equals("internetExplorerDriverPath")) {
          myProperties.setInternetExplorerDriverPath(propertyValue);
      } else if (propertyName.equals("chromeDriverPath")) {
          myProperties.setChromeDriverPath(propertyValue);
      }

    }
    return myProperties;
  }
}
