//Search.java

import java.io.*;
import java.util.Scanner;

class Search
{
	public static void mergeSort(String[] word, int[] LineNumber, int p, int r)
	{
		int mid;
		if(p < r)
		{
   		mid = (p + r) / 2;
   		mergeSort(word,LineNumber, p, mid);
   		mergeSort(word,LineNumber, mid + 1, r);
   		merge(word,LineNumber, p, mid, r);
		}
	}

	public static void merge(String[] word, int[] LineNumber, int p, int q, int r)
   {
      int n1 = q - p + 1;
      int n2 = r - q;
      String[] L = new String[n1];
      String[] R = new String[n2];
      int[] Ln = new int[n1];
      int[] Rn = new int[n2];

      int i, j, k;

      for(i = 0; i < n1; i++)
      {
         L[i] = word[p + i];
         Ln[i] = LineNumber[p + i];
      }
      for(j = 0; j < n2; j++)
      {
         R[j] = word[q + j + 1];
         Rn[j] = LineNumber[q + j + 1];
      }

      i = 0; j = 0;
      for(k = p; k <= r; k++)
      {
            if( i < n1 && j < n2 )
            {
               if( L[i].compareTo(R[j]) < 0 )
               {
                     word[k] = L[i];
                     LineNumber[k] = Ln[i];
                     i++;
               }else
               {
                     word[k] = R[j];
                     LineNumber[k] = Rn[j];
                     j++;
               }
            }else if( i < n1 )
            {
               word[k] = L[i];
               LineNumber[k] = Ln[i];
               i++;
            }else // j < n2
            {
               word[k] = R[j];
               LineNumber[k] = Rn[j];
               j++;
            }
         }
   }

	static int binarySearch(String[] A, int p, int r, String target){
      int q;
      if(p > r) {
         return -1;
      }else{
         q = (p + r) / 2;
         if(A[q].equals(target)){
            return q;
         }else if(target.compareTo(A[q]) < 0){
            return binarySearch(A, p, q - 1, target);
         }else{ // target > A[q]
            return binarySearch(A, q + 1, r, target);
         }
      }
   }

	public static void main(String[] args) throws IOException
	{
		int LN = 0;

		//check number of cammand line arguments
		if(args.length <= 1)
		{
			System.err.println("Usage: Search file target1 [target2 ..]");
			System.exit(1);
		}
		// open file
		Scanner sc = new Scanner(new File(args[0]));
		while(sc.hasNextLine())
		{
			sc.nextLine();
			LN++;
		}
		sc.close();// first close

		String[] token = new String[LN];
		int[] LineNumber = new int[LN];

		//oren file again
		Scanner scTrack = new Scanner(new File(args[0]));
		for(int i = 0; i < LN; i++)
		{
			token[i] = scTrack.nextLine();
			LineNumber[i] = i + 1;
		}

		mergeSort(token, LineNumber, 0, LN - 1);
		for(int j = 1; j < args.length; j++)
		{
			if(binarySearch(token, 0, LN - 1, args[j]) != -1)
			{
					System.out.println(args[j] + " found on line " + LineNumber[binarySearch(token, 0, LN-1, args[j])]);
			}else
			{
				System.out.println(args[j] + " not found");
			}
		}
		// second close file
		scTrack.close();
	}

}
