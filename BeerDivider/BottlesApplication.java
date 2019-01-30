package BeerDivider;

import java.util.ArrayList;
import java.util.List;

public class BottlesApplication {

    public static void main(String[] args) {
        //========================== configure incoming list here =================================
        List<Beer> listOrder = new ArrayList<>();

        listOrder.add(new Beer("beer1", 1, 32));
//        listOrder.add(new Beer("beer2", 0.5, 51));
//        listOrder.add(new Beer("beer3", 0.5, 14));
//        listOrder.add(new Beer("beer4", 1, 3));
//        listOrder.add(new Beer("beer5", 1, 6));

        List<BoxPack> getOrder = BeerDivider.divideOrder(listOrder);
        /* get result of sorting beers in boxes */
        int numberOfBox = 1;
        for (BoxPack boxPack : getOrder) {
            System.out.println("BoxPack №" + numberOfBox + ", size:" + boxPack.getSize());
            numberOfBox++;
            for (int j = 0; j < boxPack.listBeers.size(); j++) {
                System.out.print("Code of beer: " + boxPack.listBeers.get(j).getName());
                System.out.println(", quantity: " + boxPack.listBeers.get(j).getQuantity());
            }
        }
    }

}