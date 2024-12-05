package algo6_0.dz3;

import java.io.*;
import java.util.*;

public class MinimumPSPJava {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine().trim());
        String weight = reader.readLine().trim();
        String startSubSequence = reader.readLine().trim();

        writer.write(resultSequence(n, weight, startSubSequence));

        reader.close();
        writer.close();
    }

    private static String resultSequence(int n, String weight, String startSubSequence) {
        if (!startSubSequence.isBlank()) {
            if (n == startSubSequence.length()) return startSubSequence;
        }

        StringBuilder result = new StringBuilder(startSubSequence);
        Stack<Character> endStack = new Stack<>();
        Map<Character, Integer> bracketsWeight = new HashMap<>();

        for (int i = 0; i < weight.length(); i++) {
            bracketsWeight.put(weight.charAt(i), i);
        }

        if (!startSubSequence.isBlank()) {
            for (char c : startSubSequence.toCharArray()) {
                switch (c) {
                    case '(' -> endStack.push(')');
                    case '[' -> endStack.push(']');
                    case ')', ']' -> endStack.pop();
                }
            }
        }

        while (result.length() + endStack.size() < n) {
            char lastChar = ')';
            if (!startSubSequence.isBlank()) {
                lastChar = result.charAt(result.length() - 1);
            }
            char addedValue;

            if (isOpen(lastChar)) {
                if (bracketsWeight.get(suitable(lastChar)) < bracketsWeight.get('(') &&
                        bracketsWeight.get(suitable(lastChar)) < bracketsWeight.get('[')) {
                    addedValue = suitable(lastChar);
                } else {
                    addedValue = bracketsWeight.get('(') < bracketsWeight.get('[') ? '(' : '[';
                }
            } else {
                if (!endStack.isEmpty() && bracketsWeight.get(endStack.peek()) < bracketsWeight.get('(') &&
                        bracketsWeight.get(endStack.peek()) < bracketsWeight.get('[')) {
                    addedValue = endStack.peek();
                } else {
                    addedValue = bracketsWeight.get('(') < bracketsWeight.get('[') ? '(' : '[';
                }
            }

            if (isOpen(addedValue)) {
                char suit = suitable(addedValue);
                result.append(addedValue);
                endStack.push(suit);
            } else {
                result.append(addedValue);
            }

            if (!endStack.isEmpty() && endStack.peek() == result.charAt(result.length() - 1)) {
                endStack.pop();
            }
        }

        while (!endStack.isEmpty()) {
            result.append(endStack.pop());
        }

        return result.toString();
    }

    private static boolean isOpen(char c) {
        return c == '(' || c == '[';
    }

    private static char suitable(char c) {
        return switch (c) {
            case '(' -> ')';
            case '[' -> ']';
            default -> '\0';
        };
    }
}

