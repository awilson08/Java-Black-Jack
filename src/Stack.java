

/*(20 points)
2. Implement a Stack using java generics rather than upcasting to Object. You are free to choose the
 underlying data structure on which the Stack is built. Don't extend
 any existing Collections classes, just build the Stack from scratch,
 supporting the following typical Stack operations; push(), pop(), peek(). Include exception handling (i.e. 
have a few of the methods throw one or more exceptions when appropriate, for example trying to pop an empty Stack).

Create a JUnit test to test the operations in your stack.



*/
public class Stack<T> {
	private sNode<T> current;
	private sNode<T> first;
	
	int nSize=0;


	public void push(T info){ //add to end
		sNode<T> end = new sNode<T>(info, null);
		if (first == null){
			first = end;
		}
		else{
			sNode<T> temp = nodeAt(nSize-1);
			temp.next = end;
		}
		nSize++;

	}
	
	public sNode<T> pop(){ //Removes the object at the top of this stack and returns that object as the value of this function.
		sNode<T> returned;
		//if size<0 throw exception
		if(nSize<0){
			throw new NullPointerException("list is empty");
		}
		
		//if size =1, set front to null
		else if(nSize ==1){
			returned = new sNode<T>(first.data, first.next);
			first = null;
			nSize--;
			
		}
		else{ //if more than one node set node at size-1 to link to null
			returned = new sNode<T>(nodeAt(nSize-1).data, nodeAt(nSize-1).next);
			nodeAt(nSize-1).setValue(null);
			nodeAt(nSize-2).setNext(null);
			
		}
		nSize--;
		return returned;
	}
	
	public sNode<T> peek(){ //Looks at the object at the top of this stack without removing it from the stack.
		return nodeAt(nSize-1);
	}
	
	 public sNode<T> nodeAt(int index){
		current = (sNode<T>) first;
		if (first == null) {
			throw new NullPointerException("index does not exist");
		}
		else{
		for (int i =0; i<index; i++){
			current = current.next;
		}
		return current;
		}
	 }
	 
	 public void printNodes(Stack<T> S){
		 S.current = (sNode<T>) S.first;
		 while (S.current != null){
			 System.out.println(S.current.data);
		 S.current = S.current.next;
		 }
	 }
	
	
	//inner class
	
	@SuppressWarnings("hiding")
	class sNode<T>{
		public T data;
		public sNode<T> next;
		
	sNode(T sData, sNode<T> sNext){
		data = sData;
		next = sNext;
	}
	
	T getValue() {
		return data;
	}

	sNode<T> getNext() {
		return next;
	}

	void setValue(T data) {
		this.data = data;
	}

	void setNext(sNode<T> next) {
		this.next = next;
	
	}
	
	}//end inner class

}//end class

