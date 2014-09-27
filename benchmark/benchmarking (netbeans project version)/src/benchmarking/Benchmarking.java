package benchmarking;


/* This program was written by Neal Friedman
 * CS 2400 with Dr. Aaron Gordon
 * Assignment 4
 * 
 * The purpose of this program is to act like a basic benchmarking utility.
 * The program asks the user to input a desired size of a 2D array. Then the 2D
 * array is filled with very large floating point numbers. The idea of this 
 * benchmarking program is to see how long it takes for the computer to add the 
 * values in the 2D array by going through it by rows and by columns. Then the 
 * program compares the times for rows versus the time for columns and sees 
 * which addition was faster. The results are then displayed to the user. 
 * Unfortunately, the size of the array is limited due to the size of the heap.
 * Specialized software and hardware would be required to perform an accurate
 * measure of FLOPS on large amounts of data.
 */


import java.util.Scanner;

public class Benchmarking 
{
    public static void main (String args[]) 
    {
        final int rowSize; // Choose amount of rows
        final int colSize; // Choose amount of columns
        float rowSum = 0; //sum of the table by rows
        float colSum = 0; //sum of the table by columns
        Scanner userInput = new Scanner(System.in); //Scanner for user input
        
        //Print warning message to screen
        System.out.println("WARNING: You are adding very large numbers.");
        System.out.println("If you set rows or column size to be too big, "
                                + "program may crash.");
        System.out.println("I suggest not using a table larger than "
                                + "20000 x 20000.\n");
        
        
        //User enters amount of rows they desire
        System.out.print("Enter amount of rows: ");
        rowSize = userInput.nextInt();
        if(rowSize <= 0)
        {
            System.out.println("You cannot enter a row size that is less "
                                    + " or equal to zero");
            System.exit(1);
        }
            
        //User enters amount of columns they desire
        System.out.print("Enter amount of columns: ");
        colSize = userInput.nextInt();
        if(colSize <= 0)
        {
            System.out.println("You cannot enter a column size that is less "
                                    + " or equal to zero");
            System.exit(1);
        }
        System.out.println();
        
        float [][] a = new float [rowSize][colSize];  // construct 2d array
        
        //fill rows and colums
        for (int row = 0; row < rowSize; row++)
        {
            for(int col = 0; col < colSize; col++)
            {   // fill 2D array with largest floats java can handle
                a[row][col] = (float)(Math.random()*1999999999); 
                
                //Print the data table, comment out for large tables
                //System.out.print(a[row][col] + "\t"); 
            }
            //System.out.println(); //comment out for large tables
        }
        
        //************add by rows**************************************
        long rowStart = System.nanoTime();	//get starting time
        for (int row = 0; row < rowSize; row++) 
        {
            for(int col = 0; col < colSize; col++)
            {
                rowSum += a[row][col];
            }
        }
        long rowEnd = System.nanoTime(); //get end time
        long rowTime = rowEnd - rowStart; // calculate total time adding

        System.out.println("\nThe sum by rows are: " + rowSum);
        System.out.println("The rows were summed in: " + rowTime + 
                                " nanoseconds");

        //**********end of add by rows***********************************

        
        //***************add by columns**********************************
        long colStart = System.nanoTime();	//get starting time
        for (int col = 0; col < colSize; col++) 
        {
            for(int row = 0; row < rowSize;row++)
            {
                colSum += a[row][col];
            }
        }
        long colEnd = System.nanoTime(); //get ending time
        long colTime = colEnd - colStart; // Calculate total time adding
        System.out.println("\nThe sum by columns are: " + colSum);	
        System.out.println("The columns were summed in: " + colTime + 
                                " nanoseconds");

        //*************end of add by columns*****************************
        
        
        // Which type of adding was faster and by how much
        if(rowTime < colTime)
        {
            System.out.println("\nAdding by rows was faster by " + 
                                    (colTime-rowTime) + " nanoseconds");
            //convert nanoseconds to seconds 
            System.out.println("Or " +((colTime-rowTime)/1000000000.0) + 
                                    " seconds");
        }								
        else if(rowTime > colTime)
        {
            System.out.println("\nAdding by columns was faster by " + 
                (rowTime-colTime) + " nanoseconds");
            //convert nanoseconds to seconds
            System.out.println("Or " +((rowTime-colTime)/1000000000.0) + 
                                    " seconds"); 
        }
        else
            System.out.println("The time it took to add by rows and by "
                                    + "columns were equal");
    }
}