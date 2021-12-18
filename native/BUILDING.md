# Building sdljoystick4java JNI Natives

You can build the JNI native shared libraries using my janky first-ever CMake build file, with these
caveats:

1. The `CMakeLists.txt` only supports single-configuration generators (Makefiles or Ninja). Remember
   to add the `CMAKE_BUILD_TYPE` definition on the command-line.

2. There's currently no support for non-Windows platform (just because `CMakeLists.txt` doesn't yet
   include the proper include directories and link libraries -- it should be easy to add though).

3. You'll need to pass two cache variables in the configuration step:

   `SDLJOYSTICK4JAVA_JDK_HOME` -- path to the JDK installation you want to use
   
   `SDLJOYSTICK4JAVA_SDL_DIR` -- path to SDL directory (that has `include`, `lib/x86`, and `lib/x64`
   subdirectories)

If you want to use precompiled headers, pass `-D SDLJOYSTICK4JAVA_USE_PCH=TRUE` to `cmake`.

## Examples

```
cmake -D "SDLJOYSTICK4JAVA_JDK_HOME:PATH=c:\Program Files (x86)\Java\jdk1.7.0_261" ^
      -D "SDLJOYSTICK4JAVA_SDL_DIR:PATH=c:\Users\JP\Code\Tools\SDL2-2.0.12" ^
      -D CMAKE_BUILD_TYPE=Release ^
      -G "NMake Makefiles" ^
      -S . -B build32

cmake --build build32 --target sdljoystick4java
```

```
cmake -D "SDLJOYSTICK4JAVA_JDK_HOME:PATH=c:\Program Files\Java\jdk1.7.0_80" ^
      -D SDLJOYSTICK4JAVA_SDL_DIR:PATH=c:\Users\JP\Code\Tools\SDL2-2.0.12  ^
      -D SDLJOYSTICK4JAVA_USE_PCH=TRUE ^
      -D CMAKE_BUILD_TYPE=Release ^
      -G "Ninja" ^
      -S . -B build64

cmake --build build64  
```

<!-- :maxLineLen=100: -->  
