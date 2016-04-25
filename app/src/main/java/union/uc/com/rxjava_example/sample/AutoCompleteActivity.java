package union.uc.com.rxjava_example.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import union.uc.com.rxjava_example.base.BaseActivity;
import union.uc.com.rxjava_example.base.UIThreadExecutor;

/**
 * Created by wangli on 4/5/16.
 */
public class AutoCompleteActivity extends BaseActivity {
  private EditText mSearchBox;
  private TextView mResult;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupRx();
  }

  @Override
  protected void setupWorkspace(LinearLayout workspace) {
    workspace.setOrientation(LinearLayout.VERTICAL);
    mSearchBox = new EditText(this);
    mSearchBox.setHintTextColor(Color.GRAY);
    mSearchBox.setHint("input your search key words ...");
    workspace.addView(mSearchBox,
                      new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                    LinearLayout.LayoutParams.WRAP_CONTENT));

    mResult = new TextView(this);
    mResult.setBackgroundColor(Color.LTGRAY);
    workspace.addView(mResult,
                      new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                    LinearLayout.LayoutParams.WRAP_CONTENT));
  }

  @Override
  protected String myDescription() {
    return "本示例展示如何监视用户输入:\r\n" +
           "1. 输入中包含字符串\"ab\"才会触发后续处理\r\n" +
           "2. 每隔1秒钟采集一次输入";
  }

  private void setupRx() {
    final PublishSubject<Editable> subject = PublishSubject.create();
    mSearchBox.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        subject.onNext(s);
      }
    });

    subject//
      .throttleLast(1, TimeUnit.SECONDS)//
      .subscribeOn(Schedulers.from(UIThreadExecutor.SINGLETON))//
      .observeOn(Schedulers.io())//
      .doOnSubscribe(new Action0() {
        @Override
        public void call() {
          appendTip("subscribe on:" + Thread.currentThread().getName());
        }
      }).flatMap(new Func1<Editable, Observable<String>>() {
      @Override
      public Observable<String> call(Editable editable) {
        appendTip("flatMap on:" + Thread.currentThread().getName());
        return Observable.just(editable.toString());
      }
    })//
      .filter(new Func1<String, Boolean>() {
        @Override
        public Boolean call(String s) {
          appendTip("filter on:" + Thread.currentThread().getName());
          boolean match = !TextUtils.isEmpty(s) && s.contains("ab");
          return match;
        }
      })//
        //      .compose(new Observable.Transformer<String, String>() {
        //        @Override
        //        public Observable<String> call(Observable<String> stringObservable) {
        //          Observable<String> ob = stringObservable.mergeWith(Observable.just("##"));
        //          ob//
        //            //            .reduce(new Func2<String, String, String>() {
        //            //              @Override
        //            //              public String call(String s, String s2) {
        //            //                return s + s2;
        //            //              }
        //            //            })//
        //            .map(new Func1<String, String>() {
        //              @Override
        //              public String call(String s) {
        //                return s + "##";
        //              }
        //            });
        //          return ob;
        //        }
        //      })//
      .retry(2)//
      .observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))//
      .map(new Func1<String, String>() {
        @Override
        public String call(String s) {
          appendTip("map on:" + Thread.currentThread().getName());
          if (TextUtils.isEmpty(s)) {
            return s;
          }
          return s.toUpperCase();
        }
      })//
      .subscribe(new Action1<String>() {//
        @Override
        public void call(String s) {
          appendTip("observer run on:" + Thread.currentThread().getName());
          mResult.setText(s);
        }
      });
  }
}
