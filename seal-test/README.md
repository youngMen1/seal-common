# 各种业务学习
## 1.异常处理
try catch 有多烦人，我就有多暴躁！一次搞定 Exception
```
https://mp.weixin.qq.com/s/NLpf6LjqPhPgveLVfi3cYQ
```
## 2.极客时间的《Java业务开发常见错误100例》
### 2.1.六类典型空指针问题
1.包装类型的空指针问题
2.级联调用的空指针问题
3.Equals方法左边的空指针问题
4.ConcurrentHashMap 这样的容器不支持 Key，Value 为 null。
5.集合，数组直接获取元素
6.对象直接获取属性
### 2.2.日期YYYY格式设置的坑
1. 日期大写YYYY格式设置的坑  正确的是小写yyyy-MM-dd HH:mm:ss

### 2.3.金额数值计算精度的坑
### 2.4.FileReader默认编码导致乱码问题
### 2.5.Integer缓存的坑
### 2.6.static静态变量依赖spring实例化变量，可能导致初始化出错
### 2.7.使用ThreadLocal，线程重用导致信息错乱的坑
### 2.8.疏忽switch的return和break
### 2.9.Arrays.asList的几个坑
#### 2.9.1.基本类型不能作为 Arrays.asList方法的参数，否则会被当做一个参数。
#### 9.9.2.Arrays.asList 返回的 List 不支持增删操作。
#### 9.9.3.ArrayList.toArray() 强转的坑

### 3.0.异常使用的几个坑
1.不要弄丢了你的堆栈异常信息
错误的打印方式：
~~~
public void wrong1(){
try {
readFile();
} catch (IOException e) {
//没有把异常e取出来，原始异常信息丢失  
throw new RuntimeException("系统忙请稍后再试");
}
}

public void wrong2(){
try {
readFile();
} catch (IOException e) {
//只保留了异常消息，栈没有记录啦
log.error("文件读取错误, {}", e.getMessage());
throw new RuntimeException("系统忙请稍后再试");
}
~~~

正确的打印方式：
~~~
try {
readFile();
} catch (IOException e) {
//把整个IO异常都记录下来，而不是只打印消息
log.error("文件读取错误", e);
throw new RuntimeException("系统忙请稍后再试");
}
~~~

## 15. 先查询，再更新/删除的并发一致性问题

在日常开发中，这种代码实现经常可见：先查询是否有剩余可用的票，再去更新票余量。

`
if(selectIsAvailable(ticketId){
1、deleteTicketById(ticketId)
2、给现金增加操作
}else{
return “没有可用现金券”
}`

如果是**并发执行**，很可能有问题的，应该利用数据库的更新/删除的原子性，正解如下：
`
if(deleteAvailableTicketById(ticketId) == 1){
1、给现金增加操作
}else{
return “没有可用现金券”
}`

## 16. 数据库使用utf-8存储， 插入表情异常的坑
低版本的MySQL支持的utf8编码，最大字符长度为 3 字节，但是呢，存储表情需要4个字节，
因此如果用utf8存储表情的话，
会报SQLException: Incorrect string value: '\xF0\x9F\x98\x84' for column，
所以一般用utf8mb4编码去存储表情。

## 17. 事务未生效的坑
日常业务开发中，我们经常跟事务打交道，「事务失效」主要有以下几个场景：
1.底层数据库引擎不支持事务
2.在非public修饰的方法使用
3.rollbackFor属性设置错误
4.本类方法直接调用
5.异常被try...catch吃了，导致事务失效。

### 参考
1.六类典型空指针问题
https://mp.weixin.qq.com/s/Y_xeiHfdMfj043mGLIni1A