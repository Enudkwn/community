@echo off

docker-compose up -d

timeout /t 1

java -jar target/community-1.0-SNAPSHOT.jar