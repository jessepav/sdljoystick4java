package com.illcode.sdljoystick4java;

import java.nio.ByteBuffer;

/**
 * Direct mappings for JNI methods.
 */
public final class SdlNative
{
    /**
     * Get the native address of a direct byte buffer.
     * @param b buffer
     * @return address of buffer
     */
    public static native long getDirectByteBufferAddress(ByteBuffer b);

    /**
     * Initialize the SDL Joystick subsystem
     */
    public static native void initJoysticks();

    /**
     * Initialize the SDL GameController subsystem
     */
    public static native void initGameControllers();

    /**
     * Cleanup any resources and call SDL_Quit()
     */
    public static native void cleanup();

    /**
     * Poll joystick and gamecontroller state
     */
    public static native void update();

    /**
     * If any method signalled an error (via return code), return the error string.
     */
    public static native String getError();


    // ==================================================================================
    //   Joystick methods
    // ==================================================================================

    /**
     * Return number of joysticks attached to the system.
     */
    public static native int numJoysticks();

    /**
     * Open a joystick and return a pointer to a joystick ID structure
     * @param deviceIdx index of the joystick ({@code 0 <= deviceIdx < numJoysticks()} )
     * @return pointer value, or 0 on error
     */
    public static native long joystickOpen(int deviceIdx);

    /**
     * Close a joystick previously opened with joystickOpen()
     * @param joystickPtr joystick pointer value
     */
    public static native void joystickClose(long joystickPtr);

    /**
     * Get the status of a specified joystick
     * @param joystickPtr joystick pointer value
     * @return true if the joystick has been opened, false if it has not
     */
    public static native boolean joystickGetAttached(long joystickPtr);

    /**
     * Returns the instance ID of the specified joystick on success or a negative error code on failure.
     * @param joystickPtr long pointer returned by {@code joystickOpen()}
     */
    public static native int getInstanceId(long joystickPtr);

    /**
     * Returns a pointer to a joystick ID structure on success or 0 on failure.
     * @param instanceId instance ID as returned by {@code getInstanceId()}
     */
    public static native long joystickFromInstanceId(int instanceId);

    /**
     * Returns the name of the given joystick.
     * @param joystickPtr joystick pointer value
     * @return joystick name or <tt>null</tt> if not found
     */
    public static native String joystickName(long joystickPtr);

    /**
     * Returns the name of the joystick at the given index
     * @param deviceIdx index of the joystick ({@code 0 <= deviceIdx < numJoysticks()} )
     * @return joystick name or <tt>null</tt> if not found
     */
    public static native String joystickNameForIndex(int deviceIdx);

    /**
     * Returns the number of axis controls/number of axes on success or a negative error code on failure
     * @param joystickPtr joystick pointer value
     */
    public static native int joystickNumAxes(long joystickPtr);

    /**
     * Returns the number of buttons on success or a negative error code on failure
     * @param joystickPtr joystick pointer value
     */
    public static native int joystickNumButtons(long joystickPtr);

    /**
     * Returns a 16-bit signed integer representing the current position of the axis
     * @param joystickPtr joystick pointer value
     * @param axis the axis to query; the axis indices start at index 0. On most modern joysticks the
     *      X axis is usually represented by axis 0 and the Y axis by axis 1
     * @return a value ranging from -32768 to 32767 or 0 on failure
     */
    public static native short joystickGetAxis(long joystickPtr, int axis);

    /**
     * Returns true if the specified button is pressed, false otherwise
     * @param joystickPtr joystick pointer value
     * @param button the button index to get the state from; indices start at index 0
     */
    public static native boolean joystickGetButton(long joystickPtr, int button);

    /**
     * Get the battery level of a joystick as a SDL_JoystickPowerLevel value.
     * @param joystickPtr joystick pointer value
     * @return the current battery level as one of the <tt>POWER</tt> constants in {@link SdlConstants}
     *      on success or <tt>SDL_JOYSTICK_POWER_UNKNOWN</tt> if it is unknown
     */
    public static native int joystickCurrentPowerLevel(long joystickPtr);

    /**
     * Get the implementation-dependent GUID for the joystick at a given device index.
     * @param deviceIdx index of the joystick ({@code 0 <= deviceIdx < numJoysticks()} )
     * @return GUID of the given joystick as a byte array (of length 16, as of SDL 2.0.12)
     *         If called on an invalid index, this function returns a zero GUID
     */
    public static native byte[] joystickGetDeviceGUID(int deviceIdx);

    /**
     * Get the implementation-dependent GUID for the joystick
     * @param joystickPtr joystick pointer value
     * @return GUID of the given joystick as a byte array (of length 16, as of SDL 2.0.12)
     *         If called on an invalid index, this function returns a zero GUID
     */
    public static native byte[] joystickGetGUID(long joystickPtr);

    /**
     * Convert a GUID string into a GUID structure
     * @param guidStr string containing an ASCII representation of a GUID
     * @return GUID structure as a byte array
     */
    public static native byte[] joystickGetGUIDFromString(String guidStr);

    /**
     * Get an ASCII string representation for a given GUID
     * @param guid GUID structure as a byte array
     * @return ASCII string
     */
    public static native String joystickGetGUIDString(byte[] guid);


    // ==================================================================================
    //   GameController methods
    // ==================================================================================

    /**
     * Use this function to load a set of Game Controller mappings from a file, filtered by the current platform
     * @param filename the name of the database you want to load
     * @return Returns the number of mappings added or -1 on error
     * @see <a href="https://github.com/gabomdq/SDL_GameControllerDB">SDL_GameControllerDB</a> on GitHub
     */
    public static native int gameControllerAddMappingsFromFile(String filename);

    /**
     * Use this function to check if the given joystick is supported by the game controller interface.
     * @param deviceIdx index of the joystick ({@code 0 <= deviceIdx < numJoysticks()} )
     * @return Returns <tt>true</tt> if the given joystick is supported by the game controller interface,
     *      <tt>false</tt> if it isn't or it's an invalid index
     */
    public static native boolean isGameController(int deviceIdx);

    /**
     * Open a game controller and return a pointer to a game controller ID structure
     * @param deviceIdx index of the joystick ({@code 0 <= deviceIdx < numJoysticks()} )
     * @return pointer value, or 0 on error
     */
    public static native long gameControllerOpen(int deviceIdx);

    /**
     * Returns a pointer to a game controller ID structure on success or 0 on failure.
     * @param instanceId instance ID as returned by {@code getInstanceId()}
     */
    public static native long gameControllerFromInstanceId(int instanceId);

    /**
     * Close a game controller previously opened with gameControllerOpen()
     * @param gameControllerPtr game controller pointer value
     */
    public static native void gameControllerClose(long gameControllerPtr);

    /**
     * Check if a controller has been opened and is currently connected
     * @param gameControllerPtr game controller pointer value
     * @return true if the controller has been opened and currently connected, or false otherwise
     */
    public static native boolean gameControllerGetAttached(long gameControllerPtr);

    /**
     * Return the joystick ID pointer for a game controller
     * @param gameControllerPtr game controller pointer value
     * @return joystick pointer value
     */
    public static native long gameControllerGetJoystick(long gameControllerPtr);

    /**
     * Returns the name of the given game controller
     * @param gameControllerPtr game controller pointer value
     * @return game controller name or <tt>null</tt> if not found
     */
    public static native String gameControllerName(long gameControllerPtr);

    /**
     * Returns the name of the game controller at the given index
     * @param deviceIdx index of the game controller ({@code 0 <= deviceIdx < numJoysticks()} )
     * @return game controller name or <tt>null</tt> if not found
     */
    public static native String gameControllerNameForIndex(int deviceIdx);

    /**
     * Get the current state of an axis control on a game controller.
     * @param gameControllerPtr game controller pointer value
     * @param axis one of the AXIS constants in {@link SdlConstants}
     * @return Returns axis state (including 0) on success or 0 (also) on failure.
     *         The state is a value ranging from -32768 to 32767.
     *         Triggers, however, range from 0 to 32767 (they never return a negative value)
     */
    public static native short gameControllerGetAxis(long gameControllerPtr, int axis);

    /**
     * get the current state of a button on a game controller
     * @param gameControllerPtr game controller pointer value
     * @param button one of the BUTTON constants in {@link SdlConstants}
     * @return Returns <tt>true</tt> for pressed state or <tt>false</tt> for not pressed state or error
     */
    public static native boolean gameControllerGetButton(long gameControllerPtr, int button);

    static {
        System.loadLibrary("SDL2");
        System.loadLibrary("sdljoystick4java");
    }
}
