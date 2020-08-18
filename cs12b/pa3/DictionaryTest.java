//DictionaryTest.java
//serve as a test client for the Dictionary ADT while it is under construction

public class DictionaryTest
{
	public static void main(String[] args)
	{
		Dictionary A = new Dictionary();
		String v;
		A.insert("1","a");
    	A.insert("2","b");
      	A.insert("3","c");
      	A.insert("4","d");
      	A.insert("5","e");
      	A.insert("6","f");
      	A.insert("7","g");
        //my test
        A.insert("20","g");
        A.insert("22","y");
        //A.insert("2","p");
        A.insert("42","i");
        A.insert("e","2");

      	System.out.println(A);

    	  v = A.lookup("1");
      	System.out.println("key=1 "+(v==null?"not found":("value="+v)));
      	v = A.lookup("3");
      	System.out.println("key=3 "+(v==null?"not found":("value="+v)));
      	v = A.lookup("7");
      	System.out.println("key=7 "+(v==null?"not found":("value="+v)));
      	v = A.lookup("8");
        System.out.println("key=8 "+(v==null?"not found":("value="+v)));
        //my test
        v = A.lookup("56");
        System.out.println("key=56 "+(v==null?"not found":("value="+v)));
        v = A.lookup("22");
        System.out.println("key=22 "+(v==null?"not found":("value="+v)));
        v = A.lookup("e");
        System.out.println("key=e "+(v==null?"not found":("value="+v)));
        v = A.lookup("20");
        System.out.println("key=20 "+(v==null?"not found":("value="+v)));
        v = A.lookup("0");
        System.out.println("key=0 "+(v==null?"not found":("value="+v)));

      	System.out.println();

      	//A.insert("2","f");  // causes DuplicateKeyException

      	A.delete("1");
      	A.delete("3");
      	A.delete("7");
        //my test
        A.delete("20");
        A.delete("e");
        //A.delete("3");
      	System.out.println(A);

      	//A.delete("8");  // causes KeyNotFoundException

     	System.out.println(A.isEmpty());
      	System.out.println(A.size());
      	A.makeEmpty();
      	System.out.println(A.isEmpty());
      	System.out.println(A);
		

      
	}
}
