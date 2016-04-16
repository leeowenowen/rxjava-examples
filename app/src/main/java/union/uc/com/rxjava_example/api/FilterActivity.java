package union.uc.com.rxjava_example.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.base.AsyncExecutor;

/**
 * Created by wangli on 4/12/16.
 */
public class FilterActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    //filter items emitted by an Observable
    registery.add("filter", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).filter(new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer integer) {
            return integer % 2 == 0;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("takeLast", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).takeLast(3).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("last", new Runnable() {
      @Override
      public void run() {
        //normal last
        Observable.range(1, 10).last().subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
        // last with predicate
        Observable.range(1, 10).last(new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer integer) {
            return integer % 2 == 0;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("lastOrDefault", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).lastOrDefault(10).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
        Observable.range(1, 10).lastOrDefault(10, new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer integer) {
            return integer % 2 == 0;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("takeLastBuffer", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10)
                  .subscribeOn(Schedulers.newThread())
                  .takeLastBuffer(3)
                  .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                      String s = "";
                      for (Integer i : integers) {
                        s += i;
                      }
                      log(s);
                    }
                  });

        Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            for (int i = 0; i < 10; i++) {
              subscriber.onNext(i);
              sleep(100);
            }
          }
        })
                  .subscribeOn(Schedulers.newThread())
                  .takeLastBuffer(3, 100, TimeUnit.MILLISECONDS)
                  .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                      String s = "";
                      for (Integer i : integers) {
                        s += i;
                      }
                      log(s);
                    }
                  });
      }
    });
    registery.add("skip", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).skip(2).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("skipLast", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).skipLast(3).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("take", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).take(3).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("first", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).first().subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("takeFirst", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).takeFirst(new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer integer) {
            return integer % 2 == 0;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("firstOrDefault", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).firstOrDefault(3).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("elementAt", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).elementAt(3).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("elementAtOrDefault", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).elementAtOrDefault(100, 1000).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("sample", new Runnable() {
      @Override
      public void run() {
        final Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            for (int i = 0; i < 10; i++) {
              subscriber.onNext(i);
              sleep(100);
            }
          }
        })
                                                    .subscribeOn(Schedulers.newThread())
                                                    .sample(1, TimeUnit.SECONDS)
                                                    .subscribe(new Action1<Integer>() {
                                                      @Override
                                                      public void call(Integer integer) {
                                                        log(integer);

                                                      }
                                                    });
        AsyncExecutor.SINGLETON.schedule(new Runnable() {
          @Override
          public void run() {
            if (!subscription.isUnsubscribed()) {
              subscription.unsubscribe();
            }
          }
        }, 3, TimeUnit.SECONDS);
      }
    });
    registery.add("throttleLast", new Runnable() {
      @Override
      public void run() {
        final Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            for (int i = 0; i < 5; i++) {
              subscriber.onNext(i);
              sleep(300);
            }
          }
        }).throttleLast(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("throttleFirst", new Runnable() {
      @Override
      public void run() {
        final Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            for (int i = 0; i < 5; i++) {
              subscriber.onNext(i);
              sleep(300);
            }
          }
        }).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
        AsyncExecutor.SINGLETON.schedule(new Runnable() {
          @Override
          public void run() {
            if (!subscription.isUnsubscribed()) {
              subscription.unsubscribe();
            }
          }
        }, 3, TimeUnit.SECONDS);
      }
    });
    registery.add("throttleWithTimeout", new Runnable() {
      @Override
      public void run() {
        Observable.just(1)
                  .throttleWithTimeout(2, TimeUnit.SECONDS)
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  });
      }
    });
    registery.add("debounce", new Runnable() {
      @Override
      public void run() {
        Observable.just(1).debounce(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("timeout", new Runnable() {
      @Override
      public void run() {
        Observable.<Integer>never().timeout(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {
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
    registery.add("distinct", new

      Runnable() {
        @Override
        public void run() {
          Observable.just(1, 1, 2, 2, 3, 4, 4, 1, 1, 5)
                    .distinct()
                    .subscribe(new Action1<Integer>() {
                      @Override
                      public void call(Integer integer) {
                        log(integer);
                      }
                    });
        }
      }

    );
    registery.add("distinctUntilChanged", new

      Runnable() {
        @Override
        public void run() {
          Observable.just(1, 1, 2, 2, 3, 4, 4, 1, 1, 5)
                    .distinctUntilChanged()
                    .subscribe(new Action1<Integer>() {
                      @Override
                      public void call(Integer integer) {
                        log(integer);
                      }
                    });
        }
      }

    );
    registery.add("ofType", new

      Runnable() {
        @Override
        public void run() {
          Observable.<Object>just(1, "2", new Exception("abc")).
          ofType(Integer.class).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
              log(integer);
            }
          });
        }
      }

    ); registery.add("ignoreElements", new

      Runnable() {
        @Override
        public void run() {
          Observable.just(1, 2, 3).ignoreElements().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
              log(integer);
            }
          });
        }
      }

    );
  }
}
