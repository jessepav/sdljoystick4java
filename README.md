# sdljoystick4java

JNI bindings for the Joystick & GameController functions of SDL.

It binds all of the [`SDL_Joystick` API] (with the exception of `SDL_JoystickEventState`,
which is used with the SDL event loop), and the "user-facing" functions of the
[`SDL_GameController` API]:

    SDL_GameControllerAddMappingsFromFile
    SDL_IsGameController
    SDL_GameControllerOpen
    SDL_GameControllerFromInstanceID
    SDL_GameControllerClose
    SDL_GameControllerGetAttached
    SDL_GameControllerGetJoystick
    SDL_GameControllerName
    SDL_GameControllerNameForIndex
    SDL_GameControllerGetAxis
    SDL_GameControllerGetButton

[`SDL_Joystick` API]: https://wiki.libsdl.org/CategoryJoystick
[`SDL_GameController` API]: https://wiki.libsdl.org/CategoryGameController

## Why?

The most prominent libraries that bind SDL's joystick/gamepad API to Java ([sdl2gdx] and
[jamepad]) are meant for use with LibGDX, and pull in parts of LibGDX as compile-time
dependencies. Also, completely anectdotally (though it *is* what actually got me started
writing my own binding), `sdl2gdx` reported the wrong number of buttons on my cheapo Retro
SNES Controller, so I couldn't use the right shoulder button.

Finally, `sdljoystick4java` has a requirement on Java 7 instead of Java 8.

[sdl2gdx]: https://github.com/electronstudio/sdl2gdx
[jamepad]: https://github.com/williamahartman/Jamepad

## Usage

Grab the latest distribution ZIP from [releases] — it has library, source, and javadoc jars,
in addition to native DLLs for Windows x86 & x64.

Note that this library is *not* one of those that auto-extracts and loads native DLLs
based on system architecture probing: I've never liked that method, because it makes it
hard to update the native libraries without waiting for the project maintainer to put out
a new release.

Instead, set `java.library.path` to the directory containing `sdljoystick4java.dll` and
`SDL2.dll` (or your platform equivalents) and call [`SdlNative.loadNative()`] <!-- ` -->
before calling any other methods.

If you're running Windows, you can run either `test32.bat` or `test64.bat` (for 32-bit and
64-bit JVMs respectively) for a simple test of the first joystick or game controller on
your system.

[releases]: https://github.com/jessepav/sdljoystick4java/releases
[`SdlNative.loadNative()`]: https://github.com/jessepav/sdljoystick4java/blob/master/src/com/illcode/sdljoystick4java/SdlNative.java#L15

## API

The Javadocs are available [online][javadocs], and [`BasicTests.java`] shows how you might
use the library, including native initialization and cleanup.

[javadocs]: https://jessepav.github.io/sdljoystick4java/javadoc/
[`BasicTests.java`]: https://github.com/jessepav/sdljoystick4java/blob/master/src/com/illcode/sdljoystick4java/test/BasicTests.java

### Polling

`sdljoystick4java` does not use the SDL event loop — instead, you'll need to poll the
joystick/controller state on each iteration of your game loop. You do this by calling the
`update()` method of each [`Joystick`][jupdate] and [`GameController`][gcupdate] that
you've opened. *Once* each polling cycle you'll need to update the native joystick state –
you can do this by passing `true` to one of the above `update()` methods, or calling
[`SdlNative.update()`] <!-- ` --> directly.

[jupdate]: https://jessepav.github.io/sdljoystick4java/javadoc/com/illcode/sdljoystick4java/Joystick.html#update(boolean)
[gcupdate]: https://jessepav.github.io/sdljoystick4java/javadoc/com/illcode/sdljoystick4java/GameController.html#update(boolean)
[`SdlNative.update()`]: https://jessepav.github.io/sdljoystick4java/javadoc/com/illcode/sdljoystick4java/SdlNative.html#update()


## Other Platforms

I built and tested this library on Windows, but the JNI code is dead simple, comprising
only two files in the `native/sdljoystick4java` directory: [`com_illcode_sdljoystick4java_SdlNative.h`][SdlNative.h]
and [`com_illcode_sdljoystick4java_SdlNative.cpp`][SdlNative.cpp].

If you set up build scripts for other platforms, I'll happily integrate the PRs.

[SdlNative.h]: https://github.com/jessepav/sdljoystick4java/blob/master/native/sdljoystick4java/com_illcode_sdljoystick4java_SdlNative.h
[SdlNative.cpp]: https://github.com/jessepav/sdljoystick4java/blob/master/native/sdljoystick4java/com_illcode_sdljoystick4java_SdlNative.cpp
