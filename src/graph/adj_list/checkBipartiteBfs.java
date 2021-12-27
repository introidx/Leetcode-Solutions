package graph.adj_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class checkBipartiteBfs {

    public static void main(String[] args) {
        int n = 7;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<Integer>());

        adj.get(0).add(1);
        adj.get(1).add(0);

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(2).add(3);
        adj.get(3).add(2);

        adj.get(4).add(3);
        adj.get(3).add(4);

        adj.get(4).add(5);
        adj.get(5).add(4);

        adj.get(4).add(6);
        adj.get(6).add(4);

        adj.get(1).add(6);
        adj.get(6).add(1);

        boolean res = checkBipartite(n, adj);
        System.out.println("Graph is bipartite? " + res);

    }

    public static boolean checkBipartite(int v, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            color[i] = -1;
        }

        for (int i = 1; i <= v; i++) {
            if (color[i] == -1) {
                if (!checkBfs(i, color, adj)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkBfs(int node, int[] color, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        color[node] = 1;

        while (!q.isEmpty()) {
            node = q.poll();
            for (int it : adj.get(node)) {
                if (color[it] == -1) {
                    color[it] = 1 - color[node];
                    q.add(it);
                } else if (color[it] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }
}
