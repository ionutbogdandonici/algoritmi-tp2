package it.unicam.cs.asdl2021.totalproject2;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe singoletto che implementa l'algoritmo di Prim per trovare un Minimum
 * Spanning Tree di un grafo non orientato, pesato e con pesi non negativi.
 * <p>
 * L'algoritmo usa una coda di min priorità tra i nodi implementata dalla classe
 * TernaryHeapMinPriorityQueue. I nodi vengono visti come PriorityQueueElement
 * poiché la classe GraphNode<L> implementa questa interfaccia. Si noti che
 * nell'esecuzione dell'algoritmo è necessario utilizzare l'operazione di
 * decreasePriority.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class PrimMSP<L> {

    /*
     * Coda di priorità che va usata dall'algoritmo. La variabile istanza è
     * protected solo per scopi di testing JUnit.
     */
    protected BinaryHeapMinPriorityQueue queue;

    /**
     * Crea un nuovo algoritmo e inizializza la coda di priorità con una coda
     * vuota.
     */
    public PrimMSP() {
        this.queue = new BinaryHeapMinPriorityQueue();
    }

    /**
     * Utilizza l'algoritmo goloso di Prim per trovare un albero di copertura
     * minimo in un grafo non orientato e pesato, con pesi degli archi non negativi.
     * Dopo l'esecuzione del metodo nei nodi del grafo il campo previous deve
     * contenere un puntatore a un nodo in accordo all'albero di copertura
     * minimo calcolato, la cui radice è il nodo sorgente passato.
     *
     * @param g un grafo non orientato, pesato, con pesi non negativi
     * @param s il nodo del grafo g sorgente, cioè da cui parte il calcolo
     *          dell'albero di copertura minimo. Tale nodo sarà la radice
     *          dell'albero di copertura trovato
     * @throw NullPointerException se il grafo g o il nodo sorgente s sono nulli
     * @throw IllegalArgumentException se il nodo sorgente s non esiste in g
     * @throw IllegalArgumentException se il grafo g è orientato, non pesato o
     * con pesi negativi
     */
    public void computeMSP(Graph<L> g, GraphNode<L> s) {
        // In caso di parametro null
        if ((g == null) || (s == null)) {
            throw new NullPointerException("Parametri null!");
        }
        // Il nodo s non esiste in g
        if (!g.containsNode(s)) {
            throw new IllegalArgumentException("Non non esistente!");
        }
        // Se g è orientato
        if (g.isDirected()) {
            throw new IllegalArgumentException("Grafo orientato!");
        }
        // Se g non è pesato o ha pesi negativi
        Set<GraphEdge<L>> toCheckWeight = new HashSet<>(g.getEdges());
        for (GraphEdge<L> edge : toCheckWeight) {
            if ((!edge.hasWeight()) || (edge.getWeight() < 0)) {
                throw new IllegalArgumentException("Arco non pesato o negativo!");
            }
        }
        // Impostazione di tutti i nodi a WHITE tranne s
        for (GraphNode<L> node : g.getNodes()) {
            if (!node.equals(s)) {
                node.setColor(0);
                node.setFloatingPointDistance(Double.MAX_VALUE);
                node.setPrevious(null);
            }
        }

        // Inizializzazione del nodo passato
        s.setColor(1);
        s.setFloatingPointDistance(0);
        s.setPrevious(null);
        // Aggiungo il nodo di partenza alla queue
        Enqueue(s);

        // Finché non è vuota la coda
        while (!queue.isEmpty()) {
            // Estrago il nodo con peso minimo
            GraphNode<L> previous = Dequeue();
            // Per ogni suo adiacente
            for (GraphNode<L> node : g.getAdjacentNodesOf(previous)) {
                // Se è un nodo di quale sappiamo già la sua esistenza
                if (node.getColor() == 1) {
                    // Se il suo peso attuale è maggiore rispetto a quello nuovo
                    if (node.getPriority() > g.getEdge(previous, node).getWeight()) {
                        // Aggiorno la sua priorità
                        node.setPriority(g.getEdge(previous, node).getWeight());
                        // Reimposto il suo precedente
                        node.setPrevious(previous);
                        // Aggiorno i dati all'interno dello heap
                        try {
                            queue.decreasePriority(node, node.getPriority());
                        } catch (IllegalArgumentException ignored) {
                        }
                    }
                }

                // Se non è stato visitato
                if (node.getColor() == 0) {
                    node.setColor(1);
                    node.setPriority(g.getEdge(previous, node).getWeight());
                    node.setPrevious(previous);
                    // Lo aggiungo alla queue
                    Enqueue(node);
                }
            }
            // Una volta finito con i suoi nodi adiacenti lo rendo nero
            previous.setColor(2);
        }
    }

    private void Enqueue(GraphNode<L> node) {
        queue.insert(node);
    }

    private GraphNode<L> Dequeue() {
        if (queue.extractMinimum() instanceof GraphNode) {
            return (GraphNode<L>) queue.extractMinimum();
        }
        return null;
    }
}
