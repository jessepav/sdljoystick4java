package com.illcode.sdljoystick4java;

public class SdlException extends Exception
{
    public SdlException() {
        super(Native.getError());
    }

    public SdlException(String message) {
        super(message);
    }
}
