package union.uc.com.rxjava_example.api;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.APIBaseActivity;

/**
 * Created by wangli on 4/12/16.
 */
public class ConditionActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add("amb", new Runnable() {
      @Override
      public void run() {
        Observable.amb(Observable.create(new Observable.OnSubscribe<Integer>() {
                         @Override
                         public void call(Subscriber<? super Integer> subscriber) {
                           sleep(1000);
                           subscriber.onNext(1);
                           subscriber.onNext(11);
                           subscriber.onCompleted();
                         }
                       }).subscribeOn(Schedulers.newThread()),
                       Observable.create(new Observable.OnSubscribe<Integer>() {
                         @Override
                         public void call(Subscriber<? super Integer> subscriber) {
                           sleep(500);
                           subscriber.onNext(2);
                           subscriber.onNext(22);
                           subscriber.onCompleted();
                         }
                       }).subscribeOn(Schedulers.newThread()),
                       Observable.create(new Observable.OnSubscribe<Integer>() {
                         @Override
                         public void call(Subscriber<? super Integer> subscriber) {
                           sleep(300);
                           subscriber.onNext(3);
                           subscriber.onNext(33);
                           subscriber.onCompleted();
                         }
                       }).subscribeOn(Schedulers.newThread())).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("defaultIfEmpty", new Runnable() {
      @Override
      public void run() {
        Observable.<Integer>empty().defaultIfEmpty(3).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("doWhile", new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    registery.add("ifThen", new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    registery.add("skipUtil", new Runnable() {
      @Override
      public void run() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            for (int i = 0; i < 10; ++i) {
              sleep(200);
              subscriber.onNext(i);
            }
            subscriber.onCompleted();
          }
        })
                  .subscribeOn(Schedulers.newThread())
                  .skipUntil(Observable.timer(1, TimeUnit.SECONDS))
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  });
      }
    });
    registery.add("skipWhile", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).skipWhile(new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer i) {
            return i < 3;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("switchcase", new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    registery.add("takeUntil", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).takeUntil(new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer i) {
            return i > 3;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("takeWhile", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).takeWhile(new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer i) {
            return i < 3;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("takeWhileWithIndex", new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    registery.add("WhileDo", new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
  }
}
