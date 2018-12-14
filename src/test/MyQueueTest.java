/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.EmptyQueueException;
import lists.DLL;
import lists.MyQueue;
import lists.MyStack;
import utils.Iterator;

/**
 * @author 767110
 *
 */
public class MyQueueTest {

	/**
	 * Precondition:
	 * 
	 * Description: This method
	 *
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Precondition:
	 * 
	 * Description: This method
	 *
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private MyQueue<String> queue;
	String a, b, c, d, e;

	/**
	 * Precondition:
	 * 
	 * Description: This method
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		queue = new MyQueue<String>();
		a = "A";
		b = "B";
		c = "C";
		d = "D";
		e = "E";
	}

	/**
	 * Precondition:
	 * 
	 * Description: This method
	 *
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link lists.MyQueue#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(0, queue.size());
		queue.enqueue("hola");
		assertEquals(1, queue.size());
	}

	/**
	 * Test method for {@link lists.MyQueue#enqueue(int,java.lang.Object )}.
	 */
	@Test
	public void testPush() throws EmptyQueueException {
		queue.enqueue(a);
		queue.enqueue(d);
		if (!queue.peek().equals("A"))
			fail("Invalid remove method");
		queue.enqueue(c);
		queue.enqueue(a);
		assertEquals(4, queue.size());
		assertEquals("[A, D, C, A]", queue.toString());
		if (!queue.peek().equals("A"))
			fail("Invalid remove method");
	}

	/**
	 * Test method for {@link lists.MyQueue#enqueue(int,java.lang.Object )}.
	 */
	@Test(expected = NullPointerException.class)
	public void testPushNull() {
		queue.enqueue(null);
	}

	/**
	 * Test method for {@link lists.MyQueue#dequeueAll()}.
	 */
	@Test
	public void testDequeAll1() {
		queue.enqueue(a);
		queue.enqueue(b);
		queue.enqueue(c);
		assertEquals("[A, B, C]", queue.toString());
		queue.dequeueAll();
		assertEquals(0, queue.size());
	}

	/**
	 * Test method for {@link lists.MyQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testPushInteger() {
		MyQueue<Integer> queue = new MyQueue<Integer>();
		Integer[] ints = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		queue.enqueue(ints[1]);
		queue.enqueue(ints[2]);
		queue.enqueue(4);
		queue.enqueue(ints[4]);
		assertEquals(4, queue.size());
		assertEquals("[2, 3, 4, 5]", queue.toString());
	}

	/**
	 * Test method for {@link lists.MyQueue#peek()}.
	 * @throws EmptyQueueException 
	 */
	@Test(expected = EmptyQueueException.class)
	public void testPeekException() throws EmptyQueueException {
		queue.peek();
	}

	/**
	 * Test method for {@link lists.MyQueue#dequeue()}.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testDeque() throws EmptyQueueException { 
		queue.enqueue(a); 
		queue.enqueue(b);
		queue.enqueue(c);
		if (!queue.dequeue().equals("A") || !queue.dequeue().equals("B"))
			fail("Invalid dequeue method");
		queue.enqueue(d);
		assertEquals("C", queue.peek());
		assertEquals(2, queue.size());
		assertEquals("[C, D]", queue.toString());
	} 

	/**
	 * Test method for {@link lists.MyQueue#dequeue()}.
	 * @throws EmptyQueueException 
	 */
	@Test(expected = EmptyQueueException.class)
	public void testDequeException() throws EmptyQueueException {
		queue.dequeue();
	}

	/**
	 * Test method for {@link lists.MyQueue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty1() {
		assertEquals(true, queue.isEmpty());
		queue.enqueue(d);
		assertEquals(false, queue.isEmpty());
	}

	/**
	 * Test method for {@link lists.MyQueue#dequeueAll()}.
	 */
	@Test
	public void testDequeAll () {
		MyQueue<Integer> queue = new MyQueue<Integer>();
		queue.enqueue(15);
		queue.enqueue(92);
		queue.enqueue(42);
		queue.dequeueAll();
		assertEquals("DequeueAll is not deleting", 0, queue.size());
	}

	/**
	 * Test method for {@link lists.MyQueue#peek()}.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testPeekInteger() throws EmptyQueueException {
		MyQueue <Integer> queue = new MyQueue <Integer>();
		 Integer[] ints = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		queue.enqueue(ints[2]);
		queue.enqueue(20);
		queue.enqueue(12);
		queue.enqueue(32);
		if (3 != queue.peek())
			fail("Invalid storing method");
		if (3 != queue.dequeue())
			fail("Invalid dequeue method");
		if (20 != queue.peek())
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.MyQueue#peek()}.
	 * @throws EmptyQueueException 
	 */
	@Test
	public void testPeekString() throws EmptyQueueException {
		 MyQueue<String> queue = new  MyQueue<String>();
		 
		queue.enqueue("10");
		queue.enqueue("20");
		queue.enqueue("12");
		queue.enqueue("32");

		if ("10" != queue.dequeue())
			fail("Invalid dequeue method");
		if ("20" != queue.peek())
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.MyQueue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		MyQueue<String> queue = new MyQueue<String>();
		assertEquals(true, queue.isEmpty());
		queue.enqueue("Hola");
		assertEquals(false, queue.isEmpty());
	}


	/**
	 * Test method for {@link lists.MyQueue#toArray(E[])}.
	 */
	@Test
	public void testToArrayEArray() {
		MyQueue<Integer> myList = new MyQueue<Integer>();
		myList.enqueue(20);
		myList.enqueue(10);
		myList.enqueue(12);
		myList.enqueue(32);
		myList.enqueue(110);
		Integer[] newList = new Integer[5];
		myList.toArray(newList);

		if (32 != newList[3] || 110 != newList[4])
			fail("invalid method");
		assertEquals("[20, 10, 12, 32, 110]", myList.toString());
	}

	/**
	 * Test method for {@link lists.MyQueue#toArray(E[])}.
	 */
	@Test(expected = NullPointerException.class)
	public void testToArrayNullException() {
		queue.toArray(null);
	}

	/**
	 * Test method for {@link lists.MyQueue#toArray(E[])}.
	 */
	@Test
	public void testToArraySmallSize() {
		MyQueue<Integer> queue = new MyQueue<Integer>();
		queue.enqueue(20);
		queue.enqueue(10);
		queue.enqueue(12);
		queue.enqueue(32);
		queue.enqueue(110);
		Object[] newList = new Integer[3];
		newList = queue.toArray((Integer[]) newList);
		if (!newList[3].equals(32) || !newList[4].equals(110))
			fail("invalid method");
		assertEquals(5,newList.length);
	}

	/**
	 * Test method for {@link lists.MyQueue#toArray()}.
	 */
	@Test
	public void testToArray() {
		MyQueue<Integer> queue = new MyQueue<Integer>();
		queue.enqueue(20);
		queue.enqueue(10);
		queue.enqueue(12);
		queue.enqueue(32);
		queue.enqueue(110);
		Object[] newlist = queue.toArray();
		if (newlist.length != 5)
			fail("invalid method");
	}

	/**
	 * Test method for {@link lists.MyQueue#iterator()}.
	 */
	@Test
	public void testIterator() {
		MyQueue <Integer> queue = new MyQueue <Integer>();
		queue.enqueue(20);
		queue.enqueue(10);
		queue.enqueue(12);
		queue.enqueue(32);
		queue.enqueue(110);
		Iterator<Integer> itr = queue.iterator();
			Object[] arr = queue.toArray();
		
		int i = 0;
		while (itr.hasNext()) {
			assertEquals(arr[i++], itr.next());
		}
	}

	/**
	 * Test method for {@link lists.MyQueue#iterator()}.
	 */
	@Test
	public void testIteratorEmpty() {
		MyQueue<Integer> queue = new MyQueue<Integer>();
		Iterator<Integer> itr = queue.iterator();
		assertEquals(false, itr.hasNext());
	}
	/**
	 * Test method for {@link lists.MyQueue#equals()}.
	 */
	@Test
	public void testEmptyStackEquals() {
		MyQueue<Integer> queue = new MyQueue<Integer>();
		queue.enqueue(20);
		queue.enqueue(10);
		
		MyQueue<Integer> stack2 = new MyQueue<Integer>();
		stack2.enqueue(20);
		stack2.enqueue(10);
		assertEquals(true, queue.equals(stack2) );
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public void testToString() {
		MyQueue<Integer> queue = new MyQueue<Integer>();
		queue.enqueue(20);
		queue.enqueue(10);
		queue.enqueue(12);
		queue.enqueue(32);
		queue.enqueue(110);
		assertEquals("[20, 10, 12, 32, 110]", queue.toString());
	}
}
