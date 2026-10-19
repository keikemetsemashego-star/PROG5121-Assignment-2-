/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package poepart2;

import java.util.Random;
import java.util.Scanner;

/**
 * Main application for ChatApp Part 2.
 *
 * @author Student
 */


/** Registration, login, and messaging application. */
public class PoePart2 {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("=================================");
            System.out.println("       CHATAPP REGISTRATION");
            System.out.println("=================================");

            System.out.print("Please enter your first name: ");
            String firstName = scan.nextLine();
            System.out.println("First name successfully captured.");

            System.out.print("Please enter your last name: ");
            String lastName = scan.nextLine();
            System.out.println("Last name successfully captured.");

            System.out.print("Please enter the username: ");
            String username = scan.nextLine();
            System.out.println("Username name successfully captured.");

            System.out.print("Please enter the password: ");
            String password = scan.nextLine();
            System.out.println("Password name successfully captured.");

            System.out.print("Please enter your South African cell number (+27...): ");
            String phone = scan.nextLine();
            System.out.println("Cell number successfully captured.");

            Registration registration = new Registration(
                    username, password, phone, firstName, lastName);

            System.out.println(registration.usernameMessage());
            System.out.println(registration.passwordMessage());
            System.out.println(registration.cellPhoneMessage());

            if (!registration.checkUserName()
                    || !registration.checkPasswordComplexity()
                    || !registration.checkCellPhoneNumber()) {
                System.out.println(
                        "Registration unsuccessful. Please correct the information above.");
                return;
            }

            System.out.println("User has been registered successfully.");

            Login login = new Login(username, password);

            System.out.print("Enter your username: ");
            String loginUsername = scan.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = scan.nextLine();

            boolean loggedIn = login.loginUser(loginUsername, loginPassword);
            System.out.println(login.returnLoginStatus(firstName, lastName));

            if (!loggedIn) return;

            System.out.println("Welcome to QuickChat.");
            System.out.print("How many messages would you like to send? ");

            int requested;
            try {
                requested = Integer.parseInt(scan.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a whole number.");
                return;
            }

            Random random = new Random();
            int firstNumber = random.nextInt(90) + 10; // 10–99
int secondNumber = random.nextInt(9) + 1;  // 1–9

System.out.println("Message Hash Number: "
        + firstNumber + ":" + secondNumber);
            while (true) {
                System.out.println();
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Select an option: ");

                String option = scan.nextLine().trim();

                if (option.equals("3")) break;

                if (option.equals("2")) {
                    System.out.println("Coming Soon.");
                    continue;
                }

                if (!option.equals("1")) {
                    System.out.println("Please select 1, 2, or 3.");
                    continue;
                }
                int completed = 0;

                if (completed >= requested) {
                    System.out.println(
                            "You have entered the requested number of messages.");
                    continue;
                }

                while (completed < requested) {
                    System.out.print(
                            "Enter recipient cell number (+27 followed by 9 digits): ");
                    String recipient = scan.nextLine().trim();

                    System.out.print("Enter message (maximum 250 characters): ");
                    String text = scan.nextLine();

                    Message message = new Message(
                            recipient, text, Message.getTotalMessages());

                    System.out.println(
                            "Message ID generated: " + message.getMessageId());

                    if (!message.validateMessageLength()
                            .equals("Message ready to send.")) {
                        System.out.println(message.validateMessageLength());
                        continue;
                    }

                    String recipientStatus = message.checkRecipientCell();
                    if (!recipientStatus.equals(
                            "Cell phone number successfully captured.")) {
                        System.out.println(recipientStatus);
                        continue;
                    }

                    System.out.println(message.details());
                    System.out.println("Message Hash: "
                            + message.getMessageHash());
                    System.out.println(message.SentMessage(scan));
                    completed++;
                }
            }

            System.out.println(
                    "Total messages sent: " + Message.getTotalMessages());
        }
    }
}
    
