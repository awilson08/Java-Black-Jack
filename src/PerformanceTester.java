import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;



/*(20 points)
3. Create a normal Java Driver class which tests the performance results of 

a) object insertions (add method) and  
b) object retrievals among Hashtable, ArrayList, and LinkedList.  

It is up to you to define a set of tests that sheds light on the 
underlying performance differences. This should include testing a 
range of collection sizes. Your program should produce a histogram of the test results. 
You may use a JUnit test here as well, but it is not required. 
*/
public class PerformanceTester {

	//@SuppressWarnings("rawtypes")
	
	static Hashtable<Integer, Integer> testHash = new Hashtable<Integer, Integer>();
	static ArrayList<Integer> testArrayList = new ArrayList<Integer>();
	static LinkedList<Integer> testLinkedList = new LinkedList<Integer>();
	
	static int first = Integer.MIN_VALUE;
	static int last = Integer.MIN_VALUE;
	
	static Random R = new Random();
	
	public static void main(String[] args) {
		
		Tests(100);
		//Tests(1000);
		//Tests(100000);
		//Tests(1000000);
		

	}
	
	public static void Tests(int k){
		System.out.println("List Length " + k);
		fillLists(k); //load
		
		//adding number at first index - T1
			//AL
			long start = System.nanoTime(); //AL Start Time
			addToArrayList(0, first);
			long ALT1 = System.nanoTime()- start;
		

			
			//LL
			start = System.nanoTime();
			addToLinkedList(0, first);
			long LLT1 = System.nanoTime() - start; //LL time

			
			//HT
			start = System.nanoTime();
			addToHashTable(0, first);
			long HTT1 = System.nanoTime() - start; //HS time
			
			
			//print results - T1
			System.out.println("****T1 - adding number at first index*****");
			
			printResults(ALT1, LLT1, HTT1);
			
			
		//adding number at last index - T2
			//AL
			start = System.nanoTime();
			addToArrayList(k, last);
			long ALT2 = System.nanoTime() - start;
			
			//LL
			start = System.nanoTime();
			addToLinkedList(k, last);
			long LLT2 = System.nanoTime() - start;
			
			//HT
			start = System.nanoTime();
			addToHashTable(k, last);
			long HTT2 = System.nanoTime() - start;
			
			//print results -T2
			System.out.println("****T2 - adding number at last index*****");
			printResults(ALT2, LLT2, HTT2);
		
		
		//retrieve value at first index - T3
			//AL
			start = System.nanoTime();
			valAtIndexAL(0);
			long ALT3 = System.nanoTime() - start;
		
			//LL
			start = System.nanoTime();
			valAtIndexLL(0);
			long LLT3 = System.nanoTime() - start;
			
			//HT
			start = System.nanoTime();
			valAtIndexHT(0);
			long HTT3 = System.nanoTime() - start;
			
			//print results = T3
			System.out.println("****T3 - retrieve value at first index*****");
			printResults(ALT3, LLT3, HTT3);
		
		//retrieve value at last index -T4
			//AL
			start = System.nanoTime();
			valAtIndexAL(k);
			long ALT4 = System.nanoTime() - start;
			
			//LL
			start = System.nanoTime();
			valAtIndexLL(k);
			long LLT4 = System.nanoTime() - start;
			
			//HT
			start = System.nanoTime();
			valAtIndexHT(k);
			long HTT4 = System.nanoTime() - start;
			
		//print results T4
		System.out.println("****T4 - retrieve value at last index*****");
		printResults(ALT4, LLT4, HTT4);
		
			
		//retrieve at k-1  - T5
			//AL
			start = System.nanoTime();
			valAtIndexAL(k-1);
			long ALT5 = System.nanoTime() - start;
			
			
			//LL
			start = System.nanoTime();
			valAtIndexLL(k-1);
			long LLT5 = System.nanoTime() - start;
			
			
			//HT
			start = System.nanoTime();
			valAtIndexHT(k-1);
			long HTT5 = System.nanoTime() - start;
			
		
		//print results T5
			System.out.println("****T5 - retrieve value at k-1*****");
		printResults(ALT5, LLT5, HTT5);
	
		clearLists(); //clear
	}
	
	public static void printResults(long AL, long LL, long HT){
		
		//I clearly did something wrong with the scaling
		
		long min =  Math.min(AL, Math.min(LL, HT));
		long max =  Math.max(AL, Math.min(LL, HT));
		
		System.out.print("AL: ");
		int Alist = (int) (AL*((AL-min)/(max-min)));
		for(int i = 0; i<Alist; i++){System.out.print("#");}
		System.out.print(" "+ AL + "ns");
		System.out.println();
		
		System.out.print("LL: ");
		int Llist = (int) (LL*((LL-min)/(max-min)));
		for(int i = 0; i<Llist; i++){System.out.print("#");}
		System.out.print(" "+ LL + "ns");
		System.out.println();
		
		System.out.print("HT: ");
		int Hlist = (int) (HT*((HT-min)/(max-min)));
		for(int i = 0; i<Hlist; i++){System.out.print("#");}
		System.out.print(" "+ HT + "ns");
		System.out.println();
	
	}
	
	
	
	public static void fillLists(int k){
		for (int i =0; i<k; i++){
				Integer num = R.nextInt(k);
				testArrayList.add(num);
				testLinkedList.add(num);
				testHash.put(num%k, num);
			
			//System.out.println(testArrayList.get(i));
			//System.out.println("number is " + num + " . Hashcode is " + num%1000);
			}

		}
	public static void clearLists(){
		testArrayList.clear();
		testLinkedList.clear();
		testHash.clear();
	}
	
	public static void addToHashTable(int index, int b){
		testHash.put(index, b);
	}
	
	public static void valAtIndexHT(int index){ //value at index
		testHash.get(index);
	}
	
	public void indexOfValHT(int b){ //index of value
		
	}
	
	public static void addToArrayList(int index, int b){
		testArrayList.add(index, b);
	}
	
	
	public static int valAtIndexAL(int index){
		return testArrayList.get(index);
	}
	
	public static int indexOfValAL(int b){
		return testArrayList.indexOf(b);
	}
	
	public static void addToLinkedList(int index, int b){
		testLinkedList.add(index,b);
	}
	
	
	public static int valAtIndexLL(int index){
		return testLinkedList.get(index);
	}
	
	public static int indexOfValLL(int b){
	 	return testLinkedList.indexOf(b);
	}
	
}//end class
