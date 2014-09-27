package peanut;

/* Neal Friedman
 * CS 2400
 * Peanut Assembler
 */
import java.util.*;
import java.io.*;

public class PeanutAssembler 
{
    static  int totalLines;
    static  int opLines;
    
    public static void main(String[] args) throws Exception
    {
        Scanner input = new Scanner(new FileReader("stats.asm.txt"));
        PrintWriter output = new PrintWriter("AssemblerOutput.txt"); 
        String[] labels = new String[300];
        String[] opCode = new String [300];
        String[] variables = new String [300];
        String[] data = new String[300];
        Map<String,Integer> variableTable = new HashMap<String,Integer>();
        Map<String,Integer> branchTable = new HashMap<String,Integer>();
        totalLines = 0; //initialize total line counter
        opLines = 0;    //initialize total opcode counter
     
        inputData(input, labels, opCode, variables, data);
        translateData(labels, opCode, variables, data, variableTable, 
                        branchTable);
        outputData(output, opCode, variables, data, variableTable);

    }
//****************************************************************************
// Input Data
    public static void inputData(Scanner input, String[] labels, 
                                    String[] opCode, String[] variables, 
                                    String[] data)
    {
        StringTokenizer st;
        String line;
        int i = 0;
        
        while(input.hasNext())
        {
            line = input.nextLine();
            st = new StringTokenizer(line);

            if(line.contains("data")) //Break loop when scanner reads "data"
            {
                break;
            }
            
            else if(line.contains(":")) //do if line has a label on it
            {
                labels[i]= st.nextToken();
                labels[i] = labels[i].substring(0, labels[i].length() - 1);
                opCode[i] = st.nextToken();
                
                variables[i] = st.nextToken();
            }
            
            else if(line.contains("halt")) //Read in the final opcode
            {
                labels[i] = "";
                opCode[i] = st.nextToken();
                variables[i] = "";
            }
            
            else      //no labels, read opcode and variable
            {
                labels[i]= "";
                opCode[i] = st.nextToken();
                
                variables[i] = st.nextToken();
            }
            
            opLines++;
            i++;
            totalLines++; 
        }
        
        while(input.hasNext() )   //Read in data after the halt
        {
            line = input.nextLine();
            st = new StringTokenizer(line);
            data[i]=st.nextToken();
            i++;
            totalLines++;
        }
    }
//****************************************************************************
// Translate Data
    public static void translateData(String[] labels, String[] opCode, 
                                        String[] variables, String[] data,  
                                        Map variableTable, Map branchTable)
    
    {   
        //Translate Opcode
      
        for(int i = 0; i < opLines; i++)
        {
          
            if(opCode[i].equals("add"))      //add becomes 1
            {
                opCode[i] = "1";
            }
            else if(opCode[i].equals("sub")) //sub becomes 2
            {
                opCode[i] = "2";
            }
           else if(opCode[i].equals("mult")) //mult becomes 3
           {
               opCode[i] = "3";
           }
           else if(opCode[i].equals("div")) //div becomes 4
           {
               opCode[i] = "4";
           }
           else if(opCode[i].equals("read")) //read becomes 5
           {
               opCode[i] = "5";
           }
           else if(opCode[i].equals("print")) //print becomes 6
           {
               opCode[i] = "6";
           }
           else if(opCode[i].equals("load")) //load becomes 7
           {
               opCode[i] = "7";
           }
           else if(opCode[i].equals("store")) //store becomes 8
           {
               opCode[i] = "8";
           }
           else if(opCode[i].equals("iload")) //iload becomes 9
           {
               opCode[i] = "9";
           }
           else if(opCode[i].equals("br")) //br becomes 10
           {
               opCode[i] = "10";
           }
           else if(opCode[i].equals("beq")) //beq becomes 11
           {
               opCode[i] = "11";
           }
           else if(opCode[i].equals("blt")) //blt becomes 12
           {
               opCode[i] = "12";
           }
           else if(opCode[i].equals("halt")) //halt becomes 13      0
           {
               opCode[i] = "13";
           }
       }
      //End of Opcode Translation
        
        
      //Make hashmap for labels, <label, address to branch to>
       for (int i = 0; i < opLines; i++)
       {
           if(!labels[i].equals(""))
           {
           branchTable.put(labels[i], i*2);
           }
       }
       
       // Line up branch labels in array and start at index 0
       int j = 0;
       for(int i = 0; i < opLines; i ++)
       {
           if(!labels[i].equals(""))
           {
               labels[j] = labels[i];
               j++;
           }
       }
       
       // Make Hashmap for variables, address(start at 100)
       int hashAddress = 100;
       for(int i = 0; i < opLines; i++)
       {
           
           if(variables[i].matches(".*\\d.*"))
           {
               variableTable.put(variables[i], variables[i]);
           }
           else if(opCode[i].equals("13"))
           {
               variableTable.put(variables[i], 0);
           }
           else if(!variableTable.containsKey(variables[i]))
           {
           variableTable.put(variables[i], hashAddress);
           hashAddress++;
           }
       }
       
       //replace branch labels in variable column with address to branch to
       int k = 0;
       for(int i = 0; i < opLines-1; i ++)
       {
           if(labels[k].equals(variables[i]))
           {
               variableTable.put(variables[i], branchTable.get(labels[k]));
               k++;
               i = 0;
           }
       }
    }
//****************************************************************************
// Output Data
    public static void outputData(PrintWriter output, String[] opCode, 
                                    String[] variables, String []data, 
                                    Map variableTable)
    {
        for (int i = 0; i < opLines; i++) //output opcode and variables to file
        {
            output.println(opCode[i]+ "\t" + variableTable.get(variables[i])); 
            
        }
        output.println("0\t0"); //0 , 0 after the line with opcode 13
      
        for(int i = opLines; i < totalLines; i++) //output data to file
        {
            output.println(data[i]);
        }
        output.close(); //close output
    }
}