@echo off

setlocal EnableExtensions

javah -d %~dp0../native/sdljoystick4java -classpath %~dp0../build/classes -jni -v com.illcode.sdljoystick4java.Native

endlocal
exit /B