@echo off
set day=2023/05/22
set system=TCBBMNB_WEB
set fileNum=78
set codeNum=996

D:
cd D:\workspace\Mobile\GenTest
REM COMPILER
javac -encoding utf-8 -d bin\ -cp "D:\workspace\Mobile\GenTest\lib\itext-xtra-5.5.13.2.jar;D:\workspace\Mobile\GenTest\lib\itextpdf-5.5.13.1.jar;D:\workspace\Mobile\GenTest\lib\itext-asian-5.2.0.jar;" src\pdf\PdfConversionMain.java
REM ¤Á´«¨ìclasses
cd D:\workspace\Mobile\GenTest\bin\
dir
REM °õ¦æJAVAÀÉ®×
java -cp  "D:\workspace\Mobile\GenTest\lib\itext-xtra-5.5.13.2.jar;D:\workspace\Mobile\GenTest\lib\itextpdf-5.5.13.1.jar;D:\workspace\Mobile\GenTest\lib\itext-asian-5.2.0.jar;" pdf.PdfConversionMain %day% %system% %fileNum% %codeNum%

cd D:\workspace\Mobile\GenTest

pause
pause