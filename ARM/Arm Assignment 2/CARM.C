/* this is a main program used to test the ARM functions
** countbits() and bestbit
*/

#include <stdio.h>
extern int countbits(int num);
extern int bestbit(int a, int b, int c, int d);

int main(int argc, char **argv)  {
	int n;
	int i;
	int args[] 		= {0, 16, 1049, 4095, -1}; /* args for countbits */
	int results[]	= {0,  1,    4,   12, 32}; /* expected result for arg */

	int vals[][4]	= {{    0,     0,     0,     0},		/* args for bestbit */
					   {    0,     1,     2,     3},
					   {    5,    13,    29,    61},
					   {16384, 32767, 17408,    24},
					   {16383, 32767,    96,    36}};

	int result2[]	= {-1,  0,    0,   14, 5}; /* expected result for vals */


	printf("\n\n            Testing countbits  \n\n\n");
	for (i=0; i<5; i++) {
	 	n = countbits(args[i]);
		printf("For %6d countbits is %2d -- expecting %3d\n",args[i],n,results[i]);	
	}

	printf("\n\n            Testing bestbit  \n\n\n");
	for (i=0; i<5; i++) {
	 	n = bestbit(vals[i][0],vals[i][1],vals[i][2],vals[i][3]);
		printf("For {%6d,%6d,%6d,%6d}, bestbit is %2d -- expecting %3d\n",
		    vals[i][0],vals[i][1],vals[i][2],vals[i][3],n,result2[i]);
	}
	return 0;
}

