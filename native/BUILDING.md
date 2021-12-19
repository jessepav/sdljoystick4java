# Building sdljoystick4java JNI Natives

You can build the JNI native shared library using CMake.

## Finding Libraries

If CMake doesn't pick up the correct JNI and SDL directories, you can set some cache variables
at the command-line to direct the operation of [`find_package(JNI)`] and [`find_package(SDL)`].

`JAVA_AWT_LIBRARY`\
  the path to the Java AWT Native Interface (JAWT) library

`JAVA_JVM_LIBRARY`\
  the path to the Java Virtual Machine (JVM) library

`JAVA_INCLUDE_PATH`\
  the include path to jni.h

`JAVA_INCLUDE_PATH2`\
  the include path to jni_md.h and jniport.h

`JAVA_AWT_INCLUDE_PATH`\
  the include path to jawt.h

`SDL_INCLUDE_DIR`\
  where to find SDL.h

`SDL_LIBRARY`\
  the name of the library to link against

Setting multiple path cache variables on the command-line quickly becomes tedious, so you can
create an initial-cache file (see the examples in the `sample-initial-cache` folder), and pass
it to `cmake` using the `-C <initial-cache>` option.

## Precompiled Headers

To use precompiled headers, pass `-D SDLJOYSTICK4JAVA_USE_PCH=TRUE` to `cmake`.

## Examples

```
cmake -G "NMake Makefiles" -C initial-cache-win32-x86.cmake -S . -B build32
cmake --build build32 --target sdljoystick4java
```

```
cmake -G "Ninja" -C initial-cache-win32-x64.cmake -D SDLJOYSTICK4JAVA_USE_PCH=TRUE -S . -B build64
cmake --build build64  
```

[`find_package(JNI)`]: https://cmake.org/cmake/help/v3.21/module/FindJNI.html
[`find_package(SDL)`]: https://cmake.org/cmake/help/v3.21/module/FindSDL.html

<!-- :maxLineLen=100: -->  
