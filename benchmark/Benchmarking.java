/* This Benchmarking program was written by Neal Friedman
 * CS 2400 with Dr. Aaron Gordon
 * Assignment 4
 * 
 * The purpose of this program is to act like a basic benchmarking 
 * utility. The program has multiple tests that are defined by the 
 * size of the 2D array. The 2D array is then filled with 
 * very large floating point numbers. The idea of this benchmarking 
 * program is to see how long it takes for the computer to add 
 * the values in the 2D array by going through it by rows and by 
 * columns. The program compares the amount of time spent adding 
 * for rows versus the time for columns and sees which addition 
 * was faster. The results are then displayed to the user for each 
 * test. Feel free to change the row and column size for one of 
 * the tests. But if you specify too large of a table you will run 
 * out of memory. If you have a lot of memory you can run this 
 * program with the -Xmx4g (where this would change the heap to 4 
 * gigabytes). For example, you would run this program with 
 * "java -Xmx4g Benchmarking" . 
 *
 */

public class Benchmarking 
{
	static float [][] a;
	static int rowSize;
	static int colSize;	
	static long rowTime;
	static long colTime;
    public static void main (String args[]) 
    {
		
		//test 1: small 10 x 10 table
		System.out.println("***** Test 1: small 10 x 10 table *****\n");
        rowSize = 10;
        colSize = 10;
        System.out.println("Table size: " + rowSize + " x " + colSize);
        a = new float [rowSize][colSize];  // construct 2d array
        fillTable(rowSize,colSize);
        addRows(rowSize, colSize);
        addCols(rowSize, colSize);
        compareAdds(rowTime, colTime);
        
        //test 2: large 10000 x 10000 table
        System.out.println("***** Test 2: large 10000 x 10000 table "
								+ "******\n");
        rowSize = 10000;
        colSize = 10000;
        System.out.println("Table size: " + rowSize + " x " + colSize);
        a = new float [rowSize][colSize];  // construct 2d array
        fillTable(rowSize,colSize);
        addRows(rowSize, colSize);
        addCols(rowSize, colSize);
        compareAdds(rowTime, colTime);
        
        //test 3: Many rows, few columns 20000 x 2
        System.out.println("***** Test 3: Many rows, few columns " 
							+ "20000 x 2 ******\n");
        rowSize = 20000;
        colSize = 2;
        System.out.println("Table size: " + rowSize + " x " + colSize);
        a = new float [rowSize][colSize];  // construct 2d array
        fillTable(rowSize,colSize);
        addRows(rowSize, colSize);
        addCols(rowSize, colSize);
        compareAdds(rowTime, colTime);
        
        //test 4:few rows, many columns 2 x 20000
        System.out.println("****** Test 4:few rows, many columns " 
							+ "2 x 20000 ******\n");
        rowSize = 2;
        colSize = 2000;
        System.out.println("Table size: " + rowSize + " x " + colSize);
        a = new float [rowSize][colSize];  // construct 2d array
        fillTable(rowSize,colSize);
        addRows(rowSize, colSize);
        addCols(rowSize, colSize);
        compareAdds(rowTime, colTime);
    }   
       
    // Fill table with random floating point numbers
	public static void fillTable(int rowSize, int colSize)
    {
        for (int row = 0; row < rowSize; row++)
        {
            for(int col = 0; col < colSize; col++)
            {   // fill 2D array with largest floats java can handle
                a[row][col] = (float)(Math.random()*1999999999); 
                
                //Print the data table, comment out for large tables
                //System.out.print(a[row][col] + "\t"); 
            }
           // System.out.println(); //comment out for large tables
        }
	}
    // Add by Rows
    public static long addRows(int rowSize, int colSize)
    {
		System.out.println("----------------------------------------");
		float rowSum = 0;
        long rowStart = System.nanoTime();	//get starting time
        for (int row = 0; row < rowSize; row++) 
        {
            for(int col = 0; col < colSize; col++)
            {
                rowSum += a[row][col];
            }
        }
        long rowEnd = System.nanoTime(); //get end time
        rowTime = rowEnd - rowStart; // calculate total time adding
		
        System.out.println("The sum by rows are: " + rowSum);
        System.out.println("The rows were summed in: " + rowTime + 
                                " nanoseconds");
        System.out.println("----------------------------------------");
        return rowTime;
	}
        
    // Add by columns
	public static long addCols(int rowSize, int ColSize)
	{
		float colSum = 0;
        long colStart = System.nanoTime();	//get starting time

        for (int col = 0; col < colSize; col++) 
        {
            for(int row = 0; row < rowSize;row++)
            {
                colSum += a[row][col];
            }
        }
        long colEnd = System.nanoTime(); //get ending time
        colTime = colEnd - colStart; // Calculate total time adding
        System.out.println("The sum by columns are: " + colSum);	
        System.out.println("The columns were summed in: " + colTime + 
                                " nanoseconds");
        System.out.println("----------------------------------------");
        return colTime;
	}
          
    // Compare add times of rows and columns 
	public static void compareAdds(long rowTime, long colTime)
    {
        if(rowTime < colTime)
        {
            System.out.println("\nAdding by rows was faster by " + 
                                    (colTime-rowTime) + " nanoseconds");
            //convert nanoseconds to seconds 
            System.out.println("Or " +((colTime-rowTime)/1000000000.0) + 
                                    " seconds\n");
        }								
        else if(rowTime > colTime)
        {
            System.out.println("\nAdding by columns was faster by " + 
                (rowTime-colTime) + " nanoseconds");
            //convert nanoseconds to seconds
            System.out.println("Or " +((rowTime-colTime)/1000000000.0) + 
                                    " seconds\n"); 
        }
        else
            System.out.println("The time it took to add by rows and by "
                                    + "columns were equal\n");
	}
}
