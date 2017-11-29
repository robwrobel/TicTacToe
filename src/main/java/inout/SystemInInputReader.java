package inout;

import game.Game;

import java.util.Scanner;

public class SystemInInputReader implements InputReader {
    private Scanner scanner = new Scanner(System.in);
    OutputWriter ow;

    public SystemInInputReader(OutputWriter ow) {
        this.ow = ow;
    }

    public int getInt() {
        boolean repeat = false;
        int i=0;

        do {
            String input=scanner.nextLine();
            ow.println(input);
            try {
                i= Integer.parseInt(input);
                repeat = false;
            } catch (NumberFormatException e) {
                repeat = true;
            }
        } while (repeat);

        return i;
    }

    public String getString() {
        String input=scanner.nextLine();
        ow.println(input);
        return input;
    }

}
