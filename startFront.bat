@echo off

timeout /t 10

npm install

npm audit fix

npm run dev

pause