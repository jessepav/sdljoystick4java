@echo off

setlocal EnableExtensions

call setvars.bat

IF NOT EXIST build\x86 MKDIR build\x86
IF NOT EXIST out\x86 MKDIR out\x86

cl /c /I"%JDK_HOME_X86%\include" /I"%JDK_HOME_X86%\include\win32" /I"%SDL_DIR%\include" /W3 /WX- /diagnostics:classic /sdl- /O2 /Oi /Oy- /D WIN32 /D NDEBUG /D _WINDOWS /D _USRDLL /D _WINDLL /D _UNICODE /D UNICODE /EHsc /MD /Gy /fp:precise /permissive- /Zc:wchar_t /Zc:forScope /Zc:inline /std:c++14 /Fo"build\x86\\" /Gd /TP /analyze- com_illcode_sdljoystick4java_SdlNative.cpp

link /out:"out\x86\sdljoystick4java.dll" /INCREMENTAL:NO "%SDL_DIR%\lib\x86\SDL2.LIB" KERNEL32.LIB USER32.LIB GDI32.LIB WINSPOOL.LIB COMDLG32.LIB ADVAPI32.LIB SHELL32.LIB OLE32.LIB OLEAUT32.LIB UUID.LIB ODBC32.LIB ODBCCP32.LIB /MANIFEST /MANIFESTUAC:NO /manifest:embed /SUBSYSTEM:WINDOWS /OPT:REF /OPT:ICF /TLBID:1 /DYNAMICBASE /NXCOMPAT /IMPLIB:"out\x86\sdljoystick4java.lib" /MACHINE:X86 /SAFESEH /DLL "build\x86\com_illcode_sdljoystick4java_SdlNative.obj"

endlocal
exit /B
