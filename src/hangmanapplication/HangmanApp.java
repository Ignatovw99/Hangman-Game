package hangmanapplication;

import java.util.Scanner;

public class HangmanApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");
        System.out.println("When you start the game, a random word will be generate for you.\n");
        System.out.println("Rules: \n" +
                "You have to guess the hidden word character by character. \n" +
                "If you guess wrong 10 times, than you lose the game.\n");
        System.out.print("If you want to play type \"yes\": ");
        String command = scanner.nextLine();

        boolean doYouWantToPlay = command.toLowerCase().equals("yes") ? true : false;

        while (doYouWantToPlay){
            System.out.println("Are you ready? Let's play!");
            Hangman game = new Hangman();
            game.play();

            System.out.print("Do you want to play one more game? ");
            command = scanner.nextLine();
            doYouWantToPlay = command.toLowerCase().equals("yes") ? true : false;
        }
    }
}
