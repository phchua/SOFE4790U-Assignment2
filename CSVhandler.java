import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

public class CSVhandler {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    //Student attributes index
    private static final int USERNAME_IDX = 0;
    private static final int EMAIL_IDX = 1;
    private static final int BOOKNAME_IDX = 2;
    private static final int BOOKEDITION_IDX = 3;
    private static final int BOOKACADYEAR_IDX = 4;
    private static final int BOOKCOURSECODE_IDX = 5;
    private static final int BOOKFACULTY_IDX = 6;

    //CSV file header
    private static final String FILE_HEADER = "username,email,bookName,bookEdition,bookAcadYear,bookCourseCode,bookFaculty";

    public static void writeCsvFile(String fileName, Listing addList) {

        FileWriter fileWriter = null;
    
        try {
            fileWriter = new FileWriter(fileName, true);
            //Write a new student object list to the CSV file
            //For the appending of listing
            // append every field
            // append comma delimiter in between
            // when done append line seperator
            fileWriter.append(addList.getUsername());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(addList.getEmail());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(addList.getBookName());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(String.valueOf(addList.getbookEdition()));
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(String.valueOf(addList.getBookAcadYear()));
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(addList.getBookCourseCode());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(addList.getBookFaculty());
            fileWriter.append(NEW_LINE_SEPARATOR);
            // System.out.println("Listing was added successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }

    public static List<Listing> readCsvFile(String fileName) {

        BufferedReader fileReader = null;
        //Create a new list of student to be filled by CSV file data
        List<Listing> allListings = new ArrayList<Listing>();
        List<String> allListingsString = new ArrayList<String>();

        try {



            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    //Create a new student object and fill his  data
                    Listing bookItem = new Listing(tokens[USERNAME_IDX], tokens[EMAIL_IDX], tokens[BOOKNAME_IDX], Integer.parseInt(tokens[BOOKEDITION_IDX]), Integer.parseInt(tokens[BOOKACADYEAR_IDX]), tokens[BOOKCOURSECODE_IDX], tokens[BOOKFACULTY_IDX]);
                    allListings.add(bookItem);
                }

            }
            return allListings;

        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        }
        finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
        return allListings;
    }


    public static List<String> readCsvToString(String fileName) {

        BufferedReader fileReader = null;
        //Create a new list of student to be filled by CSV file data
        List<String> allListings = new ArrayList<String>();

        try {



            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    //Create a new student object and fill his  data
                    Listing bookItem = new Listing(tokens[USERNAME_IDX], tokens[EMAIL_IDX], tokens[BOOKNAME_IDX], Integer.parseInt(tokens[BOOKEDITION_IDX]), Integer.parseInt(tokens[BOOKACADYEAR_IDX]), tokens[BOOKCOURSECODE_IDX], tokens[BOOKFACULTY_IDX]);
                    allListings.add(bookItem.toString());
                }

            }
            return allListings;

        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        }
        finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
        return allListings;

    }
    public static void removeListing(String username, int choice){
    	String inputFileName = "listings.csv";
    	String outputFileName = "listings1.csv";
    	int counter = 0;
    	
    	try {
    	    File inputFile = new File(inputFileName);
    	    File outputFile = new File(outputFileName);
    	    // Open the reader/writer, this ensure that's encapsulated
    	    // in a try-with-resource block, automatically closing
    	    // the resources regardless of how the block exists
    	    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    	             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
    	        // Read each line from the reader and compare it with
    	        // with the line to remove and write if required
    	    	
    	    	String line = null;
    	        while ((line = reader.readLine()) != null) {
    	        	counter += 1;
    	            if (choice == counter) {
    	                writer.write(line);
    	                writer.newLine();
    	            }
    	        }
    	    }
    	    // This is some magic, because of the compounding try blocks
    	    // this section will only be called if the above try block
    	    // exited without throwing an exception, so we're now safe
    	    // to update the input file

    	    // If you want two files at the end of his process, don't do
    	    // this, this assumes you want to update and replace the 
    	    // original file

    	    // Delete the original file, you might consider renaming it
    	    // to some backup file
    	    if (inputFile.delete()) {
    	        // Rename the output file to the input file
    	        if (!outputFile.renameTo(inputFile)) {
    	            throw new IOException("Could not rename " + outputFileName + " to " + inputFileName);
    	        }
    	    } else {
    	        throw new IOException("Could not delete original input file " + inputFileName);
    	    }
    	} catch (IOException ex) {
    	    // Handle any exceptions
    	    ex.printStackTrace();
    	}
    }
}
