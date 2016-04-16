package union.uc.com.rxjava_example.plugin;

import android.content.Context;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import union.uc.com.rxjava_example.contants.Constants;
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
                         if (mKeyToCode == null) {
                           load();
                         }
                         return mKeyToCode.get(s);
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

  private Map<String, String> mKeyToCode;

  private void add(String key, String code) {
    mKeyToCode.put(key, code);
  }

  private void load() {
    mKeyToCode = new HashMap<>();
    String code = "" +
                  "    Observable o1 = Observable.range(1, 3).materialize();\n" +
                  "    o1.subscribe(new Action1<Notification<Integer>>() {\n" +
                  "      @Override\n" +
                  "      public void call(Notification<Integer> integerNotification) {\n" +
                  "        log(\"******\");\n" +
                  "        log(\"kind:\" + integerNotification.getKind());\n" +
                  "        log(\"value:\" + integerNotification.getValue());\n" +
                  "      }\n" +
                  "    });\n" +
                  "    o1.dematerialize().subscribe(new Action1() {\n" +
                  "      @Override\n" +
                  "      public void call(Object o) {\n" +
                  "        log(o.toString());\n" +
                  "      }\n" +
                  "    });\n";
    add(Constants.Utility.materialize, code);
  }
}
