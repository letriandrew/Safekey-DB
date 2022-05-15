package Manager;
import java.sql.Connection;
import java.util.ArrayList;

public interface I_KeyManagement {
	Connection getConnection();
	void addPassword(String namePass, String password) throws Exception;
	void editPassword(String newPass, String user, String oldpass);
	ArrayList<String> viewAllPasswords(String user);
	void deleteAPassword(String passwordName);
	String encrypt(String pass) throws Exception, Exception;
	String decrypt(String encryptedString) throws Exception, Exception;
}
