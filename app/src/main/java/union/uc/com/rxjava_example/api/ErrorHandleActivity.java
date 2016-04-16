package union.uc.com.rxjava_example.api;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class ErrorHandleActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.ErrorHandler.onErrorResumeNext, new Runnable() {
      @Override
      public void run() {
        Observable.just(1, "abc")
                  .cast(Integer.class)
                  .onErrorResumeNext(Observable.just(1, 2))
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                      log(throwable);
                    }
                  });
      }
    });
    registery.add(Constants.ErrorHandler.onErrorReturn, new Runnable() {
      @Override
      public void run() {
        Observable.just(1, "abc")
                  .cast(Integer.class)
                  .onErrorReturn(new Func1<Throwable, Integer>() {
                    @Override
                    public Integer call(Throwable throwable) {
                      return -1;
                    }
                  })
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                      log(throwable);
                    }
                  });
      }
    });
    registery.add(Constants.ErrorHandler.onExceptionResumeNext, new Runnable() {
      @Override
      public void run() {
        Observable.just(1, "abc", "2")
                  .cast(Integer.class)
                  .onExceptionResumeNext(Observable.just(5, 6))
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                      log(throwable);
                    }
                  });
      }
    });
    registery.add(Constants.ErrorHandler.retry, new Runnable() {
      @Override
      public void run() {
        Observable.just(1, "abc", 2).cast(Integer.class).retry(2).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        }, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {
            log(throwable);
          }
        });
      }
    });
    registery.add(Constants.ErrorHandler.retryWhen, new Runnable() {
      @Override
      public void run() {
        Observable.just(1, "abc", "2")
                  .cast(Integer.class)
                  .retryWhen(new Func1<Observable<? extends Throwable>, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Observable<? extends Throwable> observable) {
                      observable.subscribe(new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                          log("error inner:" + throwable.getMessage());
                        }
                      });
                      return Observable.timer(1, TimeUnit.SECONDS);
                    }
                  })
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                      log(throwable);
                    }
                  });
      }
    });
  }
}
