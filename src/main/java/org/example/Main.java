package org.example;

import org.example.exception.ArrayDataException;
import org.example.exception.ArraySizeException;

public class Main {
    public static void main(String[] args) {

        ArrayValueCalculator calc = new ArrayValueCalculator();
        String[] arr1 = new String[]{"1", "2", "3", "4"};
        String[] arr2 = new String[]{"1", "2", "3", "4"};
        String[] arr3 = new String[]{"1", "2", "3", "4"};
        String[] arr4 = new String[]{"1", "2", "3", "4"};
        String[][] arrays = new String[][]{arr1, arr2, arr3, arr4};

        try {
            System.out.println("Total sum equals: " + calc.doCalc(arrays));
        } catch (ArraySizeException e) {
            System.out.println("Got ArraySizeException: " + e.getMessage());
        } catch (ArrayDataException e) {
            System.out.println("Got ArrayDataException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Got Exception: " + e.getMessage());
        }
    }
}