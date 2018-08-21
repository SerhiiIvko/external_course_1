package com.epam.ivko.command;

public class LightOffCommand implements Command {
    private Light light;

    LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        System.out.println("Light off");
        light.switchOff();
    }
}