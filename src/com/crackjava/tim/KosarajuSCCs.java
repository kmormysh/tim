//The file contains the edges of a directed graph. Vertices are labeled as positive integers from 1 to 875714.
// Every row indicates an edge, the vertex label in first column is the tail and the vertex label in second column
// is the head (recall the graph is directed, and the edges are directed from the first column vertex to the second
// column vertex). So for example, the 11th row looks liks : "2 47646". This just means that the vertex with label 2
// has an outgoing edge to the vertex with label 47646
//
//Your task is to code up the algorithm from the video lectures for computing strongly connected components (SCCs),
// and to run this algorithm on the given graph.
//
//        Output Format: You should output the sizes of the 5 largest SCCs in the given graph, in decreasing order of
// sizes, separated by commas (avoid any spaces). So if your algorithm computes the sizes of the five largest SCCs to
// be 500, 400, 300, 200 and 100, then your answer should be "500,400,300,200,100". If your algorithm finds less than
// 5 SCCs, then write 0 for the remaining terms. Thus, if your algorithm computes only 3 SCCs whose sizes are 400, 300,
// and 100, then your answer should be "400,300,100,0,0".
//
//        WARNING: This is the most challenging programming assignment of the course. Because of the size of the graph
// you may have to manage memory carefully. The best way to do this depends on your programming language and
// environment, and we strongly suggest that you exchange tips for doing this on the discussion forums.

package com.crackjava.tim;

import sun.security.provider.certpath.Vertex;

import java.io.*;
import java.sql.Time;
import java.util.*;

/**
 * Created by Katsiaryna on 1/7/2015.
 */
public class KosarajuSCCs {
    public final int n = 875714;
    public final String path = "C:\\Users\\Katsiaryna\\coursera\\tim\\SCC.txt";
    public ArrayList<Integer>[] g = new ArrayList[n];
    public ArrayList<Integer>[] gRev = new ArrayList[n];
    public int[] visited = new int[n];
    public int[] finishingTime = new int[n];
    public int[] leader = new int[n];
    public int lead = 0;
    public int ft = 0;

    KosarajuSCCs() throws Exception{

        Date start = new Date();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String str;
        StringTokenizer st;

        for (int i = 0; i < n; i ++)
        {
            g[i] = new ArrayList<Integer>(1);
            gRev[i] = new ArrayList<Integer>(1);
            visited[i] = 0;
        }

        while ((str = br.readLine()) != null) {
            st = new StringTokenizer(str);
            Integer v = Integer.valueOf(st.nextToken());
            Integer u = Integer.valueOf(st.nextToken());
            g[v-1].add(u - 1);
            gRev[u-1].add(v - 1);
        }

        Date finish = new Date();
        System.out.println("File read in " + (finish.getTime()-start.getTime()));
    }

    public void dfs(ArrayList<Integer>[] graph, int i) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(i);
        visited[i] = 1;
        leader[i] = lead;

        if (graph[i].size() > 0) {
            for (int u = 0; u < list.size(); u++) {
                visited[list.get(u)] = 1;
                leader[list.get(u)] = lead;

                for (Integer v : graph[list.get(u)])
                    if (visited[v] == 0) {
                        list.add(v);
                        //i = v.intValue();
                    }
            }
        }
        while (list.size() > 0) {
            int v = list.size();
            if (finishingTime[list.get(v - 1)] == 0)
                finishingTime[list.get(v - 1)] = ++ft;
            list.remove(v - 1);
        }
    }

    public void dfsloop(ArrayList<Integer>[] graph, int[] nodes, int pass) {
        int i = 0;
        for (i = n - 1; i >= 0; i--) {
            int k = i;
            if (pass == 2)
                k = nodes[i];
            if (visited[k] == 0) {
                lead = k;
                dfs(graph, k);
            }
        }
    }

    public void dfsloop(ArrayList<Integer>[] graph) {
        int i = 0;
        for (i = n - 1; i >= 0; i--) {
            int k = i;
            if (visited[k] == 0) {
                lead = k;
                dfs(graph, k);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        System.out.println("Started");
        KosarajuSCCs sccs = new KosarajuSCCs();

        System.out.print("The first pass, for Graph. ");

        Date start = new Date();
        sccs.dfsloop(sccs.gRev);
        Date finish = new Date();
        System.out.println("DFS-loop for G(rev) completed in " + (finish.getTime() - start.getTime()));

        System.out.print("End of the first pass, for Graph. ");

        for (int i = 0; i < sccs.n; i++)
            sccs.visited[i] = 0;
        int[] nodes = new int[sccs.n];

        for (int i = 0; i < sccs.n; i++)
            nodes[sccs.finishingTime[i] - 1] = i;

        start = new Date();
        sccs.dfsloop(sccs.g, nodes, 2);
        finish = new Date();
        System.out.println("DFS-loop for G completed in " + (finish.getTime() - start.getTime()));

        System.out.println("Sizes of SCCs: ");
        Arrays.sort(sccs.leader);
        ArrayList<Integer> stat = new ArrayList<Integer>(1);
        int pre = 0;
        for (int i = 0; i < sccs.n - 1; i++) {
            if (sccs.leader[i] != sccs.leader[i + 1]) {
                stat.add(new Integer(i - pre + 1));
                pre = i + 1;
            }
        }

        stat.add(new Integer(sccs.n - pre));
        Integer[] s1 = stat.toArray(new Integer[stat.size()]);

        int[] s = new int[s1.length];
        for (int i = 0; i < s1.length; i++) s[i] = s1[i].intValue();
        Arrays.sort(s);
        int i = s.length - 1, j = 0;
        /* print only the top 5 largest SCC's */
//            for (; j < 5; j++, i--) System.out.print(s[i] + " ");
//            System.out.println();

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (j = 0; j < sccs.leader.length; j++) {
            if (!map.containsKey(sccs.leader[j]))
                map.put(sccs.leader[j], null);
        }
        // System.out.println("Number of leaders: " + map.size());
    }
}
