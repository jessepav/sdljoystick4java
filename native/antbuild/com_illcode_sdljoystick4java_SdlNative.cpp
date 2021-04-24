#pragma warning( disable : 26812 26451)   // use of regular enums

#ifdef WIN32
	#define WIN32_LEAN_AND_MEAN             // Exclude rarely-used stuff from Windows headers
	#include <windows.h>
#endif

#include <stdint.h>
#include <string.h>

#define SDL_MAIN_HANDLED
#include <SDL.h>

#include "com_illcode_sdljoystick4java_SdlNative.h"

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_getPointerSize
(JNIEnv *env, jclass cls) {
	return (jint)sizeof(void *);
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_getDirectByteBufferAddress
(JNIEnv* env, jclass cls, jobject buffer) {
	return (jlong)(uintptr_t)env->GetDirectBufferAddress(buffer);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_initJoysticks
(JNIEnv* env, jclass cls) {
	SDL_InitSubSystem(SDL_INIT_JOYSTICK);
	SDL_JoystickEventState(SDL_IGNORE);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_initGameControllers
(JNIEnv* env, jclass cls) {
	SDL_InitSubSystem(SDL_INIT_GAMECONTROLLER);
	SDL_GameControllerEventState(SDL_IGNORE);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_cleanup
(JNIEnv* env, jclass cls) {
	SDL_Quit();
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_update
(JNIEnv* env, jclass cls) {
	SDL_JoystickUpdate();
	// We don't need to call SDL_GameControllerUpdate(), because it itself simply calls SDL_JoystickUpdate()
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_getError
(JNIEnv* env, jclass cls) {
	const char* error = SDL_GetError();
	return env->NewStringUTF(error);
}

// ---------------- Joystick functions -------------------------------


JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_numJoysticks
(JNIEnv* env, jclass cls) {
	return SDL_NumJoysticks();
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickOpen
(JNIEnv* env, jclass cls, jint deviceIdx) {
	return (jlong)(uintptr_t)SDL_JoystickOpen(deviceIdx);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickClose
(JNIEnv* env, jclass, jlong joystickPtr) {
	SDL_JoystickClose((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetAttached
(JNIEnv* env, jclass cls, jlong joystickPtr) {
	return SDL_JoystickGetAttached((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_getInstanceId
(JNIEnv* env, jclass cls, jlong joystickPtr) {
	SDL_Joystick* joystick = (SDL_Joystick*)(uintptr_t)joystickPtr;
	return (jint) SDL_JoystickInstanceID(joystick);
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickFromInstanceId
(JNIEnv* env, jclass cls, jint instanceId) {
	return (jlong)(uintptr_t)SDL_JoystickFromInstanceID(instanceId);
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickName
(JNIEnv* env, jclass cls, jlong joystickPtr) {
	const char* name = SDL_JoystickName((SDL_Joystick*)(uintptr_t)joystickPtr);
	if (name == NULL)
		return NULL;
	return env->NewStringUTF(name);
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNameForIndex
(JNIEnv* env, jclass cls, jint idx) {
	const char* name = SDL_JoystickNameForIndex(idx);
	if (name == NULL)
		return NULL;
	return env->NewStringUTF(name);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNumAxes
(JNIEnv* env, jclass cls, jlong joystickPtr) {
	return SDL_JoystickNumAxes((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNumButtons
(JNIEnv* env, jclass cls, jlong joystickPtr) {
	return SDL_JoystickNumButtons((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNumBalls
(JNIEnv* env, jclass cls, jlong joystickPtr) {
	return SDL_JoystickNumBalls((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNumHats
(JNIEnv* env, jclass cls, jlong joystickPtr) {
	return SDL_JoystickNumHats((SDL_Joystick*)(uintptr_t)joystickPtr);
}

JNIEXPORT jshort JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetAxis
(JNIEnv* env, jclass cls, jlong joystickPtr, jint axis) {
	return SDL_JoystickGetAxis((SDL_Joystick*)(uintptr_t)joystickPtr, axis);
}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetButton
(JNIEnv* env, jclass cls, jlong joystickPtr, jint button) {
	return SDL_JoystickGetButton((SDL_Joystick*)(uintptr_t)joystickPtr, button);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetBall
(JNIEnv* env, jclass cls, jlong joystickPtr, jint ball, jintArray deltas) {
	int dx, dy;
	int retval = SDL_JoystickGetBall((SDL_Joystick*)(uintptr_t)joystickPtr, ball, &dx, &dy);
	jint* iarr = (jint *)env->GetPrimitiveArrayCritical(deltas, NULL);
	if (iarr == NULL)
		return -1;
	iarr[0] = dx;
	iarr[1] = dy;
	env->ReleasePrimitiveArrayCritical(deltas, iarr, 0);
	return retval;
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetHat 
(JNIEnv *env, jclass cls, jlong joystickPtr, jint hat) {
	return (jint)SDL_JoystickGetHat((SDL_Joystick*)(uintptr_t)joystickPtr, hat);
}

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickCurrentPowerLevel
(JNIEnv* env, jclass cls, jlong joystickPtr) {
	return SDL_JoystickCurrentPowerLevel((SDL_Joystick*)(uintptr_t)joystickPtr);
}

static jbyteArray byteArrayFromGUID
(JNIEnv* env, const SDL_JoystickGUID * guid) {
	int n = sizeof(SDL_JoystickGUID);
	jbyteArray barr = env->NewByteArray(n);
	if (barr == NULL)
		return NULL;
	env->SetByteArrayRegion(barr, 0, n, (const jbyte*)guid->data);
	return barr;
}

JNIEXPORT jbyteArray JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetDeviceGUID
(JNIEnv* env, jclass cls, jint deviceIdx) {
	SDL_JoystickGUID guid = SDL_JoystickGetDeviceGUID(deviceIdx);
	return byteArrayFromGUID(env, &guid);
}

JNIEXPORT jbyteArray JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetGUID
(JNIEnv* env, jclass cls, jlong joystickPtr) {
	SDL_JoystickGUID guid = SDL_JoystickGetGUID((SDL_Joystick*)(uintptr_t)joystickPtr);
	return byteArrayFromGUID(env, &guid);
}

JNIEXPORT jbyteArray JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetGUIDFromString
(JNIEnv* env, jclass cls, jstring guidStr) {
	const char* cguidstr = env->GetStringUTFChars(guidStr, NULL);
	SDL_JoystickGUID guid = SDL_JoystickGetGUIDFromString(cguidstr);
	env->ReleaseStringUTFChars(guidStr, cguidstr);
	return byteArrayFromGUID(env, &guid);
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetGUIDString
(JNIEnv* env, jclass cls, jbyteArray barrGuid) {
	SDL_JoystickGUID guid;
	int n = env->GetArrayLength(barrGuid);
	int size = sizeof(SDL_JoystickGUID);
	if (n > size)
		n = size;
	else if (n < size)
		memset(guid.data, 0, size);
	env->GetByteArrayRegion(barrGuid, 0, n, (jbyte*)guid.data);
	char cstrGuid[33];
	SDL_JoystickGetGUIDString(guid, cstrGuid, 33);
	return env->NewStringUTF(cstrGuid);
}

// ---------------- GameController functions -------------------------------

JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerAddMappingsFromFile
(JNIEnv* env, jclass cls, jstring filename) {
	const char* cstr = env->GetStringUTFChars(filename, NULL);
	int numMappings = SDL_GameControllerAddMappingsFromFile(cstr);
	env->ReleaseStringUTFChars(filename, cstr);
	return (jint) numMappings;
}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_isGameController
(JNIEnv* env, jclass cls, jint deviceIdx) {
	return SDL_IsGameController(deviceIdx);
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerOpen
(JNIEnv* env, jclass cls, jint deviceIdx) {
	return (jlong)(uintptr_t)SDL_GameControllerOpen(deviceIdx);
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerFromInstanceId
(JNIEnv* env, jclass cls, jint instanceId) {
	return (jlong)(uintptr_t)SDL_GameControllerFromInstanceID(instanceId);
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerClose
(JNIEnv* env, jclass cls, jlong gameControllerPtr) {
	SDL_GameControllerClose((SDL_GameController*)(uintptr_t)gameControllerPtr);
}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetAttached
(JNIEnv* env, jclass cls, jlong gameControllerPtr) {
	return SDL_GameControllerGetAttached((SDL_GameController*)(uintptr_t)gameControllerPtr);
}

JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetJoystick
(JNIEnv* env, jclass cls, jlong gameControllerPtr) {
	return (jlong)(uintptr_t)SDL_GameControllerGetJoystick((SDL_GameController*)(uintptr_t)gameControllerPtr);
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerName
(JNIEnv* env, jclass cls, jlong gameControllerPtr) {
	const char* name = SDL_GameControllerName((SDL_GameController*)(uintptr_t)gameControllerPtr);
	if (name == NULL)
		return NULL;
	return env->NewStringUTF(name);
}

JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerNameForIndex
(JNIEnv* env, jclass cls, jint deviceIdx) {
	const char* name = SDL_GameControllerNameForIndex(deviceIdx);
	if (name == NULL)
		return NULL;
	return env->NewStringUTF(name);
}

JNIEXPORT jshort JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetAxis
(JNIEnv* env, jclass cls, jlong gameControllerPtr, jint axis) {
	return SDL_GameControllerGetAxis((SDL_GameController*)(uintptr_t)gameControllerPtr, (SDL_GameControllerAxis) axis);
}

JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetButton
(JNIEnv* env, jclass cls, jlong gameControllerPtr, jint button) {
	return SDL_GameControllerGetButton((SDL_GameController*)(uintptr_t)gameControllerPtr, (SDL_GameControllerButton)button);
}

// Structures and functions for gameControllerUpdateState()

struct GameControllerState 
{
	short axisVals[SDL_CONTROLLER_AXIS_MAX];
	Uint8 buttonVals[SDL_CONTROLLER_BUTTON_MAX];
};

JNIEXPORT jintArray JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetStateInfo
(JNIEnv *env, jclass cls) {
	jint infoArray[2];
	infoArray[0] = sizeof(GameControllerState);
	infoArray[1] = offsetof(GameControllerState, buttonVals);
	jintArray iarr = env->NewIntArray(2);
	if (iarr == NULL)
		return NULL;
	env->SetIntArrayRegion(iarr, 0, 2, infoArray);
	return iarr;
}

JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerUpdateState
(JNIEnv *env, jclass cls, jboolean nativeUpdate, jlong gameControllerPtr, jlong bufferPtr)
{
	SDL_GameController* controller = (SDL_GameController*)(uintptr_t)gameControllerPtr;
	GameControllerState* state = (GameControllerState*)(uintptr_t)bufferPtr;
	if (nativeUpdate)
		SDL_JoystickUpdate();
	for (int axis = 0; axis < SDL_CONTROLLER_AXIS_MAX; axis++)
		state->axisVals[axis] = SDL_GameControllerGetAxis(controller, (SDL_GameControllerAxis)axis);
	for (int b = 0; b < SDL_CONTROLLER_BUTTON_MAX; b++)
		state->buttonVals[b] = SDL_GameControllerGetButton(controller, (SDL_GameControllerButton)b);
}