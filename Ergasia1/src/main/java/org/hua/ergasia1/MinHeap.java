/**
 * This code is part of the lab exercises for the Data Structures course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package org.hua.ergasia1;

/**
 * A min-heap
 *
 * @param <E> the element type
 */
public interface MinHeap<E extends Comparable<E>> {

	/**
	 * Insert an element into the heap
	 * 
	 * @param elem the element
	 */
	void insert(E elem);

	/**
	 * Find the minimum element
	 * 
	 * @return the minimum element
	 */
	E findMin();

	/**
	 * Delete the minimum element
	 * 
	 * @return the deleted element
	 */
	E deleteMin();

	/**
	 * Check if the heap is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Get the size of the heap
	 * 
	 * @return the size of the heap
	 */
	int size();

	/**
	 * Clear the heap
	 */
	void clear();

}
