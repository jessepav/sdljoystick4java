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