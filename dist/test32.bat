@echo off

setlocal EnableExtensions

cd %~dp0

for /f "delims=" %%a in ('dir /b sdljoystick4java-?.?.?.jar') do set "jar=%%a"
java.exe -cp %jar% -Djava.library.path=lib\native\x86 com.illcode.sdljoystick4java.test.BasicTests %1 %2 %3 %4 %5

endlocal
exit /B
