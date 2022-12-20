package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Template: Luca Tesei
 *
 */
class MapAdjacentListUndirectedGraphTest {

    /*
     * Per l'esecuzione dei test farò uso del grafico non orientato
     * presentato nella relazione.
     */

    @Test
    final void testNodeCount() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        assertEquals(0, list.nodeCount());
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        assertEquals(3, list.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        assertEquals(0, list.edgeCount());
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, false, 1);
        list.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, false, 1);
        list.addEdge(edgeAC);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, false, 1);
        list.addEdge(edgeBC);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, false, 1);
        list.addEdge(edgeBD);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, false, 1);
        list.addEdge(edgeCD);
        GraphEdge<String> edgeCE = new GraphEdge<>(nodeC, nodeE, false, 1);
        list.addEdge(edgeCE);
        GraphEdge<String> edgeDE = new GraphEdge<>(nodeD, nodeE, false, 1);
        list.addEdge(edgeDE);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, false, 1);
        list.addEdge(edgeDF);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, false, 1);
        list.addEdge(edgeEF);
        assertEquals(9, list.edgeCount());
    }

    @Test
    final void testClear() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        assertTrue(list.isEmpty());
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, false, 1);
        list.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, false, 1);
        list.addEdge(edgeAC);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, false, 1);
        list.addEdge(edgeBC);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, false, 1);
        list.addEdge(edgeBD);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, false, 1);
        list.addEdge(edgeCD);
        GraphEdge<String> edgeCE = new GraphEdge<>(nodeC, nodeE, false, 1);
        list.addEdge(edgeCE);
        GraphEdge<String> edgeDE = new GraphEdge<>(nodeD, nodeE, false, 1);
        list.addEdge(edgeDE);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, false, 1);
        list.addEdge(edgeDF);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, false, 1);
        list.addEdge(edgeEF);
        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    final void testIsDirected() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        assertFalse(list.isDirected());
    }

    @Test
    final void testGetNodes() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        Set<GraphNode<String>> toCheck = new HashSet<>();
        toCheck.add(nodeA);
        toCheck.add(nodeB);
        toCheck.add(nodeC);
        toCheck.add(nodeD);
        toCheck.add(nodeE);
        toCheck.add(nodeF);

        assertEquals(toCheck, list.getNodes());
        list.removeNode(nodeA);
        assertNotEquals(toCheck, list.getNodes());

    }

    @Test
    final void testAddNode() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        assertEquals(0, list.nodeCount());
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");
        GraphNode<String> nodeC = new GraphNode<>("C");
        GraphNode<String> nodeD = new GraphNode<>("D");
        GraphNode<String> nodeE = new GraphNode<>("E");
        GraphNode<String> nodeF = new GraphNode<>("F");

        // Check delle Exceptions
        assertThrows(NullPointerException.class, () -> list.addNode(null));

        assertTrue(list.addNode(nodeA));
        assertEquals(1, list.nodeCount());
        assertFalse(list.addNode(nodeA));
        assertEquals(1, list.nodeCount());
        assertTrue(list.addNode(nodeB));
        assertEquals(2, list.nodeCount());
        assertTrue(list.addNode(nodeC));
        assertEquals(3, list.nodeCount());
        assertTrue(list.addNode(nodeD));
        assertEquals(4, list.nodeCount());
        assertTrue(list.addNode(nodeE));
        assertEquals(5, list.nodeCount());
        assertTrue(list.addNode(nodeF));
        assertEquals(6, list.nodeCount());
    }

    @Test
    final void testRemoveNode() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);
        assertEquals(6, list.nodeCount());

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> list.removeNode(null));

        assertTrue(list.removeNode(nodeA));
        assertFalse(list.removeNode(nodeA));
        assertEquals(5, list.nodeCount());
        assertTrue(list.removeNode(nodeB));
        assertEquals(4, list.nodeCount());
        assertTrue(list.removeNode(nodeC));
        assertEquals(3, list.nodeCount());
        assertTrue(list.removeNode(nodeD));
        assertEquals(2, list.nodeCount());
        assertTrue(list.removeNode(nodeE));
        assertEquals(1, list.nodeCount());
        assertTrue(list.removeNode(nodeF));
        assertEquals(0, list.nodeCount());
    }

    @Test
    final void testContainsNode() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> list.containsNode(null));

        assertTrue(list.containsNode(nodeA));
        list.removeNode(nodeA);
        assertFalse(list.containsNode(nodeA));
        assertTrue(list.containsNode(nodeB));
        list.removeNode(nodeB);
        assertFalse(list.containsNode(nodeB));
        assertTrue(list.containsNode(nodeC));
        list.removeNode(nodeC);
        assertFalse(list.containsNode(nodeC));
        assertTrue(list.containsNode(nodeD));
        list.removeNode(nodeD);
        assertFalse(list.containsNode(nodeD));
        assertTrue(list.containsNode(nodeE));
        list.removeNode(nodeE);
        assertFalse(list.containsNode(nodeE));
        assertTrue(list.containsNode(nodeF));
        list.removeNode(nodeF);
        assertFalse(list.containsNode(nodeF));
    }

    @Test
    final void testGetNodeOf() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> list.getNodeOf(null));

        assertEquals(nodeA, list.getNodeOf("A"));
        assertEquals(nodeB, list.getNodeOf("B"));
        assertEquals(nodeC, list.getNodeOf("C"));
        assertEquals(nodeD, list.getNodeOf("D"));
        assertEquals(nodeE, list.getNodeOf("E"));
        assertEquals(nodeF, list.getNodeOf("F"));
    }
    /*
     * Essendo un'operazione non supportata non ne eseguo il test
     */
    @Disabled
    final void testGetNodeIndexOf() {
        fail("Not yet implemented");
    }

    /*
     * Essendo un'operazione non supportata non ne eseguo il test
     */
    @Disabled
    final void testGetNodeAtIndex() {
        fail("Not yet implemented");
    }

    @Test
    final void testGetEdge() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, false, 1);
        list.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, false, 1);
        list.addEdge(edgeAC);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, false, 1);
        list.addEdge(edgeBC);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, false, 1);
        list.addEdge(edgeBD);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, false, 1);
        list.addEdge(edgeCD);
        GraphEdge<String> edgeCE = new GraphEdge<>(nodeC, nodeE, false, 1);
        list.addEdge(edgeCE);
        GraphEdge<String> edgeDE = new GraphEdge<>(nodeD, nodeE, false, 1);
        list.addEdge(edgeDE);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, false, 1);
        list.addEdge(edgeDF);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, false, 1);
        list.addEdge(edgeEF);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> list.getEdge(null, nodeA));
        assertThrows(NullPointerException.class, ()-> list.getEdge(nodeA, null));
        assertThrows(NullPointerException.class, ()-> list.getEdge(null, null));
        GraphNode<String> nodeX = new GraphNode<>("X");
        GraphNode<String> nodeY = new GraphNode<>("Y");
        assertThrows(IllegalArgumentException.class, ()-> list.getEdge(nodeA, nodeX));
        assertThrows(IllegalArgumentException.class, ()-> list.getEdge(nodeX, nodeA));
        assertThrows(IllegalArgumentException.class, ()-> list.getEdge(nodeX, nodeY));

        assertEquals(edgeAB, list.getEdge(nodeA, nodeB));
        assertEquals(edgeAB, list.getEdge(nodeB, nodeA));
        assertNull(list.getEdge(nodeA, nodeF));
        assertNull(list.getEdge(nodeF, nodeA));
    }

    /*
     * Essendo un'operazione non supportata non ne eseguo il test
     */
    @Disabled
    final void testGetEdgeAtNodeIndexes() {
        fail("Not yet implemented");
    }

    @Test
    final void testGetAdjacentNodesOf() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, false, 1);
        list.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, false, 1);
        list.addEdge(edgeAC);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, false, 1);
        list.addEdge(edgeBC);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, false, 1);
        list.addEdge(edgeBD);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, false, 1);
        list.addEdge(edgeCD);
        GraphEdge<String> edgeCE = new GraphEdge<>(nodeC, nodeE, false, 1);
        list.addEdge(edgeCE);
        GraphEdge<String> edgeDE = new GraphEdge<>(nodeD, nodeE, false, 1);
        list.addEdge(edgeDE);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, false, 1);
        list.addEdge(edgeDF);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, false, 1);
        list.addEdge(edgeEF);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> list.getAdjacentNodesOf(null));
        GraphNode<String> nodeX = new GraphNode<>("X");
        assertThrows(IllegalArgumentException.class, ()-> list.getAdjacentNodesOf(nodeX));

        Set<GraphNode<String>> toCheckA = new HashSet<>();
        toCheckA.add(nodeB);
        toCheckA.add(nodeC);
        Set<GraphNode<String>> toCheckB = new HashSet<>();
        toCheckB.add(nodeA);
        toCheckB.add(nodeC);
        toCheckB.add(nodeD);
        Set<GraphNode<String>> toCheckC = new HashSet<>();
        toCheckC.add(nodeA);
        toCheckC.add(nodeB);
        toCheckC.add(nodeD);
        toCheckC.add(nodeE);
        Set<GraphNode<String>> toCheckD = new HashSet<>();
        toCheckD.add(nodeB);
        toCheckD.add(nodeC);
        toCheckD.add(nodeE);
        toCheckD.add(nodeF);
        Set<GraphNode<String>> toCheckE = new HashSet<>();
        toCheckE.add(nodeC);
        toCheckE.add(nodeD);
        toCheckE.add(nodeF);
        Set<GraphNode<String>> toCheckF = new HashSet<>();
        toCheckF.add(nodeD);
        toCheckF.add(nodeE);
        assertEquals(toCheckA, list.getAdjacentNodesOf(nodeA));
        assertEquals(toCheckB, list.getAdjacentNodesOf(nodeB));
        assertEquals(toCheckC, list.getAdjacentNodesOf(nodeC));
        assertEquals(toCheckD, list.getAdjacentNodesOf(nodeD));
        assertEquals(toCheckE, list.getAdjacentNodesOf(nodeE));
        assertEquals(toCheckF, list.getAdjacentNodesOf(nodeF));
    }

    /*
     * Essendo un'operazione non supportata non ne eseguo il test
     */
    @Disabled
    final void testGetPredecessorNodesOf() {
        fail("Not yet implemented");
    }

    @Test
    final void testGetEdges() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, false, 1);
        list.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, false, 1);
        list.addEdge(edgeAC);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, false, 1);
        list.addEdge(edgeBC);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, false, 1);
        list.addEdge(edgeBD);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, false, 1);
        list.addEdge(edgeCD);
        GraphEdge<String> edgeCE = new GraphEdge<>(nodeC, nodeE, false, 1);
        list.addEdge(edgeCE);
        GraphEdge<String> edgeDE = new GraphEdge<>(nodeD, nodeE, false, 1);
        list.addEdge(edgeDE);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, false, 1);
        list.addEdge(edgeDF);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, false, 1);
        list.addEdge(edgeEF);

        Set<GraphEdge<String>> toChcek = new HashSet<>();
        toChcek.add(edgeAB);
        toChcek.add(edgeAC);
        toChcek.add(edgeBC);
        toChcek.add(edgeBD);
        toChcek.add(edgeCE);
        toChcek.add(edgeCD);
        toChcek.add(edgeDE);
        toChcek.add(edgeDF);
        toChcek.add(edgeEF);

        assertEquals(toChcek, list.getEdges());
        list.removeEdge(edgeAB);
        assertNotEquals(toChcek, list.getEdges());
    }

    @Test
    final void testAddEdge() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, false, 1);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, false, 1);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, false, 1);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, false, 1);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, false, 1);
        GraphEdge<String> edgeCE = new GraphEdge<>(nodeC, nodeE, false, 1);
        GraphEdge<String> edgeDE = new GraphEdge<>(nodeD, nodeE, false, 1);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, false, 1);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, false, 1);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> list.addEdge(null));
        GraphNode<String> nodeX = new GraphNode<>("X");
        GraphNode<String> nodeY = new GraphNode<>("Y");
        GraphEdge<String> edgeAX = new GraphEdge<>(nodeA, nodeX, false, 1);
        GraphEdge<String> edgeXA = new GraphEdge<>(nodeX, nodeA, false, 1);
        GraphEdge<String> edgeXY = new GraphEdge<>(nodeX, nodeY, false, 1);
        assertThrows(IllegalArgumentException.class, ()-> list.addEdge(edgeAX));
        assertThrows(IllegalArgumentException.class, ()-> list.addEdge(edgeXA));
        assertThrows(IllegalArgumentException.class, ()-> list.addEdge(edgeXY));
        GraphEdge<String> oriented = new GraphEdge<>(nodeA, nodeB, true, 1);
        assertThrows(IllegalArgumentException.class, ()-> list.addEdge(oriented));

        assertEquals(0, list.edgeCount());
        assertTrue(list.addEdge(edgeAB));
        assertEquals(1, list.edgeCount());
        assertFalse(list.addEdge(edgeAB));
        assertTrue(list.addEdge(edgeAC));
        assertEquals(2, list.edgeCount());
        assertTrue(list.addEdge(edgeBC));
        assertEquals(3, list.edgeCount());
        assertTrue(list.addEdge(edgeBD));
        assertEquals(4, list.edgeCount());
        assertTrue(list.addEdge(edgeCD));
        assertEquals(5, list.edgeCount());
        assertTrue(list.addEdge(edgeCE));
        assertEquals(6, list.edgeCount());
        assertTrue(list.addEdge(edgeDE));
        assertEquals(7, list.edgeCount());
        assertTrue(list.addEdge(edgeDF));
        assertEquals(8, list.edgeCount());
        assertTrue(list.addEdge(edgeEF));
        assertEquals(9, list.edgeCount());

    }

    @Test
    final void testRemoveEdge() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, false, 1);
        list.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, false, 1);
        list.addEdge(edgeAC);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, false, 1);
        list.addEdge(edgeBC);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, false, 1);
        list.addEdge(edgeBD);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, false, 1);
        list.addEdge(edgeCD);
        GraphEdge<String> edgeCE = new GraphEdge<>(nodeC, nodeE, false, 1);
        list.addEdge(edgeCE);
        GraphEdge<String> edgeDE = new GraphEdge<>(nodeD, nodeE, false, 1);
        list.addEdge(edgeDE);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, false, 1);
        list.addEdge(edgeDF);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, false, 1);
        list.addEdge(edgeEF);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> list.removeEdge(null));
        GraphNode<String> nodeX = new GraphNode<>("X");
        GraphNode<String> nodeY = new GraphNode<>("Y");
        GraphEdge<String> edgeAX = new GraphEdge<>(nodeA, nodeX, false, 1);
        GraphEdge<String> edgeXA = new GraphEdge<>(nodeX, nodeA, false, 1);
        GraphEdge<String> edgeXY = new GraphEdge<>(nodeX, nodeY, false, 1);
        assertThrows(IllegalArgumentException.class, ()-> list.removeEdge(edgeAX));
        assertThrows(IllegalArgumentException.class, ()-> list.removeEdge(edgeXA));
        assertThrows(IllegalArgumentException.class, ()-> list.removeEdge(edgeXY));

        GraphEdge<String> notInside = new GraphEdge<>(nodeA, nodeF, false, 1);
        assertFalse(list.removeEdge(notInside));
        assertEquals(9, list.edgeCount());
        assertTrue(list.removeEdge(edgeAB));
        assertEquals(8, list.edgeCount());
        assertTrue(list.removeEdge(edgeAC));
        assertEquals(7, list.edgeCount());
        assertTrue(list.removeEdge(edgeBC));
        assertEquals(6, list.edgeCount());
        assertTrue(list.removeEdge(edgeBD));
        assertEquals(5, list.edgeCount());
        assertTrue(list.removeEdge(edgeCD));
        assertEquals(4, list.edgeCount());
        assertTrue(list.removeEdge(edgeCE));
        assertEquals(3, list.edgeCount());
        assertTrue(list.removeEdge(edgeDE));
        assertEquals(2, list.edgeCount());
        assertTrue(list.removeEdge(edgeDF));
        assertEquals(1, list.edgeCount());
        assertTrue(list.removeEdge(edgeEF));
        assertEquals(0, list.edgeCount());

    }

    @Test
    final void testContainsEdge() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, false, 1);
        list.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, false, 1);
        list.addEdge(edgeAC);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, false, 1);
        list.addEdge(edgeBC);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, false, 1);
        list.addEdge(edgeBD);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, false, 1);
        list.addEdge(edgeCD);
        GraphEdge<String> edgeCE = new GraphEdge<>(nodeC, nodeE, false, 1);
        list.addEdge(edgeCE);
        GraphEdge<String> edgeDE = new GraphEdge<>(nodeD, nodeE, false, 1);
        list.addEdge(edgeDE);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, false, 1);
        list.addEdge(edgeDF);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, false, 1);
        list.addEdge(edgeEF);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> list.containsEdge(null));
        GraphNode<String> nodeX = new GraphNode<>("X");
        GraphNode<String> nodeY = new GraphNode<>("Y");
        GraphEdge<String> edgeAX = new GraphEdge<>(nodeA, nodeX, false, 1);
        GraphEdge<String> edgeXA = new GraphEdge<>(nodeX, nodeA, false, 1);
        GraphEdge<String> edgeXY = new GraphEdge<>(nodeX, nodeY, false, 1);
        assertThrows(IllegalArgumentException.class, ()-> list.containsEdge(edgeAX));
        assertThrows(IllegalArgumentException.class, ()-> list.containsEdge(edgeXA));
        assertThrows(IllegalArgumentException.class, ()-> list.containsEdge(edgeXY));

        assertTrue(list.containsEdge(edgeAB));
        list.removeEdge(edgeAB);
        assertFalse(list.containsEdge(edgeAB));
        assertTrue(list.containsEdge(edgeAC));
        list.removeEdge(edgeAC);
        assertFalse(list.containsEdge(edgeAC));
        assertTrue(list.containsEdge(edgeBC));
        list.removeEdge(edgeBC);
        assertFalse(list.containsEdge(edgeBC));
        assertTrue(list.containsEdge(edgeBD));
        list.removeEdge(edgeBD);
        assertFalse(list.containsEdge(edgeBD));
        assertTrue(list.containsEdge(edgeCD));
        list.removeEdge(edgeCD);
        assertFalse(list.containsEdge(edgeCD));
        assertTrue(list.containsEdge(edgeCE));
        list.removeEdge(edgeCE);
        assertFalse(list.containsEdge(edgeCE));
        assertTrue(list.containsEdge(edgeDE));
        list.removeEdge(edgeDE);
        assertFalse(list.containsEdge(edgeDE));
        assertTrue(list.containsEdge(edgeDF));
        list.removeEdge(edgeDF);
        assertFalse(list.containsEdge(edgeDF));
        assertTrue(list.containsEdge(edgeEF));
        list.removeEdge(edgeEF);
        assertFalse(list.containsEdge(edgeEF));
    }

    @Test
    final void testGetEdgesOf() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        list.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        list.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        list.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        list.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        list.addNode(nodeE);
        GraphNode<String> nodeF = new GraphNode<>("F");
        list.addNode(nodeF);

        GraphEdge<String> edgeAB = new GraphEdge<>(nodeA, nodeB, false, 1);
        list.addEdge(edgeAB);
        GraphEdge<String> edgeAC = new GraphEdge<>(nodeA, nodeC, false, 1);
        list.addEdge(edgeAC);
        GraphEdge<String> edgeBC = new GraphEdge<>(nodeB, nodeC, false, 1);
        list.addEdge(edgeBC);
        GraphEdge<String> edgeBD = new GraphEdge<>(nodeB, nodeD, false, 1);
        list.addEdge(edgeBD);
        GraphEdge<String> edgeCD = new GraphEdge<>(nodeC, nodeD, false, 1);
        list.addEdge(edgeCD);
        GraphEdge<String> edgeCE = new GraphEdge<>(nodeC, nodeE, false, 1);
        list.addEdge(edgeCE);
        GraphEdge<String> edgeDE = new GraphEdge<>(nodeD, nodeE, false, 1);
        list.addEdge(edgeDE);
        GraphEdge<String> edgeDF = new GraphEdge<>(nodeD, nodeF, false, 1);
        list.addEdge(edgeDF);
        GraphEdge<String> edgeEF = new GraphEdge<>(nodeE, nodeF, false, 1);
        list.addEdge(edgeEF);

        // Check delle Exceptions
        assertThrows(NullPointerException.class, ()-> list.getEdgesOf(null));
        GraphNode<String> notInside = new GraphNode<>("o");
        assertThrows(IllegalArgumentException.class, ()-> list.getEdgesOf(notInside));

        Set<GraphEdge<String>> toCheck = new HashSet<>();
        toCheck.add(edgeAC);
        toCheck.add(edgeCE);
        toCheck.add(edgeBC);
        toCheck.add(edgeCD);

        assertEquals(toCheck, list.getEdgesOf(nodeC));
        list.removeNode(nodeA);
        assertNotEquals(toCheck, list.getEdgesOf(nodeC));
    }

    /*
     * Essendo un'operazione non supportata non ne eseguo il test
     */
    @Disabled
    final void testGetIngoingEdgesOf() {
        fail("Not yet implemented");
    }

    @Test
    final void testMapAdjacentListUndirectedGraph() {
        MapAdjacentListUndirectedGraph<String> list = new MapAdjacentListUndirectedGraph<>();
        assertEquals(0, list.nodeCount());
        assertEquals(0, list.edgeCount());
    }

}
