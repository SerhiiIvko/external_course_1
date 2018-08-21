package com.epam.ivko.command;

import org.junit.Assert;
import org.junit.Test;

public class CommandTest {

    @Test
    public void lightOnCommandTest() {
        //GIVEN:
        RemoteControl remoteControl = new RemoteControl();
        Light light = new Light();
        Command lightOnCommand = new LightOnCommand(light);
        remoteControl.setCommand(lightOnCommand);

        //WHEN:
        remoteControl.pressButton();

        //THEN:
        Assert.assertTrue(light.isLightOn());
    }

    @Test
    public void lightOffCommandTest() {
        //GIVEN:
        RemoteControl remoteControl = new RemoteControl();
        Light light = new Light();
        Command lightOffCommandCommand = new LightOffCommand(light);
        remoteControl.setCommand(lightOffCommandCommand);

        //WHEN:
        remoteControl.pressButton();

        //THEN:
        Assert.assertFalse(light.isLightOn());
    }
}