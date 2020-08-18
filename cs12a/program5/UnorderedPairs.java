class UnorderedPairs{
   
   public static void main(String[] agrs){

      int n = 5;
      int k = n*(n-1)/2;
      int i, j;

      System.out.print("the "+k+" 2-element subsets of {");
      for(i=1; i<n; i++)
         System.out.print(i+",");
      System.out.println(n+"} are: ");

      for(i=1; i<n; i++){
         for(j=i+1; j<=n; j++){
            System.out.println("{"+i+","+j+"}");
         }
      }
      System.out.println();

   }
}
