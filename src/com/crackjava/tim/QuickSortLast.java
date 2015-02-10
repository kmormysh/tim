package com.crackjava.tim;

import java.util.Arrays;

/**
 * Created by Katsiaryna on 12/30/2014.
 */
public class QuickSortLast {
    public static int pivotIndex = 0;
    public static int QuickSortLast(int[] array, int length){
        int comp = 0, compLess = 0, compGreater = 0, i = 0, j = 0;
        if (length <= 1)
            return 0;
        else {
            pivotIndex = ChoosePivotLast(array, length);
            comp += PartitionLast(array, pivotIndex, array.length);
            int[] arrayLess = Arrays.copyOfRange(array, 0, pivotIndex);
            int[] arrayGreater = Arrays.copyOfRange(array, pivotIndex+1, length);

            compLess += QuickSortLast(arrayLess, arrayLess.length);
            compGreater += QuickSortLast(arrayGreater, arrayGreater.length);

            while (j < arrayLess.length)
                array[i++] = arrayLess[j++];
            j = 0;
            i++;
            while (j < arrayGreater.length)
                array[i++] = arrayGreater[j++];
        }
        return comp + compLess + compGreater;
    }

    public static int PartitionLast(int[] array, int beg, int end){
        int tmp;
        int comp = 0;

        tmp = array[0];
        array[0] = array[end-1];
        array[end-1] = tmp;

        beg = 0;

        int pivot = array[0];
        int i = beg+1;
        for (int j = beg+1; j < end; j++){
            if (array[j] < pivot) {
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
            }
        }

        comp += array.length - 1;

        tmp = array[i-1];
        array[i-1] = array[beg];
        array[beg] = tmp;

        pivotIndex = i - 1;

        return comp;
    }
    public static int ChoosePivotLast(int[] array, int length){
        return length-1;
    }
}
