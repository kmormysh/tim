//The file contains an adjacency list representation of an undirected weighted graph with 200 vertices labeled 1 to 200.
// Each row consists of the node tuples that are adjacent to that particular vertex along with the length of that edge.
// For example, the 6th row has 6 as the first entry indicating that this row corresponds to the vertex labeled 6.
// The next entry of this row "141,8200" indicates that there is an edge between vertex 6 and vertex 141 that has
// length 8200. The rest of the pairs of this row indicate the other vertices adjacent to vertex 6 and the lengths
// of the corresponding edges.
//
//        Your task is to run Dijkstra's shortest-path algorithm on this graph, using 1 (the first vertex) as the
// source vertex, and to compute the shortest-path distances between 1 and every other vertex of the graph. If there
// is no path between a vertex v and vertex 1, we'll define the shortest-path distance between 1 and v to be 1000000.
//
//        You should report the shortest-path distances to the following ten vertices, in order:
// 7,37,59,82,99,115,133,165,188,197. You should encode the distances as a comma-separated string of integers.
// So if you find that all ten of these vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000
// distance away, then your answer should be 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. Remember the order of
// reporting DOES MATTER, and the string should be in the same order in which the above ten vertices are given. Please
// type your answer in the space provided.
//
//        IMPLEMENTATION NOTES: This graph is small enough that the straightforward O(mn) time implementation of
// Dijkstra's algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, try implementing
// the heap-based version. Note this requires a heap that supports deletions, and you'll probably need to maintain some
// kind of mapping between vertices and their positions in the heap.

package com.crackjava.tim;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Katsiaryna on 1/8/2015.
 */

class Vertex implements Comparable<Vertex>
{
    public int v;
    public Edge[] adjacencies;
    public int minDistance;
    public Vertex previous;
    public int compareTo(Vertex other)
    {
        return Integer.compare(minDistance, other.minDistance);
    }
}

class Edge
{
    public final Vertex target;
    public final int weight;
    public Edge(Vertex argTarget, int argWeight)
    {
        target = argTarget;
        weight = argWeight;
    }
}

public class Dijkstra {

    public static int n = 200;
    public static String path = "C:\\Users\\Katsiaryna\\coursera\\tim\\dijkstraData.txt";

    public void computePaths(Vertex source)
    {
        source.minDistance = 0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                int weight = e.weight;
                int distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

    public Vertex readFile (){
        return null;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(path));
        String str;
        StringTokenizer st;

        List<Vertex> vert = new ArrayList<Vertex>(n);
        Vertex v = new Vertex();
        Edge u;
        int i = 0;
        int j = 0;

//        while ((str = br.readLine()) != null) {
//            st = new StringTokenizer(str);
//            //Integer vValue =
//            v.v = Integer.valueOf(st.nextToken());
//            int length = st.countTokens();
//
//            //;vert.[i] = new Vertex();
//            vert[i].adjacencies = new Edge[length];
//
//            String delimeters = ",";
//            while (st.hasMoreTokens()) {
//                StringTokenizer sv = new StringTokenizer((st.nextToken()).toString(), delimeters);
//                while (sv.hasMoreTokens()) {
//                    Vertex tmp = new Vertex();
//                    tmp.previous = vert[i];
//                    tmp.v = Integer.valueOf(sv.nextToken());
//                    u = new Edge(tmp, Integer.valueOf(sv.nextToken()));
//                    vert[i].adjacencies[j++] = u;
//                }
//            }
//            i++;
//        }


    }
}
