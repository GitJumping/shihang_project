## 如何启动并运行应用(参考的前人的作品)

* **docker-compose.yml**：【完整版】中间件部署脚本。相关中间件的安装脚本，可以通过执行`docker-compose -f docker-compose.yml up`命令安装所依赖的全部中间件；
* **docker-compose-light.yml**：【轻量版】中间件部署脚本（**测试开发与验证，用这个**），移除了非必要的中间件服务。相关中间件的安装脚本，可以通过执行`docker-compose -f docker-compose-light.yml up`命令安装所依赖的全部中间件；

### 第一步：启动中间件

1. 进入`environment`目录，执行`docker-compose -f docker-compose-light.yml up `启动中间件；
2. 需要停止所有容器时，请执行`docker-compose -f docker-compose-light.yml down`；
4. 需要重新创建所有容器时，请执行`docker-compose -f docker-compose-light.yml up --force-recreate`.

#### 关于数据库中库表的初始化

初始化脚本在`enviroment/config/mysql`  中，**docker-compose在安装完MYSQL之后，便会执行数据表初始化动作**。

```
.
├── config
│   └── mysql
│   	   └── init
│   	      ├── flash_sale_init.sql // 默认主库初始化语句
│   	      ├── flash_sale_init_sharding_0.sql // #0号数据库初始化语句
│   	      ├── flash_sale_init_sharding_1.sql // #1号数据库初始化语句
│   	      └── nacos_init.sql //Nacos持久化语句
├── docker-cluster-apps.yml
├── docker-cluster-middlewares.yml
├── docker-compose-light.yml
└── docker-compose.yml
```


有两份初始化脚本：`flash_sale_init.sql`和`nacos_init.sql`，前者是业务表初始化脚本，后者是Nacos的初始化脚本

### 第二步：通过IDE启动应用运行

在启用应用前，请务必确保已成功执行第一步，并且各中间件容器启动成功。
1. 执行`./mvnw clean install`完成系统依赖包的安装；
2. 选择`start`模块中的`com.actionworks.flashsale.FlashSaleApplication`作为程序入口运行。
>**特别提醒**
>本地启动时请在IDE中指定properties为`local`.

在调试阶段，推荐使用这种方式。FlashSale启动时，将会连接到前面所安装的中间件。

**可选：通过Docker启动运行**

除了在IDE启动FlashSale之外，通过Docker启动也是一种非常便捷的方案。

1. 通过下面的命令构建FlashSale本地镜像：

```shell
docker build -t flash-sale-app . 
```

构建完成后，通过`docker images`查看镜像。

2. 将下面的配置添加到前面的`docker-compose.yml`中，在运行中间的时候，也将同时启动系统。需要注意的是，在通过docker运行时，FlashSale将和中间件共处同一个网络中，在运行时需要指定`docker` 配置。

```yml
services:
  flash-sale-app:
    image: flash-sale-app
    container_name: flash-sale-app
    environment:
      - SPRING_PROFILES_ACTIVE=docker
    ports:
      - 8090:8090
    networks:
      - thoughts-beta-cluster-apps
    restart: on-failure
  flash-sale-gateway:
    image: flash-sale-gateway
    container_name: flash-sale-gateway
    environment:
      - SPRING_PROFILES_ACTIVE=docker
    ports:
      - 8080:8080
    networks:
      - thoughts-beta-cluster-apps
    restart: on-failure
```

## 测试接口

在项目的根目录下的`postman`目录，它是Postman的测试脚本，包含了接口定义和测试数据，直接选择某个接口点击测试。

Postman的脚本位置如下所示：

```shell
├── environment
│   ├── config
│   ├── data
│   ├── docker-compose.yml
│   └── grafana-storage
└── postman
    └── flash-sale-postman.json # 测试脚本
```