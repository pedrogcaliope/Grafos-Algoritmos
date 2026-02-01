package Algoritmos;

import java.util.*;

public class FloydWarshall {

    // dist[i][j] representa o peso da aresta i->j
    // ou Integer.MAX_VALUE caso não exista aresta
    public static void floydWarshall(int[][] dist, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE &&
                            dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    // Método principal para teste
    public static void main(String[] args) {
        final int INF = Integer.MAX_VALUE;
        int n = 4; // vértices 0..3

        // Matriz de distâncias inicial
        int[][] dist = {
                {0,   5,   INF, 10},
                {INF, 0,   3,   INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0}
        };

        System.out.println("Matriz inicial:");
        printMatrix(dist, INF);

        floydWarshall(dist, n);

        System.out.println("\nMatriz final (menores caminhos):");
        printMatrix(dist, INF);
    }

    private static void printMatrix(int[][] dist, int INF) {
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                if (dist[i][j] == INF) System.out.print("INF ");
                else System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}

