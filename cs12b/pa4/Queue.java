// Queue.java
// This is the Queue ADT for pa4

public class Queue implements QueueInterface{
	//private inner Node class
	private class Node{
		Object item;
		Node next;

		//constructor for Node class
		Node(Object ob){
			item = ob;
			next = null;
		}
	}

	// Fileds for the Queue class
	private Node head;
	private Node tail;
	private int numItem;

	// constructor for Queue class
	public Queue(){
		head = null;
		tail = null; 
		numItem = 0;
	}

	//functions
	public boolean isEmpty(){
		if(numItem == 0){
			return true;
		}else{
			return false;
		}
	}

	public int length(){
		return numItem;
	}

	public void enqueue(Object newItem){
		if(head == null){ // if it is an empty Node
			head = new Node(newItem); 
			tail = head;
		}else{
			tail.next = new Node(newItem);
			tail = tail.next;	
		}
		numItem++;

	}

	public Object dequeue() throws QueueEmptyException{ // is there a return value for Object?
		if(numItem == 0)
			throw new QueueEmptyException("cannot dequeue() empty queue");
		
			Node temp = head;
			head = head.next;	
			numItem--;
			return temp.item;
	}

	public Object peek() throws QueueEmptyException{
		if(numItem == 0) // same as isEmpty == ture
			throw new QueueEmptyException("cannot peek() empty queue");
		
		return head.item;
	}

	public void dequeueAll() throws QueueEmptyException{
		if(numItem == 0)
			throw new QueueEmptyException("cannot dequeueAll() empty queue");
		
		head = null;
		tail = null;
		numItem = 0;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		Node N = head;
		for(; N != null; N = N.next){
			sb.append(N.item).append(" ");
		}
		return new String(sb);
	}
}
