package config;

import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;

/**
 * @author Zifeng Yuan
 */
public class Config {

  public static void init() {
    ClientFactory.init(ConfigEnv.CONNECT_STRING);
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
      CuratorFramework client = ClientFactory.getClient(business);
      client.start();
      PathChildrenCache cache = new PathChildrenCache(client, "/", true);
      try {
        cache.start();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      cache.getListenable().addListener(new BusinessListener(ConfigUtils.toVariable(business)));
    }
  }

}
