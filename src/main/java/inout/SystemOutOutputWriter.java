package inout;

public class SystemOutOutputWriter implements OutputWriter {
    public void println (String s) {
        System.out.println(s);
    }
}
