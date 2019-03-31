/*
 *   Intro to Programming CSE 1310
 * University of Texas at Arlington
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author jcmtiernan
 * @esited by Shivangi Vyas
 */
public class Lab5Part6
{
    public static void main(String[] args) 
    {
    	Scanner kb = new Scanner(System.in);
        String answer="";								//LAb5Part4
    
    	do
    	{
        int numLetters = 20;  // 1.c) Change to match words/phrases in MakeWordArray
        int numberOfWordsToGuess =20;// 1.d) Change to match words in MakeWordArray
        String wordToGuess;
        char[] guesses = new char[numLetters];
        char[] correctGuess = new char[numLetters];
        char[][] wordArray = new char[30][30];// 1.a)  Declare a 2D array of at least 30 rows and 30 columns to 
        // hold the words that can be guessed by the user.  Just
        // declare the array here and initialize its size.
        String printGuess;
        int count = 0;
        int wrongCount = 0;
        int inWord = 0;
        int indexWord = 0;
        int noBlank = 0;
        boolean done = false;
        Scanner input = new Scanner(System.in);
        
        
        MakeWordArray(wordArray);								// 1.e) call MakeWordArray method to put words into 2D array
        
     
        int r = (int)(Math.random() * numberOfWordsToGuess);		   /*   Choose a word   */
        
        wordToGuess = selectWord(r,wordArray);// 2.b) pass in array as second parameter
        
        System.out.println("Welcome to the game!  ");
        System.out.println("Enter letters to guess the word.  Get done before the doll is hung!\n");
        System.out.println("The number of letters in your word are " + wordToGuess.length());
        	// 6)  Give the user an indication of the number of letters in the
			// selected word.  You can print blanks or you can just tell how
        	// many letters are in the word or do something else to give this info.

        
       for (int i= 0; i < numLetters; i++)
        {
            guesses[i] = ' ';
            correctGuess[i] = ' ';
        }
        
        while (!done)
        {
        	 boolean flag=true;  
            System.out.print("\nPlease enter a lowercase letter for your guess: ");
            guesses[count] = input.next().charAt(0);  // What is done here?
         
            //Check if word is repeated
            for(int h=0;h<count;h++)
            {
            if (guesses[count]== guesses[h])
            {
            	System.out.println("You have already guessed this letter");
            	flag=false;
            }
            }    
            
            
            if (wordToGuess.indexOf(guesses[count]) == -1)
            {   													// The letter is not in the word
            	
            	
                if(flag) {
             if ((wrongCount = hangDoll(wrongCount)) > 7)  						// 3.a) Why does this check > 7?
                {
                    done = true;
                    System.out.println("\nSo sorry. You lose.");
                   
                }
                }
            }
            else
            {   // 3.b)  Does this section correctly record the guesses?  Why or why not?
                // 3.c)  If needed, correct this section to record all guesses.
                // 3.d)  What happens if the user guesses a letter they have already used?
                // 3.e)  Indicate to the user if they guess a letter they already used.
                indexWord = wordToGuess.indexOf(guesses[count], inWord);
                correctGuess[indexWord] = guesses[count];
                printGuess = String.valueOf(correctGuess);
                noBlank = printGuess.indexOf(' ');
                printGuess = printGuess.substring(0, wordToGuess.length());
                System.out.println("**"+printGuess+"**");
                while(indexWord>=0)
                {
                	String check=wordToGuess.substring(indexWord+1);
                	String yes=wordToGuess.substring(0,indexWord+1);
                	 if (check.indexOf(guesses[count]) == -1)
                     {  
                		 indexWord=-1;
                     }
                	 else
                	 {
                indexWord = check.indexOf(guesses[count], inWord) + yes.length() ;
                correctGuess[indexWord] = guesses[count];
                printGuess = String.valueOf(correctGuess);
                noBlank = printGuess.indexOf(' ');
                printGuess = printGuess.substring(0, wordToGuess.length());
                System.out.println("**"+printGuess+"**");
                	 }
                }
               
                if (wordToGuess.equalsIgnoreCase(printGuess))
                {
                    done = true;
                    System.out.println("\nGreat! You win!");
                    System.out.println("You are a "+ wordToGuess +" student!");
                    
                }                
            	}
            
            count++;
        }  
        
        // 4.a) Modify the game so that the user can play multiple times if desired.
        System.out.println("\nThank you for playing!");
        System.out.print("Do you want to play again (y/n)? ");
		answer = kb.next();
		if(answer.contains("n"))
		{
			System.out.print("You have a wonderful day...");
		}
		System.out.println("\n\n");
    	}while (answer.equalsIgnoreCase("y"));
    }
    
    public static char [][] MakeWordArray (char [][] guessArray)
    {
    	File inputFileVar = new File("C:/Users/Dhruvi Vyas/eclipse-workspace/Lab5/src/words");
    	boolean fileValid = true;
    	Scanner inFile = null;
        try{
        	inFile = new Scanner(inputFileVar);         
            
        }
        catch(FileNotFoundException Fnfe){
            System.err.println(Fnfe.getMessage());
            fileValid=false;
        }
        if (fileValid)
        {
        	
            
            String readData = "";
            while(inFile.hasNextLine()){
                readData = inFile.next(); 
               
            
          
            char[][] Array= new char[30][30];
            int count = 0;
           
            for (int i = 0; inFile.hasNextLine(); readData=inFile.nextLine())
            {
            	int j=0;
        
                for(count = 0; count < readData.length(); count++)
                {
                    Array[i][j] = readData.charAt(count); 
                    guessArray[i][j]= Array[i][j];
                   
                    j++;
                   }
                System.out.println(" "); i++;
            }
            return guessArray;
        }
        }
        
            return null;
        
    }
   
    public static String selectWord(int roll, char wordArray[][])  // 2.a)add the word array as parameter    
    {
    	char [] word = new char [30];
    for (int i=0; i<30;i++)
    {
    	word[i]= wordArray [roll] [i];
    	
    }
    		
    	String yourword= new String(word);
       yourword= yourword.trim();				// 2.c) Select the row in the word array
       											// indicated by 'roll' to be the chosen 
       											// word for the game. Turn the array into 
       return yourword;									// a string and return the string of the word.
        
    }
   
    
    public static int hangDoll(int guess)
    {
        System.out.println("");
        System.out.printf("%10s%2s%4s\n"," "," |",(guess>=1?"---+":" "));
        System.out.printf("%10s%2s%4s\n"," "," |",(guess>=2?"   o":" "));
        System.out.printf("%10s%2s%3s%1s%1s\n"," "," |",
                (guess>=3?"  /":" "),(guess>=4?"@":" "),(guess>=5?"\\":" "));
        System.out.printf("%10s%2s%3s%2s\n"," "," |",
                (guess>=6?"  /":" "),(guess>=7?" \\":" "));
        System.out.printf("%10s%2s\n"," "," |        ");
        System.out.printf("%10s%2s\n"," "," |        ");                

        System.out.printf("%10s%13s\n"," ","+----------+");
        System.out.printf("%10s%13s\n"," ","|          |");
        System.out.printf("%10s%13s\n"," ","+----------+");
        return ++guess;  			/////////5
                         
    }

}