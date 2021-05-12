# JUC 并发包工具类

## CountDownLatch(用 CountDownLatch 实现线程等待)

1.这个类使一个线程等待其他线程各自执行完毕后再执行
2.是通过一个计数器来实现的，计数器的初始值是线程的数量。
每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，
然后在闭锁上等待的线程就可以恢复工作了。

使用场景:
CountDownLatch 主要用来解决一个线程等待多个线程的场景，
可以类比旅游团团长要等待所有的游客到齐才能去下一个景点。

## CyclicBarrier(用 CyclicBarrier 实现线程同步)

同步就是指多线程之间的线程间的协作

`while(存在未对账订单){
  // 查询未对账订单
  pos = getPOrders();
  // 查询派送单
  dos = getDOrders();
  // 执行对账操作
  diff = check(pos, dos);
  // 差异写入差异库
  save(diff);
} `

使用场景:
CyclicBarrier 是一组线程之间互相等待，更像是几个驴友之间不离不弃。

## Executor线程池

目前业界线程池的设计，普遍采用的都是生产者 - 消费者模式。
线程池的使用方是生产者，线程池本身是消费者。

## Future：如何用多线程实现最优的“烧水泡茶”程序？
1.我们仅仅介绍了 ThreadPoolExecutor 的 void execute(Runnable command) 方法，
利用这个方法虽然可以提交任务，但是却没有办法获取任务的执行结果（execute() 方法没有返回值）。
而很多场景下，我们又都是需要获取任务的执行结果的。
那 ThreadPoolExecutor 是否提供了相关功能呢？必须的，这么重要的功能当然需要提供了。

2.Java 通过 ThreadPoolExecutor 提供的 3 个 submit() 方法和 1 个 FutureTask 工具类来支持获得任务执行结果的需求。

## CompletableFuture
Java 在 1.8 版本提供了 CompletableFuture 来支持异步编程，CompletableFuture 有可能是你见过的最复杂的工具类了，不过功能也着实让人感到震撼。

## CompletionService

解决批量执行异步任务

对于简单的并行任务，你可以通过“线程池 + Future”的方案来解决；
如果任务之间有聚合关系，无论是 AND 聚合还是 OR 聚合，都可以通过 CompletableFuture 来解决；
而批量的并行任务，则可以通过 CompletionService来解决。

## Fork/Join
Java 并发包里提供了一种叫做 Fork/Join 的并行计算框架，就是用来支持分治这种任务模型的。分治任务模型

Fork 对应的是分治任务模型里的任务分解，Join 对应的是结果合并。

Fork/Join 计算框架主要包含两部分，一部分是分治任务的线程池 ForkJoinPool，另一部分是分治任务 ForkJoinTask。

### 分治任务模型
分治任务模型可分为两个阶段：一个阶段是任务分解，也就是将任务迭代地分解为子任务，直至子任务可以直接计算出结果；
另一个阶段是结果合并，即逐层合并子任务的执行结果，直至获得最终结果。







