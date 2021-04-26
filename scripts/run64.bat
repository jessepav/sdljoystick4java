@echo off

setlocal EnableExtensions

cd %~dp0..

"c:\Program Files\Java\jdk1.7.0_261\bin\java.exe" -cp build\classes -Djava.library.path=lib\native\x64;native\antbuild\out\x64 ^
    com.illcode.sdljoystick4java.test.%1 %2 %3 %4 %5

endlocal
exit /B
