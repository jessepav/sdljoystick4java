@echo off

setlocal EnableExtensions

rem /build /rebuild /clean
if [%1] == [] (
	set COMMAND=/build
	goto arch
)
set COMMAND=%1

rem Either x86 or x64
:arch
if [%2] == [] (
	set ARCH=x64
	goto command
)
set ARCH=%2

:command

devenv %~dp0sdljoystick4java.sln %COMMAND% "Release|%ARCH%" /Project sdljoystick4java

endlocal
