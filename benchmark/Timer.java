/*	This prgram demonstrates the use of
**	System.nanoTime() for timing a section of the
**	program.
*/

import java.util.*;
public class Timer  {

	public static void main (String [] arg) {
		String name = "timing";		//just here to get hashcode
		long first = System.nanoTime();	//get starting time
		long sum = 0;
		for (int i = 0; i< 1000000; i++) {
			sum = (long) (3.2 * name.hashCode()) + 1;
		} //for
		long last = System.nanoTime();	//get ending time
		long ans = last - first;
		System.out.printf("The answer is %,d nanoseconds\n",ans);

	} //main

}
