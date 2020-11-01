# Building sdljoystick4java JNI Natives

## Windows

Set three environment variables to appropriate values:

* `SDL_DIR` – path to SDL2 development libraries (i.e. where `lib` and `include` can be found)
* `JDK_HOME_X86` – path to 32-bit JDK home
* `JDK_HOME_X64` – path to 64-bit JDK home

Then either run `devenv` and open the `sdljoystick4java.sln` solution, or run `build.bat` as so:

```
build.bat /build <arch>
```

where `<arch>` is either `x86` or `x64`.
