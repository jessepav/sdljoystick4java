#pragma warning( disable : 26812)   // use of regular enums

#include "pch.h"
#include "com_illcode_sdljoystick4java_SdlNative.h"

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_getDirectByteBufferAddress(JNIEnv* env, jclass cls, jobject buffer) {
	return (jlong)(uintptr_t)env->GetDirectBufferAddress(buffer);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_initJoysticks(JNIEnv* env, jclass cls) {
	SDL_InitSubSystem(SDL_INIT_JOYSTICK);
	SDL_JoystickEventState(SDL_IGNORE);

}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_initGameControllers(JNIEnv* env, jclass cls) {
	SDL_InitSubSystem(SDL_INIT_GAMECONTROLLER);
	SDL_GameControllerEventState(SDL_IGNORE);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_cleanup(JNIEnv* env, jclass cls) {
	SDL_Quit();
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_update(JNIEnv* env, jclass cls) {
	SDL_JoystickUpdate();
	// We don't need to call SDL_GameControllerUpdate(), because it itself simply calls SDL_JoystickUpdate()
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_getError(JNIEnv* env, jclass cls) {
	const char* error = SDL_GetError();
	return env->NewStringUTF(error);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_numJoysticks(JNIEnv* env, jclass cls) {
	return SDL_NumJoysticks();
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickOpen(JNIEnv* env, jclass cls, jint deviceIdx) {
	return (jlong)(uintptr_t)SDL_JoystickOpen(deviceIdx);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickClose(JNIEnv* env, jclass, jlong joystickPtr) {
	SDL_JoystickClose((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetAttached(JNIEnv* env, jclass cls, jlong joystickPtr) {
	return SDL_JoystickGetAttached((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_getInstanceId(JNIEnv* env, jclass cls, jlong joystickPtr) {
	SDL_Joystick* joystick = (SDL_Joystick*)(uintptr_t)joystickPtr;
	return (jint) SDL_JoystickInstanceID(joystick);
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickFromInstanceId(JNIEnv* env, jclass cls, jint instanceId) {
	return (jlong)(uintptr_t)SDL_JoystickFromInstanceID(instanceId);
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickName(JNIEnv* env, jclass cls, jlong joystickPtr) {
	const char* name = SDL_JoystickName((SDL_Joystick*)(uintptr_t)joystickPtr);
	if (name == NULL)
		return NULL;
	return env->NewStringUTF(name);
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNameForIndex(JNIEnv* env, jclass cls, jint idx) {
	const char* name = SDL_JoystickNameForIndex(idx);
	if (name == NULL)
		return NULL;
	return env->NewStringUTF(name);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNumAxes(JNIEnv* env, jclass cls, jlong joystickPtr) {
	return SDL_JoystickNumAxes((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNumButtons(JNIEnv* env, jclass cls, jlong joystickPtr) {
	return SDL_JoystickNumButtons((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jshort JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetAxis(JNIEnv* env, jclass cls, jlong joystickPtr, jint axis) {
	return SDL_JoystickGetAxis((SDL_Joystick*)(uintptr_t)joystickPtr, axis);
}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetButton(JNIEnv* env, jclass cls, jlong joystickPtr, jint button) {
	return SDL_JoystickGetButton((SDL_Joystick*)(uintptr_t)joystickPtr, button) == 1 ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerAddMappingsFromFile(JNIEnv* env, jclass cls, jstring filename) {
	const char* cstr = env->GetStringUTFChars(filename, NULL);
	int numMappings = SDL_GameControllerAddMappingsFromFile(cstr);
	env->ReleaseStringUTFChars(filename, cstr);
	return (jint) numMappings;
}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_isGameController(JNIEnv* env, jclass cls, jint deviceIdx) {
	return SDL_IsGameController(deviceIdx);
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerOpen(JNIEnv* env, jclass cls, jint deviceIdx) {
	return (jlong)(uintptr_t)SDL_GameControllerOpen(deviceIdx);
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerFromInstanceId(JNIEnv* env, jclass cls, jint instanceId) {
	return (jlong)(uintptr_t)SDL_GameControllerFromInstanceID(instanceId);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerClose(JNIEnv* env, jclass cls, jlong gameControllerPtr) {
	SDL_GameControllerClose((SDL_GameController*)(uintptr_t)gameControllerPtr);
}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetAttached(JNIEnv* env, jclass cls, jlong gameControllerPtr) {
	return SDL_GameControllerGetAttached((SDL_GameController*)(uintptr_t)gameControllerPtr);
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetJoystick(JNIEnv* env, jclass cls, jlong gameControllerPtr) {
	return (jlong)(uintptr_t)SDL_GameControllerGetJoystick((SDL_GameController*)(uintptr_t)gameControllerPtr);
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerName(JNIEnv* env, jclass cls, jlong gameControllerPtr) {
	const char* name = SDL_GameControllerName((SDL_GameController*)(uintptr_t)gameControllerPtr);
	if (name == NULL)
		return NULL;
	return env->NewStringUTF(name);
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerNameForIndex(JNIEnv* env, jclass cls, jint deviceIdx) {
	const char* name = SDL_GameControllerNameForIndex(deviceIdx);
	if (name == NULL)
		return NULL;
	return env->NewStringUTF(name);
}

JNIEXPORT jshort JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetAxis(JNIEnv* env, jclass cls, jlong gameControllerPtr, jint axis) {
	return SDL_GameControllerGetAxis((SDL_GameController*)(uintptr_t)gameControllerPtr, (SDL_GameControllerAxis) axis);
}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetButton(JNIEnv* env, jclass cls, jlong gameControllerPtr, jint button) {
	return SDL_GameControllerGetButton((SDL_GameController*)(uintptr_t)gameControllerPtr, (SDL_GameControllerButton)button) == 1 ? JNI_TRUE : JNI_FALSE;
}
