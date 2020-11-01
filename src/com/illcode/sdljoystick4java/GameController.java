package com.illcode.sdljoystick4java;

import java.nio.file.Path;

public class GameController
{
    private long gameControllerPtr;
    private String name;

    private boolean transitionDetectionEnabled;
    private boolean[] currentButtonState, previousButtonState;

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
        if (transitionDetectionEnabled)
            return currentButtonState[button];   // no need to call to native
        else
            return Native.gameControllerGetButton(gameControllerPtr, button);
    }

    /**
     * Enable or disable support for button transition events. These are exposed through
     * the {@link #buttonPressed(int)} and {@link #buttonReleased(int)} methods. The usual
     * button polling method, {@link #getButton(int)}, reports if the button was pressed
     * at the time of the last call to {@link #update()}. The button transition methods,
     * in comparison, report if the button was pressed (or released) in the most recent
     * call to <tt>update()</tt>, and <em>not</em> pressed (or released) at the time of
     * the previous <tt>update()</tt>.
     *
     * @param transitionDetectionEnabled transition detection state
     */
    public void setTransitionDetectionEnabled(boolean transitionDetectionEnabled) {
        this.transitionDetectionEnabled = transitionDetectionEnabled;
        if (transitionDetectionEnabled && currentButtonState == null) {
            currentButtonState = new boolean[SdlConstants.SDL_CONTROLLER_BUTTON_MAX];
            previousButtonState = new boolean[SdlConstants.SDL_CONTROLLER_BUTTON_MAX];
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

    /** Update state for game controller. */
    public void update() {
        if (transitionDetectionEnabled)
            System.arraycopy(currentButtonState, 0, previousButtonState, 0, SdlConstants.SDL_CONTROLLER_BUTTON_MAX);
        Joystick.nativeUpdate();
        if (transitionDetectionEnabled) {
            for (int b = 0; b < SdlConstants.SDL_CONTROLLER_BUTTON_MAX; b++)
                currentButtonState[b] = Native.gameControllerGetButton(gameControllerPtr, b);
        }
    }

    public static void main(String[] args) throws SdlException, InterruptedException {
        if (args.length < 2) {
            System.out.println("Usage: GameController axes|buttons <num iterations>");
            System.exit(0);
        }
        Native.initJoysticks();
        Native.initGameControllers();
        if (Joystick.numJoysticks() > 0) {
            int numMappings = GameController.addMappingsFromFile("resources/gamecontrollerdb.txt");
            if (numMappings > 0)
                System.out.printf("Loaded %d controller mappings\n\n", numMappings);
            String cmd = args[0];
            int n = Integer.parseInt(args[1]);
            final GameController gc = new GameController(0);
            gc.setTransitionDetectionEnabled(true);
            System.out.println("GameController - " + gc.getName());
            Thread.sleep(200);
            switch (cmd) {
            case "buttons":
                for (int i = 0; i < n; i++) {
                    gc.update();
                    if (i % 10 == 0) {
                        System.out.println("---------------------------------------------------------------------");
                        for (String sn : SdlConstants.SHORT_BUTTON_NAMES)
                            System.out.printf("%3s ", sn);
                        System.out.println("\n---------------------------------------------------------------------");
                    }
                    for (int button = 0; button < SdlConstants.SDL_CONTROLLER_BUTTON_MAX; button++) {
                        System.out.print(gc.getButton(button) ? " 1" : " 0");
                        if (gc.buttonPressed(button))
                            System.out.print("P");
                        else if (gc.buttonReleased(button))
                            System.out.print("R");
                        else
                            System.out.print(" ");
                        System.out.print(" ");
                    }
                    System.out.println();
                    Thread.sleep(500);
                }
                break;
            case "axes":
                for (int i = 0; i < n; i++) {
                    gc.update();
                    if (i % 10 == 0) {
                        System.out.println("---------------------------------------------------------------------");
                        for (String sn : SdlConstants.SHORT_AXIS_NAMES)
                            System.out.printf("%6s  ", sn);
                        System.out.println("\n---------------------------------------------------------------------");
                    }
                    for (int axis = 0; axis < SdlConstants.SDL_CONTROLLER_AXIS_MAX; axis++)
                        System.out.printf("%+6d  ", gc.getAxis(axis));
                    System.out.println();
                    Thread.sleep(500);
                }
                break;
            }
            gc.close();
        }
        Native.cleanup();
    }
}
