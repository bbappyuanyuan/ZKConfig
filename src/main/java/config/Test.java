package config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Zifeng Yuan
 */
public class Test {

  public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    // TODO Auto-generated method stub
    System.out.println("started");
    Config.init();
    while (true) {
      Method[] methods1 = BusinessOneData.class.getMethods();
      Method[] methods2 = BusinessTwoData.class.getMethods();
      for (Method method : methods1) {
        if (!method.getName().startsWith("get"))
          continue;
        Object o = method.invoke(new BusinessOneData(), new Object[] {});
        if (o instanceof String) {
          System.out.println(method.getName() + ": " + (String) o);
        }
      }
      for (Method method : methods2) {
        if (!method.getName().startsWith("get"))
          continue;
        Object o = method.invoke(new BusinessTwoData(), new Object[] {});
        if (o instanceof String) {
          System.out.println(method.getName() + ": " + (String) o);
        }
      }
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

}
