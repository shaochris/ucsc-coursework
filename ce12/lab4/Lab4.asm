# Lab4.asm
# Purpose: This program would take an integer from user, and if the input is divisible
# by 3, it prints "FEED" to the user; if the input is divisible by 4, it print "BABE" to the user; 
# if the input is both divisible by 3 and 4, it prints "FEEDBABE" to the user; otherwise, the input 
# is neither divisible by 3 or 4, it prints back the input to the user.

.text				
main:	
	# ask input from the user		
	li $v0,4 	
	la $a0, prompt 
	syscall 	
	
	# Read the integer and save it in $s0 		
	li $v0, 5
	syscall
	move $s0, $v0
	
	li $t1, 0 		# temp var counter for loop
	li $t2, 3 		#temp denominator 3
	li $t3, 4 		# temp denominator 4

	Loop:
		# set the condition if $t1 = $s0, ends the program
		beq $t1, $s0, End 
		addi $t1, $t1, 1 	# $t1 = $t1 + 1
	
		rem $t4, $t1, $t2 	# $t4 is the reminder of $t1 / $t2
		rem $t5, $t1, $t3 	# $t5 is the reminder of $t1 / $t3

		add $t0, $t4,$t5 	# $t0 is the addtion of $t4 and $t5 

		bnez $t0,PrintFEED 	# if the reminder $t5 and reminder $t7 are equal, it means the input can either be divided by 3 or 4
	PrintFEEDBABE:
		li $v0, 4
		la $a0, FEEDBABE
		syscall
		b Loop 			# start a new loop
	PrintFEED:			# else if
		bnez $t4,PrintBABE 	# if the reminder $t5 is not 0, it means the input cannot be divided by 3
		li $v0, 4
		la $a0, FEED
		syscall
		b Loop
	PrintBABE:			# else if
		bnez $t5, PrintNumber   # if the reminder $t7 is not 0, it means the input cannot be divided by 4
		li $v0, 4
		la $a0, BABE
		syscall
		b Loop
	PrintNumber:			# else (if the input can neither be divided by 3 nor 4, print itself
		li $v0, 1
		move $a0, $t1
		syscall	
		li $v0, 4
		la $a0, newline
		syscall	
		b  Loop
	End: 				# Exit hte program
		li $v0, 10	
		syscall			# The program ends.
.data				
	prompt: .asciiz "Please input a number: "
	FEEDBABE: .asciiz "FEEDBABE\n"
	FEED: .asciiz "FEED\n"
	BABE: .asciiz "BABE\n"
	newline: .asciiz "\n"
