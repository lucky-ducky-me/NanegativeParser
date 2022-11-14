import Parsing.Parser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        interactWithUser();
    }

    private static void interactWithUser() {
        var count = 0;

        while (true) {
            var console = new Scanner(System.in);

            if (count > 0) {
                System.out.println("Закончить работу? [y/n]");

                var answer = console.nextLine();

                if (answer.equals("y") || answer.equals("Y")) {
                    break;
                }
            }

            var greetingMessage = "Для получения данных об отзывах с Негатив.ру введите количетсво считываемых страниц:    ";

            System.out.println(greetingMessage);

            var pagesAmount = -1;

            try {
                pagesAmount = Integer.parseInt(console.nextLine());
            }
            catch (NumberFormatException ex) {
                System.out.println("Введите корректное число.");
                continue;
            }

            try {
                var parser = new Parser();
                var storeReviews = parser.getStoresReviews(pagesAmount);
                System.out.println(storeReviews.size());
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                continue;
            }

            count++;
        }
    }
}
