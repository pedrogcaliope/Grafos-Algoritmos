package Algoritmos;

import java.util.*;

public class DFS {

    // Retorna o mapa de predecessores (árvore/floresta de DFS)
    public static Map<Integer, Integer> dfs(Map<Integer, List<Integer>> G) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> pred = new HashMap<>();

        for (int u : G.keySet()) {
            if (!visited.contains(u)) {
                pred.put(u, null);
                dfsVisit(G, u, visited, pred);
            }
        }
        return pred;
    }

    private static void dfsVisit(Map<Integer, List<Integer>> G, int u,
                                 Set<Integer> visited,
                                 Map<Integer, Integer> pred) {
        visited.add(u);

        for (int v : G.getOrDefault(u, Collections.emptyList())) {
            if (!visited.contains(v)) {
                pred.put(v, u);
                dfsVisit(G, v, visited, pred);
            }
        }
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

        Map<Integer, Integer> pred = dfs(G);

        System.out.println("Busca em Profundidade (DFS) — mapa de predecessores");
        System.out.println("\nVértice | Predecessor");

        // Ordena para imprimir bonito
        List<Integer> vertices = new ArrayList<>(pred.keySet());
        Collections.sort(vertices);

        for (int v : vertices) {
            System.out.printf("%7d | %11s%n", v, pred.get(v));
        }
    }
}
