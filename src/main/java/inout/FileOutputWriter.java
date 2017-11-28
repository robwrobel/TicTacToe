package inout;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutputWriter implements OutputWriter {

    String outputFileName = System.getProperty("OutFileName","out.txt");;

    BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFileName));

    public FileOutputWriter() throws IOException {
    }

    @Override
    public void println(String s) {
        try {
            fileWriter.write(s,0,s.length());
            fileWriter.newLine();
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
