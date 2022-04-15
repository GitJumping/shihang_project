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
改写前面讲解的 Starter，获取天气数据，使用响应式编程接口
[代码](https://github.com/GitJumping/shihang_project/tree/main)

- 切换分支
```shell
git switch jdk17
```

- 编译与打包
```shell
cd quarkus-weather-starter
mvn clean package
```

- 选择使用 Quarkus，开发模式运行
```shell
cd quarkus-weather-boot-starter
mvn quarkus:dev
```

- 同步调用实现原有请求
```shell
curl http://127.0.0.1:8080/weather/city/%E4%B8%8A%E6%B5%B7
curl http://127.0.0.1:8080/weather/city-weather
curl http://127.0.0.1:8080/weather/currentcity
```

- 响应式编程
```shell
curl http://127.0.0.1:8080/uniweather/city/%E4%B8%8A%E6%B5%B7
curl http://127.0.0.1:8080/uniweather/city-weather
curl http://127.0.0.1:8080/uniweather/currentcity
```
- 编译本地应用。graalvm需升级到21.3.0，按照日志提示命令运行`quarkus-weather-boot-starter-1.0-SNAPSHOT-runner`
```shell
export GRAALVM_HOME=graalvm-ce-java11-21.3.0
./mvnw package -Dnative
./mvnw verify -Pnative
quarkus-weather-starter/quarkus-weather-boot-starter/target/quarkus-weather-boot-starter-1.0-SNAPSHOT-runner 
```

# 第四次大作业
缓存API的使用
[代码在jdk17的分支上](https://github.com/GitJumping/shihang_project/tree/jdk17/starter)
```shell
mvn clean package
java -jar starter/weather-demo/target/weather-demo-0.1.jar
```
<br/>第一次执行耗时
```json lines
{
  "深圳访问了20次": 1663,
  "总访问了": 13233,
  "上海访问了40次": 3319,
  "广州访问了30次": 2439,
  "成都访问了10次": 1037,
  "北京访问了50次": 4773
}
```
<br/>第一次执行耗时
```json lines
{
  "深圳访问了20次": 1663,
  "总访问了": 13233,
  "上海访问了40次": 3319,
  "广州访问了30次": 2439,
  "成都访问了10次": 1037,
  "北京访问了50次": 4773
}
```
<br/>第一次执行耗时从缓存获取，很快返回