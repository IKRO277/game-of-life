

import ui.UI;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        UI ui = new UI();
        System.out.println(ui.validateParams(args));
        ui.addChessParams(args);



        System.out.println("test");
        sc.close();
    }
}