package com.seal.features.juc;

import com.seal.features.entity.City;
import com.seal.features.entity.Person;
import com.seal.features.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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
        completableFutureExample3();
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
}
