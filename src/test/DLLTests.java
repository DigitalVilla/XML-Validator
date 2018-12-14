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
import utils.Iterator;

/**
 * @author 767110
 *
 */
public class DLLTests {

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

	private DLL<Object> list;
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
		list = new DLL<Object>();
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
	 * Test method for {@link lists.DLL#size()}.
	 */
	@Test
	public void testSize() {
		list.add("hola");
		assertEquals(1, list.size());
	}

	/**
	 * Test method for {@link lists.DLL#add(int,java.lang.Object )}.
	 */
	@Test
	public void testAddToIndex() {
		assertTrue(list.add(0, b));
		assertTrue(list.add(1, a));
		assertTrue(list.add(2, d));
		assertTrue(list.add(2, c));
		assertTrue(list.add(0, a));
		assertEquals(5, list.size());
		assertEquals("[A, B, A, C, D]", list.toString());
		if (!list.get(1).equals("B") || !list.get(3).equals("C") || !list.get(0).equals("A"))
			fail("Invalid remove method");
	}

	/**
	 * Test method for {@link lists.DLL#add(int,java.lang.Object )}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddToIndexException() {
		assertTrue(list.add(1, b));
	}

	/**
	 * Test method for {@link lists.DLL#add(int,java.lang.Object )}.
	 */
	@Test
	public void testAddToIndexEmpty() {
		assertTrue(list.add(0, a));
		assertEquals(1, list.size());
		assertEquals("[A]", list.toString());
	}

	/**
	 * Test method for {@link lists.DLL#clear()}.
	 */
	@Test
	public void testClear1() {
		assertTrue(list.add(a));
		assertTrue(list.add(b));
		assertTrue(list.add(c));
		list.clear();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link lists.DLL#add(java.lang.Object)}.
	 */
	@Test
	public void testAddString1() {
		assertTrue(list.add(a));
		assertTrue(list.add(b));
		assertTrue(list.add(c));
		assertTrue(list.add(d));
		assertEquals(4, list.size());
		assertEquals("[A, B, C, D]", list.toString());
	}

	/**
	 * Test method for {@link lists.DLL#add(java.lang.Object)}.
	 */
	@Test
	public void testAddInteger1() {
		assertTrue(list.add(1));
		assertTrue(list.add(2));
		assertTrue(list.add(3));
		assertTrue(list.add(4));
//		.out.println(list);
		assertEquals(4, list.size());
		assertEquals("[1, 2, 3, 4]", list.toString());
	}

	/**
	 * Test method for {@link lists.DLL#get(int)}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetException() {
		list.get(0);
	}

	/**
	 * Test method for {@link lists.DLL#get(int)}.
	 */
	@Test
	public void testGet() {
		assertTrue(list.add(a));
		assertEquals("A", list.get(0));
		assertTrue(list.add(b));
		assertEquals("B", list.get(1));
		assertTrue(list.add(c));
		assertTrue(list.add(d));
		assertEquals("D", list.get(3));
		assertEquals(4, list.size());
		assertEquals("[A, B, C, D]", list.toString());
	}

	/**
	 * Test method for {@link lists.DLL#addAll(utils.List)}.
	 */
	@Test
	public void testAddAll() {
		DLL<Integer> arr = new DLL<Integer>();
		list.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		list.addAll(arr);
		assertEquals(4, list.size());
		assertEquals("[1, 2, 3, 4]", list.toString());
		if (!list.get(1).equals(2) || !list.get(3).equals(4) || !list.get(0).equals(1))
			fail("Invalid remove method");
	}

	/**
	 * Test method for {@link lists.DLL#remove(int)}.
	 */
	@Test
	public void testRemoveIndexTail() {
		list.add(1);
		list.add(2);
		list.add(3);

		list.remove(0);
		assertEquals(2, list.size());
		assertEquals("[2, 3]", list.toString());
	}

	/**
	 * Test method for {@link lists.DLL#remove(int)}.
	 */
	@Test
	public void testRemoveIndexHead() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		list.remove(3);
		assertEquals(3, list.size());
		assertEquals("[1, 2, 3]", list.toString());
	}

	/**
	 * Test method for {@link lists.DLL#remove(int)}.
	 */
	@Test
	public void testRemoveIndex() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);

		list.remove(2);
		assertEquals(6, list.size());
		assertEquals("[1, 2, 4, 5, 6, 7]", list.toString());
		list.remove(2);
		assertEquals(5, list.size());
		assertEquals("[1, 2, 5, 6, 7]", list.toString());

		if (!list.get(2).equals(5) || !list.remove(3).equals(6))
			fail("Invalid remove method");
	}

	/**
	 * Test method for {@link lists.DLL#remove(int)}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveIndexException() {
		list.add(1);
		list.remove(2);
	}

	/**
	 * Test method for {@link lists.DLL#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveE() {
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);

		assertEquals("B", list.remove(b));
		assertEquals(3, list.size());

		assertEquals("A", list.remove(a));
		assertEquals(2, list.size());

		assertEquals("[C, D]", list.toString());

		assertEquals("D", list.remove(d));
		assertEquals(1, list.size());

		assertEquals("C", list.remove(c));
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link lists.DLL#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveENULL() {
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);

		assertEquals("B", list.remove(b));
		assertEquals(3, list.size());

		assertEquals("A", list.remove(a));
		assertEquals(2, list.size());

		assertEquals("[C, D]", list.toString());

		assertEquals("D", list.remove(d));
		assertEquals(1, list.size());

		assertEquals("C", list.remove(c));
		assertEquals(0, list.size());

		assertEquals(null, list.remove(c));
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link lists.DLL#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSet1() {
		DLL<String> list = new DLL<String>();
		list.add("20");
		list.add("110");
		list.add("12");
		list.add("32");

		assertEquals("110", list.set(1, "Hola"));
		assertEquals("Invalid size", 4, list.size());
		assertEquals("Hola", list.set(1, "vamos"));
		assertEquals("vamos", list.get(1));
		assertEquals("Invalid size", 4, list.size());
	}

	/**
	 * Test method for {@link lists.DLL#isEmpty()}.
	 */
	@Test
	public void testIsEmpty1() {
		assertEquals(true, list.isEmpty());
		list.add(d);
		assertEquals(false, list.isEmpty());
	}

//-----------------------------------------------------------------------------
	/**
	 * Test method for {@link lists.DLL#clear()}.
	 */
	@Test
	public void testClear() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(15);
		list.add(92);
		list.add(42);
		list.clear();
		assertEquals("Clear is not deleting", 0, list.size());
	}

	/**
	 * Test method for {@link lists.DLL#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddAtIndex() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(15);
		list.add(92);
		list.add(42);
		list.add(1, 33);
		list.add(1, 200);
		assertEquals("Invalid size", 5, list.size());
		if (15 != list.get(0) || 42 != list.get(4))
			fail("Invalid getting method");
		if (200 != list.get(1))
			fail("Invalid storing method");
		assertEquals("[15, 200, 33, 92, 42]", list.toString());
	}

	/**
	 * Test method for {@link lists.DLL#add(int, java.lang.Object)}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddAtIndexException() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(1, 200);
	}

	/**
	 * Test method for {@link lists.DLL#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddAtIndexEmpty() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(0, 20);
		list.add(1, 30);
		list.add(1, 40);
		list.add(3, 50);
		list.add(0, 60);
		list.add(4, 70);
		assertEquals("Invalid size", 6, list.size());
		if (60 != list.get(0) || 50 != list.get(list.size() - 1))
			fail("Invalid getting method");
		assertEquals("[60, 20, 40, 30, 70, 50]", list.toString());
	}

	/**
	 * Test method for {@link lists.DLL#add(java.lang.Object)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testAddNull() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(15);
		list.add(null);
		if (null != list.get(1))
			fail("Invalid getting method");
	}

	/**
	 * Test method for {@link lists.DLL#add(java.lang.Object)}.
	 */
	@Test
	public void testAddInteger() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(15);
		list.add(1);
		list.add(92);
		list.add(42);
		assertEquals("Invalid size", 4, list.size());
		if (15 != list.get(0) || 42 != list.get(3))
			fail("Invalid getting method");
		if (42 != list.get(list.size() - 1))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.DLL#add(java.lang.Object)}.
	 */
	@Test
	public void testAddString() {
		DLL<String> list = new DLL<String>();
		list.add("15");
		list.add("1");
		list.add("92");
		list.add("42");
		assertEquals("Invalid size", 4, list.size());
		if ("15" != list.get(0) || "42" != list.get(3))
			fail("Invalid getting method");
		if ("42" != list.get(list.size() - 1))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.DLL#add(java.lang.Object)}.
	 */
	@Test
	public void testAddChar() {
		DLL<Character> list = new DLL<Character>();
		list.add('5');
		list.add('1');
		list.add('2');
		list.add('4');
		assertEquals("Invalid size", 4, list.size());
		if ('5' != list.get(0) || '2' != list.get(2))
			fail("Invalid getting method");
		if ('4' != list.get(list.size() - 1))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.DLL#addAll(utils.List)}.
	 */
	@Test
	public void testAddAllInteger() {
		DLL<Integer> list1 = new DLL<Integer>();
		list1.add(10);
		list1.add(20);

		DLL<Integer> list2 = new DLL<Integer>();
		list2.add(12);
		list2.add(21);
		list2.add(32);

		// add list 2 to original list
		list1.addAll(list2);
		assertEquals("Invalid size", 5, list1.size());
		if (10 != list1.get(0) || 12 != list1.get(2))
			fail("Invalid getting method");
		if (32 != list1.get(list1.size() - 1))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.DLL#addAll(utils.List)}.
	 */
	@Test
	public void testAddAllString() {
		DLL<String> list1 = new DLL<String>();
		list1.add("10");
		list1.add("20");

		DLL<String> list2 = new DLL<String>();
		list2.add("12");
		list2.add("21");
		list2.add("32");

		// add list 2 to original list
		list1.addAll(list2);
		assertEquals("Invalid size", 5, list1.size());
		if (!"10".equals(list1.get(0)) || !"12".equals(list1.get(2)))
			fail("Invalid getting method");
		if (!"32".equals(list1.get(list1.size() - 1)))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.DLL#get(int)}.
	 */
	@Test
	public void testGetInteger() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(10);
		list.add(20);
		list.add(12);
		list.add(32);

		if (10 != list.get(0) || 12 != list.get(2))
			fail("Invalid getting method");
		if (32 != list.get(list.size() - 1))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.DLL#get(int)}.
	 */
	@Test
	public void testGetString() {
		DLL<String> list1 = new DLL<String>();
		list1.add("10");
		list1.add("20");
		list1.add("12");
		list1.add("32");

		if (!"10".equals(list1.get(0)) || !"12".equals(list1.get(2)))
			fail("Invalid getting method");
		if (!"32".equals(list1.get(list1.size() - 1)))
			fail("Invalid storing method");
	}

	/**
	 * Test method for {@link lists.DLL#clear()}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetExeption() {
		DLL<Integer> list = new DLL<Integer>();
		list.get(0);
	}

	/**
	 * Test method for {@link lists.DLL#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveByElementExeption() {
		DLL<String> myList = new DLL<String>();
		assertEquals(null, myList.remove("hello"));
	}

	/**
	 * Test method for {@link lists.DLL#remove(java.lang.Object)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveByElementExeption2() {
		DLL<String> myList = new DLL<String>();
		myList.remove("car");
		myList.remove(null);
	}

	/**
	 * Test method for {@link lists.DLL#remove(int)}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveExeption() {
		DLL<Integer> myList = new DLL<Integer>();
		myList.remove(0);
	}

	/**
	 * Test method for {@link lists.DLL#remove(int)}.
	 */
	@Test
	public void testRemoveByIndex() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(10);
		list.add(20);
		list.add(12);
		list.add(32);

		// only left side gets executed
		if (!list.remove(1).equals(20) && 12 != list.remove(1))
			fail("Invalid remove method");
		if (32 != list.get(list.size() - 1))
			fail("Invalid storing method");
		assertEquals("Invalid size", 3, list.size());
	}

	/**
	 * Test method for {@link lists.DLL#remove(int)}.
	 */
	@Test
	public void testRemoveByIndex0() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(10);
		if (!list.remove(0).equals(10))
			fail("Invalid remove method");

		assertEquals("Invalid size", 0, list.size());
	}

	/**
	 * Test method for {@link lists.DLL#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveByIntetger() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(20);
		list.add(10);
		list.add(12);
		list.add(32);
		list.add(110);

		if (10 != list.remove(new Integer(10)))
			fail("Invalid remove method");
		assertEquals("Invalid size", 4, list.size());
		if (110 != list.remove(new Integer(110)))
			fail("Invalid remove method");
		assertEquals("Invalid size", 3, list.size());

	}

	/**
	 * Test method for {@link lists.DLL#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveByString() {
		DLL<String> list = new DLL<String>();
		list.add("20");
		list.add("Hola");
		list.add("12");
		list.add("32");
		list.add("110");

		if (!list.remove("Hola").equals("Hola"))
			fail("Invalid remove method");
		assertEquals("Invalid size", 4, list.size());
		if (!list.remove("110").equals("110"))
			fail("Invalid remove method");
		assertEquals("Invalid size", 3, list.size());
	}

	/**
	 * Test method for {@link lists.DLL#finder(java.lang.Object)}.
	 */
	@Test
	public void testfindElementR() {
		DLL<String> list = new DLL<String>();
		list.add("20");
		list.add("9");
		list.add("12");
		list.add("37");
		list.add("24");
		list.add("11");
		list.add("31");
		list.add("74");
		list.add("42");
		list.add("42");
		list.add("42");
		list.add("8");
		list.add("19");
		list.add("32");
		assertEquals(7, list.finder("74"));
		assertEquals(11, list.finder("8"));
		assertEquals(12, list.finder("19"));
		assertEquals(4, list.finder("24"));
		assertEquals(6, list.finder("31"));
		assertEquals(-1, list.finder("174"));
	}

	/**
	 * Test method for {@link lists.DLL#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSet() {
		DLL<String> list = new DLL<String>();
		list.add("20");
		list.add("110");
		list.add("12");
		list.add("32");

		assertEquals("110", list.set(1, "Hola"));
		assertEquals("Invalid size", 4, list.size());
		assertEquals("Hola", list.get(1));

	}

	/**
	 * Test method for {@link lists.DLL#set(int, java.lang.Object)}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetException() {
		DLL<String> list = new DLL<String>();
		assertEquals("110", list.set(1, "Hola"));
	}

	/**
	 * Test method for {@link lists.DLL#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		DLL<String> list = new DLL<String>();
		assertEquals(true, list.isEmpty());
		list.add("Hola");
		assertEquals(false, list.isEmpty());
	}

	/**
	 * Test method for {@link lists.DLL#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains1() {
		DLL<String> list = new DLL<String>();
		assertEquals(false, list.contains("casa"));
	}

	/**
	 * Test method for {@link lists.DLL#contains(java.lang.Object)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsException2() {
		DLL<String> list = new DLL<String>();
		assertEquals(false, list.contains(null));
	}

	/**
	 * Test method for {@link lists.DLL#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		DLL<String> list = new DLL<String>();
		list.add("Hola");
		assertEquals(false, list.contains("casa"));
		assertEquals(true, list.contains("Hola"));
	}

	/**
	 * Test method for {@link lists.DLL#toArray(E[])}.
	 */
	@Test
	public void testToArrayEArray() {
		DLL<Integer> myList = new DLL<Integer>();
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
	 * Test method for {@link lists.DLL#toArray(E[])}.
	 */
	@Test(expected = NullPointerException.class)
	public void testToArrayEArrayNull() {
		list.toArray(null);
	}

	/**
	 * Test method for {@link lists.DLL#toArray(E[])}.
	 */
	@Test
	public void testToArraySmall() {
		DLL<Object> list = new DLL<Object>();
		list.add(20);
		list.add(10);
		list.add(12);
		list.add(32);
		list.add(110);
		Object[] newList = new Integer[3];
		newList = list.toArray(newList);

		if (!newList[3].equals(32) || !newList[4].equals(110))
			fail("invalid method");
	}

	/**
	 * Test method for {@link lists.DLL#toArray()}.
	 */
	@Test
	public void testToArray() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(20);
		list.add(10);
		list.add(12);
		list.add(32);
		list.add(110);
		Object[] newlist = list.toArray();
		if (newlist.length != 5)
			fail("invalid method");
	}

	/**
	 * Test method for {@link lists.DLL#iterator()}.
	 */
	@Test
	public void testIterator1() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(20);
		list.add(10);
		list.add(12);
		list.add(32);
		list.add(110);
		Iterator<Integer> itr = list.iterator();
		int i = 0;
		while (itr.hasNext()) {
			assertEquals(list.get(i++), itr.next());
		}
	}

	/**
	 * Test method for {@link lists.DLL#iterator()}.
	 */
	@Test
	public void testIterator() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(20);
		list.add(10);
		list.add(12);
		list.add(32);
		list.add(110);
		Iterator<Integer> itr = list.iterator();
		int i = 0;
		while (itr.hasNext()) {
			assertEquals(list.get(i++), itr.next());
		}
	}

	/**
	 * Test method for {@link lists.DLL#iterator()}.
	 */
	@Test
	public void testIteratorEmpty() {
		DLL<Integer> list = new DLL<Integer>();
		Iterator<Integer> itr = list.iterator();
		assertEquals(false, itr.hasNext());
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public void testToString() {
		DLL<Integer> list = new DLL<Integer>();
		list.add(20);
		list.add(10);
		list.add(12);
		list.add(32);
		list.add(110);
		assertEquals("[20, 10, 12, 32, 110]", list.toString());
	}
}
