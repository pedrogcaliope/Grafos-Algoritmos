package Algoritmos;

import java.util.*;

public class BFS {

    // Classe para armazenar o resultado da BFS
    public static class Result {
        public final Map<Integer, Integer> dist;
        public final Map<Integer, Integer> pred;

        Result(Map<Integer, Integer> dist, Map<Integer, Integer> pred) {
            this.dist = dist;
            this.pred = pred;
        }
    }

    // Implementação da Busca em Largura (BFS)
    public static Result bfs(Map<Integer, List<Integer>> G, int s) {
        final int INF = Integer.MAX_VALUE;

        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> pred = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();

        for (int u : G.keySet()) {
            dist.put(u, INF);
            pred.put(u, null);
        }

        dist.put(s, 0);
        visited.add(s);
        q.add(s);

        while (!q.isEmpty()) {
            int u = q.remove();

            for (int v : G.getOrDefault(u, Collections.emptyList())) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    dist.put(v, dist.get(u) + 1);
                    pred.put(v, u);
                    q.add(v);
                }
            }
        }

        return new Result(dist, pred);
    }

    // Método principal para teste
    public static void main(String[] args) {

        // Grafo de exemplo (lista de adjacência)
        Map<Integer, List<Integer>> G = new HashMap<>();

        G.put(1, Arrays.asList(2, 3));
        G.put(2, Arrays.asList(1, 4, 5));
        G.put(3, Arrays.asList(1, 6));
        G.put(4, Arrays.asList(2));
        G.put(5, Arrays.asList(2, 6));
        G.put(6, Arrays.asList(3, 5));

        int origem = 1;

        Result r = bfs(G, origem);

        System.out.println("Busca em Largura (BFS) a partir do vértice " + origem);
        System.out.println("\nVértice | Distância | Predecessor");

        for (int v : r.dist.keySet()) {
            String d = (r.dist.get(v) == Integer.MAX_VALUE) ? "INF" : r.dist.get(v).toString();
            System.out.printf("%7d | %9s | %11s%n",
                    v,
                    d,
                    r.pred.get(v));
        }
    }
}
