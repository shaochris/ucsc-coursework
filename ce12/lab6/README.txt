--------------------------------
LAB 6: FLOATING POINT MATH
--------------------------------


----------------
LEARNING

<<Describe what you learned, what was surprising, what worked well and what did not.>>
This assignment is much difficult than I thought that it includes so many concepts and implementation of functions. I went to several lab sections and tried to understand the concepts. I also watched lectures several times. After I understand most of them, I finally got some ideas to start this lab assignment. The Lab Strategy is useful than ever. I followed the steps and finish my program part by part. Overall, I think this lab is pretty good that it gives us a chance to understand a lot of materials, such as float point, stack, and functions. I think the most difficult part for me is that I could not get rid of the ideas of high-level language like jave and c, because Mips is so different than them. I always confuse by that. However, the more I wrote with assembly language, the better I feel. It is an improvement.

----------------
ANSWERS TO QUESTIONS

1.
First, I wrote many unrelative code just for understanding how functions work in mips, because I was very confusing how $ra works and how to store and restore. And I also use syscall 35 to help me figure out the value of my input.

2.
Overflow in float point means the result after arithmetic operation is too large to represent. For example, 01101111 * 01101111 = 01110000

3.
Because the mantissa only contains 23 bit, so when I normalize the float, I shift the float to get my mantissa, but there are some of them lost by shift, so I just offset the lost mantissa by another mantissa.

4.
I wrote many extra code in test_Lab6.asm to test my program to make sure every function is working, especially for the normalizeFloat.
