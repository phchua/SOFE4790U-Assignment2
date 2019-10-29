import java.io.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public class FileImpl extends UnicastRemoteObject
implements FileInterface {

    private String name;
    public static HashMap<String, Account> AllTheAccounts;

    public FileImpl(String s) throws RemoteException{
        super();
        name = s;
        AllTheAccounts = new HashMap<>();

    }

    // todo list
    // rest of the commmands
    // clean up code
    // comment

    public void createAccountService(String username, String password, int acadYear, String userEmail){
        // set password instead of pin
        int accID = ThreadLocalRandom.current().nextInt(1000,9999);

        // message sent to server as information
        Account newAcc = new Account(accID, username, password, acadYear,userEmail);

        // the newly created account is added into the hashmap
        AllTheAccounts.put(username,newAcc);
        System.out.println("New Account added!");
        System.out.println("Username: "+username);
        System.out.println("Email: "+userEmail+"\n");

    }

    // checks whether username has been used
    public boolean checkUsername(String username){
        Account checkU = AllTheAccounts.get(username);
        if(checkU == null){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkAccount(String username, String password){
        Account checkA = AllTheAccounts.get(username);
        if(checkA == null){
            return true;
        }
        else{
            String checkP = checkA.getPassword();
            if (checkP.equals(password)){
                return false;
            }
            else {
                return true;
            }
        }
    }

    public void closeAccountService(String username){
        AllTheAccounts.remove(username);
        System.out.println("Account deleted!");
        System.out.println("Username: "+username);

    }

    public void createListingService(String username, String bookName, int bookEdition, int bookAcadYear, String bookCourseCode, String bookFaculty){
        Account listingAccount = AllTheAccounts.get(username);
        String userEmail = listingAccount.getEmail();
        // message sent to server as information
        Listing newListing = new Listing(username, userEmail, bookName, bookEdition, bookAcadYear, bookCourseCode, bookFaculty);

        // the newly created listing is written into the csv
        CSVhandler.writeCsvFile("listings.csv", newListing);
        System.out.println("New Listing added!");
        System.out.println("Username: "+newListing.getUsername());
        System.out.println("Book Name: "+bookName+"\n");


    }
    public List<String> viewAllListings(){
        return CSVhandler.readCsvToString("listings.csv");

    }
    public List<String> bookSearch(String searchString){
        List<Listing> fullBooklist = new ArrayList<Listing>();
        fullBooklist = CSVhandler.readCsvFile("listings.csv");
        List<String> searchResults = new ArrayList<String>();

        for (Listing bookItem : fullBooklist){
            if(bookItem.getBookName().equals(searchString)){
                searchResults.add(bookItem.toString());
            }
        }
        return searchResults;
    }
    public List<String> bookSearchInt(int searchInt){
        List<Listing> fullBooklist = new ArrayList<Listing>();
        fullBooklist = CSVhandler.readCsvFile("listings.csv");
        List<String> searchResults = new ArrayList<String>();

        for (Listing bookItem : fullBooklist){
            if(bookItem.getBookAcadYear() == searchInt){
                searchResults.add(bookItem.toString());
            }
        }
        return searchResults;
    }

    public List<String> bookSearchFac(String searchString){
        List<Listing> fullBooklist = new ArrayList<Listing>();
        fullBooklist = CSVhandler.readCsvFile("listings.csv");
        List<String> searchResults = new ArrayList<String>();

        for (Listing bookItem : fullBooklist){
            if(bookItem.getBookFaculty().equals(searchString)){
                searchResults.add(bookItem.toString());
            }
        }
        return searchResults;

    }


    public List<String> bookSearchUser(String searchString){
        List<Listing> fullBooklist = new ArrayList<Listing>();
        fullBooklist = CSVhandler.readCsvFile("listings.csv");
        List<String> searchResults = new ArrayList<String>();
        for (Listing bookItem : fullBooklist){
            if(bookItem.getUsername().equals(searchString)){
                searchResults.add(bookItem.toString());
            }
        }
        return searchResults;

    }


    public void testremoveHashmap(){
        // AllTheAccounts.contains();
        AllTheAccounts.clear();
        System.out.println(AllTheAccounts);
        System.out.println("Hashmap is empty: "+ AllTheAccounts.isEmpty());
    }












}
