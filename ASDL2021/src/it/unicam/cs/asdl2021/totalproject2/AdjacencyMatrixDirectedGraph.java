package it.unicam.cs.asdl2021.totalproject2;

import java.util.*;

/**
 * Classe che implementa un grafo orientato tramite matrice di adiacenza. Non
 * sono accettate etichette dei nodi null e non sono accettate etichette
 * duplicate nei nodi (che in quel caso sono lo stesso nodo).
 * <p>
 * I nodi sono indicizzati da 0 a nodeCoount() - 1 seguendo l'ordine del loro
 * inserimento (0 è l'indice del primo nodo inserito, 1 del secondo e così via)
 * e quindi in ogni istante la matrice di adiacenza ha dimensione nodeCount() *
 * nodeCount(). La matrice, sempre quadrata, deve quindi aumentare di dimensione
 * ad ogni inserimento di un nodo. Per questo non è rappresentata tramite array
 * ma tramite ArrayList.
 * <p>
 * Gli oggetti GraphNode<L>, cioè i nodi, sono memorizzati in una mappa che
 * associa ad ogni nodo l'indice assegnato in fase di inserimento. Il dominio
 * della mappa rappresenta quindi l'insieme dei nodi.
 * <p>
 * Gli archi sono memorizzati nella matrice di adiacenza. A differenza della
 * rappresentazione standard con matrice di adiacenza, la posizione i,j della
 * matrice non contiene un flag di presenza, ma è null se i nodi i e j non sono
 * collegati da un arco orientato e contiene un oggetto della classe
 * GraphEdge<L> se lo sono. Tale oggetto rappresenta l'arco.
 * <p>
 * Questa classe non supporta la cancellazione di nodi, ma supporta la
 * cancellazione di archi e tutti i metodi che usano indici, utilizzando
 * l'indice assegnato a ogni nodo in fase di inserimento.
 *
 * @author Template: Luca Tesei
 */
public class AdjacencyMatrixDirectedGraph<L> extends Graph<L> {
    /*
     * Le seguenti variabili istanza sono protected al solo scopo di agevolare
     * il JUnit testing
     */

    // Insieme dei nodi e associazione di ogni nodo con il proprio indice nella
    // matrice di adiacenza
    protected Map<GraphNode<L>, Integer> nodesIndex;

    // Matrice di adiacenza, gli elementi sono null o oggetti della classe
    // GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
    // dimensione gradualmente ad ogni inserimento di un nuovo nodo.
    protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */

    /**
     * Crea un grafo vuoto.
     */
    public AdjacencyMatrixDirectedGraph() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
    }

    @Override
    public int nodeCount() {
        /*
         * Il numero dei nodi è dato dal numero di elementi presenti nell'insieme
         * nodesIndex, ma questo può essere ricavato anche dalla grandezza della matrice
         */
        return nodesIndex.size();
    }

    @Override
    public int edgeCount() {
        /*
         * Il numero di archi è dato dal numero di elementi !null
         * all'interno della matrix
         */

        // Contatore per i nodi
        int count = 0;
        for (ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> graphEdge : graphEdges) {
                // Se l'elemento che sto scorrendo è diverso da null
                if (graphEdge != null) {
                    // Incremento il numero degli archi contati
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public void clear() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
    }

    @Override
    public boolean isDirected() {
        // Questa classe implementa un grafo orientato
        return true;
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        /*
         * Creo un nuovo insieme di tipo HashSet() dove vado ad inserire tutti
         * i nodi presenti all'interno dell'inseieme nodesIndex
         */
        Set<GraphNode<L>> toReturn = new HashSet<>();
        for (Map.Entry<GraphNode<L>, Integer> entry : nodesIndex.entrySet()) {
            toReturn.add(entry.getKey());
        }

        return toReturn;
    }

    @Override
    public boolean addNode(GraphNode<L> node) {
        /*
         * Per aggiungere un nodo al grafo, mi devo innanzitutto accertare che
         * questo non sia già presente all'interno dell'insieme indexNodes.
         * Se questo non è presente, procedo con l'inserimento del nodo
         * nell'insieme indexNodes. Una volta nell'insieme devo aggiornare
         * la grandezza della matrice, incrementando le righe e le colonne.
         * I valori dei nuovi campi sono impostati a null
         */

        // Controllo che il parametro non sia null
        if (node == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Controllo che il nodo non sia già stato inserito
        if (nodesIndex.containsKey(node)) {
            return false;
        }

        /*
         * Comando base per l'inserimento del nodo nell'insieme dei nodi
         * nodesIndex. node rappresenta la chiave, matrix.size() rappresenta
         * invece il valore associato al nodo nella matrice
         */

        nodesIndex.put(node, matrix.size());

        // Incremento della matrice
        matrix.add(new ArrayList<>());
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = matrix.size() - 1; j < matrix.size(); j++) {
                matrix.get(i).add(null);
                // Se arrivo all'ultima riga della matrice
                if (i == matrix.size() - 1) {
                    for (int k = 1; k < matrix.size(); k++) {
                        matrix.get(i).add(null);
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Remove di nodi non supportata");
    }

    @Override
    public boolean containsNode(GraphNode<L> node) {
        /*
         * Metodo che va a controllare se nell'insieme nodesIndex vi è
         * presente il nodo passato
         */

        // In caso di parametro null
        if (node == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Ritorno true se presente, false altrimenti
        return nodesIndex.containsKey(node);
    }

    @Override
    public GraphNode<L> getNodeOf(L label) {
        /*
         * Metodo che ritorna il nodo abbinato ad una certa label.
         * Ogni nodo ha la sua label, unica.
         */

        // In caso di parametro null
        if (label == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Ricerca del nodo con la node.getLabel == label
        for (Map.Entry<GraphNode<L>, Integer> entry : nodesIndex.entrySet()) {
            if (entry.getKey().getLabel().equals(label)) {
                return entry.getKey();
            }
        }

        // Se non è stato trovato
        return null;
    }

    @Override
    public int getNodeIndexOf(L label) {
        /*
         * Restituisce un indice unico associato attualmente ad un certo nodo
         */

        // In caso di parametro null
        if (label == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Ricerca dell'indice associato al node[label]
        for (Map.Entry<GraphNode<L>, Integer> entry : nodesIndex.entrySet()) {
            if (entry.getKey().getLabel().equals(label)) {
                return entry.getValue();
            }
        }

        // Se il nodo non esiste
        throw new IllegalArgumentException("Nodo non presente!");
    }

    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        /*
         * Se l'indice passato non corrisponde a nessun nodo o è fuori
         * dai limiti dell'intervallo
         */
        if (i < 0 || i >= nodesIndex.size()) {
            throw new IndexOutOfBoundsException("Parametro out-of-range!");
        }

        // Ricerca del nodo all'indice i
        for (Map.Entry<GraphNode<L>, Integer> entry : nodesIndex.entrySet()) {
            if (entry.getValue().equals(i)) {
                return entry.getKey();
            }
        }

        // Se il nodo non è stato trovato
        return null;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        /*
         * Restituisce l'insieme di tutti i nodi adiacenti ad un certo nodo.
         */

        // In caso di parametro null
        if (node == null) {
            throw new NullPointerException("Parametro null!");
        }
        // Il nodo non esiste nell'insieme
        if (!containsNode(node)) {
            throw new IllegalArgumentException("Il nodo non esiste!");
        }

        // Insieme dei nodi adiacenti
        Set<GraphNode<L>> toReturn = new HashSet<>();

        for (ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> graphEdge : graphEdges) {
                if (graphEdge != null) {
                    if (graphEdge.getNode1().equals(node)) {
                        toReturn.add(graphEdge.getNode2());
                    }
                }
            }
        }

        return toReturn;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        /*
         * Restituisce l'insieme di tutti i nodi collegati tramiute un arco
         * entrante in un certo nodo in un grafo orientato
         */

        // In caso di parametro null
        if (node == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Il nodo non esiste nell'insieme
        if (!containsNode(node)) {
            throw new IllegalArgumentException("Il nodo non esiste!");
        }

        // Insieme dei nodi predecessori
        Set<GraphNode<L>> toReturn = new HashSet<>();

        for (ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> graphEdge : graphEdges) {
                if (graphEdge != null) {
                    if (graphEdge.getNode2().equals(node)) {
                        toReturn.add(graphEdge.getNode1());
                    }
                }
            }
        }

        return toReturn;
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        /*
         * Restituisce l'insieme degli archi
         */

        // Insieme degli archi da restituire
        Set<GraphEdge<L>> toReturn = new HashSet<>();

        for (ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> graphEdge : graphEdges) {
                // Se ho trovato un elemento diverso da null, ovvero un arco
                if (graphEdge != null) {
                    toReturn.add(graphEdge);
                }
            }
        }

        return toReturn;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        /*
         * Aggiunge un arco a questo grafo
         */

        // In caso di parametro null
        if (edge == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Arco non orientato
        if (!edge.isDirected()) {
            throw new IllegalArgumentException("Arco non orientato!");
        }

        // Controllo che i nodi appartengano all'insieme
        if ((!containsNode(edge.getNode1())) && (!containsNode(edge.getNode2()))) {
            throw new IllegalArgumentException("Nodi non validi!");
        }

        // Controllo che l'arco non sia stato già inserito
        for (ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> graphEdge : graphEdges) {
                /*
                 * Mi assicuro che sia != null altrimenti rischio un
                 * NullPointerException
                 */
                if(graphEdge!=null){
                    if (graphEdge.equals(edge)) {
                        return false;
                    }
                }

            }
        }

        // Ricavo gli indici dei nodi
        int indexRow = getNodeIndexOf(edge.getNode1().getLabel());
        int indexCol = getNodeIndexOf(edge.getNode2().getLabel());

        // Inserimento nella matrice
        matrix.get(indexRow).set(indexCol, edge);

        return true;
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        /*
         * Rimuove un arco da questo grafo
         */

        // In caso di parametro null
        if (edge == null) {
            throw new NullPointerException("Parametro null");
        }

        // Se almeno uno dei due nodi specificati nell'arco non esiste
        if (!(containsNode(edge.getNode1())) || !(containsNode(edge.getNode2()))) {
            throw new IllegalArgumentException("Nodi non validi");
        }

        for (ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (int y = 0; y < graphEdges.size(); y++) {
                /*
                 * Mi assicuro che sia != null altrimenti rischio un
                 * NullPointerException
                 */
                if(graphEdges.get(y)!=null){
                    if (graphEdges.get(y).equals(edge)) {
                        graphEdges.set(y, null);
                        return true;
                    }
                }

            }
        }

        return false;
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        /*
         * Verifica la presenza di un certo arco nel grafo
         */

        // In caso di parametro null
        if (edge == null) {
            throw new NullPointerException("Parametro null");
        }

        // Se almeno uno dei due nodi specificati nell'arco non esiste
        if (!(containsNode(edge.getNode1())) || !(containsNode(edge.getNode2()))) {
            throw new IllegalArgumentException("Nodi non validi");
        }

        for (ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> graphEdge : graphEdges) {
                if(graphEdge!=null){
                    if (graphEdge.equals(edge)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        /*
         * Vengono restituiti tutti gli archi uscenti dal nodo passato
         */

        // In caso di parametro null
        if (node == null) {
            throw new NullPointerException("Parametro null");
        }

        // Il nodo non esiste
        if (!containsNode(node)) {
            throw new IllegalArgumentException("Nodo inesistente");
        }

        // Insieme degli archi uscenti
        Set<GraphEdge<L>> toReturn = new HashSet<>();

        for (ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> graphEdge : graphEdges) {
                if(graphEdge!=null){
                    // Se Node1 uguale a node
                    if (graphEdge.getNode1().equals(node)) {
                        toReturn.add(graphEdge);
                    }
                }
            }
        }
        return toReturn;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        /*
         * Restituisce l'insieme di tutti gli archi entranti in un certo nodo
         */

        // In caso di parametro null
        if (node == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Il nodo non esiste
        if (!containsNode(node)) {
            throw new IllegalArgumentException("Nodo inesistente!");
        }

        // Insieme degli archi entranti
        Set<GraphEdge<L>> toReturn = new HashSet<>();

        for (ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> graphEdge : graphEdges) {
                if(graphEdge!=null){
                    // Se l'arco entrante da Node2 uguale a node
                    if (graphEdge.getNode2().equals(node)) {
                        toReturn.add(graphEdge);
                    }
                }
            }
        }

        return toReturn;
    }

    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) {
        /*
         * Restituisce se esiste, l'arco che connette i due nodi passati
         */

        // In caso di parametro null
        if (node1 == null || node2 == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Almeno un nodo nodo non esiste
        if (!containsNode(node1) || !containsNode(node2)) {
            throw new IllegalArgumentException("Nodo inesistente!");
        }

        for (ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> graphEdge : graphEdges) {
                if (graphEdge != null) {
                    if (graphEdge.getNode1().equals(node1) && graphEdge.getNode2().equals(node2)) {
                        return graphEdge;
                    }
                }
            }
        }

        // I nodi esistono ma non formano un arco
        return null;
    }


    @Override
    public GraphEdge<L> getEdgeAtNodeIndexes(int i, int j) {
        /*
         * Restituisce se esiste, l'arco che connette due nodi a indici
         * specificati
         */

        // Out-of-range
        if ((i < 0 || i >= matrix.size()) || (j < 0 || j >= matrix.size())) {
            throw new IndexOutOfBoundsException("Out-of-range!");
        }

        // Ricavo i nodi agli indici i e j
        GraphNode<L> node1 = getNodeAtIndex(i);
        GraphNode<L> node2 = getNodeAtIndex(j);

        return getEdge(node1, node2);
    }
}
