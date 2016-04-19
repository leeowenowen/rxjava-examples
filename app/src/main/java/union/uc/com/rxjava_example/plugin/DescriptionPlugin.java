package union.uc.com.rxjava_example.plugin;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import union.uc.com.rxjava_example.R;
import union.uc.com.rxjava_example.base.Tuple;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/16/16.
 */
public class DescriptionPlugin implements DisplayPluginManager.Plugin {
  @Override
  public Tuple.Tuple2<Observable<View>, View> getView(final Context context, String key) {
    final TextView textView = new TextView(context);
    final Reference<TextView> ref = new WeakReference<>(textView);
    Observable<View> o = Observable.just(key)
                                   //  .observeOn(Schedulers.io())
                                   .map(new Func1<String, Integer>() {
                                     @Override
                                     public Integer call(String s) {
                                       if (mKeyToResource == null) {
                                         load();
                                       }
                                       return mKeyToResource.get(s);
                                     }
                                   }).map(new Func1<Integer, View>() {
        @Override
        public View call(Integer integer) {
          textView.setText(integer);
          return textView;
        }
      });

    return new Tuple.Tuple2<>(o, (View) textView);
  }

  private Map<String, Integer> mKeyToResource;

  private void add(String key, int resId) {
    mKeyToResource.put(key, resId);
  }

  private void load() {
    mKeyToResource = new HashMap<>();
    //Utility
    add(Constants.Utility.materialize, R.string.desc_utility_materialize);
    add(Constants.Utility.timestamp, R.string.desc_utility_timestamp);
    add(Constants.Utility.serialize, R.string.desc_utility_serialize);
    add(Constants.Utility.cache, R.string.desc_utility_cache);
    add(Constants.Utility.observeOn, R.string.desc_utility_observeOn);
    add(Constants.Utility.subscribeOn, R.string.desc_utility_subscribeOn);
    add(Constants.Utility.doOnEach, R.string.desc_utility_doOnEach);
    add(Constants.Utility.doOnCompleted, R.string.desc_utility_doOnCompleted);
    add(Constants.Utility.doOnError, R.string.desc_utility_doOnError);
    add(Constants.Utility.doOnTerminate, R.string.desc_utility_doOnTerminate);
    add(Constants.Utility.doOnSubscribe, R.string.desc_utility_doOnSubscribe);
    add(Constants.Utility.doOnUnsubscribe, R.string.desc_utility_doOnUnsubscribe);
    add(Constants.Utility.finallyDo, R.string.desc_utility_finallyDo);
    add(Constants.Utility.delay, R.string.desc_utility_delay);
    add(Constants.Utility.delaySubscription, R.string.desc_utility_delaySubscription);
    add(Constants.Utility.timeInterval, R.string.desc_utility_timeInterval);
    add(Constants.Utility.using, R.string.desc_utility_using);
    add(Constants.Utility.single, R.string.desc_utility_single);
    add(Constants.Utility.singleOrDefault, R.string.desc_utility_singleOrDefault);

    //Transformation
    add(Constants.Transformation.map, R.string.desc_transformation_map);
    add(Constants.Transformation.flatMap, R.string.desc_transformation_flatMap);
    add(Constants.Transformation.concatMap, R.string.desc_transformation_concatMap);
    add(Constants.Transformation.flatMapIterable, R.string.desc_transformation_flatMapIterable);
    add(Constants.Transformation.switchMap, R.string.desc_transformation_switchMap);
    add(Constants.Transformation.scan, R.string.desc_transformation_scan);
    add(Constants.Transformation.groupBy, R.string.desc_transformation_groupBy);
    add(Constants.Transformation.buffer, R.string.desc_transformation_buffer);
    add(Constants.Transformation.window, R.string.desc_transformation_window);
    add(Constants.Transformation.cast, R.string.desc_transformation_cast);

    //Subject
    add(Constants.Subject.async, R.string.desc_subject_async);
    add(Constants.Subject.behavior, R.string.desc_subject_behavior);
    add(Constants.Subject.behavior_with_init_value, R.string.desc_subject_behavior_with_init_value);
    add(Constants.Subject.publish, R.string.desc_subject_publish);
    add(Constants.Subject.replay, R.string.desc_subject_replay);
    add(Constants.Subject.replay_create_with_time, R.string.desc_subject_replay_create_with_time);

    //String
    add(Constants.Strings.byLine, R.string.desc_string_byline);
    add(Constants.Strings.decode, R.string.desc_string_decode);
    add(Constants.Strings.encode, R.string.desc_string_encode);
    add(Constants.Strings.from, R.string.desc_string_from);
    add(Constants.Strings.join, R.string.desc_string_join);
    add(Constants.Strings.split, R.string.desc_string_split);
    add(Constants.Strings.stringConcat, R.string.desc_string_stringConcat);
    //Scheduler
    add(Constants.Scheduler.io, R.string.desc_scheduler_io);
    add(Constants.Scheduler.compute, R.string.desc_scheduler_compute);
    add(Constants.Scheduler.immediate, R.string.desc_scheduler_immediate);
    add(Constants.Scheduler.self_define, R.string.desc_scheduler_self_define);
    //ReactiveStream
    add(Constants.ReactiveStream.materialize, R.string.desc_reactive_stream_materialize);
    //Plugin
    add(Constants.Plugin.start_hook, R.string.desc_plugin_start_hook);

    //ObservableCreate
    add(Constants.ObservableCreate.just, R.string.desc_observable_create_just);
    add(Constants.ObservableCreate.from_future, R.string.desc_observable_create_from_future);
    add(Constants.ObservableCreate.from_iterable, R.string.desc_observable_create_from_iterable);
    add(Constants.ObservableCreate.repeat, R.string.desc_observable_create_repeat);
    add(Constants.ObservableCreate.repeatWhen, R.string.desc_observable_create_repeatWhen);
    add(Constants.ObservableCreate.create, R.string.desc_observable_create_create);
    add(Constants.ObservableCreate.defer, R.string.desc_observable_create_defer);
    add(Constants.ObservableCreate.range, R.string.desc_observable_create_range);
    add(Constants.ObservableCreate.interval, R.string.desc_observable_create_interval);
    add(Constants.ObservableCreate.timer, R.string.desc_observable_create_timer);
    add(Constants.ObservableCreate.empty, R.string.desc_observable_create_empty);
    add(Constants.ObservableCreate.error, R.string.desc_observable_create_error);
    add(Constants.ObservableCreate.never, R.string.desc_observable_create_never);

    //MathAggregate
    add(Constants.MathAggregate.averageInteger, R.string.desc_math_averageInteger);
    add(Constants.MathAggregate.averageLong, R.string.desc_math_averageLong);
    add(Constants.MathAggregate.averageFloat, R.string.desc_math_averageFloat);
    add(Constants.MathAggregate.averageDouble, R.string.desc_math_averageDouble);
    add(Constants.MathAggregate.max, R.string.desc_math_max);
    add(Constants.MathAggregate.maxBy, R.string.desc_math_maxBy);
    add(Constants.MathAggregate.min, R.string.desc_math_min);
    add(Constants.MathAggregate.minBy, R.string.desc_math_minBy);
    add(Constants.MathAggregate.sumInteger, R.string.desc_math_sumInteger);
    add(Constants.MathAggregate.sumLong, R.string.desc_math_sumLong);
    add(Constants.MathAggregate.sumFloat, R.string.desc_math_sumFloat);
    add(Constants.MathAggregate.sumDouble, R.string.desc_math_sumDouble);
    add(Constants.MathAggregate.concat, R.string.desc_math_concat);
    add(Constants.MathAggregate.count, R.string.desc_math_count);
    add(Constants.MathAggregate.countLong, R.string.desc_math_countLong);
    add(Constants.MathAggregate.reduce, R.string.desc_math_reduce);
    add(Constants.MathAggregate.collect, R.string.desc_math_collect);
    add(Constants.MathAggregate.toList, R.string.desc_math_toList);
    add(Constants.MathAggregate.toSortedList, R.string.desc_math_toSortedList);
    add(Constants.MathAggregate.toMap, R.string.desc_math_toMap);
    add(Constants.MathAggregate.toMultiMap, R.string.desc_math_toMultiMap);

    //Filter
    add(Constants.Filter.filter, R.string.desc_filter_filter);
    add(Constants.Filter.takeLast, R.string.desc_filter_takeLast);
    add(Constants.Filter.last, R.string.desc_filter_last);
    add(Constants.Filter.lastOrDefault, R.string.desc_filter_lastOrDefault);
    add(Constants.Filter.takeLastBuffer, R.string.desc_filter_takeLastBuffer);
    add(Constants.Filter.skip, R.string.desc_filter_skip);
    add(Constants.Filter.skipLast, R.string.desc_filter_skipLast);
    add(Constants.Filter.take, R.string.desc_filter_take);
    add(Constants.Filter.first, R.string.desc_filter_first);
    add(Constants.Filter.takeFirst, R.string.desc_filter_takeFirst);
    add(Constants.Filter.firstOrDefault, R.string.desc_filter_firstOrDefault);
    add(Constants.Filter.elementAt, R.string.desc_filter_elementAt);
    add(Constants.Filter.elementAtOrDefault, R.string.desc_filter_elementAtOrDefault);
    add(Constants.Filter.sample, R.string.desc_filter_sample);
    add(Constants.Filter.throttleLast, R.string.desc_filter_throttleLast);
    add(Constants.Filter.throttleFirst, R.string.desc_filter_throttleFirst);
    add(Constants.Filter.throttleWithTimeout, R.string.desc_filter_throttleWithTimeout);
    add(Constants.Filter.debounce, R.string.desc_filter_debounce);
    add(Constants.Filter.timeout, R.string.desc_filter_timeout);
    add(Constants.Filter.distinct, R.string.desc_filter_distinct);
    add(Constants.Filter.distinctUntilChanged, R.string.desc_filter_distinctUntilChanged);
    add(Constants.Filter.ofType, R.string.desc_filter_ofType);
    add(Constants.Filter.ignoreElements, R.string.desc_filter_ignoreElements);

    //ErrorHandler
    add(Constants.ErrorHandler.onErrorResumeNext, R.string.desc_error_handler_onErrorResumeNext);
    add(Constants.ErrorHandler.onErrorReturn, R.string.desc_error_handler_onErrorReturn);
    add(Constants.ErrorHandler.onExceptionResumeNext,
        R.string.desc_error_handler_onExceptionResumeNext);
    add(Constants.ErrorHandler.retry, R.string.desc_error_handler_retry);
    add(Constants.ErrorHandler.retryWhen, R.string.desc_error_handler_retryWhen);

    //CustomerOperator
    add(Constants.CustomerOperator.customeOperator,
        R.string.desc_custormer_operator_customeOperator);
    //ConnectableObservable
    add(Constants.ConnectableObservable.connect, R.string.desc_connectable_obervable_connect);
    add(Constants.ConnectableObservable.publish, R.string.desc_connectable_obervable_publish);
    add(Constants.ConnectableObservable.replay, R.string.desc_connectable_obervable_replay);
    add(Constants.ConnectableObservable.refCount, R.string.desc_connectable_obervable_refCount);

    //Condition
    add(Constants.Condition.amb, R.string.desc_condition_amb);
    add(Constants.Condition.defaultIfEmpty, R.string.desc_condition_defaultIfEmpty);
    add(Constants.Condition.doWhile, R.string.desc_condition_doWhile);
    add(Constants.Condition.ifThen, R.string.desc_condition_ifThen);
    add(Constants.Condition.skipUtil, R.string.desc_condition_skipUtil);
    add(Constants.Condition.skipWhile, R.string.desc_condition_skipWhile);
    add(Constants.Condition.switchcase, R.string.desc_condition_switchcase);
    add(Constants.Condition.takeUntil, R.string.desc_condition_takeUntil);
    add(Constants.Condition.takeWhile, R.string.desc_condition_takeWhile);
    add(Constants.Condition.takeWhileWithIndex, R.string.desc_condition_takeWhileWithIndex);
    add(Constants.Condition.WhileDo, R.string.desc_condition_WhileDo);

    add(Constants.Condition.all, R.string.desc_condition_all);
    add(Constants.Condition.contains, R.string.desc_condition_contains);
    add(Constants.Condition.exists, R.string.desc_condition_exists);
    add(Constants.Condition.isEmpty, R.string.desc_condition_isEmpty);
    add(Constants.Condition.sequenceEqual, R.string.desc_condition_sequenceEqual);

    //Combine
    add(Constants.Combine.startWith, R.string.desc_combine_startWith);
    add(Constants.Combine.merge, R.string.desc_combine_merge);
    add(Constants.Combine.mergeDelayError, R.string.desc_combine_mergeDelayError);
    add(Constants.Combine.zip, R.string.desc_combine_zip);
    add(Constants.Combine.and_then_when, R.string.desc_combine_and_then_when);
    add(Constants.Combine.combineLatest, R.string.desc_combine_combineLatest);
    add(Constants.Combine.join, R.string.desc_combine_join);
    add(Constants.Combine.groupjoin, R.string.desc_combine_groupjoin);
    add(Constants.Combine.switchIfEmpty, R.string.desc_combine_switchIfEmpty);
    add(Constants.Combine.switchOnNext, R.string.desc_combine_switchOnNext);

    //BlockingObservable
    add(Constants.BlockingObservable.forEach, R.string.desc_blocking_observable_forEach);
    add(Constants.BlockingObservable.first, R.string.desc_blocking_observable_first);
    add(Constants.BlockingObservable.firstOrDefault,
        R.string.desc_blocking_observable_firstOrDefault);
    add(Constants.BlockingObservable.last, R.string.desc_blocking_observable_last);
    add(Constants.BlockingObservable.lastOrDefault,
        R.string.desc_blocking_observable_lastOrDefault);
    add(Constants.BlockingObservable.mostRecent, R.string.desc_blocking_observable_mostRecent);
    add(Constants.BlockingObservable.next, R.string.desc_blocking_observable_next);
    add(Constants.BlockingObservable.latest, R.string.desc_blocking_observable_latest);
    add(Constants.BlockingObservable.single, R.string.desc_blocking_observable_single);
    add(Constants.BlockingObservable.singleOrDefault,
        R.string.desc_blocking_observable_singleOrDefault);
    add(Constants.BlockingObservable.toFuture, R.string.desc_blocking_observable_toFuture);
    add(Constants.BlockingObservable.toIterable, R.string.desc_blocking_observable_toIterable);
    add(Constants.BlockingObservable.getIterator, R.string.desc_blocking_observable_getIterator);
    //Async
    add(Constants.Async.start, R.string.desc_async_start);
    add(Constants.Async.toAsync, R.string.desc_async_toAsync);
    add(Constants.Async.startFuture, R.string.desc_async_startFuture);
    add(Constants.Async.deferFuture, R.string.desc_async_deferFuture);
    add(Constants.Async.forEachFuture, R.string.desc_async_forEachFuture);
    add(Constants.Async.fromAction, R.string.desc_async_fromAction);
    add(Constants.Async.fromCallable, R.string.desc_async_fromCallable);
    add(Constants.Async.fromRunnable, R.string.desc_async_fromRunnable);
    add(Constants.Async.runAsync, R.string.desc_async_runAsync);

  }
}
