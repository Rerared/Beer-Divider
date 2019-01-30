package BeerDivider;

import java.util.ArrayList;
import java.util.List;

public class BoxPack {
    private double size;
    private double freeSpace;

    List<Beer> listBeers = new ArrayList<>();

    // for adding Beer to list
    public void addBeer(Beer beer) {
        listBeers.add(beer);
    }

    public BoxPack(double size) {
        this.size = size;
        this.freeSpace = size;
    }

    public List<Beer> getListBeers() {
        return listBeers;
    }

    public void setListBeers(List<Beer> listBeers) {
        this.listBeers = listBeers;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(double freeSpace) {
        this.freeSpace = freeSpace;
    }
}
