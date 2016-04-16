package union.uc.com.rxjava_example.base;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by wangli on 4/5/16.
 */
public class UIThreadExecutor implements Executor {
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  public static UIThreadExecutor SINGLETON = new UIThreadExecutor();

  @Override
  public void execute(Runnable command) {
    mHandler.post(command);
  }
}
