//The file contains all of the integers between 1 and 10,000 (inclusive, with no repeats) in unsorted order. The integer in the ith row of the file gives you the ith entry of an input array.

//Your task is to compute the total number of comparisons used to sort the given input file by QuickSort. As you know,
// the number of comparisons depends on which elements are chosen as pivots, so we'll ask you to explore three
// different pivoting rules.
//        You should not count comparisons one-by-one. Rather, when there is a recursive call on a subarray of
// length m, you should simply add m−1 to your running total of comparisons. (This is because the pivot element
// is compared to each of the other m−1 elements in the subarray in this recursive call.)
//
//        WARNING: The Partition subroutine can be implemented in several different ways, and different implementations
// can give you differing numbers of comparisons. For this problem, you should implement the Partition subroutine
// exactly as it is described in the video lectures (otherwise you might get the wrong answer).
//
//        DIRECTIONS FOR THIS PROBLEM:
//
//        For the first part of the programming assignment, you should always use the first element of the array as the
// pivot element.
//
//        HOW TO GIVE US YOUR ANSWER:
//
//        Type the numeric answer in the space provided.
//        So if your answer is 1198233847, then just type 1198233847 in the space provided without any space / commas
// / other punctuation marks. You have 5 attempts to get the correct answer.
//        (We do not require you to submit your code, so feel free to use the programming language of your choice,
// just type the numeric answer in the following space.)

package com.crackjava.tim;

import java.io.*;
import java.util.*;

public class QuickSort {
    public static int pivotIndex = 0;

    public static int QuickSortFirst(int[] array, int length) {
        int comp = 0, compLess = 0, compGreater = 0, j = 0, i = 0;
        if (length <= 1)
            return 0;
        else {
            pivotIndex = ChoosePivotFirst(array, length);
            comp += Partition(array, pivotIndex, array.length);
            int[] arrayLess = Arrays.copyOfRange(array, 0, pivotIndex);
            int[] arrayGreater = Arrays.copyOfRange(array, pivotIndex + 1, length);

            compLess += QuickSortFirst(arrayLess, arrayLess.length);
            compGreater += QuickSortFirst(arrayGreater, arrayGreater.length);

            while (j < arrayLess.length)
                array[i++] = arrayLess[j++];
            j = 0;
            i++;
            while (j < arrayGreater.length)
                array[i++] = arrayGreater[j++];
        }
        return comp + compLess + compGreater;
    }

    public static int Partition(int[] array, int beg, int end) {
        int tmp;
        int comp = 0;
        int pivot = array[beg];
        int i = beg + 1;
        for (int j = beg + 1; j < end; j++) {
            if (array[j] < pivot) {
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;

            }
        }

        comp += array.length - 1;

        tmp = array[i - 1];
        array[i - 1] = array[beg];
        array[beg] = tmp;

        pivotIndex = i - 1;

        return comp;
    }

    public static int ChoosePivotFirst(int[] array, int length) {
        return 0;
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                new File("C:\\Users\\Katsiaryna\\coursera\\tim\\QuickSort.txt"));

        int[] arrayFirst = new int[10000];
        int[] arrayMiddle = new int[arrayFirst.length];
        int[] arrayLast = new int[arrayFirst.length];

        for (int i = 0; i < arrayFirst.length; i++) {
            arrayFirst[i] = scanner.nextInt();
            arrayMiddle[i] = arrayFirst[i];
            arrayLast[i] = arrayFirst[i];
        }


        System.out.println("Number of comparisons(pivot is the first element) = " + QuickSortFirst(arrayFirst, arrayFirst.length));

        System.out.println("Number of comparisons(pivot is the last element) = " + QuickSortLast.QuickSortLast(arrayLast, arrayLast.length));


        System.out.println("Number of comparisons(pivot is the middle element) = " + QuickSortMiddle.QuickSortMiddle(arrayMiddle, arrayMiddle.length));

    }
}
