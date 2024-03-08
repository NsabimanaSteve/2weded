/*
 * PasswordGenerator.java
 * 
 * This program creates random passwords based on your preferences. 
 * You set the minimum length, uppercase, digits, and special characters.
 * It then generates a password and checks if it meets your criteria.
 * 
 * Author: Steve Nsabimana
 * Date: Feb/2/2024
 */

import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator 
{

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        // Read user input
        System.out.print("Enter minimum password length: ");
        int minLength = scanner.nextInt();

        System.out.print("Enter minimum number of uppercase characters: ");
        int minUpperCase = scanner.nextInt();

        System.out.print("Enter minimum number of digits: ");
        int minDigits = scanner.nextInt();

        System.out.print("Enter minimum number of special characters: ");
        int minSpecialChars = scanner.nextInt();

        System.out.print("Enter allowed special characters such one of them &!#@: ");
        String specialChars = scanner.next();

        // Generate and check password
        String password = generatePassword(minLength, minUpperCase, minDigits, minSpecialChars, specialChars);
        System.out.println("Generated password: " + password);

        boolean isValid = checkPassword(password, minLength, minUpperCase, minDigits, minSpecialChars, specialChars);
        System.out.println("Is the password valid? " + isValid);

        scanner.close();
    }

    // Method to generate a random password
    public static String generatePassword(int minLength, int minUpperCase, int minDigits, int minSpecialChars, String specialChars) 
    {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        // Generate uppercase characters
        for (int i = 0; i < minUpperCase; i++) 
        {
            password.append((char) ('A' + random.nextInt(26)));
        }

        // Generate digits
        for (int i = 0; i < minDigits; i++) 
        {
            password.append(random.nextInt(10));
        }

        // Generate special characters
        for (int i = 0; i < minSpecialChars; i++) 
        {
            password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        }

        // Fill remaining characters with random ones
        while (password.length() < minLength) 
        {
            int type = random.nextInt(3);
            if (type == 0) 
            {
                password.append((char) ('a' + random.nextInt(26)));
            } else if (type == 1) 
            {
                password.append((char) ('A' + random.nextInt(26)));
            } else 
            {
                password.append(random.nextInt(10));
            }
        }

        // Shuffle the password characters
        for (int i = password.length() - 1; i > 0; i--) 
        {
            int index = random.nextInt(i + 1);
            char temp = password.charAt(index);
            password.setCharAt(index, password.charAt(i));
            password.setCharAt(i, temp);
        }

        return password.toString();
    }

    // Method to check if a password is valid
    public static boolean checkPassword(String password, int minLength, int minUpperCase, int minDigits, int minSpecialChars, String specialChars) 
    {
        int upperCaseCount = 0;
        int digitCount = 0;
        int specialCharCount = 0;

        char[] passwordChars = password.toCharArray();

        for (int i = 0; i < passwordChars.length; i++) {
            char ch = passwordChars[i];
        
            if (Character.isUpperCase(ch)) 
            {
                upperCaseCount++;
            } else if (Character.isDigit(ch))
            {
                digitCount++;
            } else if (specialChars.contains(String.valueOf(ch))) 
            {
                specialCharCount++;
            }
        }
        

        return password.length() >= minLength && upperCaseCount >= minUpperCase && digitCount >= minDigits && specialCharCount >= minSpecialChars;
    }
}
