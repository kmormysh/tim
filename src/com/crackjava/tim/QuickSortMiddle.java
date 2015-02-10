package com.crackjava.tim;

import java.util.Arrays;

/**
 * Created by Katsiaryna on 12/30/2014.
 */
public class QuickSortMiddle {

    public static int pivotIndex = 0;
    public static int QuickSortMiddle(int[] array, int length){
        int comp = 0, compLess = 0, compGreater = 0, i = 0, j = 0;
        if (length <= 1)
            return 0;
        else {
            pivotIndex = ChoosePivotMiddle(array, length);
            comp += PartitionMiddle(array, pivotIndex, array.length);
            int[] arrayLess = Arrays.copyOfRange(array, 0, pivotIndex);
            int[] arrayGreater = Arrays.copyOfRange(array, pivotIndex+1, length);

            compLess += QuickSortMiddle(arrayLess, arrayLess.length);
            compGreater += QuickSortMiddle(arrayGreater, arrayGreater.length);

            while (j < arrayLess.length)
                array[i++] = arrayLess[j++];
            j = 0;
            i++;
            while (j < arrayGreater.length)
                array[i++] = arrayGreater[j++];

        }
        return comp + compLess + compGreater;
    }

    public static int PartitionMiddle(int[] array, int beg, int end){
        int tmp;
        int comp = 0;

        tmp = array[pivotIndex];
        array[pivotIndex] = array[0];
        array[0] = tmp;

        beg = 0;

        int pivot = array[beg];
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

    public static int ChoosePivotMiddle(int[] array, int length){

        int[] arrMiddle = {array[0], array[(length-1)/2], array[length-1]};

        int max = Math.max(Math.max(arrMiddle[0], arrMiddle[1]), arrMiddle[2]);
        int min = Math.min(Math.min(arrMiddle[0], arrMiddle[1]), arrMiddle[2]);

        int tmp = arrMiddle[0]^arrMiddle[1]^arrMiddle[2]^max^min;

        if (tmp == array[length/2])
            return (length-1)/2;
        if (tmp == array[length-1])
            return length-1;
        return 0;
    }
}
