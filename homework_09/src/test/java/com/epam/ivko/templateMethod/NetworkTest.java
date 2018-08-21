package com.epam.ivko.templateMethod;

import org.junit.Assert;
import org.junit.Test;

public class NetworkTest {

    @Test
    public void postMessageOnFacebook() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Facebook(username, password);
        String expectedMessage = "expectedMessage";
        boolean isMessagePosted;

        //WHEN:
        isMessagePosted = network.post(expectedMessage);

        //THEN:
        Assert.assertTrue(isMessagePosted);
    }

    @Test
    public void logInOnFacebook() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Facebook(username, password);
        boolean isLogin;

        //WHEN:
        isLogin = network.logIn(username, password);

        //THEN:
        Assert.assertTrue(isLogin);
    }

    @Test
    public void sendDataOnFacebook() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Facebook(username, password);
        String expectedMessage = "expectedMessage";
        byte[] messageAsByteArray = expectedMessage.getBytes();
        boolean isDataSent;

        //WHEN:
        isDataSent = network.sendData(messageAsByteArray);

        //THEN:
        Assert.assertTrue(isDataSent);
    }

    @Test
    public void logOutFromFacebook() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Facebook(username, password);
        String expectedLogOutMessage = "User: '" + username + "' was logged out from Facebook";
        String actualLogoutMessage;

        //WHEN:
        actualLogoutMessage = network.logOut();

        //THEN:
        Assert.assertEquals(expectedLogOutMessage, actualLogoutMessage);
    }

    @Test
    public void postMessageOnTwitter() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Twitter(username, password);
        String expectedMessage = "expected message";
        boolean isMessagePosted;

        //WHEN:
        isMessagePosted = network.post(expectedMessage);

        //THEN:
        Assert.assertTrue(isMessagePosted);
    }

    @Test
    public void logInOnTwitter() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Twitter(username, password);
        boolean isLogin;

        //WHEN:
        isLogin = network.logIn(username, password);

        //THEN:
        Assert.assertTrue(isLogin);
    }

    @Test
    public void sendDataOnTwitter() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Twitter(username, password);
        String expectedMessage = "expectedMessage";
        byte[] messageAsByteArray = expectedMessage.getBytes();
        boolean isDataSent;

        //WHEN:
        isDataSent = network.sendData(messageAsByteArray);

        //THEN:
        Assert.assertTrue(isDataSent);
    }

    @Test
    public void logOutFromTwitter() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Twitter(username, password);
        String expectedLogOutMessage = "User: '" + username + "' was logged out from Twitter";
        String actualLogoutMessage;

        //WHEN:
        actualLogoutMessage = network.logOut();

        //THEN:
        Assert.assertEquals(expectedLogOutMessage, actualLogoutMessage);
    }

    @Test
    public void postMessageOnTelegram() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Telegram(username, password);
        String expectedMessage = "expectedMessage";
        boolean isMessagePosted;

        //WHEN:
        isMessagePosted = network.post(expectedMessage);

        //THEN:
        Assert.assertTrue(isMessagePosted);
    }

    @Test
    public void logInOnTelegram() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Telegram(username, password);
        boolean isLogin;

        //WHEN:
        isLogin = network.logIn(username, password);

        //THEN:
        Assert.assertTrue(isLogin);
    }

    @Test
    public void sendDataOnTelegram() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Telegram(username, password);
        String expectedMessage = "expectedMessage";
        byte[] messageAsByteArray = expectedMessage.getBytes();
        boolean isDataSent;

        //WHEN:
        isDataSent = network.sendData(messageAsByteArray);

        //THEN:
        Assert.assertTrue(isDataSent);
    }

    @Test
    public void logOutFromTelegram() {
        //GIVEN:
        String username = "user";
        String password = "root";
        Network network = new Telegram(username, password);
        String expectedLogOutMessage = "User: '" + username + "' was logged out from Telegram";
        String actualLogoutMessage;

        //WHEN:
        actualLogoutMessage = network.logOut();

        //THEN:
        Assert.assertEquals(expectedLogOutMessage, actualLogoutMessage);
    }
}