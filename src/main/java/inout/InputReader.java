package inout;

import java.util.Scanner;

public class InputReader {

    private Scanner scanner = new Scanner(System.in);

    public int getInt() {
        boolean repeat = false;
        int i=0;

        do {
            String input=scanner.nextLine();
            try {
                i= Integer.parseInt(input);
                repeat = false;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
                repeat = true;
            }
        } while (repeat);

        return i;
    }

    public String getString() {
        String input=scanner.nextLine();
        return input;
    }
}
