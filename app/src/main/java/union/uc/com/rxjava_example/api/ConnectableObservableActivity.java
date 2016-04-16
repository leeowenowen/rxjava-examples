package union.uc.com.rxjava_example.api;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class ConnectableObservableActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.ConnectableObservable.connect, new Runnable() {
      @Override
      public void run() {
        Observable<Integer> o = Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            for (int i = 0; i < 5; ++i) {
              subscriber.onNext(i);
              sleep(500);
            }
            subscriber.onCompleted();
          }
        }).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread());
        o.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("s1:" + integer);
          }
        });
        sleep(1000);
        ConnectableObservable<Integer> co = o.publish();
        co.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("s2:" + integer);
          }
        });

        log("begin connect");
        co.connect();
      }
    });
    registery.add(Constants.ConnectableObservable.publish, new Runnable() {
      @Override
      public void run() {
        log("showed in connect!");
      }
    });
    registery.add(Constants.ConnectableObservable.replay, new Runnable() {
      @Override
      public void run() {
        Observable<Integer> o = Observable.create(new Observable.OnSubscribe<Integer>() {
          @Override
          public void call(Subscriber<? super Integer> subscriber) {
            for (int i = 0; i < 5; ++i) {
              subscriber.onNext(i);
              sleep(500);
            }
            subscriber.onCompleted();
          }
        }).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread());
        o.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("s1:" + integer);
          }
        });
        sleep(1000);
        ConnectableObservable<Integer> co = o.publish();
        co.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("s2:" + integer);
          }
        });

        log("begin connect");
        co.connect();
      }
    });
    registery.add(Constants.ConnectableObservable.refCount, new Runnable() {
      @Override
      public void run() {
        ConnectableObservable<Integer> co =
          Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
              for (int i = 0; i < 5; ++i) {
                subscriber.onNext(i);
                sleep(500);
              }
              subscriber.onCompleted();
            }
          }).subscribeOn(Schedulers.newThread()).publish();

        Subscription s1 = co.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("s1:" + integer);
          }
        });
        Subscription s2 = co.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("s2:" + integer);
          }
        });
        sleep(1000);
        log("begin refcount!");
        Observable<Integer> o = co.refCount();
        o.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("obs:" + integer);
          }
        });

        sleep(1000);
        log("begin connect!");
        co.connect();
        s1.unsubscribe();
        s2.unsubscribe();
        log("both disconnected!");
      }
    });
  }
}
