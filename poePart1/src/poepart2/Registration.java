/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poepart2;

/**
 * Handles user registration and validation.
 */
public class Registration {

    private String username;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;

    // Default constructor
    public Registration(String kyl_1, String chj89ke99, String string) {
    }

    // Constructor
    public Registration(String username, String password,
                        String cellPhone, String firstName,
                        String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Checks that the username contains an underscore
     * and is no more than 5 characters long.
     * @return 
     */
    public boolean checkUserName() {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    /**
     * Checks password complexity.
     * @return 
     */
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");

        return hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Checks the South African cell phone number.
     * @return 
     */
    public boolean checkCellPhoneNumber() {
        return cellPhone != null
                && cellPhone.matches("^\\+27[0-9]{9}$");
    }

    /**
     * Checks that the first name is not empty.
     * @return 
     */
    public boolean checkFirstName() {
        return firstName != null
                && !firstName.trim().isEmpty();
    }

    /**
     * Checks that the last name is not empty.
     * @return 
     */
    public boolean checkLastName() {
        return lastName != null
                && !lastName.trim().isEmpty();
    }

    /**
     * Returns the appropriate registration message.
     * @return 
     */
    public String registerUser() {

        if (!checkFirstName()) {
            return "First name is required. Please enter your first name.";
        }

        if (!checkLastName()) {
            return "Last name is required. Please enter your last name.";
        }

        if (!checkUserName()) {
            return "Username is not correctly formatted; "
                    + "please ensure that your username contains an "
                    + "underscore and is no more than five characters "
                    + "in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; "
                    + "please ensure that the password contains at least "
                    + "eight characters, a capital letter, a number, "
                    + "and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number is incorrectly formatted or "
                    + "does not contain international code; please "
                    + "correct the number and try again.";
        }

        return "User has been registered successfully.";
    }

    /**
     * First name message.
     * @return 
     */
    public String firstNameMessage() {
        if (checkFirstName()) {
            return "First name successfully captured.";
        }

        return "First name is required. Please enter your first name.";
    }

    /**
     * Last name message.
     * @return 
     */
    public String lastNameMessage() {
        if (checkLastName()) {
            return "Last name successfully captured.";
        }

        return "Last name is required. Please enter your last name.";
    }

    /**
     * Username message.
     * @return 
     */
    public String usernameMessage() {
        if (checkUserName()) {
            return "Username successfully captured.";
        }

        return "Username is not correctly formatted; "
                + "please ensure that your username contains an "
                + "underscore and is no more than five characters "
                + "in length.";
    }

    /**
     * Password message.
     * @return 
     */
    public String passwordMessage() {
        if (checkPasswordComplexity()) {
            return "Password successfully captured.";
        }

        return "Password is not correctly formatted; "
                + "please ensure that the password contains at least "
                + "eight characters, a capital letter, a number, "
                + "and a special character.";
    }

    /**
     * Cell phone message.
     * @return 
     */
    public String cellPhoneMessage() {
        if (checkCellPhoneNumber()) {
            return "Cell number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or "
                + "does not contain international code; please "
                + "correct the number and try again.";
    }
}
