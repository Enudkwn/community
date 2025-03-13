@echo off

:: 启动容器并自动执行init.sql
docker-compose up -d

:: 启动Spring Boot应用
mvn spring-boot:run

:: 清理命令（按Ctrl+C停止服务后执行）
docker-compose down