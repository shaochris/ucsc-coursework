//problem2.java

import java.util.Scanner;
import static java.lang.Math.*;

class Problem2{
public static void main(String[] args){
Scanner sc = new Scanner(System.in);
double x,y,s,t,r;

x = sc.nextDouble();
y = sc.nextDouble();
s = sqrt(x);
t = sqrt(y);
r = s + t;
System.out.println(r);
}
}

