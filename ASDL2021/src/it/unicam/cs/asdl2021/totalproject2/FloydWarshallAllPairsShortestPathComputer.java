package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementazione dell'algoritmo di Floyd-Warshall per il calcolo di cammini
 * minimi tra tutte le coppie di nodi in un grafo pesato che può contenere anche
 * pesi negativi, ma non cicli di peso negativo.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class FloydWarshallAllPairsShortestPathComputer<L> {

    /*
     * Il grafo su cui opera questo calcolatore.
     */
    private Graph<L> graph;

    /*
     * Matrice dei costi dei cammini minimi. L'elemento in posizione i,j
     * corrisponde al costo di un cammino minimo tra il nodo i e il nodo j, dove
     * i e j sono gli interi associati ai nodi nel grafo (si richiede quindi che
     * la classe che implementa il grafo supporti le operazioni con indici).
     */
    private double[][] costMatrix;

    /*
     * Matrice dei predecessori. L'elemento in posizione i,j è -1 se non esiste
     * nessun cammino tra i e j oppure corrisponde all'indice di un nodo che
     * precede il nodo j in un qualche cammino minimo da i a j. Si intende che i
     * e j sono gli indici associati ai nodi nel grafo (si richiede quindi che
     * la classe che implementa il grafo supporti le operazioni con indici).
     */
    private int[][] predecessorMatrix;

    private boolean computed;

    /**
     * Crea un calcolatore di cammini minimi fra tutte le coppie di nodi per un
     * grafo orientato e pesato. Non esegue il calcolo, che viene eseguito
     * invocando successivamente il metodo computeShortestPaths().
     *
     * @param graph il grafo su cui opera il calcolatore di cammini minimi
     * @throws NullPointerException     se il grafo passato è nullo
     * @throws IllegalArgumentException se il grafo passato è vuoto
     * @throws IllegalArgumentException se il grafo passato non è orientato
     * @throws IllegalArgumentException se il grafo passato non è pesato,
     *                                  cioè esiste almeno un arco il cui
     *                                  peso è {@code Double.NaN}
     */
    public FloydWarshallAllPairsShortestPathComputer(Graph<L> g) {
        // In caso di parametro null
        if (g == null) {
            throw new NullPointerException("Parametro null!");
        }
        // Grafo vuoto
        if (g.isEmpty()) {
            throw new IllegalArgumentException("Grafo vuoto!");
        }
        // Grafo non orientato
        if (!g.isDirected()) {
            throw new IllegalArgumentException("Grafo non orientato!");
        }
        // Grafo contenente archi non pesati
        for (GraphEdge<L> edge : g.getEdges()) {
            if (!edge.hasWeight()) {
                throw new IllegalArgumentException("Arco non pesato!");
            }
        }

        // Inizializzazione variabili
        this.graph = g;
        this.costMatrix = new double[g.getNodes().size()][g.getNodes().size()];
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix.length; j++) {
                // Se l'arco è diverso da null
                if (graph.getEdgeAtNodeIndexes(i, j) != null) {
                    // Assegno alla cella il suo peso
                    costMatrix[i][j] = graph.getEdgeAtNodeIndexes(i, j).getWeight();
                } else if (i == j) {
                    // Se l'arco ha come node1 == node2
                    costMatrix[i][j] = 0;
                } else {
                    // Per le celle restanti che non appartengono al grafo, assegno il valore infinito
                    costMatrix[i][j] = Double.MAX_VALUE;
                }
            }
        }
        this.predecessorMatrix = new int[g.getNodes().size()][g.getNodes().size()];
        for (int i = 0; i < predecessorMatrix.length; i++) {
            for (int j = 0; j < predecessorMatrix.length; j++) {
                // Se l'arco è diverso da null
                if (graph.getEdgeAtNodeIndexes(i, j) != null) {
                    // Inserisco nella cella l'indice del nodo precedente
                    predecessorMatrix[i][j] = i;
                } else {
                    // Altrimenti
                    predecessorMatrix[i][j] = -1;
                }
            }
        }
        this.computed = false;
    }

    /**
     * Esegue il calcolo per la matrice dei costi dei cammini minimi e per la
     * matrice dei predecessori così come specificato dall'algoritmo di
     * Floyd-Warshall.
     *
     * @throws IllegalStateException se il calcolo non può essere effettuato
     *                               per via dei valori dei pesi del grafo,
     *                               ad esempio se il grafo contiene cicli
     *                               di peso negativo.
     */
    public void computeShortestPaths() {
        // Implementazione Floyd-Warshall
        for (int k = 0; k < costMatrix.length; k++) {
            for (int i = 0; i < costMatrix.length; i++) {
                for (int j = 0; j < costMatrix.length; j++) {
                    // Se la distanza attuale è maggiore rispetto alla nuova
                    if (costMatrix[i][j] > costMatrix[i][k] + costMatrix[k][j]) {
                        // Aggiorno il valore della distanza
                        costMatrix[i][j] = costMatrix[i][k] + costMatrix[k][j];
                        // Aggiorno anche l'indice del nodo precedente
                        predecessorMatrix[i][j] = predecessorMatrix[k][j];
                    }
                    // In caso di ciclo negativo
                    if (costMatrix[k][k] < 0) {
                        throw new IllegalStateException("Ciclo negativo!");
                    }
                }
            }
        }

        computed = true;
    }

    /**
     * Determina se è stata invocatala procedura di calcolo dei cammini minimi.
     *
     * @return true se i cammini minimi sono stati calcolati, false altrimenti
     */
    public boolean isComputed() {
        return computed;
    }

    /**
     * Restituisce il grafo su cui opera questo calcolatore.
     *
     * @return il grafo su cui opera questo calcolatore
     */
    public Graph<L> getGraph() {
        return this.graph;
    }

    /**
     * Restituisce una lista di archi da un nodo sorgente a un nodo target. Tale
     * lista corrisponde a un cammino minimo tra i due nodi nel grafo gestito da
     * questo calcolatore.
     *
     * @param sourceNode il nodo di partenza del cammino minimo da
     *                   restituire
     * @param targetNode il nodo di arrivo del cammino minimo da restituire
     * @return la lista di archi corrispondente al cammino minimo; la lista è
     * vuota se il nodo sorgente è il nodo target. Viene restituito
     * {@code null} se il nodo target non è raggiungibile dal nodo
     * sorgente
     * @throws NullPointerException     se almeno uno dei nodi passati è
     *                                  nullo
     * @throws IllegalArgumentException se almeno uno dei nodi passati non
     *                                  esiste
     * @throws IllegalStateException    se non è stato eseguito il calcolo
     *                                  dei cammini minimi
     */
    public List<GraphEdge<L>> getShortestPath(GraphNode<L> sourceNode, GraphNode<L> targetNode) {
        if (sourceNode == null || targetNode == null) {
            throw new NullPointerException("Parametro null!");
        }
        if (!graph.containsNode(sourceNode) || !graph.containsNode(targetNode)) {
            throw new IllegalArgumentException("Nodi non appartenenti al grafo!");
        }
        if (!isComputed()) {
            throw new IllegalStateException("Grafo non computato");
        }
        if(sourceNode==targetNode){
            return null;
        }
        // Indice nodo partenza
        int i = graph.getNodeIndexOf(sourceNode.getLabel());
        // Indice nodo destinazione
        int j = graph.getNodeIndexOf(targetNode.getLabel());
        // Indice del nodo precedente
        int target = predecessorMatrix[i][j];
        // Insieme degli archi che formano il percorso
        List<GraphEdge<L>> toReturn = new ArrayList<>();
        // Il previous del targetNode
        GraphNode<L> toCheck = graph.getNodeAtIndex(target);
        // Finché non arrivo al nodo di partenza
        while(!toCheck.equals(sourceNode)){
            // Aggiungo l'arco all'insieme degli archi
            toReturn.add(graph.getEdge(toCheck, targetNode));
            // Aggiorno l'indice del nodo precedente
            target = predecessorMatrix[i][graph.getNodeIndexOf(toCheck.getLabel())];
            // Scorro il nodo, andando indietro
            targetNode = toCheck;
            // Aggiorno con il nuovo previous
            toCheck = graph.getNodeAtIndex(target);
        }
        // Aggiungo l'ultimo arco che collega il sourceNode al nodo successivo
        toReturn.add(graph.getEdge(sourceNode, targetNode));
        // Faccio il reverse, in modo da avere l'array di archi in ordine
        Collections.reverse(toReturn);
        return toReturn;
    }

    /**
     * Restituisce il costo di un cammino minimo da un nodo sorgente a un nodo
     * target.
     *
     * @param sourceNode il nodo di partenza del cammino minimo
     * @param targetNode il nodo di arrivo del cammino minimo
     * @return il coso di un cammino minimo tra il nodo sorgente e il nodo
     * target. Viene restituito {@code Double.POSITIVE_INFINITY} se il
     * nodo target non è raggiungibile dal nodo sorgente, mentre viene
     * restituito zero se il nodo sorgente è il nodo target.
     * @throws NullPointerException     se almeno uno dei nodi passati è
     *                                  nullo
     * @throws IllegalArgumentException se almeno uno dei nodi passati non
     *                                  esiste
     * @throws IllegalStateException    se non è stato eseguito il calcolo
     *                                  dei cammini minimi
     */
    public double getShortestPathCost(GraphNode<L> sourceNode, GraphNode<L> targetNode) {
        if (sourceNode == null || targetNode == null) {
            throw new NullPointerException("Parametro null!");
        }
        if (!graph.containsNode(sourceNode) || !graph.containsNode(targetNode)) {
            throw new IllegalArgumentException("Nodi non appartenenti al grafo!");
        }
        if (!isComputed()) {
            throw new IllegalStateException("Grafo non computato");
        }

        int i = graph.getNodeIndexOf(sourceNode.getLabel());
        int j = graph.getNodeIndexOf(targetNode.getLabel());

        return costMatrix[i][j];
    }

    /**
     * Genera una stringa di descrizione di un path riportando i nodi
     * attraversati e i pesi degli archi. Nel caso di cammino vuoto genera solo
     * la stringa {@code "[ ]"}.
     *
     * @param path un cammino minimo
     * @return una stringa di descrizione del cammino minimo
     * @throws NullPointerException se il cammino passato è nullo
     */
    public String printPath(List<GraphEdge<L>> path) {
        if (path == null)
            throw new NullPointerException(
                    "Richiesta di stampare un path nullo");
        if (path.isEmpty())
            return "[ ]";
        // Costruisco la stringa
        StringBuffer s = new StringBuffer();
        s.append("[ " + path.get(0).getNode1().toString());
        for (int i = 0; i < path.size(); i++)
            s.append(" -- " + path.get(i).getWeight() + " --> "
                    + path.get(i).getNode2().toString());
        s.append(" ]");
        return s.toString();
    }

    /**
     * @return the costMatrix
     */
    public double[][] getCostMatrix() {
        return costMatrix;
    }

    /**
     * @return the predecessorMatrix
     */
    public int[][] getPredecessorMatrix() {
        return predecessorMatrix;
    }

    // TODO inserire eventuali metodi privati per fini di implementazione

}
