package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Template: Luca Tesei
 */
class AdjacencyMatrixDirectedGraphTest {

    /*
     * Per l'esecuzione dei test farò uso del grafico orientato presentato nella
     * relazione.
     */

    @Test
    final void testNodeCount() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");
        GraphNode<String> nodeC = new GraphNode<>("C");
        GraphNode<String> nodeD = new GraphNode<>("D");
        GraphNode<String> nodeE = new GraphNode<>("E");
        GraphNode<String> nodeF = new GraphNode<>("F");
        GraphNode<String> nodeG = new GraphNode<>("G");

        // Inserimento dei nodi nel grafo
        matrix.addNode(nodeA);
        matrix.addNode(nodeB);
        matrix.addNode(nodeC);
        matrix.addNode(nodeD);
        matrix.addNode(nodeE);
        matrix.addNode(nodeF);
        matrix.addNode(nodeG);

        // Mi aspetto che il numero dei nodi totali sia 7
        assertEquals(7, matrix.nodeCount());
        // Provo a reinserire un nodo già esistente, per esempio il nodo A
        matrix.addNode(nodeA);
        // Il nodo non verrà aggiunto, perciò il numero rimane invariato
        assertEquals(7, matrix.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);

        // Mi aspetto che gli archi inseriti siano 0
        assertEquals(0, matrix.edgeCount());

        // Aggiunta degli archi
        assertTrue(matrix.addEdge(edgeAB));
        assertTrue(matrix.addEdge(edgeAE));
        assertTrue(matrix.addEdge(edgeBC));
        assertTrue(matrix.addEdge(edgeCC));
        assertTrue(matrix.addEdge(edgeCD));
        assertTrue(matrix.addEdge(edgeCF));
        assertTrue(matrix.addEdge(edgeDF));
        assertTrue(matrix.addEdge(edgeEC));
        assertTrue(matrix.addEdge(edgeEF));
        assertTrue(matrix.addEdge(edgeEG));

        // Mi aspetto che gli archi inseriti siano 10
        assertEquals(10, matrix.edgeCount());

        // Provo a rimuovere un edge
        assertTrue(matrix.removeEdge(edgeAB));

        // Mi aspetto che gli archi inseriti ora siano 9
        assertEquals(9, matrix.edgeCount());
    }

    @Test
    final void testClear() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);

        // Aggiunta degli archi
        assertTrue(matrix.addEdge(edgeAB));
        assertTrue(matrix.addEdge(edgeAE));
        assertTrue(matrix.addEdge(edgeBC));
        assertTrue(matrix.addEdge(edgeCC));
        assertTrue(matrix.addEdge(edgeCD));
        assertTrue(matrix.addEdge(edgeCF));
        assertTrue(matrix.addEdge(edgeDF));
        assertTrue(matrix.addEdge(edgeEC));
        assertTrue(matrix.addEdge(edgeEF));
        assertTrue(matrix.addEdge(edgeEG));

        // Controllo che il numero dei nodi e archi sia quello che mi aspetto
        // N. Nodes: 7
        // N. Edges: 10
        assertEquals(7, matrix.nodeCount());
        assertEquals(10, matrix.edgeCount());

        // Effettuo la cancellazione del grafico
        matrix.clear();

        // Mi aspetto che il numero di nodi archi sia pari a 0
        assertEquals(0, matrix.nodeCount());
        assertEquals(0, matrix.edgeCount());
    }

    @Test
    final void testIsDirected() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();
        assertTrue(matrix.isDirected());
    }

    @Test
    final void testGetNodes() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");
        GraphNode<String> nodeC = new GraphNode<>("C");
        GraphNode<String> nodeD = new GraphNode<>("D");
        GraphNode<String> nodeE = new GraphNode<>("E");
        GraphNode<String> nodeF = new GraphNode<>("F");
        GraphNode<String> nodeG = new GraphNode<>("G");

        // Inserimento dei nodi nel grafo
        matrix.addNode(nodeA);
        matrix.addNode(nodeB);
        matrix.addNode(nodeC);
        matrix.addNode(nodeD);
        matrix.addNode(nodeE);
        matrix.addNode(nodeF);
        matrix.addNode(nodeG);

        Set<GraphNode<String>> toCheck = new HashSet<>();
        toCheck.add(nodeA);
        toCheck.add(nodeB);
        toCheck.add(nodeC);
        toCheck.add(nodeD);
        toCheck.add(nodeE);
        toCheck.add(nodeF);
        toCheck.add(nodeG);

        assertEquals(toCheck, matrix.getNodes());

    }

    @Test
    final void testAddNode() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");
        GraphNode<String> nodeC = new GraphNode<>("C");
        GraphNode<String> nodeD = new GraphNode<>("D");
        GraphNode<String> nodeE = new GraphNode<>("E");
        GraphNode<String> nodeF = new GraphNode<>("F");
        GraphNode<String> nodeG = new GraphNode<>("G");

        // Check delle Exceptions
        assertThrows(NullPointerException.class, () -> matrix.addNode(null));

        // Inserimento dei nodi nel grafo
        assertTrue(matrix.addNode(nodeA));
        // Reinserimento di un nodo già presente
        assertFalse(matrix.addNode(nodeA));
        assertTrue(matrix.addNode(nodeB));
        assertTrue(matrix.addNode(nodeC));
        assertTrue(matrix.addNode(nodeD));
        assertTrue(matrix.addNode(nodeE));
        assertTrue(matrix.addNode(nodeF));
        assertTrue(matrix.addNode(nodeG));
    }

    /*
     * Essendo un'operazione non supportata non ne eseguo il test
     */
    @Disabled
    final void testRemoveNode() {
        fail("Not yet implemented");
    }

    @Test
    final void testContainsNode() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);
        GraphNode<String> nodeOUT = new GraphNode<>("OUT");

        // Mi aspetto che siano contenuti visto che sono stati aggiunti
        assertTrue(matrix.containsNode(nodeA));
        assertTrue(matrix.containsNode(nodeB));
        assertTrue(matrix.containsNode(nodeC));
        assertTrue(matrix.containsNode(nodeD));
        assertTrue(matrix.containsNode(nodeE));
        assertTrue(matrix.containsNode(nodeF));
        assertTrue(matrix.containsNode(nodeG));

        // Mi aspetto un false visto che non è stato aggiunto
        assertFalse(matrix.containsNode(nodeOUT));
    }

    @Test
    final void testGetNodeOf() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, () -> matrix.getNodeOf(null));

        // Mi aspetto che ritornino i rispettivi nodi
        assertEquals(nodeA, matrix.getNodeOf("A"));
        assertEquals(nodeB, matrix.getNodeOf("B"));
        assertEquals(nodeC, matrix.getNodeOf("C"));
        assertEquals(nodeD, matrix.getNodeOf("D"));
        assertEquals(nodeE, matrix.getNodeOf("E"));
        assertEquals(nodeF, matrix.getNodeOf("F"));
        assertEquals(nodeG, matrix.getNodeOf("G"));

        // Vedo cosa succede in caso di label non presente nell'insieme
        assertNull(matrix.getNodeOf("Z"));
    }

    @Test
    final void testGetNodeIndexOf() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, () -> matrix.getNodeIndexOf(null));
        assertThrows(IllegalArgumentException.class, () -> matrix.getNodeIndexOf("a"));

        // Controllo sugli indici
        assertEquals(0, matrix.getNodeIndexOf("A"));
        assertEquals(1, matrix.getNodeIndexOf("B"));
        assertEquals(2, matrix.getNodeIndexOf("C"));
        assertEquals(3, matrix.getNodeIndexOf("D"));
        assertEquals(4, matrix.getNodeIndexOf("E"));
        assertEquals(5, matrix.getNodeIndexOf("F"));
        assertEquals(6, matrix.getNodeIndexOf("G"));
    }

    @Test
    final void testGetNodeAtIndex() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Check delle Exceptions
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.getNodeAtIndex(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.getNodeAtIndex(7));

        assertEquals(nodeA, matrix.getNodeAtIndex(0));
        assertEquals(nodeB, matrix.getNodeAtIndex(1));
        assertEquals(nodeC, matrix.getNodeAtIndex(2));
        assertEquals(nodeD, matrix.getNodeAtIndex(3));
        assertEquals(nodeE, matrix.getNodeAtIndex(4));
        assertEquals(nodeF, matrix.getNodeAtIndex(5));
        assertEquals(nodeG, matrix.getNodeAtIndex(6));
    }

    @Test
    final void testGetEdge() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, () -> matrix.getEdge(null, nodeE));
        assertThrows(NullPointerException.class, () -> matrix.getEdge(nodeA, null));
        assertThrows(NullPointerException.class, () -> matrix.getEdge(null, null));
        // Nodi non aggiunti al grafo
        GraphNode<String> nodeX = new GraphNode<>("X");
        GraphNode<String> nodeY = new GraphNode<>("Y");
        assertThrows(IllegalArgumentException.class, () -> matrix.getEdge(nodeA, nodeX));
        assertThrows(IllegalArgumentException.class, () -> matrix.getEdge(nodeX, nodeA));
        assertThrows(IllegalArgumentException.class, () -> matrix.getEdge(nodeX, nodeY));

        // Nodi appartenenti al grafo ma non collegati
        assertNull(matrix.getEdge(nodeA, nodeG));

        assertEquals(edgeAB, matrix.getEdge(nodeA, nodeB));
        assertEquals(edgeAE, matrix.getEdge(nodeA, nodeE));
        assertEquals(edgeBC, matrix.getEdge(nodeB, nodeC));
        assertEquals(edgeCC, matrix.getEdge(nodeC, nodeC));
        assertEquals(edgeCD, matrix.getEdge(nodeC, nodeD));
        assertEquals(edgeCF, matrix.getEdge(nodeC, nodeF));
        assertEquals(edgeDF, matrix.getEdge(nodeD, nodeF));
        assertEquals(edgeEC, matrix.getEdge(nodeE, nodeC));
        assertEquals(edgeEF, matrix.getEdge(nodeE, nodeF));
        assertEquals(edgeEG, matrix.getEdge(nodeE, nodeG));
    }

    @Test
    final void testGetEdgeAtNodeIndexes() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        // Check delle Exceptions
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.getEdgeAtNodeIndexes(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.getEdgeAtNodeIndexes(1, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.getEdgeAtNodeIndexes(-1, -1));

        // Asserzioni
        assertEquals(edgeAB, matrix.getEdgeAtNodeIndexes(0, 1));
        assertEquals(edgeAE, matrix.getEdgeAtNodeIndexes(0, 4));
        assertEquals(edgeBC, matrix.getEdgeAtNodeIndexes(1, 2));
        assertEquals(edgeCC, matrix.getEdgeAtNodeIndexes(2, 2));
        assertEquals(edgeCD, matrix.getEdgeAtNodeIndexes(2, 3));
        assertEquals(edgeCF, matrix.getEdgeAtNodeIndexes(2, 5));
        assertEquals(edgeDF, matrix.getEdgeAtNodeIndexes(3, 5));
        assertEquals(edgeEC, matrix.getEdgeAtNodeIndexes(4, 2));
        assertEquals(edgeEF, matrix.getEdgeAtNodeIndexes(4, 5));
        assertEquals(edgeEG, matrix.getEdgeAtNodeIndexes(4, 6));
    }

    @Test
    final void testGetAdjacentNodesOf() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, () -> matrix.getAdjacentNodesOf(null));

        GraphNode<String> nodeX = new GraphNode<>("X");
        assertThrows(IllegalArgumentException.class, () -> matrix.getAdjacentNodesOf(nodeX));

        Set<GraphNode<String>> toCheck = new HashSet<>();
        toCheck.add(nodeC);
        toCheck.add(nodeD);
        toCheck.add(nodeF);

        assertEquals(toCheck, matrix.getAdjacentNodesOf(nodeC));
    }

    @Test
    final void testGetPredecessorNodesOf() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, () -> matrix.getPredecessorNodesOf(null));

        GraphNode<String> nodeX = new GraphNode<>("X");
        assertThrows(IllegalArgumentException.class, () -> matrix.getPredecessorNodesOf(nodeX));

        Set<GraphNode<String>> toCheck = new HashSet<>();
        toCheck.add(nodeB);
        toCheck.add(nodeC);
        toCheck.add(nodeE);

        assertEquals(toCheck, matrix.getPredecessorNodesOf(nodeC));
    }

    @Test
    final void testGetEdges() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        Set<GraphEdge<String>> toCheck = new HashSet<>();
        assertEquals(toCheck, matrix.getEdges());

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        toCheck.add(edgeAB);
        toCheck.add(edgeAE);
        toCheck.add(edgeBC);
        toCheck.add(edgeCC);
        toCheck.add(edgeCD);
        toCheck.add(edgeCD);
        toCheck.add(edgeCF);
        toCheck.add(edgeDF);
        toCheck.add(edgeEC);
        toCheck.add(edgeEF);
        toCheck.add(edgeEG);

        assertEquals(toCheck, matrix.getEdges());
    }

    @Test
    final void testAddEdge() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, () -> matrix.addEdge(null));
        GraphEdge<String> notDirected = new GraphEdge<>(nodeA, nodeB, false, 1);
        assertThrows(IllegalArgumentException.class, () -> matrix.addEdge(notDirected));

        // Nodi non contenuti
        GraphNode<String> nodeX = new GraphNode<>("X");
        GraphNode<String> nodeY = new GraphNode<>("Y");
        GraphEdge<String> edgeAX = new GraphEdge<>(nodeA, nodeX, true, 0);
        GraphEdge<String> edgeXA = new GraphEdge<>(nodeX, nodeA, true, 0);
        GraphEdge<String> edgeXY = new GraphEdge<>(nodeX, nodeY, true, 0);
        assertThrows(IllegalArgumentException.class, () -> matrix.addEdge(edgeAX));
        assertThrows(IllegalArgumentException.class, () -> matrix.addEdge(edgeXA));
        assertThrows(IllegalArgumentException.class, () -> matrix.addEdge(edgeXY));

        assertTrue(matrix.addEdge(edgeAB));
        assertFalse(matrix.addEdge(edgeAB));
        assertTrue(matrix.addEdge(edgeAE));
        assertTrue(matrix.addEdge(edgeBC));
        assertTrue(matrix.addEdge(edgeCC));
        assertTrue(matrix.addEdge(edgeCD));
        assertTrue(matrix.addEdge(edgeCF));
        assertTrue(matrix.addEdge(edgeDF));
        assertTrue(matrix.addEdge(edgeEC));
        assertTrue(matrix.addEdge(edgeEF));
        assertTrue(matrix.addEdge(edgeEG));
    }

    @Test
    final void testRemoveEdge() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, () -> matrix.removeEdge(null));
        GraphNode<String> nodeX = new GraphNode<>("X");
        GraphNode<String> nodeY = new GraphNode<>("Y");
        GraphEdge<String> edgeAX = new GraphEdge<>(nodeA, nodeX, true, 0);
        GraphEdge<String> edgeXA = new GraphEdge<>(nodeX, nodeA, true, 0);
        GraphEdge<String> edgeXY = new GraphEdge<>(nodeX, nodeY, true, 0);

        assertThrows(IllegalArgumentException.class, ()-> matrix.removeEdge(edgeAX));
        assertThrows(IllegalArgumentException.class, ()-> matrix.removeEdge(edgeXA));
        assertThrows(IllegalArgumentException.class, ()-> matrix.removeEdge(edgeXY));

        assertTrue(matrix.removeEdge(edgeAB));
        assertFalse(matrix.removeEdge(edgeAB));
        assertTrue(matrix.removeEdge(edgeAE));
        assertTrue(matrix.removeEdge(edgeBC));
        assertTrue(matrix.removeEdge(edgeCC));
        assertTrue(matrix.removeEdge(edgeCD));
        assertTrue(matrix.removeEdge(edgeCF));
        assertTrue(matrix.removeEdge(edgeDF));
        assertTrue(matrix.removeEdge(edgeEC));
        assertTrue(matrix.removeEdge(edgeEF));
        assertTrue(matrix.removeEdge(edgeEG));
    }

    @Test
    final void testContainsEdge() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> matrix.containsEdge(null));

        GraphNode<String> nodeX = new GraphNode<>("X");
        GraphNode<String> nodeY = new GraphNode<>("Y");
        GraphEdge<String> edgeAX = new GraphEdge<>(nodeA, nodeX, true, 0);
        GraphEdge<String> edgeXA = new GraphEdge<>(nodeX, nodeA, true, 0);
        GraphEdge<String> edgeXY = new GraphEdge<>(nodeX, nodeY, true, 0);

        assertThrows(IllegalArgumentException.class, ()-> matrix.containsEdge(edgeAX));
        assertThrows(IllegalArgumentException.class, ()-> matrix.containsEdge(edgeXA));
        assertThrows(IllegalArgumentException.class, ()-> matrix.containsEdge(edgeXY));

        assertTrue(matrix.containsEdge(edgeAB));
        matrix.removeEdge(edgeAB);
        assertFalse(matrix.containsEdge(edgeAB));
        assertTrue(matrix.containsEdge(edgeAE));
        assertTrue(matrix.containsEdge(edgeBC));
        assertTrue(matrix.containsEdge(edgeCC));
        assertTrue(matrix.containsEdge(edgeCD));
        assertTrue(matrix.containsEdge(edgeCF));
        assertTrue(matrix.containsEdge(edgeDF));
        assertTrue(matrix.containsEdge(edgeEC));
        assertTrue(matrix.containsEdge(edgeEF));
        assertTrue(matrix.containsEdge(edgeEG));

    }

    @Test
    final void testGetEdgesOf() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> matrix.getEdgesOf(null));
        GraphNode<String> nodeX = new GraphNode<>("X");
        assertThrows(IllegalArgumentException.class, ()-> matrix.getEdgesOf(nodeX));

        Set<GraphEdge<String>> toCheck = new HashSet<>();
        toCheck.add(edgeEC);
        toCheck.add(edgeEF);
        toCheck.add(edgeEG);
        assertEquals(toCheck, matrix.getEdgesOf(nodeE));
    }

    @Test
    final void testGetIngoingEdgesOf() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> matrix.getIngoingEdgesOf(null));
        GraphNode<String> nodeX = new GraphNode<>("X");
        assertThrows(IllegalArgumentException.class, ()-> matrix.getIngoingEdgesOf(nodeX));

        Set<GraphEdge<String>> toCheck = new HashSet<>();
        toCheck.add(edgeBC);
        toCheck.add(edgeCC);
        toCheck.add(edgeEC);

        assertEquals(toCheck, matrix.getIngoingEdgesOf(nodeC));
    }

    @Test
    final void testAdjacencyMatrixDirectedGraph() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();
        assertEquals(0, matrix.nodeCount());
        assertEquals(0, matrix.edgeCount());
    }

    @Test
    final void testSize() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        assertEquals(0, matrix.size());

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        /*
         * Numero nodi: 7
         * Numero archi: 10
         * Size: 17
         */

        assertEquals(17, matrix.size());
        matrix.removeEdge(edgeAB);
        assertEquals(16, matrix.size());
    }

    @Test
    final void testIsEmpty() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();
        assertTrue(matrix.isEmpty());

        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 1);
        matrix.addEdge(edgeAB);
        assertFalse(matrix.isEmpty());
        matrix.clear();
        assertTrue(matrix.isEmpty());
    }

    @Test
    final void testGetDegreeOf() {
        AdjacencyMatrixDirectedGraph<String> matrix = new AdjacencyMatrixDirectedGraph<>();

        // Creazione e aggiunta dei nodi
        GraphNode<String> nodeA = new GraphNode<>("A");
        matrix.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        matrix.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        matrix.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        matrix.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        matrix.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        matrix.addNode(nodeF);
        GraphNode<String> nodeG = new GraphNode<>("G");
        matrix.addNode(nodeG);

        // Creazione degli archi
        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, true, 8);
        matrix.addEdge(edgeAB);
        GraphEdge<String> edgeAE = new GraphEdge<>(nodeA, nodeE, true, 6);
        matrix.addEdge(edgeAE);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, true, 2);
        matrix.addEdge(edgeBC);
        GraphEdge<String> edgeCC = new GraphEdge<>(nodeC, nodeC, true, 1);
        matrix.addEdge(edgeCC);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, true, 2);
        matrix.addEdge(edgeCD);
        GraphEdge<String> edgeCF = new GraphEdge<>(nodeC, nodeF, true, 7);
        matrix.addEdge(edgeCF);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, true, 5);
        matrix.addEdge(edgeDF);
        GraphEdge<String> edgeEC = new GraphEdge<>(nodeE, nodeC, true, 3);
        matrix.addEdge(edgeEC);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, true, 9);
        matrix.addEdge(edgeEF);
        GraphEdge<String> edgeEG = new GraphEdge<>(nodeE, nodeG, true, 3);
        matrix.addEdge(edgeEG);

        // Somma del numero di archi in entrata e del numero di archi in uscita.
        assertEquals(2, matrix.getDegreeOf(nodeA));
        assertEquals(2, matrix.getDegreeOf(nodeB));
        assertEquals(6, matrix.getDegreeOf(nodeC));
        assertEquals(2, matrix.getDegreeOf(nodeD));
        assertEquals(4, matrix.getDegreeOf(nodeE));
        assertEquals(3, matrix.getDegreeOf(nodeF));
        assertEquals(1, matrix.getDegreeOf(nodeG));
    }

}
