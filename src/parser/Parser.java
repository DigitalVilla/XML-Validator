package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.EmptyQueueException;
import lists.MyArrayList;
import lists.MyQueue;
import lists.MyStack;
import utils.Iterator;

/**
 *This is a simple parser to test the implementation and functionality of MyArray, MyQueue and MyStack. 
 *It takes the path file for an xml file and it prompts a massage if the code is valid or not. 
 *It also checks for equality in tag names. 
 *
 */
public class Parser {

	private MyQueue<Character> errorQ;
	private MyQueue<Character> extrasL;
	private MyStack<Character> stack;

	private MyArrayList<myTag> tags;
	private MyArrayList<myTag> endtags;
	private MyArrayList<myTag> selftags;

	private MyArrayList<String> errorLog;

	public Parser() {
		console();
	}

	/**
	 * the core loop that maintains a running program in the console. 
	 */
	private void console() {
		try {
			String next = "y";
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			while (next.equals("y")) {
				int line = 0;
				tags = new MyArrayList<myTag>();
				endtags = new MyArrayList<myTag>();
				selftags = new MyArrayList<myTag>();
				stack = new MyStack<Character>();
				errorQ = new MyQueue<Character>();
				extrasL = new MyQueue<Character>();
				errorLog = new MyArrayList<String>();
				System.out.print("\nEnter File to parse: ");
				
				File myfile = new File(in.readLine());

				if (!myfile.exists()) {
					System.out.println("Invalid File Path!\n");
					continue;
				}

				BufferedReader read = new BufferedReader(new FileReader(myfile));
				while (read.ready() && ++line != 0) {
					parse(read.readLine(), line);
				}

				validateTags();

				read.close();
				System.out.print("\nValidate another file ? Y/N: ");
				next = in.readLine().toLowerCase();
				if (!next.equals("y"))
					System.out.print("\nGood bye!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * it validates one line of xml. 
	 * @param xml the string containing one line of xml text
	 * @param line the line number of this string
	 */
	public void parse(String xml, int line) {
		try {
			String str = "";

//			find start tags
			int startTag1 = xml.indexOf("<");
			// this is not a closing tag or xml title
			if (startTag1 > -1 && xml.charAt(startTag1 + 1) != '/' && !xml.contains("?xml")) {
				int startTag2 = xml.indexOf(">", startTag1);
				if (startTag2 > 0) {
					int whitespace = xml.indexOf(" ", startTag1);

					if (xml.charAt(startTag2 - 1) != '/') {
						str = (whitespace > -1 && whitespace < startTag2) ? xml.substring(startTag1, whitespace) + '>'
								: xml.substring(startTag1, startTag2 + 1);
						tags.add(new myTag(str, line));
					} else {
						str = (whitespace > -1 && whitespace < startTag2) ? xml.substring(startTag1, whitespace) + "/>"
								: xml.substring(startTag1, startTag2 + 1);
						selftags.add(new myTag(str, line));

					}
				} else {
					errorLog.add("Tag was not properely closed at line " + line);
				}
			}

			int endTag1 = xml.indexOf("</");
			str = "";
			if (endTag1 > -1) {
				int endTag2 = xml.indexOf(">", endTag1);
				if (endTag2 > 0) {
					str = xml.substring(endTag1, endTag2 + 1);
					endtags.add(new myTag(str, line));
				} else {
					errorLog.add("Tag was not properely closed at line " + line);
				}
			}
//				find closing tags

			int startTag = -4;

			for (int i = 0; i < xml.length(); i++) {
				// if star-tag push on Stack

				if (xml.charAt(i) == '<') {
					stack.push(xml.charAt(i));

					if (i - startTag <= 3)
						errorLog.add("Duplicate tag < at line " + line);

					int limit = xml.indexOf('>', i);
					if (limit < 0)
						errorLog.add("Missing tag > at line " + line);

					startTag = i;
					// if closing tag is found...
				} else if (xml.charAt(i) == '>') {
					// if stack has a match it is a valid tag so pop stack
					if (!stack.isEmpty() && stack.peek() == '<')
						stack.pop();
					// if tag-match is found in errorQ instead ignore and dequeue
					else if (!errorQ.isEmpty() && errorQ.peek() == '<')
						errorQ.dequeue();
					// if stack is empty add it to errorQ
					else if (stack.isEmpty()) {
						errorQ.enqueue(xml.charAt(i));
						errorLog.add("Duplicate tag > at line " + line);
					}
					// if start-tag is found within stack
					else if (stack.contains('<')) {
						// prepare new element
						char err;
						// pop each element from stack into errorQ
						while ((err = stack.pop()) != '<') {
							// continue until match is found
							errorQ.enqueue(err);
						}
						errorQ.enqueue(err);
//						errorLog.add("Wrong tag at line " + line);
						// else add error to extras
					} else {
						errorLog.add("Invalid tag at line " + line);
						extrasL.enqueue(xml.charAt(i));
					}
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}

	/**
	 * it validates the stacks and queues at the end of the parsing process and looks for duplicates.
	 * @throws NullPointerException
	 * @throws EmptyQueueException
	 */
	private void validateTags() throws NullPointerException, EmptyQueueException {
		boolean valid = true;
		if (!stack.isEmpty()) {
			while (!stack.isEmpty())
				errorQ.enqueue(stack.pop());
			valid = false;
		}
		if ((!extrasL.isEmpty() && errorQ.isEmpty()) || (extrasL.isEmpty() && !errorQ.isEmpty())) {
			while (!extrasL.isEmpty())
				errorQ.enqueue(extrasL.dequeue());
			valid = false;
		}
		while (!extrasL.isEmpty() && !errorQ.isEmpty()) {
			if (extrasL.peek() == errorQ.peek()) {
				extrasL.dequeue();
				errorQ.dequeue();
			} else {
				if (!extrasL.isEmpty())
					errorLog.add("Error tag: " + extrasL.dequeue());
				if (!errorQ.isEmpty())
					errorLog.add("Error tag: " + errorQ.dequeue());
			}
		}
		valid = validateNames();
		System.out.println("\n\t**This XML is " + ((!valid)?"NOT valid.":"valid.")+"**\n");
		if (!valid) {
//			System.out.println("Error brackets: " + errorQ);
			printLog();		
		}
		
		
		
	}

	/**
	 * Prints the contents of the error log to the console
	 */
	private void printLog() {
		for (int i = 0; i < errorLog.size(); i++) {
			System.out.println(errorLog.get(i));
		}
	}


	/**
	 * prints the contents of a stack to the console
	 * @param tags
	 * @return
	 */
	private String print(MyStack<myTag> tags) {
		Iterator<myTag> iter = tags.iterator();
		String str = "[";
		while (iter.hasNext()) {
			str += iter.next().tag + " ";
		}
		return str += "]";
	}

	/**
	 * validates the tag names and check for name equality.
	 * @return
	 */
	private boolean validateNames() {
		sort(tags);
		sort(endtags);
		MyStack<myTag> errorTags = new MyStack<myTag>();

		while (tags.size() > 0) {
			int found = 0;
			if (!endtags.isEmpty()) {
				if (tagEquals(tags.get(0).tag, endtags.get(0).tag)) {
					tags.remove(0);
					endtags.remove(0);
				} else if ((found = find(endtags, tags.get(0).tag)) > 0) {
					tags.remove(0);
					endtags.remove(found);
				} else {
					errorTags.push(tags.remove(0));
				}
			}
		}
		while (endtags.size() > 0) {
			errorTags.push(endtags.remove(0));
		}
		
		if (!errorTags.isEmpty()) {
//			System.out.println("EndTags: " + print(errorTags));
			while(!errorTags.isEmpty()) {
				String tag = errorTags.peek().tag;
				int line = errorTags.pop().line;
				errorLog.add("Wrong tag "+tag+" at line "+line);
			}
			return false;
		}
		return true;
	}

	/**
	 * looks for a match in a list
	 * @param list
	 * @param tag
	 * @return
	 */
	private int find(MyArrayList<myTag> list, String tag) {
		for (int i = 0; i < list.size(); i++) {
			if (tagEquals(list.get(i).tag, tag))
				return i;
		}
		return -1;
	}

	/**
	 * checks for element equality
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean tagEquals(String a, String b) {
		return a.replaceAll("[\\W]", "").equals(b.replaceAll("[\\W]", ""));
	}

	/**
	 * sorts the contents of the list
	 * @param list
	 */
	public void sort(MyArrayList<myTag> list) {
		int right = list.size() - 1;
		int left = 0;

		for (int i = left, j = i; i < right; j = ++i) {
			myTag temp = list.get(i + 1);
			while (temp.tag.compareTo(list.get(j).tag) < 0) {
				list.set(j + 1, list.get(j));
				if (j-- == left) {
					break;
				}
			}
			list.set(j + 1, temp);
		}

	}

	/**
	 * class to hold the tag element and the line where it is found
	 *
	 */
	public class myTag {
		String tag;
		int line;

		myTag(String tag, int line) {
			this.tag = tag;
			this.line = line;
		}

	}
}
