package com.illcode.sdljoystick4java;

public class SdlException extends Exception
{
    public SdlException() {
        super(SdlNative.getError());
    }

    public SdlException(String message) {
        super(message);
    }
}
