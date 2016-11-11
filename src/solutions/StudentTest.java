package solutions;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class StudentTest {

	@Test
	public void test1() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("10");
		t.insert("11");
		t.insert("111");
		t.insert("1111");
		t.printTree(t.root);
		System.out.println();
	}
	
	@Test
	public void test2() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("100");
		t.insert("101");
		t.insert("10");
		t.printTree(t.root);
	}
	@Test
	public void test3() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("100");
		t.insert("101");
		t.insert("0");
		t.getLongest();
		t.printTree(t.root);
	}
	@Test
	public void test4() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("100");
		t.insert("101");
		t.insert("0");
		t.insert("10111");
		t.insert("1011");
		t.insert("1010");
		t.insert("1");
		t.insert("1111");
		t.insert("110");
		t.getLongest();
		t.printTree(t.root);
	}
	
	@Test
	public void test5() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("1000");
		t.insert("1010");
		t.insert("00");
		t.insert("101110");
		t.insert("10110");
		t.insert("10100");
		t.insert("10");
		t.insert("11110");
		t.insert("1100");
		t.insert("0");
		t.printTree(t.root);
	}
	
	@Test
	public void test6() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("10");
		t.insert("11");
		t.insert("100");
		t.insert("101");
		t.delete("100");
		t.delete("101");
		t.printTree(t.root);
	}
	@Test
	public void test7() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("10");
		t.insert("11");
		t.insert("100");
		t.insert("1010");
		t.insert("1011");
		t.delete("1010");
		t.delete("1011");
		t.printTree(t.root);
	}
	@Test
	public void test8() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("0");
		t.insert("1");
		t.delete("1");
		t.printTree(t.root);
	}
	
	@Test
	public void test9() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("10");
		t.insert("11");
		t.insert("100");
		t.insert("1010");
		t.insert("1011");
		t.insert("10110");
		t.insert("10111");
		t.delete("1010");
		t.delete("1011");
		t.delete("11");
		t.printTree(t.root);
	}
	@Test
	public void test10() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();

		t.insert("10");
		t.insert("11");
		t.insert("100");
		t.insert("1010");
		t.insert("1011");
		t.insert("10110");
		t.insert("10111");
		Iterator<String> iter =  t.inOrderTraversal();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
	}
	@Test
	public void test11() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("1000");
		t.insert("1001");
		t.insert("11");
		t.insert("101");
		
		t.printTree(t.root);
		
	}
	@Test
	public void test12() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("1000");
		t.insert("1001");
		t.insert("0");
		
		t.printTree(t.root);
		
	}
	@Test
	public void test13() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("00001");
		t.insert("010010");
		t.insert("010111");
		t.insert("011011");
		t.insert("011101");
		t.insert("011111");
		t.insert("10010");
		t.insert("11011");
		t.insert("10110");
		
		t.printTree(t.root);
		
	}
	@Test
	public void test14() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("000000");
		t.insert("00000");
		t.insert("0000");
		t.insert("000");
		t.insert("00");
		t.insert("0");
		t.delete("0");
		t.delete("00");
		t.delete("000");
		t.delete("0000");
		t.delete("00000");
		
		t.printTree(t.root);
		
	}	
	@Test
	public void test15() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("000");
		t.insert("001");
		t.insert("00");	
		
		t.printTree(t.root);
		
	}
	
	@Test
	public void test16() {
		BinaryPatriciaTrie t = new BinaryPatriciaTrie();
		
		t.insert("000000000000000000");	
		t.insert("0");	
		
		t.printTree(t.root);
		
	}
	
}
