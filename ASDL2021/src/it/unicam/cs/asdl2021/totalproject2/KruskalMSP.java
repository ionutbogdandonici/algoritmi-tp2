package it.unicam.cs.asdl2021.totalproject2;

import java.util.*;

/**
 * Classe singoletto che implementa l'algoritmo di Kruskal per trovare un
 * Minimum Spanning Tree di un grafo non orientato, pesato e con pesi non
 * negativi.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class KruskalMSP<L> {

    /*
     * Struttura dati per rappresentare gli insiemi disgiunti utilizzata
     * dall'algoritmo di Kruskal.
     */
    private ArrayList<HashSet<GraphNode<L>>> disjointSets;
    /**
     * Costruisce un calcolatore di un albero di copertura minimo che usa
     * l'algoritmo di Kruskal su un grafo non orientato e pesato.
     */
    public KruskalMSP() {
        this.disjointSets = new ArrayList<HashSet<GraphNode<L>>>();
    }

    /**
     * Utilizza l'algoritmo goloso di Kruskal per trovare un albero di copertura
     * minimo in un grafo non orientato e pesato, con pesi degli archi non
     * negativi. L'albero restituito non è radicato, quindi è rappresentato
     * semplicemente con un sottoinsieme degli archi del grafo.
     *
     * @param g un grafo non orientato, pesato, con pesi non negativi
     * @return l'insieme degli archi del grafo g che costituiscono l'albero di
     * copertura minimo trovato
     * @throw NullPointerException se il grafo g è null
     * @throw IllegalArgumentException se il grafo g è orientato, non pesato o
     * con pesi negativi
     */
    public Set<GraphEdge<L>> computeMSP(Graph<L> g) {
        // In caso di parametro null
        if (g == null) {
            throw new NullPointerException("Parametro null!");
        }
        // Se il grafo è orientato
        if (g.isDirected()) {
            throw new IllegalArgumentException("Il grafo è orientato");
        }
        // Se il grafo ha archi non pesati o negativi
        for (GraphEdge<L> edge : g.getEdges()) {
            if ((!edge.hasWeight()) || (edge.getWeight() < 0)) {
                throw new IllegalArgumentException("Arco non pesato o negativo!");
            }
        }

        // Insieme degli archi che completano l'algoritmo
        Set<GraphEdge<L>> toReturn = new HashSet<>();

        // Creo gli insiemi disgiunti all'interno di disjointSets
        for (GraphNode<L> node : g.getNodes()) {
            makeSet(node);
        }

        // Ordino gli edges
        ArrayList<GraphEdge<L>> tmp = new ArrayList<>(g.getEdges());
        ArrayList<GraphEdge<L>> orderedEdges = new ArrayList<>((orderEdges(tmp)));

        // Per ogni edge
        for(GraphEdge<L> edge : orderedEdges){
            // Se i vertici non fanno parte dello stesso Insieme
            if(findSet(edge.getNode1())!=findSet(edge.getNode2())){
                // L'arco viene aggiunto all'insieme degli archi che effettuano il Kruskal
                toReturn.add(edge);
                // Unisco i due insiemei degli nodi in un'unico insieme
                union(edge.getNode1(), edge.getNode2());
            }
        }

        return toReturn;
    }

    /*
     * Crea un nuovo insieme di nodi il cui unico elemento è
     * il nodo stesso
     */
    private void makeSet(GraphNode<L> node) {
        disjointSets.add(new HashSet<>());
        disjointSets.get(disjointSets.size() - 1).add(node);
    }

    // Restituisce l'indice dell'insieme che contiene node
    private int findSet(GraphNode<L> node) {
        Set<GraphNode<L>> no1 = new HashSet<>();
        no1.add(node);
        return disjointSets.indexOf(no1);
    }

    // Ordinamento dell'array di archi
    private ArrayList<GraphEdge<L>> orderEdges(ArrayList<GraphEdge<L>> edges) {
        for (int i = 0; i < edges.size(); i++) {
            for (int j = 0; j < edges.size() - 1 - i; j++) {
                if (edges.get(j).getWeight() > edges.get(j + 1).getWeight()) {
                    GraphEdge<L> temp = edges.get(j);
                    edges.set(j, edges.get(j + 1));
                    edges.set(j + 1, temp);
                }
            }
        }
        return edges;
    }

    // Unisce gli insiemi dinamici che contengono node1 e node2
    private void union(GraphNode<L> node1, GraphNode<L> node2){
        Set<GraphNode<L>> no2 = new HashSet<>();
        no2.add(node2);

        disjointSets.get(findSet(node1)+1).add(node2);
        disjointSets.remove(no2);
    }
}