## BeanUtils提供对Java反射和自省API的包装
【开宗明义】
1.无论是org.springframework.beans或者org.apache.commons.beanutils，与get/set方式相比，都存在性能问题。
2.效率由高到底：get/set 》PropertyUtils 》BeanUtils。
3.PropertyUtils和BeanUtils两个工具类都是对bean之间存在属性名相同的属性进行处理，无论是源bean或者是目标bean中多出来的属性均不处理。
4.具体来说：
BeanUtils.copyProperties()可以在一定范围内进行类型转换，同时还要注意一些不能转换时候，会将默认null值转化成0;
Property.copyProperties()则是严格的类型转化，必须类型和属性名完全一致才转化。
对于null的处理：PropertyUtils支持为null的场景；BeanUtils对部分属性不支持null，具体如下：
a.java.util.Date类型不支持,但是它的子类java.sql.Date是被支持的。java.util.Date直接copy会报异常；
b.Boolean，Integer，Long等不支持，会将null转化为0；
c.String支持，转化后依然为null。
4.BeanUtils的高级功能org.apache.commons.beanutils.Converter接口可以自定义类型转化，
也可以对部分类型数据的null值进行特殊处理，如ConvertUtils.register(new DateConverter(null), 
java.util.Date.class);但是PropertyUtils没有。
另外：值得注意的是，在测试过程中发现，commons-beanutils-1.8.0.jar版本中的BeanUtils类，
支持Byte到Integer或int的转化。说明实际使用过程中，我们还是要多看源码，多做测试，
并且注意版本号升级带来的微小变化。
