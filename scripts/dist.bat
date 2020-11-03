@echo off

SETLOCAL ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION

if "%1" == "" (
    echo Usage: dist ^<version^>
    goto END
)

set VERSION=%1

cd %~dp0..

IF NOT EXIST dist MKDIR dist

call ant build.all.artifacts
@echo off
copy build\artifacts\jar\sdljoystick4java.jar dist\sdljoystick4java-%VERSION%.jar

call scripts\make-javadoc.bat
@echo off
jar cvMf dist\sdljoystick4java-%VERSION%-javadoc.jar -C build\javadoc\ .

jar cvMf dist\sdljoystick4java-%VERSION%-sources.jar -C src .

IF NOT EXIST dist\lib MKDIR dist\lib
IF NOT EXIST dist\resources MKDIR dist\resources
robocopy lib dist\lib /S
robocopy resources dist\resources /S
copy /Y native\sdljoystick4java\out\Win32\Release\sdljoystick4java.dll dist\lib\native\x86
copy /Y native\sdljoystick4java\out\x64\Release\sdljoystick4java.dll dist\lib\native\x64

:END
ENDLOCAL
ECHO ON
@EXIT /B 0
