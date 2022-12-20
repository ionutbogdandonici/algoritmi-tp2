package it.unicam.cs.asdl2021.totalproject2;

import java.util.*;

/**
 * Gli oggetti di questa classe sono calcolatori di cammini minimi con sorgente
 * singola su un certo grafo orientato e pesato dato. Il grafo su cui lavorare
 * deve essere passato quando l'oggetto calcolatore viene costruito e non può
 * contenere archi con pesi negativi. Il calcolatore implementa il classico
 * algoritmo di Dijkstra per i cammini minimi con sorgente singola utilizzando
 * una coda con priorità che estrae l'elemento con priorità minima e aggiorna le
 * priorità con l'operazione decreasePriority in tempo logaritmico (coda
 * realizzata con uno heap binario). In questo caso il tempo di esecuzione
 * dell'algoritmo di Dijkstra è {@code O(n log m)} dove {@code n} è il numero di
 * nodi del grafo e {@code m} è il numero di archi.
 *
 * @param <L> il tipo delle etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class DijkstraShortestPathComputer<L> implements SingleSourceShortestPathComputer<L> {

    private Graph<L> graph;
    private boolean computed;
    private GraphNode<L> lastSource;
    private BinaryHeapMinPriorityQueue queue;
    private Set<GraphNode<L>> computedNodes;

    /**
     * Crea un calcolatore di cammini minimi a sorgente singola per un grafo
     * diretto e pesato privo di pesi negativi.
     *
     * @param graph il grafo su cui opera il calcolatore di cammini minimi
     * @throws NullPointerException     se il grafo passato è nullo
     * @throws IllegalArgumentException se il grafo passato è vuoto
     * @throws IllegalArgumentException se il grafo passato non è orientato
     * @throws IllegalArgumentException se il grafo passato non è pesato,
     *                                  cioè esiste almeno un arco il cui
     *                                  peso è {@code Double.NaN}
     * @throws IllegalArgumentException se il grafo passato contiene almeno
     *                                  un peso negativo
     */
    public DijkstraShortestPathComputer(Graph<L> graph) {
        // In caso di parametro null
        if (graph == null) {
            throw new NullPointerException("Parametro null!");
        }
        // Grafo vuoto
        if (graph.isEmpty()) {
            throw new IllegalArgumentException("Grafo vuoto!");
        }
        // Grafo non orientato
        if (!graph.isDirected()) {
            throw new IllegalArgumentException("Grafo non orientato!");
        }
        // Presenza arco non pesato
        for (GraphEdge<L> edge : graph.getEdges()) {
            if (!edge.hasWeight()) {
                throw new IllegalArgumentException("Grafo non pesato");
            }
        }
        // Presenza arco negativo
        for (GraphEdge<L> edge : graph.getEdges()) {
            if (edge.getWeight() < 0) {
                throw new IllegalArgumentException("Grafo negativo!");
            }
        }

        this.graph = graph;
        this.computed = false;
        this.lastSource = null;
        this.queue = new BinaryHeapMinPriorityQueue();
        this.computedNodes = new HashSet<>();
    }

    @Override
    public void computeShortestPathsFrom(GraphNode<L> sourceNode) {
        // In caso di parametro null
        if(sourceNode == null){
            throw new NullPointerException("Parametro null!");
        }
        // Non non appartenente al grafo
        if(!graph.containsNode(sourceNode)){
            throw new IllegalArgumentException("Il nodo non esiste nel grafo");
        }
        // IllegalStateException non necessario

        // Inizializzo i nodi
        initSingleSource(sourceNode);
        // Creazione queue dopo che ho il sourceNode
        createQueue();
        while(!queue.isEmpty()){
            GraphNode<L> node = (GraphNode<L>) queue.extractMinimum();
            computedNodes.add(node);
            for(GraphNode<L> adjNode : graph.getAdjacentNodesOf(node)){
                relax(graph.getEdge(node, adjNode));
            }
        }

        this.computed = true;
        this.lastSource = sourceNode;
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
        if(targetNode == null){
            throw new NullPointerException("Parametro null!");
        }
        // Nodo non appartenente al grafo
        if(!graph.containsNode(targetNode)){
            throw new IllegalArgumentException("Il nodo non esiste!");
        }
        // Se il grafo non è stato computato
        if(!isComputed()){
            throw new IllegalStateException("Grafo non computato!");
        }
        // Se il nodo non può essere raggiunto dalla sorgente e non è la sorgente
        if(targetNode!=getLastSource()&&targetNode.getPrevious()==null){
            return null;
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
     * Metodo che crea la queue per l'algoritmo di Dijkstra
     */

    private void createQueue(){
        for(GraphNode<L> node : graph.getNodes()){
            queue.insert(node);
        }
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
