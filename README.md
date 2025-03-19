# 如何启动后端
- 运行项目前确保docker正在运行中
- 需要 JDK 17
- 需要Maven 3.6+

输入以下命令检查后端依赖
```shell
.\envCheck.bat
```

如项目有更新请执行以下命令
```shell
mvn clean package -DskipTests
```

进入项目根目录下执行：
```shell
java -jar target/community-1.0-SNAPSHOT.jar
```

ctrl + c 退出项目, 并执行一下命令以退出docker:
```shell
docker-compose down
```
# 如何启动前端
- 运行前下载好node和npm

输入以下命令检查前端依赖
```shell
.\envCheckFront.bat
```

直接在整个项目根目录执行以下命令
```shell
cd .\community-front
```
```shell
npm install
```
```shell
npm audit fix
```
```shell
npm run dev
```
查询
http://localhost:5173/

# 文件结构
```text
\community
├── community-front/             # 前端工程
│   ├── src/
│   │   ├── api/                # 接口定义
│   │   │   └── post.js         # 帖子接口
│   │   ├── views/
│   │   │   ├── index/          # 首页模块
│   │   │   │   └── index.vue   # 主界面 
│   │   │   ├── user/           # 用户中心
│   │   │   │   └── ChangePassword.vue  # 密码修改
│   │   │   └── post/           # 帖子模块
│   ├── vite.config.js          # 构建配置
│   └── package.json            # 前端依赖
├── src/
│   ├── main/
│   │   ├── java/com/community/
│   │   │   ├── controller/     # 控制器层
│   │   │   │   ├── PostController.java  # 帖子控制 
│   │   │   │   └── UserController.java  # 用户控制
│   │   │   ├── service/        # 服务层
│   │   │   │   └── impl/ReplyServiceImpl.java  # 评论服务
│   │   │   ├── mapper/         # 数据访问层
│   │   │   │   └── PostMapper.java  # 帖子Mapper
│   │   │   └── pojo/           # 数据对象
│   │   │       ├── Result.java # 统一响应格式
│   │   │       └── PostQueryParam.java  # 查询参数
│   │   └── resources/
│   │       ├── application.yml # Spring配置
│   │       └── mybatis-config.xml # MyBatis配置
├── target/                     # Maven构建输出
│   └── community-1.0-SNAPSHOT.jar # 可执行JAR
├── docker-compose.yml          # 容器编排配置
├── init.sql                    # 数据库初始化脚本
├── pom.xml                     # Maven配置
├── README.md                   # 项目说明
└── envCheck.bat                # 环境检查脚本
```



