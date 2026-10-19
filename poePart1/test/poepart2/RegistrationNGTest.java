package poepart2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**

/** Tests registration validation and messages. */
public class RegistrationNGTest {
    private static int passed;
    private static int failed;

    public static void main(String[] args) {
        RegistrationNGTest good = new Registration(
                "kyl_1",
                "Ch&j89@ke99!",
                "+27838968976",
                "Keikemetse",
                "Mashego"
        );

        // Username tests
        check(good.checkUserName(), "valid username");

        RegistrationNGTest badUsername = new Registration(
                "Kyle!!!!!!!",
                "Ch&j89@ke99!",
                "+27838968976",
                "Keikemetse",
                "Mashego"
        );
        check(!badUsername.checkUserName(), "invalid username");

        // Password tests
        check(good.checkPasswordComplexity(), "valid password");

        Registration badPassword = new Registration(
                "kyl_1",
                "password",
                "+27838968976",
                "Keikemetse",
                "Mashego"
        );
        check(!badPassword.checkPasswordComplexity(), "invalid password");

        // Cell phone tests
        check(good.checkCellPhoneNumber(), "valid cell phone number");

        Registration badPhone = new Registration(
                "kyl_1",
                "Ch&j89@ke99!",
                "08966553",
                "Keikemetse",
                "Mashego"
        );
        check(!badPhone.checkCellPhoneNumber(), "invalid cell phone number");

        // Name tests
        check(good.checkFirstName(), "valid first name");
        check(good.checkLastName(), "valid last name");

        RegistrationNGTest missingFirstName = new Registration(
                "kyl_1",
                "Ch&j89@ke99!",
                "+27838968976",
                "",
                "Mashego"
        );
        check(!missingFirstName.checkFirstName(), "missing first name");

        RegistrationNGTest missingLastName = new Registration(
                "kyl_1",
                "Ch&j89@ke99!",
                "+27838968976",
                "Keikemetse",
                ""
        );
        check(!missingLastName.checkLastName(), "missing last name");

        // Success message tests
        check(good.firstNameMessage().equals(
                "First name successfully captured."),
                "first name success message");

        check(good.lastNameMessage().equals(
                "Last name successfully captured."),
                "last name success message");

        check(good.usernameMessage().equals(
                "Username successfully captured."),
                "username success message");

        check(good.passwordMessage().equals(
                "Password successfully captured."),
                "password success message");

        check(good.cellPhoneMessage().equals(
                "Cell number successfully captured."),
                "cell phone success message");

        // Error message tests
        check(badUsername.usernameMessage().contains(
                "no more than five characters"),
                "username error message");

        check(badPassword.passwordMessage().contains(
                "at least eight characters"),
                "password error message");

        check(badPhone.cellPhoneMessage().contains(
                "international code"),
                "cell phone error message");

        check(missingFirstName.firstNameMessage().contains(
                "First name is required"),
                "missing first name message");

        check(missingLastName.lastNameMessage().contains(
                "Last name is required"),
                "missing last name message");

        // Full registration message
        check(good.registerUser().equals(
                "User has been registered successfully."),
                "successful registration message");

        System.out.println();
        System.out.println("==========================");
        System.out.println("       TEST RESULTS");
        System.out.println("==========================");
        System.out.println("Tests passed: " + passed);
        System.out.println("Tests failed: " + failed);

        if (failed > 0) {
            throw new AssertionError("One or more tests failed.");
        }

        System.out.println("All tests passed successfully!");
    }

    private static void check(boolean result, String label) {
        if (result) {
            passed++;
            System.out.println("PASS: " + label);
        } else {
            failed++;
            System.out.println("FAIL: " + label);
        }
    }
}