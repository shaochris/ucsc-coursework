// Problem8.java

import java.util.*;
class Sort {
    public static void main(String[] args) {
        int[] integers = new int[5];
        Scanner sc = new Scanner(System.in);
        for(int j =0; j < integers.length; j++){
            while(true){
                while(!sc.hasNextInt()){
                    sc.next();}
                if(j < 1){
                    integers[0] = sc.nextInt();
                    break;
                }else if(j < 2) {
                    integers[1] = sc.nextInt();
                    break;
                }else if(j < 3) {
                    integers[2] = sc.nextInt();
                    break;
                }else if (j < 4) {
                    integers[3] = sc.nextInt();
                    break;
                }else{
                    integers[4] = sc.nextInt();
                    break;}
                }
                }
        integers = BubbleSort(integers);
        for (int i=0; i < integers.length; i++) {
            System.out.println(integers[i]);
        }
    }
        static int[] BubbleSort(int[] a){
            int temp;
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    if (a[i] < a[j]) {
                        temp = a[j];
                        a[j] = a[i];
                        a[i] = temp;

                    }
                }
            }
            return a;
        }
    }


