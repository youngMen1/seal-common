package com.seal.test.nullpointer;

import com.seal.test.entity.User;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.1包装类型的空指针问题
 *
 * @author fengzhiqiang
 * @date 2021/5/18 10:03
 **/
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


}
