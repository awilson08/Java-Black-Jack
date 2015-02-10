import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class StackTest {

	Stack<String> testStack = null;

	@Before
	public void setUp() throws Exception {
		testStack = new Stack<String>();
	}

	@After
	public void tearDown() throws Exception {
		testStack = null;
	}

	@Test
	public void testInitialSize() {
		Assert.assertEquals(0, testStack.nSize);
	}
	
	@Test
	public void testPushFourNodes(){
		testStack.push("1");
		testStack.push("2");
		testStack.push("3");
		testStack.push("4");
		//testStack.printNodes(testStack);
		Assert.assertEquals(4, testStack.nSize);
	}
	
	@Test
	public void testPop(){
		System.out.println("*******Test Pop BEFORE*******");
		testPushFourNodes();
		testStack.printNodes(testStack);
		//System.out.println("***");
		//System.out.println(testStack.nodeAt(3).data);
		
		System.out.println("Item in stack removed: " + testStack.pop().data);
		
		System.out.println("*******Test Pop AFTER*******");
		testStack.printNodes(testStack);
		System.out.println();
		Assert.assertEquals(3, testStack.nSize);
	}
	
	@Test
	public void testPeek(){
		testPushFourNodes();
		System.out.println("*****Test Peek Before*****");
		testStack.printNodes(testStack);
		
		System.out.println("Peek Node: " + testStack.peek().data);
		System.out.println("*****Test Peek Before- Verify Same List*****");
		testStack.printNodes(testStack);
		Assert.assertEquals(4, testStack.nSize);
		Assert.assertEquals("4", testStack.peek().data);
	}
	
	
	
}
