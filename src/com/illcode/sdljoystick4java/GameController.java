package com.illcode.sdljoystick4java;

import java.nio.file.Path;

public class GameController
{
    private long gameControllerPtr;
    private String name;

    public GameController(int deviceIdx) throws SdlException {
        if (!Native.isGameController(deviceIdx))
            throw new SdlException("Device is not a game controller");
        gameControllerPtr = Native.gameControllerOpen(deviceIdx);
        if (gameControllerPtr == 0)
            throw new SdlException();
        name = Native.gameControllerName(gameControllerPtr);
    }

    public static int addMappingsFromFile(Path dbPath) {
        return Native.gameControllerAddMappingsFromFile(dbPath.toString());
    }

    public static int addMappingsFromFile(String dbName) {
        return Native.gameControllerAddMappingsFromFile(dbName);
    }

    public void close() {
        if (gameControllerPtr != 0)
            Native.gameControllerClose(gameControllerPtr);
        gameControllerPtr = 0;
    }

    public Joystick getJoystick() {
        return new Joystick(Native.gameControllerGetJoystick(gameControllerPtr));
    }

    public String getName() {
        return name;
    }

    public short getAxis(int axis) {
        return Native.gameControllerGetAxis(gameControllerPtr, axis);
    }

    public boolean getButton(int button) {
        return Native.gameControllerGetButton(gameControllerPtr, button);
    }

    
}
