package union.uc.com.rxjava_example.plugin;

import android.content.Context;
import android.view.View;

import rx.Observable;
import rx.functions.Func1;
import us.feras.mdv.MarkdownView;

/**
 * Created by wangli on 4/16/16.
 */
public class SampleCodePlugin implements DisplayPluginManager.Plugin {
  @Override
  public Observable<View> getView(final Context context, String key) {
    return Observable.just(key)
                     // .observeOn(Schedulers.io())
                     .map(new Func1<String, String>() {
                       @Override
                       public String call(String s) {
                         return mSampleCode.get(s);
                       }
                     })
                     // .observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))
                     .map(new Func1<String, View>() {
                       @Override
                       public View call(String s) {
                         MarkdownView markdownView = new MarkdownView(context);
                         markdownView.loadMarkdown(s);
                         return markdownView;
                       }
                     });
  }

  private SampleCode mSampleCode = new SampleCode();
}
