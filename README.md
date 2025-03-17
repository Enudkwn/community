# 如何启动后端
- 运行项目前确保docker正在运行中
- 需要 JDK 17
- 需要Maven 3.6+

如有更新请执行以下命令
```shell
mvn clean package -DskipTests
```

进入项目根目录下执行：
```shell
start.bat
```

ctrl + c 退出项目, 并执行一下命令以退出docker:
```shell
docker-compose down
```
# 如何启动前端
- 运行前下载好npm

直接在整个项目根目录运行
```shell
startFront.bat
```
查询
http://localhost:5173/
