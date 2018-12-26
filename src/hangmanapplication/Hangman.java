package hangmanapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private Scanner scanner;
    private String[] wordsInDatabase = {"time", "person", "woman", "number", "hangman", "eye", "world", "government"};
    private String wordToGuess;
    private StringBuilder hiddenWord;
    private List<Character> guessedCharacters;
    private final int maxWrongAnswersCount = 10;
    private int wrongAnswersCounter = 0;

    public Hangman(){
        scanner = new Scanner(System.in);
        guessedCharacters = new ArrayList<>();
    }

    public void play(){
        this.wordToGuess = setWordToGuess();
        this.hiddenWord = initializeHiddenWord();

        do{
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("Hidden word: " + this.hiddenWord);
            System.out.println(String.format("Already guessed letters: %s",getGuessedCharacters()));

            guessCharacter();

            System.out.println(drawPicture());
            System.out.println(String.format("Wrong guesses: %d\n", this.wrongAnswersCounter));
        } while (!gameOver());
    }

    private void guessCharacter() {
        System.out.print("Enter character, that you might think is inside the word: ");
        char currentGuessedChar = scanner.nextLine().toLowerCase().charAt(0);

        while (isCharacterGuessedAlready(currentGuessedChar)){
            System.out.print("Try again! You have already guessed that character. Please, enter character: ");
            currentGuessedChar = scanner.nextLine().toLowerCase().charAt(0);
        }

        System.out.println();

        if (ifWordToGuessContainsCharacter(currentGuessedChar)){
            System.out.println("Excellent! The word contains the character.");
            this.hiddenWord = addGuessedCharacter(currentGuessedChar);
        } else {
            System.out.println("Unfortunately the word doesn't contain this character.");
            this.wrongAnswersCounter++;
        }
        this.guessedCharacters.add(currentGuessedChar);
    }

    private StringBuilder addGuessedCharacter(char currentGuessedChar) {
        StringBuilder stringBuilder = new StringBuilder(this.hiddenWord);
        for (int i = 0; i < this.wordToGuess.length(); i++){
            if (this.wordToGuess.charAt(i) == currentGuessedChar){
                stringBuilder.replace(i * 2, i * 2 + 1, String.valueOf(currentGuessedChar));
            }
        }
        return stringBuilder;
    }

    private boolean ifWordToGuessContainsCharacter(char currentGuessedChar) {
        for (int i = 0; i < this.wordToGuess.length(); i++){
            if (this.wordToGuess.charAt(i) == currentGuessedChar){
                return true;
            }
        }
        return false;
    }

    private boolean isCharacterGuessedAlready(char character) {
        return this.guessedCharacters.contains(character);
    }

    private boolean gameOver() {
        if (wrongAnswersCounter >= maxWrongAnswersCount || isWordGuessed()){
            if (isWordGuessed()){
                System.out.println(String.format("Congratulations! You guessed the word! - %s", this.wordToGuess));
            } else {
                System.out.println("Game over! Yon don't have more tries to guess the word!");
                System.out.println(String.format("The word, which you had to guess is \"%s\"", this.wordToGuess));
            }
            return true;
        }
        return false;
    }

    private boolean isWordGuessed() {
        return !this.hiddenWord.toString().contains("_");
    }

    private String getGuessedCharacters(){
        StringBuilder stringBuilder = new StringBuilder();

        for (Character guessedCharacter : this.guessedCharacters) {
            stringBuilder.append(guessedCharacter).append(" ");
        }

        return stringBuilder.toString();
    }

    private StringBuilder initializeHiddenWord() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < wordToGuess.length() * 2; i++){
            if (i % 2 == 0){
                stringBuilder.append('_');
            } else {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder;
    }

    private String setWordToGuess() {
        Random random = new Random();
        int wordIndex = random.nextInt(this.wordsInDatabase.length);
        return this.wordsInDatabase[wordIndex];
    }

    private String drawPicture(){
        switch (wrongAnswersCounter){
            case 0:
                return initialDraw();
            case 1:
                return addHead();
            case 2:
                return addBody();
            case 3:
                return addLeftArm();
            case 4:
                return addRightArm();
            case 5:
                return addLeftLeg();
            case 6:
                return addRightLeg();
            case 7:
                return addLeftHand();
            case 8:
                return addRightHand();
            case 9:
                return addLeftFoot();
            default:
                return addRightFoot();
        }
    }

    private String addLeftHand() {
        return "  ________\n"+
                "  |      |\n" +
                "  |      0\n" +
                "  |  >---|---\n" +
                "  |      |  \n" +
                "  |     / \\  \n" +
                "  |    /   \\  \n" +
                "__|__\n";
    }

    private String addRightFoot() {
        return "  ________\n"+
            "  |      |\n" +
            "  |      0\n" +
            "  |  >---|---<\n" +
            "  |      |  \n" +
            "  |     / \\  \n" +
            "  |   _/   \\_ \n" +
            "__|__\n";
    }

    private String addLeftFoot() {
        return "  ________\n"+
            "  |      |\n" +
            "  |      0\n" +
            "  |  >---|---<\n" +
            "  |      |  \n" +
            "  |     / \\  \n" +
            "  |   _/   \\  \n" +
            "__|__\n";
    }

    private String addRightHand() {
        return "  ________\n"+
                "  |      |\n" +
                "  |      0\n" +
                "  |  >---|---<\n" +
                "  |      |  \n" +
                "  |     / \\  \n" +
                "  |    /   \\  \n" +
                "__|__\n";
    }

    private String addRightLeg() {
        return "  ________\n"+
                "  |      |\n" +
                "  |      0\n" +
                "  |   ---|---\n" +
                "  |      |  \n" +
                "  |     / \\  \n" +
                "  |    /   \\  \n" +
                "__|__\n";
    }

    private String addLeftLeg() {
        return "  ________\n"+
                "  |      |\n" +
                "  |      0\n" +
                "  |   ---|---\n" +
                "  |      |\n" +
                "  |     /\n" +
                "  |    /\n" +
                "__|__\n";
    }

    private String addRightArm() {
        return "  ________\n"+
                "  |      |\n" +
                "  |      0\n" +
                "  |   ---|---\n" +
                "  |      |\n" +
                "  |\n" +
                "  |\n" +
                "__|__\n";
    }

    private String addLeftArm() {
        return "  ________\n"+
                "  |      |\n" +
                "  |      0\n" +
                "  |   ---|\n" +
                "  |      |\n" +
                "  |\n" +
                "  |\n" +
                "__|__\n";
    }

    private String addBody() {
        return "  ________\n"+
                "  |      |\n" +
                "  |      0\n" +
                "  |      |\n" +
                "  |      |\n" +
                "  |\n" +
                "  |\n" +
                "__|__\n";
    }

    private String addHead() {
        return "  ________\n"+
                "  |      |\n" +
                "  |      0\n" +
                "  |\n" +
                "  |\n" +
                "  |\n" +
                "  |\n" +
                "__|__\n";
    }

    private String initialDraw() {
        return "  ________\n"+
                "  |      |\n" +
                "  |\n" +
                "  |\n" +
                "  |\n" +
                "  |\n" +
                "  |\n" +
                "__|__\n";
    }
}
