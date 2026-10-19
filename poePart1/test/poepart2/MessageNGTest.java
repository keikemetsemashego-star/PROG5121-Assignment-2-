/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/** Part 2 message validation, hash, action, and storage tests. */
public class MessageNGTest {
    private static int passed;
    private static int failed;

    public static void main(String[] args) throws Exception {
        Message valid = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                0
        );

        check(valid.checkMessageID(),
                "message ID is ten digits");

        check(valid.checkRecipientCell().equals(
                "Cell phone number successfully captured."),
                "valid recipient message");

        check(valid.getMessageHash().matches(
                "[0-9A-F]{2}:0:HINIGHT"),
                "hash contains ID prefix, message number, first and last words");

        Message badPhone = new Message(
                "08575975889",
                "Short message",
                0
        );

        check(badPhone.checkRecipientCell().startsWith(
                "Cell phone number is incorrectly formatted"),
                "invalid recipient message");

        check(valid.validateMessageLength().equals(
                "Message ready to send."),
                "message within character limit");

        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 251; i++) {
            longText.append('x');
        }

        Message tooLong = new Message(
                "+27718693002",
                longText.toString(),
                0
        );

        check(tooLong.validateMessageLength().equals(
                "Message exceeds 250 characters by 1; please reduce the size."),
                "message over limit reports excess characters");

        int before = Message.getTotalMessages();

        try (Scanner sendInput = new Scanner("1")) {
            check(valid.SentMessage(sendInput).equals(
                    "Message successfully sent."),
                    "send action message");
        }

        check(Message.getTotalMessages() == before + 1,
                "sending increments total");

        Message discard = new Message(
                "+27718693002",
                "Discard me",
                1
        );

        try (Scanner discardInput = new Scanner("2")) {
            check(discard.SentMessage(discardInput).equals(
                    "Press 0 to delete the message."),
                    "disregard action message");
        }

        Message stored = new Message(
                "+27718693002",
                "Store me",
                1
        );

        Path json = Files.createTempFile("chatapp-messages", ".json");

        try {
            stored.storeMessage(json);

            String jsonContents = new String(
                    Files.readAllBytes(json),
                    StandardCharsets.UTF_8
            );

            check(jsonContents.contains("\"message\":\"Store me\""),
                    "JSON contains stored message details");
        } finally {
            Files.deleteIfExists(json);
        }

        System.out.println();
        System.out.println("Tests passed: " + passed);
        System.out.println("Tests failed: " + failed);

        if (failed > 0) {
            throw new AssertionError("One or more message tests failed.");
        }
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
