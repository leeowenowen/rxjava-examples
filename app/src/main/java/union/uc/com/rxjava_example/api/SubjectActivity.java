package union.uc.com.rxjava_example.api;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import union.uc.com.rxjava_example.base.APIBaseActivity;

/**
 * Created by wangli on 4/12/16.
 */
public class SubjectActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    /* AsyncSubject caches the last value. The difference now is that it doesn't emit anything
    until the sequence completes. Its use is to emit a single value and immediately complete.
     */
    registery.add(Constants.Subject.async, new Runnable() {
      @Override
      public void run() {
        AsyncSubject<Integer> s = AsyncSubject.create();
        s.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("" + integer);
          }
        });
        s.onNext(0);
        s.onNext(1);
        s.onNext(2);
        s.onCompleted();
      }
    });
    /*BehaviorSubject only remembers the last value. It is similar to a ReplaySubject with a
    buffer of size 1. An initial value can be provided on creation, therefore guaranteeing that
     a value always will be available immediately on subscription.
    */
    registery.add(Constants.Subject.behavior, new Runnable() {
      @Override
      public void run() {
        BehaviorSubject<Integer> s = BehaviorSubject.create();
        s.onNext(1);
        s.onNext(2);
        s.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("" + integer);
          }
        });
        s.onNext(3);
      }
    });
    registery.add(Constants.Subject.behavior_with_init_value, new Runnable() {
      @Override
      public void run() {
        BehaviorSubject<Integer> s = BehaviorSubject.create(0);
        s.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("" + integer);
          }
        });
        s.onNext(1);
      }
    });
    /*
    PublishSubject is the most straight-forward kind of subject.
     When a value is pushed into a PublishSubject, the subject pushes
     it to every subscriber that is subscribed to it at that moment.
     */
    registery.add(Constants.Subject.publish, new Runnable() {
      @Override
      public void run() {
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(1);
        subject.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("" + integer);
          }
        });
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
      }
    });
    /*ReplaySubject has the special feature of caching all the values pushed to it. When a new
      subscription is made, the event sequence is replayed from the start for the new subscriber.
      After catching up, every subscriber receives new events as they come.
      */
    registery.add(Constants.Subject.replay, new Runnable() {
      @Override
      public void run() {
        ReplaySubject<Integer> subject = ReplaySubject.create();
        subject.onNext(1);
        subject.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("Subscriber1:" + integer);
          }
        });
        subject.onNext(2);
        subject.onNext(3);
        subject.subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log("Subscriber2:" + integer);
          }
        });
        subject.onNext(4);
      }
    });
    registery.add(Constants.Subject.replay_create_with_time, new Runnable() {
      @Override
      public void run() {
        try {
          ReplaySubject<Integer> subject =
            ReplaySubject.createWithTime(150, TimeUnit.MILLISECONDS, Schedulers.immediate());
          subject.onNext(1);
          Thread.sleep(100);
          subject.onNext(2);
          Thread.sleep(100);
          subject.onNext(3);
          subject.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
              log("" + integer);
            }
          });
          subject.onNext(4);
        } catch (InterruptedException e) {
          log("error:" + e.getMessage());
        }
      }
    });
  }
}
