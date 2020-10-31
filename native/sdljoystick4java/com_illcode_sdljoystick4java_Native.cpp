#pragma warning( disable : 26812)   // use of regular enums

#include "pch.h"
#include "com_illcode_sdljoystick4java_Native.h"

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_Native_getDirectByteBufferAddress(JNIEnv* env, jclass cls, jobject buffer) {
	return (jlong)(uintptr_t)env->GetDirectBufferAddress(buffer);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_Native_init(JNIEnv* env, jclass cls, jboolean useControllers) {
	Uint32 flags = SDL_INIT_JOYSTICK;
	if (useControllers)
		flags |= SDL_INIT_GAMECONTROLLER;
	SDL_InitSubSystem(flags);
	SDL_JoystickEventState(SDL_IGNORE);
	if (useControllers)
		SDL_GameControllerEventState(SDL_IGNORE);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_Native_cleanup(JNIEnv* env, jclass cls) {
	SDL_Quit();
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_Native_update(JNIEnv* env, jclass cls) {
	SDL_JoystickUpdate();
	// We don't need to call SDL_GameControllerUpdate(), because it itself simply calls SDL_JoystickUpdate()
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_Native_getError(JNIEnv* env, jclass cls) {
	const char* error = SDL_GetError();
	return env->NewStringUTF(error);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_Native_numJoysticks(JNIEnv* env, jclass cls) {
	return SDL_NumJoysticks();
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_Native_joystickOpen(JNIEnv* env, jclass cls, jint deviceIdx) {
	return (jlong)(uintptr_t)SDL_JoystickOpen(deviceIdx);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_Native_joystickClose(JNIEnv* env, jclass, jlong joystickPtr) {
	SDL_JoystickClose((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_Native_getInstanceId(JNIEnv* env, jclass cls, jlong joystickPtr) {
	SDL_Joystick* joystick = (SDL_Joystick*)(uintptr_t)joystickPtr;
	return (jint) SDL_JoystickInstanceID(joystick);
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_Native_joystickFromInstanceId(JNIEnv* env, jclass cls, jint instanceId) {
	return (jlong)(uintptr_t)SDL_JoystickFromInstanceID(instanceId);
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_Native_joystickName(JNIEnv* env, jclass cls, jlong joystickPtr) {

}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_Native_joystickNameForIndex(JNIEnv* env, jclass cls, jint idx) {

}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_Native_joystickNumAxes(JNIEnv* env, jclass cls, jlong joystickPtr) {

}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_Native_joystickNumButtons(JNIEnv* env, jclass cls, jlong joystickPtr) {

}

JNIEXPORT jshort JNICALL Java_com_illcode_sdljoystick4java_Native_joystickGetAxis(JNIEnv* env, jclass cls, jlong joystickPtr, jint axis) {

}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_Native_joystickGetButton(JNIEnv* env, jclass cls, jlong joystickPtr, jint button) {

}
