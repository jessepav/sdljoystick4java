package com.illcode.sdljoystick4java;

/**
 * A wrapper class for the native joystick methods found in {@link SdlNative}.
 * <p/>
 * Prior to using this class, you'll need to call {@link SdlNative#initJoysticks()}.
 */
public class Joystick
{
    private long joystickPtr;
    private int instanceId;
    private String name;
    private int numAxes;
    private int numButtons;
    private int numBalls;
    private int numHats;

    private boolean transitionDetectionEnabled;
    private boolean[] currentButtonState, previousButtonState;

    /**
     * Construct a Joystick
     * @param deviceIdx index of the joystick ({@code 0 <= deviceIdx < }{@link #numJoysticks()} )
     * @throws SdlException thrown if the native <tt>SDL_JoystickOpen()</tt> function fails
     */
    public Joystick(int deviceIdx) throws SdlException {
        joystickPtr = SdlNative.joystickOpen(deviceIdx);
        if (joystickPtr == 0)
            throw new SdlException();
        queryJoystick();
    }

    /**
     * Construct a Joystick from a native pointer. For internal use.
     */
    Joystick(long joystickPtr) {
        this.joystickPtr = joystickPtr;
        queryJoystick();
    }

    private void queryJoystick() {
        instanceId = SdlNative.getInstanceId(joystickPtr);
        name = SdlNative.joystickName(joystickPtr);
        numAxes = SdlNative.joystickNumAxes(joystickPtr);
        numButtons = SdlNative.joystickNumButtons(joystickPtr);
        numBalls = SdlNative.joystickNumBalls(joystickPtr);
        numHats = SdlNative.joystickNumHats(joystickPtr);
        update(true);
    }

    /**
     * Close the joystick
     */
    public void close() {
        if (joystickPtr != 0)
            SdlNative.joystickClose(joystickPtr);
        joystickPtr = 0;
    }

    /**
     * Returns the instance ID of the specified joystick on success or a negative error code on failure.
     */
    public int getInstanceId() {
        return instanceId;
    }

    /**
     * Returns the name of the joystick.
     * @return joystick name or <tt>null</tt> if not found
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of axis controls/number of axes on success or a negative error code on failure
     */
    public int getNumAxes() {
        return numAxes;
    }

    /**
     * Returns the number of buttons on success or a negative error code on failure
     */
    public int getNumButtons() {
        return numButtons;
    }

    /**
     * Get the status of the joystick
     * @return true if the joystick has been opened, false if it has not
     */
    public boolean isAttached() {
        return SdlNative.joystickGetAttached(joystickPtr);
    }

    /**
     * Returns a 16-bit signed integer representing the current position of the axis
     * @param axis the axis to query; the axis indices start at index 0. On most modern joysticks the
     *      X axis is usually represented by axis 0 and the Y axis by axis 1
     * @return a value ranging from -32768 to 32767 or 0 on failure
     */
    public short getAxis(int axis) {
        return SdlNative.joystickGetAxis(joystickPtr, axis);
    }

    /**
     * Returns true if the specified button is pressed, false otherwise
     * @param button the button index to get the state from; indices start at index 0
     */
    public boolean getButton(int button) {
        if (transitionDetectionEnabled)
            return currentButtonState[button];   // no need to call to native
        else
            return SdlNative.joystickGetButton(joystickPtr, button);
    }

    /**
     * Get the ball axis change since the last poll. Trackballs can only return relative motion since
     * the last call to this method.
     * @param ball the ball index to query; ball indices start at index 0
     * @param xyDelta filled with the difference in the axis position since the last poll
     * @return 0 on success or a negative error code on failure
     */
    public int getBall(int ball, BallDelta xyDelta) {
        return SdlNative.joystickGetBall(joystickPtr, ball, xyDelta.deltas);
    }

    /**
     * Get the current state of a POV hat on a joystick
     * @param hat the hat index to get the state from; hat indices start at index 0
     * @return one of the <tt>SDL_HAT</tt> constants in {@link SdlConstants}
     */
    public int getHat(int hat) {
        return SdlNative.joystickGetHat(joystickPtr, hat);
    }

    /**
     * Get the battery level of the joystick as a SDL_JoystickPowerLevel value.
     * @return the current battery level as one of the <tt>POWER</tt> constants in {@link SdlConstants}
     *      on success or <tt>SDL_JOYSTICK_POWER_UNKNOWN</tt> if it is unknown
     */
    public int getCurrentPowerLevel() {
        return SdlNative.joystickCurrentPowerLevel(joystickPtr);
    }


    /**
     * Enable or disable support for button transition events. These are exposed through
     * the {@link #buttonPressed(int)} and {@link #buttonReleased(int)} methods. The usual
     * button polling method, {@link #getButton(int)}, reports if the button was pressed
     * at the time of the last call to {@link #update(boolean)}. The button transition methods,
     * in comparison, report if the button was pressed (or released) in the most recent
     * call to <tt>update()</tt>, and <em>not</em> pressed (or released) at the time of
     * the previous <tt>update()</tt>.
     *
     * @param transitionDetectionEnabled transition detection state
     */
    public void setTransitionDetectionEnabled(boolean transitionDetectionEnabled) {
        this.transitionDetectionEnabled = transitionDetectionEnabled;
        if (transitionDetectionEnabled && currentButtonState == null) {
            currentButtonState = new boolean[numButtons];
            previousButtonState = new boolean[numButtons];
        }
    }

    /**
     * Return true if a button press event occurred (see {@link #setTransitionDetectionEnabled}).
     * @param button the button index to get the state from; indices start at index 0
     */
    public boolean buttonPressed(int button) {
        if (transitionDetectionEnabled)
            return currentButtonState[button] && !previousButtonState[button];
        else
            return false;
    }

    /**
     * Return true if a button release event occurred (see {@link #setTransitionDetectionEnabled}).
     * @param button the button index to get the state from; indices start at index 0
     */
    public boolean buttonReleased(int button) {
        if (transitionDetectionEnabled)
            return !currentButtonState[button] && previousButtonState[button];
        else
            return false;
    }

    /**
     * Update local state for this joystick and, optionally, global native state for all joysticks.
     * During each polling cycle, you only need to update native state once, while the local state
     * needs to be updated for each joystick.
     * @param nativeUpdate if true, update native state
     */
    public void update(boolean nativeUpdate) {
        if (nativeUpdate)
            SdlNative.update();
        if (transitionDetectionEnabled) {
            System.arraycopy(currentButtonState, 0, previousButtonState, 0, numButtons);
            for (int b = 0; b < numButtons; b++)
                currentButtonState[b] = SdlNative.joystickGetButton(joystickPtr, b);
        }
    }

    /**
     * Get the implementation-dependent GUID for the joystick
     * @return GUID of the joystick.
     */
    public GUID getGUID() {
        return new GUID(SdlNative.joystickGetGUID(joystickPtr));
    }

    /** Return number of joysticks attached to the system. */
    public static int numJoysticks() {
        return SdlNative.numJoysticks();
    }

    /**
     * Get the implementation-dependent GUID for the joystick at a given device index.
     * @param deviceIdx index of the joystick ({@code 0 <= deviceIdx < numJoysticks()} )
     * @return GUID of the given joystick
     */
    public static GUID getGUID(int deviceIdx) {
        return new GUID(SdlNative.joystickGetDeviceGUID(deviceIdx));
    }

    /**
     * Convert a GUID string into a GUID structure
     * @param guidStr string containing an ASCII representation of a GUID
     * @return GUID
     */
    public static GUID getGUID(String guidStr) {
        return new GUID(SdlNative.joystickGetGUIDFromString(guidStr));
    }

    /**
     * Get an ASCII string representation for a given GUID
     * @param guid GUID structure
     * @return ASCII string
     */
    public static String getGUIDString(GUID guid) {
        return SdlNative.joystickGetGUIDString(guid.data);
    }

    /**
     * Returns the name of the joystick at the given index
     * @param deviceIdx index of the joystick ({@code 0 <= deviceIdx < }{@link #numJoysticks()} )
     * @return joystick name or <tt>null</tt> if not found
     */
    public static String getName(int deviceIdx) {
        return SdlNative.joystickNameForIndex(deviceIdx);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("sdljoystick4java.Joystick:\n");
        sb.append("  joystickPtr = ").append(String.format("0x%016X", joystickPtr)).append("\n");
        sb.append("   instanceId = ").append(instanceId).append("\n");
        sb.append("         name = ").append(name).append("\n");
        sb.append("      numAxes = ").append(numAxes).append("\n");
        sb.append("   numButtons = ").append(numButtons).append("\n");
        sb.append("     numBalls = ").append(numBalls).append("\n");
        sb.append("      numHats = ").append(numHats).append("\n");
        return sb.toString();
    }

    /**
     * A joystick GUI, corresponding to the SDL_JoystickGUID type.
     */
    public final static class GUID
    {
        public byte[] data;

        public GUID(byte[] data) {
            this.data = data;
        }

        public String toString() {
            return SdlNative.joystickGetGUIDString(data);
        }
    }

    /** A container class for deltas returned by {@link Joystick#getBall} */
    public final static class BallDelta
    {
        private int[] deltas;

        public BallDelta() {
            deltas = new int[2];
        }

        /** Get the delta-x */
        public int getX() {
            return deltas[0];
        }

        /** Get the delta-y */
        public int getY() {
            return deltas[1];
        }
    }

}
