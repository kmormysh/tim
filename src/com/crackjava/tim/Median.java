//The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 5 lecture on heap
// applications). The text file contains a list of the integers from 1 to 10000 in unsorted order; you should treat
// this as a stream of numbers, arriving one by one. Letting xi denote the ith number of the file, the kth median mk
// is defined as the median of the numbers x1,…,xk. (So, if k is odd, then mk is ((k+1)/2)th smallest number among
// x1,…,xk; if k is even, then mk is the (k/2)th smallest number among x1,…,xk.)
//
//        In the box below you should type the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits).
// That is, you should compute (m1+m2+m3+⋯+m10000)mod10000.


        package com.crackjava.tim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Katsiaryna on 1/13/2015.
 */
public class Median {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("C:\\Users\\Katsiaryna\\coursera\\tim\\Median.txt"));
        int[] array = new int[10000];
        int i = 0;
        while (s.hasNextLine()) {
            array[i] = Integer.parseInt(s.nextLine());
            i++;
        }
        int m = 0;
        long med = 0;
        for (int k = 0; k < array.length; k++) {
            //int k = 2;
            int temp[] = new int[k + 1];
            for (int j = 0; j <= k; j++) {
                temp[j] = array[j];
            }
            Arrays.sort(temp);
            if (k % 2 == 0) {
                m = k / 2;
            } else {
                m = (k - 1) / 2;
            }

            System.out.flush();
            System.out.print("\rarrayIndex [" + k + "]: "+ array[k] +" , median index: " + m + " , median: " + array[m]);
            med = med + temp[m];
        }
        System.out.println("\n" + med % 10000);
    }
}
