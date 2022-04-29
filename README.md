# Java训练营代码

## Maven 编译和运行

* 安装 JDK 8以上
* 安装 Apache Maven 3.6 以上
* 项目使用 `mvn compile` 编译
* 使用 `mvn exec:java -Dexec.mainClass="geektime.nio.CharSetDemo"` 执行测试
* `Dexec.args='XXX'` 提供运行参数

## 介绍
极客时间Java提薪营

### 并发
1. Java并发和多线程
2. 并发集合
3. 线程并发竞赛
 - 程序中用单线程完成了生成随机数成绩、和排序取前十名，这样的业务
 - 另外简单实现了使用多个线程并发生成随机数成绩（采用加锁），并使用多个线程进行排序计算（采用线程安全集合）
 - 希望重新设计程序，运用高效能集合和算法，更快的处理业务逻辑

### NIO
1. Java NIO
2. Netty


# 第一次大作业
值最小的修改<br/>
[MySimpleDivideCompute](https://github.com/GitJumping/shihang_project/blob/main/src/main/java/geektime/concurrent/race/MySimpleDivideCompute.java)
- ``geektime.concurrent.race.MySimpleDivideCompute.extracted2()方法``，采用subList方式截取，可以获得最小的比值，`0.0x~0.1`
- ``geektime.concurrent.race.MySimpleDivideCompute.extracted1()方法``，修改indexOf()为get()，并且每次拿取top10，可以获得次一点的比值，`0.1xs`
- ``geektime.concurrent.race.MySimpleDivideCompute.extracted()方法``，原始的计算方法，`15+s`

[MySimpleSyncGen](https://github.com/GitJumping/shihang_project/blob/main/src/main/java/geektime/concurrent/race/MySimpleSyncGen.java)
  ``gen()方法，但是效果不大``


```java
mvn compile
mvn exec:java -Dexec.mainClass="geektime.concurrent.race.ThreadRace"
```
<br/>
直接运行脚本

```shell
./randomRace.sh
```

# 第二次大作业
感兴趣的JEP特性<br/>
[jep-conclude.md](https://github.com/GitJumping/shihang_project/blob/main/jep-conclude.md)

# 第三次大作业
[第三次大作业](https://github.com/GitJumping/shihang_project/tree/jdk17#%E7%AC%AC%E4%B8%89%E6%AC%A1%E5%A4%A7%E4%BD%9C%E4%B8%9A)

# 第四次大作业
[第四次大作业（缓存API的使用）](https://github.com/GitJumping/shihang_project/tree/jdk17#%E7%AC%AC%E5%9B%9B%E6%AC%A1%E5%A4%A7%E4%BD%9C%E4%B8%9A)

# 第五次作业
[第五次作业](https://github.com/GitJumping/shihang_project#%E7%AC%AC%E4%BA%94%E6%AC%A1%E4%BD%9C%E4%B8%9A)