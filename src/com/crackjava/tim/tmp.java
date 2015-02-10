package com.crackjava.tim;

/**
 * Created by Katsiaryna on 2/4/2015.
 */
public class tmp {
    public static void main(String[] arg){
        int[] A = {1, 0, 0, 1, 1, 1};
        int number = 0;
        for (int i = 0; i < A.length; i++)
            number += A[i]*Math.pow((-2), i);

        int revNumber = -number;
        int tmp = 0;
        String str = "";

        while (tmp != 1){
            tmp = revNumber / (-2);
            if (revNumber < 0 && revNumber%2 != 0)
                tmp++;

            str += revNumber - tmp*(-2);
            revNumber = tmp;
        }
        str += 1;
        System.out.println("Number: " + number + ", binary code for " + -number + " is: " + str);

        System.out.println("\u263A");
    }
}
