package com.illcode.sdljoystick4java.test;

import com.illcode.sdljoystick4java.*;

public class BasicTests
{
    private static void testJoystick(String[] args) throws SdlException, InterruptedException {
        if (Joystick.numJoysticks() > 0) {
            System.out.println("GUID of device 0: " + Joystick.getGUIDString(Joystick.getGUID(0)));
            final Joystick joystick = new Joystick(0);
            final String guidString = Joystick.getGUIDString(joystick.getGUID());
            System.out.println("GUID of joystick: " + guidString);
            System.out.println("  Roundtrip GUID: " + Joystick.getGUIDString(Joystick.getGUID(guidString)));
            joystick.setTransitionDetectionEnabled(true);
            System.out.println();
            System.out.println(joystick.toString());
            if (args.length >= 1) {
                System.out.println();
                int n = Integer.parseInt(args[0]);
                Thread.sleep(200);
                iterationLoop:
                for (int i = 0; i < n; i++) {
                    joystick.update(true);
                    if (!joystick.isAttached()) {
                        System.out.println("Joystick disconnected!");
                        break iterationLoop;
                    }
                    for (int button = 0; button < joystick.getNumButtons(); button++) {
                        System.out.print(joystick.getButton(button) ? "1" : "0");
                        if (joystick.buttonPressed(button))
                            System.out.print("P");
                        else if (joystick.buttonReleased(button))
                            System.out.print("R");
                        else
                            System.out.print(" ");
                        System.out.print(" ");
                    }
                    for (int axis = 0; axis < joystick.getNumAxes(); axis++)
                        System.out.printf("%+6d ", joystick.getAxis(axis));
                    System.out.println();
                    Thread.sleep(500);
                }
            }
            joystick.close();
        }
    }

    private static void testGameController(String[] args) throws SdlException, InterruptedException {
        if (args.length < 3) {
            System.out.println("Usage: GameController <deviceIdx> axes|buttons <num iterations>");
            System.exit(0);
        }
        if (Joystick.numJoysticks() > 0) {
            int numMappings = GameController.addMappingsFromFile("resources/gamecontrollerdb.txt");
            if (numMappings > 0)
                System.out.printf("Loaded %d controller mappings\n\n", numMappings);
            int deviceIdx = Integer.parseInt(args[0]);
            String cmd = args[1];
            int n = Integer.parseInt(args[2]);
            final GameController controller = new GameController(deviceIdx);
            System.out.println("GameController - " + controller.getName());
            Thread.sleep(200);
            switch (cmd) {
            case "buttons":
                iterationLoop:
                for (int i = 0; i < n; i++) {
                    controller.update(true);
                    if (!controller.isAttached()) {
                        System.out.println("Controller disconnected!");
                        break iterationLoop;
                    }
                    if (i % 10 == 0) {
                        System.out.println("---------------------------------------------------------------------");
                        for (String sn : SdlConstants.SHORT_BUTTON_NAMES)
                            System.out.printf("%3s ", sn);
                        System.out.println("\n---------------------------------------------------------------------");
                    }
                    for (int button = 0; button < SdlConstants.SDL_CONTROLLER_BUTTON_MAX; button++) {
                        System.out.print(controller.getButton(button) ? " 1" : " 0");
                        if (controller.buttonPressed(button))
                            System.out.print("P");
                        else if (controller.buttonReleased(button))
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
                iterationLoop:
                for (int i = 0; i < n; i++) {
                    controller.update(true);
                    if (!controller.isAttached()) {
                        System.out.println("Controller disconnected!");
                        break iterationLoop;
                    }
                    if (i % 10 == 0) {
                        System.out.println("---------------------------------------------------------------------");
                        for (String sn : SdlConstants.SHORT_AXIS_NAMES)
                            System.out.printf("%6s  ", sn);
                        System.out.println("\n---------------------------------------------------------------------");
                    }
                    for (int axis = 0; axis < SdlConstants.SDL_CONTROLLER_AXIS_MAX; axis++)
                        System.out.printf("%+6d  ", controller.getAxis(axis));
                    System.out.println();
                    Thread.sleep(500);
                }
                break;
            }
            controller.close();
        }
    }

    private static void testNativeStructInfo() {
        int[] info = SdlNative.gameControllerGetStateInfo();
        System.out.println("sizeof(GameControllerState)               = " + info[0]);
        System.out.println("offsetof(GameControllerState, buttonVals) = " + info[1]);
    }

    public static void main(String[] args) throws SdlException, InterruptedException {
        if (args.length == 0) {
            showHelp();
            return;
        }
        SdlNative.loadNative(true);
        SdlNative.initJoysticks();

        final int n = args.length - 1;
        String[] args2 = new String[n];
        System.arraycopy(args, 1, args2, 0, n);
        String lcarg = args[0].toLowerCase();
        try {
            switch (lcarg) {
            case "joystick":
                testJoystick(args2);
                break;
            case "gamecontroller":
                SdlNative.initGameControllers();
                testGameController(args2);
                break;
            case "structinfo":
                testNativeStructInfo();
                break;
            default:
                showHelp();
                break;
            }
        } finally {
            SdlNative.cleanup();
        }
    }

    private static void showHelp() {
        System.out.println(
            "Usage: BasicTests Joystick [num iterations]\n" +
            "                  GameController <deviceIdx> axes|buttons <num iterations>"
        );
    }
}
