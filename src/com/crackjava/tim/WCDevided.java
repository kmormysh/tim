//In this programming problem and the next you'll code up the greedy algorithms from lecture for minimizing the
// weighted sum of completion times.. Download the text file here. This file describes a set of jobs with positive
// and integral weights and lengths. It has the format
//        [number_of_jobs]
//        [job_1_weight] [job_1_length]
//        [job_2_weight] [job_2_length]
//        ...
//        For example, the third line of the file is "74 59", indicating that the second job has weight 74 and length
// 59. You should NOT assume that edge weights or lengths are distinct.
//
//        Your task in this problem is to run the greedy algorithm that schedules jobs in decreasing order of the
// difference (weight - length). Recall from lecture that this algorithm is not always optimal. IMPORTANT: if two
// jobs have equal difference (weight - length), you should schedule the job with higher weight first. Beware: if
// you break ties in a different way, you are likely to get the wrong answer. You should report the sum of weighted
// completion times of the resulting schedule --- a positive integer --- in the box below.
//
//        ADVICE: If you get the wrong answer, try out some small test cases to debug your algorithm (and post your
// test cases to the discussion forum)!

package com.crackjava.tim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Created by Katsiaryna on 1/15/2015.
 */
public class WCDevided {
    private double[][] jobs;
    public final static String path = "C:\\Users\\Katsiaryna\\coursera\\tim\\jobs.txt";

    public void readFile() throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(path));
        StringTokenizer st;
        String str;

        int size = Integer.valueOf(br.readLine());
        jobs = new double[size][2];

        int i = 0;

        while ((str = br.readLine()) != null) {
            st = new StringTokenizer(str);
            int w_l = Integer.valueOf(st.nextToken());
            jobs[i][1] = Integer.valueOf(st.nextToken());
            jobs[i][0] = w_l/jobs[i][1];
            i++;
        }
    }

    public void counter() throws Exception{
        readFile();
        int answer = 0, tmp = 0;

        Arrays.sort(jobs, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o1[0], o2[0]);
            }
        });

        for (int i = jobs.length-1; i >= 0; i--){
            tmp += jobs[i][1];
            answer += (jobs[i][0]*jobs[i][1])*tmp;
        }

        System.out.println("Weighted completion using w/l = " + answer);
    }

    public static void main(String[] arg) throws Exception{
        WCDevided wc = new WCDevided();
        wc.counter();
    }
}
