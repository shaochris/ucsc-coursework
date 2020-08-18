//Problem9

class EchoWords{
public static void main (String[] args){

	String[] str = new String[5];
	str[0] = "one";
	str[1] = "two";
	str[2] = "three";
	str[3] = "four";
	str[4] = "galumph";

	for(int i = 0; i <5; i++){
		System.out.print(str[i]);
		System.out.print(" ");
	}
	System.out.println();
	for(int j = 0; j < 5; j++){

		if(str[j] == "galumph"){
			break;
		}
		System.out.println(str[j]);
	}

	System.out.println("bye!");
	}
}
