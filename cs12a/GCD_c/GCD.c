/* GCD.c 
 * This program can compute the GCD of two positive integers. */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

/* isInteger()
 * Returns 1 (true) if string s[] can be interpreted as a integer, and
 * returns 0 (false) otherwise. */

int isInteger(char s[]){
   int i, n;
   
   if( s==NULL ) 
      return 0;
   n = strlen(s);
   if( n==0 ) 
      return 0;
   if( s[0]!='-' && s[0]!='+' && !isdigit(s[0]) )
      return 0;
   for(i=1; i<n; i++){
      if( !isdigit(s[i]) ) 
         return 0;
   }
   return 1;
}
int gcd(int a, int b) {
	int i,gcd;
	for(i = 1; i <= a && i <= b; i++){
		if(a%i == 0 && b%i==0)
			gcd = i;
	}
	return gcd;
                }

int main(){
	int i, n,y;
	int x[2];
	char str[100];

	/* get two positive int from the user */
	printf("Enter a positive integer: ");
	for(i = 0; i<2;i++){
	n = scanf(" %s", str); // read one token from stdin
	while(1){ // seemingly infinite loop
	while(!isInteger(str)){ //if its not an integer
	printf("Please enter a positive integer: "); //ask agian
	n = scanf(" %s", str); // get another string
	}
	/* at this point str is a string that should be convertable to integer */
	sscanf(str, " %d", &x[i]); // conver string to int and store in array x[]

	if(x[i]>0){

		if (i < 1) printf("Please enter another integer: ");
		break;
	}else{ // if it's positive, then get out of the while loop
	printf("Please enter a positive integer: "); // other wise ask again
	n = scanf(" %s", str);
	}
}
	

}
	/* do the whole thing 2 times and end up here */
	y = gcd(x[0],x[1]);
	printf("The GCD of %d and %d is %d \n", x[0], x[1], y);
	return 0;
}


