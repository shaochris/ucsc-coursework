

class DrawSquare {
    public static void main(String[] args) {

        String s = " ";
        int a = Integer.parseInt(args[0]);

        if (a < 2) {
            System.out.println("Usage: java DrawSquare <positive integer tat least 2>");
        }

        for (int i = 0; i < a; i++) {
            System.out.print("*");
        }
        int n = a - 2;
        if (n >= 2) {
            for (int j = 0; j < n; j++) {
                System.out.println("*");
                while (j < n) {
                    System.out.print(s);
                }
                System.out.println("*");
            }
        }


            System.out.println();
            for (int i = 0; i < a; i++) {
                System.out.print("*");
            }
            System.out.println();


        }
    }


