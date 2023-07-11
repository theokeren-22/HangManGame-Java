import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("1 or 2 players?");
        String players = keyboard.nextLine();
        String word;

        if (players.equals("1")) {
            Scanner scanner = new Scanner(new File("C:\\HangMan\\Words.txt"));
            List<String> words = new ArrayList<>();

            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }
            scanner.close();

            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));
        } else {
            System.out.println("Player 1, please Enter your word:");
            word = keyboard.nextLine();
            System.out.println("\n".repeat(30));
            System.out.println("Ready for player 2! Good Luck!");
        }
        //System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();
        Integer wrongCount = 0;

        while (true) {
            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("YOU LOSE!");
                System.out.println("The word was: " + word);
                break;
            }

            if (printWordState(word, playerGuesses)) {
                System.out.println("YOU WIN!");
                break;
            }

            if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;
            }
        }
    }

    private static void printHangedMan(Integer wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }

        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            } else {
                System.out.println("");
            }
        }
    }

    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter:");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("-");
            }
        }
        System.out.println();

        return (word.length() == correctCount);
    }
}
