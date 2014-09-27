;Neal Friedman
;Arm Assignment 1
;Program 4
;Multiply by shifting/adding	


	AREA PROGRAM4, CODE
SWI_Exit	EQU	&11    ;finish program
	ENTRY

start	MOV	r0, #10		;multiplicand
	MOV	r1, #12		;multiplier
	MOV	r5, #8		;8 bits for counter	
	BL	multiply	;call multiply
multiply
	AND	r3, r1, #1	;is LSB a 1?
	CMP	r3, #1		;Yes or No
	BEQ	multOne		;if its a one go to multOne
	BLT	multZero	;if its a zero go to multZero
multOne	ADD	r4,r4, r0	;add multiplicand to result
	MOV	r0, r0, LSL #1	;shift the multiplicand to the left
	MOV	r1, r1, LSR #1	;shift the multiplier to the right
	SUB	r5, r5, #1	;decrement counter
	CMP	r5, #0		;are we done?
	BEQ	exit		;if so exit
	BL	multiply	;loop back 
multZero
	MOV	r0, r0, LSL #1	;shift the multiplicand to the left
	MOV	r1, r1, LSR #1	;shift the multiplier to the right
	SUB	r5, r5, #1	;decrement counter
	CMP	r5, #0		;are we done?
	BEQ	exit		;if so exit
	BL	multiply	;loop back
exit	SWI	SWI_Exit	;callin' it quits

	END