package com.example.videosharingapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UsersTest {
    @Test
    public void testConstructorWithParameters() {
        // Arrange
        String userID = "123";
        String name = "John Doe";
        String profile = "profile.png";
        // Act
        Users user = new Users(userID, name, profile);
        // Assert
        assertNotNull(user);
        assertEquals(userID, user.getUserID());
        assertEquals(name, user.getName());
        assertEquals(profile, user.getProfile());
    }
    @Test
    public void testDefaultConstructor() {
        // Act
        Users user = new Users();
        // Assert
        assertNotNull(user);
    }
    @Test
    public void testSettersAndGetters() {
        // Arrange
        Users user = new Users();
        // Act
        String userID = "456";
        String name = "Jane Smith";
        String profile = "profile.jpg";
        user.setUserID(userID);
        user.setName(name);
        user.setProfile(profile);
        // Assert
        assertEquals(userID, user.getUserID());
        assertEquals(name, user.getName());
        assertEquals(profile, user.getProfile());
    }
}

