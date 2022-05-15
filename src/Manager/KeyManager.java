package Manager;
import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class KeyManager extends UserDAO implements I_KeyManagement {
	private static String databasename = "safekeydb";
	//private String name, password;

	ArrayList<String> inputs = new ArrayList<String>();
	
	public Connection getConnection(){
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbcc:mysql://localhost:8080/'"+databasename+"'";
			String username = "root";
			String password = "";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url);
			System.out.println("Connected");
			return con;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public void addPassword(String namePass, String password) throws Exception{
		// gets name of account for password (ex: Email, Instagram, School)
		// gets added password from user
		// EX: Email: sampleEmail123@gmail.com Password: fakepassword890
		// calls encrypt
		String hashedpass = encrypt(password);
		try {
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement("insert into safekeydb (user_id, password) values('"+namePass+"', '"+hashedpass+"'");
			posted.execute();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Password has been encrypted and added to DB");
		}
	}
	
	
	
	public void editPassword(String newPass, String user, String oldpass) {
		// gets edited password from user
		// calls encrypt
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE password FROM '"+databasename+"' SET '"+newPass+"' WHERE EXISTS(user_id = '"+user+"' AND password = '"+oldpass+")");
			statement.execute();
			statement.close();
			con.close();
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	public ArrayList<String> viewAllPasswords(String user) {
		// gets all passwords from database		
		// decrypts
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT password FROM '"+databasename+"' p WHERE EXISTS(p.user_id = '"+user+"')");
			
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()) {
				String addtoarray = decrypt(result.getString("password"));
				array.add(addtoarray);
			}
			System.out.println("Array has been filled with passwords");
			statement.close();
			con.close();
			return array;
		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
	
	public void deleteAPassword(String passwordName) {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM '"+databasename+"' WHERE password = '"+passwordName+"'");
			statement.execute();
			statement.close();
			con.close();
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	public String encrypt(String pass) throws Exception, Exception {
		// encrypts password
		// stores in database
		
		//create key and cipher
		String key = "Bar12345Bar12345";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		
		//encrypt
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] encrypted = cipher.doFinal(pass.getBytes());
		return new String(encrypted);
	}
	
	public String decrypt(String encryptedString) throws Exception, Exception {
		// will only be called from view all (unless we have a search bar to search what password we want)
		// decrypts password
		// display
		// will have a loop here to decrypt all passwords until there are no more passwords
		
		//string to byte array
		byte[] b = encryptedString.getBytes();
		
		//create key and cipher
		String key = "Bar12345Bar12345";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		
		//decrypt
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		String decrypted = new String(cipher.doFinal(b));
		return new String(decrypted);
	}
}
