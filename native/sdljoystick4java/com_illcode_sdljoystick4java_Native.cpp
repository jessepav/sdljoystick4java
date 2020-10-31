#pragma warning( disable : 26812)   // use of regular enums

#include "pch.h"
#include "com_illcode_sdljoystick4java_Native.h"

#define SDL_MAIN_HANDLED
#include <SDL.h>

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_Native_init(JNIEnv*, jclass) {
	SDL_InitSubSystem(SDL_INIT_JOYSTICK | SDL_INIT_GAMECONTROLLER);
	SDL_JoystickEventState(SDL_IGNORE);
	SDL_GameControllerEventState(SDL_IGNORE);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_Native_cleanup(JNIEnv*, jclass) {
	SDL_Quit();
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_Native_update(JNIEnv*, jclass) {
	SDL_JoystickUpdate();
	SDL_GameControllerUpdate();
}


JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_Native_getDirectByteBufferAddress(JNIEnv * env, jclass cls, jobject buffer) {
	return (jlong)(intptr_t) env->GetDirectBufferAddress(buffer);
}