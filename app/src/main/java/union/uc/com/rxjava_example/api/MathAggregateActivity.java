package union.uc.com.rxjava_example.api;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.MathObservable;
import union.uc.com.rxjava_example.base.APIBaseActivity;

/**
 * Created by wangli on 4/12/16.
 */
public class MathAggregateActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add("averageInteger", new Runnable() {
      @Override
      public void run() {
        MathObservable.averageInteger(Observable.just(2, 4, 1, 5))
                      .subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer integer) {
                          log(integer);
                        }
                      });
      }
    });
    registery.add("averageLong", new Runnable() {
      @Override
      public void run() {
        MathObservable.averageInteger(Observable.just(2, 4, 1, 5))
                      .subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer integer) {
                          log(integer);
                        }
                      });
      }
    });
    registery.add("averageFloat", new Runnable() {
      @Override
      public void run() {
        MathObservable.averageFloat(Observable.just(1.0f, 2.0f, 3.0f, 4.0f))
                      .subscribe(new Action1<Float>() {
                        @Override
                        public void call(Float f) {
                          log(f);
                        }
                      });
      }
    });
    registery.add("averageDouble", new Runnable() {
      @Override
      public void run() {
        MathObservable.averageDouble(Observable.just(1.0, 2.0, 3.0, 4.0))
                      .subscribe(new Action1<Double>() {
                        @Override
                        public void call(Double f) {
                          log(f);
                        }
                      });
      }
    });
    registery.add("max", new Runnable() {
      @Override
      public void run() {
        MathObservable.max(Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("maxBy", new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    registery.add("min", new Runnable() {
      @Override
      public void run() {
        MathObservable.min(Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("minBy", new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    registery.add("sumInteger", new Runnable() {
      @Override
      public void run() {
        MathObservable.sumInteger(Observable.just(1, 2, 3)).subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer integer) {
            log(integer);
          }
        });
      }
    });
    registery.add("sumLong", new Runnable() {
      @Override
      public void run() {
        MathObservable.sumLong(Observable.just(1L, 2L, 3L)).subscribe(new Action1<Long>() {
          @Override
          public void call(Long l) {
            log(l);
          }
        });
      }
    });
    registery.add("sumFloat", new Runnable() {
      @Override
      public void run() {
        MathObservable.sumFloat(Observable.just(1.0f, 2.0f, 3.0f, 4.0f))
                      .subscribe(new Action1<Float>() {
                        @Override
                        public void call(Float f) {
                          log(f);
                        }
                      });
      }
    });
    registery.add("sumDouble", new Runnable() {
      @Override
      public void run() {
        MathObservable.sumDouble(Observable.just(1.0, 2.0, 3.0, 4.0))
                      .subscribe(new Action1<Double>() {
                        @Override
                        public void call(Double f) {
                          log(f);
                        }
                      });
      }
    });
    registery.add("concat", new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add("count", new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add("countLong", new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add("reduce", new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add("collect", new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add("toList", new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add("toSortedList", new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add("toMap", new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add("toMultiMap", new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
  }
}
