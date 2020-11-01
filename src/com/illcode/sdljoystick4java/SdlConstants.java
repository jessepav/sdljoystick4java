package com.illcode.sdljoystick4java;

public final class SdlConstants
{
    public static final int SDL_CONTROLLER_AXIS_INVALID = -1,
                            SDL_CONTROLLER_AXIS_LEFTX = 0,
                            SDL_CONTROLLER_AXIS_LEFTY = 1,
                            SDL_CONTROLLER_AXIS_RIGHTX = 2,
                            SDL_CONTROLLER_AXIS_RIGHTY = 3,
                            SDL_CONTROLLER_AXIS_TRIGGERLEFT = 4,
                            SDL_CONTROLLER_AXIS_TRIGGERRIGHT = 5,
                            SDL_CONTROLLER_AXIS_MAX = 6;

    public static final String[] AXIS_NAMES = new String[]
        {"LEFTX", "LEFTY", "RIGHTX", "RIGHTY", "TRIGGERLEFT", "TRIGGERRIGHT"};

    public static final String[] SHORT_AXIS_NAMES = new String[]
        {"LX", "LY", "RX", "RY", "TL", "TR"};

    public static final int SDL_CONTROLLER_BUTTON_INVALID = -1,
                            SDL_CONTROLLER_BUTTON_A = 0,
                            SDL_CONTROLLER_BUTTON_B = 1,
                            SDL_CONTROLLER_BUTTON_X = 2,
                            SDL_CONTROLLER_BUTTON_Y = 3,
                            SDL_CONTROLLER_BUTTON_BACK = 4,
                            SDL_CONTROLLER_BUTTON_GUIDE = 5,
                            SDL_CONTROLLER_BUTTON_START = 6,
                            SDL_CONTROLLER_BUTTON_LEFTSTICK = 7,
                            SDL_CONTROLLER_BUTTON_RIGHTSTICK = 8,
                            SDL_CONTROLLER_BUTTON_LEFTSHOULDER = 9,
                            SDL_CONTROLLER_BUTTON_RIGHTSHOULDER = 10,
                            SDL_CONTROLLER_BUTTON_DPAD_UP = 11,
                            SDL_CONTROLLER_BUTTON_DPAD_DOWN = 12,
                            SDL_CONTROLLER_BUTTON_DPAD_LEFT = 13,
                            SDL_CONTROLLER_BUTTON_DPAD_RIGHT = 14,
         	            SDL_CONTROLLER_BUTTON_MAX = 15;

    public static final String[] BUTTON_NAMES = new String[]
        {"A", "B", "X", "Y", "BACK", "GUIDE", "START", "LEFTSTICK", "RIGHTSTICK",
         "LEFTSHOULDER", "RIGHTSHOULDER", "DPAD_UP", "DPAD_DOWN", "DPAD_LEFT", "DPAD_RIGHT"};

    public static final String[] SHORT_BUTTON_NAMES = new String[]
        {"A", "B", "X", "Y", "BK", "GD", "ST", "LST", "RST",
         "LSH", "RSH", "DU", "DD", "DL", "DR"};

    public static final int SDL_JOYSTICK_POWER_UNKNOWN = -1,
                            SDL_JOYSTICK_POWER_EMPTY = 0,   /* <= 5% */
                            SDL_JOYSTICK_POWER_LOW = 1,     /* <= 20% */
                            SDL_JOYSTICK_POWER_MEDIUM = 2,  /* <= 70% */
                            SDL_JOYSTICK_POWER_FULL = 3,    /* <= 100% */
                            SDL_JOYSTICK_POWER_WIRED = 4,
                            SDL_JOYSTICK_POWER_MAX = 5;
}
