	; **********************************
	;  File: PrintHexa.s
	;  Programmer: I. Georgiev
	;  Description: A Program to print a register in hexadecimal form
	;               in hexadecimal form
	;  Project: PrintHexa.arj               
	;  Date: October 2002
	;************************************
	
	AREA PrintHexa, CODE, READONLY
SWI_WriteC	EQU	&0     ;output character in r0 
SWI_CR		EQU	&0a
SWI_LF		EQU	&0d
SWI_Exit	EQU	&11    ;finish program
	ENTRY

	LDR	r1, Number	;load value
	MOV	r5, r1
	BL	Print
	LDR	r2, Number2
	MOV	r5, r2
	BL	Print
	LDR	r3, Number3
	MOV	r5, r3
	BL	Print
	CMP	r2, r3  ; compare r0 and r1
	BHI	Swap
	B	Continue
Swap
	MOV 	r4, r3
	MOV 	r3, r2
	MOV 	r2, r4
	CMP	r1, r2
	BHI	Swap2
Continue
	CMP	r2, r3
	BHI	Swap2
	B	Done
Swap2
	MOV	r4, r2
	MOV	r2, r1
	MOV	r1, r4
	BHI	Swap
Done	MOV	r0, #SWI_CR
	SWI	SWI_WriteC
	MOV	r0, #SWI_LF
	SWI	SWI_WriteC
	MOV	r5, r1
	BL	Print
	MOV	r5, r2
	BL	Print
	MOV	r5, r3
	BL	Print
	SWI 	SWI_Exit	;finish
Number	DCD 	&09876543
Number2 DCD	&00000002
Number3 DCD	&00000001
Print	MOV	r4,#8		;count of nibbles = 8
LOOP	MOV	r0,r5,LSR #28	;get top nibble
	CMP 	r0, #9		;hexanumber 0-9 or A-F
	ADDGT 	r0,r0, #"A"-10	;ASCII alphabetic
	ADDLE 	r0,r0, #"0"	;ASCII numeric
	SWI 	SWI_WriteC	; print character
	MOV	r5,r5,LSL #4	;shift left one nibble
	SUBS	r4,r4, #1	;decrement nibble count
	BNE	LOOP		;if more nibbles,loop back
	MOV	r0, #SWI_CR
	SWI	SWI_WriteC
	MOV	r0, #SWI_LF
	SWI	SWI_WriteC
	MOV 	pc, r14		;return
	END