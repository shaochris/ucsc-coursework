--------------------------------
LAB 3: LOGIC UNIT WITH MEMORY
--------------------------------


----------------
LEARNING

<<Describe what you learned, what was surprising, what worked well and what did not.>>
When I started this assignment, I got no idea how to start. This assignment is much difficult than previous labs. I was confusing by MUX and FLIP-FLOP for a while. I went to several labs and ask a lot of questions on piazza in order to understand the goal of the assignment. Finally, I got this done. I think the hardest part for this assignment is to understand how MUX works and build it by ourselves. However, building a MUX also help me to understand the mechanism of MUX and logic gates. In addition, senders and receivers can reduce a lot of messing wires and make the logic clear to recognize. I found that labeling  and simple wire logic are essential. It makes easier to debug and find the potential problem in the logic. And also, test each parts before attempting integrate it with the other parts break down this assignment much easier.
----------------
ISSUES

<<Discuss issues you had building the circuit.>>
I was confusing by the relationship among MUX, keyboard and operations. After I figure out  that MUX is selecting four operations, I felt much easier to finish the logic. In addition, read through patt chapter 3 also gives me more ideas of building my circuits.
----------------
DEBUGGING

<<Describe what you added to each module to make debugging easier.>>
I added four LEDs with four inputs of keyboard to see the binary combination directly instead of hexadecimal digit. I have four LEDs with the outputs of each of FLIP-FLOPs to know their states. I have four LEDS with the outputs of each of MUXs to know their states. Especially, I fix my MUX with more senders and receivers and it makes much more easier to debug. I separate my MUX part into two parts to make it look clear.

----------------
QUESTIONS

What is the difference between a bit-wise and reduction logic operation?
<<insert your answer>>
We can use Bit-wise logic operation directly implement the our ideas, but when the logic gets more complicated, reduction logic operation will help us to implement it with a simpler logic, which is essential when we want to implement a larger logic.

What operations did we implement?
<<insert your answer>>
STORE, OR, AND, INVERSE
FLIP-FLOP registor
MUX selector


Why might we want to use the other type of logic operations?
<<insert your answer>>
In order to simplify our logic and save more space so that we can implement more MOSs and have faster processors. The four operations, STORE, OR, AND and INVERSE we implemented are easy and basic, we will have to build larger project with more advanced logic structures to make it efficient.


