package org.example;

import org.example.exception.ArrayDataException;
import org.example.exception.ArraySizeException;

public class ArrayValueCalculator {
    private int arrayWithException;
    private int itemInArrayWithException;

    public int doCalc(String[][] arrays) throws ArraySizeException, ArrayDataException {
        validateArraysSize(arrays);
        int[][] newIntArrays = parsStringArraysToIntArrays(arrays);
        return sumAllItemInArray(newIntArrays);
    }

    private void validateArraysSize(String[][] arrays) throws ArraySizeException {
        if (arrays.length == 4) {
            for (int i = 0; i < arrays.length; i++) {
                if (arrays[i].length != 4) {
                    arrayWithException = i;
                    throw new ArraySizeException(String.format("The nested Array length should be 4 but got %d in array with index: %d", arrays[i].length, arrayWithException));
                }
            }
        } else {
            throw new ArraySizeException(String.format("Array should nested 4 arrays but got %d", arrays.length));
        }
    }

    private int[][] parsStringArraysToIntArrays(String[][] arrays) throws ArrayDataException {
        int[][] intArrays = new int[4][4];

        for (int i = 0; i < arrays.length; i++) {
            arrayWithException = i;
            try {
                for (int j = 0; j < arrays[i].length; j++) {
                    itemInArrayWithException = j;
                    intArrays[i][j] = Integer.parseInt(arrays[i][j]);
                }
            } catch (NumberFormatException e) {
                throw new ArrayDataException(String.format("We can't pars item with index: %d in array with index: %d", itemInArrayWithException, arrayWithException));
            }
        }
        return intArrays;
    }

    private int sumAllItemInArray(int[][] arrays) {
        int result = 0;
        for (int[] arr : arrays) {
            for (int item : arr) {
                result += item;
            }
        }
        return result;
    }
}
