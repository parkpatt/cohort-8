package learn.cubicles.models;

public class Cubicle {
    private int cubicleId;
    private int floor;
    private int row;
    private int column;
    private String assignedEmployeeName;

    public Cubicle() {}

    public Cubicle(int cubicleId, int floor, int row, int column, String assignedEmployeeName) {
        this.cubicleId = cubicleId;
        this.floor = floor;
        this.row = row;
        this.column = column;
        this.assignedEmployeeName = assignedEmployeeName;
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

    public String getAssignedEmployeeName() {
        return assignedEmployeeName;
    }

    public void setAssignedEmployeeName(String assignedEmployeeName) {
        this.assignedEmployeeName = assignedEmployeeName;
    }
}
