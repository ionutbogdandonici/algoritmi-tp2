package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Template: Luca Tesei
 *
 */
class DijkstraShortestPathComputerTest {

    /*
     * Per quanto riguarda questa serie di test vi sono degli errori:
     * Non posso andare a fare il getShortestPathTo() di un nodo del tipo nxTest,
     * questo perché essendo un nodo creato dopo l'operazione computeShortestPathsFrom()
     * non avrà mai un Previous
     */
    @Test
    final void testGetShortestPathTo1() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<>(ns, nu, true, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<>(ns, nx, true, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<>(nu, nx, true, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<>(nx, nu, true, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<>(nx, ny, true, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<>(ny, ns, true, 7.03);

        g.addEdge(eys);
        GraphNode<String> nv = new GraphNode<>("v");
        g.addNode(nv);
        GraphEdge<String> euv = new GraphEdge<>(nu, nv, true, 1.0);
        g.addEdge(euv);
        GraphEdge<String> exv = new GraphEdge<>(nx, nv, true, 9.05);
        g.addEdge(exv);
        GraphEdge<String> eyv = new GraphEdge<>(ny, nv, true, 6.0);
        g.addEdge(eyv);
        GraphEdge<String> evy = new GraphEdge<>(nv, ny, true, 4.07);
        g.addEdge(evy);
        DijkstraShortestPathComputer<String> c = new DijkstraShortestPathComputer<String>(g);
        GraphNode<String> nsTest = new GraphNode<String>("s");
        c.computeShortestPathsFrom(nsTest);

        List<GraphEdge<String>> pathTest = new ArrayList<GraphEdge<String>>();
        assertTrue(c.getShortestPathTo(nsTest).equals(pathTest));

        // GraphNode<String> nuTest = new GraphNode<>("u");
        // GraphNode<String> nxTest = new GraphNode<>("x");
        // Sostituito nsTest con ns e nxTest con nx
        GraphEdge<String> esxTest = new GraphEdge<>(ns, nx, true, 5.12);
        pathTest.add(esxTest);

        // Come parametro a getShortestPathTo ho sostituito nxTest con nx
        assertTrue(c.getShortestPathTo(nx).equals(pathTest));

        GraphEdge<String> exuTest = new GraphEdge<>(nx, nu, true,
                3.04);
        pathTest.add(exuTest);
        assertTrue(c.getShortestPathTo(nu).equals(pathTest));
        // GraphNode<String> nvTest = new GraphNode<>("v");
        GraphEdge<String> euvTest = new GraphEdge<>(nu, nv, true,
                1.0);
        pathTest.add(euvTest);
        assertTrue(c.getShortestPathTo(nv).equals(pathTest));
        pathTest.clear();
        pathTest.add(esxTest);
        // GraphNode<String> nyTest = new GraphNode<>("y");
        GraphEdge<String> exyTest = new GraphEdge<>(nx, ny, true,
                2.0);
        pathTest.add(exyTest);
        assertTrue(c.getShortestPathTo(ny).equals(pathTest));
    }

    @Test
    final void testGetShortestPathTo2() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, true, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, true, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, true, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, true, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, true, 7.03);

        g.addEdge(eys);
        GraphNode<String> nv = new GraphNode<String>("v");
        g.addNode(nv);
        GraphEdge<String> euv = new GraphEdge<String>(nu, nv, true, 1.0);
        g.addEdge(euv);
        GraphEdge<String> exv = new GraphEdge<String>(nx, nv, true, 9.05);
        g.addEdge(exv);
        GraphEdge<String> eyv = new GraphEdge<String>(ny, nv, true, 6.0);
        g.addEdge(eyv);
        GraphEdge<String> evy = new GraphEdge<String>(nv, ny, true, 4.07);
        g.addEdge(evy);
        GraphNode<String> np = new GraphNode<String>("p");
        g.addNode(np);
        GraphEdge<String> epv = new GraphEdge<String>(np, nv, true, 1.0);
        g.addEdge(epv);
        // p è collegato a v, ma non è raggiungibile da s
        DijkstraShortestPathComputer<String> c = new DijkstraShortestPathComputer<String>(
                g);
        GraphNode<String> nsTest = new GraphNode<String>("s");
        c.computeShortestPathsFrom(nsTest);
        // GraphNode<String> npTest = new GraphNode<String>("p");
        assertTrue(c.getShortestPathTo(np) == null);
    }

    @Test
    public void exceptionsTest(){
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        // Check delle Exceptions
            // Parametro null
        assertThrows(NullPointerException.class, ()->{
            BellmanFordShortestPathComputer alg = new BellmanFordShortestPathComputer(null);
        });
            // Grafo vuoto
        assertThrows(IllegalArgumentException.class, ()->{
            BellmanFordShortestPathComputer alg = new BellmanFordShortestPathComputer(graph);
        });
            // Grafo non orientato
        assertThrows(IllegalArgumentException.class, ()->{
            Graph<String> notOriented = new MapAdjacentListUndirectedGraph<>();
            BellmanFordShortestPathComputer alg = new BellmanFordShortestPathComputer(notOriented);
        });
            // Grafo contenente pesi Double.NaN
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        assertThrows(IllegalArgumentException.class, ()->{
            GraphEdge<String> notWeighted = new GraphEdge<>(nodeA, nodeB, true);
            graph.addEdge(notWeighted);
            BellmanFordShortestPathComputer alg = new BellmanFordShortestPathComputer(graph);
        });
            // Grafo contenente pesi negativi
        assertThrows(IllegalArgumentException.class, ()->{
            GraphEdge<String> negativeEdge = new GraphEdge<>(nodeA, nodeB, true, -2);
            graph.addEdge(negativeEdge);
            BellmanFordShortestPathComputer alg = new BellmanFordShortestPathComputer(graph);
        });
    }

    @Test
    public void testIsComputed(){
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<>(ns, nu, true, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<>(ns, nx, true, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<>(nu, nx, true, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<>(nx, nu, true, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<>(nx, ny, true, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<>(ny, ns, true, 7.03);
        g.addEdge(eys);
        GraphNode<String> nv = new GraphNode<>("v");
        g.addNode(nv);
        GraphEdge<String> euv = new GraphEdge<>(nu, nv, true, 1.0);
        g.addEdge(euv);
        GraphEdge<String> exv = new GraphEdge<>(nx, nv, true, 9.05);
        g.addEdge(exv);
        GraphEdge<String> eyv = new GraphEdge<>(ny, nv, true, 6.0);
        g.addEdge(eyv);
        GraphEdge<String> evy = new GraphEdge<>(nv, ny, true, 4.07);
        g.addEdge(evy);
        DijkstraShortestPathComputer<String> c = new DijkstraShortestPathComputer<String>(g);
        assertFalse(c.isComputed());
        c.computeShortestPathsFrom(ns);
        assertTrue(c.isComputed());
    }

    @Test
    public void testLastSource(){
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<>(ns, nu, true, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<>(ns, nx, true, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<>(nu, nx, true, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<>(nx, nu, true, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<>(nx, ny, true, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<>(ny, ns, true, 7.03);

        g.addEdge(eys);
        GraphNode<String> nv = new GraphNode<>("v");
        g.addNode(nv);
        GraphEdge<String> euv = new GraphEdge<>(nu, nv, true, 1.0);
        g.addEdge(euv);
        GraphEdge<String> exv = new GraphEdge<>(nx, nv, true, 9.05);
        g.addEdge(exv);
        GraphEdge<String> eyv = new GraphEdge<>(ny, nv, true, 6.0);
        g.addEdge(eyv);
        GraphEdge<String> evy = new GraphEdge<>(nv, ny, true, 4.07);
        g.addEdge(evy);
        DijkstraShortestPathComputer<String> c = new DijkstraShortestPathComputer<String>(g);

        assertThrows(IllegalStateException.class, ()->{
            c.getLastSource();
        });
        c.computeShortestPathsFrom(ns);
        assertEquals(ns, c.getLastSource());
        c.computeShortestPathsFrom(nx);
        assertEquals(nx, c.getLastSource());
    }
}
