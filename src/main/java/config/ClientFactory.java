package config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFrameworkFactory.Builder;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author Zifeng Yuan
 */
public class ClientFactory {

  private static Builder builder = null;

  public static void init(String connectString) {
    builder =
        CuratorFrameworkFactory.builder().connectString(connectString).connectionTimeoutMs(5000)
            .sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .canBeReadOnly(true);
  }

  public static CuratorFramework getClient(String business) {
    String namespace = "config";
    if (!business.equals(""))
      namespace = namespace + "/" + business;
    builder.namespace(namespace);
    return builder.build();
  }
}
