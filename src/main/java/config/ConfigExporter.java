package config;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;

/**
 * Export all configs.
 * 
 * @author Zifeng Yuan
 */
public class ConfigExporter {

  public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub
    System.out.println("=======================Config Table=======================");
    ClientFactory.init(ConfigEnv.CONNECT_STRING);
    CuratorFramework client = ClientFactory.getClient("");
    client.start();
    List<String> businesses = client.getChildren().forPath("/");
    for (String business : businesses) {
      List<String> properties = client.getChildren().forPath("/" + business);
      for (String property : properties)
        System.out.println(business + "/" + property + "="
            + new String(client.getData().forPath("/" + business + "/" + property)));
    }
    client.close();
    System.out.println("=======================Config Table=======================");
  }

}
