package union.uc.com.rxjava_example.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.base.AsyncExecutor;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class FilterActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.Filter.filter, new Runnable() {
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
    registery.add(Constants.Filter.takeLast, new Runnable() {
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
    registery.add(Constants.Filter.last, new Runnable() {
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
    registery.add(Constants.Filter.lastOrDefault, new Runnable() {
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
    registery.add(Constants.Filter.takeLastBuffer, new Runnable() {
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
    registery.add(Constants.Filter.skip, new Runnable() {
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
    registery.add(Constants.Filter.skipLast, new Runnable() {
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
    registery.add(Constants.Filter.take, new Runnable() {
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
    registery.add(Constants.Filter.first, new Runnable() {
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
    registery.add(Constants.Filter.takeFirst, new Runnable() {
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
    registery.add(Constants.Filter.firstOrDefault, new Runnable() {
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
    registery.add(Constants.Filter.elementAt, new Runnable() {
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
    registery.add(Constants.Filter.elementAtOrDefault, new Runnable() {
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
    registery.add(Constants.Filter.sample, new Runnable() {
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
    registery.add(Constants.Filter.throttleLast, new Runnable() {
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
    registery.add(Constants.Filter.throttleFirst, new Runnable() {
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
    registery.add(Constants.Filter.throttleWithTimeout, new Runnable() {
      @Override
      public void run() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            for (int i = 0; i < 10; i++) {
              subscriber.onNext(i);
              sleep(500);
            }
            subscriber.onCompleted();
          }
        })
                  .subscribeOn(Schedulers.newThread())
                  .throttleWithTimeout(2, TimeUnit.SECONDS)
                  .observeOn(Schedulers.newThread())
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  });
      }
    });
    registery.add(Constants.Filter.debounce, new Runnable() {
      @Override
      public void run() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            subscriber.onNext(1);
            sleep(500);
            subscriber.onNext(2);
            sleep(1000);
            subscriber.onNext(3);
            sleep(2000);
            subscriber.onNext(4);
            subscriber.onCompleted();
          }
        })
                  .subscribeOn(Schedulers.newThread())
                  .debounce(1, TimeUnit.SECONDS)
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  });
      }
    });
    registery.add(Constants.Filter.timeout, new Runnable() {
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
    registery.add(Constants.Filter.distinct, new

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
      });
    registery.add(Constants.Filter.distinctUntilChanged, new Runnable() {
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
    });
    registery.add(Constants.Filter.ofType, new Runnable() {
                    @Override
                    public void run() {
                      Observable.<Object>//
                                           just(1, "2", //
                                                new Exception("abc")).
                                           ofType(Integer.class).subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer integer) {
                          log(integer);
                        }
                      });
                    }
                  }

    );
    registery.add(Constants.Filter.ignoreElements, new Runnable() {
                    @Override
                    public void run() {
                      Observable.create(new Observable.OnSubscribe<Integer>() {
                        @Override
                        public void call(Subscriber<? super Integer> subscriber) {
                          subscriber.onNext(1);
                          subscriber.onNext(2);
                          subscriber.onCompleted();
                        }
                      }).ignoreElements().subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer integer) {
                          log(integer);
                        }
                      }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                          log(throwable);
                        }
                      }, new Action0() {
                        @Override
                        public void call() {
                          log("onComplete");
                        }
                      });
                    }
                  }

    );
  }
}
