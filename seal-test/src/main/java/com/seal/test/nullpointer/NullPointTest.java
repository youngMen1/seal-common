package com.seal.test.nullpointer;

import com.alibaba.fastjson.JSON;
import com.seal.test.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.1包装类型的空指针问题
 *
 * @author fengzhiqiang
 * @date 2021/5/18 10:03
 **/
@Slf4j
public class NullPointTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        // 1.1包装类型的空指针问题
        System.out.println(testInteger(null));
        // 1.2 级联调用的空指针问题
        nullPointTest();
        // 1.3 Equals方法左边的空指针问题
        nullPointTest2();
        // 1.4 ConcurrentHashMap 这样的容器不支持 Key，Value 为 null。
        nullPointTest3();
        // 1.5 集合，数组直接获取元素
        nullPointTest4();
        // 1.6 对象直接获取属性
        nullPointTest5();
        // 1.7 为什么明明是2019年12月31号，就转了一下格式，就变成了2020年12月31号了
        dateTestErr();
        // 3.金额数值计算精度的坑
        doubleTest();
        // 4.FileReader默认编码导致乱码问题
        fileReaderTest();
        // 5.Integer缓存的坑
        integerTest();
        // 8.疏忽switch的return和break
        testSwitchTest("1");
    }


    /**
     * 1.1包装类型的空指针问题
     *
     * @param i
     * @return
     */
    private static Integer testInteger(Integer i) {
        // 包装类型，传参可能为null，直接计算，则会导致空指针问题
        return i + 1;
    }


    /**
     * 1.2 级联调用的空指针问题
     */
    public static void nullPointTest() {
        // fruitService.getAppleService() 可能为空，会导致空指针问题
        // fruitService.getAppleService().getWeight().equals("OK");
    }

    /**
     * 1.3 Equals方法左边的空指针问题
     */
    public static void nullPointTest2() {
        String s = null;
        // s可能为空，会导致空指针问题
        if (s.equals("666")) {
            System.out.println("公众号：捡田螺的小男孩，666");
        }

        // 这样是不会报空指针的
        if ("666".equals(s)) {
            System.out.println("公众号：捡田螺的小男孩，666");
        }
    }

    /**
     * 1.4 ConcurrentHashMap 这样的容器不支持 Key，Value 为 null。
     */
    public static void nullPointTest3() {
        Map map = new ConcurrentHashMap<>(16);
        String key = null;
        String value = null;
        map.put(key, value);
    }


    /**
     * 1.5  集合，数组直接获取元素
     */
    public static void nullPointTest4() {
        int[] array = null;
        List list = null;
        // 空指针异常
        System.out.println(array[0]);
        // 空指针异常
        System.out.println(list.get(0));
    }

    /**
     * 1.6 对象直接获取属性
     */
    public static void nullPointTest5() {
        User user = null;
        //空指针异常
        System.out.println(user.getAge());
    }

    // ========================================================================================

    //  2. 日期大写YYYY格式设置的坑  正确的是小写yyyy-MM-dd HH:mm:ss

    /**
     * 日常开发，经常需要对日期格式化，但是呢，年份设置为YYYY大写的时候，是有坑的哦。
     * <p>
     * 反例：
     * 运行结果：2019-12-31 转 YYYY-MM-dd 格式后 2020-12-31
     * <p>
     * 为什么明明是2019年12月31号，就转了一下格式，就变成了2020年12月31号了
     * 因为YYYY是基于周来计算年的，它指向当天所在周属于的年份，一周从周日开始算起，
     * 周六结束，只要本周跨年，那么这一周就算下一年的了。正确姿势是使用yyyy格式。
     */
    public static void dateTestErr() {
        // 反例：
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 31);

        Date testDate = calendar.getTime();

        SimpleDateFormat dtf = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("2019-12-31 转 YYYY-MM-dd 格式后 " + dtf.format(testDate));


        // 正例：
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2019, Calendar.DECEMBER, 31);
//
//        Date testDate = calendar.getTime();
//
//        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println("2019-12-31 转 yyyy-MM-dd 格式后 " + dtf.format(testDate));
    }

    // ================================================================================================

    // 3.金额数值计算精度的坑

    /**
     * 看下这个浮点数计算的例子吧：
     * 可以发现，结算结果跟我们预期不一致，
     * 其实是因为计算机是以二进制存储数值的，
     * 对于浮点数也是。对于计算机而言，0.1无法精确表达，
     * 这就是为什么浮点数会导致精确度缺失的。
     * 因此，「金额计算，一般都是用BigDecimal 类型」
     */
    public static void doubleTest() {
        System.out.println(0.1 + 0.2);
        System.out.println(1.0 - 0.8);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);

        double amount1 = 3.15;
        double amount2 = 2.10;
        if (amount1 - amount2 == 1.05) {
            System.out.println("OK");
        }

        // 运行结果：
//        0.30000000000000004
//        0.19999999999999996
//        401.49999999999994
//        1.2329999999999999

        // 对于以上例子，我们改为BigDecimal，再看看运行效果：

        System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.2)));
        System.out.println(new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
        System.out.println(new BigDecimal(4.015).multiply(new BigDecimal(100)));
        System.out.println(new BigDecimal(123.3).divide(new BigDecimal(100)));

        // 0.3000000000000000166533453693773481063544750213623046875
        // 0.1999999999999999555910790149937383830547332763671875
        // 401.49999999999996802557689079549163579940795898437500
        // 1.232999999999999971578290569595992565155029296875


        // 发现结果还是不对，「其实」，使用 BigDecimal 表示和计算浮点数，必须使用「字符串的构造方法」
        // 来初始化 BigDecimal，正例如下：
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println(new BigDecimal("1.0").subtract(new BigDecimal("0.8")));
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100")));
        System.out.println(new BigDecimal("123.3").divide(new BigDecimal("100")));

        // 在进行金额计算，使用BigDecimal的时候，我们还需要「注意BigDecimal的几位小数点，还有它的八种舍入模式哈」。

    }

    // ==========================================================================================

    // 4. FileReader默认编码导致乱码问题

    /**
     * 4.FileReader默认编码导致乱码问题
     */
    private static void fileReaderTest() throws IOException {

//        Files.deleteIfExists(Paths.get("test.txt"));
//        Files.write(Paths.get("jay.txt"), "你好,捡田螺的小男孩".getBytes(Charset.forName("GBK")));
//        System.out.println("系统默认编码：" + Charset.defaultCharset());
//
//        char[] chars = new char[10];
//        String content = "";
//        try (FileReader fileReader = new FileReader("jay.txt")) {
//            int count;
//            while ((count = fileReader.read(chars)) != -1) {
//                content += new String(chars, 0, count);
//            }
//        }
//        System.out.println(content);

        // 系统默认编码：UTF-8
        //���,�����ݵ�С�к�

        // 从运行结果，可以知道，系统默认编码是utf8，demo中读取出来，出现乱码了。为什么呢？
        // FileReader 是以当「前机器的默认字符集」来读取文件的，如果希望指定字符集的话，
        // 需要直接使用 InputStreamReader 和 FileInputStream。

        Files.deleteIfExists(Paths.get("test.txt"));
        Files.write(Paths.get("test.txt"), "你好,捡田螺的小男孩".getBytes(StandardCharsets.UTF_8));
        System.out.println("系统默认编码：" + Charset.defaultCharset());

        char[] chars = new char[10];
        String content = "";
        try (FileInputStream fileInputStream = new FileInputStream("test.txt");
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("GBK"))) {
            int count;
            while ((count = inputStreamReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }
        System.out.println(content);
    }

    /**
     * 5.Integer缓存的坑
     */
    private static void integerTest() {
        Integer a = 127;
        Integer b = 127;
        System.out.println("a==b:" + (a == b));

        Integer c = 128;
        Integer d = 128;
        System.out.println("c==d:" + (c == d));
        System.out.println("c==d:" + (c.equals(d)));

        // 运行结果：
//        a==b:true
//        c==d:false
//        c==d:true

        // 为什么Integer值如果是128就不相等了呢？「编译器会把 Integer a = 127 转换为 Integer.valueOf(127)。」
        // 我们看下源码。
//        public static Integer valueOf(int i) {
//            if (i >= IntegerCache.low && i <= IntegerCache.high)
//                return IntegerCache.cache[i + (-IntegerCache.low)];
//            return new Integer(i);
//        }

//        可以发现，i在一定范围内，是会返回缓存的。

//        默认情况下呢，这个缓存区间就是[-128, 127]，所以我们业务日常开发中，
//        如果涉及Integer值的比较，需要注意这个坑哈。还有呢，设置 JVM 参数加上 -XX:AutoBoxCacheMax=1000，
//        是可以调整这个区间参数的，大家可以自己试一下哈
    }

    // ===============================================================================

    // 6. static静态变量依赖spring实例化变量，可能导致初始化出错

    // 之前看到过类似的代码。静态变量依赖于spring容器的bean。
    // private static SmsService smsService = SpringContextUtils.getBean(SmsService.class);
    // 这个静态的smsService有可能获取不到的，因为类加载顺序不是确定的，正确的写法可以这样，如下：

    // private static SmsService  smsService =null;
    // // 使用到的时候采取获取
    // public static SmsService getSmsService(){
    //   if(smsService == null){
    //      smsService = SpringContextUtils.getBean(SmsService.class);
    //   }
    //   return smsService;
    // }

    // ============================================================================================

    // 7.使用ThreadLocal，线程重用导致信息错乱的坑

    // private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);
    //
    //@GetMapping("wrong")
    //public Map wrong(@RequestParam("userId") Integer userId) {
    //    //设置用户信息之前先查询一次ThreadLocal中的用户信息
    //    String before  = Thread.currentThread().getName() + ":" + currentUser.get();
    //    //设置用户信息到ThreadLocal
    //    currentUser.set(userId);
    //    //设置用户信息之后再查询一次ThreadLocal中的用户信息
    //    String after  = Thread.currentThread().getName() + ":" + currentUser.get();
    //    //汇总输出两次查询结果
    //    Map result = new HashMap();
    //    result.put("before", before);
    //    result.put("after", after);
    //    return result;
    //}

    // 按理说，每次获取的before应该都是null，但是呢，程序运行在 Tomcat 中，
    // 执行程序的线程是 Tomcat 的工作线程，而 Tomcat 的工作线程是基于线程池的。

    // 线程池会重用固定的几个线程，一旦线程重用，那么很可能首次从 ThreadLocal 获取的值是之前其他用户的请求遗留的值。
    // 这时，ThreadLocal 中的用户信息就是其他用户的信息。

    // 因此，使用类似 ThreadLocal 工具来存放一些数据时，需要特别注意在代码运行完后，显式地去清空设置的数据，
    // 正例如下：
    // @GetMapping("right")
    //public Map right(@RequestParam("userId") Integer userId) {
    //    String before  = Thread.currentThread().getName() + ":" + currentUser.get();
    //    currentUser.set(userId);
    //    try {
    //        String after = Thread.currentThread().getName() + ":" + currentUser.get();
    //        Map result = new HashMap();
    //        result.put("before", before);
    //        result.put("after", after);
    //        return result;
    //    } finally {
    //        // 在finally代码块中删除ThreadLocal中的数据，确保数据不串
    //        currentUser.remove();
    //    }
    //}

    // =========================================================================================

    // 8. 疏忽switch的return和break

    /**
     * 输入参数：1
     * 输出结果：
     * 测试switch
     * 1
     * 2
     * testSwitch结果是：2
     * <p>
     * switch 是会「沿着case一直往下匹配的，直到遇到return或者break。」
     * 所以，在写代码的时候留意一下，是不是你要的结果。
     *
     * @param key
     * @return
     */
    private static String testSwitchTest(String key) {

        // 反例：
        switch (key) {
            case "1":
                System.out.println("1");
            case "2":
                System.out.println(2);
                return "2";
            case "3":
                System.out.println("3");
            default:
                System.out.println("返回默认值");
                return "4";
        }

        // 正例：
//        switch (key) {
//            case "1":
//                System.out.println("1");
//                return "1";
//            case "2":
//                System.out.println(2);
//                return "2";
//            case "3":
//                System.out.println("3");
//                return "3";
//            default:
//                System.out.println("返回默认值");
//                return "4";
//        }
    }

// ================================================================================================

    // 9. Arrays.asList的几个坑

    /**
     * 9.1 [基本类型] 不能作为 Arrays.asList方法的参数，否则会被当做一个参数。
     * 基本类型不能作为 Arrays.asList方法的参数，否则会被当做一个参数。
     */
    private static void arrayAsListTest() {
        // 错误的反例
        int[] array = {1, 2, 3};
        List list = Arrays.asList(array);
        System.out.println(list.size());

        // 返回结果：1

        // Arrays.asList源码如下：
        // public static <T> List<T> asList(T... a) {
        //    return new ArrayList<>(a);
        //}
    }

    /**
     * 9.2 Arrays.asList 返回的 List 不支持增删操作。
     */
    private static void arrayAsListTest2() {
        // 错误的反例
        String[] array = {"1", "2", "3"};
        List list = Arrays.asList(array);
        list.add("5");
        System.out.println(list.size());

        // 运行结果：
        // Exception in thread "main" java.lang.UnsupportedOperationException
        // at java.util.AbstractList.add(AbstractList.java:148)
        // at java.util.AbstractList.add(AbstractList.java:108)
        // at object.ArrayAsListTest.main(ArrayAsListTest.java:11)

        // Arrays.asList 返回的 List 并不是我们期望的 java.util.ArrayList，
        // 而是 Arrays 的内部类 ArrayList。
        // 内部类的ArrayList没有实现add方法，
        // 而是父类的add方法的实现，是会抛出异常的呢。
    }

    /**
     * 9.3 使用Arrays.asLis的时候，对原始数组的修改会影响到我们获得的那个List
     */
    private static void arrayAsListTest3() {
        // 错误的反例
        String[] arr = {"1", "2", "3"};
        List list = Arrays.asList(arr);
        arr[1] = "4";
        System.out.println("原始数组" + Arrays.toString(arr));
        System.out.println("list数组" + list);

        // 运行的结果：
        // 原始数组[1, 4, 3]
        // list数组[1, 4, 3]

        // 从运行结果可以看到，原数组改变，Arrays.asList转化来的list也跟着改变啦，
        // 大家使用的时候要注意一下哦，可以用new ArrayList(Arrays.asList(arr))包一下的。
    }

    // ===================================================================

    // 10. ArrayList.toArray() 强转的坑

    /**
     * 9.10.ArrayList.toArray() 强转的坑
     */
    private static void arrayAsListTest4() {
        // 错误的反例
        List<String> list = new ArrayList<String>(1);
        list.add("公众号：捡田螺的小男孩");
        // 类型转换异常
        String[] array21 = (String[]) list.toArray();

        // 因为返回的是Object类型，Object类型数组强转String数组，
        // 会发生ClassCastException。解决方案是，使用toArray()重载方法toArray(T[] a)

        // String[] array1 = list.toArray(new String[0]);//可以正常运行
    }

    // ====================================================================================================

    // 11. 异常使用的几个坑

    /**
     * 11.1.不要弄丢了你的堆栈异常信息
     */
    // 错误的打印方式：
//        public void wrong1(){
//            try {
//                readFile();
//            } catch (IOException e) {
//                //没有把异常e取出来，原始异常信息丢失
//                throw new RuntimeException("系统忙请稍后再试");
//            }
//        }
//
//        public void wrong2(){
//            try {
//                readFile();
//            } catch (IOException e) {
//                //只保留了异常消息，栈没有记录啦
//                log.error("文件读取错误, {}", e.getMessage());
//                throw new RuntimeException("系统忙请稍后再试");
//            }
//        }

    // 正确的打印方式:
//    public void right(){
//        try {
//            readFile();
//        } catch (IOException e) {
//            //把整个IO异常都记录下来，而不是只打印消息
//            log.error("文件读取错误", e);
//            throw new RuntimeException("系统忙请稍后再试");
//        }
//    }

    /**
     * 11.2 不要把异常定义为静态变量
     */
//    public void testStaticExeceptionOne{
//        try {
//            exceptionOne();
//        } catch (Exception ex) {
//            log.error("exception one error", ex);
//        }
//        try {
//            exceptionTwo();
//        } catch (Exception ex) {
//            log.error("exception two error", ex);
//        }
//    }
//
//    private void exceptionOne() {
//        //这里有问题
//        throw Exceptions.ONEORTWO;
//    }
//
//    private void exceptionTwo() {
//        //这里有问题
//        throw Exceptions.ONEORTWO;
//    }


    /**
     *  exceptionTwo抛出的异常，很可能是 exceptionOne的异常哦。正确使用方法，应该是new 一个出来。
     */
//    private void exceptionTwo() {
//        throw new BusinessException("业务异常", 0001);
//    }

    /**
     * 11.3 生产环境不要使用e.printStackTrace();
     * 因为它占用太多内存，造成锁死，并且，日志交错混合，也不易读。
     */
//    public void wrong(){
//        try {
//            readFile();
//        } catch (IOException e) {
//            // 生产环境别用它
//            e.printStackTrace();
//        }
//    }

    // 因为它占用太多内存，造成锁死，并且，日志交错混合，也不易读。正确使用如下：log.error("异常日志正常打印方式",e);

    /**
     * 11.4 线程池提交过程中，出现异常怎么办？
     */
//    public class ThreadExceptionTest {
//
//            ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//            IntStream.rangeClosed(1, 10).forEach(i -> executorService.submit(()-> {
//                        if (i == 5) {
//                            System.out.println("发生异常啦");
//                            throw new RuntimeException("error");
//                        }
//                        System.out.println("当前执行第几:" + Thread.currentThread().getName() );
//                    }
//            ));
//            executorService.shutdown();
//    }

    // 运行结果：
    // 当前执行第几:pool-1-thread-1
    //当前执行第几:pool-1-thread-2
    //当前执行第几:pool-1-thread-3
    //当前执行第几:pool-1-thread-4
    //发生异常啦
    //当前执行第几:pool-1-thread-6
    //当前执行第几:pool-1-thread-7
    //当前执行第几:pool-1-thread-8
    //当前执行第几:pool-1-thread-9
    //当前执行第几:pool-1-thread-10

    // 可以发现，如果是使用submit方法提交到线程池的异步任务，异常会被吞掉的，所以在日常发现中，如果会有可预见的异常，
    // 可以采取这几种方案处理：
    //1.在任务代码try/catch捕获异常
    //2.通过Future对象的get方法接收抛出的异常，再处理
    //3.为工作者线程设置UncaughtExceptionHandler，在uncaughtException方法中处理异常
    //4.重写ThreadPoolExecutor的afterExecute方法，处理传递的异常引用

    /**
     * 11.5 finally重新抛出的异常也要注意啦
     * <p>
     * 一个方法是不会出现两个异常的呢，所以finally的异常会把try的「异常覆盖」。
     * 正确的使用方式应该是，finally 代码块「负责自己的异常捕获和处理」。
     */
    // 错误如下：
    public void wrong() {
        try {
            log.info("try");
            // 异常丢失
            throw new RuntimeException("try");
        } finally {
            log.info("finally");
            throw new RuntimeException("finally");
        }
    }

    // 一个方法是不会出现两个异常的呢，所以finally的异常会把try的「异常覆盖」。正确的使用方式应该是，finally 代码块「负责自己的异常捕获和处理」。

    // 正确如下：
    public void right() {
        try {
            log.info("try");
            throw new RuntimeException("try");
        } finally {
            log.info("finally");
            try {
                throw new RuntimeException("finally");
            } catch (Exception ex) {
                log.error("finally", ex);
            }
        }
    }

    /**
     * 12.JSON序列化,Long类型被转成Integer类型！
     */
    private static void jSONTest() {

        Long idValue = 3000L;
        Map<String, Object> data = new HashMap<>(2);
        data.put("id", idValue);
        data.put("name", "捡田螺的小男孩");

        Assert.assertEquals(idValue, (Long) data.get("id"));
        String jsonString = JSON.toJSONString(data);

        // 反序列化时Long被转为了Integer
        Map map = JSON.parseObject(jsonString, Map.class);
        Object idObj = map.get("id");
        System.out.println("反序列化的类型是否为Integer：" + (idObj instanceof Integer));
        Assert.assertEquals(idValue, (Long) idObj);

        // 「运行结果：」
        // Exception in thread "main" 反序列化的类型是否为Integer：true
        // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.Long
        // at object.JSONTest.main(JSONTest.java:24)

        // 序列化为Json串后，Josn串是没有Long类型呢。而且反序列化回来如果也是Object接收，
        // 数字小于Interger最大值的话，给转成Integer啦！

    }


}
