package union.uc.com.rxjava_example.contants;

/**
 * Created by wangli on 4/16/16.
 */
public class Constants {
  public static final String async = "async";
  public static final String blocking_observable = "blocking_observable";
  public static final String combine = "combine";
  public static final String condition = "condition";
  public static final String connectable_observable = "connectable_observable";
  public static final String customer_operator = "customer_operator";
  public static final String error_handler = "error_handler";
  public static final String filter = "filter";
  public static final String math_aggregate = "math_aggregate";
  public static final String observable_create = "observable_create";
  public static final String plugin = "plugin";
  public static final String scheduler = "scheduler";
  public static final String string = "string";
  public static final String subject = "subject";
  public static final String transformation = "transformation";
  public static final String utility = "utility";

  public static final class Utility {
    public static final String materialize = "materialize";
    public static final String timestamp = "timestamp";
    public static final String serialize = "serialize";
    public static final String cache = "cache";
    public static final String observeOn = "observeOn";
    public static final String subscribeOn = "subscribeOn";
    public static final String doOnEach = "doOnEach";
    public static final String doOnCompleted = "doOnCompleted";
    public static final String doOnError = "doOnError";
    public static final String doOnTerminate = "doOnTerminate";
    public static final String doOnSubscribe = "doOnSubscribe";

    public static final String doOnUnsubscribe = "doOnUnsubscribe";
    public static final String finallyDo = "finallyDo";
    public static final String delay = "delay";
    public static final String delaySubscription = "delaySubscription";
    public static final String timeInterval = "timeInterval";
    public static final String using = "using";
    public static final String single = "single";
    public static final String singleOrDefault = "singleOrDefault";
  }

  public static final class Transformation {
    public static final String map = "map";
    public static final String flatMap = "flatMap";
    public static final String concatMap = "concatMap";
    public static final String flatMapIterable = "flatMapIterable";
    public static final String switchMap = "switchMap";
    public static final String scan = "scan";
    public static final String groupBy = "groupBy";
    public static final String buffer = "buffer";
    public static final String window = "window";
    public static final String cast = "cast";
  }

  public static final class Subject {
    public static final String async = "async";
    public static final String behavior = "behavior";
    public static final String behavior_with_init_value = "behavior_with_init_value";
    public static final String publish = "publish";
    public static final String replay = "replay";
    public static final String replay_create_with_time = "replay_create_with_time";
  }

  public static final class Strings {
    public static final String byLine = "byLine";
    public static final String decode = "decode";
    public static final String encode = "encode";
    public static final String from = "from";
    public static final String join = "join";
    public static final String split = "split";
    public static final String stringConcat = "stringConcat";
  }

  public static final class Scheduler {
    public static final String io = "io";
    public static final String compute = "compute";
    public static final String immediate = "immediate";
    public static final String new_thread = "new_thread";
    public static final String trampoline = "trampoline";
    public static final String self_define = "self_define";
  }

  public static final class ReactiveStream {
    public static final String materialize = "test";
  }

  public static final class Plugin {
    public static final String start_hook = "start_hook";
  }

  public static final class ObservableCreate {
    public static final String just = "just";
    public static final String from_future = "from_future";
    public static final String from_iterable = "from_iterable";
    public static final String repeat = "repeat";
    public static final String repeatWhen = "repeatWhen";
    public static final String create = "create";
    public static final String defer = "defer";
    public static final String range = "range";
    public static final String interval = "interval";
    public static final String timer = "timer";
    public static final String empty = "empty";
    public static final String error = "error";
    public static final String never = "never";
  }

  public static final class MathAggregate {
    public static final String averageInteger = "averageInteger";
    public static final String averageLong = "averageLong";
    public static final String averageFloat = "averageFloat";
    public static final String averageDouble = "averageDouble";
    public static final String max = "max";
    public static final String maxBy = "maxBy";
    public static final String min = "min";
    public static final String minBy = "minBy";
    public static final String sumInteger = "sumInteger";
    public static final String sumLong = "sumLong";
    public static final String sumFloat = "sumFloat";
    public static final String sumDouble = "sumDouble";
    public static final String concat = "concat";
    public static final String count = "count";
    public static final String countLong = "countLong";
    public static final String reduce = "reduce";
    public static final String collect = "collect";
    public static final String toList = "toList";
    public static final String toSortedList = "toSortedList";
    public static final String toMap = "toMap";
    public static final String toMultiMap = "toMultiMap";
  }

  public static final class Filter {
    public static final String filter = "filter";
    public static final String takeLast = "takeLast";
    public static final String last = "last";
    public static final String lastOrDefault = "lastOrDefault";
    public static final String takeLastBuffer = "takeLastBuffer";
    public static final String skip = "skip";
    public static final String skipLast = "skipLast";
    public static final String take = "take";
    public static final String first = "first";
    public static final String takeFirst = "takeFirst";
    public static final String firstOrDefault = "firstOrDefault";
    public static final String elementAt = "elementAt";
    public static final String elementAtOrDefault = "elementAtOrDefault";
    public static final String sample = "sample";
    public static final String throttleLast = "throttleLast";
    public static final String throttleFirst = "throttleFirst";
    public static final String throttleWithTimeout = "throttleWithTimeout";
    public static final String debounce = "debounce";
    public static final String timeout = "timeout";
    public static final String distinct = "distinct";
    public static final String distinctUntilChanged = "distinctUntilChanged";
    public static final String ofType = "ofType";
    public static final String ignoreElements = "ignoreElements";
  }

  public static final class ErrorHandler {
    public static final String onErrorResumeNext = "onErrorResumeNext";
    public static final String onErrorReturn = "onErrorReturn";
    public static final String onExceptionResumeNext = "onExceptionResumeNext";
    public static final String retry = "retry";
    public static final String retryWhen = "retryWhen";
  }

  public static final class CustomerOperator {
    public static final String customeOperator = "customeOperator";
  }

  public static final class ConnectableObservable {
    public static final String connect = "connect";
    public static final String publish = "publish";
    public static final String replay = "replay";
    public static final String refCount = "refCount";
  }

  public static final class Condition {
    public static final String amb = "amb";
    public static final String defaultIfEmpty = "defaultIfEmpty";
    public static final String doWhile = "doWhile";
    public static final String ifThen = "ifThen";
    public static final String skipUtil = "skipUtil";
    public static final String skipWhile = "skipWhile";
    public static final String switchcase = "switchcase";
    public static final String takeUntil = "takeUntil";
    public static final String takeWhile = "takeWhile";
    public static final String takeWhileWithIndex = "takeWhileWithIndex";
    public static final String WhileDo = "WhileDo";

    public static final String all = "all";
    public static final String contains = "contains";
    public static final String exists = "exists";
    public static final String isEmpty = "isEmpty";
    public static final String sequenceEqual = "sequenceEqual";
  }

  public static final class Combine {
    public static final String startWith = "startWith";
    public static final String merge = "merge";
    public static final String mergeDelayError = "mergeDelayError";
    public static final String zip = "zip";
    public static final String and_then_when = "and_then_when";
    public static final String combineLatest = "combineLatest";
    public static final String join = "join";
    public static final String groupjoin = "groupjoin";
    public static final String switchIfEmpty = "switchIfEmpty";
    public static final String switchOnNext = "switchOnNext";
  }

  public static final class BlockingObservable {
    public static final String forEach = "forEach";
    public static final String first = "first";
    public static final String firstOrDefault = "firstOrDefault";
    public static final String last = "last";
    public static final String lastOrDefault = "lastOrDefault";
    public static final String mostRecent = "mostRecent";
    public static final String next = "next";
    public static final String latest = "latest";
    public static final String single = "single";
    public static final String singleOrDefault = "singleOrDefault";
    public static final String toFuture = "toFuture";
    public static final String toIterable = "toIterable";
    public static final String getIterator = "getIterator";
  }

  public static final class Async {
    public static final String start = "start";
    public static final String toAsync = "toAsync";
    public static final String startFuture = "startFuture";
    public static final String deferFuture = "deferFuture";
    public static final String forEachFuture = "forEachFuture";
    public static final String fromAction = "fromAction";
    public static final String fromCallable = "fromCallable";
    public static final String fromRunnable = "fromRunnable";
    public static final String runAsync = "runAsync";
  }

}
