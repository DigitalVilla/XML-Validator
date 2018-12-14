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

import lists.DLL;
import lists.MyStack;
import utils.Iterator;
import java.util.EmptyStackException;

/**
 * @author 767110
 *
 */
public class MyStackTest {

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

	private MyStack<String> stack;
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
		stack = new MyStack<String>();
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
	 * Test method for {@link lists.MyStack#size()}.
	 */
	@Test
	public void testSize() {
		stack.push("hola");
		assertEquals(1, stack.size());
	}

	/**
	 * Test method for {@link lists.MyStack#push(int,java.lang.Object )}.
	 */
	@Test
	public void testPush() {
		stack.push(a);
		stack.push(d);
		if (!stack.peek().equals("D"))
			fail("Invalid remove method");
		stack.push(c);
		stack.push(a);
		assertEquals(4, stack.size());
		assertEquals("[A, D, C, A]", stack.toString());
		if (!stack.peek().equals("A"))
			fail("Invalid remove method");
	}

	/**
	 * Test method for {@link lists.MyStack#push(int,java.lang.Object )}.
	 */
	@Test(expected = NullPointerException.class)
	public void testPushNull() {
		stack.push(null);
	}

	/**
	 * Test method for {@link lists.MyStack#clear()}.
	 */
	@Test
	public void testClear1() {
		stack.push(a);
		stack.push(b);
		stack.push(c);
		assertEquals("[A, B, C]", stack.toString());
		stack.clear();
		assertEquals(0, stack.size());
	}

	/**
	 * Test method for {@link lists.MyStack#push(java.lang.Object)}.
	 */
	@Test
	public void testPushInteger() {
		MyStack<Integer> stack = new MyStack<Integer>();
		Integer[] ints = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		stack.push(ints[1]);
		stack.push(ints[2]);
		stack.push(4);
		stack.push(ints[4]);
//		.out.println(stack);
		assertEquals(4, stack.size());
		assertEquals("[2, 3, 4, 5]", stack.toString());
	}

	/**
	 * Test method for {@link lists.MyStack#peek()}.
	 */
	@Test(expected = EmptyStackException.class)
	public void testPeekException() {
		stack.peek();
	}

	/**
	 * Test method for {@link lists.MyStack#peek()}.
	 */
	@Test
	public void testPOP() { 
		stack.push(a); 
		stack.push(b);
		stack.push(c);
		if (!stack.pop().equals("C") || !stack.pop().equals("B"))
			fail("Invalid pop method");
		stack.push(d);
		assertEquals("D", stack.peek());
		assertEquals(2, stack.size());
		assertEquals("[A, D]", stack.toString());
		
	} 

	/**
	 * Test method for {@link lists.MyStack#pop()}.
	 */
	@Test(expected = EmptyStackException.class)
	public void testPOPException() {
		stack.pop();
	}

	/**
	 * Test method for {@link lists.MyStack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty1() {
		assertEquals(true, stack.isEmpty());
		stack.push(d);
		assertEquals(false, stack.isEmpty());
	}

	/**
	 * Test method for {@link lists.MyStack#clear()}.
	 */
	@Test
	public void testClear() {
		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(15);
		stack.push(92);
		stack.push(42);
		stack.clear();
		assertEquals("Clear is not deleting", 0, stack.size());
	}

	/**
	 * Test method for {@link lists.MyStack#peek()}.
	 */
	@Test
	public void testPeekInteger() {
		 MyStack <Integer> stack = new  MyStack <Integer>();
		stack.push(10);
		stack.push(20);
		stack.push(12);
		stack.push(32);

		if (32 != stack.pop())
			fail("Invalid pop method");
		if (12 != stack.peek())
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.MyStack#peek()}.
	 */
	@Test
	public void testPeekString() {
		 MyStack <String> stack = new  MyStack <String>();
		stack.push("10");
		stack.push("20");
		stack.push("12");
		stack.push("32");

		if ("32" != stack.pop())
			fail("Invalid pop method");
		if ("12" != stack.peek())
			fail("Invalid storing method");
	}


	/**
	 * Test method for {@link lists.MyStack#search(java.lang.Object)}.
	 */
	@Test
	public void testfindElementR() {
		MyStack <String> stack = new MyStack <String>();
		stack.push("24");
		stack.push("95");
		stack.push("18");
		stack.push("35");
		stack.push("29");
		stack.push("11");
		stack.push("13");
		stack.push("20");
		stack.push("9");
		stack.push("12");
		stack.push("37");
		stack.push("11");
		stack.push("31");
		stack.push("74");
		stack.push("42");
		stack.push("41");
		stack.push("42");
		stack.push("8");
		stack.push("19");
		stack.push("32");
		assertEquals(1, stack.search("32"));
		assertEquals(4, stack.search("42"));
		assertEquals(9, stack.search("11"));
		assertEquals(3, stack.search("8"));
		assertEquals(12, stack.search("9"));
		assertEquals(20, stack.search("24"));
	}

	/**
	 * Test method for {@link lists.MyStack#search(java.lang.Object)}.
	 */
	@Test
	public void testContainsR() {
		MyStack <String> stack = new MyStack <String>();
		stack.push("24");
		stack.push("95");
		stack.push("18");
		stack.push("35");
		stack.push("29");
		stack.push("11");
		stack.push("13");
		stack.push("20");
		stack.push("9");
		stack.push("12");
		stack.push("37");
		stack.push("11");
		stack.push("31");
		stack.push("74");
		stack.push("42");
		stack.push("41");
		stack.push("42");
		stack.push("8");
		stack.push("19");
		stack.push("32");
		assertEquals(false, stack.contains("320"));
		assertEquals(true, stack.contains("32"));
		assertEquals(true, stack.contains("42"));
		assertEquals(true, stack.contains("11"));
		assertEquals(true, stack.contains("8"));
		assertEquals(true, stack.contains("9"));
		assertEquals(false, stack.contains("99"));
		assertEquals(true, stack.contains("24"));
	}

	/**
	 * Test method for {@link lists.MyStack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		MyStack <String> stack = new MyStack <String>();
		assertEquals(true, stack.isEmpty());
		stack.push("Hola");
		assertEquals(false, stack.isEmpty());
	}


	/**
	 * Test method for {@link lists.MyStack#contains(java.lang.Object)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsException2() {
		MyStack <String> stack = new MyStack <String>();
		assertEquals(false, stack.contains(null));
	}

	/**
	 * Test method for {@link lists.MyStack#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		MyStack <String> stack = new MyStack <String>();
		stack.push("Hola");
		assertEquals(false, stack.contains("casa"));
		assertEquals(true, stack.contains("Hola"));
	}

	/**
	 * Test method for {@link lists.MyStack#toArray(E[])}.
	 */
	@Test
	public void testToArrayEArray() {
		MyStack<Integer> myList = new MyStack<Integer>();
		myList.push(20);
		myList.push(10);
		myList.push(12);
		myList.push(32);
		myList.push(110);
		Integer[] newList = new Integer[5];
		myList.toArray(newList);

		if (32 != newList[3] || 110 != newList[4])
			fail("invalid method");
		assertEquals("[20, 10, 12, 32, 110]", myList.toString());
	}

	/**
	 * Test method for {@link lists.MyStack#toArray(E[])}.
	 */
	@Test(expected = NullPointerException.class)
	public void testToArrayNullException() {
		stack.toArray(null);
	}

	/**
	 * Test method for {@link lists.MyStack#toArray(E[])}.
	 */
	@Test
	public void testToArraySmallSize() {
		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(20);
		stack.push(10);
		stack.push(12);
		stack.push(32);
		stack.push(110);
		Object[] newList = new Integer[3];
		newList = stack.toArray((Integer[]) newList);
		if (!newList[3].equals(32) || !newList[4].equals(110))
			fail("invalid method");
		assertEquals(5,newList.length);
	}

	/**
	 * Test method for {@link lists.MyStack#toArray()}.
	 */
	@Test
	public void testToArray() {
		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(20);
		stack.push(10);
		stack.push(12);
		stack.push(32);
		stack.push(110);
		Object[] newlist = stack.toArray();
		if (newlist.length != 5)
			fail("invalid method");
	}

	/**
	 * Test method for {@link lists.MyStack#iterator()}.
	 */
	@Test
	public void testIterator() {
		 MyStack <Integer> stack = new  MyStack <Integer>();
		stack.push(20);
		stack.push(10);
		stack.push(12);
		stack.push(32);
		stack.push(110);
		Iterator<Integer> itr = stack.iterator();
			Object[] arr = stack.toArray();
		
		int i = 0;
		while (itr.hasNext()) {
			assertEquals(arr[i++], itr.next());
		}
	}

	/**
	 * Test method for {@link lists.MyStack#iterator()}.
	 */
	@Test
	public void testIteratorEmpty() {
		MyStack<Integer> stack = new MyStack<Integer>();
		Iterator<Integer> itr = stack.iterator();
		assertEquals(false, itr.hasNext());
	}
	/**
	 * Test method for {@link lists.MyStack#equals()}.
	 */
	@Test
	public void testEmptyStackEquals() {
		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(20);
		stack.push(10);
		
		MyStack<Integer> stack2 = new MyStack<Integer>();
		stack2.push(20);
		stack2.push(10);
		
//		MyStack<String> stack3 = new MyStack<String>();
//		stack3.push("20");
//		stack3.push("10");
//		stack3.push(12);
		assertEquals(true, stack.equals(stack2) );
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public void testToString() {
		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(20);
		stack.push(10);
		stack.push(12);
		stack.push(32);
		stack.push(110);
		assertEquals("[20, 10, 12, 32, 110]", stack.toString());
	}
}
