package com.epam.ivko.command;

class Light {
    private boolean lightOn;

    void switchOn() {
        lightOn = true;
    }

    void switchOff() {
        lightOn = false;
    }

    boolean isLightOn() {
        return lightOn;
    }
}