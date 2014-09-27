	AREA 	Example, CODE, READONLY	; name this block of code
	ENTRY							; mark first instruction
									; to execute
start   MOV     r5, #0x7F
	MOV	r5, r5, ROR #8
	MOV     r6, r5
	ADDS	r7,r5,r6	;after execution the flags should be: 
	BLGE	firstfunc   ;the branch will be taken or not
	SWI	0x11			
firstfunc					
	MOV	r7, #0x00		
	MOV	pc, lr			

	END								; mark end of file
