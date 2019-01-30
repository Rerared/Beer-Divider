package BeerDivider;

public class Beer {
    private String name;
    private double cell;
    private double quantity;

    public Beer(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Beer(String name, double cell, double quantity) {
        this.name = name;
        this.cell = cell;
        this.quantity = quantity;
    }

    public double getCell() {
        return cell;
    }

    public void setCell(double cell) {
        this.cell = cell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
