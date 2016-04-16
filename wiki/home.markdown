### Welcome to RxJava Example wiki

---


### 名词解释

**Notification**: RxJava中有三种不同类型的事件:onNext,onComplete,onError. Notification是对这三种事件及其附带数据的封装.

**Observable**:被观察者,概念上的事件源.
    *Hot Observable*: 一旦创建就开始对外发出事件,最常用的是鼠标.
    *Cold Observable*: 创建以后不会立即对外发出事件,直到有订阅者订阅才会发出事件.
    `Hot`和`Cold`是主要分类,另外还有一种概念是Connectable Observable, 只有被connect之后才会对外发出事件,否则即使被订阅也不会.
>RxJava目前的大部分内置Observerable(内置转换中产生的Observable)实现是`Cold Observable`,所以这里主要介绍的也是`Cold Observable`,
也就是*Observable创建时是什么都不做的,只有在被订阅时才会开始对外发出事件.

>说Observerable是概念上的数据源的原因是它在RxJava中的实现有点像Canvas, Canvas名字叫做画布, 但其实只是各种绘图操作的集合,最终的图像数据并不在Canvas里.

>RxJava中Observable虽名为被观察者,但事件的发出却是由其成员OnSubscribe完成的.Observable的主要作用起始是对外提供一致的API,并将不同的OnSubscribe对象串联起来,以在被订阅的时候传递事件流.

**Producer**:上边说了,Observable不是事件源,真实的事件源就是Producer,它提供了request接口用来请求数据.

**Observer**:观察者, 这里指事件onNext,onComplete,onError的关注者.

**Subscription**: 订阅行为,你可以从中获取该订阅的状态或者取消订阅.

**Subscriber**: 继承自Observer以及subscription, 代表一个订阅者,在Observer的基础上增加了subscription的功能.

**OnSubscribe**: RxJava中Observable的构造参数,在该Observer被订阅时调用.

**Subject**: 继承自Observable和Observer,既关注其他Observable同时也被其他的Observer关注.

**Operator**: 操作符,RxJava中的订阅行为是链式的, Operator负责不同订阅者的功能衔接即转换.其接口签名是将一个Subscriber转换为另一个Subscriber.

**lift**: 转换操作,每次转换都会从旧的Observable产生一个全新的Observable,RxJava每一步转换操作就是一次lift.

### RxJava是什么

RxJava是一个异步框架，是ReactiveX的Java实现.




[源码分析](http://git.ucweb.local/ucunion_frontend/rxjava-example/wikis/code_analyse)
