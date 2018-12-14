package lists;

import exceptions.EmptyQueueException;
import utils.Iterator;
import utils.QueueADT;

public class MyQueue<E> implements QueueADT<E> {

	private static final long serialVersionUID = 1L;
	private DLL<E> queue;
	private int limit;

	public MyQueue() {
		init(0);
	}

	public MyQueue(int size) {
		init(size);
	}

	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		if (isFull())
			throw new NullPointerException();
		queue.add(toAdd);
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		try {
			return queue.remove(0);
		} catch (IndexOutOfBoundsException e) {
			throw new EmptyQueueException();
		}

	}

	@Override
	public E peek() throws EmptyQueueException {
		try {
			return queue.get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new EmptyQueueException();
		}
	}

	@Override
	public void dequeueAll() {
		init(limit);
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return queue.iterator();
	}

	@Override
	public boolean equals(QueueADT<E> that) {
		if (that.size() != queue.size())
			return false;

		Object[] dis = queue.toArray();
		Object[] dat = that.toArray();

		for (int i = 0; i < queue.size(); i++) {
			if (!dis[i].equals(dat[i]))
				return false;
		}
		return true;
	}

	@Override
	public Object[] toArray() {
		return queue.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return queue.toArray(holder);
	}

	@Override
	public boolean isFull() {
		if (limit != 0 && queue.size() == limit)
			return true;
		return false;
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public String toString() {
		return queue.toString();
	}

	private void init(int size) {
		this.setLimit(size);
		queue = new DLL<E>();
		queue.letNulls = false;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
