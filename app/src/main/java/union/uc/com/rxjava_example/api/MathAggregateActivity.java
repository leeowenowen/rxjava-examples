package union.uc.com.rxjava_example.api;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.MathObservable;
import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class MathAggregateActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.MathAggregate.averageInteger, new Runnable() {
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
    registery.add(Constants.MathAggregate.averageLong, new Runnable() {
      @Override
      public void run() {
        MathObservable.averageLong(Observable.just(2L, 4L, 1L, 5L)).subscribe(new Action1<Long>() {
          @Override
          public void call(Long l) {
            log(l);
          }
        });
      }
    });
    registery.add(Constants.MathAggregate.averageFloat, new Runnable() {
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
    registery.add(Constants.MathAggregate.averageDouble, new Runnable() {
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
    registery.add(Constants.MathAggregate.max, new Runnable() {
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
    registery.add(Constants.MathAggregate.maxBy, new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    registery.add(Constants.MathAggregate.min, new Runnable() {
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
    registery.add(Constants.MathAggregate.minBy, new Runnable() {
      @Override
      public void run() {
        logNotImplemented();
      }
    });
    registery.add(Constants.MathAggregate.sumInteger, new Runnable() {
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
    registery.add(Constants.MathAggregate.sumLong, new Runnable() {
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
    registery.add(Constants.MathAggregate.sumFloat, new Runnable() {
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
    registery.add(Constants.MathAggregate.sumDouble, new Runnable() {
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
    registery.add(Constants.MathAggregate.concat, new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add(Constants.MathAggregate.count, new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add(Constants.MathAggregate.countLong, new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add(Constants.MathAggregate.reduce, new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add(Constants.MathAggregate.collect, new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add(Constants.MathAggregate.toList, new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add(Constants.MathAggregate.toSortedList, new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add(Constants.MathAggregate.toMap, new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
    registery.add(Constants.MathAggregate.toMultiMap, new Runnable() {
      @Override
      public void run() {
        logUseObservable();
      }
    });
  }
}
