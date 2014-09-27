; Neal Friedman
; CS 2400
; Arm Assignment #1
; Program 1
; This program takes the sum of 20 words

	AREA PROGRAM1, CODE, READONLY
SWI_WriteC	EQU	&0     ;output character in r0 
SWI_Exit	EQU	&11    ;finish program
SWI_LI		EQU	&0a
SWI_NE		EQU	&0d
	ENTRY
	LDR	r1, =animas	;load the word
	LDR	r4, #20		;initialize counter to 20
	LDR	r3, [r1]	
Pr	BL	Print
	SUBS	r4, r4, #1
	ADD	r1, r1, #4
	LDR	r3, [r1]
	ADD	r5, r5, r3
	CMP	r4, #0
	BGE	Pr
	MOV	r3, r5
	BL	Print
	SWI 	SWI_Exit	;finish
Print	MOV	r2,#8		;count of nibbles = 8
LOOP	MOV	r0,r3,LSR #28	;get top nibble
	CMP 	r0, #9		;hexanumber 0-9 or A-F
	ADDGT 	r0,r0, #"A"-10	;ASCII alphabetic
	ADDLE 	r0,r0, #"0"	;ASCII numeric
	SWI	SWI_LI
	SWI 	SWI_WriteC	; print character
	SWI	SWI_NE
	SWI	SWI_WriteC	
	MOV	r3,r3,LSL #4	;shift left one nibble
	SUBS	r2,r2, #1	;decrement nibble count
	BNE	LOOP		;if more nibbles,loop back
	MOV 	pc, r14		;return
	
	AREA    Data, DATA
animas	DCD	&00000001
	DCD	&00000001
	DCD 	&00000001
	DCD	&00000001
	DCD	&00000001
	DCD 	&00000001
	DCD	&00000001
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	DCD	&00000002
	
	END
