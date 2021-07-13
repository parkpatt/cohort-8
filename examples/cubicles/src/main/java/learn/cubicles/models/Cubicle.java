package learn.cubicles.models;

public class Cubicle {
    private int cubicleId;
    private int floor;
    private int row;
    private int column;

    public Cubicle() {}

    public Cubicle(int cubicleId, int floor, int row, int column) {
        this.cubicleId = cubicleId;
        this.floor = floor;
        this.row = row;
        this.column = column;
    }

    public int getCubicleId() {
        return cubicleId;
    }

    public void setCubicleId(int cubicleId) {
        this.cubicleId = cubicleId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
