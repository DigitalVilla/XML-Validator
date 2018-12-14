
package lists;

import java.util.EmptyStackException;

import lists.MyArrayList;
import utils.Iterator;
import utils.StackADT;

/**
 * This class proceeds in a last-in-first-out (LIFO) manner. Elements are only
 * added to the top of the stack and only the last element/top can be removed.
 * It uses MyArrayList for the method implementations. with five methods to
 * treat the array into a stack. When a stack is first created, it contains no
 * elements.
 * 
 * @param <E> the type of elements held in this collection
 */
public class MyStack<E> implements StackADT<E> {
	private MyArrayList<E> stack;

	public MyStack() {
		init();
	}

	@Override
	public void push(E toAdd) throws NullPointerException {
		stack.add(toAdd);

	}

	@Override
	public E pop() throws EmptyStackException {
		try {
			return stack.remove(stack.size() - 1);
		} catch (IndexOutOfBoundsException E) {
			throw new EmptyStackException();
		}

	}

	@Override
	public E peek() throws EmptyStackException {
		try {
			return stack.get(stack.size() - 1);
		} catch (IndexOutOfBoundsException E) {
			throw new EmptyStackException();
		}
	}

	@Override
	public void clear() {
		init();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public Object[] toArray() {
		return stack.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return stack.toArray(holder);
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		return stack.contains(toFind);
	}

	@Override
	public int search(E toFind) {
		int indx = stack.size();
		for (int i = stack.size() - 1; i >= 0; i--) {
			if (stack.get(i).equals(toFind))
				return indx -= i;
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return stack.iterator();
	}

	@Override
	public boolean equals(StackADT<E> that) {
		if (that.size() != stack.size())
			return false;

		Object[] dis = stack.toArray();
		Object[] dat = that.toArray();

		for (int i = 0; i < stack.size(); i++) {
			if (!dis[i].equals(dat[i])) 
				return false;
		}
		return true;
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public String toString() {
		return stack.toString();
	}

	private void init() {
		stack = new MyArrayList<E>();
		stack.acceptNulls(false);
	}


}
