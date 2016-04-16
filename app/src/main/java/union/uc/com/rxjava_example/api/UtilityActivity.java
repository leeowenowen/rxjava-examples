package union.uc.com.rxjava_example.api;

import java.io.File;
import java.util.concurrent.TimeUnit;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.schedulers.TimeInterval;
import rx.schedulers.Timestamped;
import union.uc.com.rxjava_example.base.APIBaseActivity;

/**
 * Created by wangli on 4/12/16.
 */
public class UtilityActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add("materialize", new Runnable() {
      @Override
      public void run() {
        Observable o1 = Observable.range(1, 3).materialize();
        o1.subscribe(new Action1<Notification<Integer>>() {
          @Override
          public void call(Notification<Integer> integerNotification) {
            log("******");
            log("kind:" + integerNotification.getKind());
            log("value:" + integerNotification.getValue());
          }
        });
        o1.dematerialize().subscribe(new Action1() {
          @Override
          public void call(Object o) {
            log(o.toString());
          }
        });
      }
    });
    //    registery.add("dematerialize", new Runnable() {
    //      @Override
    //      public void run() {
    //      }
    //    });
    registery.add("timestamp", new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2).timestamp().subscribe(new Action1<Timestamped<Integer>>() {
          @Override
          public void call(Timestamped<Integer> integerTimestamped) {
            log("" + integerTimestamped.getValue() + " " + integerTimestamped.getTimestampMillis());
          }
        });
      }
    });
    registery.add("serialize", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 3).serialize().subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("cache", new Runnable() {
      @Override
      public void run() {
        Observable o = Observable.range(1, 10).cache();
        o.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("s1:" + integer);
          }
        });

        o.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("s2:" + integer);
          }
        });
      }
    });
    registery.add("observeOn", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 2).observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("" + integer + " on " + Thread.currentThread().getName());
          }
        });
      }
    });
    registery.add("doOnEach", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).doOnEach(new Action1<Notification<? super Integer>>() {
          @Override
          public void call(Notification<? super Integer> notification) {
            log("doOnEach:" + notification.getKind() + " " + notification.getValue());
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("fromRunnable", new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    registery.add("doOnCompleted", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 3).doOnCompleted(new Action0() {
          @Override
          public void call() {
            log("onCompleted");
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("doOnError", new Runnable() {
      @Override
      public void run() {
        Observable.just(1, "3").cast(Integer.class).doOnError(new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {
            log("doOnError:" + throwable.getMessage());
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        }, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {
            log(throwable);
          }
        });
      }
    });
    registery.add("doOnTerminate", new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2).doOnTerminate(new Action0() {
          @Override
          public void call() {
            log("OnTerminate");
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("doOnUnsubscribe", new Runnable() {
      @Override
      public void run() {
        Subscription subscription = Observable.just(1, 2).doOnSubscribe(new Action0() {
          @Override
          public void call() {
            log("OnSubscribe");
          }
        }).doOnUnsubscribe(new Action0() {
          @Override
          public void call() {
            log("OnUnSubscribe");
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
        subscription.unsubscribe();
      }
    });
    registery.add("finallyDo", new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2).finallyDo(new Action0() {
          @Override
          public void call() {
            log("finallyDo");
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("delay", new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2).delay(2, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("delaySubscription", new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2)
                  .delaySubscription(2, TimeUnit.SECONDS)
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  });
      }
    });
    registery.add("timeInterval", new Runnable() {
      @Override
      public void run() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            subscriber.onNext(1);
            sleep(1000);
            subscriber.onNext(2);
            subscriber.onCompleted();
          }
        })
                  .subscribeOn(Schedulers.newThread())
                  .timeInterval()
                  .subscribe(new Action1<TimeInterval<Integer>>() {
                    @Override
                    public void call(TimeInterval<Integer> integerTimeInterval) {
                      log("" + integerTimeInterval.getValue() + " " +
                          integerTimeInterval.getIntervalInMilliseconds());
                    }
                  });
      }
    });

    registery.add("using", new Runnable() {
      @Override
      public void run() {
        Observable.using(new Func0<File>() {
          @Override
          public File call() {
            File f = null;
            try {
              f = new File("ABC");
              if (!f.exists()) {
                f.createNewFile();
              }

            } catch (Exception e) {
              Observable.error(e);
            }
            return f;
          }
        }, new Func1<File, Observable<String>>() {
          @Override
          public Observable<String> call(File file) {
            return Observable.just(file.exists() ? "exist" : "not exist");
          }
        }, new Action1<File>() {
          @Override
          public void call(File file) {
            if (file != null && file.exists()) {
              file.delete();
            }
          }
        }).subscribe(new Action1<String>() {
          @Override
          public void call(String exist) {
            log(exist);
          }
        }, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {
            log(throwable);
          }
        });
      }
    });
    registery.add("single", new Runnable() {
      @Override
      public void run() {
        Observable.just(1).single().subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("singleOrDefault", new Runnable() {
      @Override
      public void run() {
        Observable.<Integer>empty().singleOrDefault(10).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
  }
}
