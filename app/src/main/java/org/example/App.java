package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        CircularlyLinkedList<Space> board = new CircularlyLinkedList<>();

        String path = "../monopoly_spaces.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                // skip empty lines
                if (line.trim().isEmpty()) continue;
                board.addLast(new Space(line.trim()));
            }
        } catch (IOException e) {
            System.err.println("Failed to read " + path + ": " + e.getMessage());
            return;
        }

        // Verify head (first) and tail (last)
        Space head = board.first();
        Space tail = board.last();

        System.out.println("Head (should be 'Go'): " + (head != null ? head.getName() : "null"));
        System.out.println("Tail (should be 'Boardwalk'): " + (tail != null ? tail.getName() : "null"));

        // Roll two dice and determine landing space from 'Go' (index 0)
        int die1 = rollDie();
        int die2 = rollDie();
        int steps = die1 + die2;
        System.out.println("Rolled: " + die1 + " + " + die2 + " = " + steps);

        // starting from head (index 0)
        Space landed = board.get(steps % board.size());
        System.out.println("Landed on: " + (landed != null ? landed.getName() : "null"));
    }

    private static final Random RNG = new Random();

    private static int rollDie() {
        return RNG.nextInt(6) + 1;
    }
}
