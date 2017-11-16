package configuration;

public class BoardDimensions {
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
