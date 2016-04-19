package union.uc.com.rxjava_example.api;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class SubjectActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
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
