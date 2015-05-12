package config;

import org.apache.commons.lang.WordUtils;

/**
 * @author Zifeng Yuan
 */
public class ConfigUtils {

  public static String capitalize(String word) {
    return WordUtils.capitalize(word);
  }

  public static String toVariable(String param) {
    param = WordUtils.capitalizeFully(param, new char[] {'_'});
    param = param.replaceAll("_", "");
    return param.substring(0, 1).toLowerCase() + param.substring(1);
  }

}
