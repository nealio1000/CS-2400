;Neal Friedman
;CS 2400
;ARM Assignment #2
;countbits and bestbits
;passed to carm.c



	AREA ARM2, CODE, READONLY
|x$codeseg|
	
	EXPORT	countbits, bestbit
countbits	MOV	r5, r0		;pass argument to register
		MOV	r0, #0		;initialize total to 0
		MOV	r3, #32		;initialize counter to 32
LOOP		AND	r1, r5, #1	;is LSB a one?
		CMP	r1, #1		;Yes or No?
		ADDEQ	r0, r0, #1	;icrement total
		SUB	r3, r3, #1	;decrement counter
		MOV	r5, r5, LSR #1	;shift to the right
		CMP	r3, #0		;are we done  counting?
		BGT	LOOP		;loop back until all bits have been checked
		MOV	pc,lr
		
bestbit		
		END