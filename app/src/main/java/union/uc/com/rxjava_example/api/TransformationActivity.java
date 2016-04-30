package union.uc.com.rxjava_example.api;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class TransformationActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.Transformation.map, new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).map(new Func1<Integer, Integer>() {
          @Override
          public Integer call(Integer integer) {
            return integer * 2;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add(Constants.Transformation.flatMap, new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2).flatMap(new Func1<Integer, Observable<Integer>>() {
          @Override
          public Observable<Integer> call(final Integer integer) {
            return Observable.create(new Observable.OnSubscribe<Integer>() {
              @Override
              public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(integer);
                subscriber.onCompleted();
              }
            }).subscribeOn(Schedulers.newThread());
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add(Constants.Transformation.concatMap, new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2).concatMap(new Func1<Integer, Observable<Integer>>() {
          @Override
          public Observable<Integer> call(Integer integer) {
            return Observable.just(integer);
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add(Constants.Transformation.flatMapIterable, new Runnable() {
      @Override
      public void run() {
        Observable.just(1, 2).flatMapIterable(new Func1<Integer, Iterable<String>>() {
          @Override
          public Iterable<String> call(Integer integer) {
            return Arrays.asList(new String[] {"" + integer, "" + integer * 3});
          }
        }).subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            log(s);
          }
        });
      }
    });
    registery.add(Constants.Transformation.switchMap, new Runnable() {
      @Override
      public void run() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            for (int i = 0; i < 3; ++i) {
              subscriber.onNext(i);
              sleep(500);
            }
            subscriber.onCompleted();
          }
        }).subscribeOn(Schedulers.newThread()).switchMap(new Func1<Integer, Observable<Integer>>() {
          @Override
          public Observable<Integer> call(final Integer integer) {
            return Observable.create(new Observable.OnSubscribe<Integer>() {
              @Override
              public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(integer);
                sleep(500);
                subscriber.onNext(integer);
                subscriber.onCompleted();
              }
            }).subscribeOn(Schedulers.newThread());
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer s) {
            log(s);
          }
        });
      }
    });
    registery.add(Constants.Transformation.scan, new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).scan(new Func2<Integer, Integer, Integer>() {
          @Override
          public Integer call(Integer integer, Integer integer2) {
            return integer + integer2;
          }
        }).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add(Constants.Transformation.groupBy, new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).groupBy(new Func1<Integer, String>() {
          @Override
          public String call(Integer integer) {
            return (integer % 2 == 0) ? "even" : "odd";
          }
        }).subscribe(new Action1<GroupedObservable<String, Integer>>() {
          @Override
          public void call(final GroupedObservable<String, Integer> stringIntegerGroupedObservable) {
            log("group ok:" + stringIntegerGroupedObservable.getKey());
            stringIntegerGroupedObservable.subscribe(new Action1<Integer>() {
              @Override
              public void call(Integer integer) {
                log(integer + " of group " + stringIntegerGroupedObservable.getKey());
              }
            });
          }
        });
      }
    });
    registery.add(Constants.Transformation.buffer, new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).buffer(3).subscribe(new Action1<List<Integer>>() {
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
    registery.add(Constants.Transformation.window, new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).window(3).subscribe(new Action1<Observable<Integer>>() {
          @Override
          public void call(final Observable<Integer> integerObservable) {
            integerObservable.subscribe(new Action1<Integer>() {
              @Override
              public void call(Integer integer) {
                log(integer + " of window " + integerObservable);
              }
            });
          }
        });
      }
    });
    registery.add(Constants.Transformation.cast, new Runnable() {
      @Override
      public void run() {
        Observable.<Object>just(1, 2, 3).
                                          cast(Integer.class).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
  }
}
