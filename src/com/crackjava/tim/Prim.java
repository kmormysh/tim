//In this programming problem you'll code up Prim's minimum spanning tree algorithm. Download the text file here.
// This file describes an undirected graph with integer edge costs. It has the format
//        [number_of_nodes] [number_of_edges]
//        [one_node_of_edge_1] [other_node_of_edge_1] [edge_1_cost]
//        [one_node_of_edge_2] [other_node_of_edge_2] [edge_2_cost]
//        ...
//        For example, the third line of the file is "2 3 -8874", indicating that there is an edge connecting vertex
// #2 and vertex #3 that has cost -8874. You should NOT assume that edge costs are positive, nor should you assume
// that they are distinct.
//
//        Your task is to run Prim's minimum spanning tree algorithm on this graph. You should report the overall
// cost of a minimum spanning tree --- an integer, which may or may not be negative --- in the box below.
//
//        IMPLEMENTATION NOTES: This graph is small enough that the straightforward O(mn) time implementation of
// Prim's algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, try implementing
// a heap-based version. The simpler approach, which should already give you a healthy speed-up, is to maintain relevant
// edges in a heap (with keys = edge costs). The superior approach stores the unprocessed vertices in the heap, as
// described in lecture. Note this requires a heap that supports deletions, and you'll probably need to maintain some
// kind of mapping between vertices and their positions in the heap.


package com.crackjava.tim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Katsiaryna on 1/15/2015.
 */
public class Prim {
    private int[][] graph;
    private Set<Integer> x;
    private Set<Integer> v_x;
    public final static String path = "C:\\Users\\Katsiaryna\\coursera\\tim\\edges.txt";

    public void readFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringTokenizer st;
        String str;

        str = br.readLine();
        st = new StringTokenizer(str);
        int sizeV = Integer.valueOf(st.nextToken());
        graph = new int[sizeV+1][sizeV+1];
        for(int[] row: graph)
        Arrays.fill(row, Integer.MAX_VALUE);

        v_x = new HashSet<Integer>();

        while ((str = br.readLine()) != null) {
            st = new StringTokenizer(str);
            int v = Integer.valueOf(st.nextToken());
            int u = Integer.valueOf(st.nextToken());
            graph[v][u] = Integer.valueOf(st.nextToken());
            graph[u][v] = graph[v][u];
            v_x.add(v);
            v_x.add(u);
        }
    }

    public void countMST(){
        int[] count = new int[graph.length];
        x = new HashSet<Integer>();
        int[] weight = new int[graph.length];
        int i_weight = 0, j = 0;

        int z, y = 1;

        z = v_x.iterator().next();
        x.add(z);
        v_x.remove(z);
        count[j++] = z;

        while (v_x.size() > 0)
        {
            y = 0;
            z = x.iterator().next();
            for (Integer i_vx: v_x)
                if (graph[z][i_vx] < graph[z][y])
                    y = i_vx;

            for (Integer i_x: x)
                if (i_x != z)
                    for (Integer i_vx: v_x ) {
                        if (graph[i_x][i_vx] < graph[z][y]) {
                            z = i_x;
                            y = i_vx;
                        }
                    }
            weight[i_weight++] = graph[z][y];
            x.add(y);
            System.out.println(v_x.toString());
            v_x.remove(y);
            count[j++] = y;
        }

        System.out.println("Edges");
        for (int i = 0; i < count.length; i++) {
            System.out.print(count[i] + " ");
        }
        System.out.println();

        i_weight = 0;
        System.out.println("Weights");
        for (int i = 0; i < weight.length; i++) {
            System.out.print(weight[i] + " ");
            i_weight += weight[i];
        }
        System.out.println();
        System.out.println("Weight = " + i_weight);
    }

    public static void main(String[] arg) throws Exception{
        Prim pr = new Prim();
        pr.readFile();
        pr.countMST();
    }
}
