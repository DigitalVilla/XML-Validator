package lists;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

import utils.Iterator;
import utils.List;

public class MyArrayList<E> implements List<E> {
	private static final long serialVersionUID = 1L;
	private E[] list;

	/**
	 * the logical size (number of elements inserted)
	 */
	private int mySize;
	/**
	 * actual physical size of the array
	 */
	private int myLength;

	/**
	 * 
	 */
	private boolean acceptNulls;

	/**
	 * Default constructor
	 */
	public MyArrayList() {
		init(10);
	}

	/**
	 * Default constructor with specific capacity
	 */
	public MyArrayList(int capacity) {
		init(capacity);
	}

	@Override
	public int size() {
		return mySize;
	}

	@Override
	public void clear() {
		init(10);
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (index < 0 || index > mySize)
			throw new IndexOutOfBoundsException();
		if (!acceptNulls && toAdd == null)
			throw new NullPointerException();
		reSize(mySize + 1);
		if (index != mySize) {
			Object[] temp1 = Arrays.copyOfRange(list, 0, index);
			Object[] temp2 = Arrays.copyOfRange(list, index, mySize);
			for (int i = 0; i <= mySize; i++) {
				list[i] = (i < index) ? (E) temp1[i] : (i == index) ? toAdd : (E) temp2[i - index - 1];
			}
			mySize++;
		} else {
			list[mySize++] = toAdd;
		}
		return true;
	}

	public String toString(Object[] temp1) {
		String print = "[";
		int len = temp1.length;
		for (int i = 0; i < len; i++) {
			print += temp1[i];
			print += (i != len - 1) ? ", " : "]";
		}
		return print;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		add(mySize, toAdd);
		return true;
	}

	@Override
	public boolean addAll(List<? extends E> toAdd) throws NullPointerException {
		reSize(mySize + toAdd.size());
		for (int i = 0; i < toAdd.size(); i++) {
			add(mySize, toAdd.get(i));
		}
		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index >= mySize)
			throw new IndexOutOfBoundsException();
		return (E) list[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= mySize)
			throw new IndexOutOfBoundsException();
		Object element = list[index];
		for (int i = index; i < mySize - 1; i++)
			list[i] = list[i + 1];
		mySize--;
		return (E) element;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (!acceptNulls && toRemove == null)
			throw new NullPointerException();
		int indx = indexOf(toRemove);
		if (indx >= 0)
			return remove(indx);
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (!acceptNulls && toChange == null)
			throw new NullPointerException();
		if (index < 0 || index >= mySize)
			throw new IndexOutOfBoundsException();
		Object el = list[index];
		list[index] = toChange;
		return (E) el;
	}

	@Override
	public boolean isEmpty() {
		return mySize == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (!acceptNulls && toFind == null)
			throw new NullPointerException();
		if (mySize < 1)
			return false;
		return (indexOf(toFind) != -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null)
			throw new NullPointerException();
		if (mySize > 0) {
			if (toHold.length < mySize)
				toHold = (E[]) Array.newInstance(toHold.getClass().getComponentType(), mySize);
			for (int i = 0; i < mySize; i++)
				toHold[i] = (E) list[i];
		}
		return toHold;
	}

	@Override
	public E[] toArray() {
		E[] myList = (E[]) new Object[mySize];
		for (int i = 0; i < mySize; i++) {
			myList[i] = (E) list[i];
		}
		return myList;
	}

	@Override
	public Iterator<E> iterator() {
		return new myIterator();
	}

	@Override
	public String toString() {
		String print = (mySize != 0) ? "[" : "[]";
		for (int i = 0; i < mySize; i++) {
			print += list[i];
			print += (i != mySize - 1) ? ", " : "]";
		}
		return print;
	}

	///// UTILITY METHODS

	private int indexOf(E element) {
		for (int i = 0; i < mySize; i++) {
			if (list[i].equals(element))
				return i;
		}
		return -1;
	}

	private void reSize(int elements) {
		if (elements > myLength) {
			int newCapacity = (myLength * 2 > elements) ? myLength * 2 : elements;
			Object[] list2 = new Object[newCapacity];
			for (int i = 0; i < mySize; i++) {
				list2[i] = list[i];
			}
			list = (E[]) list2;
			myLength = list.length;
		}
	}

	private void init(int capacity) {
		list = (E[]) new Object[capacity];
		myLength = list.length;
		mySize = 0;
		acceptNulls = false;
	}

	/**
	 * Trims the capacity of this list to be the exact item count in length. This
	 * operation minimizes the storage usage.
	 */
	public void trimToSize() {
		if (mySize < myLength) {
			list = (mySize == 0) ? (E[]) new Object[0] : Arrays.copyOf(list, mySize);
			myLength = list.length;
			mySize = 0;
		}
	}

	public void acceptNulls(boolean nulls) {
		this.acceptNulls = nulls;

	}
	
//	iterator for link list
//	https://www.youtube.com/watch?v=arkoC146TfQ 
	public class myIterator<E> implements Iterator<E> {
		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return mySize > 0 && currentIndex < mySize && list[currentIndex] != null;
		}

		@Override
		public E next() {
			if (currentIndex == mySize)
				throw new NoSuchElementException();
			return (E) list[currentIndex++];
		}
	}



	
}
