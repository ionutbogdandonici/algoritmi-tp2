package it.unicam.cs.asdl2021.totalproject2;

import java.util.*;

/**
 * Implementazione dell'algoritmo di Bellman-Ford per il calcolo di cammini
 * minimi a sorgente singola in un grafo pesato che può contenere anche pesi
 * negativi, ma non cicli di peso negativo.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class BellmanFordShortestPathComputer<L> implements SingleSourceShortestPathComputer<L> {

    private Graph<L> graph;
    private boolean computed;
    private GraphNode<L> lastSource = null;

    /**
     * Crea un calcolatore di cammini minimi a sorgente singola per un grafo
     * orientato e pesato.
     *
     * @param graph il grafo su cui opera il calcolatore di cammini minimi
     * @throws NullPointerException     se il grafo passato è nullo
     * @throws IllegalArgumentException se il grafo passato è vuoto
     * @throws IllegalArgumentException se il grafo passato non è diretto
     * @throws IllegalArgumentException se il grafo passato non è pesato,
     *                                  cioè esiste almeno un arco il cui
     *                                  peso è {@code Double.NaN}.
     */
    public BellmanFordShortestPathComputer(Graph<L> graph) {
        // In caso di parametro null
        if (graph == null) {
            throw new NullPointerException("Parametro null!");
        }
        // Grafo vuoto
        if (graph.isEmpty()) {
            throw new IllegalArgumentException("Grafo vuoto!");
        }
        // Grafo non diretto
        if (!graph.isDirected()) {
            throw new IllegalArgumentException("Grafo non diretto!");
        }
        // Archi non pesati
        for (GraphEdge<L> edge : graph.getEdges()) {
            if (!edge.hasWeight()) {
                throw new IllegalArgumentException("Grafo non pesato!");
            }
        }
        this.graph = graph;
        this.computed = false;
        this.lastSource = null;
    }

    @Override
    public void computeShortestPathsFrom(GraphNode<L> sourceNode) {
        // In caso di parametro null
        if (sourceNode == null) {
            throw new NullPointerException("Parametro null");
        }
        // Nodo non appartenente al grafo
        if (!graph.containsNode(sourceNode)) {
            throw new IllegalArgumentException("Il nodo non esiste nel grafo!");
        }

        initSingleSource(sourceNode);
        for (GraphNode<L> node : graph.getNodes()) {
            for (GraphEdge<L> edge : graph.getEdges()) {
                relax(edge);
            }
        }
        // Controllo che non ci siano cicli negativi
        for (GraphEdge<L> edge : graph.getEdges()) {
            if (edge.getNode2().getFloatingPointDistance() > edge.getNode1().getFloatingPointDistance() + edge.getWeight()) {
                throw new IllegalStateException("Presenza ciclo negativo");
            }
        }

        this.lastSource = sourceNode;
        this.computed = true;
    }

    @Override
    public boolean isComputed() {
        return this.computed;
    }

    @Override
    public GraphNode<L> getLastSource() {
        if (!isComputed()) {
            throw new IllegalStateException("Grafo non computato!");
        }
        return this.lastSource;
    }

    @Override
    public Graph<L> getGraph() {
        return this.graph;
    }

    @Override
    public List<GraphEdge<L>> getShortestPathTo(GraphNode<L> targetNode) {
        // In caso di parametro null
        if (targetNode == null) {
            throw new NullPointerException("Parametro null");
        }
        // Nodo non appartiene al grafo
        if (!graph.containsNode(targetNode)) {
            throw new IllegalArgumentException("Il nodo non esiste!");
        }
        // Se il grafo non è stato computato
        if (!isComputed()) {
            throw new IllegalStateException("Grafo non computato");
        }

        List<GraphEdge<L>> toReturn = new ArrayList<>();
        // Se il nodo in riferimento ha un nodo precedente
        if (targetNode.getPrevious() != null) {
            // Finché ha un precedente
            while (targetNode.getPrevious() != null) {
                // Aggiungo l'arco che lo collega al precedente
                toReturn.add(graph.getEdge(targetNode.getPrevious(), targetNode));
                // Il targetNode diventa il suo previous e continuo finché non ne ha più
                targetNode = targetNode.getPrevious();
            }
        }
        /*
         * Essendo che sono partito dal target e sono andato indietro fino al nodo di partenza,
         * faccio il reverse dell'array in modo da avere gli archi in ordine
         */
        Collections.reverse(toReturn);
        return toReturn;
    }

    /*
     * La seguente procedura inizializza le stime dei cammini minimi e i predecessori
     */
    private void initSingleSource(GraphNode<L> s) {
        if (s == null) {
            throw new NullPointerException("Parametro null!");
        }
        for (GraphNode<L> node : graph.getNodes()) {
            if (!node.equals(s)) {
                node.setFloatingPointDistance(Double.MAX_VALUE);
                node.setPrevious(null);
            } else {
                node.setFloatingPointDistance(0);
            }
        }
    }

    /*
     * Processo di rilassamento di un'arco
     */
    private void relax(GraphEdge<L> toRelax) {
        if (toRelax == null) {
            throw new NullPointerException("Parametro null!");
        }
        if (toRelax.getNode2().getFloatingPointDistance() > toRelax.getNode1().getFloatingPointDistance() + toRelax.getWeight()) {
            toRelax.getNode2().setFloatingPointDistance(toRelax.getNode1().getFloatingPointDistance() + toRelax.getWeight());
            toRelax.getNode2().setPrevious(toRelax.getNode1());
        }
    }
}
