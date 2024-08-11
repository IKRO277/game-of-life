

import ui.UI;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        UI ui = new UI();

        ui.addChessParams(args);
        ui.runChess(ui.generateChess(args));

        sc.close();
    }
}
