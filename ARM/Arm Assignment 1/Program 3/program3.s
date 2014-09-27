	AREA PROGRAM3, CODE
SWI_WriteC	EQU	&0     ;output character in r0 
SWI_Exit	EQU	&11    ;finish program

	ENTRY
mainODD
	LDR	r1, OddNum	;load value
	ADD	r1,r1,#1
	AND	r1,r1,#1
	BL	Print1		;call print subroutine
	
	
OddNum	DCD 	0x5
Print1	MOV	r2,#8		;count of nibbles = 8
LOOP1	MOV	r0,r1,LSR #28	;get top nibble
	CMP 	r0, #9		;hexanumber 0-9 or A-F
	ADDGT 	r0,r0, #"A"-10	;ASCII alphabetic
	ADDLE 	r0,r0, #"0"	;ASCII numeric
	SWI 	SWI_WriteC	;print character
	MOV	r1,r1,LSL #4	;shift left one nibble
	SUBS	r2,r2, #1	;decrement nibble count
	BNE	LOOP1		;if more nibbles,loop back
	
format	ADR 	r2,text
	BL  	Print		;call print subroutine
	ALIGN
	SWI 	SWI_Exit	;finish
Print	LDRB	r0,[r2], #1	;get a character
	CMP 	r0, #0		;end mark NUL?
	SWINE 	SWI_WriteC	;if not, print
	BNE	Print
	
text	DCB	"\n",&0a,&0d,0 
	
mainEven
	LDR	r1, EvenNum	;load value
	ADD	r1,r1,#1
	AND	r1,r1,#1
	BL	Print2		;call print subroutine
	SWI 	SWI_Exit	;finish
EvenNum	DCD 	0x6
Print2	MOV	r2,#8		;count of nibbles = 8
LOOP2	MOV	r0,r1,LSR #28	;get top nibble
	CMP 	r0, #9		;hexanumber 0-9 or A-F
	ADDGT 	r0,r0, #"A"-10	;ASCII alphabetic
	ADDLE 	r0,r0, #"0"	;ASCII numeric
	SWI 	SWI_WriteC	; print character
	MOV	r1,r1,LSL #4	;shift left one nibble
	SUBS	r2,r2, #1	;decrement nibble count
	BNE	LOOP2		;if more nibbles,loop back
	MOV 	pc, r14		;return

Line	=	"     ",&0a,&0d,0
	ALIGN
	END