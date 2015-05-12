package config;

import java.lang.reflect.Field;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type;

/**
 * @author Zifeng Yuan
 */
public class BusinessListener implements PathChildrenCacheListener {

  private String business;

  public BusinessListener(String business) {
    // TODO Auto-generated constructor stub
    System.out.println("class : " + ConfigEnv.PACKAGE + "." + ConfigUtils.capitalize(business)
        + "Data");
    this.business = business;
  }

  @Override
  public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
    // TODO Auto-generated method stub
    if (Type.CHILD_UPDATED == event.getType() || Type.CHILD_ADDED == event.getType()) {
      System.out.println(event.toString());
      Class<?> data = null;
      data = Class.forName(ConfigEnv.PACKAGE + "." + ConfigUtils.capitalize(business) + "Data");
      Field field =
          data.getDeclaredField(ConfigUtils.toVariable(event.getData().getPath().substring(1)));
      field.setAccessible(true);
      field.set(data, new String(event.getData().getData()));
    }
  }

}
