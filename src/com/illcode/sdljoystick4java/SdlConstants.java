package com.illcode.sdljoystick4java;

public final class SdlConstants
{
    public static final int SDL_CONTROLLER_AXIS_INVALID = -1;
    public static final int SDL_CONTROLLER_AXIS_LEFTX = 0;
    public static final int SDL_CONTROLLER_AXIS_LEFTY = 1;
    public static final int SDL_CONTROLLER_AXIS_RIGHTX = 2;
    public static final int SDL_CONTROLLER_AXIS_RIGHTY = 3;
    public static final int SDL_CONTROLLER_AXIS_TRIGGERLEFT = 4;
    public static final int SDL_CONTROLLER_AXIS_TRIGGERRIGHT = 5;
    public static final int SDL_CONTROLLER_AXIS_MAX = 6;

    public static final String[] AXIS_NAMES = new String[]
        {"LEFTX", "LEFTY", "RIGHTX", "RIGHTY", "TRIGGERLEFT", "TRIGGERRIGHT"};

    public static final String[] SHORT_AXIS_NAMES = new String[]
        {"LX", "LY", "RX", "RY", "TL", "TR"};

    public static final int SDL_CONTROLLER_BUTTON_INVALID = -1;
    public static final int SDL_CONTROLLER_BUTTON_A = 0;
    public static final int SDL_CONTROLLER_BUTTON_B = 1;
    public static final int SDL_CONTROLLER_BUTTON_X = 2;
    public static final int SDL_CONTROLLER_BUTTON_Y = 3;
    public static final int SDL_CONTROLLER_BUTTON_BACK = 4;
    public static final int SDL_CONTROLLER_BUTTON_GUIDE = 5;
    public static final int SDL_CONTROLLER_BUTTON_START = 6;
    public static final int SDL_CONTROLLER_BUTTON_LEFTSTICK = 7;
    public static final int SDL_CONTROLLER_BUTTON_RIGHTSTICK = 8;
    public static final int SDL_CONTROLLER_BUTTON_LEFTSHOULDER = 9;
    public static final int SDL_CONTROLLER_BUTTON_RIGHTSHOULDER = 10;
    public static final int SDL_CONTROLLER_BUTTON_DPAD_UP = 11;
    public static final int SDL_CONTROLLER_BUTTON_DPAD_DOWN = 12;
    public static final int SDL_CONTROLLER_BUTTON_DPAD_LEFT = 13;
    public static final int SDL_CONTROLLER_BUTTON_DPAD_RIGHT = 14;
    public static final int SDL_CONTROLLER_BUTTON_MAX = 15;

    public static final String[] BUTTON_NAMES = new String[]
        {"A", "B", "X", "Y", "BACK", "GUIDE", "START", "LEFTSTICK", "RIGHTSTICK",
         "LEFTSHOULDER", "RIGHTSHOULDER", "DPAD_UP", "DPAD_DOWN", "DPAD_LEFT", "DPAD_RIGHT"};

    public static final String[] SHORT_BUTTON_NAMES = new String[]
        {"A", "B", "X", "Y", "BK", "GD", "ST", "LST", "RST",
         "LSH", "RSH", "DU", "DD", "DL", "DR"};
}
