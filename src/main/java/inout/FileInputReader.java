package inout;

import java.io.*;

public class FileInputReader implements InputReader {

    String inputFileName = "in.txt";

    BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName));

    public FileInputReader() throws FileNotFoundException {
    }

    @Override
    public int getInt() {
        int i = -1;
        try {
            i = Integer.parseInt(fileReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public String getString() {
        String s = "";
        try {
            s = fileReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
