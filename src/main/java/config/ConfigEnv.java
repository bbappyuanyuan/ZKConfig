package config;

/**
 * @author Zifeng Yuan
 */
public class ConfigEnv {

  public final static String PACKAGE = ConfigEnv.class.getPackage().toString().substring(8);
  public final static String PATH = "src/main/java/config/";
  public static final String CONNECT_STRING =
      "192.168.8.135:2181,192.168.8.231:2181,192.168.8.114:2181";

}
