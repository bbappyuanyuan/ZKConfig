package config;

import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.curator.framework.CuratorFramework;

/**
 * Import configs from config-import.properties (they'll cover the old ones).
 * 
 * @author Zifeng Yuan
 */
public class ConfigImporter {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("=======================Config Table=======================");
    ClientFactory.init(ConfigEnv.CONNECT_STRING);
    CuratorFramework client = ClientFactory.getClient("");
    client.start();
    PropertiesConfiguration configList = null;
    try {
      configList = new PropertiesConfiguration("config-import.properties");
    } catch (ConfigurationException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    Iterator<String> keys = configList.getKeys();
    while (keys.hasNext()) {
      String key = keys.next();
      String value = configList.getString(key);
      String path = "/" + key;
      try {
        client.create().creatingParentsIfNeeded().forPath(path);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        // e.printStackTrace();
      }
      try {
        client.setData().forPath(path, value.getBytes());
        System.out.println(key + "=" + value);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    client.close();
    System.out.println("=======================Config Table=======================");
  }

}
