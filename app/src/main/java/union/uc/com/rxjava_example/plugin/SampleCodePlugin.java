package union.uc.com.rxjava_example.plugin;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.Tuple;
import union.uc.com.rxjava_example.base.UIThreadExecutor;
import us.feras.mdv.MarkdownView;

/**
 * Created by wangli on 4/16/16.
 */
public class SampleCodePlugin implements DisplayPluginManager.Plugin {
  @Override
  public Tuple.Tuple2<Observable<View>, View> getView(final Context context, String key) {
    MarkdownView markdownView = new MarkdownView(context){
      @Override
      public boolean onTouchEvent(MotionEvent event) {
        requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
      }
    };
    markdownView.setBackgroundColor(Color.LTGRAY);
    final Reference<MarkdownView> ref = new WeakReference<>(markdownView);
    Observable<View> o = Observable.just(key)
                                   // .observeOn(Schedulers.io())
                                   .map(new Func1<String, String>() {
                                     @Override
                                     public String call(String s) {
                                       return mSampleCode.get(s);
                                     }
                                   })
                                   .observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))
                                   .map(new Func1<String, View>() {
                                     @Override
                                     public View call(String s) {
                                       MarkdownView mv = ref.get();
                                       if (mv != null) {
                                         mv.loadMarkdown(s);
                                       }
                                       return mv;
                                     }
                                   });
    return new Tuple.Tuple2<>(o, (View) markdownView);
  }

  private SampleCode mSampleCode = new SampleCode();
}
