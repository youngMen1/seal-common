package com.seal.features.juc;

import com.seal.features.entity.City;
import com.seal.features.entity.Person;
import com.seal.features.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 异步编程
 * Java 在 1.8 版本提供了 CompletableFuture 来支持异步编程，
 * CompletableFuture 有可能是你见过的最复杂的工具类了，不过功能也着实让人感到震撼。
 * <p>
 * runAsync(Runnable runnable)--->run()方法没有返回值
 * <p>
 * supplyAsync(Supplier<U> supplier)--->调用方通过join或者get就能取到该CompletableFuture的result字段的值。（1.join()方法抛出的是uncheck异常（即未经检查的异常),不会强制开发者抛出，
 * 2.get()方法抛出的是经过检查的异常，ExecutionException, InterruptedException 需要用户手动处理（抛出或者 try catch））
 * <p>
 * thenCombine()--->thenCombine会在两个任务都执行完成后，把两个任务的结果合并。
 * 注意:
 * 两个任务中只要有一个执行异常，则将该异常信息作为指定任务的执行结果。
 * 两个任务是并行执行的，它们之间并没有先后依赖顺序。
 * <p>
 * thenApply()--->还是原来的CompletableFuture，相当于将CompletableFuture<T> 转换成CompletableFuture<U>,只是泛型从Student转换成Person。
 * <p>
 * thenCompose()--->用来连接两个CompletableFuture，是生成一个新的CompletableFuture。
 * <p>
 * thenApplyAsync()--->链式编程
 *
 * @author fengzhiqiang
 * @date 2020/12/23 18:11
 **/
public class CompletableFutureTest {

    public static void main(String[] args) {
        // 烧水泡茶例子
        // createCompletableFuture();

        // thenApply()方法例子
        // completableFutureExample();

        // thenCompose()方法例子
        // completableFutureExample2();

        // thenApplyAsync()方法例子
        // completableFutureExample3();

        // allOf 多种结合的调用
        completableFutureExample4();

        // anyOf 的含义是只要有任意一个CompletableFuture结束，
        // 就可以做 接下来的事情，而无须像AllOf那样，
        // 等待所有的CompletableFuture结束。
        // completableFutureExample5();
    }


    /**
     * 我们分了3个任务:
     * 任务1-> 负责洗水壶、烧开水
     * 任务2-> 负责洗茶壶、洗茶杯和拿茶叶
     * 任务3-> 要等待任务 1 和任务 2 都完成后才能开始。
     */
    private static void createCompletableFuture() {
        // 任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
        // 任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                });
        // 任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (result1, result2) -> {
                    System.out.println("T1:拿到茶叶:" + result2);
                    System.out.println("T1:泡茶...");
                    return "上茶:" + result2;
                });
        // 等待任务3执行结果
        System.out.println(f3.join());

        //                一次执行结果：
        //                T1:洗水壶...
        //                T2:洗茶壶...
        //                T1:烧开水...
        //                T2:洗茶杯...
        //                T2:拿茶叶...
        //                T1:拿到茶叶:龙井
        //                T1:泡茶...
        //                上茶:龙井
    }

    private static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * thenApply()例子
     */
    private static void completableFutureExample() {
        CompletableFuture<Person> future1 = CompletableFuture.supplyAsync(() -> {
            Student student = new Student();
            student.setName("张三");
            student.setSex("男");
            student.setMoney(1.1D);
            return student;
        }).thenApply(student -> {
            Person person = new Person();
            person.setName(student.getName());
            person.setAge(student.getSex().equals("男") ? "0" : "1");
            person.setCity(new City("11"));
            person.setHeight(175);
            return person;
        });
        Person person = future1.join();
        System.out.println(person.toString());
    }

    /**
     * thenCompose()例子
     */
    private static void completableFutureExample2() {
        CompletableFuture<Person> future1 = CompletableFuture.supplyAsync(() -> {
            Student student = new Student();
            student.setName("张三");
            student.setSex("男");
            student.setMoney(1.1D);
            return student;
        }).thenCompose(student -> CompletableFuture.supplyAsync(() -> {
            // 注意这里用到了上个线程的返回值student
            Person person = new Person();
            person.setName(student.getName());
            person.setAge(student.getSex().equals("男") ? "0" : "1");
            person.setCity(new City("11"));
            person.setHeight(175);
            return person;
        }));
        Person person = future1.join();
        System.out.println(person.toString());
    }

    /**
     * thenApplyAsync()方法例子链式编程
     * [Student(name=张三, sex=男, money=0.0), Student(name=李四, sex=男, money=0.0), Student(name=王五, sex=男, money=0.0)]
     */
    private static void completableFutureExample3() {
        CompletableFuture<List<Student>> future = CompletableFuture.supplyAsync(() -> {
            List<Student> list = new ArrayList<>();
            Student student = new Student();
            student.setName("张三");
            student.setSex("男");
            student.setMoney(0);
            list.add(student);
            return list;
        }).thenApplyAsync(list -> {
            Student student = new Student();
            student.setName("李四");
            student.setSex("男");
            student.setMoney(0);
            list.add(student);
            return list;
        }).thenApplyAsync(list -> {
            Student student = new Student();
            student.setName("王五");
            student.setSex("男");
            student.setMoney(0);
            list.add(student);
            return list;
        });
        List<Student> result = future.join();
        System.out.println(result.toString());
    }

    /**
     * allOf是「与」
     * allOf 多种结合的调用
     * allOf 的返回值是 CompletableFuture<Void>类型，
     * 这是因为 每个传入的 CompletableFuture 的返回值都可能不同，
     * 所以组合的结果是 无法用某种类型来表示的，索性返回 Void 类型。
     * CompletableFuture.allOf实现异步执行同步(阻塞)收集结果
     */
    private static void completableFutureExample4() {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "biandan");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "说");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "让天下没有难写的代码");

        // 不需要返回值推荐用allOf（一般使用allof方法的时候,都是消费Action,一般不会对结果进行处理,如果对结果进行处理,那肯定会发生错误...）
        // 返回的是 void 类型 注意 CompletableFuture.allOf 的返回类型是CompletableFuture。
        // 这种方法的局限性在于它不能返回所有 Supplier 的组合结果。
        // 我们必须从未来手动获取结果。使用 CompletableFuture.join() 方法获取。
        // 等待所有future执行完成，收集结果返回。allOf里面的所有线程为执行完毕，主线程会阻塞，直到allOf里面的所有线程都执行，线程就会被唤醒。
        CompletableFuture<Void> completableFuture1 = CompletableFuture.allOf(future1, future2, future3);
        completableFuture1.join();
        /**@see https://www.thinbug.com/q/53391071**/
        if (completableFuture1.isDone()) {
            System.out.println("Future result " + future1.join() + " | " + future2.join() + " | " + future3.join());
        } else {
            System.out.println("Futures are not ready");
        }

        // 使用 join 方法手动获取结果
        List<String> completableFuture2 = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        System.out.println(completableFuture2.toString());

    }


    /**
     * anyOf是「或」
     * anyOf 的含义是只要有任意一个 CompletableFuture 结束，
     * 就可以做 接下来的事情，而无须像 AllOf 那样，等待所有的 CompletableFuture 结束。
     */
    private static void completableFutureExample5() {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "biandan");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "说");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "让天下没有难写的代码");
        CompletableFuture<Object> completableFuture1 = CompletableFuture.anyOf(future1, future2, future3);
        System.out.println(completableFuture1.join());
    }


}
