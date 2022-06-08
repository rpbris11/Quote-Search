import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


/**
 * Capstone Project - Quote of the Day Generator
 * Ryan Brisbane - COSC 489 - February 11th, 2022 ~10:30 pm
 * This Java program takes a .csv (semicolon separated? .ssv?) file and
 * asks for user input, and based on that input prints out either a random quote or a random quote from
 * a specific author that the user specifies.
 */
public class QuoteOutput {

    /**
     * I hope you remember this method from last semester, Mr. Kennedy! It's back!
     * The method reads each line of the .csv file and stores each value per line in an
     * ArrayList and each of those ArrayLists within another ArrayList for easy access and use.
     * @param line a given line from the .csv file
     * @return ArrayList containing the values of that line
     */
    private List<String> getLines(String line){
        List<String> values = new ArrayList<String>();
        try(Scanner rowReader = new Scanner(line)){
            rowReader.useDelimiter(";");
            while(rowReader.hasNext()){
                values.add(rowReader.next());
            }
        }
        return values;
    }

    /**
     * The main method. Runs through the .csv file and then proceeds to ask for user input and loops
     * until the user wishes to exit.
     * @param args
     */
    public static void main(String[] args){
        QuoteOutput quoteGenerator = new QuoteOutput();
        //Reading the .csv file and storing within the ArrayList of ArrayLists
        List<List<String>> inputFile = new ArrayList<>();
        try(Scanner read = new Scanner(new File("quotes_all.csv"))){
            while(read.hasNextLine()){
                inputFile.add(quoteGenerator.getLines(read.nextLine()));
            }
        }catch(IOException e){
            System.err.println(e);
        }
        //Variables declared here for use within the active loop
        Random rng = new Random();
        Scanner in = new Scanner(System.in);
        String input;

        System.out.println("Hello!");
        //user input begins here
        do{
            System.out.println("\nPlease enter 1, 2, or 3 for:\n1. Print random quote" +
                    "\n2. Search by author and print quote\n3. Exit");
            input = in.nextLine();

            //Random quote with no specific author
            if(input.equals("1")){
                int number = rng.nextInt(75967);
                String quote = inputFile.get(number).get(0);
                String author = inputFile.get(number).get(1);
                System.out.println("\"" + quote + "\"" + " - " + author);

            //random quote from a specific author
            }else if(input.equals("2")){
                System.out.print("Please enter the last name you would like to search for: ");
                String name = in.nextLine();
                int number;
                String quote = "";
                String author = "";
                //Picks a quote, and if the last name doesn't match, picks a different one and tries again..
                //until it does match
                while(!author.contains(name)){
                    number = rng.nextInt(75967);
                    quote = inputFile.get(number).get(0);
                    author = inputFile.get(number).get(1);
                }
                System.out.println("\"" + quote + "\"" + " - " + author + "\n");

            //Error condition
            }else if(!input.equals("1") && !input.equals("2") && !input.equals("3")){
                System.out.println("Input not recognized. Please try again.");
            }
        //Exit condition
        }while(!input.equals("3"));

        System.out.println("Thank you, have a nice day! Exiting...");
        System.exit(1);
    }
}
