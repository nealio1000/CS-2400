package peanut;

/* Neal Friedman
 * CS 2400
 * Peanut Simulator
 */
import java.util.*;
import java.io.*;

public class PeanutExecutor {

    static int acc;
    static int counter;
    static int dataArray[];
    static Scanner inFile;

    public static void main(String[] args) throws Exception {
        //verbose output header
//        System.out.println("Printer \tOpCode \t\tMemLoc \t       "
//                              + "Acc   counter \t[100] \t[101] "
//                              + "\t[102] \t[103] \t[104] \t[105] \t[106] "
//                              + "\t[112]");


        System.out.println("Printer:"); //non verbose output header

        inFile = new Scanner(new FileReader("AssemblerOutput.txt"));
        dataArray = new int[1000];
        acc = 0; //accumulator 
        counter = 0; //Counter used for branch instructions
        int tmpCounter = 0;

        while (inFile.hasNext()) {
            int tmp = 0;
            dataArray[tmpCounter] = inFile.nextInt();
            if (tmpCounter % 2 == 0) //all even integers are opCode
            {
                tmp = tmpCounter;
            }

            if (dataArray[tmp] == 0) //Ends the loop at the first 0
            {
                break;
            }

            tmpCounter++;
        }

        inFile.nextInt();  //Skips the second 0

        for (int i = 0; dataArray[i] != 0; i += 2) {

            Calc(dataArray[i], dataArray[i + 1]);     // runs the switch method
            i = counter;       //keeps the index with the counter for branching
            counter += 2;              //increments by 2 for opcode memloc pair
        }
    }

    public static void Calc(Integer opCode, Integer memLoc) {

        switch (opCode) {
            //add the value in the memory location to the accumulator
            case 1:
                acc = acc + dataArray[memLoc];
                break;

            // subtract the value at the memory location from the acc.    
            case 2:
                acc = acc - dataArray[memLoc];
                break;

            //multiply the acc. by the value at the memory location    
            case 3:
                acc = acc * dataArray[memLoc];
                break;

            //divide the acc.by the value at the memory location    
            case 4:
                acc = acc / dataArray[memLoc];
                break;

            //get a value from the data and put it in the memory location    
            case 5:
                dataArray[memLoc] = inFile.nextInt();
                break;

            // print the value stored at the memory location    
            case 6:
                System.out.println(dataArray[memLoc]);
                break;

            // load the acc. from the memory location    
            case 7:
                acc = dataArray[memLoc];
                break;

            // store the value of the acc. in the memory location    
            case 8:
                dataArray[memLoc] = acc;
                break;

            //load the acc. with the address
            case 9:
                acc = memLoc;
                break;

            //branch to the instruction at the address    
            case 10:
                counter = memLoc;
                counter = counter - 2;
                break;

            //if the acc. = 0 then branch to the instruction at the address    
            case 11:
                if (acc == 0) {
                    counter = memLoc;
                    counter = counter - 2;
                }
                break;

            //if the acc. < 0 then branch to the instruction at the address    
            case 12:
                if (acc < 0) {
                    counter = memLoc;
                    counter = counter - 2;
                }
                break;

            //halt the machine         
            case 13:
                System.exit(0);
        }
        //verbose output footer
//        System.out.println("\t\t" + opCode + "\t\t" + memLoc + "\t\t" + acc + 
//                                "\t" + counter  + "\t" + dataArray[100] + "\t" + 
//                               dataArray[101] + "\t" + dataArray[102] + "\t" + 
//                               dataArray[103] + "\t" + dataArray[104] + "\t" + 
//                               dataArray[105] + "\t" + dataArray[106] + "\t" + 
//                               dataArray[112]);
    }
}
