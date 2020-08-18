// lab1: A simple program assignment

class FeedBabe {
   public static void main(String[] args) {
      int a = 500;
      for(int i = 1; i <= a; i++) {
         if(i % 3 == 0 && i % 4 == 0) {
            System.out.println("FEEDBABE");
         } else if(i % 3 == 0) {
            System.out.println("FEED");
         } else if(i % 4 == 0) {
            System.out.println("BABE");
         } else {
            System.out.println(i);
         }
      }
   }
}