package union.uc.com.rxjava_example.api;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.joins.Plan0;
import rx.observables.JoinObservable;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.APIBaseActivity;

/**
 * Created by wangli on 4/12/16.
 */
public class CombineActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    //emit a specified sequence of items before beginning to emit the items from the Observable
    registery.add("startWith", new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    //combine multiple Observables into one
    registery.add("merge", new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2, 3)
                  .mergeWith(Observable.just(4, 5, 6))
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  });
      }
    });
    // combine multiple Observables into one, allowing error-free Observables to continue before propagating errors
    registery.add("mergeDelayError", new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    //combine sets of items emitted by two or more Observables together via a specified function and emit items based on the results of this function
    registery.add("zip", new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2, 3)
                  .zipWith(Observable.just("a", "b", "c"), new Func2<Integer, String, String>() {
                    @Override
                    public String call(Integer integer, String s) {
                      return s + integer * 2;
                    }
                  })
                  .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                      log(s);
                    }
                  });
      }
    });
    //combine sets of items emitted by two or more Observables by means of Pattern and Plan intermediaries
    registery.add("and then when", new Runnable() {
      @Override
      public void run() {
        Plan0<String> p = //
          JoinObservable.from(Observable.just(1, 2, 3))//
                        .and(Observable.just("a", "b", "c"))//
                        .and(Observable.just("d", "e"))//
                        .then(new Func3<Integer, String, String, String>() {
                          @Override
                          public String call(Integer integer, String s, String s2) {
                            String ret = "" + integer + " " + s + " " + s2;
                            return ret;
                          }
                        });
        JoinObservable.when(p).toObservable().subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            log(s);
          }
        });
      }
    });

    //    registery.add("then", new Runnable() {
    //      @Override
    //      public void run() {
    //
    //      }
    //    });
    //    registery.add("when", new Runnable() {
    //      @Override
    //      public void run() {
    //
    //      }
    //    });

    //when an item is emitted by either of two Observables, combine the latest item emitted by each Observable via a specified function and emit items based on the results of this function
    registery.add("combineLatest", new Runnable() {
      @Override
      public void run() {
        Observable.combineLatest(Observable.create(new Observable.OnSubscribe<Integer>() {
                                   @Override
                                   public void call(Subscriber<? super Integer> subscriber) {
                                     for (int i = 0; i < 10; ++i) {
                                       subscriber.onNext(i);
                                       sleep(1000);
                                     }
                                   }
                                 }).subscribeOn(Schedulers.newThread()),
                                 Observable.create(new Observable.OnSubscribe<String>() {
                                   @Override
                                   public void call(Subscriber<? super String> subscriber) {
                                     final String[] arr = new String[] {"a", "b", "c"};
                                     for (int i = 0; i < arr.length; ++i) {
                                       subscriber.onNext(arr[i]);
                                       sleep(400);
                                     }
                                   }
                                 }).subscribeOn(Schedulers.newThread()),
                                 new Func2<Integer, String, String>() {
                                   @Override
                                   public String call(Integer integer, String s) {
                                     return "" + integer + " " + s;
                                   }
                                 }).subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            log(s);
          }
        });
      }
    });

    //join( ) and groupJoin( ) — combine the items emitted by two Observables whenever one item from one Observable falls within a window of duration specified by an item emitted by the other Observable
    registery.add("join", new Runnable() {
                    @Override
                    public void run() {
                      Observable.create(new Observable.OnSubscribe<Integer>() {
                        @Override
                        public void call(Subscriber<? super Integer> subscriber) {
                          for (int i = 0; i < 10; ++i) {
                            subscriber.onNext(i);
                            sleep(1000);
                          }
                        }
                      })
                                .subscribeOn(Schedulers.newThread())
                                .join(Observable.create(new Observable.OnSubscribe<String>() {
                                  @Override
                                  public void call(Subscriber<? super String> subscriber) {
                                    final String[] arr = new String[] {"a", "b", "c"};
                                    for (int i = 0; i < arr.length; ++i) {
                                      subscriber.onNext(arr[i]);
                                      sleep(400);
                                    }
                                  }
                                }).subscribeOn(Schedulers.newThread()), new Func1<Integer, Observable<Long>>() {
                                  @Override
                                  public Observable<Long> call(Integer integer) {
                                    return Observable.timer(1, TimeUnit.SECONDS);
                                  }
                                }, new Func1<String, Observable<Long>>() {
                                  @Override
                                  public Observable<Long> call(String s) {
                                    return Observable.timer(2, TimeUnit.SECONDS);
                                  }
                                }, new Func2<Integer, String, String>() {
                                  @Override
                                  public String call(Integer integer, String s) {
                                    return " " + integer + " " + s;
                                  }
                                })
                                .subscribe(new Action1<String>() {
                                  @Override
                                  public void call(String s) {
                                    log(s);
                                  }
                                });
                    }
                  }

    );


    registery.add("groupjoin", new Runnable() {
      @Override
      public void run() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            for (int i = 0; i < 10; ++i) {
              subscriber.onNext(i);
              sleep(1000);
            }
          }
        })
                  .subscribeOn(Schedulers.newThread())
                  .groupJoin(Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                      final String[] arr = new String[] {"a", "b", "c"};
                      for (int i = 0; i < arr.length; ++i) {
                        subscriber.onNext(arr[i]);
                        sleep(400);
                      }
                    }
                  }).subscribeOn(Schedulers.newThread()), new Func1<Integer, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Integer integer) {
                      return Observable.timer(1, TimeUnit.SECONDS);
                    }
                  }, new Func1<String, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(String s) {
                      return Observable.timer(2, TimeUnit.SECONDS);
                    }
                  }, new Func2<Integer, Observable<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(final Integer integer,
                                                   Observable<String> stringObservable) {
                      return stringObservable.map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                          return " " + integer + s;
                        }
                      });
                    }
                  })
                  .subscribe(new Action1<Observable<String>>() {
                    @Override
                    public void call(Observable<String> o) {
                      o.subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                          log(s);
                        }
                      });
                    }
                  });
      }
    });
    //convert an Observable that emits Observables into a single Observable that emits the items
    //emitted by the most-recently emitted of those Observables
    registery.add("switchIfEmpty", new Runnable() {
                    @Override
                    public void run() {
                      Observable.<Integer>empty().switchIfEmpty(Observable.just(4, 5))
                                                 .subscribe(new Action1<Integer>() {
                                                   @Override
                                                   public void call(Integer integer) {
                                                     log(integer);
                                                   }
                                                 });
                    }
                  }

    );

    registery.add("switchOnNext", new Runnable() {
                    @Override
                    public void run() {
                      logNotImplemented();
                    }
                  }

    );
  }
}
