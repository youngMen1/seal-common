## 模块说明
seal-commons: Apache Commons 工具包
seal-features: jdk8新特性
seal-googleguava: Google Guava
seal-hutool: hutool工具包
seal-jackson: jackson
seal-json: json工具包
seal-util: util工具类总结,BeanUtils提供对Java反射和自省API的包装
## jdk8新特性学习

## google-Guava
http://ifeve.com/google-guava-using-and-avoiding-null/

## jdk8

## jackson
Jackson是一个简单基于Java应用库，Jackson可以轻松的将Java对象转换成json对象和xml文档，同样也可以将json、xml转换成Java对象。Jackson所依赖的jar包较少，简单易用并且性能也要相对高些，并且Jackson社区相对比较活跃，更新速度也比较快。
### 特点
* 容易使用 - jackson API提供了一个高层次外观，以简化常用的用例。
* 无需创建映射 - API提供了默认的映射大部分对象序列化。
* 性能高 - 快速，低内存占用，适合大型对象图表或系统。
* 干净的JSON - jackson创建一个干净和紧凑的JSON结果，这是让人很容易阅读。
* 不依赖 - 库不需要任何其他的库，除了JDK。
* 开源代码 - jackson是开源的，可以免费使用。
### 三种方式处理JSON
提供了三种不同的方法来处理JSON
* 流式API - 读取并将JSON内容写入作为离散事件。 JsonParser读取数据，而JsonGenerator写入数据。它是三者中最有效的方法，是最低的开销和最快的读/写操作。它类似于Stax解析器XML。
* 树模型 - 准备JSON文件在内存里以树形式表示。 ObjectMapper构建JsonNode节点树。这是最灵活的方法。它类似于XML的DOM解析器。
* 数据绑定 - 转换JSON并从POJO（普通Java对象）使用属性访问或使用注释。它有两个类型。
* 简单的数据绑定 - 转换JSON和Java Maps, Lists, Strings, Numbers, Booleans 和null 对象。
* 全部数据绑定 - 转换为JSON从任何JAVA类型。
* ObjectMapper读/写JSON两种类型的数据绑定。数据绑定是最方便的方式是类似XML的JAXB解析器。


## fastJson
Fastjson是一个Java语言编写的高性能功能完善的JSON库。它采用一种“假定有序快速匹配”的算法，
把JSON Parse的性能提升到极致，是目前Java语言中最快的JSON库。Fastjson接口简单易用，
已经被广泛使用在缓存序列化、协议交互、Web输出、Android客户端等多种应用场景。
### 主要特点：
快速FAST (比其它任何基于Java的解析器和生成器更快，包括jackson）
强大（支持普通JDK类包括任意Java Bean Class、Collection、Map、Date或enum）
零依赖（没有依赖其它任何类库除了JDK）

Fastjson Obejct/Map/JSON/String 互转方法
在日志解析，前后端数据传输交互中，经常会遇到 String 与 map、json、xml 等格式相互转换与解析的场景，
其中 json 基本成为了跨语言、跨前后端的事实上的标准数据交互格式。应该来说各个语言中 
解析 json 的库都一大片（具体 json 格式与三方库的介绍请见： http://www.json.org/json-zh.html ），
比如 python 都集成在了内置库中，成为标准 API，今天我们要聊的是 java 中如何方便的使用 json 格式。
从上面的链接介绍中我们可以看到，java 的三方 json 库是最多的，可谓百花齐放百家争鸣的节奏。。。但是，某些库存在链式依赖的问题，
使用的时候要注意，如果你没用 maven 管你你的依赖，用起来会很痛苦，比如 json-lib、smart-json 等。
下面我要推荐的是阿里巴巴工程师开源的一个 json 库：FastJSON，这个库在解析速度和易用性上来说都很不错。

## @JSONField注解的使用
FastJson中的注解@JSONField，一般作用在get/set方法上面，常用的使用场景有下面三个：
修改和json字符串的字段映射【name】
格式化数据【format】
过滤掉不需要序列化的字段【serialize】
*  一、修改字段映射使用方法：
```
　　private Integer aid;
　　// 实体类序列化为json字符串的时候，此类的aid字段，序列化为json中的testid字段
　　@JSONField(name="testid") 
　　public Integer getAid() {
　　    return aid;
　　}

　　// json字符串解析为类实体的时候，json中的id字段，写入此类的aid字段
　　@JSONField(name="id")
　　public void setAid(Integer aid) {
　　    this.aid = aid;
　　}
```
*  二、格式化使用方法
```
　　@JSONField(format = "yyyy-MM-dd HH:mm:ss")
　　public Date getDateCompleted(...)
```
*  三、过滤不需要序列化的字段
```
　　@JSONField(serialize = false)
　　public Integer getProgress() {
    　　return progress;
　　}
```

## Hutool
一个Java基础工具类，对文件、流、加密解密、转码、正则、线程、XML等JDK方法进行封装，组成各种Util工具类，同时提供以下组件：
* 布隆过滤
* 缓存
* 克隆接口
* 类型转换
* 日期处理
* 数据库ORM（基于ActiveRecord思想）
* 基于DFA有限自动机的多个关键字查找
* HTTP客户端
* IO和文件
* 有用的一些数据结构
* 日志
* 反射代理类的简化（AOP切面实现）
* Setting（一种扩展Properties的配置文件）
* System（JVM和系统信息等）
* WatchService的封装（文件变动监控）
* XXXUtil各种有用的工具类

## 参考
