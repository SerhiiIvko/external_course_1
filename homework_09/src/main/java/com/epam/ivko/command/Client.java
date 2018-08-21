package com.epam.ivko.command;

/**
 * Created by ivko on 17.05.18.
 */
public class Client {
    public static void main(String[] args) {
        RemoteControl control = new RemoteControl();
        Light light = new Light();
        Command lightsOn = new LightOnCommand(light);
        Command lightsOff = new LightOffCommand(light);
        control.setCommand(lightsOn);
        control.pressButton();
        control.setCommand(lightsOff);
        control.pressButton();
    }
}