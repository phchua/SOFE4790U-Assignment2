public class Listing{
	private Account listingAccount;
	private String username;
	private String userEmail;
	private String bookName;
	private int bookEdition;
	private int bookAcadYear;
	private String bookCourseCode;
	private String bookFaculty;

	public Listing(){}
	public Listing(String username, String userEmail, String bookName, int bookEdition, int bookAcadYear, String bookCourseCode, String bookFaculty) {
		super();
		this.username = username;
		this.userEmail = userEmail;

		this.bookName = bookName;
		this.bookEdition = bookEdition;
		this.bookAcadYear = bookAcadYear;
		this.bookCourseCode = bookCourseCode;
		this.bookFaculty = bookFaculty;

	}
	@Override
	public String toString() {
		return "Username= " + username + ", Email= " + userEmail
				+ ", Book Title= " + bookName + ", Edition= " + bookEdition + ", Acad Yr= " + bookAcadYear + ", Course Code= " + bookCourseCode + ", Faculty= " + bookFaculty + "]\n";
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return userEmail;
	}

	public String getBookName() {
		return bookName;
	}
	public int getbookEdition() {
		return bookEdition;
	}
	public int getBookAcadYear() {
		return bookAcadYear;
	}
	public String getBookCourseCode(){
		return bookCourseCode;
	}
	public String getBookFaculty(){
		return bookFaculty;
	}

}
