package com.illcode.sdljoystick4java;

public class Joystick
{
    private int deviceIdx;
    private long joystickPtr;
    private int instanceId;
    private String name;
    private int numAxes;
    private int numButtons;

    public Joystick(int deviceIdx) throws SdlException {
        this.deviceIdx = deviceIdx;
        joystickPtr = Native.joystickOpen(deviceIdx);
        if (joystickPtr == 0)
            throw new SdlException();
        instanceId = Native.getInstanceId(joystickPtr);
        name = Native.joystickName(joystickPtr);
        numAxes = Native.joystickNumAxes(joystickPtr);
        numButtons = Native.joystickNumButtons(joystickPtr);
    }

    public void close() {
        if (joystickPtr != 0)
            Native.joystickClose(joystickPtr);
    }

    public void update() {
        Native.update();
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("sdljoystick4java.Joystick #").append(deviceIdx).append(":\n");
        sb.append("  joystickPtr = ").append(String.format("0x%016X", joystickPtr)).append("\n");
        sb.append("   instanceId = ").append(instanceId).append("\n");
        sb.append("         name = ").append(name).append("\n");
        sb.append("      numAxes = ").append(numAxes).append("\n");
        sb.append("   numButtons = ").append(numButtons).append("\n");
        return sb.toString();
    }

    public static void main(String[] args) throws SdlException {
        Native.initJoysticks();
        final Joystick joystick = new Joystick(0);
        System.out.println(joystick.toString());
        joystick.close();
        Native.cleanup();
    }

}
