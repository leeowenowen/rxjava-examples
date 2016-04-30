package union.uc.com.rxjava_example.plugin;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import union.uc.com.rxjava_example.base.Tuple;
import union.uc.com.rxjava_example.contants.Constants;
import union.uc.com.rxjava_example.ui.Utils;

/**
 * Created by wangli on 4/16/16.
 */
public class MarbleDiagramPlugin implements DisplayPluginManager.Plugin {
    private MarbleDiagram mMarbleDiagram = new MarbleDiagram();

    @Override
    public Tuple.Tuple2<Observable<View>, View> getView(final Context context, String key) {
        Integer[] ids = mMarbleDiagram.get(key);
        if (ids == null) {
            return new Tuple.Tuple2<>(Observable.<View>empty(), null);
        }
        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (Integer id : ids) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(id);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.dipToPixels(context, 200)));
            linearLayout.addView(imageView);
        }
        return new Tuple.Tuple2<>(Observable.just((View) linearLayout), (View) linearLayout);
    }

    //  @Override
    //  public Tuple.Tuple2<Observable<View>, View> getView(final Context context, String key) {
    //    final LinearLayout linearLayout = new LinearLayout(context);
    //    linearLayout.setOrientation(LinearLayout.VERTICAL);
    //    final Reference<LinearLayout> ref = new WeakReference<>(linearLayout);
    //    Observable<View> o = Observable.just(key)
    //                                   //  .observeOn(Schedulers.io())
    //                                   .map(new Func1<String, String[]>() {
    //                                     @Override
    //                                     public String[] call(String s) {
    //                                       if (mKeyToUrl == null) {
    //                                         load();
    //                                       }
    //                                       return mKeyToUrl.get(s);
    //                                     }
    //                                   }).flatMap(new Func1<String[], Observable<View>>() {
    //        @Override
    //        public Observable<View> call(String[] urls) {
    //          final PublishSubject<View> subject = PublishSubject.create();
    //          for (int i = 0; i < urls.length; ++i) {
    //            String url = urls[i];
    //            ImageLoader.getInstance().loadImage(url, new ImageLoadingListener() {
    //              @Override
    //              public void onLoadingStarted(String imageUri, View view) {
    //              }
    //
    //              @Override
    //              public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
    //              }
    //
    //              @Override
    //              public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
    //                LinearLayout ll = ref.get();
    //                if (ll != null) {
    //                  ImageView imageView = new ImageView(context);
    //                  imageView.setImageBitmap(loadedImage);
    //                  ll.addView(imageView);
    //                }
    //                subject.onNext(ll);
    //              }
    //
    //              @Override
    //              public void onLoadingCancelled(String imageUri, View view) {
    //              }
    //            });
    //          }
    //          return subject;
    //        }
    //      });
    //
    //    return new Tuple.Tuple2<>(o, (View) linearLayout);
    //  }
    //
    private Map<String, String[]> mKeyToUrl;

    private void add(String key, String... urls) {
        mKeyToUrl.put(key, urls);
    }

    private void load() {
        mKeyToUrl = new HashMap<>();
        //Utility
        add(Constants.Utility.materialize,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/materialize.png",
                "http://reactivex.io/documentation/operators/images/dematerialize.c.png");
        add(Constants.Utility.timestamp,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/timestamp.png");
        add(Constants.Utility.serialize,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/synchronize.png");
        add(Constants.Utility.cache,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/cache.png");
        add(Constants.Utility.observeOn,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/observeOn.png");
        add(Constants.Utility.subscribeOn,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/subscribeOn.png");
        add(Constants.Utility.doOnEach,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/doOnEach.png");
        add(Constants.Utility.doOnCompleted,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/doOnCompleted.png");
        add(Constants.Utility.doOnError,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/doOnError.png");
        add(Constants.Utility.doOnTerminate,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/doOnTerminate.png");
        add(Constants.Utility.doOnSubscribe,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/doOnSubscribe.png");
        add(Constants.Utility.doOnUnsubscribe,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/doOnUnsubscribe.png");
        add(Constants.Utility.finallyDo,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/finallyDo.png");
        add(Constants.Utility.delay,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/delay.oo.png");
        add(Constants.Utility.delaySubscription,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/delaySubscription.o.png");
        add(Constants.Utility.timeInterval,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/timeInterval.png");
        add(Constants.Utility.using,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/using.png");
        add(Constants.Utility.single,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/single.png");
        add(Constants.Utility.singleOrDefault,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/singleOrDefault.png");

        //Transformation
        add(Constants.Transformation.map,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/map.png");
        add(Constants.Transformation.flatMap,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/flatMap.png");
        add(Constants.Transformation.concatMap,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/concatMap.png");
        add(Constants.Transformation.flatMapIterable,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/mergeMapIterable.png");
        add(Constants.Transformation.switchMap,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/switchMap.png");
        add(Constants.Transformation.scan,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/scan.png");
        add(Constants.Transformation.groupBy,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/groupBy.png");
        add(Constants.Transformation.buffer,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/buffer1.png");
        add(Constants.Transformation.window,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/window1.png");
        add(Constants.Transformation.cast,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/cast.png");

        //Subject
        add(Constants.Subject.async,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.AsyncSubject.png");
        add(Constants.Subject.behavior,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.BehaviorSubject.png");
        add(Constants.Subject.behavior_with_init_value,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/S.BehaviorSubject.png");
        add(Constants.Subject.publish,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/S.PublishSubject.png");
        add(Constants.Subject.replay,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/S.ReplaySubject.png");
        add(Constants.Subject.replay_create_with_time,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/S.ReplaySubject.png");

        //String
        add(Constants.Strings.byLine,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/St.byLine.png");
        add(Constants.Strings.decode,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/St.decode.png");
        add(Constants.Strings.encode,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/St.encode.png");
        add(Constants.Strings.from,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/St.from.png");
        add(Constants.Strings.join,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/St.join.png");
        add(Constants.Strings.split,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/St.split.png");
        add(Constants.Strings.stringConcat,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/St.stringConcat.png");
        //Scheduler
        add(Constants.Scheduler.io, "");
        add(Constants.Scheduler.compute, "");
        add(Constants.Scheduler.immediate, "");
        add(Constants.Scheduler.self_define, "");
        //ReactiveStream
        add(Constants.ReactiveStream.materialize, "");
        //Plugin
        add(Constants.Plugin.start_hook, "");

        //ObservableCreate
        add(Constants.ObservableCreate.just,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/just.png");
        add(Constants.ObservableCreate.from_future,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/from.Future.png");
        add(Constants.ObservableCreate.from_iterable,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/from.png");
        add(Constants.ObservableCreate.repeat,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/repeat.o.png");
        add(Constants.ObservableCreate.repeatWhen,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/repeatWhen.f.png");
        add(Constants.ObservableCreate.create,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/create.png");
        add(Constants.ObservableCreate.defer,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/defer.png");
        add(Constants.ObservableCreate.range,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/range.png");
        add(Constants.ObservableCreate.interval,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/timer.p.png");
        add(Constants.ObservableCreate.timer,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/timer.png");
        add(Constants.ObservableCreate.empty,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/empty.png");
        add(Constants.ObservableCreate.error,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/error.png");
        add(Constants.ObservableCreate.never,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/never.png");

        //MathAggregate
        add(Constants.MathAggregate.averageInteger,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/average.png");
        add(Constants.MathAggregate.averageLong,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/average.png");
        add(Constants.MathAggregate.averageFloat,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/average.png");
        add(Constants.MathAggregate.averageDouble,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/average.png");
        add(Constants.MathAggregate.max,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/max.png");
        add(Constants.MathAggregate.maxBy,
                "http://reactivex.io/documentation/operators/images/maxBy.png");
        add(Constants.MathAggregate.min,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/min.png");
        add(Constants.MathAggregate.minBy,
                "http://reactivex.io/documentation/operators/images/minBy.png");
        add(Constants.MathAggregate.sumInteger,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/sum.f.png");
        add(Constants.MathAggregate.sumLong,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/sum.f.png");
        add(Constants.MathAggregate.sumFloat,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/sum.f.png");
        add(Constants.MathAggregate.sumDouble,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/sum.f.png");
        add(Constants.MathAggregate.concat,
                "http://reactivex.io/documentation/operators/images/concat.png");
        add(Constants.MathAggregate.count, "");
        add(Constants.MathAggregate.countLong, "");
        add(Constants.MathAggregate.reduce, "");
        add(Constants.MathAggregate.collect, "");
        add(Constants.MathAggregate.toList, "");
        add(Constants.MathAggregate.toSortedList, "");
        add(Constants.MathAggregate.toMap, "");
        add(Constants.MathAggregate.toMultiMap, "");

        //Filter
        add(Constants.Filter.filter, "http://reactivex.io/documentation/operators/images/filter.png");
        add(Constants.Filter.takeLast,
                "http://reactivex.io/documentation/operators/images/takeLast.t.png");
        add(Constants.Filter.last, "http://reactivex.io/documentation/operators/images/last.png");
        add(Constants.Filter.lastOrDefault,
                "http://reactivex.io/documentation/operators/images/lastOrDefault.png");
        add(Constants.Filter.takeLastBuffer,
                "http://reactivex.io/documentation/operators/images/takeLastBuffer.png");
        add(Constants.Filter.skip, "http://reactivex.io/documentation/operators/images/skip.png");
        add(Constants.Filter.skipLast,
                "http://reactivex.io/documentation/operators/images/skipLast.png");
        add(Constants.Filter.take, "http://reactivex.io/documentation/operators/images/take.png");
        add(Constants.Filter.first, "http://reactivex.io/documentation/operators/images/first.png");
        add(Constants.Filter.takeFirst,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/takeFirstN.png");
        add(Constants.Filter.firstOrDefault,
                "http://reactivex.io/documentation/operators/images/firstOrDefault.png");
        add(Constants.Filter.elementAt,
                "http://reactivex.io/documentation/operators/images/elementAt.png");
        add(Constants.Filter.elementAtOrDefault,
                "http://reactivex.io/documentation/operators/images/elementAtOrDefault.png");
        add(Constants.Filter.sample, "http://reactivex.io/documentation/operators/images/sample.png");
        add(Constants.Filter.throttleLast,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/throttleLast.png");
        add(Constants.Filter.throttleFirst,
                "http://reactivex.io/documentation/operators/images/throttleFirst.png");
        add(Constants.Filter.throttleWithTimeout,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/throttleWithTimeout.png");
        add(Constants.Filter.debounce,
                "http://reactivex.io/documentation/operators/images/debounce.png");
        add(Constants.Filter.timeout,
                "http://reactivex.io/documentation/operators/images/materialize.png");
        add(Constants.Filter.distinct,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/distinct.key.png");
        add(Constants.Filter.distinctUntilChanged,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/distinctUntilChanged.png");
        add(Constants.Filter.ofType, "http://reactivex.io/documentation/operators/images/ofClass.png");
        add(Constants.Filter.ignoreElements,
                "https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/ignoreElements.png");

        //ErrorHandler
        add(Constants.ErrorHandler.onErrorResumeNext,
                "http://reactivex.io/documentation/operators/images/onErrorResumeNext.png");
        add(Constants.ErrorHandler.onErrorReturn,
                "http://reactivex.io/documentation/operators/images/onErrorReturn.png");
        add(Constants.ErrorHandler.onExceptionResumeNext,
                "http://reactivex.io/documentation/operators/images/onExceptionResumeNextViaObservable.png");
        add(Constants.ErrorHandler.retry,
                "http://reactivex.io/documentation/operators/images/retry.png");
        add(Constants.ErrorHandler.retryWhen,
                "http://reactivex.io/documentation/operators/images/retryWhen.f.png");

        //CustomerOperator
        add(Constants.CustomerOperator.customeOperator, "");
        //ConnectableObservable
        add(Constants.ConnectableObservable.connect, "");
        add(Constants.ConnectableObservable.publish,
                "http://reactivex.io/documentation/operators/images/publishConnect.png");
        add(Constants.ConnectableObservable.replay,
                "http://reactivex.io/documentation/operators/images/replay.c.png");
        add(Constants.ConnectableObservable.refCount,
                "http://reactivex.io/documentation/operators/images/publishRefCount.c.png");

        //Condition
        add(Constants.Condition.amb,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/amb.png");
        add(Constants.Condition.defaultIfEmpty,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/defaultIfEmpty.png");
        add(Constants.Condition.doWhile,
                "http://reactivex.io/documentation/operators/images/doWhile.png");
        add(Constants.Condition.ifThen,
                "http://reactivex.io/documentation/operators/images/ifThen.png");
        add(Constants.Condition.skipUtil,
                "http://reactivex.io/documentation/operators/images/skipUntil.png");
        add(Constants.Condition.skipWhile,
                "http://reactivex.io/documentation/operators/images/skipWhile.png");
        add(Constants.Condition.switchcase,
                "http://reactivex.io/documentation/operators/images/switchCase.png");
        add(Constants.Condition.takeUntil,
                "http://reactivex.io/documentation/operators/images/takeUntil.png");
        add(Constants.Condition.takeWhile,
                "http://reactivex.io/documentation/operators/images/takeWhile.png");
        add(Constants.Condition.takeWhileWithIndex, "");
        add(Constants.Condition.WhileDo,
                "http://reactivex.io/documentation/operators/images/whileDo.png");

        add(Constants.Condition.all,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/all.png");
        add(Constants.Condition.contains,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/contains.png");
        add(Constants.Condition.exists,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/exists.png");
        add(Constants.Condition.isEmpty,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/isEmpty.png");
        add(Constants.Condition.sequenceEqual,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/sequenceEqual.png");

        //Combine
        add(Constants.Combine.startWith,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/startWith.o.png");
        add(Constants.Combine.merge,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/merge.png");
        add(Constants.Combine.mergeDelayError,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/mergeDelayError.png");
        add(Constants.Combine.zip,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/zip.png");
        add(Constants.Combine.and_then_when,
                "http://reactivex.io/documentation/operators/images/and_then_when.C.png");
        add(Constants.Combine.combineLatest,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/combineLatest.png");
        add(Constants.Combine.join,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/join_.png");
        add(Constants.Combine.groupjoin,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/groupJoin.png");
        add(Constants.Combine.switchIfEmpty, "");
        add(Constants.Combine.switchOnNext,
                "https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/switchDo.png");

        //BlockingObservable
        add(Constants.BlockingObservable.forEach,
                "http://reactivex.io/documentation/operators/images/B.forEach.png");
        add(Constants.BlockingObservable.first, "");
        add(Constants.BlockingObservable.firstOrDefault, "");
        add(Constants.BlockingObservable.last,
                "https://github.com/ReactiveX/RxJava/wiki/images/rx-operators/B.last.png");
        add(Constants.BlockingObservable.lastOrDefault,
                "https://github.com/ReactiveX/RxJava/wiki/images/rx-operators/B.lastOrDefault.png");
        add(Constants.BlockingObservable.mostRecent,
                "https://github.com/ReactiveX/RxJava/wiki/images/rx-operators/B.mostRecent.png");
        add(Constants.BlockingObservable.next,
                "https://github.com/ReactiveX/RxJava/wiki/images/rx-operators/B.next.png");
        add(Constants.BlockingObservable.latest, "");
        add(Constants.BlockingObservable.single,
                "https://github.com/ReactiveX/RxJava/wiki/images/rx-operators/B.single.png");
        add(Constants.BlockingObservable.singleOrDefault,
                "https://github.com/ReactiveX/RxJava/wiki/images/rx-operators/B.singleOrDefault.png");
        add(Constants.BlockingObservable.toFuture,
                "https://github.com/ReactiveX/RxJava/wiki/images/rx-operators/B.toFuture.png");
        add(Constants.BlockingObservable.toIterable,
                "https://github.com/ReactiveX/RxJava/wiki/images/rx-operators/B.toIterable.png");
        add(Constants.BlockingObservable.getIterator,
                "https://github.com/ReactiveX/RxJava/wiki/images/rx-operators/B.getIterator.png");
        //Async
        add(Constants.Async.start, "http://reactivex.io/documentation/operators/images/start.png");
        add(Constants.Async.toAsync, "http://reactivex.io/documentation/operators/images/toAsync.png");
        add(Constants.Async.startFuture,
                "http://reactivex.io/documentation/operators/images/startFuture.png");
        add(Constants.Async.deferFuture,
                "http://reactivex.io/documentation/operators/images/deferFuture.png");
        add(Constants.Async.forEachFuture,
                "http://reactivex.io/documentation/operators/images/forEachFuture.png");
        add(Constants.Async.fromAction,
                "http://reactivex.io/documentation/operators/images/fromAction.png");
        add(Constants.Async.fromCallable,
                "http://reactivex.io/documentation/operators/images/fromCallable.png");
        add(Constants.Async.fromRunnable,
                "http://reactivex.io/documentation/operators/images/fromRunnable.png");
        add(Constants.Async.runAsync, "");

    }
}
