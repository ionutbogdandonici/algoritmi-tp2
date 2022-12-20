package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Implementazione di una coda con priorità tramite heap binario. Gli oggetti
 * inseriti in coda implementano l'interface PriorityQueueElement che permette
 * di gestire la priorità e una handle dell'elemento. La handle è fondamentale
 * per realizzare in tempo logaritmico l'operazione di decreasePriority che,
 * senza la handle, dovrebbe cercare l'elemento all'interno dello heap e poi
 * aggiornare la sua posizione. Nel caso di heap binario rappresentato con una
 * ArrayList la handle è semplicemente l'indice dove si trova l'elemento
 * nell'ArrayList. Tale campo naturalmente va tenuto aggiornato se l'elemento
 * viene spostato in un'altra posizione.
 *
 * @author Template: Luca Tesei
 *
 * @param <E>
 *                il tipo degli elementi che vengono inseriti in coda.
 *
 */
public class BinaryHeapMinPriorityQueue {

    /*
     * ArrayList per la rappresentazione dello heap. Vengono usate tutte le
     * posizioni (la radice dello heap è quindi in posizione 0).
     */
    private ArrayList<PriorityQueueElement> heap;

    // TODO implementare: inserire eventuali altre variabili istanza

    /**
     * Crea una coda con priorità vuota.
     *
     */
    public BinaryHeapMinPriorityQueue() {
        // Coda dove andranno inseriti gli elementi
        heap = new ArrayList<>();
    }

    /**
     * Add an element to this min-priority queue. The current priority
     * associated with the element will be used to place it in the correct
     * position in the heap. The handle of the element will also be set
     * accordingly.
     *
     * @param element
     *                    the new element to add
     * @throws NullPointerException
     *                                  if the element passed is null
     */
    public void insert(PriorityQueueElement element) {
        // In caso di parametro null
        if (element == null) {
            throw new NullPointerException("Parametro null!");
        }
        heap.add(element);
        minHeap();
    }

    /**
     * Returns the current minimum element of this min-priority queue without
     * extracting it. This operation does not affect the heap.
     *
     * @return the current minimum element of this min-priority queue
     *
     * @throws NoSuchElementException
     *                                    if this min-priority queue is empty
     */
    public PriorityQueueElement minimum() {
        // Heap vuoto
        if (heap.size() == 0) {
            throw new NoSuchElementException("Heap Vuoto!");
        }

        return heap.get(0);
    }

    /**
     * Extract the current minimum element from this min-priority queue. The
     * ternary heap will be updated accordingly.
     *
     * @return the current minimum element
     * @throws NoSuchElementException
     *                                    if this min-priority queue is empty
     */
    public PriorityQueueElement extractMinimum() {
        // Heap Vuoto
        if (heap.size() == 0) {
            throw new NoSuchElementException("Heap Vuoto!");
        }

        PriorityQueueElement toReturn = heap.remove(0);
        // Prima di ritornare rifaccio l'operazione di minHeap
        minHeap();
        return toReturn;
    }

    /**
     * Decrease the priority associated to an element of this min-priority
     * queue. The position of the element in the heap must be changed
     * accordingly. The changed element may become the minimum element. The
     * handle of the element will also be changed accordingly.
     *
     * @param element
     *                        the element whose priority will be decreased, it
     *                        must currently be inside this min-priority queue
     * @param newPriority
     *                        the new priority to assign to the element
     *
     * @throws NoSuchElementException
     *                                      if the element is not currently
     *                                      present in this min-priority queue
     * @throws IllegalArgumentException
     *                                      if the specified newPriority is not
     *                                      strictly less than the current
     *                                      priority of the element
     */
    public void decreasePriority(PriorityQueueElement element, double newPriority) {
        if (!heap.contains(element)) {
            throw new NoSuchElementException("Elemeno non presente nell'heap!");
        }
        if (heap.get(heap.indexOf(element)).getPriority() <= newPriority) {
            throw new IllegalArgumentException("Nuova priorità più grande o uguale a quella attuale!");
        }

        // Aggiorno il nuovo valore e rifaccio il minHeap
        heap.get(heap.indexOf(element)).setPriority(newPriority);
        minHeap();
    }

    /**
     * Determines if this priority queue is empty.
     *
     * @return true if this priority queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Return the current size of this queue.
     *
     * @return the number of elements currently in this queue.
     */
    public int size() {
        return heap.size();
    }

    /**
     * Erase all the elements from this min-priority queue. After this operation
     * this min-priority queue is empty.
     */
    public void clear() {
        this.heap.clear();
    }

    // Processo per riordinare gli elementi all'interno dell'albero
    private void minHeap() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    // Restituisce l'indice del figlio sinistro
    private int leftChild(int i) {
        return (i * 2) + 1;
    }
    // Restituisce l'indice del figlio destro
    private int rightChild(int i) {
        return (i * 2) + 2;
    }

    // Implementazione di heapSort
    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);

        PriorityQueueElement min = heap.get(i);
        if (heap.size() > left) {
            if (heap.get(left).getPriority() < min.getPriority()) {
                min = heap.get(left);
            }
        }

        if (heap.size() > right) {
            if (heap.get(right).getPriority() < min.getPriority()) {
                min = heap.get(right);
            }
        }
        if (heap.get(i).getPriority() != min.getPriority()) {
            PriorityQueueElement tmp = heap.get(i);
            int lastIndexOf = heap.lastIndexOf(min);
            heap.set(i, min);
            heap.set(lastIndexOf, tmp);
            heapify(heap.indexOf(min));
        }
    }

    @Override
    public String toString() {
        return ""+heap+"";
    }
}
