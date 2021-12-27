package graph.adj_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bfsCycleDetect {

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
                if (isBfsCycle(i, vis, adj)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isBfsCycle(int i, boolean[] vis, ArrayList<ArrayList<Integer>> adj) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(i, -1));
        vis[i] = true;

        while (!q.isEmpty()) {
            int node = q.peek().first;
            int parentNode = q.peek().second;
            q.remove();

            for (int temp : adj.get(node)) {
                if (vis[temp] == false) {
                    q.add(new Node(temp, node));
                    vis[temp] = true;
                } else if (parentNode != temp)
                    return true;
            }
        }
        return false;
    }
}

class Node {
    int first;
    int second;

    public Node(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
