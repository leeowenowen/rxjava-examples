package union.uc.com.rxjava_example.api;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.base.UIThreadExecutor;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class SchedulerActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.Scheduler.io, new Runnable() {
      @Override
      public void run() {
        Observable.just("a", "b").observeOn(Schedulers.io()).subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            log(s + " on " + Thread.currentThread().getName());
          }
        });
      }
    });
    registery.add(Constants.Scheduler.compute, new Runnable() {
      @Override
      public void run() {
        Observable.just("a", "b")
                  .observeOn(Schedulers.computation())
                  .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                      log(s + " on " + Thread.currentThread().getName());
                    }
                  });
      }
    });
    registery.add(Constants.Scheduler.new_thread, new Runnable() {
      @Override
      public void run() {
        Observable.just("a", "b")
                  .observeOn(Schedulers.newThread())
                  .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                      log(s + " on " + Thread.currentThread().getName());
                    }
                  });
      }
    });
    registery.add(Constants.Scheduler.trampoline, new Runnable() {
      @Override
      public void run() {
        Observable.just("a", "b")
                  .observeOn(Schedulers.trampoline())
                  .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                      log(s + " on " + Thread.currentThread().getName());
                    }
                  });
        log("i'm on thread " + Thread.currentThread().getName());
      }
    });
    registery.add(Constants.Scheduler.immediate, new Runnable() {
      @Override
      public void run() {
        Observable.just("a", "b")
                  .observeOn(Schedulers.immediate())
                  .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                      log(s + " on " + Thread.currentThread().getName());
                    }
                  });
        log("i'm on thread " + Thread.currentThread().getName());
      }
    });
    registery.add(Constants.Scheduler.self_define, new Runnable() {
      @Override
      public void run() {
        Observable.just("a", "b")
                  .observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))
                  .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                      log(s + " on " + Thread.currentThread().getName());
                    }
                  });
      }
    });

  }
}
