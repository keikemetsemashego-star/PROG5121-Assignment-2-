/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poepart2;

/**
 * Handles user login authentication.
 *
 * @author Student
 */
public class Login {

    private final String registeredUsername;
    private final String registeredPassword;

    private boolean loginSuccessful;

    /**
     * Constructor.
     *
     * Stores the username and password created during registration.
     * @param registeredUsername
     * @param registeredPassword
     */
    public Login(String registeredUsername, String registeredPassword) {

        this.registeredUsername = registeredUsername;
        this.registeredPassword = registeredPassword;

        this.loginSuccessful = false;
    }

    /**
     * Checks whether the entered username and password
     * match the registered details.
     * @param username
     * @param password
     * @return 
     */
    public boolean loginUser(String username, String password) {

        loginSuccessful = registeredUsername != null
                && registeredPassword != null
                && registeredUsername.equals(username)
                && registeredPassword.equals(password);

        return loginSuccessful;
    }

    /**
     * Returns the appropriate login message.
     * @param firstName
     * @param lastName
     * @return 
     */
    public String returnLoginStatus(String firstName, String lastName) {

        if (loginSuccessful) {

            return "Welcome "
                    + firstName
                    + " "
                    + lastName
                    + ", it is great to see you again.";

        } else {

            return "Username or password incorrect, "
                    + "please try again.";
        }
    }
}


