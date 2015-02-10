//The file contains the adjacency list representation of a simple undirected graph. There are 200 vertices labeled 1
// to 200. The first column in the file represents the vertex label, and the particular row (other entries except
// the first column) tells all the vertices that the vertex is adjacent to. So for example, the 6th row looks like :
// "6	155	56	52	120	......". This just means that the vertex with label 6 is adjacent to (i.e., shares an edge with)
// the vertices with labels 155,56,52,120,......,etc
//
//        Your task is to code up and run the randomized contraction algorithm for the min cut problem and use it on
// the above graph to compute the min cut (i.e., the minimum-possible number of crossing edges). (HINT: Note that
// you'll have to figure out an implementation of edge contractions. Initially, you might want to do this naively,
// creating a new graph from the old every time there's an edge contraction. But you should also think about more
// efficient implementations.) (WARNING: As per the video lectures, please make sure to run the algorithm many times
// with different random seeds, and remember the smallest cut that you ever find.) Write your numeric answer in the
// space provided. So e.g., if your answer is 5, just type 5 in the space provided.

package com.crackjava.tim;

import java.util.*;
import java.io.File;
import java.util.Scanner;

public class GraphMinCut {

    private final MyFileReader fileReader;

    public GraphMinCut() {
        fileReader = new MyFileReader();
    }

    public int randomU(Map<Integer, ArrayList<Integer>> arr, int v) {
        int u = 0;
        Random r = new Random();

        int index = r.nextInt(arr.get(v).size());
        u = arr.get(v).get(index);

        return u;
    }

    public int randomV(Map<Integer, ArrayList<Integer>> arr) {
        int v = 0;
        Random r = new Random();

        int index = r.nextInt(arr.size());
        Set<Integer> key = arr.keySet();
        Integer[] keys = key.toArray(new Integer[key.size()]);
        v = keys[index];

        return v;
    }

    public void randomContraction(Map<Integer, ArrayList<Integer>> arr) {
        while (arr.size() > 2) {
            int v = randomV(arr);
            int u = randomU(arr, v);

            for (int i = 0; i < arr.get(u).size(); i++){
                int keyChange = arr.get(u).get(i);
                if (arr.containsKey(keyChange)){
                    for (int j = 0; j < arr.get(keyChange).size(); j++){
                        if (arr.get(keyChange).get(j).equals(u)){
                            int index = arr.get(keyChange).indexOf(u);
                            arr.get(keyChange).remove(index);
                            arr.get(keyChange).add(v);
                        }
                    }
                }
            }

            arr.get(v).addAll(arr.get(u));

            ArrayList tmp = new ArrayList(); //to collect all non-v values

            for (int i = 0; i < arr.get(v).size(); i++) {
                if (!arr.get(v).get(i).equals(v)) {
                    tmp.add(arr.get(v).get(i));
                }
            }
            arr.get(v).clear();
            arr.get(v).addAll(tmp);

            arr.remove(u);
        }
    }

    public static void main(String[] args) throws Exception {
        GraphMinCut graphMinCut = new GraphMinCut();
        graphMinCut.calculate("C:\\Users\\Katsiaryna\\coursera\\tim\\kargerMinCut.txt");
    }

    public void calculate(String path) throws Exception {
        int i = 0;
        while (i < 200) {

            Scanner scanner = new Scanner(new File(path));

            Scanner lineScanner;

            Map<Integer, ArrayList<Integer>> array = new LinkedHashMap<Integer, ArrayList<Integer>>();

            while (scanner.hasNext()) {
                ArrayList subArray = new ArrayList();
                lineScanner = new Scanner(scanner.nextLine());
                int key = lineScanner.nextInt();
                while (lineScanner.hasNext())
                    subArray.add(lineScanner.nextInt());
                array.put(key, subArray);
            }

            randomContraction(array);

            if (i == 50 || i == 100 || i == 150)
                System.out.println();
            System.out.print(" " + array.get(randomV(array)).size());
            i++;
        }
    }
}
