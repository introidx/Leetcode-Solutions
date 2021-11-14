package graph.adj_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bfsTraversal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt(); // vertex
        int e = sc.nextInt(); // edge

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(i, new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();

            adj.get(s).add(d);
        }

        ArrayList<Integer> res = bfsTraversal(v, adj);
        for (int i : res) {
            System.out.println(i);
        }


    }

    public static ArrayList<Integer> bfsTraversal(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] vis = new boolean[v];
        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        vis[0] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            res.add(node);

            for (int i : adj.get(node)) {
                if (vis[i] == false) {
                    vis[i] = true;
                    q.add(i);
                }
            }

        }
        return res;

    }


}
