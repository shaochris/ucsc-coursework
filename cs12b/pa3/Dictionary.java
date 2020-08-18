// This is the Dictionary ADT for pa3

public class Dictionary implements DictionaryInterface
{
	// private inner Node class
	private class Node
	{
		String value;
		String key;
		Node next;
		//constructor
		Node(String st, String vl)
		{
			key = st;
			value = vl;
			next = null;
		}
	}

   // Fields for the Dictionary class
	private Node head; // head is the reference of a node and it isn't the node itself bacsially
	private int numItems;

	// constructor for the Dictionary class
	public Dictionary()
	{
		head = null;
		numItems = 0;
	}

	public boolean isEmpty()
	{
		if(numItems == 0)
		{
			return true;
		}else
		{
			return false;
		}
	}

	public int size()
	{
		return numItems;
	}

	public String lookup(String key) // compire every node with key
	{
		// head is dereference
		Node cur = head;
		while(cur != null)
		{
			if((cur.key).equals(key))
			{
				return cur.value;
			}else
			{
				cur = cur.next;
			}
		}
		return null;
	}

	public void insert(String key, String value) throws DuplicateKeyException 
	{
		
		if(lookup(key) != null)
		{
			throw new DuplicateKeyException("cannot insert duplicate keys.");
		}

		if(head == null) // basically the same with isEmpty does // head ==null
		{
			head = new Node(key, value);
		}else
		{
			Node N;
			for( N = head; N.next != null; N = N.next){}
				N.next = new Node(key, value);
			}
		numItems++;
	}

	public void delete(String key) throws KeyNotFoundException// keyNotFountException
	{	
		if(lookup(key) == null) // if there is no such a key or this is an empty
		{
			throw new KeyNotFoundException("cannot delete non-existent key.");
		}else if((head.key).equals(key))
		{
			head = head.next;
		}else
		{
			Node N;
			for(N = head; !(N.next.key).equals(key); N = N.next){}  
			N.next = N.next.next;
		}
		
		numItems--;
	}

	public void makeEmpty() // change the states of dictionary
	{
		head = null;
		numItems = 0;
	}

	  public String toString()
	  {
      StringBuffer sb = new StringBuffer();
      Node N = head;

      for( ; N!=null; N=N.next){
         sb.append(N.key).append(" ");
         sb.append(N.value).append("\n");
      }
      return new String(sb);
   }
}

