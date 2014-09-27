	; **********************************
	;  File: PrintAscii.s
	;  Programmer: I. Georgiev
	;  Description: A Program to ouput a text string immediately
	;               following the call
	; It is based on a subroutine to print a null-terminated string
	;                 ALIGN
	;  Project: PrintAscii.arj               
	;  Date: February 2013
	;************************************
	
	AREA PrintAscii, CODE, READONLY
SWI_WriteC	EQU	&0     ;output character in r0 
SWI_Exit	EQU	&11    ;finish program
		ENTRY
		ADR r2,text
		BL  Print	;call print subroutine
		=   "Text to print",&0a,&0d,0   
		ALIGN
		SWI 	SWI_Exit	;finish
Print		LDRB	r0,[r2], #1	;get a character
		CMP 	r0, #0		;end mark NUL?
		SWINE 	SWI_WriteC	;if not, print
		BNE	Print
		MOV	pc, r14		;return
text		DCB	"Text to print",&0a,&0d,0 
 		END	
;*****************************
;The program works and prints!
;***************************** 