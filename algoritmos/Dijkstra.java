package Algoritmos;

import java.util.*;

public class Dijkstra {

    // G: u -> lista de (v, peso) representados por int[]{v, w}
    public static Map<Integer, Integer> dijkstra(Map<Integer, List<int[]>> G, int s) {
        final int INF = Integer.MAX_VALUE;

        Map<Integer, Integer> dist = new HashMap<>();
        for (int u : G.keySet()) dist.put(u, INF);
        dist.put(s, 0);

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];

            if (d != dist.getOrDefault(u, INF)) continue;

            for (int[] e : G.getOrDefault(u, Collections.emptyList())) {
                int v = e[0], w = e[1];

                if (dist.get(u) != INF && dist.get(u) + w < dist.getOrDefault(v, INF)) {
                    dist.put(v, dist.get(u) + w);
                    pq.add(new int[]{v, dist.get(v)});
                }
            }
        }
        return dist;
    }

    // Método principal para teste
    public static void main(String[] args) {

        // Grafo ponderado (lista de adjacência com pesos)
        Map<Integer, List<int[]>> G = new HashMap<>();

        // Exemplo (direcionado). Se quiser não-direcionado, adicione as arestas de volta.
        G.put(1, Arrays.asList(new int[]{2, 7}, new int[]{3, 9}, new int[]{6, 14}));
        G.put(2, Arrays.asList(new int[]{3, 10}, new int[]{4, 15}));
        G.put(3, Arrays.asList(new int[]{4, 11}, new int[]{6, 2}));
        G.put(4, Arrays.asList(new int[]{5, 6}));
        G.put(5, Collections.emptyList());
        G.put(6, Arrays.asList(new int[]{5, 9}));

        int origem = 1;

        Map<Integer, Integer> dist = dijkstra(G, origem);

        System.out.println("Dijkstra — distâncias mínimas a partir do vértice " + origem);
        System.out.println("\nVértice | Distância");

        List<Integer> vertices = new ArrayList<>(dist.keySet());
        Collections.sort(vertices);

        for (int v : vertices) {
            int d = dist.get(v);
            String ds = (d == Integer.MAX_VALUE) ? "INF" : String.valueOf(d);
            System.out.printf("%7d | %8s%n", v, ds);
        }
    }
}
