package com.crackjava.tim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Katsiaryna on 1/14/2015.
 * Your task in this problem is to run the greedy algorithm that schedules jobs in decreasing order of the difference
 * (weight - length). Recall from lecture that this algorithm is not always optimal. IMPORTANT: if two jobs have equal
 * difference (weight - length), you should schedule the job with higher weight first. Beware: if you break ties in a
 * different way, you are likely to get the wrong answer. You should report the sum of weighted completion times of the
 * resulting schedule --- a positive integer --- in the box below.
 */
public class WCdifference {
    private int[][] jobs;
    public final static String path = "C:\\Users\\Katsiaryna\\coursera\\tim\\jobs.txt";

    public void readFile() throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(path));
        StringTokenizer st;
        String str;

        int size = Integer.valueOf(br.readLine());
        jobs = new int[size][2];

        int i = 0;

        while ((str = br.readLine()) != null) {
            st = new StringTokenizer(str);
            int w_l = Integer.valueOf(st.nextToken());
            jobs[i][1] = Integer.valueOf(st.nextToken());
            jobs[i][0] = w_l - jobs[i][1];
            i++;
        }
    }

    public void counter() throws Exception{
        readFile();
        int answer = 0, tmp = 0;

        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        for (int i = jobs.length-1; i >= 0; i--){
            tmp += jobs[i][1];
            answer += (jobs[i][0]+jobs[i][1])*tmp;
        }

        System.out.println("Weighted completion using w-l = " + answer);
    }

    public static void main(String[] arg) throws Exception{
        WCdifference wc = new WCdifference();
        wc.counter();
    }
}