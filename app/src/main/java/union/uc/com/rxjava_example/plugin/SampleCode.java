
package union.uc.com.rxjava_example.plugin;

import java.util.HashMap;
import java.util.Map;
import union.uc.com.rxjava_example.contants.Constants;

public class SampleCode{
  private Map<String, String> mCodes = new HashMap<>();
  public SampleCode(){

mCodes.put(Constants.BlockingObservable.singleOrDefault,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onCompleted();"+
"      }"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().singleOrDefault(3000);"+
"    log(i);"+
"  }"+
"});"+
"registery.add(\"toFuture\", new Runnable() {"+
"  @Override"+
"    Future<Integer> future = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(2);"+
"        subscriber.onCompleted();"+
"      }"+
"    try {"+
"      log(future.get());"+
"    } catch (InterruptedException e) {"+
"      e.printStackTrace();"+
"      log(e);"+
"    } catch (ExecutionException e) {"+
"      e.printStackTrace();"+
"      log(e);"+
"    }"+
"  }");
mCodes.put(Constants.ErrorHandler.onErrorResumeNext,
"    Observable.just(1, \"abc\")"+
"              .cast(Integer.class)"+
"              .onErrorResumeNext(Observable.just(1, 2))"+
"              .subscribe(new Action1<Integer>() {"+
"                @Override"+
"                public void call(Integer integer) {"+
"                  log(integer);"+
"                }"+
"              }, new Action1<Throwable>() {"+
"                @Override"+
"                public void call(Throwable throwable) {"+
"                  log(throwable);"+
"                }"+
"  }");
mCodes.put(Constants.Combine.groupjoin,
"    Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        for (int i = 0; i < 10; ++i) {"+
"          subscriber.onNext(i);"+
"          sleep(1000);"+
"        }"+
"      }"+
"    })"+
"              .subscribeOn(Schedulers.newThread())"+
"              .groupJoin(Observable.create(new Observable.OnSubscribe<String>() {"+
"                @Override"+
"                public void call(Subscriber<? super String> subscriber) {"+
"                  final String[] arr = new String[] {\"a\", \"b\", \"c\"};"+
"                  for (int i = 0; i < arr.length; ++i) {"+
"                    subscriber.onNext(arr[i]);"+
"                    sleep(400);"+
"                  }"+
"                }"+
"              }).subscribeOn(Schedulers.newThread()), new Func1<Integer, Observable<Long>>() {"+
"                @Override"+
"                public Observable<Long> call(Integer integer) {"+
"                  return Observable.timer(1, TimeUnit.SECONDS);"+
"                }"+
"              }, new Func1<String, Observable<Long>>() {"+
"                @Override"+
"                public Observable<Long> call(String s) {"+
"                  return Observable.timer(2, TimeUnit.SECONDS);"+
"                }"+
"              }, new Func2<Integer, Observable<String>, Observable<String>>() {"+
"                @Override"+
"                public Observable<String> call(final Integer integer,"+
"                                               Observable<String> stringObservable) {"+
"                  return stringObservable.map(new Func1<String, String>() {"+
"                    @Override"+
"                    public String call(String s) {"+
"                      return \" \" + integer + s;"+
"                    }"+
"                  });"+
"                }"+
"              })"+
"              .subscribe(new Action1<Observable<String>>() {"+
"                @Override"+
"                public void call(Observable<String> o) {"+
"                  o.subscribe(new Action1<String>() {"+
"                    @Override"+
"                    public void call(String s) {"+
"                      log(s);"+
"                    }"+
"                  });"+
"                }"+
"  }"+
"//convert an Observable that emits Observables into a single Observable that emits the items"+
"//emitted by the most-recently emitted of those Observables");
mCodes.put(Constants.BlockingObservable.firstOrDefault,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onCompleted();"+
"      }"+
"    log(i);"+
"  }");
mCodes.put(Constants.Filter.firstOrDefault,
"    Observable.range(1, 10).firstOrDefault(3).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Async.toAsync,
"    Async.<Integer>toAsync(new Action0() {"+
"      @Override"+
"      public void call() {"+
"        log(\"Action0.call\");"+
"      }"+
"    }).call().subscribe(new Action1<Void>() {"+
"      @Override"+
"      public void call(Void aVoid) {"+
"        log(\"Action1.call\");"+
"      }"+
"  }");
mCodes.put(Constants.Utility.cache,
"    Observable o = Observable.range(1, 10).cache();"+
"    o.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"s1:\" + integer);"+
"      }"+
"    });"+
""+
"    o.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"s2:\" + integer);"+
"      }"+
"  }");
mCodes.put(Constants.Utility.delay,
"    Observable.just(1, 2).delay(2, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.MathAggregate.maxBy,
"    logNotImplemented();"+
"  }");
mCodes.put(Constants.ObservableCreate.from_iterable,
"    Observable.from(Arrays.asList(new String[] {\"s1\", \"s2\", \"s3\"}))"+
"              .subscribe(new Action1<String>() {"+
"                @Override"+
"                public void call(String s) {"+
"                  log(s);"+
"                }"+
"  }"+
"//[repeat]create an Observable that emits a particular item or sequence of items repeatedly");
mCodes.put(Constants.Subject.behavior,
"    BehaviorSubject<Integer> s = BehaviorSubject.create();"+
"    s.onNext(1);"+
"    s.onNext(2);"+
"    s.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"\" + integer);"+
"      }"+
"    s.onNext(3);"+
"  }");
mCodes.put(Constants.Transformation.map,
"    Observable.range(1, 10).map(new Func1<Integer, Integer>() {"+
"      @Override"+
"      public Integer call(Integer integer) {"+
"        return integer * 2;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Utility.single,
"    Observable.just(1).single().subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.MathAggregate.sumDouble,
"    MathObservable.sumDouble(Observable.just(1.0, 2.0, 3.0, 4.0))"+
"                  .subscribe(new Action1<Double>() {"+
"                    @Override"+
"                    public void call(Double f) {"+
"                      log(f);"+
"                    }"+
"  }");
mCodes.put(Constants.BlockingObservable.forEach,
"    Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(1);"+
"        subscriber.onNext(2);"+
"        subscriber.onCompleted();"+
"      }"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().forEach(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Condition.takeWhileWithIndex,
"    logNotImplemented();"+
"  }");
mCodes.put(Constants.BlockingObservable.next,
"    Iterator<Integer> itr = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(1);"+
"        subscriber.onNext(2);"+
"        subscriber.onCompleted();"+
"      }"+
"    while (itr.hasNext()) {"+
"      log(itr.next());"+
"    }"+
"  }");
mCodes.put(Constants.MathAggregate.count,
"    logUseObservable();"+
"  }");
mCodes.put(Constants.MathAggregate.countLong,
"    logUseObservable();"+
"  }");
mCodes.put(Constants.ObservableCreate.interval,
"    final Subscription subscription ="+
"      Observable.interval(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {"+
"        @Override"+
"        public void call(Long aLong) {"+
"          log(aLong);"+
"        }"+
"    AsyncExecutor.SINGLETON.schedule(new Runnable() {"+
"      @Override"+
"        if (!subscription.isUnsubscribed()) {"+
"          subscription.unsubscribe();"+
"        }"+
"      }"+
"    }, 10, TimeUnit.SECONDS);"+
"  }"+
"}"+
""+
""+
"te an Observable that emits a single item after a given delay");
mCodes.put(Constants.Transformation.flatMapIterable,
"    Observable.just(1, 2).flatMapIterable(new Func1<Integer, Iterable<String>>() {"+
"      @Override"+
"      public Iterable<String> call(Integer integer) {"+
"        return Arrays.asList(new String[] {\"\" + integer, \"\" + integer * 3});"+
"      }"+
"    }).subscribe(new Action1<String>() {"+
"      @Override"+
"      public void call(String s) {"+
"        log(s);"+
"      }"+
"  }");
mCodes.put(Constants.Strings.split,
"    StringObservable.split(Observable.just(\"ab#cd#ef\"), \"#\").subscribe(new Action1<String>() {"+
"      @Override"+
"      public void call(String s) {"+
"        log(s);"+
"      }"+
"  }");
mCodes.put(Constants.Utility.using,
"    Observable.using(new Func0<File>() {"+
"      @Override"+
"      public File call() {"+
"        File f = null;"+
"        try {"+
"          f = new File(\"ABC\");"+
"          if (!f.exists()) {"+
"            f.createNewFile();"+
"          }"+
""+
"        } catch (Exception e) {"+
"          Observable.error(e);"+
"        }"+
"        return f;"+
"      }"+
"    }, new Func1<File, Observable<String>>() {"+
"      @Override"+
"      public Observable<String> call(File file) {"+
"        return Observable.just(file.exists() ? \"exist\" : \"not exist\");"+
"      }"+
"    }, new Action1<File>() {"+
"      @Override"+
"      public void call(File file) {"+
"        if (file != null && file.exists()) {"+
"          file.delete();"+
"        }"+
"      }"+
"    }).subscribe(new Action1<String>() {"+
"      @Override"+
"      public void call(String exist) {"+
"        log(exist);"+
"      }"+
"    }, new Action1<Throwable>() {"+
"      @Override"+
"      public void call(Throwable throwable) {"+
"        log(throwable);"+
"      }"+
"  }");
mCodes.put(Constants.Condition.takeWhile,
"    Observable.range(1, 10).takeWhile(new Func1<Integer, Boolean>() {"+
"      @Override"+
"      public Boolean call(Integer i) {"+
"        return i < 3;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.ofType,
"    Observable.<Object>just(1, \"2\", new Exception(\"abc\"))."+
"                                                           ofType(Integer.class)"+
"                                                         .subscribe(new Action1<Integer>() {"+
"                                                           @Override"+
"                                                           public void call(Integer integer) {"+
"                                                             log(integer);"+
"                                                           }"+
"  }"+
"}"+
""+
"");
mCodes.put(Constants.Utility.doOnError,
"    Observable.just(1, \"3\").cast(Integer.class).doOnError(new Action1<Throwable>() {"+
"      @Override"+
"      public void call(Throwable throwable) {"+
"        log(\"doOnError:\" + throwable.getMessage());"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"    }, new Action1<Throwable>() {"+
"      @Override"+
"      public void call(Throwable throwable) {"+
"        log(throwable);"+
"      }"+
"  }");
mCodes.put(Constants.Condition.defaultIfEmpty,
"    Observable.<Integer>empty().defaultIfEmpty(3).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.ErrorHandler.retry,
"    Observable.just(1, \"abc\", 2).cast(Integer.class).retry(2).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"    }, new Action1<Throwable>() {"+
"      @Override"+
"      public void call(Throwable throwable) {"+
"        log(throwable);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.takeLastBuffer,
"    Observable.range(1, 10)"+
"              .subscribeOn(Schedulers.newThread())"+
"              .takeLastBuffer(3)"+
"              .subscribe(new Action1<List<Integer>>() {"+
"                @Override"+
"                public void call(List<Integer> integers) {"+
"                  String s = \"\";"+
"                  for (Integer i : integers) {"+
"                    s += i;"+
"                  }"+
"                  log(s);"+
"                }"+
"              });"+
""+
"    Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        for (int i = 0; i < 10; i++) {"+
"          subscriber.onNext(i);"+
"          sleep(100);"+
"        }"+
"      }"+
"    })"+
"              .subscribeOn(Schedulers.newThread())"+
"              .takeLastBuffer(3, 100, TimeUnit.MILLISECONDS)"+
"              .subscribe(new Action1<List<Integer>>() {"+
"                @Override"+
"                public void call(List<Integer> integers) {"+
"                  String s = \"\";"+
"                  for (Integer i : integers) {"+
"                    s += i;"+
"                  }"+
"                  log(s);"+
"                }"+
"  }");
mCodes.put(Constants.Combine.join,
"    Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        for (int i = 0; i < 10; ++i) {"+
"          subscriber.onNext(i);"+
"          sleep(1000);"+
"        }"+
"      }"+
"    })"+
"              .subscribeOn(Schedulers.newThread())"+
"              .join(Observable.create(new Observable.OnSubscribe<String>() {"+
"                @Override"+
"                public void call(Subscriber<? super String> subscriber) {"+
"                  final String[] arr = new String[] {\"a\", \"b\", \"c\"};"+
"                  for (int i = 0; i < arr.length; ++i) {"+
"                    subscriber.onNext(arr[i]);"+
"                    sleep(400);"+
"                  }"+
"                }"+
"              }).subscribeOn(Schedulers.newThread()), new Func1<Integer, Observable<Long>>() {"+
"                @Override"+
"                public Observable<Long> call(Integer integer) {"+
"                  return Observable.timer(1, TimeUnit.SECONDS);"+
"                }"+
"              }, new Func1<String, Observable<Long>>() {"+
"                @Override"+
"                public Observable<Long> call(String s) {"+
"                  return Observable.timer(2, TimeUnit.SECONDS);"+
"                }"+
"              }, new Func2<Integer, String, String>() {"+
"                @Override"+
"                public String call(Integer integer, String s) {"+
"                  return \" \" + integer + \" \" + s;"+
"                }"+
"              .subscribe(new Action1<String>() {"+
"                @Override"+
"                public void call(String s) {"+
"                  log(s);"+
"                }"+
"  }"+
"}"+
""+
""+
""+
"");
mCodes.put(Constants.MathAggregate.averageInteger,
"    MathObservable.averageInteger(Observable.just(2, 4, 1, 5))"+
"                  .subscribe(new Action1<Integer>() {"+
"                    @Override"+
"                    public void call(Integer integer) {"+
"                      log(integer);"+
"                    }"+
"  }");
mCodes.put(Constants.Condition.doWhile,
"    logNotImplemented();"+
"  }");
mCodes.put(Constants.Filter.takeFirst,
"    Observable.range(1, 10).takeFirst(new Func1<Integer, Boolean>() {"+
"      @Override"+
"      public Boolean call(Integer integer) {"+
"        return integer % 2 == 0;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.throttleLast,
"    final Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        for (int i = 0; i < 5; i++) {"+
"          subscriber.onNext(i);"+
"          sleep(300);"+
"        }"+
"      }"+
"    }).throttleLast(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Condition.skipUtil,
"    Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        for (int i = 0; i < 10; ++i) {"+
"          sleep(200);"+
"          subscriber.onNext(i);"+
"        }"+
"        subscriber.onCompleted();"+
"      }"+
"    })"+
"              .subscribeOn(Schedulers.newThread())"+
"              .skipUntil(Observable.timer(1, TimeUnit.SECONDS))"+
"              .subscribe(new Action1<Integer>() {"+
"                @Override"+
"                public void call(Integer integer) {"+
"                  log(integer);"+
"                }"+
"  }");
mCodes.put(Constants.Utility.delaySubscription,
"    Observable.just(1, 2)"+
"              .delaySubscription(2, TimeUnit.SECONDS)"+
"              .subscribe(new Action1<Integer>() {"+
"                @Override"+
"                public void call(Integer integer) {"+
"                  log(integer);"+
"                }"+
"  }");
mCodes.put(Constants.Filter.skipLast,
"    Observable.range(1, 10).skipLast(3).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Subject.replay,
"    ReplaySubject<Integer> subject = ReplaySubject.create();"+
"    subject.onNext(1);"+
"    subject.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"Subscriber1:\" + integer);"+
"      }"+
"    });"+
"    subject.onNext(2);"+
"    subject.onNext(3);"+
"    subject.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"Subscriber2:\" + integer);"+
"      }"+
"    subject.onNext(4);"+
"  }");
mCodes.put(Constants.ObservableCreate.from_future,
"    Observable.from(AsyncExecutor.SINGLETON.submit(new Callable<String>() {"+
"      @Override"+
"      public String call() throws Exception {"+
"        return \"I 'm from future of thread \" + Thread.currentThread().getName();"+
"      }"+
"    })).subscribe(new Action1<String>() {"+
"      @Override"+
"      public void call(String s) {"+
"        log(s);"+
"      }"+
"  }"+
"//[from] convert an Iterable, a Future, or an Array into an Observable");
mCodes.put(Constants.ConnectableObservable.publish,
"    log(\"showed in connect!\");"+
"  }");
mCodes.put(Constants.ObservableCreate.just,
"    Observable.just(1, 2, 3).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }"+
"//[from] convert an Iterable, a Future, or an Array into an Observable");
mCodes.put(Constants.ObservableCreate.error,
"    Observable.error(new Exception(\"abc\")).subscribe(new Action1<Object>() {"+
"      @Override"+
"      public void call(Object o) {"+
"        log(\"onNext\");"+
"      }"+
"    }, new Action1<Throwable>() {"+
"      @Override"+
"      public void call(Throwable throwable) {"+
"        log(\"onError:\" + throwable.getMessage());"+
"      }"+
"    }, new Action0() {"+
"      @Override"+
"      public void call() {"+
"        log(\"onComplete\");"+
"      }"+
"  }"+
"}"+
""+
""+
"");
mCodes.put(Constants.Transformation.concatMap,
"    Observable.just(1, 2).concatMap(new Func1<Integer, Observable<Integer>>() {"+
"      @Override"+
"      public Observable<Integer> call(Integer integer) {"+
"        return Observable.just(integer);"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.ObservableCreate.repeat,
"    log(\"RxJava not implemented!\");"+
"  }"+
"}"+
""+
""+
"create an Observable that emits a particular item or sequence of items repeatedly, depending on the emissions of a second Observable");
mCodes.put(Constants.MathAggregate.min,
"    MathObservable.min(Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.distinctUntilChanged,
"    Observable.just(1, 1, 2, 2, 3, 4, 4, 1, 1, 5)"+
"              .distinctUntilChanged()"+
"              .subscribe(new Action1<Integer>() {"+
"                @Override"+
"                public void call(Integer integer) {"+
"                  log(integer);"+
"                }"+
"  }"+
"}"+
""+
"");
mCodes.put(Constants.Filter.elementAtOrDefault,
"    Observable.range(1, 10).elementAtOrDefault(100, 1000).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Combine.zip,
"    Observable.just(1, 2, 3)"+
"              .zipWith(Observable.just(\"a\", \"b\", \"c\"), new Func2<Integer, String, String>() {"+
"                @Override"+
"                public String call(Integer integer, String s) {"+
"                  return s + integer * 2;"+
"                }"+
"              })"+
"              .subscribe(new Action1<String>() {"+
"                @Override"+
"                public void call(String s) {"+
"                  log(s);"+
"                }"+
"  }"+
"//combine sets of items emitted by two or more Observables by means of Pattern and Plan intermediaries");
mCodes.put(Constants.BlockingObservable.single,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(1);"+
"        subscriber.onCompleted();"+
"      }"+
"    log(i);"+
"  }");
mCodes.put(Constants.Condition.skipWhile,
"    Observable.range(1, 10).skipWhile(new Func1<Integer, Boolean>() {"+
"      @Override"+
"      public Boolean call(Integer i) {"+
"        return i < 3;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.MathAggregate.averageFloat,
"    MathObservable.averageFloat(Observable.just(1.0f, 2.0f, 3.0f, 4.0f))"+
"                  .subscribe(new Action1<Float>() {"+
"                    @Override"+
"                    public void call(Float f) {"+
"                      log(f);"+
"                    }"+
"  }");
mCodes.put(Constants.Transformation.buffer,
"    Observable.range(1, 10).buffer(3).subscribe(new Action1<List<Integer>>() {"+
"      @Override"+
"      public void call(List<Integer> integers) {"+
"        String s = \"\";"+
"        for (Integer i : integers) {"+
"          s += i;"+
"        }"+
"        log(s);"+
"      }"+
"  }");
mCodes.put(Constants.Scheduler.compute,
"    Observable.just(\"a\", \"b\")"+
"              .observeOn(Schedulers.computation())"+
"              .subscribe(new Action1<String>() {"+
"                @Override"+
"                public void call(String s) {"+
"                  log(s + \" on \" + Thread.currentThread().getName());"+
"                }"+
"  }");
mCodes.put(Constants.Utility.timeInterval,
"    Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(1);"+
"        sleep(1000);"+
"        subscriber.onNext(2);"+
"        subscriber.onCompleted();"+
"      }"+
"    })"+
"              .subscribeOn(Schedulers.newThread())"+
"              .timeInterval()"+
"              .subscribe(new Action1<TimeInterval<Integer>>() {"+
"                @Override"+
"                public void call(TimeInterval<Integer> integerTimeInterval) {"+
"                  log(\"\" + integerTimeInterval.getValue() + \" \" +"+
"                      integerTimeInterval.getIntervalInMilliseconds());"+
"                }"+
"  }"+
"");
mCodes.put(Constants.MathAggregate.sumFloat,
"    MathObservable.sumFloat(Observable.just(1.0f, 2.0f, 3.0f, 4.0f))"+
"                  .subscribe(new Action1<Float>() {"+
"                    @Override"+
"                    public void call(Float f) {"+
"                      log(f);"+
"                    }"+
"  }");
mCodes.put(Constants.ObservableCreate.create,
"    Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(1);"+
"        subscriber.onNext(2);"+
"        subscriber.onCompleted();"+
"      }"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }"+
"}"+
""+
""+
"ot create the Observable until a Subscriber subscribes; create a fresh Observable"+
"scription");
mCodes.put(Constants.Filter.timeout,
"    Observable.<Integer>never().timeout(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"    }, new Action1<Throwable>() {"+
"      @Override"+
"      public void call(Throwable throwable) {"+
"        log(throwable);"+
"      }"+
"  }");
mCodes.put(Constants.Condition.switchcase,
"    logNotImplemented();"+
"  }");
mCodes.put(Constants.Condition.ifThen,
"    logNotImplemented();"+
"  }");
mCodes.put(Constants.Utility.doOnCompleted,
"    Observable.range(1, 3).doOnCompleted(new Action0() {"+
"      @Override"+
"      public void call() {"+
"        log(\"onCompleted\");"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Utility.doOnEach,
"    Observable.range(1, 10).doOnEach(new Action1<Notification<? super Integer>>() {"+
"      @Override"+
"      public void call(Notification<? super Integer> notification) {"+
"        log(\"doOnEach:\" + notification.getKind() + \" \" + notification.getValue());"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Strings.encode,
"    StringObservable.encode(Observable.just(\"abc\"), \"UTF-8\").subscribe(new Action1<byte[]>() {"+
"      @Override"+
"      public void call(byte[] bytes) {"+
"        log(bytes.length);"+
"      }"+
"  }");
mCodes.put(Constants.BlockingObservable.mostRecent,
"    Iterator<Integer> itr = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(1);"+
"        subscriber.onNext(2);"+
"        subscriber.onCompleted();"+
"      }"+
"    while (itr.hasNext()) {"+
"      log(itr.next());"+
"    }"+
"  }");
mCodes.put(Constants.MathAggregate.collect,
"    logUseObservable();"+
"  }");
mCodes.put(Constants.Subject.async,
"    AsyncSubject<Integer> s = AsyncSubject.create();"+
"    s.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"\" + integer);"+
"      }"+
"    s.onNext(0);"+
"    s.onNext(1);"+
"    s.onNext(2);"+
"    s.onCompleted();"+
"  }"+
"/*BehaviorSubject only remembers the last value. It is similar to a ReplaySubject with a"+
"buffer of size 1. An initial value can be provided on creation, therefore guaranteeing that"+
" a value always will be available immediately on subscription."+
"*/");
mCodes.put(Constants.ErrorHandler.onErrorReturn,
"    Observable.just(1, \"abc\")"+
"              .cast(Integer.class)"+
"              .onErrorReturn(new Func1<Throwable, Integer>() {"+
"                @Override"+
"                public Integer call(Throwable throwable) {"+
"                  return -1;"+
"                }"+
"              })"+
"              .subscribe(new Action1<Integer>() {"+
"                @Override"+
"                public void call(Integer integer) {"+
"                  log(integer);"+
"                }"+
"              }, new Action1<Throwable>() {"+
"                @Override"+
"                public void call(Throwable throwable) {"+
"                  log(throwable);"+
"                }"+
"  }");
mCodes.put(Constants.BlockingObservable.last,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(1);"+
"        subscriber.onNext(2);"+
"        subscriber.onCompleted();"+
"      }"+
"    log(i);"+
"  }");
mCodes.put(Constants.ObservableCreate.empty,
"    Observable.<String>empty().subscribe(new Observer<String>() {"+
"      @Override"+
"      public void onNext(String s) {"+
"        log(\"onNext:\" + s);"+
"      }"+
""+
"      @Override"+
"      public void onCompleted() {"+
"        log(\"onCompleted\");"+
"      }"+
""+
"      @Override"+
"      public void onError(Throwable e) {"+
"        log(\"onError:\" + e.getMessage());"+
"      }"+
"  }"+
"}"+
""+
""+
"te an Observable that emits nothing and then signals an error");
mCodes.put(Constants.MathAggregate.toList,
"    logUseObservable();"+
"  }");
mCodes.put(Constants.Combine.mergeDelayError,
"    logNotImplemented();"+
"  }"+
"//combine sets of items emitted by two or more Observables together via a specified function and emit items based on the results of this function");
mCodes.put(Constants.ObservableCreate.timer,
"    final Subscription subscription ="+
"      Observable.timer(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {"+
"        @Override"+
"        public void call(Long aLong) {"+
"          log(aLong);"+
"        }"+
"  }"+
"}"+
""+
""+
"te an Observable that emits nothing and then completes");
mCodes.put(Constants.ErrorHandler.onExceptionResumeNext,
"    Observable.just(1, \"abc\", \"2\")"+
"              .cast(Integer.class)"+
"              .onExceptionResumeNext(Observable.just(5, 6))"+
"              .subscribe(new Action1<Integer>() {"+
"                @Override"+
"                public void call(Integer integer) {"+
"                  log(integer);"+
"                }"+
"              }, new Action1<Throwable>() {"+
"                @Override"+
"                public void call(Throwable throwable) {"+
"                  log(throwable);"+
"                }"+
"  }");
mCodes.put(Constants.Utility.finallyDo,
"    Observable.just(1, 2).finallyDo(new Action0() {"+
"      @Override"+
"      public void call() {"+
"        log(\"finallyDo\");"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Utility.materialize,
"    Observable o1 = Observable.range(1, 3).materialize();"+
"    o1.subscribe(new Action1<Notification<Integer>>() {"+
"      @Override"+
"      public void call(Notification<Integer> integerNotification) {"+
"        log(\"******\");"+
"        log(\"kind:\" + integerNotification.getKind());"+
"        log(\"value:\" + integerNotification.getValue());"+
"      }"+
"    });"+
"    o1.dematerialize().subscribe(new Action1() {"+
"      @Override"+
"      public void call(Object o) {"+
"        log(o.toString());"+
"      }"+
"  }");
mCodes.put(Constants.Utility.doOnTerminate,
"    Observable.just(1, 2).doOnTerminate(new Action0() {"+
"      @Override"+
"      public void call() {"+
"        log(\"OnTerminate\");"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Subject.behavior_with_init_value,
"    BehaviorSubject<Integer> s = BehaviorSubject.create(0);"+
"    s.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"\" + integer);"+
"      }"+
"    s.onNext(1);"+
"  }"+
"/*"+
"PublishSubject is the most straight-forward kind of subject."+
" When a value is pushed into a PublishSubject, the subject pushes"+
" it to every subscriber that is subscribed to it at that moment."+
" */");
mCodes.put(Constants.Scheduler.io,
"    Observable.just(\"a\", \"b\").observeOn(Schedulers.io()).subscribe(new Action1<String>() {"+
"      @Override"+
"      public void call(String s) {"+
"        log(s + \" on \" + Thread.currentThread().getName());"+
"      }"+
"  }");
mCodes.put(Constants.Filter.filter,
"    Observable.range(1, 10).filter(new Func1<Integer, Boolean>() {"+
"      @Override"+
"      public Boolean call(Integer integer) {"+
"        return integer % 2 == 0;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.debounce,
"    Observable.just(1).debounce(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Condition.amb,
"    Observable.amb(Observable.create(new Observable.OnSubscribe<Integer>() {"+
"                     @Override"+
"                     public void call(Subscriber<? super Integer> subscriber) {"+
"                       sleep(1000);"+
"                       subscriber.onNext(1);"+
"                       subscriber.onNext(11);"+
"                       subscriber.onCompleted();"+
"                     }"+
"                   }).subscribeOn(Schedulers.newThread()),"+
"                   Observable.create(new Observable.OnSubscribe<Integer>() {"+
"                     @Override"+
"                     public void call(Subscriber<? super Integer> subscriber) {"+
"                       sleep(500);"+
"                       subscriber.onNext(2);"+
"                       subscriber.onNext(22);"+
"                       subscriber.onCompleted();"+
"                     }"+
"                   }).subscribeOn(Schedulers.newThread()),"+
"                   Observable.create(new Observable.OnSubscribe<Integer>() {"+
"                     @Override"+
"                     public void call(Subscriber<? super Integer> subscriber) {"+
"                       sleep(300);"+
"                       subscriber.onNext(3);"+
"                       subscriber.onNext(33);"+
"                       subscriber.onCompleted();"+
"                     }"+
"                   }).subscribeOn(Schedulers.newThread())).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.elementAt,
"    Observable.range(1, 10).elementAt(3).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Combine.switchIfEmpty,
"    Observable.<Integer>empty().switchIfEmpty(Observable.just(4, 5))"+
"                               .subscribe(new Action1<Integer>() {"+
"                                 @Override"+
"                                 public void call(Integer integer) {"+
"                                   log(integer);"+
"                                 }"+
"  }"+
"}"+
""+
""+
"");
mCodes.put(Constants.Async.startFuture,
"    Async.startFuture(new Func0<Future<Integer>>() {"+
"      @Override"+
"      public Future<Integer> call() {"+
"        return AsyncExecutor.SINGLETON.submit(new Callable<Integer>() {"+
"          @Override"+
"          public Integer call() throws Exception {"+
"            return 3;"+
"          }"+
"        });"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.ObservableCreate.repeatWhen,
"    log(\"RxJava not implemented!\");"+
"  }"+
"}"+
""+
""+
"ate an Observable from scratch by means of a function");
mCodes.put(Constants.Filter.skip,
"    Observable.range(1, 10).skip(2).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Strings.decode,
"    StringObservable.decode(Observable.just(\"ABC\".getBytes(Charset.forName(\"UTF-8\"))),"+
"                            Charset.forName(\"UTF-8\")).subscribe(new Action1<String>() {"+
"      @Override"+
"      public void call(String s) {"+
"        log(s);"+
"      }"+
"  }");
mCodes.put(Constants.ConnectableObservable.replay,
"    Observable<Integer> o = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        for (int i = 0; i < 5; ++i) {"+
"          subscriber.onNext(i);"+
"          sleep(500);"+
"        }"+
"        subscriber.onCompleted();"+
"      }"+
"    }).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread());"+
"    o.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"s1:\" + integer);"+
"      }"+
"    });"+
"    sleep(1000);"+
"    ConnectableObservable<Integer> co = o.publish();"+
"    co.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"s2:\" + integer);"+
"      }"+
""+
"    log(\"begin connect\");"+
"    co.connect();"+
"  }");
mCodes.put(Constants.Combine.combineLatest,
"    Observable.combineLatest(Observable.create(new Observable.OnSubscribe<Integer>() {"+
"                               @Override"+
"                               public void call(Subscriber<? super Integer> subscriber) {"+
"                                 for (int i = 0; i < 10; ++i) {"+
"                                   subscriber.onNext(i);"+
"                                   sleep(1000);"+
"                                 }"+
"                               }"+
"                             }).subscribeOn(Schedulers.newThread()),"+
"                             Observable.create(new Observable.OnSubscribe<String>() {"+
"                               @Override"+
"                               public void call(Subscriber<? super String> subscriber) {"+
"                                 final String[] arr = new String[] {\"a\", \"b\", \"c\"};"+
"                                 for (int i = 0; i < arr.length; ++i) {"+
"                                   subscriber.onNext(arr[i]);"+
"                                   sleep(400);"+
"                                 }"+
"                               }"+
"                             }).subscribeOn(Schedulers.newThread()),"+
"                             new Func2<Integer, String, String>() {"+
"                               @Override"+
"                               public String call(Integer integer, String s) {"+
"                                 return \"\" + integer + \" \" + s;"+
"                               }"+
"                             }).subscribe(new Action1<String>() {"+
"      @Override"+
"      public void call(String s) {"+
"        log(s);"+
"      }"+
"  }"+
""+
"//join( ) and groupJoin( ) — combine the items emitted by two Observables whenever one item from one Observable falls within a window of duration specified by an item emitted by the other Observable");
mCodes.put(Constants.Transformation.switchMap,
"    Observable.just(1, 2).switchMap(new Func1<Integer, Observable<Integer>>() {"+
"      @Override"+
"      public Observable<Integer> call(Integer integer) {"+
"        return Observable.just(integer);"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer s) {"+
"        log(s);"+
"      }"+
"  }");
mCodes.put(Constants.BlockingObservable.latest,
"    Iterator<Integer> itr = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(1);"+
"        subscriber.onNext(2);"+
"        subscriber.onCompleted();"+
"      }"+
"    while (itr.hasNext()) {"+
"      log(itr.next());"+
"    }"+
"  }");
mCodes.put(Constants.Transformation.window,
"    Observable.range(1, 10).window(3).subscribe(new Action1<Observable<Integer>>() {"+
"      @Override"+
"      public void call(final Observable<Integer> integerObservable) {"+
"        integerObservable.subscribe(new Action1<Integer>() {"+
"          @Override"+
"          public void call(Integer integer) {"+
"            log(integer + \" of window \" + integerObservable);"+
"          }"+
"        });"+
"      }"+
"  }");
mCodes.put(Constants.Combine.merge,
"    Observable.just(1, 2, 3)"+
"              .mergeWith(Observable.just(4, 5, 6))"+
"              .subscribe(new Action1<Integer>() {"+
"                @Override"+
"                public void call(Integer integer) {"+
"                  log(integer);"+
"                }"+
"  }"+
"// combine multiple Observables into one, allowing error-free Observables to continue before propagating errors");
mCodes.put(Constants.MathAggregate.reduce,
"    logUseObservable();"+
"  }");
mCodes.put(Constants.Filter.takeLast,
"    Observable.range(1, 10).takeLast(3).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Subject.publish,
"    PublishSubject<Integer> subject = PublishSubject.create();"+
"    subject.onNext(1);"+
"    subject.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"\" + integer);"+
"      }"+
"    subject.onNext(2);"+
"    subject.onNext(3);"+
"    subject.onNext(4);"+
"  }"+
"/*ReplaySubject has the special feature of caching all the values pushed to it. When a new"+
"  subscription is made, the event sequence is replayed from the start for the new subscriber."+
"  After catching up, every subscriber receives new events as they come."+
"  */");
mCodes.put(Constants.Strings.from,
"    StringObservable.from(new ByteArrayInputStream(\"ABC\".getBytes()))"+
"                    .subscribe(new Action1<byte[]>() {"+
"                      @Override"+
"                      public void call(byte[] bytes) {"+
"                        log(bytes.length);"+
"                      }"+
"  }");
mCodes.put(Constants.MathAggregate.concat,
"    logUseObservable();"+
"  }");
mCodes.put(Constants.Strings.byLine,
"    StringObservable.byLine(Observable.just(\"ab\r\ncd\r\nef\", \"12\r\n34\"))"+
"                    .subscribe(new Action1<String>() {"+
"                      @Override"+
"                      public void call(String s) {"+
"                        log(s);"+
"                      }"+
"  }");
mCodes.put(Constants.MathAggregate.max,
"    MathObservable.max(Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.MathAggregate.toMap,
"    logUseObservable();"+
"  }");
mCodes.put(Constants.Async.fromCallable,
"    Async.fromCallable(new Callable<Integer>() {"+
"      @Override"+
"      public Integer call() throws Exception {"+
"        return 3;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.MathAggregate.minBy,
"    logNotImplemented();"+
"  }");
mCodes.put(Constants.Async.forEachFuture,
"    Future<Void> f = Async.forEachFuture(Observable.just(1, 2, 3), new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"    }, new Action1<Throwable>() {"+
"      @Override"+
"      public void call(Throwable throwable) {"+
"        log(throwable);"+
"      }"+
"    log(\"task done:\" + f.isDone());"+
"  }");
mCodes.put(Constants.Async.fromAction,
"    Async.fromAction(new Action0() {"+
"      @Override"+
"      public void call() {"+
"        log(\"Action0.call\");"+
"      }"+
"    }, 3).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.MathAggregate.toSortedList,
"    logUseObservable();"+
"  }");
mCodes.put(Constants.Combine.startWith,
"    logNotImplemented();"+
"  }"+
"//combine multiple Observables into one");
mCodes.put(Constants.Scheduler.immediate,
"    Observable.just(\"a\", \"b\")"+
"              .observeOn(Schedulers.immediate())"+
"              .subscribe(new Action1<String>() {"+
"                @Override"+
"                public void call(String s) {"+
"                  log(s + \" on \" + Thread.currentThread().getName());"+
"                }"+
"  }");
mCodes.put(Constants.BlockingObservable.toIterable,
"    Iterator<Integer> itr = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(1);"+
"        subscriber.onNext(2);"+
"        subscriber.onCompleted();"+
"      }"+
"    while (itr.hasNext()) {"+
"      log(itr.next());"+
"    }"+
"  }");
mCodes.put(Constants.ObservableCreate.range,
"    Observable.range(1, 10).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }"+
"}"+
""+
""+
"create an Observable that emits a sequence of integers spaced by a given time interval");
mCodes.put(Constants.Filter.throttleFirst,
"    final Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        for (int i = 0; i < 5; i++) {"+
"          subscriber.onNext(i);"+
"          sleep(300);"+
"        }"+
"      }"+
"    }).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"    AsyncExecutor.SINGLETON.schedule(new Runnable() {"+
"      @Override"+
"        if (!subscription.isUnsubscribed()) {"+
"          subscription.unsubscribe();"+
"        }"+
"      }"+
"    }, 3, TimeUnit.SECONDS);"+
"  }");
mCodes.put(Constants.Filter.take,
"    Observable.range(1, 10).take(3).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.MathAggregate.averageLong,
"    MathObservable.averageLong(Observable.just(2L, 4L, 1L, 5L)).subscribe(new Action1<Long>() {"+
"      @Override"+
"      public void call(Long l) {"+
"        log(l);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.throttleWithTimeout,
"    Observable.just(1)"+
"              .throttleWithTimeout(2, TimeUnit.SECONDS)"+
"              .subscribe(new Action1<Integer>() {"+
"                @Override"+
"                public void call(Integer integer) {"+
"                  log(integer);"+
"                }"+
"  }");
mCodes.put(Constants.Combine.and_then_when,
"    Plan0<String> p = //"+
"      JoinObservable.from(Observable.just(1, 2, 3))//"+
"                    .and(Observable.just(\"a\", \"b\", \"c\"))//"+
"                    .and(Observable.just(\"d\", \"e\"))//"+
"                    .then(new Func3<Integer, String, String, String>() {"+
"                      @Override"+
"                      public String call(Integer integer, String s, String s2) {"+
"                        String ret = \"\" + integer + \" \" + s + \" \" + s2;"+
"                        return ret;"+
"                      }"+
"                    });"+
"    JoinObservable.when(p).toObservable().subscribe(new Action1<String>() {"+
"      @Override"+
"      public void call(String s) {"+
"        log(s);"+
"      }"+
"    });"+
"  }"+
"});"+
""+
"//    registery.add(\"then\", new Runnable() {"+
"//      @Override"+
"//"+
"//      }"+
"//    registery.add(\"when\", new Runnable() {"+
"//      @Override"+
"//"+
"//      }"+
""+
"//when an item is emitted by either of two Observables, combine the latest item emitted by each Observable via a specified function and emit items based on the results of this function");
mCodes.put(Constants.BlockingObservable.first,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onNext(1);"+
"        subscriber.onNext(2);"+
"        subscriber.onCompleted();"+
"      }"+
"    log(i);"+
"  }");
mCodes.put(Constants.Condition.takeUntil,
"    Observable.range(1, 10).takeUntil(new Func1<Integer, Boolean>() {"+
"      @Override"+
"      public Boolean call(Integer i) {"+
"        return i > 3;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.last,
"    //normal last"+
"    Observable.range(1, 10).last().subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"    });"+
"    // last with predicate"+
"    Observable.range(1, 10).last(new Func1<Integer, Boolean>() {"+
"      @Override"+
"      public Boolean call(Integer integer) {"+
"        return integer % 2 == 0;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.BlockingObservable.lastOrDefault,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        subscriber.onCompleted();"+
"      }"+
"    log(i);"+
"  }");
mCodes.put(Constants.MathAggregate.sumInteger,
"    MathObservable.sumInteger(Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.MathAggregate.sumLong,
"    MathObservable.sumLong(Observable.just(1L, 2L, 3L)).subscribe(new Action1<Long>() {"+
"      @Override"+
"      public void call(Long l) {"+
"        log(l);"+
"      }"+
"  }");
mCodes.put(Constants.Transformation.groupBy,
"    Observable.range(1, 10).groupBy(new Func1<Integer, String>() {"+
"      @Override"+
"      public String call(Integer integer) {"+
"        return (integer % 2 == 0) ? \"even\" : \"odd\";"+
"      }"+
"    }).subscribe(new Action1<GroupedObservable<String, Integer>>() {"+
"      @Override"+
"      public void call(final GroupedObservable<String, Integer> stringIntegerGroupedObservable) {"+
"        log(\"group ok:\" + stringIntegerGroupedObservable.getKey());"+
"        stringIntegerGroupedObservable.subscribe(new Action1<Integer>() {"+
"          @Override"+
"          public void call(Integer integer) {"+
"            log(integer + \" of group \" + stringIntegerGroupedObservable.getKey());"+
"          }"+
"        });"+
"      }"+
"  }");
mCodes.put(Constants.Strings.join,
"    StringObservable.join(Observable.just(\"abc\", \"def\"), \"#\").subscribe(new Action1<String>() {"+
"      @Override"+
"      public void call(String s) {"+
"        log(s);"+
"      }"+
"  }");
mCodes.put(Constants.Utility.serialize,
"    Observable.range(1, 3).serialize().subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Transformation.flatMap,
"    Observable.just(1, 2).flatMap(new Func1<Integer, Observable<Integer>>() {"+
"      @Override"+
"      public Observable<Integer> call(final Integer integer) {"+
"        return Observable.create(new Observable.OnSubscribe<Integer>() {"+
"          @Override"+
"          public void call(Subscriber<? super Integer> subscriber) {"+
"            subscriber.onNext(integer);"+
"            subscriber.onCompleted();"+
"          }"+
"        }).subscribeOn(Schedulers.newThread());"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.sample,
"    final Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        for (int i = 0; i < 10; i++) {"+
"          subscriber.onNext(i);"+
"          sleep(100);"+
"        }"+
"      }"+
"    })"+
"                                                .subscribeOn(Schedulers.newThread())"+
"                                                .sample(1, TimeUnit.SECONDS)"+
"                                                .subscribe(new Action1<Integer>() {"+
"                                                  @Override"+
"                                                  public void call(Integer integer) {"+
"                                                    log(integer);"+
""+
"                                                  }"+
"    AsyncExecutor.SINGLETON.schedule(new Runnable() {"+
"      @Override"+
"        if (!subscription.isUnsubscribed()) {"+
"          subscription.unsubscribe();"+
"        }"+
"      }"+
"    }, 3, TimeUnit.SECONDS);"+
"  }");
mCodes.put(Constants.Utility.doOnUnsubscribe,
"    Subscription subscription = Observable.just(1, 2).doOnSubscribe(new Action0() {"+
"      @Override"+
"      public void call() {"+
"        log(\"OnSubscribe\");"+
"      }"+
"    }).doOnUnsubscribe(new Action0() {"+
"      @Override"+
"      public void call() {"+
"        log(\"OnUnSubscribe\");"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"    subscription.unsubscribe();"+
"  }");
mCodes.put(Constants.ObservableCreate.defer,
"    Observable<Long> now = Observable.defer(new Func0<Observable<Long>>() {"+
"      @Override"+
"      public Observable<Long> call() {"+
"        return Observable.just(System.currentTimeMillis());"+
"      }"+
"    });"+
""+
"    now.subscribe(new Action1<Long>() {"+
"      @Override"+
"      public void call(Long aLong) {"+
"        log(aLong);"+
"      }"+
"    try {"+
"      Thread.sleep(1000);"+
"    } catch (Exception e) {"+
"      log(\"exception:\" + e.getMessage());"+
"    }"+
"    now.subscribe(new Action1<Long>() {"+
"      @Override"+
"      public void call(Long aLong) {"+
"        log(aLong);"+
"      }"+
"  }"+
"}"+
""+
""+
"te an Observable that emits a range of sequential integers");
mCodes.put(Constants.ConnectableObservable.connect,
"    Observable<Integer> o = Observable.create(new Observable.OnSubscribe<Integer>() {"+
"      @Override"+
"      public void call(Subscriber<? super Integer> subscriber) {"+
"        for (int i = 0; i < 5; ++i) {"+
"          subscriber.onNext(i);"+
"          sleep(500);"+
"        }"+
"        subscriber.onCompleted();"+
"      }"+
"    }).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread());"+
"    o.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"s1:\" + integer);"+
"      }"+
"    });"+
"    sleep(1000);"+
"    ConnectableObservable<Integer> co = o.publish();"+
"    co.subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"s2:\" + integer);"+
"      }"+
""+
"    log(\"begin connect\");"+
"    co.connect();"+
"  }");
mCodes.put(Constants.MathAggregate.averageDouble,
"    MathObservable.averageDouble(Observable.just(1.0, 2.0, 3.0, 4.0))"+
"                  .subscribe(new Action1<Double>() {"+
"                    @Override"+
"                    public void call(Double f) {"+
"                      log(f);"+
"                    }"+
"  }");
mCodes.put(Constants.Async.deferFuture,
"    Async.deferFuture(new Func0<Future<? extends Observable<Integer>>>() {"+
"      @Override"+
"      public Future<? extends Observable<Integer>> call() {"+
"        return AsyncExecutor.SINGLETON.submit(new Callable<Observable<Integer>>() {"+
"          @Override"+
"          public Observable<Integer> call() throws Exception {"+
"            return Observable.just(1, 2, 3);"+
"          }"+
"        });"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.distinct,
"    Observable.just(1, 1, 2, 2, 3, 4, 4, 1, 1, 5)"+
"              .distinct()"+
"              .subscribe(new Action1<Integer>() {"+
"                @Override"+
"                public void call(Integer integer) {"+
"                  log(integer);"+
"                }"+
"  }"+
"}"+
""+
"");
mCodes.put(Constants.Async.start,
"    Async.start(new Func0<Integer>() {"+
"      @Override"+
"      public Integer call() {"+
"        return 3;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.first,
"    Observable.range(1, 10).first().subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Transformation.scan,
"    Observable.range(1, 10).scan(new Func2<Integer, Integer, Integer>() {"+
"      @Override"+
"      public Integer call(Integer integer, Integer integer2) {"+
"        return integer + integer2;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Filter.lastOrDefault,
"    Observable.range(1, 10).lastOrDefault(10).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"    });"+
"    Observable.range(1, 10).lastOrDefault(10, new Func1<Integer, Boolean>() {"+
"      @Override"+
"      public Boolean call(Integer integer) {"+
"        return integer % 2 == 0;"+
"      }"+
"    }).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Async.fromRunnable,
"    Async.fromRunnable(new Runnable() {"+
"      @Override"+
"        log(\"Runnable.run\");"+
"      }"+
"    }, 3).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(integer);"+
"      }"+
"  }");
mCodes.put(Constants.Utility.timestamp,
"    Observable.just(1, 2).timestamp().subscribe(new Action1<Timestamped<Integer>>() {"+
"      @Override"+
"      public void call(Timestamped<Integer> integerTimestamped) {"+
"        log(\"\" + integerTimestamped.getValue() + \" \" + integerTimestamped.getTimestampMillis());"+
"      }"+
"  }");
mCodes.put(Constants.Utility.observeOn,
"    Observable.range(1, 2).observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {"+
"      @Override"+
"      public void call(Integer integer) {"+
"        log(\"\" + integer + \" on \" + Thread.currentThread().getName());"+
"      }"+
"  }");  
  }
  public String get(String key){
    return mCodes.get(key);
  }
}
