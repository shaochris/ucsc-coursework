# Lab6.asm
# Purpose: write subroutines and learn about IEEE single-precision 
# 	   floating point numbers and arithmetic.
#--------------------Pseudo-code------------------
# Subroutine PrintFloat
# store input to $s0
# determine the sign of input
# push stack
# print exponent Loop
# shift left 1 bit to mask the sign bit
# then shift 2 bit
# ...
# and then shift right 31 bit to mask other bits by 8 times
# print mantissa Loop
# shift left 9 bit and then shift right 32 bit to mask other bits by 23 times
# pop stack
# return
#
# Subroutine CompareFloats
# Store the input $a0 to $s0, and $a1 to $s1
# check they sign
# if A = 0, but B = 1, then A > B
# if A = 1, but B = 0, then B > A
# otherwise go masking
# Masking
# Since they have the same sign bit
# if their exponents are the same, go CompareMantissa
# else if they are positive
# the larger exponent float has larger value
# else they are negative
# the larger exponetn float has smaller value
# When the exponents are equal, the larger mantissa number
# has the larger value, otherwise
#
# Subroutine NormalizeFloat
# shift mantissa $a1 to the left until reach the leading 1
# shift mantissa $a2 to the right by the same times as mantissa $a1 shifted to the left as rounded
# so if there are 18 zeros in front of the leading one, we need 
# to shift to the right and decrease the exponent; otherwise,
# we need to shift to the left and increase the exponent
# shift final mantissa to the left by one to offset the hiding 1
# and then shift right to its right position
# make sign bit to its right position
# make exponent to its right position
# add sign bit, exponent, and mantissa together 
# and return the value
#
# Subroutine AddFloat
#
#
#
# Subroutine MultFloat
# check their sign
# add exponent 
# multiply mantissas
# normalize
# return

# Subroutine PrintFloat
#	Prints the sign, mantissa, and exponent of a SP FP value.
# input:	$a0 = Single precision float
# Side effects: None
# Notes:	see the example for the exact output format
.text
PrintFloat:
	
	# get input from $a0 and store it in $s0
	move $t9, $a0
	
	# print sign
	li   $v0, 4
	la   $a0, SIGN
	syscall
	
	bgez $t9, Positive
Negative:
	li   $v0, 1
	la   $a0, 1
	syscall
	b Next
Positive:
	li   $v0, 1
	la   $a0, 0
	syscall
Next:
	li   $v0, 4
	la   $a0, NEWLINE
	syscall
	
	# print exponent
	li   $v0, 4
	la   $a0, EXPONENT
	syscall
	
	subi $sp, $sp, 4 # push to the stack
	sw $ra, ($sp)
	li   $t1, 31
	li   $t2, 1
	li   $t3, 9 # break condition
ExLoop:
	beq  $t2, $t3, GoM
	sllv $a0, $t9, $t2
	srlv $a0, $a0, $t1
	li   $v0, 1
	syscall
	addi $t2, $t2, 1
	b ExLoop
GoM:	
	li   $v0, 4
	la   $a0, NEWLINE
	syscall
	
	lw   $ra, 4($sp)
	addi $sp, $sp, 8
	
	# print mantissa
	li   $v0, 4
	la   $a0, MANTISSA
	syscall
	
	subi $sp, $sp, 8
	sw   $ra, 4($sp)
	
	li   $t2, 9
	li   $t3, 32
MsLoop:
	beq  $t2, $t3, End
	sllv $a0, $t9, $t2
	srlv $a0, $a0, $t1
	li   $v0, 1
	syscall
	addi $t2, $t2, 1
	b MsLoop
End:
	li   $v0, 4
	la   $a0, NEWLINE
	syscall
	lw    $ra, ($sp)
	addi $sp, $sp, 4 # pop off from the stack
	jr   $ra
.data
SIGN:     .asciiz "SIGN: "
EXPONENT: .asciiz "EXPONENT: "
MANTISSA: .asciiz "MANTISSA: "
NEWLINE:  .asciiz "\n"

# Subroutine CompareFloats
#	Compares two floating point values A and B.
# input:	$a0 = Single precision float A
#		$a1 = Single precision float B
# output:	$v0 = Comparison result
# Side effects: None
# Notes: Returns 1 if A>B, 0 if A==B, and -1 if A<B
.text
CompareFloats:
	srl  $t8, $a0, 31 # sign of A
	srl  $t7, $a1, 31 # sign of B
	
	beq  $t8, $t7, Masking
	# if A = 0, but B = 1, then A > B
	# if A = 1, but B = 0, then B > A
	# otherwise go masking
	bgt  $t7, $t8, PrintG
	b PrintL
Masking:
	# Since they have the same sign bit
	# if their exponents are the same, go CompareMantissa
	# else if they are positive
	# the larger exponent float has larger value
	# else they are negative
	# the larger exponetn float has smaller value
	
	# When the exponents are equal, the larger mantissa number
	# has the larger value, otherwise
	lw   $t0, MaskE # 0xff000000
	lw   $t1, MaskM # 0x00ffffff
	and  $t2, $a0, $t0 # exponent of A
	and  $t3, $a1, $t0 # exponent of B

	beq  $t2, $t3, CompareMantissa
	beqz $t8, Pos
	
	bgt  $t2, $t3, PrintL
	b PrintG
Pos:
	bgt  $t2, $t3, PrintG
	b PrintL
CompareMantissa:
	and  $t4, $a0, $t1 # mantissa of A
	and  $t5, $a1, $t1 # mantissa of B
	beq  $t4, $t5, PrintE
	bgt  $t4, $t5, PrintG
	b PrintL
	
PrintG:
	li   $t9, 1
	b FinishCompare

PrintL:
	li   $t9, -1
	b FinishCompare
PrintE:
	li   $t9, 0
FinishCompare:
	move $v0, $t9
	jr   $ra
.data
MaskE:   .word    0xff800000
MaskM:   .word    0x007fffff


# Subroutine AddFloats
# 	Adds together two floating point values A and B.
# input:	$a0 = Single precision float A
#		$a1 = Single precision float B
# output:	$v0 = Addition result A+B
# Side effects: None
# Notes: 	Returns the normalized FP result of A+B
.text
AddFloats:
	# Scaler numbers to have same exponnet
	# Rewrite the smaller number so exponent matches the larger
	# E.x. 8.077*10^-1 = 0.087 *10^1
	
	# get sign 
	srl  $t0, $a0, 31
	srl  $t1, $a1, 31
	# get exponent
	sll  $t1, $a1, 1
	srl  $t1, $t1, 24 # $t1 is the exponent of $a1
	
	sll  $t2, $a2, 1
	srl  $t2, $t2, 24 # $t2 is the exponent of $a2
	# Add the mantissas
	# Add back the sahred exponent
	
	# Normalize the result
	# Round (not care)
	# 4 digits in the mantissa
	
	#move $v0,
	jr $ra

# Subroutine MultFloats
#	Multiplies two floating point values A  and B.
# input:	$a0 = Single precision float A
# 		$a1 = Single precision float B
# output:	$v0 = Multiplication result A*B
# Side effects: None
# Notes: Returns the normalized FP result of A*B
.text
MultFloats:
	# manitude check
	xor  $t0, $a0, $a1
	srl  $t0, $t0, 31
	# if $a1 and $a2 have the same sign magnitude, then they are positive
	# otherwise negative
	beqz $t0, Zheng
	# negative set $t0 to 1 as sign bit
	addi $t0, $zero, 1
Zheng:
	# get exponents
	sll  $t1, $a1, 1
	srl  $t1, $t1, 24 # $t1 is the exponent of $a1
	
	sll  $t2, $a2, 1
	srl  $t2, $t2, 24 # $t2 is the exponent of $a2
	
	# Add the exponents
	li   $t3, 127 # bias number
	# since ($t1 - 127) + ($t2 - 127) + 127 = $t1 + $t2 - 127
	add  $t4, $t1, $t2
	sub  $t4, $t4, $t3
	 
	# Multiply the mantissas
	# mask sign and exponent
	lw   $t9, MaskSE
	
	and  $t5, $a1, $t9
	
	and  $t6, $a2, $t9

	mult $t5, $t6
	mthi $t7
	mtlo $t8
	
	# Normalze 
	move $a0, $t0 # as input of normalizeFloat
	move $a1, $t7
	move $a2, $t8
	move $a3, $t4 # as input of normalizeFloat
	
	subi $sp, $sp, 4
	sw   $ra, ($sp)
	
	la   $t9, NormalizeFloat
	jalr $t9
	
	lw   $ra, ($sp)
	addi $sp, $sp, 4
	#move $v0, $t9
	jr   $ra
	# E.x. A*10^B * C*10^D = A*C * 10^(B+D)
.data 
MaskSE: .word 0x003fffff


# Subroutine NormalizeFloat
#	Normalizes, rounds, and "packs" a floating point value.
# input:	$a0 = 1-bit Sign bit (right aligned)
# 		$a1 = [63:32] of Mantissa
#		$a2 = [31:0] of Mantissa
#		$a3 = 8-bit Biased Exponent (right aligned)
# output:	$v0 = Normalized FP result of $a0, $a1, $a2
# Side effects: None
# Notes: Returns the normalized FP value by adjusting the
#	 exponent and mantissa so that the 23-bit result 
#	 mantissa has the leading 1(hidden bit). More than
#	 23-bits will be rounded. Two words are used to 
#	 represent an 18-bit integer plus 46-bit fraction 
#	 Mantissa for the MultFloats function. (HINT: This
#	 can be the output of the MULTU HI/LO registers!)
.text
NormalizeFloat:

	# adjust $a1 and $a2 using shifts to make it so that leading 1 is in the 1s 
	# place if $a1
	clz  $t0, $a1
	# so if there are 18 zeros in front of the leading one, we need 
	# to shift to the right and decrease the exponent; otherwise,
	# we need to shift to the left and increase the exponent
	
	li   $t8, 18
	li   $t7, 32
	sllv $t1, $a1, $t0
	sub  $t7, $t7, $t0
	# shift to the right 32 -$t0 bits to coroperate with a1
	srlv $t2, $a2, $t7
	bge  $t0, $t8, Fraction
	
	sub  $t0, $t8, $t0
	subi $t0, $t0, 1
	add  $t3, $a3, $t0
	b Offset 
Fraction:
	sub  $t0, $t0, $t8
	addi $t0, $t0, 1
	sub  $t3, $a3, $t0
Offset:
	# add mantissa $a1 and $a2 together
	add  $t6, $t1, $t2
	# shift left 1 to get offset hiding one
	sll  $t6, $t6, 1
	
	# shift sign, exponent and mantissa to their correct position
	srl  $t6, $t6, 9

	sll  $t5, $a0, 31
	
	sll  $t3, $t3, 23

	add  $t5, $t5, $t3
	add  $t5, $t5, $t6
	# return
	move $v0, $t5
	jr   $ra


