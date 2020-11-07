package com.illcode.sdljoystick4java;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;

/**
 * A wrapper class for the native GameController methods found in {@link SdlNative}.
 * <p/>
 * Prior to using this class, you'll need to call {@link SdlNative#initGameControllers()}.
 */
public class GameController
{
    private long gameControllerPtr;
    private String name;
    private GameControllerState state;

    private short[] currentAxisState;
    private boolean[] currentButtonState, previousButtonState;

    /**
     * Construct a GameController
     * @param deviceIdx index of the game controller ({@code 0 <= deviceIdx < }{@link Joystick#numJoysticks()} )
     * @throws SdlException thrown if the native <tt>SDL_GameControllerOpen()</tt> function fails
     */
    public GameController(int deviceIdx) throws SdlException {
        if (!SdlNative.isGameController(deviceIdx))
            throw new SdlException("Device is not a game controller");
        gameControllerPtr = SdlNative.gameControllerOpen(deviceIdx);
        if (gameControllerPtr == 0)
            throw new SdlException();
        name = SdlNative.gameControllerName(gameControllerPtr);
        state = new GameControllerState();
        currentAxisState = new short[SdlConstants.SDL_CONTROLLER_AXIS_MAX];
        currentButtonState = new boolean[SdlConstants.SDL_CONTROLLER_BUTTON_MAX];
        previousButtonState = new boolean[SdlConstants.SDL_CONTROLLER_BUTTON_MAX];

    }

    /**
     * Load a set of Game Controller mappings from a file, filtered by the current platform
     * @param dbPath the path of the database you want to load
     * @return Returns the number of mappings added or -1 on error
     * @see <a href="https://github.com/gabomdq/SDL_GameControllerDB">SDL_GameControllerDB on GitHub</a>
     */
    public static int addMappingsFromFile(Path dbPath) {
        return SdlNative.gameControllerAddMappingsFromFile(dbPath.toString());
    }

    /**
     * Load a set of Game Controller mappings from a file, filtered by the current platform
     * @param dbName the name of the database you want to load
     * @return Returns the number of mappings added or -1 on error
     * @see <a href="https://github.com/gabomdq/SDL_GameControllerDB">SDL_GameControllerDB on GitHub</a>
     */
    public static int addMappingsFromFile(String dbName) {
        return SdlNative.gameControllerAddMappingsFromFile(dbName);
    }

    /** Close the game controller */
    public void close() {
        if (gameControllerPtr != 0)
            SdlNative.gameControllerClose(gameControllerPtr);
        gameControllerPtr = 0;
    }

    /**
     * Return the underlying joystick of this game controller
     */
    public Joystick getJoystick() {
        return new Joystick(SdlNative.gameControllerGetJoystick(gameControllerPtr));
    }

    /**
     * Returns the name of the game controller
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the game controller at the given index
     * @param deviceIdx index of the game controller ({@code 0 <= deviceIdx < }{@link Joystick#numJoysticks()} )
     * @return game controller name or <tt>null</tt> if not found
     */
    public static String getName(int deviceIdx) {
        return SdlNative.gameControllerNameForIndex(deviceIdx);
    }


    /**
     * Check if the controller has been opened and is currently connected
     * @return true if the controller has been opened and currently connected, or false otherwise
     */
    public boolean isAttached() {
        return SdlNative.gameControllerGetAttached(gameControllerPtr);
    }

    /**
     * Get the current state of an axis control on the game controller.
     * @param axis one of the AXIS constants in {@link SdlConstants}
     * @return Returns axis state (including 0) on success or 0 (also) on failure.
     *         The state is a value ranging from -32768 to 32767.
     *         Triggers, however, range from 0 to 32767 (they never return a negative value)
     */
    public short getAxis(int axis) {
        return currentAxisState[axis];
    }

    /**
     * Get the current state of a button on the game controller
     * @param button one of the BUTTON constants in {@link SdlConstants}
     * @return Returns <tt>true</tt> for pressed state or <tt>false</tt> for not pressed state or error
     */
    public boolean getButton(int button) {
        return currentButtonState[button];   // no need to call to native
    }

    /**
     * Return true if a button press event occurred (see {@link #setTransitionDetectionEnabled}).
     * @param button the button index to get the state from; indices start at index 0
     */
    public boolean buttonPressed(int button) {
        return currentButtonState[button] && !previousButtonState[button];
    }

    /**
     * Return true if a button release event occurred (see {@link #setTransitionDetectionEnabled}).
     * @param button the button index to get the state from; indices start at index 0
     */
    public boolean buttonReleased(int button) {
        return !currentButtonState[button] && previousButtonState[button];
    }

    /**
     * Update local state for this game controller and, optionally, global native state for all game controllers.
     * During each polling cycle, you only need to update native state once, while the local state
     * needs to be updated for each game controller.
     * @param nativeUpdate if true, update native state
     */
    public void update(boolean nativeUpdate) {
        System.arraycopy(currentButtonState, 0, previousButtonState, 0, SdlConstants.SDL_CONTROLLER_BUTTON_MAX);
        SdlNative.gameControllerUpdateState(nativeUpdate, gameControllerPtr, state.bufPtr);
        state.getAllAxes(currentAxisState);
        state.getAllButtons(currentButtonState);
    }

    private static final class GameControllerState
    {
        private static int structSize;
        private static int buttonValsOffset;

        static {
            int[] info = SdlNative.gameControllerGetStateInfo();
            structSize = info[0];
            buttonValsOffset = info[1];
        }

        private ByteBuffer buf;
        private long bufPtr;

        private GameControllerState() {
            buf = ByteBuffer.allocateDirect(structSize);
            buf.order(ByteOrder.nativeOrder());
            bufPtr = SdlNative.getDirectByteBufferAddress(buf);
        }

        private short getAxis(int axis) {
            return buf.getShort(axis * 2);
        }

        private boolean getButton(int button) {
            return buf.get(buttonValsOffset + button) == 1;
        }

        private void getAllAxes(short[] axes) {
            for (int axis = 0; axis < SdlConstants.SDL_CONTROLLER_AXIS_MAX; axis++)
                axes[axis] = buf.getShort(axis * 2);
        }

        private void getAllButtons(boolean[] buttons) {
            int offset = buttonValsOffset;
            for (int b = 0; b < SdlConstants.SDL_CONTROLLER_BUTTON_MAX; b++)
                buttons[b] = buf.get(offset++) == 1;
        }
    }
}
