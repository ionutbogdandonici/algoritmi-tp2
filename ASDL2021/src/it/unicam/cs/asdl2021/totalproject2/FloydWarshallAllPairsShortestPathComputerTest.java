package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.intellij.lang.annotations.Flow;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Template: Luca Tesei
 */
class FloydWarshallAllPairsShortestPathComputerTest {

    @Test
    final void testFloydWarshallAllPairsShortestPathComputer() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 3);
        graph.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, true, 8);
        graph.addEdge(edgeAC);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, -4);
        graph.addEdge(edgeAE);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, true, 1);
        graph.addEdge(edgeBD);
        GraphEdge<String> edgeBE = new GraphEdge<>(nodeB, nodeE, true, 7);
        graph.addEdge(edgeBE);
        GraphEdge<String> edgeCB = new GraphEdge<>(nodeC, nodeB, true, 4);
        graph.addEdge(edgeCB);
        GraphEdge<String> edgeDA = new GraphEdge<>(nodeD, nodeA, true, 2);
        graph.addEdge(edgeDA);
        GraphEdge<String> edgeDC = new GraphEdge<>(nodeD, nodeC, true, -5);
        graph.addEdge(edgeDC);
        GraphEdge<String> edgeED = new GraphEdge<>(nodeE, nodeD, true, 6);
        graph.addEdge(edgeED);

        FloydWarshallAllPairsShortestPathComputer<String> alg = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        // Check delle Exceptions
        assertThrows(NullPointerException.class, () -> {
            FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(null);
        });
        // Grafo vuoto
        assertThrows(IllegalArgumentException.class, () -> {
            Graph<String> x = new AdjacencyMatrixDirectedGraph<>();
            FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(x);
        });
        // Grafo non orientato
        assertThrows(IllegalArgumentException.class, () -> {
            Graph<String> x = new MapAdjacentListUndirectedGraph<>();
            GraphNode<String> A = new GraphNode<>("A");
            GraphNode<String> B = new GraphNode<>("B");
            GraphEdge<String> AB = new GraphEdge<>(A, B, false, 4);
            FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(x);
        });
        // Archi non pesati
        assertThrows(IllegalArgumentException.class, () -> {
            Graph<String> x = new AdjacencyMatrixDirectedGraph<>();
            GraphNode<String> A = new GraphNode<>("A");
            GraphNode<String> B = new GraphNode<>("B");
            GraphEdge<String> AB = new GraphEdge<>(A, B, true);
            FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(x);
        });
        assertFalse(alg.isComputed());
        alg.computeShortestPaths();
        assertTrue(alg.isComputed());
    }

    @Test
    final void testComputeShortestPaths() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 3);
        graph.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, true, 8);
        graph.addEdge(edgeAC);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, -4);
        graph.addEdge(edgeAE);
        GraphEdge<String> edgeEA = new GraphEdge<>(nodeE, nodeA, true, -2);
        graph.addEdge(edgeEA);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, true, 1);
        graph.addEdge(edgeBD);
        GraphEdge<String> edgeBE = new GraphEdge<>(nodeB, nodeE, true, 7);
        graph.addEdge(edgeBE);
        GraphEdge<String> edgeCB = new GraphEdge<>(nodeC, nodeB, true, 4);
        graph.addEdge(edgeCB);
        GraphEdge<String> edgeDA = new GraphEdge<>(nodeD, nodeA, true, 2);
        graph.addEdge(edgeDA);
        GraphEdge<String> edgeDC = new GraphEdge<>(nodeD, nodeC, true, -5);
        graph.addEdge(edgeDC);
        GraphEdge<String> edgeED = new GraphEdge<>(nodeE, nodeD, true, 6);
        graph.addEdge(edgeED);

        // IllegalStateException per i cicli negativi
        FloydWarshallAllPairsShortestPathComputer<String> alg = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        FloydWarshallAllPairsShortestPathComputer<String> finalAlg = alg;
        assertThrows(IllegalStateException.class, () -> {
            finalAlg.computeShortestPaths();
        });
        // Rimozione dell'edge che crea il loop negativo
        graph.removeEdge(edgeEA);
        // Ripasso il grafo all'algoritmo
        alg = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        assertFalse(alg.isComputed());
        alg.computeShortestPaths();
        assertEquals(graph, alg.getGraph());
    }

    @Test
    final void testIsComputed() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 3);
        graph.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, true, 8);
        graph.addEdge(edgeAC);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, -4);
        graph.addEdge(edgeAE);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, true, 1);
        graph.addEdge(edgeBD);
        GraphEdge<String> edgeBE = new GraphEdge<>(nodeB, nodeE, true, 7);
        graph.addEdge(edgeBE);
        GraphEdge<String> edgeCB = new GraphEdge<>(nodeC, nodeB, true, 4);
        graph.addEdge(edgeCB);
        GraphEdge<String> edgeDA = new GraphEdge<>(nodeD, nodeA, true, 2);
        graph.addEdge(edgeDA);
        GraphEdge<String> edgeDC = new GraphEdge<>(nodeD, nodeC, true, -5);
        graph.addEdge(edgeDC);
        GraphEdge<String> edgeED = new GraphEdge<>(nodeE, nodeD, true, 6);
        graph.addEdge(edgeED);

        FloydWarshallAllPairsShortestPathComputer<String> alg = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        assertFalse(alg.isComputed());
        alg.computeShortestPaths();
        assertTrue(alg.isComputed());
    }

    @Test
    final void testGetGraph() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 3);
        graph.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, true, 8);
        graph.addEdge(edgeAC);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, -4);
        graph.addEdge(edgeAE);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, true, 1);
        graph.addEdge(edgeBD);
        GraphEdge<String> edgeBE = new GraphEdge<>(nodeB, nodeE, true, 7);
        graph.addEdge(edgeBE);
        GraphEdge<String> edgeCB = new GraphEdge<>(nodeC, nodeB, true, 4);
        graph.addEdge(edgeCB);
        GraphEdge<String> edgeDA = new GraphEdge<>(nodeD, nodeA, true, 2);
        graph.addEdge(edgeDA);
        GraphEdge<String> edgeDC = new GraphEdge<>(nodeD, nodeC, true, -5);
        graph.addEdge(edgeDC);
        GraphEdge<String> edgeED = new GraphEdge<>(nodeE, nodeD, true, 6);
        graph.addEdge(edgeED);

        FloydWarshallAllPairsShortestPathComputer<String> alg = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        alg.computeShortestPaths();
        assertEquals(graph, alg.getGraph());
    }

    @Test
    final void testGetShortestPath() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 3);
        graph.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, true, 8);
        graph.addEdge(edgeAC);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, -4);
        graph.addEdge(edgeAE);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, true, 1);
        graph.addEdge(edgeBD);
        GraphEdge<String> edgeBE = new GraphEdge<>(nodeB, nodeE, true, 7);
        graph.addEdge(edgeBE);
        GraphEdge<String> edgeCB = new GraphEdge<>(nodeC, nodeB, true, 4);
        graph.addEdge(edgeCB);
        GraphEdge<String> edgeDA = new GraphEdge<>(nodeD, nodeA, true, 2);
        graph.addEdge(edgeDA);
        GraphEdge<String> edgeDC = new GraphEdge<>(nodeD, nodeC, true, -5);
        graph.addEdge(edgeDC);
        GraphEdge<String> edgeED = new GraphEdge<>(nodeE, nodeD, true, 6);
        graph.addEdge(edgeED);
        FloydWarshallAllPairsShortestPathComputer<String> alg = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        alg.computeShortestPaths();
        List<GraphEdge<String>> toAdd = new ArrayList<>();
        toAdd.add(edgeAE);
        toAdd.add(edgeED);
        toAdd.add(edgeDC);
        toAdd.add(edgeCB);
        assertNull(alg.getShortestPath(nodeA, nodeA));
        assertEquals(toAdd, alg.getShortestPath(nodeA, nodeB));
    }

    @Test
    final void testGetShortestPathCost() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 3);
        graph.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, true, 8);
        graph.addEdge(edgeAC);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, -4);
        graph.addEdge(edgeAE);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, true, 1);
        graph.addEdge(edgeBD);
        GraphEdge<String> edgeBE = new GraphEdge<>(nodeB, nodeE, true, 7);
        graph.addEdge(edgeBE);
        GraphEdge<String> edgeCB = new GraphEdge<>(nodeC, nodeB, true, 4);
        graph.addEdge(edgeCB);
        GraphEdge<String> edgeDA = new GraphEdge<>(nodeD, nodeA, true, 2);
        graph.addEdge(edgeDA);
        GraphEdge<String> edgeDC = new GraphEdge<>(nodeD, nodeC, true, -5);
        graph.addEdge(edgeDC);
        GraphEdge<String> edgeED = new GraphEdge<>(nodeE, nodeD, true, 6);
        graph.addEdge(edgeED);
        FloydWarshallAllPairsShortestPathComputer<String> alg = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        // Grafo non computato
        assertThrows(IllegalStateException.class, () -> {
            alg.getShortestPathCost(nodeA, nodeB);
        });
        alg.computeShortestPaths();
        // Parametri null
        assertThrows(NullPointerException.class, () -> {
            alg.getShortestPathCost(null, nodeA);
        });
        assertThrows(NullPointerException.class, () -> {
            alg.getShortestPathCost(nodeA, null);
        });
        assertThrows(NullPointerException.class, () -> {
            alg.getShortestPathCost(null, null);
        });
        // Nodi non esistenti
        GraphNode<String> X = new GraphNode<>("x");
        GraphNode<String> Y = new GraphNode<>("y");
        assertThrows(IllegalArgumentException.class, () -> {
            alg.getShortestPathCost(X, nodeA);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            alg.getShortestPathCost(nodeA, X);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            alg.getShortestPathCost(X, Y);
        });

        assertEquals(-5, alg.getShortestPathCost(nodeD, nodeC));
        assertEquals(7, alg.getShortestPathCost(nodeC, nodeA));
        assertEquals(6, alg.getShortestPathCost(nodeE, nodeD));
    }

    @Test
    final void testPrintPath() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 3);
        graph.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, true, 8);
        graph.addEdge(edgeAC);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, -4);
        graph.addEdge(edgeAE);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, true, 1);
        graph.addEdge(edgeBD);
        GraphEdge<String> edgeBE = new GraphEdge<>(nodeB, nodeE, true, 7);
        graph.addEdge(edgeBE);
        GraphEdge<String> edgeCB = new GraphEdge<>(nodeC, nodeB, true, 4);
        graph.addEdge(edgeCB);
        GraphEdge<String> edgeDA = new GraphEdge<>(nodeD, nodeA, true, 2);
        graph.addEdge(edgeDA);
        GraphEdge<String> edgeDC = new GraphEdge<>(nodeD, nodeC, true, -5);
        graph.addEdge(edgeDC);
        GraphEdge<String> edgeED = new GraphEdge<>(nodeE, nodeD, true, 6);
        graph.addEdge(edgeED);
        FloydWarshallAllPairsShortestPathComputer<String> alg = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        alg.computeShortestPaths();
        System.out.println(alg.printPath(alg.getShortestPath(nodeA, nodeB)));
    }

    @Test
    final void testGetCostMatrix() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 3);
        graph.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, true, 8);
        graph.addEdge(edgeAC);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, -4);
        graph.addEdge(edgeAE);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, true, 1);
        graph.addEdge(edgeBD);
        GraphEdge<String> edgeBE = new GraphEdge<>(nodeB, nodeE, true, 7);
        graph.addEdge(edgeBE);
        GraphEdge<String> edgeCB = new GraphEdge<>(nodeC, nodeB, true, 4);
        graph.addEdge(edgeCB);
        GraphEdge<String> edgeDA = new GraphEdge<>(nodeD, nodeA, true, 2);
        graph.addEdge(edgeDA);
        GraphEdge<String> edgeDC = new GraphEdge<>(nodeD, nodeC, true, -5);
        graph.addEdge(edgeDC);
        GraphEdge<String> edgeED = new GraphEdge<>(nodeE, nodeD, true, 6);
        graph.addEdge(edgeED);
        FloydWarshallAllPairsShortestPathComputer<String> alg = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        alg.computeShortestPaths();
        double[][] toCheck = new double[5][5];
        toCheck[0][0] = 0;
        toCheck[0][1] = 1;
        toCheck[0][2] = -3;
        toCheck[0][3] = 2;
        toCheck[0][4] = -4;
        toCheck[1][0] = 3;
        toCheck[1][1] = 0;
        toCheck[1][2] = -4;
        toCheck[1][3] = 1;
        toCheck[1][4] = -1;
        toCheck[2][0] = 7;
        toCheck[2][1] = 4;
        toCheck[2][2] = 0;
        toCheck[2][3] = 5;
        toCheck[2][4] = 3;
        toCheck[3][0] = 2;
        toCheck[3][1] = -1;
        toCheck[3][2] = -5;
        toCheck[3][3] = 0;
        toCheck[3][4] = -2;
        toCheck[4][0] = 8;
        toCheck[4][1] = 5;
        toCheck[4][2] = 1;
        toCheck[4][3] = 6;
        toCheck[4][4] = 0;
        double[][] x = alg.getCostMatrix();
        for(int i=0;i<toCheck.length;i++){
            for(int j=0;j<toCheck.length;j++){
                assertEquals(toCheck[i][j], x[i][j]);
            }
        }

    }

    @Test
    final void testGetPredecessorMatrix() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 3);
        graph.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, true, 8);
        graph.addEdge(edgeAC);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, -4);
        graph.addEdge(edgeAE);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, true, 1);
        graph.addEdge(edgeBD);
        GraphEdge<String> edgeBE = new GraphEdge<>(nodeB, nodeE, true, 7);
        graph.addEdge(edgeBE);
        GraphEdge<String> edgeCB = new GraphEdge<>(nodeC, nodeB, true, 4);
        graph.addEdge(edgeCB);
        GraphEdge<String> edgeDA = new GraphEdge<>(nodeD, nodeA, true, 2);
        graph.addEdge(edgeDA);
        GraphEdge<String> edgeDC = new GraphEdge<>(nodeD, nodeC, true, -5);
        graph.addEdge(edgeDC);
        GraphEdge<String> edgeED = new GraphEdge<>(nodeE, nodeD, true, 6);
        graph.addEdge(edgeED);
        FloydWarshallAllPairsShortestPathComputer<String> alg = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        alg.computeShortestPaths();

        int[][] toCheck = new int[5][5];
        toCheck[0][0] = -1;
        toCheck[0][1] = 2;
        toCheck[0][2] = 3;
        toCheck[0][3] = 4;
        toCheck[0][4] = 0;
        toCheck[1][0] = 3;
        toCheck[1][1] = -1;
        toCheck[1][2] = 3;
        toCheck[1][3] = 1;
        toCheck[1][4] = 0;
        toCheck[2][0] = 3;
        toCheck[2][1] = 2;
        toCheck[2][2] = -1;
        toCheck[2][3] = 1;
        toCheck[2][4] = 0;
        toCheck[3][0] = 3;
        toCheck[3][1] = 2;
        toCheck[3][2] = 3;
        toCheck[3][3] = -1;
        toCheck[3][4] = 0;
        toCheck[4][0] = 3;
        toCheck[4][1] = 2;
        toCheck[4][2] = 3;
        toCheck[4][3] = 4;
        toCheck[4][4] = -1;
        int[][] x = alg.getPredecessorMatrix();
        for(int i=0;i<toCheck.length;i++){
            for(int j=0;j<toCheck.length;j++){
                assertEquals(toCheck[i][j], x[i][j]);
            }
        }
    }
}
