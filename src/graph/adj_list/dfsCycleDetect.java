package graph.adj_list;


import java.util.ArrayList;
import java.util.Scanner;

public class dfsCycleDetect {
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

        boolean isThereACycle = isCycle(v, adj);
        System.out.println(isThereACycle);
    }

    private static boolean isCycle(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[v];
        for (int i = 1; i <= v; i++) {
            if (vis[i] == false) {
                if (dfsCycle(i, -1, vis, adj)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfsCycle(int node, int parent, boolean[] vis, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;
        for (int it : adj.get(node)) {
            if (vis[it] == false) {
                if (dfsCycle(it, node, vis, adj) == true) {
                    return true;
                }

            } else if (it != parent) return false;
        }
        return false;
    }
}
