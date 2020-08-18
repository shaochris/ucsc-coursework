class CLA{
   
   public static void main( String[] args ){

      int i, n = args.length;
      
      for(i=0; i<n; i++){
         try{
            Integer.parseInt(args[i]);
            System.out.println(args[i]+" is an int");
         }catch(NumberFormatException e1){
            try{
               Double.parseDouble(args[i]);
               System.out.println(args[i]+" is a double");
            }catch(NumberFormatException e2){
               System.out.println(args[i]+" is a non-numeric string");
            }
         }  
      }

   }
}
