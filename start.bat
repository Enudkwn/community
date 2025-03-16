@echo off

:: 启动容器并等待数据库初始化完成
docker-compose up -d

:: 清理旧构建并启动Spring Boot应用
mvn clean spring-boot:run -Dspring-boot.run.profiles=dev