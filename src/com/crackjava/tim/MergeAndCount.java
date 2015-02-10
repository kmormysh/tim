//
//        This file contains all of the 100,000 integers between 1 and 100,000 (inclusive) in some order, with no
//        integer repeated.
//
//        Your task is to compute the number of inversions in the file given, where the ith row of the file indicates
//        the ith entry of an array.
//        Because of the large size of this array, you should implement the fast divide-and-conquer algorithm covered
//        in the video lectures. The numeric answer for the given input file should be typed in the space below.
//        So if your answer is 1198233847, then just type 1198233847 in the space provided without any space / commas
//        / any other punctuation marks. You can make up to 5 attempts, and we'll use the best one for grading.
//        (We do not require you to submit your code, so feel free to use any programming language you want --- just
//        type the final numeric answer in the following space.)
//
//        [TIP: before submitting, first test the correctness of your program on some small test files or your own
//        devising. Then post your best test cases to the discussion forums to help your fellow students!]

package com.crackjava.tim;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class MergeAndCount {

    static class Merge_and_count {

        public static long mergeSort(int[] A, int[] B, int[] cSorted) {
            int i = 0, j = 0, k = 0;
            int inv = 0;
            while (i < A.length && j < B.length) {
                if (A[i] < B[j]) {
                    cSorted[k] = A[i];
                    k++;
                    i++;
                } else {
                    cSorted[k] = B[j];
                    inv += A.length - i;
                    k++;
                    j++;
                }
            }
            if (i == A.length) {
                while (j < B.length) {
                    cSorted[k] = B[j];
                    j++;
                    k++;
                }
            } else if (j == B.length)
                while (i < A.length) {
                    cSorted[k] = A[i];
                    i++;
                    k++;
                }
            return inv;
        }
        public long count_inv(int[] C) {
            int length = C.length;
            if ( C.length < 2 ) {
                return 0;
            }
            else {
                int middle = C.length / 2;
                int[] left = Arrays.copyOfRange(C, 0, middle);
                int[] right = Arrays.copyOfRange(C, middle, length);

                long invX = count_inv(left);
                long invY = count_inv(right);

                int[] result = new int[length];
                long invZ = mergeSort(left, right, result);

                for (int k = 0; k < length; k++)
                    C[k] = result[k];
                return invX + invY + invZ;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(
                new File("C:\\Users\\Katsiaryna\\coursera\\tim\\IntegerArray.txt"));

        Merge_and_count mc = new Merge_and_count();
        int[] C = new int[100000];

        for (int i = 0; i < C.length; i++)
            C[i] =  scanner.nextInt();

        long start = new Date().getTime();
        System.out.println("Number of inversions: " + mc.count_inv(C));
        System.out.println("Time: " + (new Date().getTime() - start) + "ms");
    }
}
