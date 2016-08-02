package union.uc.com.rxjava_example.api;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func1;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class CustomeOperatorActivity extends APIBaseActivity {

  private class MyMapOperator<T, R> implements Observable.Operator<R, T> {
    private Func1<? super T, ? extends R> mTransformer;
    public MyMapOperator(Func1<? super T, ? extends R> transformer) {
      mTransformer = transformer;
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
      return new Subscriber<T>() {
        @Override
        public void onCompleted() {
          log("onComplete");
          subscriber.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
          log("onError:" + e);
          subscriber.onError(e);
        }

        @Override
        public void onNext(T t) {
          log("onNext:" + t);
          try {
            R r = mTransformer.call(t);
            subscriber.onNext(r);
          } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            unsubscribe();
            onError(OnErrorThrowable.addValueAsLastCause(ex, t));
          }
        }
      };
    }
  }

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.CustomerOperator.customeOperator, new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10)
                  .lift(new MyMapOperator<Integer, Integer>(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                      return integer * 2;
                    }
                  }))
                  .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                      log(integer);
                    }
                  });
      }
    });
  }
}
