package org.example;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("measure swithc statement vs if-else chain speed");

        Random r = new Random();
        List<Integer> numbersOfSelects = List.of(900,9000,90000,9000000);

        for (int t = 0; t < numbersOfSelects.size(); t++) {
            System.out.println("Number of iterations: " + numbersOfSelects.get(t));
            long start = System.currentTimeMillis();
            for (int i = 0; i < numbersOfSelects.get(t); i++) {
                int min = 0;
                int max = 10;
                int randomNum = r.nextInt(max - min) + min;
                int testVar = 0;
                switch(randomNum) {
                    case 0:
                        testVar = randomNum;
                        break;
                    case 1:
                        testVar = randomNum;
                        break;
                    case 2:
                        testVar = randomNum;
                        break;
                    case 3:
                        testVar = randomNum;
                        break;
                    case 4:
                        testVar = randomNum;
                        break;
                    case 5:
                        testVar = randomNum;
                        break;
                    case 6:
                        testVar = randomNum;
                        break;
                    case 7:
                        testVar = randomNum;
                        break;
                    case 8:
                        testVar = randomNum;
                        break;
                    case 9:
                        testVar = randomNum;
                        break;
                    default:
                        testVar = randomNum;
                        break;
                }

            }
            long end = System.currentTimeMillis();
            System.out.println("Elapsed time of switch is: " + (end - start));


            start = System.currentTimeMillis();
            for (int i = 0; i < numbersOfSelects.get(t); i++) {
                int min = 0;
                int max = 10;
                int randomNum = r.nextInt(max - min) + min;
                int testVar = 0;
                if(randomNum == 0) {
                    testVar = randomNum;
                }
                else if(randomNum == 1) {
                    testVar = randomNum;
                }
                else if(randomNum == 2) {
                    testVar = randomNum;
                }
                else if(randomNum == 3) {
                    testVar = randomNum;
                }
                else if(randomNum == 4) {
                    testVar = randomNum;
                }
                else if(randomNum == 5) {
                    testVar = randomNum;
                }
                else if(randomNum == 6) {
                    testVar = randomNum;
                }
                else if(randomNum == 7) {
                    testVar = randomNum;
                }
                else if(randomNum == 8) {
                    testVar = randomNum;
                }
                else if(randomNum == 9) {
                    testVar = randomNum;
                }
                else {
                    testVar = randomNum;
                }
            }
            end = System.currentTimeMillis();
            System.out.println("Elapsed time of if else is: " + (end - start));

        }


    }
}