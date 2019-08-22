package com.seal.commons.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/22 15:58
 * @description 用于对Java类的操作（有很多方法还是挺有用的）
 **/
public class ClassUtilsTest {
    public static void main(String[] args) {
        getShortClassName();
        isPrimitiveOrWrapper();
    }

    public static void getShortClassName() {
        // int[]
        System.out.println(int[].class.getSimpleName());
        // int[]
        System.out.println(ClassUtils.getShortClassName(int[].class));
        // String
        System.out.println(ClassUtils.getShortClassName(String.class));
        // ArrayList
        System.out.println(ClassUtils.getShortClassName(ArrayList.class));
        // List
        System.out.println(ClassUtils.getShortClassName("List"));

        /**
         * getPackageName：获取包名
         */
        //""
        System.out.println(ClassUtils.getPackageName(int[].class));
        // java.lang
        System.out.println(ClassUtils.getPackageName(String.class));


        /**
         * getAllSuperclasses：获取到该类的所有父类 注意：只是父类 不包含接口
         */
        List<Class<?>> allSuperclasses = ClassUtils.getAllSuperclasses(ArrayList.class);
        // [class java.util.AbstractList, class java.util.AbstractCollection, class java.lang.Object]
        System.out.println(ArrayUtils.toString(allSuperclasses));


        List<Class<?>> allSuperclasses2 = ClassUtils.getAllSuperclasses(ArrayList.class);
        // [class java.util.AbstractList, class java.util.AbstractCollection, class java.lang.Object]
        System.out.println(ArrayUtils.toString(allSuperclasses2));
        allSuperclasses2 = ClassUtils.getAllSuperclasses(Object.class);
        // []
        System.out.println(ArrayUtils.toString(allSuperclasses2));


        /**
         * convertClassNamesToClasses/convertClassesToClassNames 见名知意
         */
        List<Class<?>> classes = ClassUtils.convertClassNamesToClasses(Arrays.asList("java.lang.Integer", "java.lang.int"));
        // [class java.lang.Integer, null]
        System.out.println(classes);

    }

    /**
     * isAssignable：是否是相同的class类型 支持class、数组等等 挺实用的
     *
     * isInnerClass：检查一个类是否是内部类或者静态内部类等
     *
     * getClass：加强版的Class.forName() 可以指定值是否要马上初始化该类
     *
     * hierarchy：获取到该类的继承结构
     */
    public static void isPrimitiveOrWrapper(){
        /**
         * isPrimitiveOrWrapper、isPrimitiveWrapper 、primitiveToWrapper、primitivesToWrappers、wrapperToPrimitive判断是基本类型还是包装类型
         */
        // true
        System.out.println(ClassUtils.isPrimitiveOrWrapper(Integer.class));
        // true
        System.out.println(ClassUtils.isPrimitiveOrWrapper(int.class));

        //检测是否是包装类型  false 注意 此处是false
        System.out.println(ClassUtils.isPrimitiveWrapper(Object.class));
        // true
        System.out.println(ClassUtils.isPrimitiveWrapper(Integer.class));
        // false
        System.out.println(ClassUtils.isPrimitiveWrapper(int.class));

        //检测是否是基本类型 false 注意 此处也是false
        System.out.println(Object.class.isPrimitive());
        // false
        System.out.println(Integer.class.isPrimitive());
        // true
        System.out.println(int.class.isPrimitive());

        /**
         * isAssignable：是否是相同的class类型 支持class、数组等等 挺实用的
         *
         * isInnerClass：检查一个类是否是内部类或者静态内部类等
         *
         * getClass：加强版的Class.forName() 可以指定值是否要马上初始化该类
         *
         * hierarchy：获取到该类的继承结构
         */
        Iterable<Class<?>> hierarchy = ClassUtils.hierarchy(ArrayList.class);
        hierarchy.forEach(System.out::println);


        //输出了类的层级结构（默认是不包含接口的）
        //class java.util.ArrayList
        //class java.util.AbstractList
        //class java.util.AbstractCollection
        //class java.lang.Object
        hierarchy = ClassUtils.hierarchy(ArrayList.class,ClassUtils.Interfaces.INCLUDE);
        hierarchy.forEach(System.out::println);
        //class java.util.ArrayList
        //interface java.util.List
        //interface java.util.Collection
        //interface java.lang.Iterable
        //interface java.util.RandomAccess
        //interface java.lang.Cloneable
        //interface java.io.Serializable
        //class java.util.AbstractList
        //class java.util.AbstractCollection
        //class java.lang.Object
    }

}
