package graph.adj_list;

import java.util.*;

public class Graph {

    private LinkedList<Integer> adj[];

    public Graph(int v){
        adj = new LinkedList[v];
        for (int i =0 ; i < v; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination){
        adj[source].add(destination);
        adj[destination].add(source);
    }

    // bfs traversal
    public int bfs(int source, int destination){
        boolean vis[] = new boolean[adj.length];
        int parent[] = new int[adj.length];

        Queue<Integer> q = new LinkedList<>();
        q.add(source);

        parent[source] = -1;

        while(!q.isEmpty()){
            int curr = q.poll();
            if(curr == destination) break;;

            for (int neighbour : adj[curr]){
                if(!vis[neighbour]){
                    vis[neighbour] = true;
                    q.add(neighbour);
                    parent[neighbour] = curr;
                }
            }
        }
        // parent will hold node which introduced them first
        // parent -[-1, 0,1 , 4, 0]
        int curr = destination;
        int distance = 0;

        while(parent[curr] != -1){
            System.out.println(curr + " -> ");
            curr = parent[curr];
            distance++;
        }
        return distance;
    }

    // dfs Method 1 Using Recursion
    private boolean dfsUtil(int source, int destination, boolean[] vis){
        if(source == destination) return true;

        for (int neighbour :adj[source]){
            if(!vis[neighbour]){
                vis[neighbour] =true;
                boolean isConnected = dfsUtil(neighbour,destination,vis);
                if(isConnected) return true;
            }
        }
        return false;
    }

    public boolean dfs(int source,int destination){
        boolean[] vis = new boolean[adj.length];
        vis[source] =true;

        return dfsUtil(source, destination,vis);
    }

    // DFS method 2 : Stack
    public boolean dfsStack(int source, int destination){
        boolean vis[] = new boolean[adj.length];
        vis[source] = true;
        Stack<Integer> stack = new Stack<>();

        stack.push(source);

        while(!stack.isEmpty()) {
            int cur = stack.pop();

            if(cur == destination) return true;

            for(int neighbor: adj[cur]) {
                if(!vis[neighbor]) {
                    vis[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices and edges");

        int v= sc.nextInt();
        int e= sc.nextInt();

        Graph graph = new Graph(v);
        System.out.println("Enter " +e + " edges" );

        for (int i =0; i < e; i++){
            int source = sc.nextInt();
            int destination = sc.nextInt();

            graph.addEdge(source, destination);
        }

        // find the distance between source and destination using bfs
        int source = sc.nextInt();
        int destination = sc.nextInt();

        System.out.println("Shortest distance is" + graph.bfs(source, destination));

        // find if the source to destination exist in graph

        System.out.println("Path exist " + graph.dfs(source, destination));
        System.out.println("Path exist " + graph.dfsStack(source, destination));
    }

}
