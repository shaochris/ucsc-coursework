--------------------------------
LAB 4: FEEDBABE IN MIPS
--------------------------------


----------------
LEARNING

<<Describe what you learned, what was surprising, what worked well and what did not.>>
It was really confusing when the first time I looked at MIPS. I learned java and C language before, and they are really similar. But MIPS was kind of different from those two language. I found it is hard to just transform my java code from Lab1 to finish this code. On the other hand, I found that it is easier to complete by following a pseudo code.  Then I wrote a pseudo code for this program and finish it. Moreover, readings also helped me understand how to use MIPS. By the way, I understood the concept of memory address from lab sections.

----------------
ANSWERS TO QUESTIONS

1.
The limit is less or equal to 2^31-1. Because MIPS's architecture is 32-bit, so the limit is based on the architecture of MIPS.

2.
The addresses are ranging from 0x10010000 to 0x10010014(in hexadecimal).

3.
In my code, I use two pseudo-ops, which are rem instruction and la instruction to print messages to the user. Pseudo-ops are combinations of basic instructions, and we can use them to replace several instructions. It helps us to code more easier and efficiently, because we can save the time to build some fundamental operations. 

4.
I used 9 registers to complete this program, which are a0, v0, s0, t0,...,t5. I think I can use fewer registers by optimizing some conditions. For example, I can just use 1 register to represent the value of 3 and 4, and reassign it when I need. Moreover, I believe I can use fewer of them.
