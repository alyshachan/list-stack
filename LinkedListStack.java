package assign06;

import java.util.NoSuchElementException;

/**
 * @author Alysha Chan and Chavo Salazar
 * 
 *         LinkedListStack is a generic stack class that
 *         implements Stack interface. The stack is backed
 *         by an instance of SinglyLinkedList as a private
 *         member variable.
 * 
 * @param <T> - generic type placeholder
 */
public class LinkedListStack<T> implements Stack<T> {

	private SinglyLinkedList<T> list;

	/**
	 * Default constructor
	 */
	public LinkedListStack() {
		list = new SinglyLinkedList<>();
	}

	/**
	 * Removes all of the elements from this stack.
	 * 
	 * This method has the complexity of O(1).
	 */
	@Override
	public void clear() {
		list.clear();
	}

	/**
	 * This method returns true if the stack's size is 0, false if not.
	 * 
	 * This method has the complexity of O(1).
	 * 
	 * @return true - if this stack contains no elements
	 * @return false - if this stack contains elements
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Returns the element at the top of the stack.
	 * 
	 * This method has the complexity of O(1).
	 * 
	 * @return list.getFirst() - the element at the top of the stack
	 * @throws NoSuchElementException - if the stack is empty
	 */
	@Override
	public T peek() throws NoSuchElementException {
		if (list.size() == 0)
			throw new NoSuchElementException();
		return list.getFirst();
	}

	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * This method has the complexity of O(1).
	 * 
	 * @return list.deleteFirst - the element at the top of the stack
	 * @throws NoSuchElementException - if the stack is empty
	 */
	@Override
	public T pop() throws NoSuchElementException {
		if (list.size() == 0)
			throw new NoSuchElementException();
		return list.deleteFirst();
	}

	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * This method has the complexity of O(1).
	 * 
	 * @param element - the element to be added
	 */
	@Override
	public void push(T element) {
		list.insertFirst(element);
	}

	/**
	 * Returns the number of elements in the stack
	 * 
	 * This method has the complexity of O(1).
	 * 
	 * @return list.size() - the number of elements in the stack
	 */
	@Override
	public int size() {
		return list.size();
	}

}
