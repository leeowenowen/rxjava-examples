package union.uc.com.rxjava_example.base;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by wangli on 4/5/16.
 */
public class AsyncExecutor {
  public static ScheduledExecutorService SINGLETON = Executors.newScheduledThreadPool(3);
}
