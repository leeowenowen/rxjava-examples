package union.uc.com.rxjava_example.sample;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;
import union.uc.com.rxjava_example.base.BaseActivity;

/**
 * Created by wangli on 4/8/16.
 */
public class OtherAPIActivity extends BaseActivity {
  @Override
  protected String myDescription() {
    return "本示例为展示多种API使用方式.";
  }

  @Override
  protected void setupWorkspace(LinearLayout workspace) {
    workspace.setOrientation(LinearLayout.VERTICAL);
    Button btn = new Button(this);
    btn.setText("Start");
    workspace.addView(btn);
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startRx();
      }
    });
  }

  private void startRx() {

    //reduce
    Observable<Integer> o1 = Observable.range(1, 3);
    Observable<String> o2 = o1.map(new Func1<Integer, String>() {
      @Override
      public String call(Integer integer) {
        return (integer % 2 == 0) ? "even" : "odd";
      }
    });
    o2.subscribe(new Action1<String>() {
      @Override
      public void call(String string) {
        System.out.println(string);
      }
    });

    Observable.range(1, 3).map(new Func1<Integer, String>() {
      @Override
      public String call(Integer integer) {
        return (integer % 2 == 0) ? "even" : "odd";
      }
    }).subscribe(new Action1<String>() {
      @Override
      public void call(String string) {
        System.out.println(string);
      }
    });


    //scan
    //    Observable.range(1, 10)//
    //              .map(new Func1<Integer, Integer>() {
    //                @Override
    //                public Integer call(Integer integer) {
    //                  return (integer == null) ? null : integer * 2;
    //                }
    //              })//
    //              .scan(new Func2<Integer, Integer, Integer>() {
    //                @Override
    //                public Integer call(Integer integer, Integer integer2) {
    //                  return integer + integer2;
    //                }
    //              })//
    //              .subscribe(new Action1<Integer>() {
    //                @Override
    //                public void call(Integer integer) {
    //                  appendTip("scan 1-10 result:" + integer);
    //                }
    //              });
    //    Observable.range(1, 10).repeat(2).subscribe(new Action1<Integer>() {
    //      @Override
    //      public void call(Integer integer) {
    //        appendTip("repeat 1-10 result:" + integer);
    //      }
    //    });
    //    Observable.range(1, 10).replay(new Func1<Observable<Integer>, Observable<String>>() {
    //      @Override
    //      public Observable<String> call(Observable<Integer> integerObservable) {
    //        return null;
    //      }
    //    }).subscribe(new Action1<String>() {
    //      @Override
    //      public void call(String string) {
    //        appendTip("repeat 1-10 result:" + string);
    //      }
    //    });
    //    class A{
    //
    //    }
    //    Observable.range(1, 10).cast(A.class).subscribe(new Action1<A>() {
    //      @Override
    //      public void call(A a) {
    //
    //      }
    //    });
    {
      Observable<Integer> o = Observable.range(0, 3);
      o.subscribe(new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
          appendTip("range:" + integer);
        }
      });
      o.count().subscribe(new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
          appendTip("count:" + integer);
        }
      });
    }
    {
      ReplaySubject<String> r = ReplaySubject.create();
      r.onNext("1");
      r.onNext("2");
      r.subscribe(new Action1<String>() {
        @Override
        public void call(String s) {
          appendTip("replay:" + s);
        }
      });
      r.onNext("3");
    }
    {
      ReplaySubject<Integer> r= ReplaySubject.createWithSize(2);
      r.onNext(1);
      r.onNext(2);
      r.onNext(3);
      r.subscribe(new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
          appendTip("replay create with size:" + integer);
        }
      });
      r.onNext(4);
    }
    try
    {
      ReplaySubject<Integer> r= ReplaySubject.createWithTime(150, TimeUnit.MILLISECONDS,
                                                             Schedulers.immediate());
      r.onNext(1);
      Thread.sleep(100);
      r.onNext(2);
      Thread.sleep(100);
      r.onNext(3);
      r.subscribe(new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
          appendTip("replay create with time:" + integer);
        }
      });
      r.onNext(4);
    }catch(Exception e){}
  }
  {

  }
}
