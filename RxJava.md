#RxJava

***

## 什么是RxJava
RxJava是Rx(Reactive Extensition)异步编程模型的Java语言实现.Rx基于Observer模式，并在此基础上对数据及行为扩展建模，思想最早来源与微软.
直观的讲，RxJava就是一个Java异步编程函数库．
在Rx之前，遇到异步任务以及嵌套型异步任务的问题模型时，我们往往用以下几种方式来解决：
  1. 回调，多层次的回调.这种方式的缺点有两个：
  　　* 只是简单的将任务的输入输出异步连接起来，却并没有对任务之间的关系建模，一旦遇到涉及任务关系的
        状况时，就需要开发人员手动编写任务关系组合逻辑代码．
        比如一个任务A包含３个子任务a,b,c，全部都是消耗性（异步）任务，只有a,b,c全失败整个任务才算失败，有任意一个成功就算成功．这种带有
        Any或者All语义的任务时，就需要手动写map-reduce的逻辑.
      * 代码金字塔缩进问题．如果任务嵌套层次过深，则代码的缩进将会严重降低代码的可阅读性.
  2. 状态机. 多步骤异步任务（子步骤也是异步任务）的情况下，状态机是目前的最佳解决方案，其控制点集中，但缺点就是必须为每一层任务定义状态
  　及状态切换逻辑．chromium的网络模块是将状态机实践到极致的代码，可谓遍地都是状态机. 

  3. Task编程模型. 又是一个源自微软被复制到各地的模型，在构建系统中尤为普遍，可以指定Task之间的关系，Task的执行线程上下文等. 在也为之前
    的团队实现了一个简单的Task模型，支持Any, All, While, Before/After等语义,在行为建模上，Task和Rx有很多相似之处，但是Task对于数据的建模
    只停留在输入输出上（这最多等价于Rx的Operator)，这就决定了它使用范围的局限性．你无法对一个数据源用Task框架异步建模．比如你无法监视一个数据源的变动.

> 微软有一种异步实现方式是await和async,它的实现让异步编程可以和同步编程一样，但是需要强大的底层框架支持．

以上方式的特点都是**基于行为建模**,关注行为及其输入输出；而Rx思想则除了对行为建模还对数据进行建模，我们知道在计算机的世界里只有数据，计算
机主要的任务也就是处理数据，所以对数据和处理数据的行为这两个关键点建模，就基本上可以涵盖到数据处理的所有Case,这也是我觉得Rx设计受追捧的一个
原因. 
Rx思想中另一个优秀的点是将数据以流的形式建模，我们知道一旦一个数据处理任务要高性能的与其他任务之间协作，就必须对数据进行流式拆分且对数据的处理
支持流式处理.
>比如你要从网络下载一个1G的文件并存储在本地，为了内存，性能等考虑，你自然不会一次把1G文件从网络全接收到内存，然后再写入文件系统，
最佳的方式是使用异步IO服务(注意是Proactor形式的读取，而不是Reactor,即读取操作是系统在通知IO完成的时候已经完成）从网络读取数据，每读取一次就将该
次读取的数据写入文件系统（当然你也可以做缓存，这里只是说明问题）．那么以上整个流程就从一个大数据处理任务拆解成了序列型的多个子任务，这就是流式
处理中最典型的一种．还有就是XML的sax解析器也是个很好的例子．

Rx将数据及其处理以流式建模，不仅可以让执行流程更加高效，　而且让整个API体系非常易用．我们知道一个优秀的函数库不仅需要其性能等硬
实力雄厚，API易用性等软实力也不可小觑，如果一个函数库的API及其难用，极其反人类，极其容易出错，那么不管这个库本身多么优秀，其使用者也会大打折扣.


## 异步
  我们已经了解到，RxJava是一个异步函数库，有必要在这里再讨论下异步是做什么的．   

  "设置个回调，不阻塞当前线程",当提到什么是异步以及异步可以做什么的时候，这个是得到的最直接的回答．的确，这个答案是对的.   
  我自己的理解，异步是**整合资源，合理分配，以达到系统的最佳性能**．这个目标跟"云"的目标似乎是一样的．

  >怎么理解这个结论呢？
  当异步需要被使用的时候，使用场景往往涉及多个资源访问的,我们的解决方案也通常是按照资源将逻辑分解到多线程上下文，各自处理，互不阻塞，以达到系统的最优性能．

  还是上边的下载1G文件到本地的例子，在我们自己的代码中，我们不难看到很多解决方案是直接起**一个**Executor,背后是个线程池，然后不管三七二十一把网络读取，数据处理
  以及写文件任务包装成Runnable直接丢到这个线程池里. 这种方式就是没有按照资源分解逻辑到线程上下文的典型例子．如果写文件的任务叫做WriteFileTask,网络读取以及数据
  处理（比如解压）的任务叫做NetworkTask,那么你的Executor下层的任务队列很容易出现如下状况:
  ~~~
  -----------------
  | WriteFileTask |
  -----------------
  | WriteFileTask |
  -----------------
  | WriteFileTask |
  -----------------
  | WriteFileTask |
  -----------------
  | NetworkTask   |
  -----------------
  | WriteFileTask |
  -----------------
  | WriteFileTask |
  -----------------
  | WriteFileTask |
  -----------------
  | WriteFileTask |
  -----------------
  | NetworkTask   |
  -----------------
  ~~~
  在这种队列状况下，即使你的网络空闲，NetworkTask仍然可能没有被执行，因为Executor下层的线程池可能满满的都在做WriteFileTask,显然这是一种资源浪费．我们希望任何时候
  都不要让我们的资源空闲，所以我们应当将NetworkTask和WriteFileTask分解到两个Executor中去，那么运行期的执行就是这个样子：

  ~~~

 -----------------                    -----------------
 | WriteFileTask |                    | NetworkTask   |
 -----------------                    -----------------
 | WriteFileTask |                    | NetworkTask   |
 -----------------                    -----------------
 | WriteFileTask |                    | NetworkTask   |
 -----------------                    -----------------
 | WriteFileTask |                    | NetworkTask   |
 -----------------                    -----------------
 | WriteFileTask |                    | NetworkTask   |
 -----------------                    -----------------

 ~~~
 
 这样任何时候，你的资源都不会闲置，始终在饱和的执行任务． 
 
 这样分解的另一个好处是你可以更加容易的对多个任务队列之间进行协调，比如写文件速度太慢，网络很快的时候可能要限制网络读取（毕竟读取的内容会缓存在内存，会对系统内存
 产生压力）. Chromium将这种思想实践到了极致，划分出了独立的File, DB, Net, Cache等命名线程，每个命名线程对应独立的任务队列，有兴趣的可以参考.

 以上内容所涉及的线程上下文（或者应该叫任务队列）分配，以及多任务生产消费之间产生的Back Pressure问题，RxJava都妥妥的在框架级实现，你可以使用一行代码切换你的任务执行上下文Scheduler,而且你可以自定义Scheduler;

 那么再回到最初的那句总结:整合资源，合理分配，以达到系统的最佳性能, 这就是异步的优势所在，也是编程语言和框架的一个大趋势.

## RxJava
###RxJava Sample
 RxJava就是一个异步编程函数库，除了它在异步编程建模上的优越性之外，最大的特点，是它优雅的API. 让开发人员可以连续流畅的写代码，这也是个人比较推崇的一个重要原因.

 那么下边我就先给出一个使用RxJava的例子，这个例子是之前[构建一个小型系统](http://www.atatech.org/articles/52873)中Android客户端从服务器通过http协议获取数据的实例代码.
 ~~~
 String url = "http://localhost:9090/programmer/query?name=";
    Observable//
      .just(url)//
      .flatMap(new Func1<String, Observable<Response>>() {
        @Override
        public Observable<Response> call(String url) {
          final PublishSubject<Response> subject = PublishSubject.create();
          Request request = new Request.Builder().url(url).build();
          Call call = new OkHttpClient().newCall(request);
          call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              subject.onError(new Exception("Fetch Programmer info failed: " + e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              subject.onNext(response);
              subject.onCompleted();
            }
          });
          return subject;
        }
      })//
      .map(new Func1<Response, ProgrammerQueryResponseBody>() {
        @Override
        public ProgrammerQueryResponseBody call(Response response) {
          if (response.isSuccessful()) {
            APIResponse<ProgrammerQueryResponseBody> apiResponse = null;
            try {
              Type type = new TypeToken<APIResponse<ProgrammerQueryResponseBody>>() {
              }.getType();
              apiResponse = new Gson().fromJson(response.body().string(), type);
            } catch (IOException e) {
              Observable.error(new Exception("Parse response failed!" + e.getMessage()));
            }
            if (apiResponse.getCode() == 0) {
              return apiResponse.getData();
            }
          }
          Observable.error(new Exception("Convert Programmer response failed!"));
          return null;
        }
      })//
     // .cast(ProgrammerQueryResponseBody.class)//
      .observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))//
      .subscribe(new Action1<ProgrammerQueryResponseBody>() {
        @Override
        public void call(ProgrammerQueryResponseBody rspBody) {
          mProgrammerAdapter.update(rspBody.getProgrammers());
        }
      }, new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
          Snackbar.make(mFab, throwable.getMessage(), Snackbar.LENGTH_LONG)
                  .setAction("Action", null)
                  .show();
        }
      });
 ~~~
 整个处理流程如下：
 1. 使用Okhttp通过http Get向服务器发起请求(异步使用flatMap,如果是同步可以直接使用map）。
 2. 使用Gson反序列化服务器响应，并获取其中的Programmer列表。
 3. 使用获取的Programmer数据更新ListView Adapter，从而更新ListView。

###RxJava API
 Rx基于Observer进行建模，同时对数据的操作场景进行了大量的总结和规范．由于场景覆盖非常之多，所以其Java实现--RxJava的API也是非常之多的，其API分为几个大类：
  * Creating 创建操作 
  * Transforming 变换操作
  * Filtering 过滤操作
  * Combining 结合操作
  * Error Handling 错误处理
  * Utility 辅助操作
  * Conditional 条件和布尔操作
  * Mathematical 算术和聚合操作
  * Async 异步操作
  * Connect 连接操作
  * Convert 转换操作
  * Blocking 阻塞操作
  * String 字符串操作

 但目前有一些模块并没有在RxJava代码库中，而是以扩展库的方式存在, 这些扩展的维护人员也比较少,所以其并入核心库估计还得一段时间.


###RxJava Example
 终于到了重点了，也是写这篇文章的目的，为了方便大家了解RxJava的API,我讲所有的RxJava API（至少是官方文档中提到的）都写在一个android apk中，并在其中附以
 功能描述,代码示例，marble-diagram(Rx用来描述数据及其处理流程的图),　以及一些使用场景. 所有的资料都是在APK中，使用的时候不会消耗任何流量，而且你可以在任何时候
 任何地方学习使用.
 示例程序的特点如下:
 1. API涵盖全面: 包含了核心库及所有扩展实现库200个左右的API.
 2. 数据本地化,无需流量: 示例中的所有数据和图片都是本地加载的,所以无需消耗流量.
 3. 示例代码都是从源码中直接生成,所以看起来跟代码直接运行的效果是一样的.

 所有示例代码托管在github上,你可以直接扫码安装(Android用户).

 [Download APK](https://raw.githubusercontent.com/wiki/leeowenowen/rxjava-examples/apk/app-debug.apk)


![Barcode/二维码](https://raw.githubusercontent.com/wiki/leeowenowen/rxjava-examples/res/barcode.png)

![Demo Screenshot1](https://raw.githubusercontent.com/wiki/leeowenowen/rxjava-examples/res/rxjava-1.png)


![Demo Screenshot2](https://raw.githubusercontent.com/wiki/leeowenowen/rxjava-examples/res/rxjava-2.png)




 
