@echo off

setlocal EnableExtensions

javah -d %~dp0../native/sdljoystick4java -classpath %~dp0../build/classes -jni -v com.illcode.sdljoystick4java.SdlNative
copy /Y %~dp0..\native\sdljoystick4java\com_illcode_sdljoystick4java_SdlNative.h %~dp0..\native\antbuild

endlocal
exit /B
