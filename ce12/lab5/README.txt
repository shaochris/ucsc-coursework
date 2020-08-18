--------------------------------
LAB 5: HEX TO DECIMAL CONVERSION
--------------------------------


----------------
LEARNING

<<Describe what you learned, what was surprising, what worked well and what did not.>>
Honestly, this assignment is the most difficult one I ever had. I went to lab sections for the whole week, and break it down to 4 parts as it suggests and finish them part by part.In order to finish this assignment, the first thing we need to know is the representation of numbersin hex, binary and decimal. All numbers sotre in the computer are binary. I was not really understand that at first. Because at that time, in order to get the input string, I try to convert them to binary,but it doesn't work, because what was I doing is convert them into binary representation, instead of getting the value of it. After I understood that, it became much easier to go on the next process. This assignment helps me a lot in understanding several concepts of fundamental computer science. For example, the implement of ASCII table and how to convert computer language into "human language." When I was doing the part 3, some exceptions were really trick to implement. However, Pseudo-code was really helpful and help me understand the logic of each parts. 

----------------
ANSWERS TO QUESTIONS

1.
There can be infinite of 0 in front of the input string, because there is a Leap function in my program and it will automatically leap all zeros before it reaches the first non-zero character and then stop leap zeros after that. As a result, if I add zeros at the end of my input string, I will get wrong output. Since I can add as many zeros as I want in front of my deserved result, I can have infinite combinations of result in zero just add 1 zero every time in front of my input '0'.

2.
The largest input value(in decimal) thta my program supports is 2^31-1, since 0x00000000 
is a 32-bit value.

3.
The smallest input value that my program supports is -2^31.

4.
The u simply means that they don't trap on overflow.I used signed arithmetic for my program.

5.
Every number is storing in computer by binary. Any format of numbers we can see on screen are transform from binary to the format we want to see by some arithmetic of ASCII table. For example, 1010 is a binary representation of 10 in decimal. We can get 10 in decimal by summation of it's corresponding 2's power. i.e 1010 => 1*2^3 + 0 * 2^2 + 1 * 2^1 + 0 * 2^0 = 10. So if we want to write a binary-to-dicimal convertor, it would be a similar idea as our assignment ask us to do.
