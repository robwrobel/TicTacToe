package configuration;

public class BoardDimensions {
    public static final int MIN_NO_OF_COLUMNS = 3;
    public static final int MAX_NO_OF_COLUMNS = 30;
    public static final int MIN_NO_OF_ROWS = 3;
    public static final int MAX_NO_OF_ROWS = 30;

    int columns;
    int rows;

    public BoardDimensions(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
