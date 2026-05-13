import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Hangman {
    public static void main(String[] args) {
        String[] woerter = {"entwickler", "java", "programm", "computer", "hangman"};
        Random random = new Random();
        String dasWort = woerter[random.nextInt(woerter.length)];
        
        char[] erratenesWort = new char[dasWort.length()];
        for (int i = 0; i < erratenesWort.length; i++) {
            erratenesWort[i] = '_';
        }

        int versuche = 6;
        ArrayList<Character> gerateneBuchstaben = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Spiel: Hangman");

        while (versuche > 0 && String.valueOf(erratenesWort).contains("_")) {
            System.out.println("\nWort: " + String.valueOf(erratenesWort));
            System.out.println("Verbleibende Versuche: " + versuche);
            System.out.println("Geratene Buchstaben: " + gerateneBuchstaben);
            System.out.print("Bitte geben Sie einen Buchstaben ein: ");
            
            String input = scanner.next().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Ungültige Eingabe!");
                continue;
            }

            char buchstabe = input.charAt(0);

            if (gerateneBuchstaben.contains(buchstabe)) {
                System.out.println("Bereits geraten!");
                continue;
            }

            gerateneBuchstaben.add(buchstabe);

            if (dasWort.indexOf(buchstabe) >= 0) {
                for (int i = 0; i < dasWort.length(); i++) {
                    if (dasWort.charAt(i) == buchstabe) {
                        erratenesWort[i] = buchstabe;
                    }
                }
            } else {
                versuche--;
            }
        }

        if (!String.valueOf(erratenesWort).contains("_")) {
            System.out.println("\nHerzlichen Glückwunsch! Wort: " + dasWort);
        } else {
            System.out.println("\nVerloren! Das Wort war: " + dasWort);
        }
        scanner.close();
    }
}
