package union.uc.com.rxjava_example.api;

import android.os.Bundle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class PluginActivity extends APIBaseActivity {
  ScheduledExecutorService SERVICE = Executors.newScheduledThreadPool(3);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    log("from now on, all operation is hooked!");
    registerHook();
  }

  private void registerHook() {
    RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
      @Override
      public void handleError(Throwable e) {
        super.handleError(e);
        log("hook handleError:" + e);
      }

      @Override
      protected String render(Object item) throws InterruptedException {
        log("hook render:" + item);
        return super.render(item);
      }
    });
    RxJavaPlugins.getInstance()
                 .registerObservableExecutionHook(new RxJavaObservableExecutionHook() {
                   @Override
                   public <T> Observable.OnSubscribe<T> onCreate(Observable.OnSubscribe<T> f) {
                     log("hook Observable::onCreate");
                     return super.onCreate(f);
                   }

                   @Override
                   public <T> Observable.OnSubscribe<T> onSubscribeStart(Observable<? extends T> observableInstance,
                                                                         Observable.OnSubscribe<T> onSubscribe) {
                     log("hook onSubscribeStart");
                     return super.onSubscribeStart(observableInstance, onSubscribe);
                   }

                   @Override
                   public <T> Subscription onSubscribeReturn(Subscription subscription) {
                     log("hook onSubscribeReturn");
                     return super.onSubscribeReturn(subscription);
                   }

                   @Override
                   public <T> Throwable onSubscribeError(Throwable e) {
                     log("hook onSubscribeError");
                     return super.onSubscribeError(e);
                   }

                   @Override
                   public <T, R> Observable.Operator<? extends R, ? super T> onLift(Observable.Operator<? extends R, ? super T> lift) {
                     log("hook onLift");
                     return super.onLift(lift);
                   }
                 });
    RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook() {
      @Override
      public Scheduler getComputationScheduler() {
        log("hook getComputationScheduler, You can replace the default one");
        return super.getComputationScheduler();
      }

      @Override
      public Scheduler getIOScheduler() {
        log("hook getIOScheduler, You can replace the default one");
        return super.getIOScheduler();
      }

      @Override
      public Scheduler getNewThreadScheduler() {
        log("hook getNewThreadScheduler, You can replace the default one");
        return super.getNewThreadScheduler();
      }

      @Override
      public Action0 onSchedule(Action0 action) {
        log("hook onSchedule, You can replace the default one");
        return super.onSchedule(action);
      }
    });
  }

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.Plugin.start_hook, new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10).observeOn(Schedulers.io()).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });

  }
}
