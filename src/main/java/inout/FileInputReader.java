package inout;

import game.Game;

import java.io.*;

public class FileInputReader implements InputReader {

    String inputFileName = "in.txt";

    BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName));

    OutputWriter ow;

    public FileInputReader(OutputWriter ow) throws FileNotFoundException {
        this.ow = ow;
    }

    @Override
    public int getInt() {
        int i = -1;
        try {
            String line = fileReader.readLine();
            ow.println(line);
            i = Integer.parseInt(line);
        } catch (IOException e) {
            ow.println(Game.resourceBundle.getString("EnterANumber"));
        }

        return i;
    }

    @Override
    public String getString() {
        String s = "";
        try {
            s = fileReader.readLine();
            ow.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
