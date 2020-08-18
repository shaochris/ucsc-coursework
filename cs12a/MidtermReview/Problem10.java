class Problem10{
   public static void main(String[] args){
      int i=0, j=1, a=2, b=3, c=4;
      a += b/a;
      if(a<b)
         c += (c<a+b ? ++i : j++);
      else
         c *= (c-a>b ? j-- : --i);
      System.out.println("i="+i+", j="+j+", a="+a+", b="+b+", c="+c);

   }
}
