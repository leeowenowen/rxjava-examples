package union.uc.com.rxjava_example.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Statement;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class ConditionActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.Condition.amb, new Runnable() {
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
    registery.add(Constants.Condition.defaultIfEmpty, new Runnable() {
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
    registery.add(Constants.Condition.doWhile, new Runnable() {
      @Override
      public void run() {
        Statement.doWhile(Observable.range(1, 10), new Func0<Boolean>() {
          boolean r = false;

          @Override
          public Boolean call() {
            r = !r;
            return r;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add(Constants.Condition.ifThen, new Runnable() {
      @Override
      public void run() {
        Statement.ifThen(new Func0<Boolean>() {
          boolean r = false;

          @Override
          public Boolean call() {
            r = !r;
            return r;
          }
        }, Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add(Constants.Condition.skipUtil, new Runnable() {
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
    registery.add(Constants.Condition.skipWhile, new Runnable() {
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
    registery.add(Constants.Condition.switchcase, new Runnable() {
      @Override
      public void run() {
        Observable<Integer> source1 = Observable.just(1, 2, 3);
        Observable<Integer> source2 = Observable.just(4, 5, 6);

        Map<Integer, Observable<Integer>> map = new HashMap<Integer, Observable<Integer>>();
        map.put(1, source1);
        map.put(2, source2);

        Statement.switchCase(new Func0<Integer>() {
          int count = 1;

          @Override
          public Integer call() {
            return count++;
          }
        }, map).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add(Constants.Condition.takeUntil, new Runnable() {
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
    registery.add(Constants.Condition.takeWhile, new Runnable() {
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
    registery.add(Constants.Condition.takeWhileWithIndex, new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    registery.add(Constants.Condition.WhileDo, new Runnable() {
      @Override
      public void run() {
        Statement.whileDo(Observable.just(1, 2, 3), new Func0<Boolean>() {
          int count = 2;

          @Override
          public Boolean call() {
            return count-- > 0;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add(Constants.Condition.all, new Runnable() {
      @Override
      public void run() {
        Observable.just(2, 4, 6).all(new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer integer) {
            return integer % 2 == 0;
          }
        }).subscribe(new Action1<Boolean>() {
          @Override
          public void call(Boolean aBoolean) {
            log(aBoolean);
          }
        });
      }
    });
    registery.add(Constants.Condition.contains, new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).contains(3).subscribe(new Action1<Boolean>() {
          @Override
          public void call(Boolean aBoolean) {
            log(aBoolean);
          }
        });
      }
    });
    registery.add(Constants.Condition.exists, new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2, 3).exists(new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer integer) {
            return integer > 3;
          }
        }).subscribe(new Action1<Boolean>() {
          @Override
          public void call(Boolean aBoolean) {
            log(aBoolean);
          }
        });
      }
    });
    registery.add(Constants.Condition.isEmpty, new Runnable() {
      @Override
      public void run() {
        Observable.empty().isEmpty().subscribe(new Action1<Boolean>() {
          @Override
          public void call(Boolean aBoolean) {
            log(aBoolean);
          }
        });
      }
    });
    registery.add(Constants.Condition.sequenceEqual, new Runnable() {
      @Override
      public void run() {
        Observable.sequenceEqual(Observable.just(1, 2, 3),
                                 Observable.range(1, 3),
                                 new Func2<Integer, Integer, Boolean>() {
                                   @Override
                                   public Boolean call(Integer integer1, Integer integer2) {
                                     return integer1 == integer2;
                                   }
                                 }).subscribe(new Action1<Boolean>() {
          @Override
          public void call(Boolean aBoolean) {
            log(aBoolean);
          }
        });
      }
    });
  }
}
