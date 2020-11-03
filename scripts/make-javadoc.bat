@ECHO OFF
SETLOCAL ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION

cd %~dp0..

SET output_dir=build\javadoc

IF NOT "%1" == "" (
    SET visibility=%1
    SET output_dir="!output_dir!-!visibility!"
    IF "!visibility!" EQU "private" SET linksrc=-linksource
) ELSE (
    SET visibility=protected
)

rmdir /S /Q %output_dir%
mkdir %output_dir%

javadoc -classpath lib\*;build\classes -%visibility% %linksrc% ^
        -sourcepath src -subpackages com.illcode.sdljoystick4java -exclude com.illcode.sdljoystick4java.test -d "%output_dir%" ^
        -windowtitle "sdljoystick4java Javadocs" -doctitle "sdljoystick4java Javadocs" -notimestamp

::    -overview src\overview.html ^

:END
ENDLOCAL
ECHO ON
@EXIT /B 0
