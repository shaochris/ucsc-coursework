# Lab5.asm
# Purpose: convert a hex number to decimal 
#############################################################################
# Pseudo-code: 
# Part 1: Read program argument string
# get input from Program argument
# read the program argumnet string and save it to $s0
#
# Part 2: Convert the program argument to a binary integer in $s0.
# load sum $s0, pointer of pointer $a1, and mychar(register)
# load initial pointer charater
# add 2 to the initial pointer
# set sum to 0
# start loop
# load byte pointer by initial pointer into mychar
# if mychar is decimal 0, then break
# if mychar is between '0' to '9', we minus 48 to have decimal value
# else, minus 55 to have decimal value
# shift sum 4 bits to the left
# add sum and muchar together, store to sum
# add 1 to the initial pointer
# jump to the start of the loop
# 
# Part 3: Convert the value in $s0 as a 2SC number to an ASCII decimal number.
# check if sum is at least zero (sum >= 0), go to division-loop
# otherwise add a negative sign "-" in front of the output
# division-loop
# reminder divided by divisor(100000000 )(one billion)
# if sum is negative, then the reminder divided by divisor(-100000000) to get correct reminder
# rem(mfhi): set "reminder" to rem
# leap the leading zeros in front of the result
# result(mflo): print char(result+48)
# 	if the sum is 0, then print 0 and End
# if divisor == 1, then break
# if divisor == -1 then break
# divisor = divisor / 10
# go back to division-loop
#
# Part 4: Print the correct signed ASCII decimal number before exiting cleanly.
# add 48 to the character to output the correct signed ASCII decimal number
# Print character by character
# Exit cleanly
#############################################################################

.text
main:
	# Part 1: Read program argument string 
	# prompt
	li   $v0, 4
	la   $a0, prompt
	syscall
	# Read input from Program Argument
	lw   $t0, ($a1) # $t0 is the program argument
	move $a0, $t0
	syscall
	
	li   $v0, 4
	la   $a0, newline
	syscall
	# Part 2: Convert the program argument to a binary integer in $s0
	addi $t0, $t0, 2 
	li   $s0, 0 # set sum to 0
Loop:	
	lb   $t1, ($t0) # load byte pointer by initial pointer into register, so $t1 is mychar
	beqz $t1, Break # break when $t1 is equal to zero
	sll  $s0, $s0, 4
	
	# assume it is a number
	subi $t2, $t1, 48 # convert ascii to decimal
	slti $t3, $t2, 10 #  $t3: $t2 <= 9 # if true $t3 = 1
	li   $t4, 1
	beq  $t3, $t4, Case2 # if $t2 <= 9, then go to case2
	# else it is a letter
	subi $t5, $t1, 55 

Case1: 	# convert char
	add  $s0, $s0, $t5
	b Next
Case2: 	# if its value is between '0' to '9', store $t2 to $s0 
	add  $s0, $s0, $t2
Next:	
	addi $t0, $t0, 1 # move to the next char
	b Loop

	# Part 3: Convert the value in $s0 as a 2SC number to an ASCII decimal number.
Break:	
	# print the message decimal
	li   $v0, 4
	la   $a0, decimal
	syscall
	
	# val initialize
	li   $t2, 100000000 # one billion
	move $t0, $s0 # make $t0 have the value of $s0
	li   $t1, 1
	li   $t3, 10
Check: 	# check if sum is positive, directly go to Divi, otherwise add negative sign at front
	bgez $t0, Divi
	li   $t1, -1
	li   $t2, -100000000 # in order to get the same result for a negative number
	# print negative sign
	li   $v0, 4
	la   $a0, negative
	syscall 
Divi:	
	div  $t0, $t2  # divide $t0 by $t2
	mfhi $t0 # $t0 is the reminder
	mflo $t6 # $t6 is the result
	beq  $t0, $s0, Pre
	b Print
Pre: 	# Prepare for next Loop
	beq  $t2, $t1, End # if divisor is 1, then print the last character
	beq  $t2, $t1, End # if the divisor is -1
	div  $t2, $t3 # divide the divisor by 10
	mflo $t2 # $t2 is the result we use for next loop as a divisor(new divisor)
	b Divi
	
	# Part 4: Print the correct signed ASCII decimal number before exiting cleanly.
Print: 
	beq  $t6, $s0, Zero # if the sum is 0, then print char '0'
	addi $t7, $t6, 48 # add 48 to get correct ASCII decimal number
	li   $v0, 11
	move $a0, $t7
	syscall
	b Pre
Zero: 
	addi  $s0, $s0, 48
	li    $v0, 11
	move  $a0, $s0
	syscall
End:	# exit
	li    $v0, 10
	syscall

.data
	prompt:   .asciiz "Input a hex number:\n"
	decimal:  .asciiz "The decimal value is:\n"
	newline:  .asciiz "\n"
	negative: .asciiz "-"
	
