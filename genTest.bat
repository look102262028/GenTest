@echo off
set systemN=MOBILE
set jobNum=77779
set createday=1020511
set subject=需求內容
set testPeopleA=陳建璋
set testPeopleB=李建榮

D:
cd D:\workspace\Mobile\GenTest
REM COMPILER
javac -encoding utf-8 -d bin\ -cp "D:\workspace\Mobile\GenTest\lib\poi-4.1.2.jar;D:\workspace\Mobile\GenTest\lib\poi-ooxml-4.1.2.jar;D:\workspace\Mobile\GenTest\lib\poi-ooxml-schemas-4.1.2.jar;D:\workspace\Mobile\GenTest\lib\poi-scratchpad-4.1.2.jar;D:\workspace\Mobile\GenTest\lib\poi-4.1.2.jar;D:\workspace\Mobile\GenTest\lib\commons-compress-1.20.jar;D:\workspace\Mobile\GenTest\lib\commons-collections4-4.4.jar;" src\gen\WordGen.java
REM 切換到classes
cd D:\workspace\Mobile\GenTest\bin\
dir
REM 執行JAVA檔案
java -cp  "D:\workspace\Mobile\GenTest\lib\poi-4.1.2.jar;D:\workspace\Mobile\GenTest\lib\poi-ooxml-4.1.2.jar;D:\workspace\Mobile\GenTest\lib\poi-ooxml-schemas-4.1.2.jar;D:\workspace\Mobile\GenTest\lib\poi-scratchpad-4.1.2.jar;D:\workspace\Mobile\GenTest\lib\poi-4.1.2.jar;D:\workspace\Mobile\GenTest\lib\commons-compress-1.20.jar;D:\workspace\Mobile\GenTest\lib\commons-collections4-4.4.jar;C:\Users\lookwu\.m2\repository\org\apache\xmlbeans\xmlbeans\3.1.0\xmlbeans-3.1.0.jar;" gen.WordGen %systemN% %jobNum% %createday% %subject% %testPeopleA% %testPeopleB%

REM C:
REM cd C:\Users\lookwu\Documents\工作單\測試報告範本

pause
pause