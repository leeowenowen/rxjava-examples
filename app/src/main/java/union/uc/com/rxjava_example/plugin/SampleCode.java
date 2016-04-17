
package union.uc.com.rxjava_example.plugin;

import java.util.HashMap;
import java.util.Map;
import union.uc.com.rxjava_example.contants.Constants;

public class SampleCode{
  private Map<String, String> mCodes = new HashMap<>();
  public SampleCode(){

mCodes.put(Constants.ObservableCreate.from_iterable,
"    Observable.from(Arrays.asList(new String[] {\"s1\", \"s2\", \"s3\"}))"+
"              .subscribe(new Action1<String>() {"+
"                @Override"+
"                public void call(String s) {"+
"                  log(s);"+
"                }"+
"  }"+
"//[repeat]create an Observable that emits a particular item or sequence of items repeatedly");
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
mCodes.put(Constants.ObservableCreate.repeat,
"    log(\"RxJava not implemented!\");"+
"  }"+
"}"+
""+
""+
"create an Observable that emits a particular item or sequence of items repeatedly, depending on the emissions of a second Observable");
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
mCodes.put(Constants.ObservableCreate.repeatWhen,
"    log(\"RxJava not implemented!\");"+
"  }"+
"}"+
""+
""+
"ate an Observable from scratch by means of a function");
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
  }
  public String get(String key){
    return mCodes.get(key);
  }
}
