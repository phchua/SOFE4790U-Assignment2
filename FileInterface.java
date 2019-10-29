import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface FileInterface extends Remote {

    public void createAccountService(String username, String password, int acadYear, String userEmail) throws RemoteException;

    public boolean checkUsername(String username) throws RemoteException;
    public boolean checkAccount(String username, String password) throws RemoteException;
    public void closeAccountService(String username) throws RemoteException;
    public void createListingService(String username, String bookName, int bookEdition, int bookAcadYear, String bookCourseCode, String bookFaculty) throws RemoteException;
    public void testremoveHashmap() throws RemoteException;
    public List<String> viewAllListings() throws RemoteException;
    public List<String> bookSearch(String searchString) throws RemoteException;
    public List<String> bookSearchInt(int searchInt) throws RemoteException;
    public List<String> bookSearchFac(String searchString) throws RemoteException;
    public List<String> bookSearchUser(String searchString) throws RemoteException;


   // all the functions go into fileimpl


}
