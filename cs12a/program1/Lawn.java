//Lawn.java
//You can use this program to compute the lawn area simply 
//type the length and width of house and lot.This program 
//can also compute mowing time.


import java.util.Scanner;

class Lawn{
	public static void main(String[] args){
	//declear variables
	double lotLength, lotWidth, houseArea,lotArea,lawnArea, houseWidth, houseLength;
	double  mowRate;
	int h, m, s;

	Scanner sc = new Scanner(System.in);

	System.out.print("Enter the length and width of the lot, in feet: ");
	lotWidth = sc.nextDouble();// user input
	lotLength = sc.nextDouble();
	System.out.print("Enter the length and width of the house, in feet: ");
	houseWidth = sc.nextDouble();//user input
	houseLength = sc.nextDouble();
	houseArea = houseWidth*houseLength;//compute the house area
	lotArea = lotWidth*lotLength;//compute the lot area
	lawnArea = lotArea - houseArea;//compute the lawn area

	System.out.println("The lawn area is " + lawnArea + " square feet.");
	System.out.print("Enter the mowing rate, in square feet per second: ");
	mowRate = sc.nextInt();//unser input

        double sd = lawnArea/mowRate; //compute the time of mowing
        
        s = (int) Math.round(sd); //compute the time 
        m = s/60;
        s = s%60;  // same as s %= 60
        h = m/60;
        m = m%60;  // same as m %= 60
        System.out.println("The mowing time is "+h+" hour"+(h==1?".":"s ")+m+" minute"+(m==1?".":"s ")+s+" second"+(s==1?".":"s."));
        

}
	
}
