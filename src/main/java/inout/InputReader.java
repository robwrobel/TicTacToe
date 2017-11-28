package inout;

public interface InputReader {

    public int getInt();
    public String getString();

    default String getNonEmptyString() {
        String s;
        do {
            s = getString();
        } while (s.isEmpty());
        return s;
    }

    default int getInt(int min, int max) {
        int i;
        do {
            i = getInt();
        } while (i < min || i > max);
        return i;
    }

    default String getYesNoString() {
        String s;
        do {
            s = getString();
        } while (!s.equals("y") && !s.equals("n"));
        return s;
    }
}
