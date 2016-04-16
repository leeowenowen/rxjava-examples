### 深入剖析RxJava

***

## 背景
在这里,你需要了解两个背景:
1. RxJava的目标是提供强大且易用的异步API.
2. RxJava是基于观察者模式并在此之上增加了很多扩展而来. 所以你会看到这么多跟观察/订阅以及通知/事件相关的名词.

## 入门实例

### 一个小例子
我们先从一个简单的实例开始,先了解RxJava的使用:
~~~
    Observable
    .range(1, 3)
    .map(new Func1<Integer, String>() {
      @Override
      public String call(Integer integer) {
        return (integer % 2 == 0) ? "even" : "odd";
      }
    }).subscribe(new Action1<String>() {
      @Override
      public void call(String string) {
        System.out.println(string);
      }
    });
~~~

以上代码的功能是对于区间[1-3]中的每个数据(即[1,2,3]),判断每个整数是否为偶数,如果是偶数将其转换为even,奇数则转换为odd.
最终的输出是:

~~~
odd
even
odd
~~~

>这个实例只是为说明问题,显然其中的某些步骤是可以合并的.

前后调用四个方法:

1. range     : 构建原始数据集合.
2. map       : 判断元素是否为偶数,如果是则转换为"even",否则转换为"odd".
3. subscribe : 订阅结果.

### 步骤拆分
从代码风格上来看,这很像我们平时使用的各种Builder类,不同的是Builder类始终是在一个对象上执行操作,而在这里
**每一操作都会产生一个全新的对象(它所操作的数据类型也可能是全新的),后续的所有操作都是在这个新对象上进行的,
这也是其转换实现的重点**.

为了更清晰的说明每个步骤都发生了什么，我们对每个步骤进行拆分:
~~~
    Observable<Integer> o1 = Observable.range(1, 3);
    Observable<String> o2 = o1.map(new Func1<Integer, String>() {
      @Override
      public String call(Integer integer) {
        return (integer % 2 == 0) ? "even" : "odd";
      }
    });
    o2.subscribe(new Action1<String>() {
      @Override
      public void call(String string) {
        System.out.println(string);
      }
    });
~~~
如上代码,整个流程跟之前一模一样. 1,2两步操作都产生了一个全新的Observable, 需要注意的是最终的订阅操作是发生在最后一个Observable `o2` 上的.


现在我们对以上代码的执行流程逐步剖析:

1. range

```
    Observable<Integer> o1 = Observable.range(1, 3);
```
这一行的作用就是创建一个包含[1,2,3]三个元素的Observable.
相关源码如下(省略参数判断代码):
~~~
public final static Observable<Integer> range(int start, int count) {
   return Observable.create(new OnSubscribeRange(start, start + (count - 1)));
}
~~~

  1. 创建OnSubscribeRange对象
  2. 创建o1,使用`1`创建的OnSubscribeRange作为构造参数.
需要注意的是Observable的唯一的创建参数是OnSubscribe类型的对象,这里使用的是OnSubscrbeRange.
*再次强调*:名为`OnSubscribe`,是因为这个类的核心函数是在其对应的Observable被订阅的时候被触发.

2. map
 
```
    Observable<String> o2 = o1.map(new Func1<Integer, String>() {
      @Override
      public String call(Integer integer) {
        return (integer % 2 == 0) ? "even" : "odd";
      }
    });
```
这段代码的作用是对o1增加一层转换操作,操作的具体内容就是判断o1中的每一个元素是奇数还是偶数,并转换为相应的字符串.

我们来看下map函数的实现:
~~~
    public final <R> Observable<R> map(Func1<? super T, ? extends R> func) {
        return lift(new OperatorMap<T, R>(func));
    }
~~~

与range步骤类似,全是创建对象的操作,唯一与range操作不同的是这里多了创建`OperatorMap`对象,并执行lift的操作.
lift核心实现(移除异常即错误处理代码)如下,其作用就是为每一次转换构造一个全新的Observable:

>注意OnSubscribe的调用链,当新创建的Observable的OnSubscribe.call被调用到时,会进一步调用到
当前Observable的onSubscribe.call,这就是*被订阅*事件的传递流程:**当最外层的Observable被订阅时
被订阅事件会从外到内层层传递,直到传递给初始Observable**(对于实例就是o1了).

~~~
    public final <R> Observable<R> lift(final Operator<? extends R, ? super T> lift) {
        return new Observable<R>(new OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> o) {
                Subscriber<? super T> st = hook.onLift(lift).call(o);
                st.onStart();
                onSubscribe.call(st);
                //...
            }
        }
    }
~~~

3. subscribe 

```
    o2.subscribe(new Action1<String>() {
      @Override
      public void call(String string) {
        System.out.println(string);
      }
    });
```
这段代码的作用是订阅o2的事件,并输出每一个事件附带的字符串.
subscribe有一系列重载,因为Subscriber支持监听三种事件,但用户却可能只关心其中的一个或者几个,
为了方便使用,对API多封装了一层.

~~~
    public final Subscription subscribe(final Action1<? super T> onNext) {
        return subscribe(new Subscriber<T>() {
            @Override
            public final void onCompleted() { }
            @Override
            
            public final void onError(Throwable e) {
                throw new OnErrorNotImplementedException(e);
            }

            @Override
            public final void onNext(T args) {
                onNext.call(args);
            }
        });
    }
~~~
~~~
    public final Subscription subscribe(Subscriber<? super T> subscriber) {
        subscriber.onStart();
        if (!(subscriber instanceof SafeSubscriber)) {
            subscriber = new SafeSubscriber<T>(subscriber);
        }
        hook.onSubscribeStart(this, onSubscribe).call(subscriber);
        return hook.onSubscribeReturn(subscriber);
    }
~~~

以上列出了subscribe调用堆栈的前两帧,后续执行流程比较复杂,就不贴源码了,具体如下图所示:

subscribe流程主要包括两个部分,调用序列的方向正好相反.

>整个图中的带有2的都是与o2相关的对象.
涉及创建某个类的对象时,使用该的大写开头名称.
涉及使用某个对象时,使用该对象对应类的小写开头名称.

* 流程一:subscribe
  1. 调用subscribe,使用action作为参数.
  2. 创建Subscriber2.
  3. 调用OnSubscribe2.call.参数为subscriber2.

  4. 调用OperatorMap.call,参数subscriber2.
  5. 创建Subscriber1.
  6. 调用OnSubscriber1(OnSubscribeRange).call,参数subscriber1.

  7. 创建RangeProducer;
  8. 调用Subscriber1.setProducer.
  9. 调用rangeProducer.request,发起数据请求.
* 流程二:send on*事件
  1. rangeProducer调用Subscriber1.onNext
  2. 调用OperatorMap进行数据转换
  3. 调用OnSubscribe2.onNext,参数位OperatorMap转换之后的数据.
  4. 调用Subscriber2.call


实例流程已经梳理完毕,现在让我们看看涉及的核心类之间的关系以及简化的subscribe处理流程图:


1. Observable使用OnSubscribe作为构造参数创建,并将其以成员变量的形式保存.其名字虽为Observerable,但本身并不是事件源,事件源由OnSubscribe实现.
2. 多层转换操作使用Observable的lift实现,其本质是使用Observable构建OnSubscribe链.
3. 在最外层Observable被订阅的时候,订阅事件会从外到内层层传递给每一层Observable的OnSubscribe成员.
4. Operator的作用是数据转换以及将相邻层次的Subscriber连接起来(用于后续的onNext/Complete/Error事件通知).
5. 事件源的Producer是OnSubscribe实现并设置给Subscriber的.
6. 多层Subscriber是链式包含及调用的.


我们已经了解了一个RxJava的简单实例及其涉及的内部源码处理流程.

最后我们来看一个稍微复杂点的例子:
监视一个输入框中的输入字符,没秒钟接收一次输入,将输入内容转化为大写然后输出.
且从编辑框内容到字符串的转换发生在UI Scheduler,大写转换发生在io Scheduler.

>实现该功能不必写成如下代码,为了说明API使用故意将其复杂化,请知悉.

~~~
    final PublishSubject<Editable> subject = PublishSubject.create();
    mSearchBox.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        subject.onNext(s);
      }
    });

    subject//
      .throttleLast(1, TimeUnit.SECONDS)//
     .observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))//
     .map(new Func1<Editable, String>() {
      @Override
      public String call(Editable editable) {
        return editable.toString();
      }
     })//
     .observeOn(Schedulers.io())//
     .map(new Func1<String, String>() {
       @Override
       public String call(String s) {
         if (TextUtils.isEmpty(s)) {
           return s;
         }
         return s.toUpperCase();
       }
     })//
     .subscribe(new Action1<String>() {//
        @Override
        public void call(String s) {
          System.out.println(s);
        }
      });
~~~

上边的例子中转换步骤多了,而且出现了几个新东西:

**throttleLast**: 事件频度限制. RxJava是基于观察者模型的, Observable内部会发出事件,Observer处理事件,这样的`生产者-消费者`模型
必然存在生产能力和消费能力的问题.在RxJava中你可以缓存事件或者忽略事件. throttle系列的函数就是一种事件消费策略,实例代码展示了如何
设置每一秒消费一个输入事件.

**observeOn**: 指定observer行为的执行线程上下文. RxJava的最终目的是提供强大易用的异步API,既然是异步操作,就一定会涉及异步操作所执行的线程
上下文.比如一个耗时操作显然不应该发生在UI线程,而应当放在后台线程执行等. 幸运的是RxJava在API级别帮你优雅的解决了这个问题,你只需要指定行为
所在的线程上下文即可,RxJava默认提供了`io`, `compute`, `immediate`等线程上下文来方便你使用,也允许你指定自定义的执行线程上下文,实例中的UIThreadExecutor就是用Android Handler实现的自定义Executor.

**PublishSubject**: Subject是前文还未涉及的一个概念,Subject继承自Observerable和Observer,也就是它既是观察者,又是被观察者,它通常的作用是连接或者适配Observerable.

至此,你已经了解到了RxJava相关的大部分核心概念,当然还有Plugin, Async, join, math, string等模块细节并没有涉及到,在后文我会详细描述.




