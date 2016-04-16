package union.uc.com.rxjava_example.api;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.operators.OperatorMap;
import union.uc.com.rxjava_example.base.APIBaseActivity;

/**
 * Created by wangli on 4/12/16.
 */
public class CustomeOperatorActivity extends APIBaseActivity {

  private class OperatorMapInterceptor<T, R> implements Observable.Operator<R, T> {
    private OperatorMap<T, R> mOperator;

    public OperatorMapInterceptor(Func1<? super T, ? extends R> transformer) {
      mOperator = new OperatorMap(transformer);
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
      final Subscriber<? super T> s = mOperator.call(subscriber);
      return new Subscriber<T>() {
        @Override
        public void onCompleted() {
          log("onComplete");
          s.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
          log("onError:" + e);
          s.onError(e);
        }

        @Override
        public void onNext(T t) {
          log("onNext:" + t);
          s.onNext(t);
        }
      };
    }
  }

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add("customeOperator", new Runnable() {
      @Override
      public void run() {
        Observable.range(1, 10)
                  .lift(new OperatorMapInterceptor<Integer, Integer>(new Func1<Integer, Integer>() {
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
