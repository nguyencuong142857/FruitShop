package utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getStringCode(String messageInfo, String messageErrorInvalid, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        do {
            System.out.print(messageInfo);
            String input = SCANNER.nextLine();
            if (input.isEmpty()) { // Kiểm tra nếu chuỗi rỗng
                System.err.println(messageErrorInvalid);
                continue;
            }
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                return input;
            }
            System.err.println(messageErrorInvalid);
        } while (true);
    }

    /**
     * Don't let anyone instantiate this class.
     */
    private Validation() {
    }

    /**
     * Returns the valid integer value scanned from the input.
     *
     * @param messageInfo the message to be printed instructing the user to
     * input
     * @param messageErrorOutOfRange the message will be printed if the parsed
     * value is out of range
     * @param messageErrorInvalidNumber the message will be printed if the
     * String does not contain a parsable integer
     * @param min minimum limit value
     * @param max maximum limit value
     * @return the valid integer value scanned from the input
     */
    public static int getInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            int min, int max
    ) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.err.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.err.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    /**
     * Returns the valid string scanned from the input that matches the
     * specified regex pattern.
     *
     * @param messageInfo the message to be printed instructing the user to
     * input
     * @param messageErrorInvalid the message will be printed if the input does
     * not match the regex pattern
     * @param regexPattern the regular expression pattern to match the input
     * against
     * @return the valid string scanned from the input
     */
    public static String getString(
            String messageInfo,
            String messageErrorInvalid,
            String regexPattern
    ) {
        Pattern pattern = Pattern.compile(regexPattern);
        do {
            System.out.print(messageInfo);
            String input = SCANNER.nextLine();
            if (input.isEmpty()) { // Kiểm tra nếu chuỗi rỗng
                System.err.println(messageErrorInvalid);
                continue;
            }
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                return input;
            }
            System.err.println(messageErrorInvalid);
        } while (true);
    }

    /**
     * Returns the valid long value scanned from the input.
     *
     * @param messageInfo the message to be printed instructing the user to
     * input
     * @param messageErrorOutOfRange the message will be printed if the parsed
     * value is out of range
     * @param messageErrorInvalidNumber the message will be printed if the
     * String does not contain a parsable long
     * @param min minimum limit value
     * @param max maximum limit value
     * @return the valid long value scanned from the input
     */
    public static long getLong(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            long min, long max
    ) {
        do {
            try {
                System.out.print(messageInfo);
                long number = Long.parseLong(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.out.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    /**
     * Returns the valid double value scanned from the input.
     *
     * @param messageInfo the message to be printed instructing the user to
     * input
     * @param messageErrorOutOfRange the message will be printed if the parsed
     * value is out of range
     * @param messageErrorInvalidNumber the message will be printed if the
     * String does not contain a parsable double
     * @param min minimum limit value
     * @param max maximum limit value
     * @return the valid double value scanned from the input
     */
    public static double getDouble(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            double min, double max
    ) {
        do {
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.err.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    /**
     * Returns the valid boolean value (true/false) scanned from the input.
     *
     * @param messageInfo the message to be printed instructing the user to
     * input
     * @param messageErrorInvalid the message will be printed if the input is
     * not a valid boolean value
     * @return the valid boolean value scanned from the input
     */
    public static boolean isBoolean(String messageInfo, String messageErrorInvalid) {
        do {
            System.out.print(messageInfo);
            String input = SCANNER.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.err.println(messageErrorInvalid);
        } while (true);
    }
}
