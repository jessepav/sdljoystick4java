# Building sdljoystick4java JNI Natives

## Windows

1. Set three environment variables to appropriate values:

   * `SDL_DIR` – path to SDL2 development libraries (i.e. where `lib` and `include` can be found)
   * `JDK_HOME_X86` – path to 32-bit JDK home
   * `JDK_HOME_X64` – path to 64-bit JDK home

2. Select appropriate solution:

   For Visual Studio 2019, use the `sdljoystick4java.sln` solution in the `native\sdljoystick4java`
   directory.

   For Visual Studio 2017, use the `sdljoystick4java.sln` solution in the
   `native\sdljoystick4java\vs2017` directory.

3. Then either run `devenv` and open the appropriate `sdljoystick4java.sln` solution, or,
   on the command line

       devenv sdljoystick4java.sln /build "Release|<arch>" /Project sdljoystick4java
   
   where `<arch>` is either `x86` or `x64`.
   
   DLLs will be placed in `native\sdljoystick4java\out\(Win32|x64)\Release`.
