package com.illcode.sdljoystick4java;

import java.nio.ByteBuffer;

/**
 * Direct mappings for JNI methods.
 */
public final class Native
{
    public static native void init();

    public static native void cleanup();

    public static native void update();

    public static native long getDirectByteBufferAddress(ByteBuffer b);

}
