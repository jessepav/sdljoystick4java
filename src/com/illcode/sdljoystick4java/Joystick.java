package com.illcode.sdljoystick4java;

public class Joystick
{
    private long joystickPtr;
    private int instanceId;
    private String name;
    private int numAxes;
    private int numButtons;

    private boolean transitionDetectionEnabled;
    private boolean[] currentButtonState, previousButtonState;


    public Joystick(int deviceIdx) throws SdlException {
        joystickPtr = SdlNative.joystickOpen(deviceIdx);
        if (joystickPtr == 0)
            throw new SdlException();
        queryJoystick();
    }

    public Joystick(long joystickPtr) {
        this.joystickPtr = joystickPtr;
        queryJoystick();
    }

    private void queryJoystick() {
        instanceId = SdlNative.getInstanceId(joystickPtr);
        name = SdlNative.joystickName(joystickPtr);
        numAxes = SdlNative.joystickNumAxes(joystickPtr);
        numButtons = SdlNative.joystickNumButtons(joystickPtr);
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

    /** Return number of joysticks attached to the system. */
    public static int numJoysticks() {
        return SdlNative.numJoysticks();
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
        return sb.toString();
    }

    public static void main(String[] args) throws SdlException, InterruptedException {
        SdlNative.initJoysticks();
        if (Joystick.numJoysticks() > 0) {
            final Joystick joystick = new Joystick(0);
            joystick.setTransitionDetectionEnabled(true);
            System.out.println(joystick.toString());
            if (args.length >= 1) {
                System.out.println();
                int n = Integer.parseInt(args[0]);
                Thread.sleep(200);
                iterationLoop:
                for (int i = 0; i < n; i++) {
                    joystick.update(true);
                    if (!joystick.isAttached()) {
                        System.out.println("Joystick disconnected!");
                        break iterationLoop;
                    }
                    for (int button = 0; button < joystick.getNumButtons(); button++) {
                        System.out.print(joystick.getButton(button) ? "1" : "0");
                        if (joystick.buttonPressed(button))
                            System.out.print("P");
                        else if (joystick.buttonReleased(button))
                            System.out.print("R");
                        else
                            System.out.print(" ");
                        System.out.print(" ");
                    }
                    for (int axis = 0; axis < joystick.getNumAxes(); axis++)
                        System.out.printf("%+6d ", joystick.getAxis(axis));
                    System.out.println();
                    Thread.sleep(500);
                }
            }
            joystick.close();
        }
        SdlNative.cleanup();
    }

}
