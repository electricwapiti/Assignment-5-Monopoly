package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        CircularlyLinkedList<Space> board = new CircularlyLinkedList<>();
        String path = "../monopoly_spaces.txt";

        // Load board from file
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                board.addLast(new Space(line.trim()));
            }
        } catch (IOException e) {
            System.err.println("Failed to read " + path + ": " + e.getMessage());
            return;
        }

        System.out.println("Monopoly board loaded! Head: " + board.first() + ", Tail: " + board.last());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1) Print all spaces");
            System.out.println("2) Roll dice and move");
            System.out.println("3) Step forward one space");
            System.out.println("4) Exit");

            System.out.print("> ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    printBoard(board);
                    break;
                case "2":
                    Space landed = board.advanceByDice();
                    System.out.println("Landed on: " + landed.getName());
                    break;
                case "3":
                    board.rotate();
                    System.out.println("Stepped forward. Current space: " + board.first().getName());
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void printBoard(CircularlyLinkedList<Space> board) {
        System.out.println("\nMonopoly Board Spaces:");
        Node<Space> current = board.getTail().getNext(); // head
        for (int i = 0; i < board.size(); i++) {
            System.out.println((i + 1) + ": " + current.getElement().getName());
            current = current.getNext();
        }
    }
}