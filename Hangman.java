import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Hangman {
    public static void main(String[] args) {
        // لیست کلمات vordefinierten Liste [cite: 6]
        String[] woerter = {"entwickler", "java", "programm", "computer", "hangman"};
        Random random = new Random();
        String dasWort = woerter[random.nextInt(woerter.length)]; // Wortauswahl [cite: 6]
        
        char[] erratenesWort = new char[dasWort.length()];
        for (int i = 0; i < erratenesWort.length; i++) {
            erratenesWort[i] = '_'; // Anzeigen با زیرخط 
        }

        int versuche = 6; // تعداد Versuche [cite: 24]
        ArrayList<Character> gerateneBuchstaben = new ArrayList<>(); // لیست حروف [cite: 14]
        Scanner scanner = new Scanner(System.in);

        System.out.println("Spiel: Hangman");

        // Spielverlauf [cite: 15]
        while (versuche > 0 && String.valueOf(erratenesWort).contains("_")) {
            System.out.println("\nWort: " + String.valueOf(erratenesWort));
            System.out.println("Verbleibende Versuche: " + versuche);
            System.out.println("Geratene Buchstaben: " + gerateneBuchstaben);
            System.out.print("Bitte geben Sie einen Buchstaben ein: ");
            
            String input = scanner.next().toLowerCase();

            // Überprüfe die Gültigkeit (فقط یک حرف) [cite: 8]
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Ungültige Eingabe!");
                continue;
            }

            char buchstabe = input.charAt(0);

            // جلوگیری از حروف تکراری [cite: 9]
            if (gerateneBuchstaben.contains(buchstabe)) {
                System.out.println("Bereits geraten!");
                continue;
            }

            gerateneBuchstaben.add(buchstabe);

            // چک کردن وجود حرف در کلمه [cite: 16, 17]
            if (dasWort.indexOf(buchstabe) >= 0) {
                for (int i = 0; i < dasWort.length(); i++) {
                    if (dasWort.charAt(i) == buchstabe) {
                        erratenesWort[i] = buchstabe;
                    }
                }
            } else {
                versuche--; // کاهش فرصت [cite: 17]
            }
        }

        // بررسی برنده یا بازنده بودن [cite: 18, 19, 20]
        if (!String.valueOf(erratenesWort).contains("_")) {
            System.out.println("\nHerzlichen Glückwunsch! Sie haben das Wort erraten: " + dasWort);
        } else {
            System.out.println("\nSpiel verloren. Das Wort war: " + dasWort);
        }
        scanner.close();
    }
}
