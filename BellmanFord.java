package Algoritmos;

import java.util.*;

public class BellmanFord {

    // Aresta: (u, v, peso)
    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    // n = número de vértices (0 .. n-1)
    public static int[] bellmanFord(int n, List<Edge> edges, int s) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        // Relaxamento das arestas |V| - 1 vezes
        for (int i = 1; i < n; i++) {
            for (Edge e : edges) {
                if (dist[e.u] != Integer.MAX_VALUE &&
                        dist[e.u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.w;
                }
            }
        }
        return dist;
    }

    // Método principal para teste
    public static void main(String[] args) {

        int n = 5;          // vértices: 0,1,2,3,4
        int origem = 0;

        List<Edge> edges = new ArrayList<>();

        // Grafo de exemplo (com pesos, pode ter pesos negativos)
        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 7));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(1, 4, -4));
        edges.add(new Edge(2, 3, -3));
        edges.add(new Edge(2, 4, 9));
        edges.add(new Edge(3, 1, -2));
        edges.add(new Edge(4, 3, 7));
        edges.add(new Edge(4, 0, 2));

        int[] dist = bellmanFord(n, edges, origem);

        System.out.println("Bellman-Ford — distâncias mínimas a partir do vértice " + origem);
        System.out.println();

        for (int v = 0; v < n; v++) {
            String d = (dist[v] == Integer.MAX_VALUE) ? "INF" : String.valueOf(dist[v]);
            System.out.println("Vértice " + v + " → " + d);
        }
    }
}
