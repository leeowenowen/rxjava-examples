package union.uc.com.rxjava_example.api;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.StringObservable;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class StringActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.Strings.byLine, new Runnable() {
      @Override
      public void run() {
        StringObservable.byLine(Observable.just("ab\r\ncd\r\nef", "12\r\n34"))
                        .subscribe(new Action1<String>() {
                          @Override
                          public void call(String s) {
                            log(s);
                          }
                        });
      }
    });
    registery.add(Constants.Strings.decode, new Runnable() {
      @Override
      public void run() {
        StringObservable.decode(Observable.just("ABC".getBytes(Charset.forName("UTF-8"))),
                                Charset.forName("UTF-8")).subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            log(s);
          }
        });
      }
    });
    registery.add(Constants.Strings.encode, new Runnable() {
      @Override
      public void run() {
        StringObservable.encode(Observable.just("abc"), "UTF-8").subscribe(new Action1<byte[]>() {
          @Override
          public void call(byte[] bytes) {
            log(bytes.length);
          }
        });
      }
    });
    registery.add(Constants.Strings.from, new Runnable() {
      @Override
      public void run() {
        StringObservable.from(new ByteArrayInputStream("ABC".getBytes()))
                        .subscribe(new Action1<byte[]>() {
                          @Override
                          public void call(byte[] bytes) {
                            log(bytes.length);
                          }
                        });
      }
    });
    registery.add(Constants.Strings.join, new Runnable() {
      @Override
      public void run() {
        StringObservable.join(Observable.just("abc", "def"), "#").subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            log(s);
          }
        });
      }
    });
    registery.add(Constants.Strings.split, new Runnable() {
      @Override
      public void run() {
        StringObservable.split(Observable.just("ab#cd#ef"), "#").subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            log(s);
          }
        });
      }
    });
    registery.add(Constants.Strings.stringConcat, new Runnable() {
      @Override
      public void run() {
        StringObservable.stringConcat(Observable.just("abc", "def"))
                        .subscribe(new Action1<String>() {
                          @Override
                          public void call(String s) {
                            log(s);
                          }
                        });
      }
    });
  }
}
