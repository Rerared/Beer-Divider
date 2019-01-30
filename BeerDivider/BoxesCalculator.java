package BeerDivider;

import java.util.ArrayList;

public class BoxesCalculator {
    private static final double boxes[] = {6, 8, 10, 12, 16, 25};

    static ArrayList<Double> getBoxesAll(double sum) {
        ArrayList<Double> neededBoxes = new ArrayList();
        boolean goodEnough = false;

        while (!goodEnough) {
            if (sum < boxes[0]) {
                neededBoxes.add(boxes[0]);
                goodEnough = true;
            }
            if (sum >= 6 && sum <= 24) {
                neededBoxes.addAll(getBoxesUnder24(sum));
                goodEnough = true;
            }
            if (sum > 24 && sum <= 25) {
                neededBoxes.add(boxes[5]);
                goodEnough = true;
            }
            if (sum > 25 && sum <= 49) {
                neededBoxes.addAll(getBoxesBetween25And49(sum));
                goodEnough = true;
            }
            if (sum > 49) {
                while (sum > 49) {
                    sum -= 25;
                    neededBoxes.add(boxes[5]);
                }
                if (sum > 25 && sum <= 49) {
                    neededBoxes.addAll(getBoxesBetween25And49(sum));
                    goodEnough = true;
                }
            }
        }
        return neededBoxes;
    }

    private static ArrayList<Double> getBoxesUnder24(double sum) {
        ArrayList<Double> neededBoxes = new ArrayList();
        double temp = 24;
        int count = 1;
        while (count == 1) {
            if (sum > temp - 2) {
                //if exists box of box[]
                for (double box : boxes) {
                    sum = temp;
                    if (sum == box) {
                        neededBoxes.add(box);
                        count--;
                        break;
                    }
                }
                if (count == 1) {
                    for (int j = 0; j < boxes.length - 1; j++) {
                        double box = temp - boxes[j];
                        for (int i = 0; i < boxes.length - 1; i++) {
                            if (box == boxes[i]) {
                                neededBoxes.add(boxes[j]);
                                neededBoxes.add(box);
                                count--;
                            }
                        }
                        if (count <= 0) {
                            break;
                        }
                    }
                }
            }
            temp -= 2;
        }
        return neededBoxes;
    }

    private static ArrayList<Double> getBoxesBetween25And49(double sum) {
        ArrayList<Double> neededBoxes = new ArrayList();
        double temp = 49;
        int count = 1; // counter to stop loop
        while (count == 1) {
            if (sum >= temp - 0.5) { /* decreasing temp on -1 until true */
                if (temp % 2 == 0) {
                    if (temp < 31) {
                        double box = temp - boxes[4];
                        neededBoxes.add(boxes[4]);
                        neededBoxes.addAll(getBoxesUnder24(box));
                        count--;
                    }
                    if (temp > 31) {
                        double box = temp - boxes[4];
                        neededBoxes.add(boxes[4]);
                        temp = box;
                        if (temp <= 32) {
                            box = temp - boxes[2];
                            neededBoxes.add(boxes[2]);
                            neededBoxes.addAll(getBoxesUnder24(box));
                            count--;
                        }
                    }
                    if (count <= 0) {
                        break;
                    }
                }
                if (temp % 2 == 1 && temp >= 31) {
                    neededBoxes.add(boxes[5]);
                    temp -=25;
                    neededBoxes.addAll(getBoxesUnder24(temp));
                    count--;
                }
                if (temp % 2 == 1 && temp < 31) {
                    neededBoxes.add(boxes[4]);
                    temp -=16;
                    neededBoxes.addAll(getBoxesUnder24(temp));
                    count--;
                }
            }
            temp -= 1;
            if (count <= 0) {
                break;
            }
        }
        return neededBoxes;
    }
}
