package com.illcode.sdljoystick4java;

import java.nio.ByteBuffer;

/**
 * Direct mappings for JNI methods.
 */
public final class Native
{
    /**
     * Get the native address of a direct byte buffer.
     * @param b buffer
     * @return address of buffer
     */
    public static native long getDirectByteBufferAddress(ByteBuffer b);

    /**
     * Initialize the SDL Joystick subsystem
     * @param useControllers if true, initialize the Gamecontroller subsystem as well
     */
    public static native void init(boolean useControllers);

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
     * Returns the name of the selected joystick.
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

    static {
        System.loadLibrary("SDL2");
        System.loadLibrary("sdljoystick4java");
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}
