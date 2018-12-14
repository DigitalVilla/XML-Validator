/**
 * 
 */
package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lists.MyArrayList;
import utils.Iterator;

/**
 * @author Omar Villanueva
 *
 */
public class MyArrayListTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link lists.MyArrayList#size()}.
	 */
	@Test
	public void testSize() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(15);
		myList.add(92);
		myList.add(42);
		assertEquals("Size is not counting", 3, myList.size());
	}

	/**
	 * Test method for {@link lists.MyArrayList#clear()}.
	 */
	@Test
	public void testClear() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(15);
		myList.add(92);
		myList.add(42);
		myList.clear();
		assertEquals("Clear is not deleting", 0, myList.size());
	}

	/**
	 * Test method for {@link lists.MyArrayList#add(int, java.lang.Object)}.
	 */ 
	@Test
	public void testAddAtIndex() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(15);
		myList.add(12);
		myList.add(92);
		myList.add(2, 201);
		myList.add(42);
		myList.add(22);
		myList.add(3, 202);
		myList.add(18);
		myList.add(5, 203);
		myList.add(19);
		myList.add(7, 204);
		myList.add(10, 205);
		myList.add(21);
		assertEquals("Invalid size", 13, myList.size());
		if ( 202 != myList.get(3) || 205 != myList.get(10))
			fail("Invalid getting method");
		if (201 != myList.get(2) || 204 != myList.get(7))
			fail("Invalid getting method");
	}

	/**
	 * Test method for {@link lists.MyArrayList#add(int, java.lang.Object)}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddAtIndexException() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(1, 200);
	}

	/**
	 * Test method for {@link lists.MyArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddAtIndexEmpty() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(0, 20);
		myList.add(1, 30);
		myList.add(1, 40);
		myList.add(3, 50);
		myList.add(0, 60);
		myList.add(4, 70);
		assertEquals("Invalid size", 6, myList.size());
		if (60 != myList.get(0) || 50 != myList.get(myList.size()-1) )
			fail("Invalid getting method");
		assertEquals("[60, 20, 40, 30, 70, 50]", myList.toString());
	}

	/**
	 * Test method for {@link lists.MyArrayList#add(java.lang.Object)}.
	 */
	@Test (expected = NullPointerException.class)
	public void testAddNull() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.acceptNulls(false);
		myList.add(15);
		myList.add(null);
		if (null != myList.get(1))
			fail("Invalid getting method");
	}
	/**
	 * Test method for {@link lists.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddInteger() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(15);
		myList.add(1);
		myList.add(92);
		myList.add(42);
		assertEquals("Invalid size", 4, myList.size());
		if (15 != myList.get(0) || 42 != myList.get(3))
			fail("Invalid getting method");
		if (42 != myList.get(myList.size() - 1))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddString() {
		MyArrayList<String> myList = new MyArrayList<String>();
		myList.add("15");
		myList.add("1");
		myList.add("92");
		myList.add("42");
		assertEquals("Invalid size", 4, myList.size());
		if ("15" != myList.get(0) || "42" != myList.get(3))
			fail("Invalid getting method");
		if ("42" != myList.get(myList.size() - 1))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddChar() {
		MyArrayList<Character> myList = new MyArrayList<Character>();
		myList.add('5');
		myList.add('1');
		myList.add('2');
		myList.add('4');
		assertEquals("Invalid size", 4, myList.size());
		if ('5' != myList.get(0) || '2' != myList.get(2))
			fail("Invalid getting method");
		if ('4' != myList.get(myList.size() - 1))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.MyArrayList#addAll(utils.List)}.
	 */
	@Test
	public void testAddAllInteger() {
		MyArrayList<Integer> myList1 = new MyArrayList<Integer>();
		myList1.add(10);
		myList1.add(20);

		MyArrayList<Integer> myList2 = new MyArrayList<Integer>();
		myList2.add(12);
		myList2.add(21);
		myList2.add(32);

		// add list 2 to original list
		myList1.addAll(myList2);
		assertEquals("Invalid size", 5, myList1.size());
		if (10 != myList1.get(0) || 12 != myList1.get(2))
			fail("Invalid getting method");
		if (32 != myList1.get(myList1.size() - 1))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.MyArrayList#addAll(utils.List)}.
	 */
	@Test
	public void testAddAllString() {
		MyArrayList<String> myList1 = new MyArrayList<String>();
		myList1.add("10");
		myList1.add("20");

		MyArrayList<String> myList2 = new MyArrayList<String>();
		myList2.add("12");
		myList2.add("21");
		myList2.add("32");

		// add list 2 to original list
		myList1.addAll(myList2);
		assertEquals("Invalid size", 5, myList1.size());
		if (!"10".equals(myList1.get(0)) || !"12".equals(myList1.get(2)))
			fail("Invalid getting method");
		if (!"32".equals(myList1.get(myList1.size() - 1)))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.MyArrayList#get(int)}.
	 */
	@Test
	public void testGetInteger() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(10);
		myList.add(20);
		myList.add(12);
		myList.add(32);

		if (10 != myList.get(0) || 12 != myList.get(2))
			fail("Invalid getting method");
		if (32 != myList.get(myList.size() - 1))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.MyArrayList#get(int)}.
	 */
	@Test
	public void testGetString() {
		MyArrayList<String> myList1 = new MyArrayList<String>();
		myList1.add("10");
		myList1.add("20");
		myList1.add("12");
		myList1.add("32");

		if (!"10".equals(myList1.get(0)) || !"12".equals(myList1.get(2)))
			fail("Invalid getting method");
		if (!"32".equals(myList1.get(myList1.size() - 1)))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.MyArrayList#clear()}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetExeption() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>(10);
		myList.get(0);
	}

	/**
	 * Test method for {@link lists.MyArrayList#remove(int)}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveExeption() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>(10);
		myList.remove(0);
	}

	/**
	 * Test method for {@link lists.MyArrayList#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveWhenEmpty() {
		MyArrayList<String> myList = new MyArrayList<String>(10);
		assertEquals( null,myList.remove("hello"));
	}

	/**
	 * Test method for {@link lists.MyArrayList#remove(java.lang.Object)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveByElementExeption2() {
		MyArrayList<String> myList = new MyArrayList<String>(10);
		myList.remove("car");
		myList.remove(null);
	}

	/**
	 * Test method for {@link lists.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveByIndex() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(10);
		myList.add(20);
		myList.add(12);
		myList.add(32);

		// only left side gets executed
		if (!myList.remove(1).equals(20) && 12 != myList.remove(1))
			fail("Invalid remove method");
		if (32 != myList.get(myList.size() - 1))
			fail("Invalid storing method");
		assertEquals("Invalid size", 3, myList.size());
	}

	/**
	 * Test method for {@link lists.MyArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveByIndex0() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(10);
		if (!myList.remove(0).equals(10))
			fail("Invalid remove method");

		assertEquals("Invalid size", 0, myList.size());
	}

	/**
	 * Test method for {@link lists.MyArrayList#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveByIntetger() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(20);
		myList.add(10);
		myList.add(12); 
		myList.add(32);
		myList.add(110);

		if (10 != myList.remove(new Integer(10)))
			fail("Invalid remove method");
		assertEquals("Invalid size", 4, myList.size());
		if (110 != myList.remove(new Integer(110)))
			fail("Invalid remove method");
		assertEquals("Invalid size", 3, myList.size());

	}

	/**
	 * Test method for {@link lists.MyArrayList#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveByString() {
		MyArrayList<String> myList = new MyArrayList<String>();
		myList.add("20");
		myList.add("Hola");
		myList.add("12");
		myList.add("32");
		myList.add("110");

		if (!myList.remove("Hola").equals("Hola"))
			fail("Invalid remove method");
		assertEquals("Invalid size", 4, myList.size());
		if (!myList.remove("110").equals("110"))
			fail("Invalid remove method");
		assertEquals("Invalid size", 3, myList.size());
	}

	/**
	 * Test method for {@link lists.MyArrayList#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSet() {
		MyArrayList<String> myList = new MyArrayList<String>();
		myList.add("20");
		myList.add("110");
		myList.add("12");
		myList.add("32");

		assertEquals("110", myList.set(1, "Hola"));
		assertEquals("Invalid size", 4, myList.size());
		assertEquals("Hola", myList.get(1));

	}

	/**
	 * Test method for {@link lists.MyArrayList#set(int, java.lang.Object)}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetException() {
		MyArrayList<String> myList = new MyArrayList<String>();
		assertEquals("110", myList.set(1, "Hola"));
	}

	/**
	 * Test method for {@link lists.MyArrayList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		MyArrayList<String> myList = new MyArrayList<String>();
		assertEquals(true, myList.isEmpty());
		myList.add("Hola");
		assertEquals(false, myList.isEmpty());
	}

	/**
	 * Test method for {@link lists.MyArrayList#contains(java.lang.Object)}.
	 */
	@Test
	public void testContainsException() {
		MyArrayList<String> myList = new MyArrayList<String>();
		assertEquals(false, myList.contains("casa"));
	}

	/**
	 * Test method for {@link lists.MyArrayList#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		MyArrayList<String> myList = new MyArrayList<String>();
		myList.add("Hola");
		assertEquals(false, myList.contains("casa"));
		assertEquals(true, myList.contains("Hola"));
	}

	/**
	 * Test method for {@link lists.MyArrayList#toArray(E[])}.
	 */
	@Test
	public void testToArrayEArray() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(20);
		myList.add(10);
		myList.add(12);
		myList.add(32);
		myList.add(110);

		Integer[] newList = new Integer[5];
		myList.toArray(newList);

		if (32 != newList[3] || 110 != newList[4])
			fail("invalid method");
	}

	/**
	 * Test method for {@link lists.MyArrayList#toArray(E[])}.
	 */
	@Test
	public void testToArraySmall() {
		MyArrayList<Object> myList = new MyArrayList<Object>();
		myList.add(20);
		myList.add(10);
		myList.add(12);
		myList.add(32);
		myList.add(110);

		Object[] newList = new Integer[3];
		newList = myList.toArray(newList);

		if (!newList[3].equals(32) || !newList[4].equals(110))
			fail("invalid method");
	}
	
	/**
	 * Test method for {@link lists.MyArrayList#toArray(E[])}.
	 */
	@Test(expected = NullPointerException.class)
	public void testToArraynull() {
		MyArrayList<Object> myList = new MyArrayList<Object>();
		myList.add(20);
		myList.add(10);
		myList.add(12);
		myList.add(32); 
		myList.add(110);

		Object[] newList = new Integer[3];
		newList = myList.toArray(null);

		if (!newList[3].equals(32) || !newList[4].equals(110))
			fail("invalid method");
	}

	/**
	 * Test method for {@link lists.MyArrayList#toArray()}.
	 */
	@Test
	public void testToArray() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(20);
		myList.add(10);
		myList.add(12);
		myList.add(32);
		myList.add(110);

		Object[] newlist = myList.toArray();
		if (newlist.length != 5)
			fail("invalid method");
	}
 
	/**
	 * Test method for {@link lists.MyArrayList#iterator()}.
	 */
	@Test
	public void testIterator() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(20);
		myList.add(10);
		myList.add(12);
		myList.add(32);
		myList.add(110); 

		Iterator<Integer> itr = myList.iterator();
		int i = 0;
		while (itr.hasNext()) {
			assertEquals(myList.get(i++), itr.next());
		}
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public void testToString() {
		MyArrayList<Integer> myList = new MyArrayList<Integer>();
		myList.add(20);
		myList.add(10);
		myList.add(12);
		myList.add(32);
		myList.add(110);
		assertEquals("[20, 10, 12, 32, 110]", myList.toString());

	}

}
