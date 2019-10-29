import java.io.*;
import java.rmi.*;
import java.rmi.server.RemoteServer;
import java.util.Scanner;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class FileClient {
	public static void main(String argv[]) {
		if(argv.length != 1) {
			System.out.println("Usage: java FileClient fileName");
			System.exit(0);
		}
		try {

			String name = "//" + argv[0] + "/FileServer";
			FileInterface fi = (FileInterface) Naming.lookup(name);
			String MANUAL = "----------------------------------------------------------------\n" +
					"Please choose a service by typing [1-8]:\n" +
					"1: Open a new buyer/seller account\n" +
					"2: Close an account\n" +
					"3: Create a listing\n" +
					"4: View all listings\n" +
					"5: Search listings\n" +
					"6: Remove listing\n" +
					"0: Stop the client\n";
			boolean shouldStop = false;
			String line = "";
			Scanner sc = new Scanner(System.in);
			while (!shouldStop)
			{
				System.out.printf(MANUAL);
				System.out.printf("Input choice: ");
				int userChoice = sc.nextInt();

				// User selects function from the menu
				// DataOutputStream out is used here for communication with the server
				switch (userChoice) {
				case 1:
					System.out.println("====================================");
					System.out.println("========== Create Account ==========");
					System.out.println("====================================");
					sc.nextLine();
					System.out.printf("Enter your username: ");
					String username = sc.nextLine();
					if(!fi.checkUsername(username)){
						System.out.println("Username used! Try another!");
						System.out.printf("Enter your username: ");
						username = sc.nextLine();
					}
					System.out.printf("Enter your password: ");
					String password = sc.nextLine();
					while (password.length() < 6) {
						System.out.println("Your password must be at least 6 characters long!");
						System.out.printf("Enter your password: ");
						password = sc.nextLine();
					}
					System.out.printf("Enter your current academic year: ");
					int acadYear = sc.nextInt();
					while (acadYear > 5 || acadYear < 0) {
						sc.nextLine();
						System.out.printf("Incorrect Acad Year!");
						System.out.printf("Enter your current year: ");
						acadYear = sc.nextInt();
					}
					sc.nextLine(); // throws away \n not consumed by nextInt
					System.out.printf("Enter your email: ");
					String userEmail = sc.nextLine();
					fi.createAccountService(username, password, acadYear,userEmail);
					System.out.println("Success! Account is created.\n"	);
					break;

				case 2:
				// asks for username and password if both match account will be removed
					System.out.println("===================================");
					System.out.println("========== Close Account ==========");
					System.out.println("===================================");
					sc.nextLine();
					Console console = System.console();
					System.out.printf("Enter username: ");
					String searchUser = sc.nextLine();
					System.out.printf("Please enter your password: ");
					char[] passwordChars = console.readPassword();
					String passwordString = new String(passwordChars);
					boolean result = fi.checkAccount(searchUser,passwordString);
					while (result){
						System.out.println("Incorrect username/password!");
						System.out.printf("Enter username: ");
						searchUser = sc.nextLine();
						System.out.printf("Please enter your password: ");
						passwordChars = console.readPassword();
						passwordString = new String(passwordChars);
						result = fi.checkAccount(searchUser,passwordString);
					}
					fi.closeAccountService(searchUser);
					System.out.println("Account successfully removed!");
					break;

				case 3:
				// creates a listing data stored prolly within account
					System.out.println("======================================");
					System.out.println("========== Create a listing ==========");
					System.out.println("======================================");
					console = System.console();
					// login
					sc.nextLine();
					System.out.printf("Enter username: ");
					searchUser = sc.nextLine();
					console.printf("Please enter your password: ");
					passwordChars = console.readPassword();
					passwordString = new String(passwordChars);
					result = fi.checkAccount(searchUser,passwordString);
					while (result){
						System.out.println("Incorrect username/password!");
						System.out.printf("Enter username: ");
						searchUser = sc.nextLine();
						console.printf("Please enter your password: ");
						passwordChars = console.readPassword();
						passwordString = new String(passwordChars);
						result = fi.checkAccount(searchUser,passwordString);
					}
					System.out.printf("Enter book title: ");
					String bookName = sc.nextLine();
					System.out.printf("Enter book edition: ");
					int bookEdition = sc.nextInt();
					System.out.printf("Enter intended academic year: ");
					sc.nextLine();
					int bookAcadYear = sc.nextInt();
					sc.nextLine();
					System.out.printf("Enter intended course code: ");
					String bookCourseCode = sc.nextLine();
					System.out.printf("Enter intended faculty: ");
					String bookFaculty = sc.nextLine();
					System.out.println("Adding listing...");
					fi.createListingService(searchUser, bookName, bookEdition, bookAcadYear, bookCourseCode, bookFaculty);
					System.out.println("Listing added: "+bookName);
					break;
				case 4:
				// view all own listings
					sc.nextLine();
					System.out.println("======================================");
					System.out.println("========= View all listings ==========");
					System.out.println("======================================");
					System.out.println("Showing all listings");
			        List<String> fullBooklist = new ArrayList<String>();
					fullBooklist = fi.viewAllListings();
					for (String bookList : fullBooklist) {
		        		System.out.println(bookList);
		      		}
					break;
				case 5:
				// search the list for listings
					sc.nextLine();
					System.out.println("====================================");
					System.out.println("========= Search listings ==========");
					System.out.println("====================================");
					boolean searchBool = false;
					while (!searchBool){
						System.out.println("Choose category of search: ");
						System.out.println("1. Book name ");
						System.out.println("2. Acad Year: ");
						System.out.println("3. Faculty  ");
						System.out.println("4. Quit  ");
						int choice = sc.nextInt();
						sc.nextLine();
						switch(choice){
							case 1:
								System.out.printf("Type in book title: ");
								String searchString = sc.nextLine();
								List<String> results = new ArrayList<String>();
								results = fi.bookSearch(searchString);
								if (results.isEmpty()){
									System.out.println("Sorry your search returned no results!");
								}
								else{
									for (String i : results){
										System.out.println(i);
									}
								}
								break;
							case 2:
							 	System.out.printf("Type in acad year: ");
								int searchInt = sc.nextInt();
								sc.nextLine();
								results = new ArrayList<String>();
								results = fi.bookSearchInt(searchInt);
								if (results.isEmpty()){
									System.out.println("Sorry your search returned no results!");
								}
								else{
									for (String i : results){
										System.out.println(i);
									}
								}
								break;
							case 3:
								System.out.printf("Type in faculty: ");
								searchString = sc.nextLine();
								results = new ArrayList<String>();
								results = fi.bookSearchFac(searchString);
								if (results.isEmpty()){
									System.out.println("Sorry your search returned no results!");
								}
								else{
									for (String i : results){
										System.out.println(i);
									}
								}
								break;
							case 4:
								searchBool = true;
								break;
							default:
								System.out.println("Invalid choice!");
								break;
						}
					}
					break;
				case 6:
				// add to cart (gives email)
					sc.nextLine();
					System.out.println("=====================================");
					System.out.println("========= View my listings ==========");
					System.out.println("=====================================");
					System.out.printf("Enter your username: ");
					String user = sc.nextLine();
					List<String> results = new ArrayList<String>();
					results = fi.bookSearchUser(user);
					if (results.isEmpty()){
						System.out.println("Sorry you do not have any listings");
					}
					else{
						for (String i : results){
							System.out.println(i);
						}
					}

					break;
				case 0:
					fi.testremoveHashmap();
					shouldStop = true;
					break;
				default:
					System.out.println("Invalid choice!");
					break;
				}
			}
		}
			catch(Exception e) {
				System.err.println("FileServer exception: "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
