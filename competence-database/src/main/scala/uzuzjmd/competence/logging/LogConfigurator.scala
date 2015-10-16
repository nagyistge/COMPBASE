package uzuzjmd.competence.logging

import org.apache.log4j.xml.DOMConfigurator
import uzuzjmd.competence.owl.access.MagicStrings
import java.io.File
import org.apache.log4j.Logger
import org.apache.log4j.Level

object LogConfigurator {
  var init = false
  def initLogger() {
    if (!init) {
      val f = new File(MagicStrings.LOG4JXMLPATH);
      if (f.exists() && !f.isDirectory()) {
        DOMConfigurator.configure(MagicStrings.LOG4JXMLPATH);
      } else {
        System.out.println("ERROR: The path of log4j.xml is not valid. Configure in your server properties file log4jlocation.");
      }
      val logJena = Logger.getLogger("com.hp.hpl.jena");
      logJena.setLevel(Level.WARN);
      Logger.getLogger(LogConfigurator.getClass.getName).debug("Start with Logging");
      init = true;
    }
  }
}