/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poepart2;

/**
 * Handles user registration and validation.
 *
 * @author Student
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Creates, validates, sends, stores, and lists chat messages. */
public final class Message {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final List<String> SENT_MESSAGES = new ArrayList<>();
    private static int totalMessages;

    private final String messageId;
    private final int messageNumber;
    private final String recipient;
    private final String text;
    private final String messageHash;
    private String actionStatus = "";

    public Message(String recipient, String text) {
        this(recipient, text, totalMessages);
    }

    public Message(String recipient, String text, int sequenceNumber) {
        this.messageId = String.format(
                "%010d", RANDOM.nextInt(1_000_000_000));
        this.messageNumber = sequenceNumber;
        this.recipient = recipient;
        this.text = text;
        this.messageHash = createMessageHash();
    }

    public String getMessageId() { return messageId; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getText() { return text; }
    public String getMessageHash() { return messageHash; }
    public String getActionStatus() { return actionStatus; }

    public boolean checkMessageID() {
        return messageId != null && messageId.matches("[0-9]{10}");
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+27[0-9]{9}$")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not "
                + "contain an international code. Please correct the number "
                + "and try again.";
    }

    public String createMessageHash() {
        if (messageId == null || text == null || text.isBlank()) {
            return "";
        }

        String[] words = text.trim().split("\\s+");
        String first = words[0].replaceAll("[^\\p{L}\\p{N}]", "");
        String last = words[words.length - 1]
                .replaceAll("[^\\p{L}\\p{N}]", "");

        return (messageId.substring(0, 2) + ":" + messageNumber + ":"
                + first + last).toUpperCase();
    }

    public String validateMessageLength() {
        if (text == null || text.length() > 250) {
            int length = text == null ? 0 : text.length();
            return "Message exceeds 250 characters by "
                    + Math.max(0, length - 250)
                    + "; please reduce the size.";
        }
        return "Message ready to send.";
    }

    /** Prompts the user: 1 send, 2 disregard, or 3 store.
     * @param input
     * @return  */
    public String SentMessage(Scanner input) {
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");

        String choice = input.nextLine().trim();

        switch (choice) {
            case "1" -> {
                actionStatus = "Message successfully sent.";
                SENT_MESSAGES.add(details());
                totalMessages++;
            }
            case "2" -> actionStatus = "Press 0 to delete the message.";
            case "3" -> {
                try {
                    storeMessage(Paths.get("messages.json"));
                    actionStatus = "Message successfully stored.";
                } catch (IOException exception) {
                    actionStatus = "Message could not be stored: "
                            + exception.getMessage();
                }
            }
            default -> actionStatus = "Invalid option. Message was not sent.";
        }

        return actionStatus;
    }

    public String printMessages() {
        return String.join(System.lineSeparator(), SENT_MESSAGES);
    }

    public int returnTotalMessages() {
        return totalMessages;
    }

    public static int getTotalMessages() {
        return totalMessages;
    }

    public String details() {
        return "Message ID: " + messageId
                + System.lineSeparator()
                + "Message Hash: " + messageHash
                + System.lineSeparator()
                + "Recipient: " + recipient
                + System.lineSeparator()
                + "Message: " + text;
    }

    /** Appends this message to a JSON array file.
     * @param path
     * @throws java.io.IOException */
    public void storeMessage(Path path) throws IOException {
        String object = "{\"messageId\":\"" + escape(messageId)
                + "\",\"messageHash\":\"" + escape(messageHash)
                + "\",\"recipient\":\"" + escape(recipient)
                + "\",\"message\":\"" + escape(text) + "\"}";

        String existing = Files.exists(path)
                ? Files.readString(path, StandardCharsets.UTF_8).trim()
                : "";

        String content;
        if (existing.isEmpty() || existing.equals("[]")) {
            content = "[" + object + "]";
        } else if (existing.endsWith("]")) {
            content = existing.substring(0, existing.length() - 1)
                    + "," + object + "]";
        } else {
            throw new IOException("Existing JSON file is not a JSON array.");
        }

        Files.writeString(
                path,
                content,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static String escape(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
