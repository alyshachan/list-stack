package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Alysha Chan and Chavo Salazar
 * 
 *         SinglyLinkedList is a generic class that implements List interface
 *         with a singly-linked list. A Node class is created in this method,
 *         for SinglyLinkedList to go through a list of nodes.
 * 
 *         The List interface extends Java's Iterable interface, so this method
 *         also has an iterator for the linked list that traverses the elements
 *         from head to tail.
 *
 * @param <T> - generic type placeholder
 */
public class SinglyLinkedList<T> implements List<T> {

	private Node head = null;
	private int size = 0;

	/**
	 * Default constructor
	 */
	public SinglyLinkedList() {
	}

	/**
	 * @author Alysha Chan and Chavo Salazar
	 * 
	 *         A node class with an element of any type and next to get the next
	 *         node.
	 * 
	 *         If Node(T data) is called with a parameter of any type, element will
	 *         be initialized to the data parameter and next will be null.
	 */
	private class Node {
		T element;
		Node next;

		Node(T data) {
			element = data;
			next = null;
		}
	}

	/**
	 * This is a private helper class to get the Node at the index inputed.
	 * 
	 * This method has the complexity of O(N) for a singly-linked list.
	 * 
	 * @param index - the integer of the index the user wants the Node of
	 * @return temp - the Node at the inputed index
	 */
	private Node getNodeAtIndex(int index) {
		Node temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}

	/**
	 * This method inserts element as the first node of a list by creating a new
	 * node of the element, initializing it as the head, and increasing the size of
	 * the SinglyLinkedList.
	 * 
	 * This method has the complexity of O(1) for a singly-linked list.
	 * 
	 * @param element - an element of any type to add to the list
	 */
	@Override
	public void insertFirst(T element) {
		Node newNode = new Node(element);
		newNode.next = head;
		head = newNode;
		size++;
	}

	/**
	 * This method inserts element at a specified index in the list. If index is out
	 * of bounds, it throws an exception. If index is 0, it calls the insertFirst()
	 * method.
	 * 
	 * This method has the complexity of O(N) for a singly-linked list.
	 * 
	 * @param index   - the specified position
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException - if index is out of range (index < 0 ||
	 *                                   index > size())
	 */
	@Override
	public void insert(int index, T element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		if (index == 0) {
			insertFirst(element);
			return;
		}

		Node beforeNodeToInsert = getNodeAtIndex(index - 1);
		Node newNode = new Node(element);
		newNode.next = beforeNodeToInsert.next;
		beforeNodeToInsert.next = newNode;
		size++;
	}

	/**
	 * This method gets the first element in the list. If the list size is 0, it
	 * throws an exception.
	 * 
	 * This method has the complexity O(1) for a singly-linked list.
	 * 
	 * @return head.element - the first element in the list
	 * @throws NoSuchElementException - if the list is empty
	 */
	@Override
	public T getFirst() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();

		return head.element;
	}

	/**
	 * This method gets the element at a specific position in the list. If index is
	 * 0, it calls the getFirst() method.
	 * 
	 * This method has the complexity O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @return getFirst() - the element at index 0
	 * @return getNodeAtIndex(index).element - the element at index
	 * @throws IndexOutOfBoundsException - if index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		if (index == 0)
			return getFirst();

		return getNodeAtIndex(index).element;
	}

	/**
	 * Deletes and returns the first element from the list.
	 * 
	 * This method has the complexity O(1) for a singly-linked list.
	 * 
	 * @return returnValue - the first and deleted element
	 * @throws NoSuchElementException - if the list is empty
	 */
	@Override
	public T deleteFirst() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		
		Node nodeToRemove = head;
		T returnValue = head.element;
		
		if (size == 1)
			nodeToRemove = null;
		else
			nodeToRemove = nodeToRemove.next;
		
		head = nodeToRemove;
		size--;
		return returnValue;
	}

	/**
	 * Deletes and returns the element at a specific position in the list.
	 * 
	 * This method has the complexity O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @return returnValue - the element at the position
	 * @throws IndexOutOfBoundsException - if index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public T delete(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		if (index == 0) {
			return deleteFirst();
		}

		Node beforeNodeToRemove = getNodeAtIndex(index - 1);
		T returnValue = beforeNodeToRemove.next.element;
		beforeNodeToRemove.next = beforeNodeToRemove.next.next;
		size--;
		return returnValue;
	}

	/**
	 * Determines the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element.
	 * 
	 * This method has the complexity of O(N) for a singly-linked list.
	 * 
	 * @param element - the element to search for
	 * @return i - the index of the first occurrence
	 * @return -1 - if the element is not found
	 */
	@Override
	public int indexOf(T element) {
		Node temp = head;
		for (int i = 0; i < size; i++) {
			if (temp.element.equals(element)) {
				return i;
			}
			temp = temp.next;
		}
		return -1;
	}

	/**
	 * This method returns the size of the list.
	 * 
	 * This method has the complexity of O(1) in a singly-linked list.
	 * 
	 * @return size - the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * This method returns true if the list's size is 0, false if not.
	 * 
	 * This method has the complexity of O(1) for a singly-linked list.
	 * 
	 * @return true - if this collection contains no elements
	 * @return false - if this collection contains elements
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/**
	 * Removes all of the elements from this list.
	 * 
	 * This method has the complexity of O(1) for a singly-linked list.
	 */
	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	/**
	 * Generates an array containing all of the elements in this list in proper
	 * sequence (from first element to last element).
	 * 
	 * This method has the complexity of O(N) for a singly-linked list.
	 * 
	 * @return newArray - an array containing all of the elements in this list, in
	 *         order
	 */
	@Override
	public Object[] toArray() {
		Object newArray[] = new Object[size];
		Node temp = head;
		for (int i = 0; i < size; i++) {
			newArray[i] = temp.element;
			temp = temp.next;
		}
		return newArray;
	}

	/**
	 * This method creates a iterator for the singly-linked list.
	 * 
	 * @return new SinglyLinkedListIterator() - an iterator over the elements in
	 *         this list in proper sequence (from first element to last element)
	 */
	@Override
	public Iterator<T> iterator() {
		return new SinglyLinkedListIterator();
	}
	/**
	 * 
	 * @author Alysha Chan and Chavo Salazar
	 * 
	 *         The SinglyLinkedListIterator implements an iterator for
	 *         SinglyLinkedList. It should iterate through the list in sequential
	 *         order, starting at index 0.
	 * 
	 *         In other words, elements are returned by the iterator's next method
	 *         in the same order that they were added to the collection.
	 */
	private class SinglyLinkedListIterator implements Iterator<T> {
		boolean calledNext;
		Node currentNode;
		Node previousNode;
		Node nextNode;

		/*
		 * Constructor for SinglyLinkedListInterator
		 */
		public SinglyLinkedListIterator() {
			calledNext = false;
			currentNode = null;
			previousNode = null;
			nextNode = head;
		}

		/**
		 * Returns true if there are any more items in the list to iterate through,
		 * false otherwise. In other words, returns false if a call to next would throw
		 * an exception.
		 * 
		 * This method has the complexity of O(1).
		 * 
		 * @return true - iterator has a next item
		 * @return false - iterator doesn't have a next item
		 */
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		/**
		 * Must throw a NoSuchElementException if there are no more items to iterate
		 * through, otherwise, returns the next element in the list.
		 * 
		 * This method has the complexity of O(1).
		 * 
		 * @return returnNode - the next item (of any type) in the iterator
		 */
		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			previousNode = currentNode;
			currentNode = nextNode;
			nextNode = nextNode.next;
			calledNext = true;

			return currentNode.element;
		}

		/**
		 * This method removes the last item that was returned by next. It can therefore
		 * only be called once per call to next. If next has not been called since the
		 * last call to remove, or if it hasn't been called at all, throws an
		 * IllegalStateException. The iterator keeps track whether or not it is
		 * currently legal to call remove from the boolean calledNext.
		 */
		public void remove() {
			if (!calledNext)
				throw new IllegalStateException();
			calledNext = false;
			if (previousNode == null) {
				head = head.next;
				currentNode = null;
			} else {
				previousNode.next = nextNode;
				currentNode = nextNode;
			}
			size--;
		}
	}
}
