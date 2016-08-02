
package union.uc.com.rxjava_example.plugin;

import java.util.HashMap;
import java.util.Map;
import union.uc.com.rxjava_example.contants.Constants;

public class SampleCode{
  private Map<String, String> mCodes = new HashMap<>();
  public SampleCode(){

mCodes.put(Constants.BlockingObservable.singleOrDefault,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().singleOrDefault(3000);\n"+
"    log(i);\n");
mCodes.put(Constants.ErrorHandler.onErrorResumeNext,
"    Observable.just(1, \"abc\")\n"+
"              .cast(Integer.class)\n"+
"              .onErrorResumeNext(Observable.just(1, 2))\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              }, new Action1<Throwable>() {\n"+
"                @Override\n"+
"                public void call(Throwable throwable) {\n"+
"                  log(throwable);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Combine.groupjoin,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 10; ++i) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(1000);\n"+
"        }\n"+
"      }\n"+
"    })\n"+
"              .subscribeOn(Schedulers.newThread())\n"+
"              .groupJoin(Observable.create(new Observable.OnSubscribe<String>() {\n"+
"                @Override\n"+
"                public void call(Subscriber<? super String> subscriber) {\n"+
"                  final String[] arr = new String[] {\"a\", \"b\", \"c\"};\n"+
"                  for (int i = 0; i < arr.length; ++i) {\n"+
"                    subscriber.onNext(arr[i]);\n"+
"                    sleep(400);\n"+
"                  }\n"+
"                }\n"+
"              }).subscribeOn(Schedulers.newThread()), new Func1<Integer, Observable<Long>>() {\n"+
"                @Override\n"+
"                public Observable<Long> call(Integer integer) {\n"+
"                  return Observable.timer(1, TimeUnit.SECONDS);\n"+
"                }\n"+
"              }, new Func1<String, Observable<Long>>() {\n"+
"                @Override\n"+
"                public Observable<Long> call(String s) {\n"+
"                  return Observable.timer(2, TimeUnit.SECONDS);\n"+
"                }\n"+
"              }, new Func2<Integer, Observable<String>, Observable<String>>() {\n"+
"                @Override\n"+
"                public Observable<String> call(final Integer integer,\n"+
"                                               Observable<String> stringObservable) {\n"+
"                  return stringObservable.map(new Func1<String, String>() {\n"+
"                    @Override\n"+
"                    public String call(String s) {\n"+
"                      return \" \" + integer + s;\n"+
"                    }\n"+
"                  });\n"+
"                }\n"+
"              })\n"+
"              .subscribe(new Action1<Observable<String>>() {\n"+
"                @Override\n"+
"                public void call(Observable<String> o) {\n"+
"                  o.subscribe(new Action1<String>() {\n"+
"                    @Override\n"+
"                    public void call(String s) {\n"+
"                      log(s);\n"+
"                    }\n"+
"                  });\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.BlockingObservable.firstOrDefault,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().firstOrDefault(5000);\n"+
"    log(i);\n");
mCodes.put(Constants.CustomerOperator.customeOperator,
"    Observable.range(1, 10)\n"+
"              .lift(new MyMapOperator<Integer, Integer>(new Func1<Integer, Integer>() {\n"+
"                @Override\n"+
"                public Integer call(Integer integer) {\n"+
"                  return integer * 2;\n"+
"                }\n"+
"              }))\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Filter.firstOrDefault,
"    Observable.range(1, 10).firstOrDefault(3).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Async.toAsync,
"    Async.<Integer>toAsync(new Action0() {\n"+
"      @Override\n"+
"      public void call() {\n"+
"        log(\"action run on \" + Thread.currentThread().getName());\n"+
"        log(\"Action0.call ...on subscribe\");\n"+
"      }\n"+
"    }).call();\n"+
"    logLineSeperator();\n"+
"    Async.<Integer>toAsync(new Action0() {\n"+
"      @Override\n"+
"      public void call() {\n"+
"        log(\"action run on \" + Thread.currentThread().getName());\n"+
"        log(\"Action0.call\");\n"+
"      }\n"+
"    }).call().subscribe(new Action1<Void>() {\n"+
"      @Override\n"+
"      public void call(Void aVoid) {\n"+
"        log(\"Action1.call\");\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.cache,
"    Observable o = Observable.range(1, 10).cache();\n"+
"    o.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"s1:\" + integer);\n"+
"      }\n"+
"    });\n"+
"    o.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"s2:\" + integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.delay,
"    Observable.just(1, 2).delay(2, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.MathAggregate.maxBy,
"    logNotImplemented();\n");
mCodes.put(Constants.ObservableCreate.from_iterable,
"    Observable.from(Arrays.asList(new String[] {\"s1\", \"s2\", \"s3\"}))\n"+
"              .subscribe(new Action1<String>() {\n"+
"                @Override\n"+
"                public void call(String s) {\n"+
"                  log(s);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Subject.behavior,
"    BehaviorSubject<Integer> s = BehaviorSubject.create();\n"+
"    s.onNext(1);\n"+
"    s.onNext(2);\n"+
"    s.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"\" + integer);\n"+
"      }\n"+
"    });\n"+
"    s.onNext(3);\n");
mCodes.put(Constants.Transformation.map,
"    Observable.range(1, 10).map(new Func1<Integer, Integer>() {\n"+
"      @Override\n"+
"      public Integer call(Integer integer) {\n"+
"        return integer * 2;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.single,
"    Observable.just(1).single().subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.MathAggregate.sumDouble,
"    MathObservable.sumDouble(Observable.just(1.0, 2.0, 3.0, 4.0))\n"+
"                  .subscribe(new Action1<Double>() {\n"+
"                    @Override\n"+
"                    public void call(Double f) {\n"+
"                      log(f);\n"+
"                    }\n"+
"                  });\n");
mCodes.put(Constants.BlockingObservable.forEach,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().forEach(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Condition.takeWhileWithIndex,
"    logNotImplemented();\n");
mCodes.put(Constants.BlockingObservable.next,
"    Iterator<Integer> itr = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().next().iterator();\n"+
"    while (itr.hasNext()) {\n"+
"      log(itr.next());\n");
mCodes.put(Constants.MathAggregate.count,
"    logUseObservable();\n");
mCodes.put(Constants.ConnectableObservable.refCount,
"    ConnectableObservable<Integer> co =\n"+
"      Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"        @Override\n"+
"        public void call(Subscriber<? super Integer> subscriber) {\n"+
"          for (int i = 0; i < 5; ++i) {\n"+
"            subscriber.onNext(i);\n"+
"            sleep(500);\n"+
"          }\n"+
"          subscriber.onCompleted();\n"+
"        }\n"+
"      }).subscribeOn(Schedulers.newThread()).publish();\n"+
"    Subscription s1 = co.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"s1:\" + integer);\n"+
"      }\n"+
"    });\n"+
"    Subscription s2 = co.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"s2:\" + integer);\n"+
"      }\n"+
"    });\n"+
"    sleep(1000);\n"+
"    log(\"begin refcount!\");\n"+
"    Observable<Integer> o = co.refCount();\n"+
"    o.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"obs:\" + integer);\n"+
"      }\n"+
"    });\n"+
"    sleep(1000);\n"+
"    log(\"begin connect!\");\n"+
"    co.connect();\n"+
"    s1.unsubscribe();\n"+
"    s2.unsubscribe();\n"+
"    log(\"both disconnected!\");\n");
mCodes.put(Constants.MathAggregate.countLong,
"    logUseObservable();\n");
mCodes.put(Constants.ObservableCreate.interval,
"    final Subscription subscription =\n"+
"      Observable.interval(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {\n"+
"        @Override\n"+
"        public void call(Long aLong) {\n"+
"          log(aLong);\n"+
"        }\n"+
"      });\n"+
"    AsyncExecutor.SINGLETON.schedule(new Runnable() {\n"+
"      @Override\n"+
"        if (!subscription.isUnsubscribed()) {\n"+
"          subscription.unsubscribe();\n"+
"        }\n"+
"      }\n"+
"    }, 10, TimeUnit.SECONDS);\n");
mCodes.put(Constants.Condition.sequenceEqual,
"    Observable.sequenceEqual(Observable.just(1, 2, 3),\n"+
"                             Observable.range(1, 3),\n"+
"                             new Func2<Integer, Integer, Boolean>() {\n"+
"                               @Override\n"+
"                               public Boolean call(Integer integer1, Integer integer2) {\n"+
"                                 return integer1 == integer2;\n"+
"                               }\n"+
"                             }).subscribe(new Action1<Boolean>() {\n"+
"      @Override\n"+
"      public void call(Boolean aBoolean) {\n"+
"        log(aBoolean);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Transformation.flatMapIterable,
"    Observable.just(1, 2).flatMapIterable(new Func1<Integer, Iterable<String>>() {\n"+
"      @Override\n"+
"      public Iterable<String> call(Integer integer) {\n"+
"        return Arrays.asList(new String[] {\"\" + integer, \"\" + integer * 3});\n"+
"      }\n"+
"    }).subscribe(new Action1<String>() {\n"+
"      @Override\n"+
"      public void call(String s) {\n"+
"        log(s);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Strings.split,
"    StringObservable.split(Observable.just(\"ab#cd#ef\"), \"#\").subscribe(new Action1<String>() {\n"+
"      @Override\n"+
"      public void call(String s) {\n"+
"        log(s);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.using,
"    Observable.using(new Func0<File>() {\n"+
"      @Override\n"+
"      public File call() {\n"+
"        File f = null;\n"+
"        try {\n"+
"          f = new File(\"ABC\");\n"+
"          if (!f.exists()) {\n"+
"            f.createNewFile();\n"+
"          }\n"+
"        } catch (Exception e) {\n"+
"          Observable.error(e);\n"+
"        }\n"+
"        return f;\n"+
"      }\n"+
"    }, new Func1<File, Observable<String>>() {\n"+
"      @Override\n"+
"      public Observable<String> call(File file) {\n"+
"        return Observable.just(file.exists() ? \"exist\" : \"not exist\");\n"+
"      }\n"+
"    }, new Action1<File>() {\n"+
"      @Override\n"+
"      public void call(File file) {\n"+
"        if (file != null && file.exists()) {\n"+
"          file.delete();\n"+
"        }\n"+
"      }\n"+
"    }).subscribe(new Action1<String>() {\n"+
"      @Override\n"+
"      public void call(String exist) {\n"+
"        log(exist);\n"+
"      }\n"+
"    }, new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(throwable);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Condition.takeWhile,
"    Observable.range(1, 10).takeWhile(new Func1<Integer, Boolean>() {\n"+
"      @Override\n"+
"      public Boolean call(Integer i) {\n"+
"        return i < 3;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.ReactiveStream.materialize,"");
mCodes.put(Constants.Filter.ofType,
"    Observable.<Object>//\n"+
"                         just(1, \"2\", //\n"+
"                              new Exception(\"abc\")).\n"+
"                         ofType(Integer.class).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.doOnError,
"    Observable.just(1, \"3\").cast(Integer.class).doOnError(new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(\"doOnError:\" + throwable.getMessage());\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    }, new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(throwable);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Subject.replay_create_with_time,
"    try {\n"+
"      ReplaySubject<Integer> subject =\n"+
"        ReplaySubject.createWithTime(150, TimeUnit.MILLISECONDS, Schedulers.immediate());\n"+
"      subject.onNext(1);\n"+
"      Thread.sleep(100);\n"+
"      subject.onNext(2);\n"+
"      Thread.sleep(100);\n"+
"      subject.onNext(3);\n"+
"      subject.subscribe(new Action1<Integer>() {\n"+
"        @Override\n"+
"        public void call(Integer integer) {\n"+
"          log(\"\" + integer);\n"+
"        }\n"+
"      });\n"+
"      subject.onNext(4);\n"+
"    } catch (InterruptedException e) {\n"+
"      log(\"error:\" + e.getMessage());\n");
mCodes.put(Constants.Condition.defaultIfEmpty,
"    Observable.<Integer>empty().defaultIfEmpty(3).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.ErrorHandler.retry,
"    Observable.just(1, \"abc\", 2).cast(Integer.class).retry(2).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    }, new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(throwable);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.ignoreElements,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).ignoreElements().subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    }, new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(throwable);\n"+
"      }\n"+
"    }, new Action0() {\n"+
"      @Override\n"+
"      public void call() {\n"+
"        log(\"onComplete\");\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.takeLastBuffer,
"    Observable.range(1, 10)\n"+
"              .subscribeOn(Schedulers.newThread())\n"+
"              .takeLastBuffer(3)\n"+
"              .subscribe(new Action1<List<Integer>>() {\n"+
"                @Override\n"+
"                public void call(List<Integer> integers) {\n"+
"                  String s = \"\";\n"+
"                  for (Integer i : integers) {\n"+
"                    s += i;\n"+
"                  }\n"+
"                  log(s);\n"+
"                }\n"+
"              });\n"+
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 10; i++) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(100);\n"+
"        }\n"+
"      }\n"+
"    })\n"+
"              .subscribeOn(Schedulers.newThread())\n"+
"              .takeLastBuffer(3, 100, TimeUnit.MILLISECONDS)\n"+
"              .subscribe(new Action1<List<Integer>>() {\n"+
"                @Override\n"+
"                public void call(List<Integer> integers) {\n"+
"                  String s = \"\";\n"+
"                  for (Integer i : integers) {\n"+
"                    s += i;\n"+
"                  }\n"+
"                  log(s);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Combine.join,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 10; ++i) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(1000);\n"+
"        }\n"+
"      }\n"+
"    })\n"+
"              .subscribeOn(Schedulers.newThread())\n"+
"              .join(Observable.create(new Observable.OnSubscribe<String>() {\n"+
"                @Override\n"+
"                public void call(Subscriber<? super String> subscriber) {\n"+
"                  final String[] arr = new String[] {\"a\", \"b\", \"c\"};\n"+
"                  for (int i = 0; i < arr.length; ++i) {\n"+
"                    subscriber.onNext(arr[i]);\n"+
"                    sleep(400);\n"+
"                  }\n"+
"                }\n"+
"              }).subscribeOn(Schedulers.newThread()), new Func1<Integer, Observable<Long>>() {\n"+
"                @Override\n"+
"                public Observable<Long> call(Integer integer) {\n"+
"                  return Observable.timer(1, TimeUnit.SECONDS);\n"+
"                }\n"+
"              }, new Func1<String, Observable<Long>>() {\n"+
"                @Override\n"+
"                public Observable<Long> call(String s) {\n"+
"                  return Observable.timer(2, TimeUnit.SECONDS);\n"+
"                }\n"+
"              }, new Func2<Integer, String, String>() {\n"+
"                @Override\n"+
"                public String call(Integer integer, String s) {\n"+
"                  return \" \" + integer + \" \" + s;\n"+
"                }\n"+
"              })\n"+
"              .subscribe(new Action1<String>() {\n"+
"                @Override\n"+
"                public void call(String s) {\n"+
"                  log(s);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.MathAggregate.averageInteger,
"    MathObservable.averageInteger(Observable.just(2, 4, 1, 5))\n"+
"                  .subscribe(new Action1<Integer>() {\n"+
"                    @Override\n"+
"                    public void call(Integer integer) {\n"+
"                      log(integer);\n"+
"                    }\n"+
"                  });\n");
mCodes.put(Constants.Condition.doWhile,
"    Statement.doWhile(Observable.range(1, 10), new Func0<Boolean>() {\n"+
"      boolean r = false;\n"+
"      @Override\n"+
"      public Boolean call() {\n"+
"        r = !r;\n"+
"        return r;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.takeFirst,
"    Observable.range(1, 10).takeFirst(new Func1<Integer, Boolean>() {\n"+
"      @Override\n"+
"      public Boolean call(Integer integer) {\n"+
"        return integer % 2 == 0;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.throttleLast,
"    final Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 5; i++) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(300);\n"+
"        }\n"+
"      }\n"+
"    }).throttleLast(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Condition.skipUtil,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 10; ++i) {\n"+
"          sleep(200);\n"+
"          subscriber.onNext(i);\n"+
"        }\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    })\n"+
"              .subscribeOn(Schedulers.newThread())\n"+
"              .skipUntil(Observable.timer(1, TimeUnit.SECONDS))\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Transformation.concatMap,
"    Observable.just(1, 2).concatMap(new Func1<Integer, Observable<Integer>>() {\n"+
"      @Override\n"+
"      public Observable<Integer> call(Integer integer) {\n"+
"        return Observable.just(integer);\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.skipLast,
"    Observable.range(1, 10).skipLast(3).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Subject.replay,
"    ReplaySubject<Integer> subject = ReplaySubject.create();\n"+
"    subject.onNext(1);\n"+
"    subject.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"Subscriber1:\" + integer);\n"+
"      }\n"+
"    });\n"+
"    subject.onNext(2);\n"+
"    subject.onNext(3);\n"+
"    subject.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"Subscriber2:\" + integer);\n"+
"      }\n"+
"    });\n"+
"    subject.onNext(4);\n");
mCodes.put(Constants.ObservableCreate.from_future,
"    Observable.from(AsyncExecutor.SINGLETON.submit(new Callable<String>() {\n"+
"      @Override\n"+
"      public String call() throws Exception {\n"+
"        return \"I 'm from future of thread \" + Thread.currentThread().getName();\n"+
"      }\n"+
"    })).subscribe(new Action1<String>() {\n"+
"      @Override\n"+
"      public void call(String s) {\n"+
"        log(s);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.ConnectableObservable.publish,
"    log(\"showed in connect!\");\n");
mCodes.put(Constants.ObservableCreate.just,
"    Observable.just(1, 2, 3).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.ObservableCreate.error,
"    Observable.error(new Exception(\"abc\")).subscribe(new Action1<Object>() {\n"+
"      @Override\n"+
"      public void call(Object o) {\n"+
"        log(\"onNext\");\n"+
"      }\n"+
"    }, new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(\"onError:\" + throwable.getMessage());\n"+
"      }\n"+
"    }, new Action0() {\n"+
"      @Override\n"+
"      public void call() {\n"+
"        log(\"onComplete\");\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.delaySubscription,
"    Observable.just(1, 2)\n"+
"              .delaySubscription(2, TimeUnit.SECONDS)\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.ObservableCreate.repeat,
"    log(\"RxJava not implemented!\");\n");
mCodes.put(Constants.MathAggregate.min,
"    MathObservable.min(Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.distinctUntilChanged,
"    Observable.just(1, 1, 2, 2, 3, 4, 4, 1, 1, 5)\n"+
"              .distinctUntilChanged()\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Utility.singleOrDefault,
"    Observable.<Integer>empty().singleOrDefault(10).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.elementAtOrDefault,
"    Observable.range(1, 10).elementAtOrDefault(100, 1000).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Combine.zip,
"    Observable.just(1, 2, 3)\n"+
"              .zipWith(Observable.just(\"a\", \"b\", \"c\"), new Func2<Integer, String, String>() {\n"+
"                @Override\n"+
"                public String call(Integer integer, String s) {\n"+
"                  return s + integer * 2;\n"+
"                }\n"+
"              })\n"+
"              .subscribe(new Action1<String>() {\n"+
"                @Override\n"+
"                public void call(String s) {\n"+
"                  log(s);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.BlockingObservable.single,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().single();\n"+
"    log(i);\n");
mCodes.put(Constants.Condition.skipWhile,
"    Observable.range(1, 10).skipWhile(new Func1<Integer, Boolean>() {\n"+
"      @Override\n"+
"      public Boolean call(Integer i) {\n"+
"        return i < 3;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.MathAggregate.averageFloat,
"    MathObservable.averageFloat(Observable.just(1.0f, 2.0f, 3.0f, 4.0f))\n"+
"                  .subscribe(new Action1<Float>() {\n"+
"                    @Override\n"+
"                    public void call(Float f) {\n"+
"                      log(f);\n"+
"                    }\n"+
"                  });\n");
mCodes.put(Constants.Transformation.buffer,
"    Observable.range(1, 10).buffer(3).subscribe(new Action1<List<Integer>>() {\n"+
"      @Override\n"+
"      public void call(List<Integer> integers) {\n"+
"        String s = \"\";\n"+
"        for (Integer i : integers) {\n"+
"          s += i;\n"+
"        }\n"+
"        log(s);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Scheduler.compute,
"    Observable.just(\"a\", \"b\")\n"+
"              .observeOn(Schedulers.computation())\n"+
"              .subscribe(new Action1<String>() {\n"+
"                @Override\n"+
"                public void call(String s) {\n"+
"                  log(s + \" on \" + Thread.currentThread().getName());\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Plugin.start_hook,
"    Observable.range(1, 10).observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.timeInterval,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        sleep(1000);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    })\n"+
"              .subscribeOn(Schedulers.newThread())\n"+
"              .timeInterval()\n"+
"              .subscribe(new Action1<TimeInterval<Integer>>() {\n"+
"                @Override\n"+
"                public void call(TimeInterval<Integer> integerTimeInterval) {\n"+
"                  log(\"\" + integerTimeInterval.getValue() + \" \" +\n"+
"                      integerTimeInterval.getIntervalInMilliseconds());\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.MathAggregate.sumFloat,
"    MathObservable.sumFloat(Observable.just(1.0f, 2.0f, 3.0f, 4.0f))\n"+
"                  .subscribe(new Action1<Float>() {\n"+
"                    @Override\n"+
"                    public void call(Float f) {\n"+
"                      log(f);\n"+
"                    }\n"+
"                  });\n");
mCodes.put(Constants.ObservableCreate.create,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.timeout,
"    Observable.<Integer>never().timeout(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    }, new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(throwable);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Condition.switchcase,
"    Observable<Integer> source1 = Observable.just(1, 2, 3);\n"+
"    Observable<Integer> source2 = Observable.just(4, 5, 6);\n"+
"    Map<Integer, Observable<Integer>> map = new HashMap<Integer, Observable<Integer>>();\n"+
"    map.put(1, source1);\n"+
"    map.put(2, source2);\n"+
"    Statement.switchCase(new Func0<Integer>() {\n"+
"      int count = 1;\n"+
"      @Override\n"+
"      public Integer call() {\n"+
"        return count++;\n"+
"      }\n"+
"    }, map).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Condition.ifThen,
"    Statement.ifThen(new Func0<Boolean>() {\n"+
"      boolean r = false;\n"+
"      @Override\n"+
"      public Boolean call() {\n"+
"        r = !r;\n"+
"        return r;\n"+
"      }\n"+
"    }, Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Condition.contains,
"    Observable.range(1, 10).contains(3).subscribe(new Action1<Boolean>() {\n"+
"      @Override\n"+
"      public void call(Boolean aBoolean) {\n"+
"        log(aBoolean);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.doOnCompleted,
"    Observable.range(1, 3).doOnCompleted(new Action0() {\n"+
"      @Override\n"+
"      public void call() {\n"+
"        log(\"onCompleted\");\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.doOnEach,
"    Observable.range(1, 10).doOnEach(new Action1<Notification<? super Integer>>() {\n"+
"      @Override\n"+
"      public void call(Notification<? super Integer> notification) {\n"+
"        log(\"doOnEach:\" + notification.getKind() + \" \" + notification.getValue());\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Combine.switchOnNext,
"    logNotImplemented();\n");
mCodes.put(Constants.Strings.encode,
"    StringObservable.encode(Observable.just(\"abc\"), \"UTF-8\").subscribe(new Action1<byte[]>() {\n"+
"      @Override\n"+
"      public void call(byte[] bytes) {\n"+
"        log(bytes.length);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.BlockingObservable.mostRecent,
"    Iterator<Integer> itr = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().mostRecent(5000).iterator();\n"+
"    while (itr.hasNext()) {\n"+
"      log(itr.next());\n");
mCodes.put(Constants.MathAggregate.collect,
"    logUseObservable();\n");
mCodes.put(Constants.ErrorHandler.retryWhen,
"    Observable.just(1, \"abc\", \"2\")\n"+
"              .cast(Integer.class)\n"+
"              .retryWhen(new Func1<Observable<? extends Throwable>, Observable<Long>>() {\n"+
"                @Override\n"+
"                public Observable<Long> call(Observable<? extends Throwable> observable) {\n"+
"                  observable.subscribe(new Action1<Throwable>() {\n"+
"                    @Override\n"+
"                    public void call(Throwable throwable) {\n"+
"                      log(\"error inner:\" + throwable.getMessage());\n"+
"                    }\n"+
"                  });\n"+
"                  return Observable.timer(1, TimeUnit.SECONDS);\n"+
"                }\n"+
"              })\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              }, new Action1<Throwable>() {\n"+
"                @Override\n"+
"                public void call(Throwable throwable) {\n"+
"                  log(throwable);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.ErrorHandler.onErrorReturn,
"    Observable.just(1, \"abc\")\n"+
"              .cast(Integer.class)\n"+
"              .onErrorReturn(new Func1<Throwable, Integer>() {\n"+
"                @Override\n"+
"                public Integer call(Throwable throwable) {\n"+
"                  return -1;\n"+
"                }\n"+
"              })\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              }, new Action1<Throwable>() {\n"+
"                @Override\n"+
"                public void call(Throwable throwable) {\n"+
"                  log(throwable);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Scheduler.trampoline,
"    Observable.just(\"a\", \"b\")\n"+
"              .observeOn(Schedulers.trampoline())\n"+
"              .subscribe(new Action1<String>() {\n"+
"                @Override\n"+
"                public void call(String s) {\n"+
"                  log(s + \" on \" + Thread.currentThread().getName());\n"+
"                }\n"+
"              });\n"+
"    log(\"i'm on thread \" + Thread.currentThread().getName());\n");
mCodes.put(Constants.BlockingObservable.last,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().last();\n"+
"    log(i);\n");
mCodes.put(Constants.ObservableCreate.empty,
"    Observable.<String>empty().subscribe(new Observer<String>() {\n"+
"      @Override\n"+
"      public void onNext(String s) {\n"+
"        log(\"onNext:\" + s);\n"+
"      }\n"+
"      @Override\n"+
"      public void onCompleted() {\n"+
"        log(\"onCompleted\");\n"+
"      }\n"+
"      @Override\n"+
"      public void onError(Throwable e) {\n"+
"        log(\"onError:\" + e.getMessage());\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.MathAggregate.toList,
"    logUseObservable();\n");
mCodes.put(Constants.Combine.mergeDelayError,
"    logNotImplemented();\n");
mCodes.put(Constants.BlockingObservable.getIterator,
"    Iterator<Integer> itr = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().getIterator();\n"+
"    while (itr.hasNext()) {\n"+
"      log(itr.next());\n");
mCodes.put(Constants.Scheduler.new_thread,
"    Observable.just(\"a\", \"b\")\n"+
"              .observeOn(Schedulers.newThread())\n"+
"              .subscribe(new Action1<String>() {\n"+
"                @Override\n"+
"                public void call(String s) {\n"+
"                  log(s + \" on \" + Thread.currentThread().getName());\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.ErrorHandler.onExceptionResumeNext,
"    Observable.just(1, \"abc\", \"2\")\n"+
"              .cast(Integer.class)\n"+
"              .onExceptionResumeNext(Observable.just(5, 6))\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              }, new Action1<Throwable>() {\n"+
"                @Override\n"+
"                public void call(Throwable throwable) {\n"+
"                  log(throwable);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Utility.doOnSubscribe,
"    Observable.just(1, 2).doOnSubscribe(new Action0() {\n"+
"      @Override\n"+
"      public void call() {\n"+
"        log(\"OnSubscribe\");\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.finallyDo,
"    Observable.just(1, 2).finallyDo(new Action0() {\n"+
"      @Override\n"+
"      public void call() {\n"+
"        log(\"finallyDo\");\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.ObservableCreate.never,
"    Observable.<Void>never().subscribe(new Action1<Void>() {\n"+
"      @Override\n"+
"      public void call(Void aVoid) {\n"+
"        log(\"it's impossible!\");\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.doOnUnsubscribe,
"    Subscription subscription = Observable.just(1, 2).doOnUnsubscribe(new Action0() {\n"+
"      @Override\n"+
"      public void call() {\n"+
"        log(\"OnUnSubscribe\");\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n"+
"    subscription.unsubscribe();\n");
mCodes.put(Constants.Transformation.cast,
"    Observable.<Object>just(1, 2, 3).\n"+
"                                      cast(Integer.class).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.doOnTerminate,
"    Observable.just(1, 2).doOnTerminate(new Action0() {\n"+
"      @Override\n"+
"      public void call() {\n"+
"        log(\"OnTerminate\");\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Subject.behavior_with_init_value,
"    BehaviorSubject<Integer> s = BehaviorSubject.create(0);\n"+
"    s.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"\" + integer);\n"+
"      }\n"+
"    });\n"+
"    s.onNext(1);\n");
mCodes.put(Constants.Scheduler.io,
"    Observable.just(\"a\", \"b\").observeOn(Schedulers.io()).subscribe(new Action1<String>() {\n"+
"      @Override\n"+
"      public void call(String s) {\n"+
"        log(s + \" on \" + Thread.currentThread().getName());\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.filter,
"    Observable.range(1, 10).filter(new Func1<Integer, Boolean>() {\n"+
"      @Override\n"+
"      public Boolean call(Integer integer) {\n"+
"        return integer % 2 == 0;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.debounce,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        sleep(500);\n"+
"        subscriber.onNext(2);\n"+
"        sleep(1000);\n"+
"        subscriber.onNext(3);\n"+
"        sleep(2000);\n"+
"        subscriber.onNext(4);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    })\n"+
"              .subscribeOn(Schedulers.newThread())\n"+
"              .debounce(1, TimeUnit.SECONDS)\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Strings.stringConcat,
"    StringObservable.stringConcat(Observable.just(\"abc\", \"def\"))\n"+
"                    .subscribe(new Action1<String>() {\n"+
"                      @Override\n"+
"                      public void call(String s) {\n"+
"                        log(s);\n"+
"                      }\n"+
"                    });\n");
mCodes.put(Constants.Condition.amb,
"    Observable.amb(Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"                     @Override\n"+
"                     public void call(Subscriber<? super Integer> subscriber) {\n"+
"                       sleep(1000);\n"+
"                       subscriber.onNext(1);\n"+
"                       subscriber.onNext(11);\n"+
"                       subscriber.onCompleted();\n"+
"                     }\n"+
"                   }).subscribeOn(Schedulers.newThread()),\n"+
"                   Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"                     @Override\n"+
"                     public void call(Subscriber<? super Integer> subscriber) {\n"+
"                       sleep(500);\n"+
"                       subscriber.onNext(2);\n"+
"                       subscriber.onNext(22);\n"+
"                       subscriber.onCompleted();\n"+
"                     }\n"+
"                   }).subscribeOn(Schedulers.newThread()),\n"+
"                   Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"                     @Override\n"+
"                     public void call(Subscriber<? super Integer> subscriber) {\n"+
"                       sleep(300);\n"+
"                       subscriber.onNext(3);\n"+
"                       subscriber.onNext(33);\n"+
"                       subscriber.onCompleted();\n"+
"                     }\n"+
"                   }).subscribeOn(Schedulers.newThread())).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.elementAt,
"    Observable.range(1, 10).elementAt(3).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Combine.switchIfEmpty,
"    Observable.<Integer>empty().switchIfEmpty(Observable.just(4, 5))\n"+
"                               .subscribe(new Action1<Integer>() {\n"+
"                                 @Override\n"+
"                                 public void call(Integer integer) {\n"+
"                                   log(integer);\n"+
"                                 }\n"+
"                               });\n");
mCodes.put(Constants.Async.startFuture,
"    Async.startFuture(new Func0<Future<Integer>>() {\n"+
"      @Override\n"+
"      public Future<Integer> call() {\n"+
"        return AsyncExecutor.SINGLETON.submit(new Callable<Integer>() {\n"+
"          @Override\n"+
"          public Integer call() throws Exception {\n"+
"            return 3;\n"+
"          }\n"+
"        });\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Condition.WhileDo,
"    Statement.whileDo(Observable.just(1, 2, 3), new Func0<Boolean>() {\n"+
"      int count = 2;\n"+
"      @Override\n"+
"      public Boolean call() {\n"+
"        return count-- > 0;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.ObservableCreate.repeatWhen,
"    log(\"RxJava not implemented!\");\n");
mCodes.put(Constants.Filter.skip,
"    Observable.range(1, 10).skip(2).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Strings.decode,
"    StringObservable.decode(Observable.just(\"ABC\".getBytes(Charset.forName(\"UTF-8\"))),\n"+
"                            Charset.forName(\"UTF-8\")).subscribe(new Action1<String>() {\n"+
"      @Override\n"+
"      public void call(String s) {\n"+
"        log(s);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.ConnectableObservable.replay,
"    Observable<Integer> o = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 5; ++i) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(500);\n"+
"        }\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread());\n"+
"    o.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"s1:\" + integer);\n"+
"      }\n"+
"    });\n"+
"    sleep(1000);\n"+
"    ConnectableObservable<Integer> co = o.publish();\n"+
"    co.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"s2:\" + integer);\n"+
"      }\n"+
"    });\n"+
"    log(\"begin connect\");\n"+
"    co.connect();\n");
mCodes.put(Constants.Combine.combineLatest,
"    Observable.combineLatest(Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"                               @Override\n"+
"                               public void call(Subscriber<? super Integer> subscriber) {\n"+
"                                 for (int i = 0; i < 10; ++i) {\n"+
"                                   subscriber.onNext(i);\n"+
"                                   sleep(1000);\n"+
"                                 }\n"+
"                               }\n"+
"                             }).subscribeOn(Schedulers.newThread()),\n"+
"                             Observable.create(new Observable.OnSubscribe<String>() {\n"+
"                               @Override\n"+
"                               public void call(Subscriber<? super String> subscriber) {\n"+
"                                 final String[] arr = new String[] {\"a\", \"b\", \"c\"};\n"+
"                                 for (int i = 0; i < arr.length; ++i) {\n"+
"                                   subscriber.onNext(arr[i]);\n"+
"                                   sleep(400);\n"+
"                                 }\n"+
"                               }\n"+
"                             }).subscribeOn(Schedulers.newThread()),\n"+
"                             new Func2<Integer, String, String>() {\n"+
"                               @Override\n"+
"                               public String call(Integer integer, String s) {\n"+
"                                 return \"\" + integer + \" \" + s;\n"+
"                               }\n"+
"                             }).subscribe(new Action1<String>() {\n"+
"      @Override\n"+
"      public void call(String s) {\n"+
"        log(s);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Condition.exists,
"    Observable.just(1, 2, 3).exists(new Func1<Integer, Boolean>() {\n"+
"      @Override\n"+
"      public Boolean call(Integer integer) {\n"+
"        return integer > 3;\n"+
"      }\n"+
"    }).subscribe(new Action1<Boolean>() {\n"+
"      @Override\n"+
"      public void call(Boolean aBoolean) {\n"+
"        log(aBoolean);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Transformation.switchMap,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 3; ++i) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(500);\n"+
"        }\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).switchMap(new Func1<Integer, Observable<Integer>>() {\n"+
"      @Override\n"+
"      public Observable<Integer> call(final Integer integer) {\n"+
"        return Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"          @Override\n"+
"          public void call(Subscriber<? super Integer> subscriber) {\n"+
"            subscriber.onNext(integer);\n"+
"            sleep(500);\n"+
"            subscriber.onNext(integer);\n"+
"            subscriber.onCompleted();\n"+
"          }\n"+
"        }).subscribeOn(Schedulers.newThread());\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer s) {\n"+
"        log(s);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.BlockingObservable.toFuture,
"    Future<Integer> future = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().toFuture();\n"+
"    try {\n"+
"      log(future.get());\n"+
"    } catch (InterruptedException e) {\n"+
"      e.printStackTrace();\n"+
"      log(e);\n"+
"    } catch (ExecutionException e) {\n"+
"      e.printStackTrace();\n"+
"      log(e);\n");
mCodes.put(Constants.BlockingObservable.latest,
"    Iterator<Integer> itr = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().latest().iterator();\n"+
"    while (itr.hasNext()) {\n"+
"      log(itr.next());\n");
mCodes.put(Constants.Transformation.window,
"    Observable.range(1, 10).window(3).subscribe(new Action1<Observable<Integer>>() {\n"+
"      @Override\n"+
"      public void call(final Observable<Integer> integerObservable) {\n"+
"        integerObservable.subscribe(new Action1<Integer>() {\n"+
"          @Override\n"+
"          public void call(Integer integer) {\n"+
"            log(integer + \" of window \" + integerObservable);\n"+
"          }\n"+
"        });\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Condition.all,
"    Observable.just(2, 4, 6).all(new Func1<Integer, Boolean>() {\n"+
"      @Override\n"+
"      public Boolean call(Integer integer) {\n"+
"        return integer % 2 == 0;\n"+
"      }\n"+
"    }).subscribe(new Action1<Boolean>() {\n"+
"      @Override\n"+
"      public void call(Boolean aBoolean) {\n"+
"        log(aBoolean);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Combine.merge,
"    Observable.just(1, 2, 3)\n"+
"              .mergeWith(Observable.just(4, 5, 6))\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.MathAggregate.reduce,
"    logUseObservable();\n");
mCodes.put(Constants.Filter.takeLast,
"    Observable.range(1, 10).takeLast(3).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Subject.publish,
"    PublishSubject<Integer> subject = PublishSubject.create();\n"+
"    subject.onNext(1);\n"+
"    subject.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"\" + integer);\n"+
"      }\n"+
"    });\n"+
"    subject.onNext(2);\n"+
"    subject.onNext(3);\n"+
"    subject.onNext(4);\n");
mCodes.put(Constants.Strings.from,
"    StringObservable.from(new ByteArrayInputStream(\"ABC\".getBytes()))\n"+
"                    .subscribe(new Action1<byte[]>() {\n"+
"                      @Override\n"+
"                      public void call(byte[] bytes) {\n"+
"                        log(bytes.length);\n"+
"                      }\n"+
"                    });\n");
mCodes.put(Constants.MathAggregate.concat,
"    logUseObservable();\n");
mCodes.put(Constants.Strings.byLine,
"    StringObservable.byLine(Observable.just(\"ab\r\ncd\r\nef\", \"12\r\n34\"))\n"+
"                    .subscribe(new Action1<String>() {\n"+
"                      @Override\n"+
"                      public void call(String s) {\n"+
"                        log(s);\n"+
"                      }\n"+
"                    });\n");
mCodes.put(Constants.MathAggregate.max,
"    MathObservable.max(Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Scheduler.self_define,
"    Observable.just(\"a\", \"b\")\n"+
"              .observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))\n"+
"              .subscribe(new Action1<String>() {\n"+
"                @Override\n"+
"                public void call(String s) {\n"+
"                  log(s + \" on \" + Thread.currentThread().getName());\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.MathAggregate.toMap,
"    logUseObservable();\n");
mCodes.put(Constants.Async.fromCallable,
"    Async.fromCallable(new Callable<Integer>() {\n"+
"      @Override\n"+
"      public Integer call() throws Exception {\n"+
"        log(\"action run on \" + Thread.currentThread().getName());\n"+
"        return 3;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.MathAggregate.minBy,
"    logNotImplemented();\n");
mCodes.put(Constants.Async.forEachFuture,
"    Future<Void> f = Async.forEachFuture(Observable.just(1, 2, 3), new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    }, new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(throwable);\n"+
"      }\n"+
"    });\n"+
"    log(\"task done:\" + f.isDone());\n");
mCodes.put(Constants.MathAggregate.toMultiMap,
"    logUseObservable();\n");
mCodes.put(Constants.Condition.isEmpty,
"    Observable.empty().isEmpty().subscribe(new Action1<Boolean>() {\n"+
"      @Override\n"+
"      public void call(Boolean aBoolean) {\n"+
"        log(aBoolean);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Async.fromAction,
"    Async.fromAction(new Action0() {\n"+
"      @Override\n"+
"      public void call() {\n"+
"        log(\"action run on \" + Thread.currentThread().getName());\n"+
"        log(\"Action0.call\");\n"+
"      }\n"+
"    }, 3).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.MathAggregate.toSortedList,
"    logUseObservable();\n");
mCodes.put(Constants.Combine.startWith,
"    logNotImplemented();\n");
mCodes.put(Constants.Scheduler.immediate,
"    Observable.just(\"a\", \"b\")\n"+
"              .observeOn(Schedulers.immediate())\n"+
"              .subscribe(new Action1<String>() {\n"+
"                @Override\n"+
"                public void call(String s) {\n"+
"                  log(s + \" on \" + Thread.currentThread().getName());\n"+
"                }\n"+
"              });\n"+
"    log(\"i'm on thread \" + Thread.currentThread().getName());\n");
mCodes.put(Constants.Utility.subscribeOn,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(0);\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onCompleted();\n"+
"        log(\"here in: \" + Thread.currentThread().getName());\n"+
"      }\n"+
"    })\n"+
"              .subscribeOn(Schedulers.computation())\n"+
"              .observeOn(Schedulers.io())\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(\"\" + integer + \" on \" + Thread.currentThread().getName());\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.BlockingObservable.toIterable,
"    Iterator<Integer> itr = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().toIterable().iterator();\n"+
"    while (itr.hasNext()) {\n"+
"      log(itr.next());\n");
mCodes.put(Constants.ObservableCreate.range,
"    Observable.range(1, 10).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.throttleFirst,
"    final Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 5; i++) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(300);\n"+
"        }\n"+
"      }\n"+
"    }).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n"+
"    AsyncExecutor.SINGLETON.schedule(new Runnable() {\n"+
"      @Override\n"+
"        if (!subscription.isUnsubscribed()) {\n"+
"          subscription.unsubscribe();\n"+
"        }\n"+
"      }\n"+
"    }, 3, TimeUnit.SECONDS);\n");
mCodes.put(Constants.Filter.take,
"    Observable.range(1, 10).take(3).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.MathAggregate.averageLong,
"    MathObservable.averageLong(Observable.just(2L, 4L, 1L, 5L)).subscribe(new Action1<Long>() {\n"+
"      @Override\n"+
"      public void call(Long l) {\n"+
"        log(l);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.ObservableCreate.timer,
"    Observable.timer(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {\n"+
"      @Override\n"+
"      public void call(Long aLong) {\n"+
"        log(aLong);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.throttleWithTimeout,
"    Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 10; i++) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(500);\n"+
"        }\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    })\n"+
"              .subscribeOn(Schedulers.newThread())\n"+
"              .throttleWithTimeout(2, TimeUnit.SECONDS)\n"+
"              .observeOn(Schedulers.newThread())\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Combine.and_then_when,
"    Plan0<String> p = //\n"+
"      JoinObservable.from(Observable.just(1, 2, 3))//\n"+
"                    .and(Observable.just(\"a\", \"b\", \"c\"))//\n"+
"                    .and(Observable.just(\"d\", \"e\"))//\n"+
"                    .then(new Func3<Integer, String, String, String>() {\n"+
"                      @Override\n"+
"                      public String call(Integer integer, String s, String s2) {\n"+
"                        String ret = \"\" + integer + \" \" + s + \" \" + s2;\n"+
"                        return ret;\n"+
"                      }\n"+
"                    });\n"+
"    JoinObservable.when(p).toObservable().subscribe(new Action1<String>() {\n"+
"      @Override\n"+
"      public void call(String s) {\n"+
"        log(s);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Subject.async,
"    AsyncSubject<Integer> s = AsyncSubject.create();\n"+
"    s.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"\" + integer);\n"+
"      }\n"+
"    });\n"+
"    s.onNext(0);\n"+
"    s.onNext(1);\n"+
"    s.onNext(2);\n"+
"    s.onCompleted();\n");
mCodes.put(Constants.BlockingObservable.first,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onNext(1);\n"+
"        subscriber.onNext(2);\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().first();\n"+
"    log(i);\n");
mCodes.put(Constants.Condition.takeUntil,
"    Observable.range(1, 10).takeUntil(new Func1<Integer, Boolean>() {\n"+
"      @Override\n"+
"      public Boolean call(Integer i) {\n"+
"        return i > 3;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.last,
"    //normal last\n"+
"    Observable.range(1, 10).last().subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n"+
"    // last with predicate\n"+
"    Observable.range(1, 10).last(new Func1<Integer, Boolean>() {\n"+
"      @Override\n"+
"      public Boolean call(Integer integer) {\n"+
"        return integer % 2 == 0;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.BlockingObservable.lastOrDefault,
"    Integer i = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).toBlocking().lastOrDefault(5000);\n"+
"    log(i);\n");
mCodes.put(Constants.Async.runAsync,
"    Async.runAsync(Schedulers.io(), new Action2<Observer<? super Integer>, Subscription>() {\n"+
"      @Override\n"+
"      public void call(Observer<? super Integer> observer, Subscription subscription) {\n"+
"        log(\"Action2 run on \" + Thread.currentThread().getName());\n"+
"        observer.onNext(1);\n"+
"        observer.onNext(2);\n"+
"        observer.onCompleted();\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.MathAggregate.sumInteger,
"    MathObservable.sumInteger(Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.MathAggregate.sumLong,
"    MathObservable.sumLong(Observable.just(1L, 2L, 3L)).subscribe(new Action1<Long>() {\n"+
"      @Override\n"+
"      public void call(Long l) {\n"+
"        log(l);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Transformation.groupBy,
"    Observable.range(1, 10).groupBy(new Func1<Integer, String>() {\n"+
"      @Override\n"+
"      public String call(Integer integer) {\n"+
"        return (integer % 2 == 0) ? \"even\" : \"odd\";\n"+
"      }\n"+
"    }).subscribe(new Action1<GroupedObservable<String, Integer>>() {\n"+
"      @Override\n"+
"      public void call(final GroupedObservable<String, Integer> stringIntegerGroupedObservable) {\n"+
"        log(\"group ok:\" + stringIntegerGroupedObservable.getKey());\n"+
"        stringIntegerGroupedObservable.subscribe(new Action1<Integer>() {\n"+
"          @Override\n"+
"          public void call(Integer integer) {\n"+
"            log(integer + \" of group \" + stringIntegerGroupedObservable.getKey());\n"+
"          }\n"+
"        });\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Strings.join,
"    StringObservable.join(Observable.just(\"abc\", \"def\"), \"#\").subscribe(new Action1<String>() {\n"+
"      @Override\n"+
"      public void call(String s) {\n"+
"        log(s);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.serialize,
"    Observable<Integer> o = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 3; i++) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(1000);\n"+
"        }\n"+
"        subscriber.onError(new Exception(\"xx\"));\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread());\n"+
"    o.observeOn(Schedulers.computation()).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"no serialize1 on compute:\" + integer);\n"+
"      }\n"+
"    }, new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(\"Exception no serialize1 on compute:\" + throwable.getMessage());\n"+
"      }\n"+
"    });\n"+
"    o.observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"no serialize1 on io:\" + integer);\n"+
"      }\n"+
"    }, new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(\"Exception no serialize1 on io:\" + throwable.getMessage());\n"+
"      }\n"+
"    });\n"+
"    o.serialize().subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"serialize:\" + integer);\n"+
"      }\n"+
"    }, new Action1<Throwable>() {\n"+
"      @Override\n"+
"      public void call(Throwable throwable) {\n"+
"        log(\"Exception serialize1:\" + throwable.getMessage());\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Transformation.flatMap,
"    Observable.just(1, 2).flatMap(new Func1<Integer, Observable<Integer>>() {\n"+
"      @Override\n"+
"      public Observable<Integer> call(final Integer integer) {\n"+
"        return Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"          @Override\n"+
"          public void call(Subscriber<? super Integer> subscriber) {\n"+
"            subscriber.onNext(integer);\n"+
"            subscriber.onCompleted();\n"+
"          }\n"+
"        }).subscribeOn(Schedulers.newThread());\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.sample,
"    final Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 10; i++) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(100);\n"+
"        }\n"+
"      }\n"+
"    })\n"+
"                                                .subscribeOn(Schedulers.newThread())\n"+
"                                                .sample(1, TimeUnit.SECONDS)\n"+
"                                                .subscribe(new Action1<Integer>() {\n"+
"                                                  @Override\n"+
"                                                  public void call(Integer integer) {\n"+
"                                                    log(integer);\n"+
"                                                  }\n"+
"                                                });\n"+
"    AsyncExecutor.SINGLETON.schedule(new Runnable() {\n"+
"      @Override\n"+
"        if (!subscription.isUnsubscribed()) {\n"+
"          subscription.unsubscribe();\n"+
"        }\n"+
"      }\n"+
"    }, 3, TimeUnit.SECONDS);\n");
mCodes.put(Constants.Utility.materialize,
"    Observable o1 = Observable.range(1, 3).materialize();\n"+
"    o1.subscribe(new Action1<Notification<Integer>>() {\n"+
"      @Override\n"+
"      public void call(Notification<Integer> integerNotification) {\n"+
"        log(\"******\");\n"+
"        log(\"kind:\" + integerNotification.getKind());\n"+
"        log(\"value:\" + integerNotification.getValue());\n"+
"      }\n"+
"    });\n"+
"    o1.dematerialize().subscribe(new Action1() {\n"+
"      @Override\n"+
"      public void call(Object o) {\n"+
"        log(o.toString());\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.ObservableCreate.defer,
"    Observable<Long> now = Observable.defer(new Func0<Observable<Long>>() {\n"+
"      @Override\n"+
"      public Observable<Long> call() {\n"+
"        return Observable.just(System.currentTimeMillis());\n"+
"      }\n"+
"    });\n"+
"    now.subscribe(new Action1<Long>() {\n"+
"      @Override\n"+
"      public void call(Long aLong) {\n"+
"        log(aLong);\n"+
"      }\n"+
"    });\n"+
"    try {\n"+
"      Thread.sleep(1000);\n"+
"    } catch (Exception e) {\n"+
"      log(\"exception:\" + e.getMessage());\n"+
"    }\n"+
"    now.subscribe(new Action1<Long>() {\n"+
"      @Override\n"+
"      public void call(Long aLong) {\n"+
"        log(aLong);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.ConnectableObservable.connect,
"    Observable<Integer> o = Observable.create(new Observable.OnSubscribe<Integer>() {\n"+
"      @Override\n"+
"      public void call(Subscriber<? super Integer> subscriber) {\n"+
"        for (int i = 0; i < 5; ++i) {\n"+
"          subscriber.onNext(i);\n"+
"          sleep(500);\n"+
"        }\n"+
"        subscriber.onCompleted();\n"+
"      }\n"+
"    }).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread());\n"+
"    o.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"s1:\" + integer);\n"+
"      }\n"+
"    });\n"+
"    sleep(1000);\n"+
"    ConnectableObservable<Integer> co = o.publish();\n"+
"    co.subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"s2:\" + integer);\n"+
"      }\n"+
"    });\n"+
"    log(\"begin connect\");\n"+
"    co.connect();\n");
mCodes.put(Constants.MathAggregate.averageDouble,
"    MathObservable.averageDouble(Observable.just(1.0, 2.0, 3.0, 4.0))\n"+
"                  .subscribe(new Action1<Double>() {\n"+
"                    @Override\n"+
"                    public void call(Double f) {\n"+
"                      log(f);\n"+
"                    }\n"+
"                  });\n");
mCodes.put(Constants.Async.deferFuture,
"    Async.deferFuture(new Func0<Future<? extends Observable<Integer>>>() {\n"+
"      @Override\n"+
"      public Future<? extends Observable<Integer>> call() {\n"+
"        return AsyncExecutor.SINGLETON.submit(new Callable<Observable<Integer>>() {\n"+
"          @Override\n"+
"          public Observable<Integer> call() throws Exception {\n"+
"            return Observable.just(1, 2, 3);\n"+
"          }\n"+
"        });\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.distinct,
"    Observable.just(1, 1, 2, 2, 3, 4, 4, 1, 1, 5)\n"+
"              .distinct()\n"+
"              .subscribe(new Action1<Integer>() {\n"+
"                @Override\n"+
"                public void call(Integer integer) {\n"+
"                  log(integer);\n"+
"                }\n"+
"              });\n");
mCodes.put(Constants.Async.start,
"    Async.start(new Func0<Integer>() {\n"+
"      @Override\n"+
"      public Integer call() {\n"+
"        log(\"action run on \" + Thread.currentThread().getName());\n"+
"        return 3;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.first,
"    Observable.range(1, 10).first().subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Transformation.scan,
"    Observable.range(1, 10).scan(new Func2<Integer, Integer, Integer>() {\n"+
"      @Override\n"+
"      public Integer call(Integer integer, Integer integer2) {\n"+
"        return integer + integer2;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Filter.lastOrDefault,
"    Observable.range(1, 10).lastOrDefault(10).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n"+
"    Observable.range(1, 10).lastOrDefault(10, new Func1<Integer, Boolean>() {\n"+
"      @Override\n"+
"      public Boolean call(Integer integer) {\n"+
"        return integer % 2 == 0;\n"+
"      }\n"+
"    }).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Async.fromRunnable,
"    Async.fromRunnable(new Runnable() {\n"+
"      @Override\n"+
"        log(\"Runnable.run on \" + Thread.currentThread().getName());\n"+
"      }\n"+
"    }, 3).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(integer);\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.timestamp,
"    Observable.just(1, 2).timestamp().subscribe(new Action1<Timestamped<Integer>>() {\n"+
"      @Override\n"+
"      public void call(Timestamped<Integer> integerTimestamped) {\n"+
"        log(\"\" + integerTimestamped.getValue() + \" \" + integerTimestamped.getTimestampMillis());\n"+
"      }\n"+
"    });\n");
mCodes.put(Constants.Utility.observeOn,
"    Observable.range(1, 2).observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {\n"+
"      @Override\n"+
"      public void call(Integer integer) {\n"+
"        log(\"\" + integer + \" on \" + Thread.currentThread().getName());\n"+
"      }\n"+
"    });\n");  
  }
  public String get(String key){
    return mCodes.get(key);
  }
}
