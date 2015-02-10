//The goal of this problem is to implement a variant of the 2-SUM algorithm (covered in the Week 6 lecture on hash
// table applications).
//        The file contains 1 million integers, both positive and negative (there might be some repetitions!).This is
// your array of integers, with the ith row of the file specifying the ith entry of the array.
//
//        Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such that
// there are distinct numbers x,y in the input file that satisfy x+y=t. (NOTE: ensuring distinctness requires a
// one-line addition to the algorithm from lecture.)
//
//        Write your numeric answer (an integer between 0 and 20001) in the space provided.
//
//
//        OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it. For
// example, you could compare performance under the chaining and open addressing approaches to resolving collisions.

package com.crackjava.tim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Katsiaryna on 1/13/2015.
 */
public class TwoSUM {

    public final static String path = "C:\\Users\\Katsiaryna\\coursera\\tim\\algo1-programming_prob-2sum.txt";
    public final static int n = 1000000; //427
    public static int count = 0;
    public static Map<Long, Integer> hashtable;

    public static void counting(long[] array, int min, int max){
        Set<Long> keys = hashtable.keySet();
        for (int i = min; i <= max; i++) {
            Iterator<Long> iterator = keys.iterator();
            while (iterator.hasNext()) {
                Long key = iterator.next();
                if (keys.contains(i - key)) {
                    count++;
                    break;
                }
            }
            System.out.flush();
        }
    }

    public static void readFile(String path) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(path));
        String str;

        hashtable = new Hashtable<Long, Integer>();

        long[] array = new long[n];
        int i = 0, min = -10000, max = 10000;

        System.out.println("Reading file");
        try {
            while ((str = br.readLine()) != null) {
                long tmp = Long.valueOf(str);
                array[i] = tmp;
                if (hashtable.containsKey(tmp)) {
                    int numberOfOccurrences = hashtable.get(array[i]);
                    hashtable.remove(array[i]);
                    hashtable.put(array[i], ++numberOfOccurrences);
                } else {
                    hashtable.put(array[i], 1);
                }
                i++;
            }
        }
        catch (Exception e){
            System.out.println(i);
        }
        System.out.println("The file is read");

        counting(array, min, max);
        //for (int j = min; j < max+1; j++)
    }

    public static void main(String[] str) throws Exception{
        readFile(path);

        System.out.println("Counter = " + count);

    }
}
