//Recursion.java

class Recursion
{
	// reverseArray1()
	// Places the leftmost n elements of X[] into the rightmost n positions in
	// Y[] in reverse order
	static void reverseArray1(int[] X, int n, int[] Y)
	{
		if(n > 0) // how recursion occurs
		{	
			Y[Y.length - n] = X[n - 1]; 
			reverseArray1(X, n-1, Y); 
		}
	}	
	// reverseArray2()
	// Places the rightmost n elements of X[] into the leftmost n positions in
	// Y[] in reverse order.
	static void reverseArray2(int[] X, int n, int[] Y)
	{
		// copy the elements in hte input array X[] into the output array Y[]
		if(n > 0) // how recursion occurs
		{
			reverseArray2(X, n - 1, Y);
			Y[n - 1] = X[X.length - n];
			
		}
	}
	// reverseArray3()
	// Reverses the subarray X[i...j].
	static void reverseArray3(int[] X, int i, int j)
	{
		if(j-i > 0) // swap until there is no or only 1 element
		{
			int temp; // swap function
			temp = X[i];
			X[i] = X[j];
			X[j] = temp;
			reverseArray3(X, i + 1, j - 1);
		}
	}

	// maxArrayIndex()
	// returns the index of the largest value in int array X
	static int maxArrayIndex(int[] X, int p, int r)
	{
		int q = (p + r) / 2;
		if(p < r)
		{
			int L = maxArrayIndex(X, p, q); // sort left array
			int R = maxArrayIndex(X, q + 1, r); // sort right array
			// find the max value element and return the index
			if(X[L] < X[R])
			{
				return R;
			}else //(X[L] > X[R])
			{
				return L;
			}
		}
				return q;
	}

	// minArrayIndex()
	// returns the index of the smallest value in int array X
	static int minArrayIndex(int[] X, int p, int r)
	{
		int q = (p + r) / 2;
		if(p < r)
		{
		
			int L = minArrayIndex(X, p, q); // sort left array
			int R = minArrayIndex(X, q + 1, r); // sort right array
			// find the min value element and return the index
			if(X[L] > X[R])
			{
				return R;
			}else //(X[L] < X[R])
			{
				return L;
			}
		}
		return q;
	}
	// main()
	public static void main(String[] args)
	{
		int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
		int[] B = new int[A.length];
		int[] C = new int[A.length];
		int minIndex = minArrayIndex(A, 0, A.length-1);
		int maxIndex = maxArrayIndex(A, 0, A.length-1);

		for(int x: A) System.out.print(x+" ");
		System.out.println();

		System.out.println("minIndex = " + minIndex);
		System.out.println("maxIndex = " + maxIndex);

		reverseArray1(A, A.length, B);
		for(int x: B) System.out.print(x+ " ");
		System.out.println();

		reverseArray2(A, A.length, C);
		for(int x: C) System.out.print(x+ " ");
		System.out.println();

		reverseArray3(A, 0, A.length-1);
		for(int x: A) System.out.print(x+" ");
		System.out.println();

	}
}


