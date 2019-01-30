package BeerDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BeerDivider {
//=========================== method for sorting beers in BoxPacks ======================================
    public static List<BoxPack> divideOrder(List<Beer> bear) {
        Stream<Beer> stream = bear.stream();

        //getting needed quantity
        double sumOfNeededQuantity = 0;
        for (Beer aBear : bear) {
            if (aBear.getCell() == 0.5) {
                sumOfNeededQuantity += aBear.getQuantity() * 0.5;
            }
            if (aBear.getCell() == 1) {
                sumOfNeededQuantity += aBear.getQuantity();
            }
        }
        //getting List<BoxPack> where keeping needed size for each box
        List<Double> neededBoxes = new ArrayList(
                BoxesCalculator.getBoxesAll(sumOfNeededQuantity));
        List<BoxPack> listOfBoxes = new ArrayList<>();
        for (Double neededBox : neededBoxes) {
            listOfBoxes.add(new BoxPack(neededBox));
        }
        //========================= dividing bottles in List<BoxPack> =======================
        int numberOfBox = 0;
        // firstly adding bottles with cell = 1
        for (int i = 0; i < bear.size(); i++) {
            double cellOfBeer = bear.get(i).getCell();
            while (cellOfBeer == 1) {
                double coefficientOfCells = 1;
                for (numberOfBox = numberOfBox; numberOfBox < listOfBoxes.size(); numberOfBox++) {
                    divideBottlesInBoxes(listOfBoxes,bear,cellOfBeer,numberOfBox,i,coefficientOfCells);
                    if (bear.get(i).getQuantity() == 0) {
                        break;
                    }
                }
                if (bear.get(i).getQuantity() == 0) {
                    break;
                }
            }

        }
        // secondly adding bottles with cell = 0.5
        for (int i = 0; i < bear.size(); i++) {
            double cellOfBeer = bear.get(i).getCell();
            while (cellOfBeer == 0.5) {
                double coefficientOfCells = 2;
                for (numberOfBox = numberOfBox; numberOfBox < listOfBoxes.size(); numberOfBox++) {
                    divideBottlesInBoxes(listOfBoxes, bear , cellOfBeer, numberOfBox, i, coefficientOfCells);
                    if (bear.get(i).getQuantity() == 0) {
                        break;
                    }
                }
                if (bear.get(i).getQuantity() == 0) {
                    break;
                }
            }
        }
        //=========================================================================================
        return listOfBoxes;
    }

    //============================== method for dividing bottles ==================================
    private static void divideBottlesInBoxes(List<BoxPack> listOfBoxes, List<Beer> bear, double cell,
                                     int numberOfBox, int numberOfBeerInTheListBeer, double coefficientOfCells) {
        boolean isQuantityBiggerInBeer = bear.get(numberOfBeerInTheListBeer)
                .getQuantity() * cell >= listOfBoxes.get(numberOfBox).getFreeSpace();
        boolean isBeerExist = bear.get(numberOfBeerInTheListBeer).getQuantity() > 0;
        boolean isFreeSpaceExists = listOfBoxes.get(numberOfBox).getFreeSpace() > 0;

        if (isQuantityBiggerInBeer && isBeerExist && isFreeSpaceExists) {
            // calculating extra quantity after adding another part to BoxPack
            double quantityOfBeerForList = bear.get(numberOfBeerInTheListBeer).
                    getQuantity() * cell - listOfBoxes.get(numberOfBox).getFreeSpace();
            // adding Beer to List<Beer> that is in BoxPack
            listOfBoxes.get(numberOfBox)
                    .addBeer(new Beer(bear.get(numberOfBeerInTheListBeer)
                            .getName(), listOfBoxes.get(numberOfBox).getFreeSpace()*coefficientOfCells));
            // calculating current number of beers(extra quantity * coef)
            bear.get(numberOfBeerInTheListBeer).setQuantity(quantityOfBeerForList * coefficientOfCells);
            // reset a FreeSpace in BoxPack
            listOfBoxes.get(numberOfBox).setFreeSpace(0);
        }
        if (!isQuantityBiggerInBeer && isBeerExist && isFreeSpaceExists) {
            //calculating quantity of Beer
            double quantityOfBeerInBoxPack = bear.get(numberOfBeerInTheListBeer).getQuantity()
                    * cell;
            listOfBoxes.get(numberOfBox)
                    .addBeer(new Beer(bear.get(numberOfBeerInTheListBeer)
                            .getName(), quantityOfBeerInBoxPack * coefficientOfCells));
            // changing freeSpace in BoxPack after adding Beer
            listOfBoxes.get(numberOfBox)
                    .setFreeSpace((listOfBoxes.get(numberOfBox).getFreeSpace() - quantityOfBeerInBoxPack));
            // reset a quantity in current Beer for changing to another
            bear.get(numberOfBeerInTheListBeer).setQuantity(0);
        }
    }

}
