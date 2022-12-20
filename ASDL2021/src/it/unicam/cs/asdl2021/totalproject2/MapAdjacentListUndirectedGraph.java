package it.unicam.cs.asdl2021.totalproject2;

import java.util.*;

/**
 * Implementazione della classe astratta {@code Graph<L>} che realizza un grafo
 * non orientato. Non sono accettate etichette dei nodi null e non sono
 * accettate etichette duplicate nei nodi (che in quel caso sono lo stesso
 * nodo).
 *
 * Per la rappresentazione viene usata una variante della rappresentazione con
 * liste di adiacenza. A differenza della rappresentazione standard si usano
 * strutture dati più efficienti per quanto riguarda la complessità in tempo
 * della ricerca se un nodo è presente (pseudocostante, con tabella hash) e se
 * un arco è presente (pseudocostante, con tabella hash). Lo spazio occupato per
 * la rappresentazione risulta tuttavia più grande di quello che servirebbe con
 * la rappresentazione standard.
 *
 * Le liste di adiacenza sono rappresentate con una mappa (implementata con
 * tabelle hash) che associa ad ogni nodo del grafo i nodi adiacenti. In questo
 * modo il dominio delle chiavi della mappa è l'insieme dei nodi, su cui è
 * possibile chiamare il metodo contains per testare la presenza o meno di un
 * nodo. Ad ogni chiave della mappa, cioè ad ogni nodo del grafo, non è
 * associata una lista concatenata dei nodi collegati, ma un set di oggetti
 * della classe GraphEdge<L> che rappresentano gli archi collegati al nodo: in
 * questo modo la rappresentazione riesce a contenere anche l'eventuale peso
 * dell'arco (memorizzato nell'oggetto della classe GraphEdge<L>). Per
 * controllare se un arco è presente basta richiamare il metodo contains in
 * questo set. I test di presenza si basano sui metodi equals ridefiniti per
 * nodi e archi nelle classi GraphNode<L> e GraphEdge<L>.
 *
 * Questa classe non supporta le operazioni indicizzate di ricerca di nodi e
 * archi.
 *
 * @author Template: Luca Tesei
 *
 * @param <L>
 *                etichette dei nodi del grafo
 */
public class MapAdjacentListUndirectedGraph<L> extends Graph<L> {

    /*
     * Le liste di adiacenza sono rappresentate con una mappa. Ogni nodo viene
     * associato con l'insieme degli archi collegati. Nel caso in cui un nodo
     * non abbia archi collegati è associato con un insieme vuoto. La variabile
     * istanza è protected solo per scopi di test JUnit.
     */
    protected final Map<GraphNode<L>, Set<GraphEdge<L>>> adjacentLists;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */

    /**
     * Crea un grafo vuoto.
     */
    public MapAdjacentListUndirectedGraph() {
        // Inizializza la mappa con la mappa vuota
        this.adjacentLists = new HashMap<GraphNode<L>, Set<GraphEdge<L>>>();
    }

    @Override
    public int nodeCount() {
        /*
         * Restituisce il numero dei nodi del grafo
         * Questo numero è dato dalla grandezza di adjacentLists
         */
        return adjacentLists.size();
    }

    @Override
    public int edgeCount() {
        /*
         * Restituisce il numero di archi
         */
        return getEdges().size();
    }

    @Override
    public void clear() {
        this.adjacentLists.clear();
    }

    @Override
    public boolean isDirected() {
        // Questa classe implementa grafi non orientati
        return false;
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        /*
         * Restituisce tutti i nodi appartenenti al grafo
         */

        Set<GraphNode<L>> toReturn = new HashSet<>();
        for (Map.Entry<GraphNode<L>, Set<GraphEdge<L>>> entry : adjacentLists.entrySet()) {
            toReturn.add(entry.getKey());
        }

        return toReturn;
    }

    @Override
    public boolean addNode(GraphNode<L> node) {
        /*
         * Aggiunge un nodo al grafo
         */

        // In caso di parametro null
        if (node == null) {
            throw new NullPointerException("Parametro null!");
        }
        // Controllo se sia già contenuto nell'insieme
        if (adjacentLists.containsKey(node)) {
            return false;
        } else {
            adjacentLists.put(node, new HashSet<>());
            return true;
        }
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        /*
         * Rimuove un nodo dal grafo e tutti gli archi collegati ad esso
         */

        // In caso di parametro null
        if (node == null) {
            throw new NullPointerException("Parametro null!");
        }

        /*
         * Insieme degli archi collegati al nodo passato, essendo un grafo non orientato
         * devo cancellarli anche dagli altri nodi
         */
        Set<GraphEdge<L>> edgesToDelete = new HashSet<>();

        if (containsNode(node)) {
            // Aggiungo all'insieme edgesToDelete, tutti gli archi collegati al nodo passato
            for (Map.Entry<GraphNode<L>, Set<GraphEdge<L>>> entry : adjacentLists.entrySet()) {
                if (entry.getKey().equals(node)) {
                    edgesToDelete.addAll(entry.getValue());
                }
            }
            // Elimino dai nodi restanti gli archi che ho nell'insieme edgesToDelete
            for (GraphEdge<L> edge : edgesToDelete) {
                removeEdge(edge);
            }
            // Rimuovo il nodo
            adjacentLists.remove(node);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsNode(GraphNode<L> node) {
        /*
         * Determina se c'è un certo nodo in questo grafo
         */

        // In caso di parametro null
        if (node == null) {
            throw new NullPointerException("Parametro null!");
        }

        return adjacentLists.containsKey(node);
    }

    @Override
    public GraphNode<L> getNodeOf(L label) {
        /*
         * Restituisce il nodo avente l'etichetta passata
         */

        // In caso di parametro null
        if (label == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Ricerca del nodo avente l'etichetta label
        for (Map.Entry<GraphNode<L>, Set<GraphEdge<L>>> entry : adjacentLists.entrySet()) {
            if (entry.getKey().getLabel().equals(label)) {
                // Ritorno il nodo trovato
                return entry.getKey();
            }
        }

        // Non è stato trovato
        return null;
    }

    @Override
    public int getNodeIndexOf(L label) {
        if (label == null)
            throw new NullPointerException(
                    "Tentativo di ricercare un nodo con etichetta null");
        throw new UnsupportedOperationException(
                "Ricerca dei nodi con indice non supportata");
    }

    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        throw new UnsupportedOperationException(
                "Ricerca dei nodi con indice non supportata");
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        /*
         * Restituisce l'insieme di tutti i nodi adiacenti a node
         */

        // In caso di parametro null
        if (node == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Se il nodo passato non esiste
        if (!containsNode(node)) {
            throw new IllegalArgumentException("Il nodo non esiste");
        }

        // Insieme dei nodi adiacenti
        Set<GraphNode<L>> toReturn = new HashSet<>();
        // Insieme degli archi che collegano il nodo
        Set<GraphEdge<L>> toScroll = new HashSet<>(getEdgesOf(node));

        // Aggiungo l'altro nodo dell'arco all'insieme toReturn
        for (GraphEdge<L> roller : toScroll) {
            if (!roller.getNode1().equals(node)) {
                toReturn.add(roller.getNode1());
            }
            if (!roller.getNode2().equals(node)) {
                toReturn.add(roller.getNode2());
            }
        }

        return toReturn;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Ricerca dei nodi predecessori non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        /*
         * Ritorna tutti gli archi appartenenti al grafo
         */

        // Insieme degli archi
        Set<GraphEdge<L>> toReturn = new HashSet<>();
        for (Map.Entry<GraphNode<L>, Set<GraphEdge<L>>> entry : adjacentLists.entrySet()) {
            toReturn.addAll(entry.getValue());
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
            throw new NullPointerException("Parametro null");
        }

        // Se uno dei due nodi non appartiene al grafo
        if ((!containsNode(edge.getNode1())) || (!containsNode(edge.getNode2()))) {
            throw new IllegalArgumentException("Nodo non appartenente al grafo");
        }

        // Se l'arco è orientato
        if (edge.isDirected()) {
            throw new IllegalArgumentException("Arco orientato!");
        }

        // Se l'arco è già contenuto nel grafo
        if (containsEdge(edge)) {
            return false;
        } else {
            // Altrimenti aggiungo l'arco su ogni node
            for (Map.Entry<GraphNode<L>, Set<GraphEdge<L>>> entry : adjacentLists.entrySet()) {
                if (entry.getKey().equals(edge.getNode1())) {
                    entry.getValue().add(edge);
                }
                if (entry.getKey().equals(edge.getNode2())) {
                    entry.getValue().add(edge);
                }
            }
            return true;
        }
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        /*
         * Rimuove un'arco dal grafo
         */

        // In caso di parametro null
        if (edge == null) {
            throw new NullPointerException("Parametro null");
        }

        // Se uno dei due nodi non appartiene al grafo
        if ((!containsNode(edge.getNode1())) || (!containsNode(edge.getNode2()))) {
            throw new IllegalArgumentException("Nodo non appartenente al grafo");
        }

        // Se l'arco non è presente
        if (!containsEdge(edge)) {
            return false;
        }

        // L'arco è completamente rimosso quando il numero di cancellazioni è pari a 2
        int del = 0;

        for (Map.Entry<GraphNode<L>, Set<GraphEdge<L>>> entry : adjacentLists.entrySet()) {
            if (entry.getValue().contains(edge)) {
                del++;
                entry.getValue().remove(edge);
            }
        }

        return del == 2;
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        /*
         * Controlla la presenza di un'arco all'interno del grafo
         */
        // In caso di parametro null
        if (edge == null) {
            throw new NullPointerException("Parametro null");
        }

        // Se uno dei due nodi non appartiene al grafo
        if ((!containsNode(edge.getNode1())) || (!containsNode(edge.getNode2()))) {
            throw new IllegalArgumentException("Nodo non appartenente al grafo!");
        }

        for (Map.Entry<GraphNode<L>, Set<GraphEdge<L>>> entry : adjacentLists.entrySet()) {
            if (entry.getValue().contains(edge)) {
                return true;
            }
        }

        // L'arco non esiste nell'insieme
        return false;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        /*
         * Ritorna tutti gli archi collegati ad un certo nodo
         */

        // In caso di parametro null
        if (node == null) {
            throw new NullPointerException("Parametro null!");
        }

        // Il nodo non esiste
        if (!containsNode(node)) {
            throw new IllegalArgumentException("Il nodo non esiste!");
        }

        // Insieme degli archi
        Set<GraphEdge<L>> toReturn = new HashSet<>();
        for (Map.Entry<GraphNode<L>, Set<GraphEdge<L>>> entry : adjacentLists.entrySet()) {
            if (entry.getKey().equals(node)) {
                toReturn.addAll(entry.getValue());
            }
        }

        return toReturn;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Ricerca degli archi entranti non supportata in un grafo non orientato");
    }

    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) {
        /*
         * Restituisce un'arco se appartiene al grafo
         */

        // In caso di parametro null
        if ((node1 == null) || (node2 == null)) {
            throw new NullPointerException("Parametro null!");
        }

        // Uno dei due nodi non appartiene all'insieme
        if((!containsNode(node1))||(!containsNode(node2))){
            throw new IllegalArgumentException("Nodo non appartenente al grafo!");
        }

        for(Map.Entry<GraphNode<L>, Set<GraphEdge<L>>> entry:adjacentLists.entrySet()){
            for(GraphEdge<L> edge: entry.getValue()){
                if((edge.getNode1().equals(node1)&&edge.getNode2().equals(node2))||(edge.getNode1().equals(node2)&&edge.getNode2().equals(node1))){
                    return edge;
                }
            }
        }

        // L'arco non esiste
        return null;
    }

    @Override
    public GraphEdge<L> getEdgeAtNodeIndexes(int i, int j) {
        throw new UnsupportedOperationException(
                "Operazioni con indici non supportate");
    }

}
