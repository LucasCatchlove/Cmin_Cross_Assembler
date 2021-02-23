public class Position implements InterfacePosition {

    private int lineCounter;
    private int columnCounter;


    Position(int lineCounter, int columnCounter) {
        this.lineCounter = lineCounter;
        this.columnCounter = columnCounter;
    }
    @Override
    public int getLineCounter() {
        return lineCounter;
    }

    @Override
    public int getColumnCounter() {
        return columnCounter;
    }
}
