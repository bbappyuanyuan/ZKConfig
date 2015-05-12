package config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * Generate java codes according to config-list.xml
 * 
 * @author Zifeng Yuan
 */
public class CodeBuilder {

  public static void eraseDataClass() {
    File path = null;
    path = new File(ConfigEnv.PATH);
    File[] files = path.listFiles();
    for (File file : files) {
      System.out.println(file.toString());
      if (file.toString().endsWith("Data.java"))
        file.delete();
    }
  }

  public static void generateDataClass() {
    XMLConfiguration configList = new XMLConfiguration();
    try {
      configList.load("config-list.xml");
    } catch (ConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Iterator<String> businesses = configList.getKeys();
    while (businesses.hasNext()) {
      String business = businesses.next();
      String[] properties = configList.getStringArray(business);
      business = ConfigUtils.toVariable(business);
      File f = new File(ConfigEnv.PATH + ConfigUtils.capitalize(business) + "Data.java");
      if (f.exists())
        f.delete();
      try {
        f.createNewFile();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      FileOutputStream fos = null;
      try {
        fos = new FileOutputStream(f);
      } catch (FileNotFoundException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
      try {
        bw.write("package " + ConfigEnv.PACKAGE + ";");
        bw.newLine();
        bw.newLine();
        bw.write("public class " + ConfigUtils.capitalize(business) + "Data {");
        bw.newLine();
        bw.newLine();
        for (String property : properties) {
          property = property.trim();
          if (property.length() < 1)
            continue;
          bw.write("  private static String " + ConfigUtils.toVariable(property) + ";");
          bw.newLine();
          bw.newLine();
          bw.write("  public static String get"
              + ConfigUtils.capitalize(ConfigUtils.toVariable(property)) + "() {");
          bw.newLine();
          bw.write("    return " + ConfigUtils.toVariable(property) + ";");
          bw.newLine();
          bw.write("  }");
          bw.newLine();
          bw.newLine();
        }
        bw.write("}");
        bw.newLine();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        bw.flush();
        bw.close();
        fos.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    eraseDataClass();
    generateDataClass();
  }

}
