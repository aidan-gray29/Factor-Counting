import java.io.*;
import java.math.BigInteger;

public class Main {
    public static int easilyDivisible(BigInteger num, BigInteger chosen){
        if(num.mod(chosen).compareTo(BigInteger.ZERO) == 0){
            num = num.divide(chosen);
            return 1+ easilyDivisible(num, chosen);
        }
        return 0;
    }
    public static void main(String[] args) throws IOException{
        long startTime = System.nanoTime();
        FileReader input = new FileReader("input.txt");
        BufferedReader bufferRead = new BufferedReader(input);
        File out = new File("output.txt");
        if(!out.exists()){
            out.createNewFile();
        }
        FileWriter output = new FileWriter(out);
        BufferedWriter bufferWrite = new BufferedWriter(output);
        int count = Integer.parseInt(bufferRead.readLine());               // total number of "easily-dividable" numbers
        int[][] easy = new int[count][2];                                  // array of given "easily-dividable" numbers and their scores
        for( int i = 0; i < count; ++i){
            easy[i][0] = Integer.parseInt(bufferRead.readLine());
        }
        while(bufferRead.ready()){                                         // run this loop for all given lines of pairs
            String[] temp = bufferRead.readLine().split("\\s");      // take a line and split it at space characters
                                                                           // run the recursive algo for both nums in a pair
            BigInteger currentBig = new BigInteger(temp[0]);
            for(int i = 0; i < easy.length; ++i){
                easy[i][1] += easilyDivisible(currentBig,BigInteger.valueOf(easy[i][0]));
            }
            currentBig = new BigInteger(temp[1]);
            for(int i = 0; i < easy.length; ++i){
                easy[i][1] += easilyDivisible(currentBig,BigInteger.valueOf(easy[i][0]));
            }
        }
        // print the results to the output file the easily dividable counter
        for(int i = 0; i < count; ++i){
            bufferWrite.write(easy[i][0]+" "+easy[i][1]+"\n");
        }
        bufferWrite.close();
        long endTime = System.nanoTime();
        long elapsed = (endTime - startTime)/1000000;
        System.out.println(elapsed+ " milliseconds");
    }
}
