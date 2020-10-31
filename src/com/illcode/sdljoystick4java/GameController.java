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

    /**
     * Load a set of Game Controller mappings from a file, filtered by the current platform
     * @param dbPath the path of the database you want to load
     * @return Returns the number of mappings added or -1 on error
     * @see <a href="https://github.com/gabomdq/SDL_GameControllerDB">SDL_GameControllerDB</a> on GitHub
     */
    public static int addMappingsFromFile(Path dbPath) {
        return Native.gameControllerAddMappingsFromFile(dbPath.toString());
    }

    /**
     * Load a set of Game Controller mappings from a file, filtered by the current platform
     * @param dbName the name of the database you want to load
     * @return Returns the number of mappings added or -1 on error
     * @see <a href="https://github.com/gabomdq/SDL_GameControllerDB">SDL_GameControllerDB</a> on GitHub
     */
    public static int addMappingsFromFile(String dbName) {
        return Native.gameControllerAddMappingsFromFile(dbName);
    }

    /** Close the game controller */
    public void close() {
        if (gameControllerPtr != 0)
            Native.gameControllerClose(gameControllerPtr);
        gameControllerPtr = 0;
    }

    /**
     * Return the underlying joystick of this game controller
     */
    public Joystick getJoystick() {
        return new Joystick(Native.gameControllerGetJoystick(gameControllerPtr));
    }

    /**
     * Returns the name of the game controller
     */
    public String getName() {
        return name;
    }

    /**
     * Get the current state of an axis control on the game controller.
     * @param axis one of the AXIS constants in {@link SdlConstants}
     * @return Returns axis state (including 0) on success or 0 (also) on failure.
     *         The state is a value ranging from -32768 to 32767.
     *         Triggers, however, range from 0 to 32767 (they never return a negative value)
     */
    public short getAxis(int axis) {
        return Native.gameControllerGetAxis(gameControllerPtr, axis);
    }

    /**
     * Get the current state of a button on the game controller
     * @param button one of the BUTTON constants in {@link SdlConstants}
     * @return Returns <tt>true</tt> for pressed state or <tt>false</tt> for not pressed state or error
     */
    public boolean getButton(int button) {
        return Native.gameControllerGetButton(gameControllerPtr, button);
    }

    /** Update state for all game controller instances. */
    public static void update() {
        Native.update();
    }

    public static void main(String[] args) throws SdlException, InterruptedException {
        if (args.length < 2) {
            System.out.println("Usage: GameController axes|buttons <num iterations>");
            System.exit(0);
        }
        Native.initJoysticks();
        Native.initGameControllers();
        if (Joystick.numJoysticks() > 0) {
            String cmd = args[0];
            int n = Integer.parseInt(args[1]);
            final GameController gc = new GameController(0);
            Thread.sleep(200);
            switch (cmd) {
            case "buttons":
                for (String sn : SdlConstants.SHORT_BUTTON_NAMES)
                    System.out.printf("%3s ", sn);
                System.out.println();
                for (int i = 0; i < n; i++) {
                    GameController.update();
                    for (int button = 0; button < SdlConstants.SDL_CONTROLLER_BUTTON_MAX; button++)
                        System.out.print(gc.getButton(button) ? "  1 " : "  0 ");
                    System.out.println();
                    Thread.sleep(500);
                }
                break;
            case "axes":
                break;
            }
            gc.close();
        }
        Native.cleanup();
    }
}
