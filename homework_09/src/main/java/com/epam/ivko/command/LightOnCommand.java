package com.epam.ivko.command;

public class LightOnCommand implements Command {
    private Light light;

    LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        System.out.println("Light on");
        light.switchOn();
    }
}