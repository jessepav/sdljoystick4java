@echo off

setlocal EnableExtensions

cd %~dp0..

"c:\Program Files (x86)\Java\jdk1.7.0_261\bin\java.exe" -cp build\classes -Djava.library.path=lib\native\x86;native\sdljoystick4java\out\Win32\Release ^
    com.illcode.sdljoystick4java.test.%1 %2 %3 %4 %5

endlocal
exit /B
