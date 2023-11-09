package org.example;

public class ValueCalculator {

    private final int arrLength;
    private final int arrHalfLength;
    private final int[] intArr;

    public ValueCalculator(int arrLength) {
        if(arrLength < 1000000){
            this.arrLength = 1_000_000;
            System.out.println("arrLength was set to 1_000_000 because length couldn't be less then 1_000_000 you set " + arrLength);
        } else {
            this.arrLength = arrLength;
        }
        this.arrHalfLength = this.arrLength / 2;
        this.intArr = new int[this.arrLength];
    }

    private long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public void doSomeCalc() {
        try {
            long start = getCurrentTimeMillis();
            fillArrWithInt(intArr);

            int[] firstArr = getCopyArrPart(intArr, 0, arrHalfLength);
            float[] arr1 = new float[arrHalfLength];
            Runnable r1 = () -> {
                for (int i = 0; i < arrHalfLength; i++) {
                    arr1[i] = (float) (firstArr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            };

            int[] secondArr = getCopyArrPart(intArr, arrHalfLength, arrHalfLength);
            float[] arr2 = new float[arrHalfLength];
            Runnable r2 = () -> {
                for (int i = 0; i < arrHalfLength; i++) {
                    arr2[i] = (float) (secondArr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            };

            Thread first = new Thread(r1);
            Thread second = new Thread(r2);
            first.start();
            second.start();

            float[] arr = new float[arrLength];
            first.join();
            System.arraycopy(arr1, 0, arr, 0, arrHalfLength);

            second.join();
            System.arraycopy(arr2, 0, arr, arrHalfLength, arrHalfLength);

            long end = getCurrentTimeMillis();
            System.out.println("time in millis: " + (end - start));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillArrWithInt(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    private int[] getCopyArrPart(int[] source, int startInd, int arrLength) {
        int[] arr = new int[arrLength];
        System.arraycopy(source, startInd, arr, 0, arrLength);
        return arr;
    }
}
