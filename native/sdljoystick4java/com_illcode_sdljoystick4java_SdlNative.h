/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_illcode_sdljoystick4java_SdlNative */

#ifndef _Included_com_illcode_sdljoystick4java_SdlNative
#define _Included_com_illcode_sdljoystick4java_SdlNative
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    getDirectByteBufferAddress
 * Signature: (Ljava/nio/ByteBuffer;)J
 */
JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_getDirectByteBufferAddress
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    initJoysticks
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_initJoysticks
  (JNIEnv *, jclass);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    initGameControllers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_initGameControllers
  (JNIEnv *, jclass);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    cleanup
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_cleanup
  (JNIEnv *, jclass);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    update
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_update
  (JNIEnv *, jclass);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    getError
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_getError
  (JNIEnv *, jclass);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    numJoysticks
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_numJoysticks
  (JNIEnv *, jclass);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    joystickOpen
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickOpen
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    joystickClose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickClose
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    getInstanceId
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_getInstanceId
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    joystickFromInstanceId
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickFromInstanceId
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    joystickName
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickName
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    joystickNameForIndex
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNameForIndex
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    joystickNumAxes
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNumAxes
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    joystickNumButtons
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickNumButtons
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    joystickGetAxis
 * Signature: (JI)S
 */
JNIEXPORT jshort JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetAxis
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    joystickGetButton
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_joystickGetButton
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    gameControllerAddMappingsFromFile
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerAddMappingsFromFile
  (JNIEnv *, jclass, jstring);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    isGameController
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_isGameController
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    gameControllerOpen
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerOpen
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    gameControllerFromInstanceId
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerFromInstanceId
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    gameControllerClose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerClose
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    gameControllerGetJoystick
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetJoystick
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    gameControllerName
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerName
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    gameControllerNameForIndex
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerNameForIndex
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    gameControllerGetAxis
 * Signature: (JI)S
 */
JNIEXPORT jshort JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetAxis
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_illcode_sdljoystick4java_SdlNative
 * Method:    gameControllerGetButton
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_illcode_sdljoystick4java_SdlNative_gameControllerGetButton
  (JNIEnv *, jclass, jlong, jint);

#ifdef __cplusplus
}
#endif
#endif
