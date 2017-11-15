package configuration;

public enum Mark {
    O("O"),X("X"),EMPTY(" ");

    private String mark;

    Mark(String mark) {
        this.mark=mark;
    }

    @Override
    public String toString() {
        return mark;
    }
}
