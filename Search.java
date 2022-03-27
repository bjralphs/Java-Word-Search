import java.io.*;
import java.util.*;


class Search {
    //These variables will hold our location as we iterate through the 2D array
    static int R, C;
    // Here we declare the int variables for moving in 8 directions.
    static int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
    // This single function searches in all
    // 8-directions from a given point
    //and returns a boolean value of whether the
    //searched word was found or not.
    public static boolean search2DArray(char[][] wordGrid, int r, int c, String givenWord){
        // Return if first character doesnt match
        //what we are looking for.
        if (wordGrid[r][c] != givenWord.charAt(0)){
            return false;
        }
        //This variable will be used to determine if word
        //is a match or not.
        int length = givenWord.length();
        // Search word in all 8 directions starting from (row (r), column (c))
        for (int direction = 0; direction < 8; direction++) {
            // Initialize starting point
            int j, rowD = r + x[direction], columnD = c + y[direction];

            // match remaining characters after first letter
            for (j = 1; j < length; j++) {
                // To avoid an index out of bound error break
                if (rowD >= R || rowD < 0 || columnD >= C || columnD < 0)
                    break;

                // If no match, break loop
                if (wordGrid[rowD][columnD] != givenWord.charAt(j))
                    break;

                // Adjust movement within grid
                columnD += y[direction];
                rowD += x[direction];
                
            }

            // If all character matched,
            // then we should be good with desired word
            if (j == length) {
                return true;
                 }   
    }
return false;
}
//This function returns the ending cordinates
//of where the word's last letter was found
//within the 2D array. This function exists because
//functions in java can only return 1 value and the 
//ending cords are needed aswell as the boolean value. 
public static void endCords(char[][] wordGrid, int r, int c, String givenWord){
        // Return if first character doesnt match
        //what we are loojing for.
        if (wordGrid[r][c] != givenWord.charAt(0)){
            return;
        }
        //This variable will be used to determine if word
        //is a match or not.
        int length = givenWord.length();

        // Search word in all 8 directions starting from (row (r), column (c))
        for (int direction = 0; direction < 8; direction++) {
            // Initialize starting point
            int j, rowD = r + x[direction], columnD = c + y[direction];
            // match remaining characters after first letter
            for (j = 1; j < length; j++) {
                // To avoid an index out of bound error break
                if (rowD >= R || rowD < 0 || columnD >= C || columnD < 0)
                    break;

                // If no match, break loop
                if (wordGrid[rowD][columnD] != givenWord.charAt(j))
                    break;

                // Adjust movement within grid.
                columnD += y[direction];
                rowD += x[direction];
                
            }
            //If characters match
            //then we should be good with desired word
            //Output ending cords.
            if (j == length) {
                columnD -= y[direction];
                rowD -= x[direction];
                
                String o = String.valueOf(rowD);
                String p = String.valueOf(columnD);
                System.out.println("end: "+ o + ", " + p);
                }
        
    }

}
    // Searches given word in a given word list
    // Outputs to terminal the location of search word.
    static void wordSearch(char[][] wordGrid, String givenWord) {
     //iterate through whole 2D array
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (wordGrid[r][c] == givenWord.charAt(0)  && search2DArray(wordGrid, r, c, givenWord)) {
                    System.out.println(givenWord + " found at start: " + r + ", " + c);
                    endCords(wordGrid, r, c, givenWord);
                }
            }
        }
    }
    // Main driver code
    public static void main(String args[])
    {
        Scanner doc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter name of txt file to search for: ");
        String txtDocument = doc.nextLine();
        //collect file to search and open it.
        List<String> input = new ArrayList<>();
        Scanner fin = null;
        try {
            fin = new Scanner(new File(txtDocument));
        } catch (FileNotFoundException x) {
            System.out.println("File open failed.");
            x.printStackTrace();
             System.exit(0);
         }
         String line;
         while (fin.hasNextLine()) {
            line = fin.nextLine();
            input.add(line);
        }
        //Determine size of grid.
        String index0 = input.get(0);
        int columns = index0.length();
        //Max size for word map is 100x100.
        // i.e. a single word cant be longer than 100.
        // This program will end if words are greater than 100 characters long.
        if (columns > 100){
            System.out.println("Your words are too big for this word map! Exiting program now...");
            System.exit(0);
        }
        R = columns;
        C = columns;
        int rows = columns; 
        int column = columns;
        char[][] arr = new char[rows][column];
        int row = 0;
        //This loop creates the grid from the mixed up words.
        for (String word : input) {
            //break loop before actual words to find.
            if (word == null || word == ""){
                break;
            }
            for (int a = 0; a < word.length(); a++){
                arr[row][a] = word.charAt(a);
            }
            row++;
        }
         ArrayList<String> searchWords = new ArrayList<String>(); //Creates a list to hold words to find.
         //This loop creates a list of actual words to search for.
         for (String word : input) {
            if (word.length() < columns || word.length() > columns ){
                searchWords.add(word);
            }
        }
        searchWords.remove(""); //removes seperating blank space between mixed and non-mixed words.

        //If the list of words is greater than 200, the program will exit.
        if (searchWords.size() > 200){
            System.out.println("You have too many words to search for! Exiting program now...");
            System.exit(0);
        }
        //System.out.println(Arrays.deepToString(arr).replace("], ", "]\n")); //This is to print out the 2D array
        //Executing code for finding words within 2D array
        for (String word : searchWords){
            if (word.length() > columns) { //JABBERWOCKY
                System.out.println(word + " not found");
            }

            else {
                wordSearch(arr, word);
            }
        }
    }
}