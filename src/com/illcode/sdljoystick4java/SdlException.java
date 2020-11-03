package com.illcode.sdljoystick4java;

/**
 * An exception indicating failure of a native SDL function.
 */
public class SdlException extends Exception
{
    public SdlException() {
        super(SdlNative.getError());
    }

    public SdlException(String message) {
        super(message);
    }
}
