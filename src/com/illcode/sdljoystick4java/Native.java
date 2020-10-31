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

    public static native String joystickName(long joystickPtr);
    public static native String joystickNameForIndex(int deviceIdx);

    public static native int joystickNumAxes(long joystickPtr);
    public static native int joystickNumButtons(long joystickPtr);

    public static native short joystickGetAxis(long joystickPtr, int axis);
    public static native boolean joystickGetButton(long joystickPtr, int button);


}
