package union.uc.com.rxjava_example.plugin;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import union.uc.com.rxjava_example.base.Tuple;
import union.uc.com.rxjava_example.base.UIThreadExecutor;

/**
 * Created by wangli on 4/16/16.
 */
//run on init and ui thread
public class DisplayPluginManager {
  private static final DisplayPluginManager INSTANCE = new DisplayPluginManager();
  private List<Plugin> mPlugins = new ArrayList<>();

  public static DisplayPluginManager singleton() {
    return INSTANCE;
  }

  public interface Plugin {
    Tuple.Tuple2<Observable<View>, View> getView(Context context, String key);
  }

  public void register(final Plugin plugin) {
    UIThreadExecutor.SINGLETON.execute(new Runnable() {
      @Override
      public void run() {
        mPlugins.add(plugin);
      }
    });
  }

  public void unregister(final Plugin plugin) {
    UIThreadExecutor.SINGLETON.execute(new Runnable() {
      @Override
      public void run() {
        mPlugins.remove(plugin);
      }
    });
  }

  public List<Plugin> getAll() {
    return mPlugins;
  }

}
