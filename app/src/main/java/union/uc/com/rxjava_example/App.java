package union.uc.com.rxjava_example;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import union.uc.com.rxjava_example.plugin.DescriptionPlugin;
import union.uc.com.rxjava_example.plugin.DisplayPluginManager;
import union.uc.com.rxjava_example.plugin.MarbleDiagramPlugin;
import union.uc.com.rxjava_example.plugin.SampleCodePlugin;

/**
 * Created by wangli on 4/16/16.
 */
public class App extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    //init
    // Create global configuration and initialize ImageLoader with this config
    ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(this);
    ImageLoader.getInstance().init(config);

    //init DisplayPluginManager
    DisplayPluginManager dpm = DisplayPluginManager.singleton();
    dpm.register(new MarbleDiagramPlugin());
    dpm.register(new DescriptionPlugin());
    dpm.register(new SampleCodePlugin());

  }
}
